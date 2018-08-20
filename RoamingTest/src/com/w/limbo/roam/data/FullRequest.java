package com.w.limbo.roam.data;

public class FullRequest {
	byte length = 3;
	byte type = 0x01;
	byte RoamProvince;

	public FullRequest(byte length, byte type, byte roamProvince) {
		this.length = length;
		this.type = type;
		RoamProvince = roamProvince;
	}

	public FullRequest(byte province) {
		this.RoamProvince = province;
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
		return RoamProvince;
	}

	public void setRoamProvince(byte roamProvince) {
		RoamProvince = roamProvince;
	}

}
