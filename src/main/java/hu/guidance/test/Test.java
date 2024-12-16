package hu.guidance.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import hu.guidance.test.scp.SCPUtil;

public class Test {
private static Map<Character, Character> CHAR_REPLACE = new ConcurrentHashMap<Character, Character>();
	
	static{
		CHAR_REPLACE.put('ö','o');
		CHAR_REPLACE.put('ü','u');
		CHAR_REPLACE.put('ó','o');
		CHAR_REPLACE.put('ő','o');
		CHAR_REPLACE.put('ú','u');
		CHAR_REPLACE.put('é','e');
		CHAR_REPLACE.put('á','a');
		CHAR_REPLACE.put('ű','u');
		CHAR_REPLACE.put('í','i');
		CHAR_REPLACE.put('Ö','O');
		CHAR_REPLACE.put('Ü','U');
		CHAR_REPLACE.put('Ó','O');
		CHAR_REPLACE.put('Ő','O');
		CHAR_REPLACE.put('Ú','U');
		CHAR_REPLACE.put('É','E');
		CHAR_REPLACE.put('Á','A');
		CHAR_REPLACE.put('Ű','U');
		CHAR_REPLACE.put('Í','I');
	}
	
	
	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			mod();
			
			//leftPad();
			//charMap();
			//binary();
			//listRemove();
			//substring1();
			//substring2();
			//parseInt1();
			
			//scpTest1();
			//scpTestP();
			
			//fileCopy1();
			
			//testReplace();
			
			//regexp1();
			//regexp2();
			//splitadd();
			
			//format1();
			
			//testMonthBegin();
			//base64toFile();
			
			//calendar1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}


	private static void mod() {
		System.out.println((2 % 7));
		System.out.println((5 % 7));
		System.out.println((9 % 7));
		System.out.println((-7 % 7));
		System.out.println((-2 % 7));
		
	}


	private static void leftPad() {
		System.out.println(StringUtils.leftPad("", 3, "4"));
		
		StringBuilder sb = new StringBuilder();
		sb.append("123456789");
		
		sb.replace(3, 4, "A");
		
		System.out.println("sb: " + sb);
		
	}


	private static void charMap() {
		HashMap<Character, String> map = new HashMap<Character, String>();
		
		map.put('a', "aa");
		map.put('b', "bb");
		map.put('c', "cc");
		
		map.forEach((key, value) -> System.out.println(key + ":" + value));
		
		map.put('b', "BB");
		
		System.out.println("-----------");
		
		map.forEach((key, value) -> System.out.println(key + ":" + value));
		
	}


	private static void binary() {
		for(int i = 0; i < Math.pow(3, 4) + 1; i++) {
			//System.out.println(Integer.toBinaryString(i));
			
			System.out.println(Integer.toString(i, 3));
		}
		
	}


	private static void listRemove() {
		ArrayList<String> list = new ArrayList<String>(100);
		
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		
		System.out.println(list);
		list.add("e");
		list.remove(0);
		
		//list.add(25, "z");
		
		System.out.println(list);
		
		Integer[] inta = new Integer[100];
		
		inta[10] = 1010;
		
		System.out.println(Arrays.toString(inta));
		
	}


	private static void calendar1() {
		int year = 2024;
		int month = 3;
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		
		System.out.println("cal: " + sdf.format(cal.getTime()));
		
	}


