package com.w.limbo.Group;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;

public class JCPerson {

	private static FileInputStream msisdnInput;
	private static Scanner msisdnScan;
	private static FileInputStream provinceInput;
	private static Scanner provinceScan;
	private static List<String> msisdnList;
	private static List<String> provinceList;
	private static Random random = new Random();
	static {
		try {
			msisdnInput = new FileInputStream("msisdn.txt");
			msisdnScan = new Scanner(msisdnInput, "UTF-8");
			msisdnList = new ArrayList<String>();
			while (msisdnScan.hasNext()) {
				String line = msisdnScan.nextLine();
				msisdnList.add(line.split(";")[0]);
			}
			msisdnInput.close();
			msisdnScan.close();

			provinceInput = new FileInputStream("province.txt");
			provinceScan = new Scanner(provinceInput, "UTF-8");
			provinceList = new ArrayList<String>();
			while (provinceScan.hasNext()) {
				String line = provinceScan.nextLine();
				provinceList.add(line);
			}
			provinceInput.close();
			provinceScan.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String phone;
	private String provinceId;
	private String provinceName;
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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

	private static JCPerson generateOnePerson() {
		JCPerson person = new JCPerson();
		person.setPhone(msisdnList.get(random.nextInt(msisdnList.size())));
		String province = provinceList.get(random.nextInt(provinceList.size()));
		person.setProvinceId(province.split(";")[0]);
		person.setProvinceName(province.split(";")[1]);
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
