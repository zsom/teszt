package hu.guidance.test;

import java.io.IOException;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class PDFMerge {
	
	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			merge1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}

	private static void merge1() throws IOException {
		
		String[] files = {
				"ALIHF-166_Feladatlap.pdf",
				"ALIHF-173_Feladatlap.pdf",
				"ALIHF-184_Feladatlap.pdf",
				"ALIHF-187_Feladatlap.pdf",
				"ALIHF-190_Feladatlap.pdf",
				"ALIHF-193_Feladatlap.pdf",
				"BIR-128_Feladatlap.pdf",
				"BOR-316_Feladatlap.pdf",
				"ELB-209_Feladatlap.pdf",
				"EUERTERV-150_Feladatlap.pdf",
				"KM-412_Feladatlap.pdf",
				"PORTAL-351_Feladatlap.pdf",
				"POTEIF-122_Feladatlap.pdf",
				"UPDEIF-35_Feladatlap.pdf",
				"VIGOR-804_Feladatlap.pdf"
		};
		
		String newFile = "IM_2024_Q3_feladatlapok.pdf";
		
		String baseDir = "g:\\Board\\megrendelok\\Nebih\\egyedi_fejlesztesek\\2024\\Q3\\";
		
		PDFMergerUtility ut = new PDFMergerUtility();
		
		for (int i = 0; i < files.length; i++) {
			ut.addSource(baseDir + files[i]);
		}
		ut.setDestinationFileName(baseDir + newFile);
		ut.mergeDocuments(null);
	}

}
