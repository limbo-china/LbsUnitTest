package com.w.limbo.testdata;

public class CDRData {
	
	private String imsi;
	private String imei;
	private String msisdn;
	private String relateNum;
	private String regionCode;
	private String lac;
	private String ci;
	private String uli;
	private String homeCode;
	private String cdrContent;
	private long timestamp;
	private int cdrType;
	
	public CDRData(){}
	public CDRData(String msisdn, String imsi, String imei, String relateNum, String homeCode, String lac,
			String ci, String uli, String regionCode, double lngi, double lati, long timestamp, int cdrType) {
		this.imsi = imsi;
		this.imei = imei;
		this.msisdn = msisdn;
		this.relateNum = relateNum;
		this.regionCode = regionCode;
		this.lac = lac;
		this.ci = ci;
		this.uli = uli;
		this.homeCode = homeCode;
		this.timestamp = timestamp;
		this.cdrType = cdrType;
	}
	
	public void setImsi(String imsi){this.imsi = imsi;}
	public void setImei(String imei){this.imei = imei;}
	public void setMsisdn(String msisdn){this.msisdn = msisdn;}
	public void setRelateNum(String relate){this.relateNum = relate;}
	public void setRegionCode(String regionCode){this.regionCode = regionCode;}
	public void setLac(String lac){this.lac = lac;}
	public void setCi(String ci){this.ci = ci;}
	public void setUli(String uli){this.uli = uli;}
	public void setHomeCode(String homeCode){this.homeCode = homeCode;}
	public void setCdrContent(String cdrContent){this.cdrContent = cdrContent;}
	public void setTimeStamp(long timestamp){this.timestamp = timestamp;}
	public void setCdrType(int cdrType){this.cdrType = cdrType;}
	
	public String getImsi(){return imsi;}
	public String getImei(){return imei;}
	public String getMsisdn(){return msisdn;}
	public String getRelateNum(){return relateNum;}
	public String getRegionCode(){return regionCode;}
	public String getLac(){return lac;}
	public String getCi(){return ci;}
	public String getUli(){return uli;}
	public String getHomeCode(){return homeCode;}
	public String getCdrContent(){return cdrContent;}
	public long getTimeStamp(){return timestamp;}
	public int getCdrType(){return cdrType;}
	
	@Override
	public String toString(){
		return toJson();
	}
	private String toJson(){
		return "{\"c_usernum\":\""+getMsisdn()+"\","+
				"\"c_imsi\":\""+getImsi()+"\","+
				"\"c_imei\":\""+getImei()+"\","+
				"\"c_relatenum\":\""+getRelateNum()+"\","+
				"\"c_uli\":\""+getUli()+"\","+
				"\"c_lac\":\""+getLac()+"\","+
				"\"c_ci\":\""+getCi()+"\","+
				"\"c_areacode\":\""+getRegionCode()+"\","+
				"\"c_homecode\":\""+getHomeCode()+"\","+
				"\"c_cdrtype\":"+getCdrType()+","+
				"\"c_timestamp\":"+getTimeStamp()+","+
				"\"c_content\":\""+getCdrContent()+"\"}";			
	}
	public byte[] toBytes(){
		return new byte[1];
	}
	public static void main(String[] args){
		CDRData cdr = new CDRData();
		cdr.setMsisdn("123");
		System.out.println(cdr.toString());
	}
}
