package com.w.limbo.avro;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DocsRecord {
	
	private String doc_schema_name;
	private String sign;
	private ArrayList<ByteBuffer> doc_set = new ArrayList<ByteBuffer>();
	
	public DocsRecord(String doc_schema_name, String sign, byte[] doc_set){
		this.doc_schema_name = doc_schema_name;
		this.sign = sign;
		this.doc_set.add(ByteBuffer.wrap(doc_set));
		
	}

	public String getDocName(){ return doc_schema_name; }
	public String getSign(){ return sign; }
	public ArrayList<ByteBuffer> getDocSet(){ 
		return doc_set; }
}
