package com.w.limbo.roam.data;

public class RepContent {
	byte length = 0x02;
	byte type = 0x02;

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

}
