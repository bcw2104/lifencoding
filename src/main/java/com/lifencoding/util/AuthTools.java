package com.lifencoding.util;

import java.security.MessageDigest;
import java.util.Random;

public class AuthTools {

	public String convertValuetoHash(String message) throws Exception {
		MessageDigest digest= MessageDigest.getInstance("SHA-256");
		StringBuffer hashMsg = new StringBuffer();

		digest.reset();

		digest.update(message.getBytes("UTF-8"));

		byte[] hash = digest.digest();

		for(int i=0; i<hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);

			if(hex.length() == 1) {
				hashMsg.append(0);
			}
			hashMsg.append(hex);
		}

		return hashMsg.toString();
	}

	public String makeRandomString(int length) {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();
			for (int i = 0; i < length; i++) {
				int rIndex = rnd.nextInt(3);
				switch (rIndex) {
				case 0:
					// a-z
					key.append((char) (rnd.nextInt(26) + 97));
					break;
				case 1:
					// A-Z
					key.append((char) (rnd.nextInt(26) + 65));
					break;
				case 2:
					// 0-9
					key.append((rnd.nextInt(10)));
					break;
				}
			}

		return key.toString();
	}
}