	private static void base64toFile() throws Exception {
		//String encoded = "MIIGxjCCBa6gAwIBAgINHloVBUkbESC2GbYwCjANBgkqhkiG9w0BAQsFADBqMQswCQYDVQQGEwJIVTERMA8GA1UEBwwIQnVkYXBlc3QxFjAUBgNVBAoMDU1pY3Jvc2VjIEx0ZC4xFDASBgNVBAsMC2UtU3ppZ25vIENBMRowGAYDVQQDDBFlLVN6aWdubyBUZXN0IENBMzAeFw0xNDA3MDQxNDA2NThaFw0yNDA3MDExNDA2NThaMIHTMQswCQYDVQQGEwJIVTERMA8GA1UEBwwIQnVkYXBlc3QxGDAWBgNVBAoMD1Rlc3p0IHN6ZXJ2ZXpldDElMCMGA1UEAwwcQ2V2ciBUZXN6dCBGZWxoYXN6bsOhbMOzIDEzMTEgMB4GCSqGSIb3DQEJARYRdGVzenRAbWljcm9zZWMuaHUxJzAlBgNVBAUTHjEuMy42LjEuNC4xLjIxNTI4LjIuMi45OS4xODEzMTElMCMGA1UEDAwcQ2V2ciBUZXN6dCBGZWxoYXN6bsOhbMOzIDEzMTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMzS8KQ6GPPfBZ8M4HFHqwvb6EFzwbDormOJCb8EeIeZgrBKTVv8glWQTZkqi5YFgYi26tQjN5pHLfKyxjHx7cr7R0RyHPfjh9+Ylm8SRH1AGpCGRE1mMhj/NClbRh5GS/Ml59XAPDQ9Ph1ti2lgKu6IJBt6UMDPb7SaGEabYfHCh6334yK5+VrSS3PVFaqk6GsJRuKZf8r4uVs8Bc+7ZUScEfgz56BgC+VaMo2D4jfnfyccr6gSpyVlnfSTmBMCHORq7pZZ4FMvcdWNJmAoWAVphNddK9eRdh8L8EJ3yO+AEMVXnhQYg6QCX/HGU/xVlxByIymjjI4Q7m4bjMT6W2ECAwEAAaOCAv8wggL7MAwGA1UdEwEB/wQCMAAwDgYDVR0PAQH/BAQDAgQwMBMGA1UdJQQMMAoGCCsGAQUFBwMEMIIBqAYDVR0gBIIBnzCCAZswggGXBgwrBgEEAYGoGAIBAWQwggGFMCcGCCsGAQUFBwIBFhtodHRwOi8vc3J2LmUtc3ppZ25vLmh1L1RIUi8wggFYBggrBgEFBQcCAjCCAUoeggFGAFQAZQBzAHoAdABlAGwA6QBzAGkAIABjAOkAbAByAGEAIABrAGkAYQBkAG8AdAB0ACAAVABFAFMAWgBUACAAdABhAG4A+gBzAO0AdAB2AOEAbgB5AC4AIABBACAAaABhAHMAegBuAOEAbABhAHQA4QB2AGEAbAAgAGsAYQBwAGMAcwBvAGwAYQB0AG8AcwBhAG4AIABmAGUAbABtAGUAcgD8AGwBUQAgAGsA4QByAG8AawDpAHIAdAAgAGEAegAgAGUALQBTAHoAaQBnAG4A8wAgAEgAaQB0AGUAbABlAHMA7QB0AOkAcwAgAFMAegBvAGwAZwDhAGwAdABhAHQA8wAgAHMAZQBtAG0AaQBsAHkAZQBuACAAZgBlAGwAZQBsAVEAcwBzAOkAZwBlAHQAIABuAGUAbQAgAHYA4QBsAGwAYQBsACEwHQYDVR0OBBYEFF1tm+xCqqmvMO0Y8VN+c9toa+89MB8GA1UdIwQYMBaAFNzmAijvNzCPiT6grSBV8+826PDNMDUGA1UdEQQuMCyBEXRlc3p0QG1pY3Jvc2VjLmh1oBcGBisGAQUFB6ANMAsGCSsGAQQBgagYAjAyBgNVHR8EKzApMCegJaAjhiFodHRwOi8vdGVzenQuZS1zemlnbm8uaHUvVENBMy5jcmwwbwYIKwYBBQUHAQEEYzBhMDAGCCsGAQUFBzABhiRodHRwOi8vdGVzenQuZS1zemlnbm8uaHUvdGVzdGNhM29jc3AwLQYIKwYBBQUHMAKGIWh0dHA6Ly90ZXN6dC5lLXN6aWduby5odS9UQ0EzLmNydDANBgkqhkiG9w0BAQsFAAOCAQEAaHZp/oXUcx6vaOxrG5PyYyxsZWV2AojYtW0+5HveAGQ77f/1uGPo88i+c3Wd0vLCzK3ik8myOVYcjMDMX0eNRVEvzPfD/umhqT7AfO/BrLsIdiwtS3f6OUxGQ+V2cIlO3ea8lQOdrtDotJywcOam60+7g8UKnYrHFjuAi8XHEvGKrptcQmUCgRGpKz1sogjJfhzcq4bsUmoUg58jq5BF09VAMycU/Rw4p69Kl5ECxhLhDBo/LdWVo1ZWQYl/zLlXTERmIaCuPG05frlku2Y96HLdqz7CpkeaWX4qZkeoLtLgVTExxZ+oAvmwpoh7datJsNkUkJLu1901Y94lP81fSg==";
		
		String encoded = "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";
		
		byte[] data = Base64.getDecoder().decode(encoded);
		
		FileOutputStream fos = new FileOutputStream("d:\\temp\\binx\\cevr_cert.txt");
		fos.write(data);
		fos.close();
		
	}


	private static void testMonthBegin() {
		int year = 2024;
		String monthLabel = "01-jut";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		System.out.println("date: " + sdf.format(getMonthBegin(year, monthLabel)));
		
	}


