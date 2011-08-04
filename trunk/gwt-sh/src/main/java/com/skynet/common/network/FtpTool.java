package com.skynet.common.network;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPConnectionClosedException;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpTool {

	private Logger log = LoggerFactory.getLogger(FtpTool.class);

	private FTPClient client;

	public void list(String server) throws IOException {
		try {
			int reply;
			client.connect(server);
			log.info("Connected to " + server + ".");
			log.info(client.getReplyString());

			// After connection attempt, you should check the reply code to
			// verify
			// success.
			reply = client.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				client.disconnect();
				log.error("FTP server refused connection.");
				throw new FTPConnectionClosedException();
			}

			client.logout();
		} catch (IOException e) {
			log.error("io oper error:", e);
			throw e;
		} finally {
			if (client.isConnected()) {
				try {
					client.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
		}

	}

	public void put() {

	}

	public void get() {

	}
}
