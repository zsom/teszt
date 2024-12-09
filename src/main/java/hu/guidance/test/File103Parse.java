package hu.guidance.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class File103Parse {

	private static String[] header = {
			"C8",
			"C8",
			"C12",
			"C18",
			"C6",
			"C6",
			"N2",
			"D8",
			"N4",
			"N4"
				};
	
	
	private static String[] trailer = {
			"C8",
			"N7",
			"N15"
	};
	
	
	private static String[] records = {
			"C8",
			"C4",
			"C2",
			"C12",
			"C1",
			"C6",
			"C10",
			"C6",
			"N15",
			"C1",
			"C5",
			"C50",
			"C50",
			"C5",
			"C40",
			"C30",
			"C2",
			"C30",
			"D8",
			"C50",
			"C1",
			"C2",
			"C20",
			"C30",
			"C30",
			"C2",
			"C10",
			"C10",
			"C30",
			"C30",
			"C2",
			"C10",
			"C10",
			"C10",
			"C11",
			"C15",
			"C8",
			"C8",
			"C8",
			"D8",
			"N20",
			"N12",
			"N12",
			"C6",
			"C4",
			"C2",
			"C1",
			"C53",
			"D8"	
	};
	
	
	private static String[] colNames = {
			"Rekord típus",
			"Letétkezelő főszámla azonosító",
			"Esemény kód",
			"ISIN azonosító",
			"Számlatípus",
			"Alszámla azonosító",
			"Tulajdonosi azonosító",
			"Részvénykönyvi tulajdonosi azonosító",
			"Névérték db szám",
			"Tulajdonos jelleg",
			"Tulajdonos előneve",
			"Tulajdonos első neve",
			"Tulajdonos második neve",
			"Tulajdonos születéskori előneve",
			"Tulajdonos születéskori első neve",
			"Tulajdonos születéskori második neve",
			"Születési hely ország",
			"Születési hely, helységnév",
			"Születési dátum",
			"Tulajdonos anyja neve",
			"Tulajdonos neme",
			"Állampolgárság",
			"Tulajdonos személyi azonosítója",
			"Állandó lakcím, helységnév",
			"Utca, házszám",
			"Ország",
			"Irányítószám",
			"Postafiók",
			"Levelezési cím, helységnév",
			"Utca, házszám",
			"Ország",
			"Irányítószám",
			"Postafiók",
			"Adóazonosító",
			"Adószám",
			"Regisztrációs szám",
			"Pénzforgalmi jelzőszám1",
			"Pénzforgalmi jelzőszám 2",
			"Pénzforgalmi jelzőszám 3",
			"Részvényszerzés dátuma",
			"Címlet azonosító",
			"Sorszám-tartomány kezdete",
			"Sorszám-tartomány vége",
			"Keler belső azonosító",
			"Osztalékjogosultság éve",
			"Osztalékfizetési részlet sorszáma",
			"TBSZ",
			"Üres",
			"Kitöltés dátuma"	
	};

	private static final String TAB = "\t";
	
	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			/*
			load1("d:\\temp\\reszv_parse\\KIBO1D41.103");
			load1("d:\\temp\\reszv_parse\\KIBO1D40.101");
			load1("d:\\temp\\reszv_parse\\KIBO1D41.102");
			*/
			
			load1("g:\\Board\\Finance\\Reszvenyek\\DRP\\20240617_adasvetelek\\output\\_KELER\\KIBO1D41_HU0000167051.90b");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}


	private static void load1(String inputPath) throws IOException {

		List<String> lines = FileUtils.readLines(new File(inputPath), "Cp850");
		File outtputFile = new File(inputPath + ".txt");
		FileUtils.writeStringToFile(outtputFile, "", Charset.forName("UTF-8"), false);
		
		int lineIndex = 0;
		
		String text = null;
		for (String line : lines) {
			lineIndex++;
			System.out.println(lineIndex + ": " + line);
			
			String[] splitArray;
			if(lineIndex == 1) {
				splitArray = header;
				System.out.println("első sor");
			} else if(lineIndex == lines.size() - 1) {
				splitArray = trailer;
				System.out.println("utolsó sor");
			} else if(lineIndex == lines.size()) {
				System.out.println("ez már nem kell, kilép");
				break;
			} else {
				splitArray = records;
			}
			
			text = splitLine(splitArray, line);
			FileUtils.writeStringToFile(outtputFile, text, Charset.forName("UTF-8"), true);
			
			if(lineIndex == 1) {
				text = getColNamesLine();
				FileUtils.writeStringToFile(outtputFile, text, Charset.forName("UTF-8"), true);
			}
			
		}
		
		
	}


	private static String getColNamesLine() {
		StringBuilder sb = new StringBuilder();
		for (String colName : colNames) {
			sb.append(colName).append(TAB);
		}
		sb.append("\r\n");
		return sb.toString();
	}


	private static String splitLine(String[] splitArray, String line) {
		StringBuilder sb = new StringBuilder();
		int currentPos = 0;
		for (String splitData : splitArray) {
			String type = splitData.substring(0,1);
			int length = Integer.parseInt(splitData.substring(1, splitData.length())) ;
			String text = line.substring(currentPos, currentPos + length).trim();
			
			if("N".equalsIgnoreCase(type)) {
				text = text.replace('.', ',');
			}
			
			sb.append(text).append(TAB);
			currentPos += length;
		}
		
		sb.append("\r\n");
		
		return sb.toString();
	}
	
}