	private static Date getMonthBegin(int year, String monthLabel) {
		int month;
		if(monthLabel.contains("-")) {
			month = Integer.parseInt(monthLabel.substring(0, monthLabel.indexOf("-")));
		} else {
			month = Integer.parseInt(monthLabel);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		
		return cal.getTime();
	}

	private static void format1() {
		String result = String.format("%02d", 12);
		System.out.println("result: " + result);
		
	}



	private static void regexp2() {
		/*
		String text3 = "VargaZsombor_jelenletiiv_202312.asice";
		String regexp3 = ".*jelenletiiv_(\\d{6})\\.(.*)";
		
		String text2 = "VARGA_ZSOMBOR-szamfejtolap-202312_hokozi.pdf";
		String regexp2 = ".*szamfejtolap-(\\d{6}_hokozi)\\.(.*)";
		*/
		//String text1 = "07";
		String text1 = "08-adó nyomtatvány";
		String regexp1 = "(\\d{2})-*(.*)";
		
		
		
		String regexp = regexp1;
		String text = text1;
		
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(text);
		
		if(matcher.find()) {
			System.out.println("ok");
			
			String month = matcher.group(1);
			String ext = matcher.group(2);
			System.out.println("date: " + month + ", ext: " + ext);
			
			
		} else {
			System.out.println("nem ok");
		}
		
	}



	private static void splitadd() {
		String text = "2022 2023 2024";
		List<String> list = Arrays.asList(text.split(" "));
		
		System.out.println("list: " + list.getClass().getName() + ", list: " + list);
		
		List<String> list2 = new ArrayList<String>(list); 
		
		list2.add("2025");
		
		System.out.println("list2: " + list2);
		
	}



	private static void regexp1() {
		
		String text3 = "VargaZsombor_jelenletiiv_202312.asice";
		String regexp3 = ".*jelenletiiv_(\\d{6})\\.(.*)";
		
		String text2 = "VARGA_ZSOMBOR-szamfejtolap-202312_hokozi.pdf";
		String regexp2 = ".*szamfejtolap-(\\d{6}_hokozi)\\.(.*)";
		
		String text1 = "VARGA_ZSOMBOR-szamfejtolap-202312.asice";
		String regexp1 = ".*szamfejtolap-(\\d{6})\\.(.*)";
		
		
		
		String regexp = regexp3;
		String text = text3;
		
		Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(text);
		
		if(matcher.find()) {
			System.out.println("ok");
			
			String date = matcher.group(1);
			String ext = matcher.group(2);
			System.out.println("date: " + date + ", ext: " + ext);
			
			
		} else {
			System.out.println("nem ok");
		}
		
	}



	private static void testReplace() {
		String text = "Árvíztűrő tükörfúrógép";
		
		System.out.println(text + " -> " + replaceAccents(text));
		
	}



	public static String replaceAccents(String text) {
		Set<Entry<Character, Character>> entrySet = CHAR_REPLACE.entrySet();
		
		for (Entry<Character, Character> entry : entrySet) {
			text = text.replace(entry.getKey(), entry.getValue());
		}
		
		return text;
	}
	
	
	private static void fileCopy1() throws Exception {
		File fromFile = new File("d:\\temp\\opapp\\VargaZsombor_jelenletiiv_202309.asice");
		File toDir = new File("d:\\temp\\opapp\\2023\\09\\alairt\\VargaZsombor_jelenletiiv_202309.asice");
		
		FileUtils.copyFile(fromFile, toDir, true);
		
	}

	private static void scpTestP() throws Exception {
		SCPUtil su = new SCPUtil();
		su.connect("192.168.1.20", 22, "opapptech", "X");
		
		File file = new File("d:\\temp\\teszt.txt");
		
		byte[] ba = FileUtils.readFileToByteArray(file);
		
		
		su.uploadFile("/home/kisgomb/Board/Könyvelésbe/Bérszamfejto\\ lapok/2023/2023_10", file.getName(), ba, false);
		
		su.disconnect();
		
	}

	private static void scpTest1() throws JSchException, IOException, SftpException {
		SCPUtil su = new SCPUtil();
		su.connect("192.168.1.63", 22, "opapptech", "opapp1234");
		
		File file = new File("d:\\temp\\teszt.txt");
		
		byte[] ba = FileUtils.readFileToByteArray(file);
		
		
		su.uploadFile("/home/opapptech/scp_in", file.getName(), ba, false);
		
		su.disconnect();
		
	}

	private static void parseInt1() {
		String s = "202408";
		int year = Integer.parseInt(s.substring(0, 4));
		int month = Integer.parseInt(s.substring(4, 6));
	
		System.out.println("year: " + year + ", month: " + month);
	}

	private static void substring2() {
		String s = "VARGA_ZSOMBOR";
		
		System.out.println("subs: " + s.substring(11, 13));
	}
	
	private static void substring1() {
		String s = "VARGA_ZSOMBOR-szamfejtolap-202311.asice";
		
		int dotIdx = s.lastIndexOf(".");
		System.out.println("dotIdx: " + dotIdx);
		
		System.out.println("subs: " + s.substring(dotIdx - 6, dotIdx));
	}
}
