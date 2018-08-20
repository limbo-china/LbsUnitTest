package com.w.limbo.roam.data;

import java.nio.ByteBuffer;

public class MessageReceive {
	ByteBuffer length;
	ByteBuffer type;
	ByteBuffer roamProvince;
	ByteBuffer region;
	ByteBuffer userNumber;
	ByteBuffer time;
	ByteBuffer action;

	public MessageReceive(ByteBuffer length, ByteBuffer type,
			ByteBuffer roamProvince, ByteBuffer region, ByteBuffer userNumber,
			ByteBuffer time, ByteBuffer action) {
		super();
		this.length = length;
		this.type = type;
		this.roamProvince = roamProvince;
		this.region = region;
		this.userNumber = userNumber;
		this.time = time;
		this.action = action;
	}

	public RoamData toRoamData() {
		return new RoamData(byteBuffer2Byte(length), byteBuffer2Byte(type),
				byteBuffer2Byte(roamProvince), byteBuffer2Short(region),
				byteBuffer2Long(userNumber), byteBuffer2Int(time),
				byteBuffer2Byte(action));
	}

	private int byteBuffer2Int(ByteBuffer buffer) {

		int res = 0;
		byte[] bytes = buffer.array();
		for (int i = 0; i < 4; i++)
			res += (bytes[i] & 0x000000FF) + (res << 8);
		return res;
	}

	private short byteBuffer2Short(ByteBuffer buffer) {

		short res = 0;
		byte[] bytes = buffer.array();
		for (int i = 0; i < 2; i++)
			res += (bytes[i] & 0x00FF) + (res << 8);
		return res;

	}

	private long byteBuffer2Long(ByteBuffer buffer) {

		long res = 0;
		byte[] bytes = buffer.array();
		for (int i = 0; i < 8; i++)
			res += (bytes[i] & 0x00000000000000FF) + (res << 8);
		return res;

	}

	private byte byteBuffer2Byte(ByteBuffer buffer) {

		return buffer.array()[0];
	}

}
