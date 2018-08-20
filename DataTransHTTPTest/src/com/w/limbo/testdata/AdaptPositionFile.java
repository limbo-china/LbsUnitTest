package com.w.limbo.testdata;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AdaptPositionFile {

	public static void main(String[] args) throws IOException {
		FileInputStream input = new FileInputStream("position.txt");
		Scanner scan = new Scanner(input, "UTF-8");

		FileInputStream input2 = new FileInputStream("city.txt");
		Scanner scan2 = new Scanner(input2, "UTF-8");

		FileWriter writer = new FileWriter("position2.txt");

		Random random = new Random();
		List<String> citys = new ArrayList<String>();
		while (scan2.hasNext()) {
			String region = scan2.next().split("=")[0];
			if (region.substring(2, 4).equals("00"))
				continue;
			citys.add(region);
		}

		while (scan.hasNext()) {
			String line = scan.next();
			String uli = line.split(";")[0];
			String lac = line.split(";")[1];
			String ci = line.split(";")[2];
			String region = citys.get(random.nextInt(citys.size()));

			writer.write(uli + ";" + lac + ";" + ci + ";" + region + "\n");
		}
		writer.flush();

	}
}
