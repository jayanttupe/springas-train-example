package com.skynet.spms.service.imp;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.skynet.common.strtemplate.TemplateGroup;
import com.skynet.common.strtemplate.TemplateTool;
import com.skynet.common.strtemplate.TemplateToolFactory;
import com.skynet.spms.service.UUIDGeneral;

@Component
public class UUIDGeneralImp implements UUIDGeneral {


	private static final int MONTH_SIZE = 2;

	private Logger log = LoggerFactory.getLogger(UUIDGeneralImp.class);

	@Value("${uuid.persistenFile}")
	private String persistenFile;

	private ConcurrentMap<String, Integer> seqMap = new ConcurrentHashMap<String, Integer>();
	
	private Integer currMonth;
	@Autowired
	private TemplateToolFactory templateFactory;

	private TemplateGroup group;
	
	private FileChannel fileChannel;

	@PostConstruct
	public void init() throws IOException {

		File file = new File(persistenFile);
		if (!file.exists()) {

			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			file.createNewFile();
		}
		
		fileChannel = new RandomAccessFile(file,"rwd").getChannel();
		
		currMonth=getMonthVal();
		
		ByteBuffer bytes=ByteBuffer.allocate(recordSize);		
		int index=-1;
		while(fileChannel.read(bytes)!=-1){
			String fullStr=new String(bytes.array());
			
			bytes.clear();
			String type = StringUtils.trim(StringUtils.substring(fullStr,0,20));
			String seq = StringUtils.trim(StringUtils.substring(fullStr,20,40));
			
			index++;			
			if(StringUtils.isNotBlank(type)&&
					StringUtils.isNotBlank(seq)&&
					StringUtils.isNumeric(seq)){
				seqMap.put(type, index);
			}			
			
		}	

		group = templateFactory.getTemplateGroup("sequence");
		group.addFloatFormat("num", "#.###");
		group.addIntFormat("seq", "#####");	
		
	}

	private int getMonthVal() throws IOException {
		ByteBuffer months=ByteBuffer.allocate(MONTH_SIZE);		
		int sign=fileChannel.read(months);
		if(sign!=-1){
			return Integer.parseInt(new String(months.array()));
		}else{
			int monthVal=Calendar.getInstance().get(Calendar.MONTH);
			
			writeMonthVal(monthVal);
			return monthVal;
		}
	}

	private void writeMonthVal(int monthVal) throws IOException {
		String strRecord=String.format(MONTH_FORMAT,monthVal);		
		ByteBuffer dst=ByteBuffer.wrap(strRecord.getBytes());
		fileChannel.write(dst,0);
		fileChannel.position(MONTH_SIZE);
	}
	
	@PreDestroy
	public void close(){
		
		IOUtils.closeQuietly(fileChannel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.skynet.spms.service.imp.UUIDGeneral#getSequence(java.lang.String)
	 */
	@Override
	public String getSequence(String type) {
		
		Calendar now=Calendar.getInstance();
		int month=now.get(Calendar.MONTH);
		
		synchronized(currMonth){
			if(currMonth.intValue()!=month){
				resetSeq(month);
			}
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("createDate",now.getTime() );
		paramMap.put("type", type);
				
		long seq=getNextSeq(type);		
		paramMap.put("seq", seq);

		TemplateTool tmpTool = group.getTemplateTool("seq");

		return tmpTool.gener(paramMap);

	}

	private long getNextSeq(String type) {
		
		Integer newPos = new Integer(seqMap.size());

		Integer currPos = seqMap.putIfAbsent(type, newPos);

		try{
			if (currPos == null) {
				return appendSeqByNio(type,newPos*recordSize+MONTH_SIZE);
			} else {				
				return modifySeqByNio(type,currPos*recordSize+MONTH_SIZE);
			}	
		}catch(IOException e){
			throw new IllegalStateException(e);
		}

	}
	
	private final static int recordSize=40;
	
	private static final String FORMAT = "%1$-20s%2$020d";
	
	private static final String MONTH_FORMAT = "%1$02d";

	private  void resetSeq(int monthVal){
		
		currMonth=monthVal;
		seqMap.clear();		
		try{
			fileChannel.truncate(2);
			writeMonthVal(monthVal);
		}catch(IOException e){
			throw new IllegalStateException(e);
		}
		
	}
	
	private long appendSeqByNio(String type,int startPos) throws IOException{
				
		String strRecord=String.format(FORMAT, type,1);
		ByteBuffer dst=ByteBuffer.wrap(strRecord.getBytes());
		
		FileLock lock=fileChannel.lock(startPos,recordSize,false);
		fileChannel.write(dst, startPos);
		lock.release();
		return 1;
	}
	
	private long  modifySeqByNio(String type,int startPos) throws IOException{
		FileLock lock=fileChannel.lock(startPos, recordSize, false);
		
		ByteBuffer dst=ByteBuffer.allocate(recordSize);
		
		fileChannel.read(dst, startPos);
				
		dst.rewind();	
		byte[] byteArray=new byte[recordSize];			
		dst.get(byteArray);		
		String str=new String(byteArray);
				
		long seq=Long.parseLong(str.substring(20));
		seq+=1;		
		String newSeq=String.format("%1$020d",seq);
				
		dst.position(20);
		dst.put(newSeq.getBytes());
				
		dst.flip();
		fileChannel.write(dst,startPos);	
		lock.release();
		return seq;
	}

	@Override
	public String getPrintSequence() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
			
		long seq=getNextSeq("printSeq");
		if(seq==0){
			seq=getNextSeq("printSeq");
		}
		paramMap.put("seq", seq);

		TemplateTool tmpTool = group.getTemplateTool("printSeq");

		return tmpTool.gener(paramMap);

	}
}
//	private void serialByNio(String type) throws IOException{
//		
//		int startPos=seqMap.get(type).getSegOffset();
//	
//		FileLock lock=fileChannel.lock(startPos, recordSize, false);
//		
//		long seq=seqMap.get(type).getCurrSeq();
//
//		String str=String.format(FORMAT, type,seq);
//		
//		ByteBuffer dst=ByteBuffer.wrap(str.getBytes(),0,recordSize);
//		
//		fileChannel.write(dst,startPos);
//		
//		lock.release();
//	}

//	private void serial(String type) {
//
//
//		List<String> lineList = new ArrayList<String>();
//		for (Map.Entry<String, AtomicLong> entry : seqMap.entrySet()) {
//			String fullLine = entry.getKey() + "="
//					+ entry.getValue().longValue();
//			lineList.add(fullLine);
//		}
//
//		OutputStream output = null;
//		try {
//			File file = new File(persistenFile);
//			output = new FileOutputStream(file);
//			IOUtils.writeLines(lineList, null, output);
//		} catch (IOException e) {
//			log.warn("write seq info fail,seq map:" + seqMap.toString());
//		} finally {
//			IOUtils.closeQuietly(output);
//
//		}
//
//	}

//	private class Record{
//		private AtomicLong seq;
//		
//		private final int index;
//		
//		Record(int idx){
//			this.index=idx;
//			this.seq=new AtomicLong(0);
//		}
//		
//		public long getCurrSeq() {
//			return seq.longValue();
//		}
//
//		public long getNewSeq(){
//			return seq.incrementAndGet();
//		}
//		
//		public int getSegOffset(){
//			return index*recordSize;
//		}
//	}
//}
