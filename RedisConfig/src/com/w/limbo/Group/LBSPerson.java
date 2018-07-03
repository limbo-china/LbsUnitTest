package com.w.limbo.Group;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;

public class LBSPerson {

	private static FileInputStream phoneInput;
	private static Scanner phoneScan;
	private static List<String> phoneList;
	private static Random random = new Random();
	static {
		try {
			phoneInput = new FileInputStream("msisdn.txt");
			phoneScan = new Scanner(phoneInput, "UTF-8");
			phoneList = new ArrayList<String>();
			while (phoneScan.hasNext()) {
				String line = phoneScan.nextLine();
				phoneList.add(line.split(";")[0] + ";" + line.split(";")[1]
						+ ";" + line.split(";")[2]);
			}
			phoneInput.close();
			phoneScan.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String phone;
	private String imsi;
	private String imei;
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public static String generateOnePersonJson() {
		Gson gson = new Gson();
		return gson.toJson(generateOnePerson());
	}

	private static LBSPerson generateOnePerson() {
		LBSPerson person = new LBSPerson();
		person.setPhone(phoneList.get(random.nextInt(phoneList.size())).split(
				";")[0]);
		person.setImsi(phoneList.get(random.nextInt(phoneList.size())).split(
				";")[1]);
		person.setImei(phoneList.get(random.nextInt(phoneList.size())).split(
				";")[2]);
		person.setCreateBy("testCreateBy");
		person.setCreateTime(stampToDate(System.currentTimeMillis()));
		person.setUpdateBy("testUpdateBy");
		person.setUpdateTime(stampToDate(System.currentTimeMillis()));

		return person;
	}

	private static String stampToDate(long stamp) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date(stamp);
		return simpleDateFormat.format(date);
	}
}
