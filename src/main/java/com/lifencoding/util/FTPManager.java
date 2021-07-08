package com.lifencoding.util;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;

public class FTPManager {
	private String host;
	private String username;
	private String password;

	public FTPManager(String host, String username, String password) {
		this.host = host;
		this.username = username;
		this.password = password;
	}

	public FTPClient connect() {
		FTPClient client = new FTPClient();
		client.setControlEncoding("UTF-8");

		FTPClientConfig config = new FTPClientConfig();
		config.setServerTimeZoneId("Asia/Seoul");
		client.configure(config);

		try {
			client.connect(host, 21);
			if(client.login(username, password)) {
				client.setFileType(FTP.BINARY_FILE_TYPE);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return client;
	}

	public void disconnect(FTPClient client) {
		if (client != null && client.isConnected()) {
			try {
				client.disconnect();
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
