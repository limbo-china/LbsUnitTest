package com.w.limbo.Group;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;

public class GJRule {

	private String ruleId;
	private String ruleName;
	private String phone;
	private String provinceId;
	private String provinceName;
	private String createBy;
	private String createTime;
	private String updateBy;
	private String updateTime;

	protected static int increRuleID = 0;

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

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

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

	public static String generateOneRuleJson() {
		Gson gson = new Gson();
		return gson.toJson(generateOneRule());
	}

	private static GJRule generateOneRule() {
		GJRule rule = new GJRule();
		rule.setRuleId(String.valueOf(increRuleID++));
		rule.setRuleName(rule.getRuleId());
		rule.setPhone(msisdnList.get(random.nextInt(msisdnList.size())));
		String province = provinceList.get(random.nextInt(provinceList.size()));
		rule.setProvinceId(province.split(";")[0]);
		rule.setProvinceName(province.split(";")[1]);
		rule.setCreateBy("testCreateBy");
		rule.setCreateTime(stampToDate(System.currentTimeMillis()));
		rule.setUpdateBy("testUpdateBy");
		rule.setUpdateTime(stampToDate(System.currentTimeMillis()));

		return rule;
	}

	private static String stampToDate(long stamp) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = new Date(stamp);
		return simpleDateFormat.format(date);
	}
}
