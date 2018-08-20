package com.w.limbo.roam.data;

public class RoamData {
	byte length;
	byte type;
	byte roamProvince;
	short region;
	long userNumber;
	int time;
	byte action;

	public RoamData(byte length, byte type, byte roamProvince, short region,
			long userNumber, int time, byte action) {
		super();
		this.length = length;
		this.type = type;
		this.roamProvince = roamProvince;
		this.region = region;
		this.userNumber = userNumber;
		this.time = time;
		this.action = action;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getRoamProvince() {
		return roamProvince;
	}

	public void setRoamProvince(byte roamProvince) {
		this.roamProvince = roamProvince;
	}

	public short getRegion() {
		return region;
	}

	public void setRegion(short region) {
		this.region = region;
	}

	public long getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(long userNumber) {
		this.userNumber = userNumber;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public byte getAction() {
		return action;
	}

	public void setAction(byte action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "RoamData [length=" + length + ", type=" + type
				+ ", roamProvince=" + roamProvince + ", region=" + region
				+ ", userNumber=" + userNumber + ", time=" + time + ", action="
				+ action + "]";
	}

}
