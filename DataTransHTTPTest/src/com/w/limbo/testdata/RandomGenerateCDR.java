package com.w.limbo.testdata;

import java.util.List;
import java.util.Random;

import com.w.limbo.avro.SerializingCDR;

public class RandomGenerateCDR {
	
	private static final Random random = new Random();
	private static final ReadToListFromFile read2list = ReadToListFromFile.getInstance("phone.txt", "position.txt");
	private static final List<String> phoneList = read2list.getPhoneList();
	private static final List<String> positionList = read2list.getPositionList();
	private static final int GROUP_NUM = 10;
	
	public static byte[] generateSerializedCDR(){
		byte[] res = null;
		try{
			SerializingCDR sc = SerializingCDR.getInstance();
			res = sc.serializeCDRToBytes(generateOneCdr());		
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	
	private static CDRData generateOneCdr(){
		
		CDRData cdr = new CDRData();
		
		String phone = phoneList.get(random.nextInt(phoneList.size()));
		cdr.setMsisdn(phone.split(";")[0]);
		cdr.setImsi(phone.split(";")[1]);
		cdr.setImei(phone.split(";")[2]);
		cdr.setHomeCode(phone.split(";")[3]);
		
		String position = positionList.get(random.nextInt(positionList.size()));
		cdr.setUli(position.split(";")[0]);
		cdr.setLac(position.split(";")[1]);
		cdr.setCi(position.split(";")[2]);
		cdr.setRegionCode(position.split(";")[3]);
		
		cdr.setCdrType(0);
		cdr.setTimeStamp(System.currentTimeMillis()/1000);
		cdr.setRelateNum("0");
		cdr.setCdrContent("0");
		
		return cdr;
	}
	
	private static String generateOneGroup(){
		
		String group = "";
		for(int i=0; i< GROUP_NUM-1 ;i++){
			group += generateOneCdr() + ",";
		}
		group += generateOneCdr();
		return "{\"type\":\"t_cdr\", \"record\":["+group+"]}";
	}
	
	public static void main(String[] args){
		System.out.println(generateOneGroup());
	}
}
