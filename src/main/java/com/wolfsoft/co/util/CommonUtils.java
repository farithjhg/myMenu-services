package com.wolfsoft.co.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

public class CommonUtils {
	final static String PROPERTY_FILE_PATH = "conf/data.properties";
	
	/**
	 * Get App UserId
	 * @return
	 */
	public static String getAppUser(){
		String appUser =  KEY+KEY+KEY; 
		String hashData = hashData(appUser);
		return hashData ;
	}
	
	/**
	 * Generates hash from the given input string. provided algorithm is used,
	 * it is one-way algorithm, hashed value can't be restored back it shouldn't
	 * be used in places where original value should be restored later.
	 * Generated binary value is returned as hexadecimal string value. This
	 * 
	 * @param data
	 *            string to be hashed
	 * @param algorithm
	 *            valid values are MD5, SHA-256, SHA-384 & SHA-512
	 * @return hashed value as hexadecimal string
	 */
	public static String hashData(String data, String algorithm) {
		try {
			return hashData(data.getBytes("UTF-8"), algorithm);
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String hashData(byte[] data, String algorithm) {

		MessageDigest md;

		try {
			md = MessageDigest.getInstance(algorithm); // step 2
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		md.update(data); // step 3

		byte raw[] = md.digest(); // step 4

		StringBuilder hexString = new StringBuilder();

		for (int i = 0; i < raw.length; i++) {
			int b = (int) raw[i] & 0xFF;
			String hex = Integer.toHexString(b);

			if (hex.length() == 1)
				hexString.append("0");
			hexString.append(hex);
		}

		return hexString.toString();
	}

	/**
	 * Generates SHA1 signature from the given input string.
	 * 
	 * @param data
	 *            string to be hashed
	 * @return hashed value as array of bytes
	 */
	public static byte[] generateSHA1Signature(String data) {

		MessageDigest md;

		try {
			md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		try {
			md.update(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		return md.digest();
	}

	public static String generateSHA1StrSignatureHex(String data) {
		byte[] sha1 = generateSHA1Signature(data);

		return new String(Hex.encodeHex(sha1));
	}

	public static String generateSHA1StrSignatureBase64(String data) {
		byte[] sha1 = generateSHA1Signature(data);

		return new String(Base64.encodeBase64(sha1));
	}

	/**
	 * Get a property value from the property file
	 * 
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		return getProperty(PROPERTY_FILE_PATH, property);
	}

	/**
	 * Get a property value from the property file
	 * 
	 * @param propertyFilePath
	 * @param property
	 * @return
	 */
	public static String getProperty(String propertyFilePath, String property) {
		Properties prop = new Properties();
		InputStream input = null;
		String propertyValue = "";
		try {
			input = new FileInputStream(propertyFilePath);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			propertyValue = prop.getProperty(property);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return propertyValue;
	}

	/**
	 * Format a String as XML
	 * 
	 * @param xml
	 * @return
	 */
	public static String formatXML(String xml) {

		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

			// May need this:
			// System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");

			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); 
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); 

			return writer.writeToString(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String hashData(String data) {
		return hashData(data, "MD5");
	}

	final static String KEY = "ABCDEF0123456789876543210fedcba";
}