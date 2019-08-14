package com.sl.demo.server.util;

import com.sl.domain.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	private static String algorithmName = "md5";

	private static int hashIterations = 2;

	private static boolean storedCredentialsHexEncoded = true;

	public static void encryptPassword(User user) {

		user.setSalt(randomNumberGenerator.nextBytes().toHex());

		SimpleHash hash = new SimpleHash(algorithmName, user.getPassword(),
				// ByteSource.Util.bytes(user.getCredentialsSalt()),
				ByteSource.Util.bytes(user.getSalt()), hashIterations);
		String newPassword = null;

		if (storedCredentialsHexEncoded) {
			newPassword = hash.toHex();
		}
		else {
			newPassword = hash.toBase64();
		}
		user.setPassword(newPassword);
	}

	public static boolean checkPassword(User user, String tryPassword) {

		SimpleHash hash = new SimpleHash(algorithmName, tryPassword,
				// ByteSource.Util.bytes(user.getCredentialsSalt()),
				ByteSource.Util.bytes(user.getSalt()), hashIterations);
		String entryptedPassword = null;

		if (storedCredentialsHexEncoded) {
			entryptedPassword = hash.toHex();
		}
		else {
			entryptedPassword = hash.toBase64();
		}
		return entryptedPassword.equals(user.getPassword());
	}

	public static String encryptTryPassword(User user, String tryPassword) {

		SimpleHash hash = new SimpleHash(algorithmName, tryPassword,
				// ByteSource.Util.bytes(user.getCredentialsSalt()),
				ByteSource.Util.bytes(user.getSalt()), hashIterations);
		String entryptedPassword = null;

		if (storedCredentialsHexEncoded) {
			entryptedPassword = hash.toHex();
		}
		else {
			entryptedPassword = hash.toBase64();
		}
		return entryptedPassword;
	}

	public static void main(String[] args) {

		User user = new User();
		user.setPassword("test123");
		PasswordHelper.encryptPassword(user);
		System.out.println("salt: " + user.getSalt());
		System.out.println("After encrypted: " + user.getPassword());

		if (PasswordHelper.checkPassword(user, "test123")) {
			System.out.println("pass");
		}
	}

	public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {

		PasswordHelper.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {

		PasswordHelper.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {

		PasswordHelper.hashIterations = hashIterations;
	}

	public boolean isStoredCredentialsHexEncoded() {

		return storedCredentialsHexEncoded;
	}

	public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {

		PasswordHelper.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
	}
}
