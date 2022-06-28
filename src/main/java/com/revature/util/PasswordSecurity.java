package com.revature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordSecurity
{
	public static boolean comparePassword(String hashedPassword, String password)
	{
		String[] array = hashedPassword.split("\\.");
		String hash = hashPassword(password, hexToByte(array[1]));
		if (hash.equals(array[0]))
		{
			return true;
		}
		return false;
	}
	
	// returns "salted hash(128 chars).salt(32 chars)"
	public static String getHashedPassword(String password)
	{
		String result = "";
		try
		{
			byte[] salt = salt();
			String saltedHash = hashPassword(password, salt);
			result =  String.format("%s.%s",saltedHash, byteToHex(salt));
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	private static String hashPassword(String passwordToHash, byte[] salt)
	{
		String generatedPassword = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			// add salt
			md.update(salt);
			// digest string to get a byte array
			byte[] bytes = md.digest(passwordToHash.getBytes());
			// convert byte array to HEX string
			generatedPassword = byteToHex(bytes);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	private static String byteToHex(byte[] bytes)
	{
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
		{
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	private static byte[] hexToByte(String hex)
	{
		byte[] bytes = new byte[16];
		String h = "";
		for (int i = 0; i < bytes.length; i++)
		{
			h = hex.substring(2 * i, 2 * i + 2);
			bytes[i] = (byte) Integer.parseInt(h, 16);
		}
		return bytes;
	}

	private static byte[] salt() throws NoSuchAlgorithmException
	{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
}
