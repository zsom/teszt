package hu.guidance.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class BerImport {

	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			
			import1();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}

	private static void import1() throws IOException {
		//String filePath = "d:\\munka\\guidance\\ber\\ber_import\\CS_ATUT_BER_02.121";
		//String filePath = "d:\\munka\\guidance\\ber\\ber_import\\CS_ATUT_SZEP_03.121";
		String filePath = "d:\\munka\\guidance\\ber\\ber_import\\CS_ATUT_JAR_02.121";
		
		
		List<String> lines = FileUtils.readLines(new File(filePath), "Cp850");
		
		for(int i = 1; i < lines.size() - 1; i++) {
			String line = lines.get(i);
			String amountText = line.substring(16, 26);
			int amount = Integer.parseInt(amountText);
			String name = line.substring(74,109).trim();
			
			System.out.println(i + ". " + name + ": " + amount);
			
		}
	}
}
