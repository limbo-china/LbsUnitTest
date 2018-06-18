package com.w.limbo.avro;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.apache.avro.Protocol;
import org.apache.avro.Schema;
import org.apache.avro.generic.*;
import org.apache.avro.io.*;

import com.w.limbo.testdata.CDRData;

public class SerializingCDR {
	
	private static Protocol protocol = null;
	private static Schema docsschema = null;
	private static Schema contentschema = null;
	private static DatumWriter<GenericRecord> docsWriter = null;
    private static DatumWriter<GenericRecord> contentWriter = null;
    private static final SerializingCDR INSTANCE = new SerializingCDR();
    
    static{
    	try{
    		protocol = Protocol.parse(new File("t_cdr.json"));
    		docsschema = protocol.getType("docs");
    		contentschema = protocol.getType("t_cdr");
    		docsWriter = new GenericDatumWriter<GenericRecord>(docsschema);
    		contentWriter = new GenericDatumWriter<GenericRecord>(contentschema);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    private SerializingCDR(){}
    public static SerializingCDR getInstance(){ return INSTANCE; }
    
    public byte[] serializeCDRToBytes(CDRData cdr) throws IOException{
    	ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
		BinaryEncoder contentEncoder = new EncoderFactory().binaryEncoder(contentOutput, null);	
		contentWriter.write(cdrToGenericRecord(cdr), contentEncoder);
		contentEncoder.flush();
    	
		ByteArrayOutputStream docsOutput = new ByteArrayOutputStream();
		BinaryEncoder docsEncoder = new EncoderFactory().binaryEncoder(docsOutput, null);	
		docsWriter.write(generateDocsRecord(contentOutput.toByteArray()), docsEncoder);
		docsEncoder.flush();
		
    	return docsOutput.toByteArray();
    }
    
    private GenericRecord cdrToGenericRecord(CDRData cdr){
    	GenericRecord cdrrecord = new GenericData.Record(contentschema);
    	cdrrecord.put("c_usernum", cdr.getMsisdn());
    	cdrrecord.put("c_imsi", cdr.getImsi());
    	cdrrecord.put("c_imei", cdr.getImei());
    	cdrrecord.put("c_relatenum", cdr.getRelateNum());
    	cdrrecord.put("c_uli", cdr.getUli());
    	cdrrecord.put("c_lac", cdr.getLac());
    	cdrrecord.put("c_ci", cdr.getCi());
    	cdrrecord.put("c_areacode", cdr.getRegionCode());
    	cdrrecord.put("c_homecode", cdr.getHomeCode());
    	cdrrecord.put("c_cdrtype", cdr.getCdrType());
    	cdrrecord.put("c_timestamp", cdr.getTimeStamp());
    	cdrrecord.put("c_content", cdr.getCdrContent()); 	
    	
    	return cdrrecord;
    }
    private GenericRecord generateDocsRecord(byte[] content){
    	GenericRecord docsrecord = new GenericData.Record(docsschema);
    	
    	DocsRecord dr = new DocsRecord("t_cdr","test",content);
    	docsrecord.put("doc_schema_name", dr.getDocName());
    	docsrecord.put("sign", dr.getSign());
    	docsrecord.put("doc_set", dr.getDocSet());
    	
    	return docsrecord;
    }
}
