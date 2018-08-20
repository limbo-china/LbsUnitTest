package com.w.limbo.roam.avro;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DecoderFactory;

import com.w.limbo.roam.data.MessageReceive;
import com.w.limbo.roam.data.RoamData;

public class AvroTool {

	public static RoamData deserialzeFromBytes(byte[] bytes) throws IOException {
		Protocol protocol = Protocol.parse(new File("Data_Struct.json"));

		ByteArrayInputStream databis = new ByteArrayInputStream(bytes);
		BinaryDecoder dataDecoder = new DecoderFactory().binaryDecoder(databis,
				null);
		Schema dataSchema = protocol.getType("MessageSend");

		new GenericData.Record(dataSchema);

		GenericDatumReader<GenericRecord> contentReader = new GenericDatumReader<GenericRecord>(
				dataSchema);

		GenericRecord record = null;
		record = (GenericRecord) contentReader.read(record, dataDecoder);

		MessageReceive ds = new MessageReceive(
				(ByteBuffer) record.get("length"),
				(ByteBuffer) record.get("type"),
				(ByteBuffer) record.get("RoamProvince"),
				(ByteBuffer) record.get("Region"),
				(ByteBuffer) record.get("UserNumber"),
				(ByteBuffer) record.get("Time"),
				(ByteBuffer) record.get("Action"));

		return ds.toRoamData();
	}

}
