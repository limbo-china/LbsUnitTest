package com.w.limbo.testdata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadToListFromFile {
	
	private final List<String> phoneList = new ArrayList<String>();
	private final List<String> positionList = new ArrayList<String>();
	private static ReadToListFromFile INSTANCE = null;
	
	private ReadToListFromFile(String phonefile, String positionfile) throws IOException{
		FileInputStream input = new FileInputStream(phonefile);
		Scanner scan = new Scanner(input);
		String line = null;
		while(scan.hasNext()){
			line = scan.nextLine();
			phoneList.add(line);
		}
		
		FileInputStream input2 = new FileInputStream(positionfile);
		Scanner scan2 = new Scanner(input2);
		String line2 = null;
		while(scan2.hasNext()){
			line2 = scan2.nextLine();
			positionList.add(line2);
		}
	
		input.close();
		scan.close();
		input2.close();
		scan2.close();
	}
	
	public static ReadToListFromFile getInstance(String phonefile, String positionfile){
		try{
			if(INSTANCE == null)
				INSTANCE = new ReadToListFromFile(phonefile, positionfile);
		}catch(IOException e){
			e.printStackTrace();
		}
		return INSTANCE;
	}
	public List<String> getPhoneList(){ return phoneList;}
	public List<String> getPositionList(){ return positionList;}
	
	public static void main(String[] args){		
	}
}
