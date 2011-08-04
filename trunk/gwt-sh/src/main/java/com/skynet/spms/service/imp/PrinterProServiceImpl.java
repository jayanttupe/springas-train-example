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
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.skynet.spms.service.PrinterEnum;
import com.skynet.spms.service.PrinterProService;

@Component
public class PrinterProServiceImpl implements PrinterProService {

	private static final int BUF = 1024;

	private final Map<PrinterEnum, SocketAddress> addressMap = new EnumMap<PrinterEnum, SocketAddress>(
			PrinterEnum.class);

	@Autowired
	private ResourceLoader loader;

	private ExecutorService executor = new ScheduledThreadPoolExecutor(1);

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

			addressMap.put(type, remote);
		}
	}

	@Async
	@Override
	public Future<String> doPrint(final PrinterEnum serviceName,
			String printOperStr) throws IOException {

		final SocketChannel chan = SocketChannel.open();
		try {
			chan.connect(addressMap.get(serviceName));
			chan.configureBlocking(true);
			byte[] bytes = printOperStr.getBytes("UTF-8");

			ByteBuffer buf = ByteBuffer.wrap(bytes);
			while (buf.remaining() > 0) {
				chan.write(buf);
			}
			
			return executor.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					ByteBuffer reader = ByteBuffer.allocate(BUF);
					int len = chan.read(reader);
					String ret = new String(reader.array(), 0, len);
					chan.close();
					return ret;
				}
			});
		} catch (IOException e) {
			IOUtils.closeQuietly(chan);
			throw e;
		}
	}
}
