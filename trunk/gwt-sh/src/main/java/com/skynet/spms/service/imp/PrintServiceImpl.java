package com.skynet.spms.service.imp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.EnumMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterService;

@Component
public class PrintServiceImpl implements PrinterService {

	private static final int BUF = 1024;

	private final Map<PrinterEnum, SocketChannel> channelMap = new EnumMap<PrinterEnum, SocketChannel>(
			PrinterEnum.class);

	private final Map<PrinterEnum, SocketAddress> addressMap = new EnumMap<PrinterEnum, SocketAddress>(
			PrinterEnum.class);

	@Autowired
	private ResourceLoader loader;

	private ExecutorService executor = Executors.newSingleThreadExecutor();

	@PostConstruct
	public void initPrintInfo() throws IOException {

		Resource resource = loader
				.getResource("classpath:application.properties");

		Properties prop = new Properties();
		prop.load(resource.getInputStream());

		for (PrinterEnum type : PrinterEnum.values()) {
			String name = type.name();
			String ip = prop.getProperty("printer." + name + ".ip");
			String host = prop.getProperty("printer." + name + ".host", "9100");

			SocketAddress remote = new InetSocketAddress(ip,
					Integer.parseInt(host));

//			SocketChannel sChannel = SocketChannel.open();
//			sChannel.configureBlocking(true);
			addressMap.put(type, remote);
//			channelMap.put(type, sChannel);
		}
		
	}

	@Async
	@Override
	public Future<String> doPrint(final PrinterEnum serviceName,
			final String printOperStr) throws IOException {

		Future<String> future= executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				SocketChannel chan = SocketChannel.open();
				try {
					
					chan.connect(addressMap.get(serviceName));
					chan.configureBlocking(true);
					
					byte[] bytes = printOperStr.getBytes("UTF-8");

					ByteBuffer buf = ByteBuffer.wrap(bytes);
					while (buf.remaining() > 0) {
						chan.write(buf);
					}
					Thread.sleep(1000);
					StringBuilder strBuf = new StringBuilder();					
					ByteBuffer reader = ByteBuffer.allocate(BUF);
					
					while (chan.read(reader) > 0) {
						
						int remain=reader.remaining();
						
						reader.flip();
						strBuf.append(new String(reader.array()));
						reader.clear();
						
						if(remain>0){
							break;
						}
					}
//					chan.finishConnect();
					Thread.sleep(1000);
					chan.close();
					return strBuf.toString();
				} catch (IOException e) {

					IOUtils.closeQuietly(chan);
					throw e;
				}

			}

		});
		return future;

	}

}
