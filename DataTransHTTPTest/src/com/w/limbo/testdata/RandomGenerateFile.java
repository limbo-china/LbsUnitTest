package com.w.limbo.testdata;

import java.io.FileWriter;
import java.util.Random;

public class RandomGenerateFile {

	private static final Random random = new Random();
	private static final String number = "0123456789";
	private static final int MSISDN_LENGTH = 13;
	private static final int IMEI_LENGTH = 14;
	private static final int IMSI_LENGTH = 15;
	private static final int REGIONCODE_LENGTH = 6;
	private static final int LACCI_LENGTH = 4;
	private static final int PHONE_DATA_SIZE = 10000;
	private static final int POSITION_DATA_SIZE = 1000;

	public static void generatePhoneFile(String filename) throws Exception {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			for (int i = 0; i < PHONE_DATA_SIZE; i++) {
				fw.write(randomMsisdn() + ";");
				fw.write(randomImsi() + ";");
				fw.write(randomImei() + ";");
				fw.write(randomRegionCode() + "\n");
			}
			fw.flush();
		} finally {
			fw.close();
		}
	}

	public static void generatePositionFile(String filename) throws Exception {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filename, false);
			for (int i = 0; i < POSITION_DATA_SIZE; i++) {
				fw.write(randomUli() + ";");
				fw.write(randomLacCi() + ";");
				fw.write(randomLacCi() + ";");
				fw.write(randomRegionCode() + "\n");
			}
			fw.flush();
		} finally {
			fw.close();
		}
	}

	public static String randomMsisdn() {
		return "861" + randomNumberString(MSISDN_LENGTH - 3);
	}

	public static String randomImsi() {
		return "460" + randomNumberString(IMSI_LENGTH - 3);
	}

	public static String randomImei() {
		return randomNumberString(IMEI_LENGTH);
	}

	public static String randomRegionCode() {
		return randomRegionString(REGIONCODE_LENGTH);
	}

	public static String randomLacCi() {
		return randomRegionString(LACCI_LENGTH);
	}

	public static String randomUli() {
		return "460" + "-" + randomRegionString(2) + "-"
				+ randomRegionString(REGIONCODE_LENGTH) + "-"
				+ randomRegionString(2);
	}

	public static String randomNumberString(int length) {
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			str[i] = randomNumberChar();
		}
		return new String(str);
	}

	public static String randomRegionString(int length) {
		char[] str = new char[length];
		for (int i = 0; i < length; i++) {
			str[i] = randomRegionChar();
		}
		return new String(str);
	}

	public static char randomNumberChar() {
		return number.charAt(random.nextInt(number.length()));
	}

	public static char randomRegionChar() {
		return number.charAt(1 + random.nextInt(5));
	}

	public static void main(String[] args) {
		try {
			generatePhoneFile("phone.txt");
			generatePositionFile("position.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
