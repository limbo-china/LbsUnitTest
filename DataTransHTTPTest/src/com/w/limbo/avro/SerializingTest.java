package com.w.limbo.avro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.*;
import org.apache.avro.io.*;

public class SerializingTest {
	
	public static void main(String[] args) throws IOException {
		
		GenericRecord user = createUser();
		byte[] buffer = serializing(user);
		deserializing(buffer);
	}
	
	private static GenericRecord createUser() throws IOException{
		Schema schema = new Schema.Parser().parse(new File("user.avsc"));

		GenericRecord user = new GenericData.Record(schema);
		user.put("name", "Ben");
		user.put("favorite_number", 7);
		user.put("favorite_color", "green");
		
		return user;
	}
	private static byte[] serializing(GenericRecord user) throws IOException {
		Schema schema = new Schema.Parser().parse(new File("user.avsc"));
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		BinaryEncoder encoder = new EncoderFactory().binaryEncoder(output, null);
		
		DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
		datumWriter.write(user, encoder);
		encoder.flush();
		
		return output.toByteArray();
	}
	private static void deserializing(byte[] buffer) throws IOException{
		Schema schema = new Schema.Parser().parse(new File("user.avsc"));
		ByteArrayInputStream input = new ByteArrayInputStream(buffer);
		BinaryDecoder decoder = new DecoderFactory().binaryDecoder(input, null);
		
		DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
		GenericRecord user = null;
		user = datumReader.read(null, decoder);
		
		System.out.println(user);
	}
}
