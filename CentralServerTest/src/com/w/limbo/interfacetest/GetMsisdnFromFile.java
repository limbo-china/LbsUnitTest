package com.w.limbo.interfacetest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GetMsisdnFromFile {
	
	private String filename = null;
	private static GetMsisdnFromFile INSTANCE = null ;
	
	private GetMsisdnFromFile(String filename){
		this.filename = filename;
	}
	
	public static GetMsisdnFromFile getInstance(String filename){
		if(INSTANCE == null )
			return new GetMsisdnFromFile(filename);
		return INSTANCE;
	}
	
	public String getMsisdnBySize(int size) throws FileNotFoundException{
		
		FileInputStream input = new FileInputStream(filename);
		Scanner scan = new Scanner(input, "UTF-8");
		
		String res = "";
		if(size > 1000)
			throw new RuntimeException("size cannot be more than 1000!");
		
		while(scan.hasNext() && size-- > 0 ){
			
			String line = scan.nextLine();
			res += line.split(";")[0]+ ",";
		}
		scan.close();
		
		return res.substring(0,res.length()-1);

		
	}
}
