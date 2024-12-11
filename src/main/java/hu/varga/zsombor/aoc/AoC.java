package hu.varga.zsombor.aoc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class AoC {
	
	private static HashSet<String> fel10TrailSet = new HashSet<String>();
	private static int fel10AllTrailCnt = 0;
	
	public static void main(String[] args) {
		System.out.println("Start");
		
		try {
			fel11b();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Stop");
	}
	
	private static void fel11b() {
		String input = "125 17";
		//String input = "5 62914 65 972 0 805922 6521 1639064";
	
		
		int[] numCntList = new int[9000000];
		Object[] numConvList = new Object[9000000];

		
		
		numCntList[125] = 1;
		numCntList[17] = 1;
		
		for(int blink = 1; blink <= 3; blink++) {
			
			for(int i = 0; i < numCntList.length; i++) {
				Integer numCnt = numCntList[i];
				
				if(numCnt == 0) {
					continue;
				}
				
				Object object = numConvList[i];
				
				if(object == null) {
					if(i == 0) {
						numConvList[i] = 1;
					} else if((i + "").length() % 2 == 0) {
						String t = i + "";
						numConvList[i] = t.substring(0, t.length() / 2) + "," + Long.parseLong(t.substring(t.length() / 2));
					} else {
						numConvList[i] = i * 2024;
					}
					
					object = numConvList[i];
				}
				
				if(object instanceof String) {
					String[] convTexts = ((String)object).split(",");
					numCntList[i] = 0;
					Integer conv0 = Integer.parseInt(convTexts[0]);
					numCntList[conv0] = numCntList[conv0] + numCnt;
					Integer conv1 = Integer.parseInt(convTexts[0]);
					numCntList[conv1] = numCntList[conv1] + numCnt;
				} else {
					//van már
					int conv = (int)object;
					numCntList[i] = 0;
					numCntList[conv] = numCntList[conv] + numCnt;
				}
			}
		}
		
		for(int i = 0; i < numCntList.length; i++) {
			if(numCntList[i] > 0) {
				System.out.println(i + " -> " + numCntList[i]);
			}
		}
		
		System.out.println();
	}
	
	
	
	private static void fel11a() {
		//String input = "125 17";
		String input = "5 62914 65 972 0 805922 6521 1639064";
		
		ArrayList<String> stones = new ArrayList<String>(Arrays.asList(input.split(" ")));
		ArrayList<String> newStones = new ArrayList<String>();
		
		for(int blink = 1; blink <= 75; blink++) {
			System.out.println(blink + ". blink: " + stones.size());
			for (String stone : stones) {
				if("0".equals(stone)) {
					newStones.add("1");
				} else if(stone.length() % 2 == 0) {
					newStones.add(stone.substring(0, stone.length() / 2));
					newStones.add("" + Long.parseLong(stone.substring(stone.length() / 2)));
				} else {
					newStones.add(""+ (Long.parseLong(stone) * 2024));
				}
				
			}
			
			stones.clear();
			stones.addAll(newStones);
			newStones.clear();
			
		}
		
		System.out.println("size: " + stones.size());
	}

	private static void fel10a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_10_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		int[][] heightMatrix = new int[ySize][xSize];
		
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				char charAt = line.charAt(x);
				int curr = charAt == '.' ? -1 : Integer.parseInt("" + charAt);
				heightMatrix[y][x] = curr;
			}
		}
		
		//printMatrix(heightMatrix, xSize, ySize);
		
		int trailCnt = 0;
		
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if(heightMatrix[y][x] == 0) {
					fel10TrailSet = new HashSet<String>();
					fel10aMove(x, y, 0, heightMatrix, xSize, ySize);
					System.out.println(x + "," + y + "->" + fel10TrailSet.size());
					trailCnt += fel10TrailSet.size();
				}
			}
		}
		
		System.out.println("fel10TrailCnt: " + trailCnt);
		System.out.println("fel10AllTrailCnt: " + fel10AllTrailCnt);
	}
	
	private static void fel10aMove(int x, int y, int nextValue, int[][] heightMatrix, int xSize, int ySize) {
		nextValue++;
		
		if(fel10aValidMove(x + 1, y, nextValue, heightMatrix, xSize, ySize)) { //jobb
			//System.out.println(x + "," + y + "; next: " + nextValue + "; jobb");
			if(nextValue == 9) {
				fel10TrailSet.add((x+1) + "," + y);
				//System.out.println("OK - " + (x+1) + "," + y);
				fel10AllTrailCnt++;
			} else {
				fel10aMove(x + 1, y, nextValue, heightMatrix, xSize, ySize);
			}
		}
		
		if(fel10aValidMove(x - 1, y, nextValue, heightMatrix, xSize, ySize)) { //bal
			//System.out.println(x + "," + y + "; next: " + nextValue + "; bal");
			if(nextValue == 9) {
				fel10TrailSet.add((x-1) + "," + y);
				//System.out.println("OK - " + (x-1) + "," + y);
				fel10AllTrailCnt++;
				
			} else {
				fel10aMove(x - 1, y, nextValue, heightMatrix, xSize, ySize);
			}
		}
		
		if(fel10aValidMove(x, y - 1, nextValue, heightMatrix, xSize, ySize)) { //fel
			//System.out.println(x + "," + y + "; next: " + nextValue + "; fel");
			if(nextValue == 9) {
				fel10TrailSet.add((x) + "," + (y-1));
				//System.out.println("OK - " + (x) + "," + (y - 1));
				fel10AllTrailCnt++;
			} else {
				fel10aMove(x, y - 1, nextValue, heightMatrix, xSize, ySize);
			}
		}
		
		
		if(fel10aValidMove(x, y + 1, nextValue, heightMatrix, xSize, ySize)) { //le
			//System.out.println(x + "," + y + "; next: " + nextValue + "; le");
			if(nextValue == 9) {
				fel10TrailSet.add((x) + "," + (y+1));
				//System.out.println("OK - " + (x) + "," + (y + 1) );
				fel10AllTrailCnt++;
			} else {
				fel10aMove(x, y + 1, nextValue, heightMatrix, xSize, ySize);
			}
		}
		
	}
	
	private static boolean fel10aValidMove(int x, int y, int nextValue, int[][] heightMatrix, int xSize, int ySize) {
		if(x >= 0 && x < xSize && y >= 0 && y < ySize && heightMatrix[y][x] == nextValue) {
			return true;
		}
		return false;
	}

	private static void fel9a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_9_input.txt"), "UTF-8");
		
		
		//String input = "2333133121414131402";
		String input = lines.get(0);
		
		ArrayList<Integer> fileIndexList = new ArrayList<Integer>();
		ArrayList<Integer> spaceIndexes = new ArrayList<Integer>();
		
		int fileIndex = 0;
		for(int i = 0; i < input.length(); i++) {
			int currNum = Integer.valueOf(""+ input.charAt(i));
			
			int index = fileIndex;
			if(i % 2 == 1) {
				index = -1;
			} else {
				fileIndex++;
			}
			
			for(int j = 0; j < currNum; j++) {
				fileIndexList.add(index);
			}
			
		}
		
		for(int i = 0; i < fileIndexList.size(); i++) {
			if(fileIndexList.get(i) == -1) {
				spaceIndexes.add(i);
			}
		}
		
		int replaceCnt = 0;
		for(int i = fileIndexList.size() - 1; i > 0; i--) {
			if(fileIndexList.get(i) != -1) {
				if(replaceCnt >= spaceIndexes.size()) {
					break;
				}
				
				int spaceIndex = spaceIndexes.get(replaceCnt);
				
				if(spaceIndex > i) {
					break;
				}
				
				int toReplace = fileIndexList.get(i);
				fileIndexList.set(spaceIndex, toReplace);
				fileIndexList.set(i, -1);
				replaceCnt++;
			}
		}
		
		System.out.println("fileIndexList: " + fileIndexList);
		
		long chekSum = 0;
		
		for(int i = 0; i < fileIndexList.size(); i++) {
			if(fileIndexList.get(i) == -1) {
				break;
			}
			int currNum = fileIndexList.get(i);
			chekSum += currNum * i;
		}
		
		System.out.println("chekSum: " + chekSum);
	}

	private static void fel8b() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_8_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] antinodeMatrix = new char[ySize][xSize];
		
		HashMap<Character, ArrayList<Coord>> antennaMap = new HashMap<Character, ArrayList<Coord>>();
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				char curr = line.charAt(x);
				if(curr == '.') {
					continue;
				}
				ArrayList<Coord> coords = antennaMap.get(curr);
				if(coords == null) {
					coords = new ArrayList<AoC.Coord>();
					antennaMap.put(curr, coords);
				}
				coords.add(new Coord(x, y));
			}
		}
		
		antennaMap.forEach((key, value) -> System.out.println(key + ":" + value));
		
		for(char key: antennaMap.keySet()) {
			System.out.println("key: " + key);
			ArrayList<Coord> coords = antennaMap.get(key);
			for(int i = 0; i < coords.size() - 1; i++) {
				for(int j = i + 1; j< coords.size(); j++) {
					Coord c1 = coords.get(i);
					Coord c2 = coords.get(j);
					
					//antinodeMatrix[c1.y][c1.x] = '#';
					//antinodeMatrix[c2.y][c2.x] = '#';
					
					int dx = c1.x - c2.x;
					int dy = c1.y - c2.y;

					
					for(int t = 0; t < 100; t++) {
						Coord an1 = new Coord(c1.x + t*dx, c1.y + t*dy);
						if(an1.x >= 0 && an1.x < xSize && an1.y >= 0 && an1.y < ySize) {
							antinodeMatrix[an1.y][an1.x] = '#';
						} else {
							break;
						}
					}
					
					
					for(int t = 0; t < 100; t++) {
						Coord an2 = new Coord(c2.x - t*dx, c2.y - t*dy);
						if(an2.x >= 0 && an2.x < xSize && an2.y >= 0 && an2.y < ySize) {
							antinodeMatrix[an2.y][an2.x] = '#';
						} else {
							break;
						}
					}
				}
			}
		}
		
		printMatrix(antinodeMatrix, xSize, ySize);
		
		int cnt = 0;
		for(int y = 0; y < ySize; y++) {
			for(int x = 0; x < xSize; x++) {
				if(antinodeMatrix[y][x] == '#') {
					cnt++;
				}
			}
		}
		
		System.out.println("cnt: " + cnt);
	}
	
	private static void fel8a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_8_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] antinodeMatrix = new char[ySize][xSize];
		
		HashMap<Character, ArrayList<Coord>> antennaMap = new HashMap<Character, ArrayList<Coord>>();
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				char curr = line.charAt(x);
				if(curr == '.') {
					continue;
				}
				ArrayList<Coord> coords = antennaMap.get(curr);
				if(coords == null) {
					coords = new ArrayList<AoC.Coord>();
					antennaMap.put(curr, coords);
				}
				coords.add(new Coord(x, y));
			}
		}
		
		antennaMap.forEach((key, value) -> System.out.println(key + ":" + value));
		
		for(char key: antennaMap.keySet()) {
			System.out.println("key: " + key);
			ArrayList<Coord> coords = antennaMap.get(key);
			for(int i = 0; i < coords.size() - 1; i++) {
				for(int j = i + 1; j< coords.size(); j++) {
					Coord c1 = coords.get(i);
					Coord c2 = coords.get(j);
					
					System.out.println("c1: " + c1 + " ; c2: " + c2);
					
					int dx = c1.x - c2.x;
					int dy = c1.y - c2.y;
					
					Coord an1 = new Coord(c1.x + dx, c1.y + dy);
					Coord an2 = new Coord(c2.x - dx, c2.y - dy);
					
					
					
					if(an1.x >= 0 && an1.x < xSize && an1.y >= 0 && an1.y < ySize) {
						antinodeMatrix[an1.y][an1.x] = '#';
					}
					
					if(an2.x >= 0 && an2.x < xSize && an2.y >= 0 && an2.y < ySize) {
						antinodeMatrix[an2.y][an2.x] = '#';
					}
				}
			}
		}
		
		printMatrix(antinodeMatrix, xSize, ySize);
		
		int cnt = 0;
		for(int y = 0; y < ySize; y++) {
			for(int x = 0; x < xSize; x++) {
				if(antinodeMatrix[y][x] == '#') {
					cnt++;
				}
			}
		}
		
		System.out.println("cnt: " + cnt);
	}
	
	private static void printMatrix(char[][] matrix, int xSize, int ySize) {
		for(int y = 0; y < ySize; y++) {
			for(int x = 0; x < xSize; x++) {
				System.out.print(matrix[y][x] == '\u0000' ? '.' : matrix[y][x]);
			}
			System.out.println("");
		}
	}
	
	private static void printMatrix(int[][] matrix, int xSize, int ySize) {
		for(int y = 0; y < ySize; y++) {
			for(int x = 0; x < xSize; x++) {
				System.out.print(matrix[y][x]);
			}
			System.out.println("");
		}
	}
	
	private static void fel7b() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_7_input.txt"), "UTF-8");
		
		long sum = 0;
		
		for (String line : lines) {
			String[] split = line.split(":");
			
			long value = Long.parseLong(split[0]);
			
			int[] nums = getNumArray(split[1].trim().split(" "));
			
			int opCnt = nums.length - 1;
			
			int opNum = (int) (Math.pow(3, opCnt));
			
			for(int i = 0; i < opNum; i++) {
				String ops = StringUtils.leftPad(Integer.toString(i, 3), opCnt, "0");
				//System.out.println(Arrays.toString(nums) + ": " + ops);
				
				long calcVal = nums[0];
				for(int j = 0; j < opCnt; j++) {
					char opChar = ops.charAt(j);
					if(opChar == '0') {
						calcVal = calcVal + nums[j+1];
					} else if(opChar == '1'){
						calcVal = calcVal * nums[j+1];
					} else {
						calcVal = Long.parseLong("" + calcVal + nums[j+1]);  
					}
				}
				
				if(calcVal == value) {
					System.out.println(value + " found");
					sum += value;
					break;
				}
			}
			
			
		}
		
		System.out.println("sum: " + sum);
		
	}
	
	private static void fel7a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_7_input.txt"), "UTF-8");
		
		long sum = 0;
		
		for (String line : lines) {
			String[] split = line.split(":");
			
			long value = Long.parseLong(split[0]);
			
			int[] nums = getNumArray(split[1].trim().split(" "));
			
			int opCnt = nums.length - 1;
			
			int opNum = (int) (Math.pow(2, opCnt));
			
			for(int i = 0; i < opNum; i++) {
				String ops = StringUtils.leftPad(Integer.toBinaryString(i), opCnt, "0");
				//System.out.println(Arrays.toString(nums) + ": " + ops);
				
				long calcVal = nums[0];
				for(int j = 0; j < opCnt; j++) {
					char opChar = ops.charAt(j);
					if(opChar == '0') {
						calcVal = calcVal + nums[j+1];
					} else {
						calcVal = calcVal * nums[j+1];
					}
				}
				
				if(calcVal == value) {
					System.out.println(value + " found");
					sum += value;
					break;
				}
			}
			
			
		}
		
		System.out.println("sum: " + sum);
		
	}
	
	private static int[] getNumArray(String[] vals) {
		int[] nums = new int[vals.length];
		
		for (int i = 0; i < vals.length; i++) {
			nums[i] = Integer.parseInt(vals[i]);
		}
		return nums;
	}

	private static void fel6b() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_6_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] charMatrix = new char[ySize][xSize];
		ArrayList<Coord> blocks = new ArrayList<AoC.Coord>();
		
		int xCurr = -1, yCurr = -1;
		int loopCnt = 0;
		int stepCnt = 0;
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				char curr = line.charAt(x);
				charMatrix[y][x] = curr;
				
				if(curr == '^') {
					xCurr = x;
					yCurr = y;
					charMatrix[y][x] = 'X';
				}
			}
			
		}
		
		int dirIndex = 0;
		System.out.println("Start: " + xCurr + "," + yCurr);
		int xNext = -1, yNext = -1;
		while(true) {
			xNext = xCurr;
			yNext = yCurr;
			
			switch (dirIndex % 4) {
				case 0: //fel
					yNext--;
					break;
				case 1: //jobb	
					xNext++;
					break;
				case 2: //le	
					yNext++;
					break;
				case 3: //bal	
					xNext--;
					break;
			}
			
			if(xNext < 0 || xNext >= xSize || yNext < 0 || yNext >= ySize) {
				break;
			}
			
			
			switch (charMatrix[yNext][xNext]) {
				case '#':
					dirIndex++;
					blocks.add(new Coord(xNext, yNext));
					/*if(blocks.size() > 3) {
						blocks.remove(0);
					}*/	
					break;
				case '.':
					charMatrix[yNext][xNext] = 'X';
				case 'X':
					xCurr = xNext;
					yCurr = yNext;
					stepCnt++;
					System.out.println("Step: " + stepCnt);
					if(fel6bCheckLoop3(xCurr, yCurr, dirIndex, blocks, charMatrix, xSize, ySize, stepCnt)) {
						loopCnt++;
						//System.out.println("loop");
					}
					break;
			}
		}
		
		System.out.println("loopCnt: " + loopCnt);
		
	}

	private static boolean fel6bCheckLoop3(int xCurr, int yCurr, int dirIndex, ArrayList<Coord> blocks, char[][] charMatrix, int xSize, int ySize, int stepCnt) {
		boolean possibleLoop = false;
		
		int xStart = xCurr;
		int yStart = yCurr;
		
		dirIndex++;
		int xNext = -1, yNext = -1;
		int subStep = 0;
		while(true) {
			xNext = xCurr;
			yNext = yCurr;
			
			switch (dirIndex % 4) {
				case 0: //fel
					yNext--;
					break;
				case 1: //jobb	
					xNext++;
					break;
				case 2: //le	
					yNext++;
					break;
				case 3: //bal	
					xNext--;
					break;
			}
			
			if(xNext < 0 || xNext >= xSize || yNext < 0 || yNext >= ySize) {
				break;
			}
			
			boolean move = false;
			switch (charMatrix[yNext][xNext]) {
				case '#':
					dirIndex++;
					break;
				case '.':
				case 'X':
					xCurr = xNext;
					yCurr = yNext;
					move = true;
					subStep++;
					System.out.println("subStep: " + stepCnt + "/" + subStep + " - " + xCurr + "," + yCurr + "; dir: " + dirIndex);
					break;
			}
			
			if(xCurr == xStart && yCurr == yStart && move) {
				possibleLoop = true;
				break;
			}
			
			if(subStep > 100000) {
				possibleLoop = true;
				break;
			}
		}
		
		return possibleLoop;
	}
	
	private static boolean fel6bCheckLoop2(int xCurr, int yCurr, int dirIndex, ArrayList<Coord> blocks, char[][] charMatrix, int xSize, int ySize) {
		boolean possibleLoop = false;
		int xLoop = 1, yLoop = -1;
		switch (dirIndex % 4) {
			case 0: //fel
				for (Coord coord : blocks) {
					if(coord.x > xCurr && coord.y == yCurr) {
						possibleLoop = true;
						xLoop = xCurr;
						yLoop = yCurr - 1;
						System.out.println("Loop: " + (xLoop) + "," + (yLoop) + "; lentről");
						break;
					}
				}
				break;

			case 1: //jobb
				for (Coord coord : blocks) {
					if(coord.x == xCurr && coord.y > yCurr) {
						possibleLoop = true;
						xLoop = xCurr + 1;
						yLoop = yCurr;
						System.out.println("Loop: " + (xLoop) + "," + (yLoop) + "; balról");
						break;
					}
				}
				break;
			
			case 2: //le
				for (Coord coord : blocks) {
					if(coord.x < xCurr && coord.y == yCurr) {
						possibleLoop = true;
						xLoop = xCurr;
						yLoop = yCurr + 1;
						System.out.println("Loop: " + (xLoop) + "," + (yLoop) + "; fentről");
						break;
					}
				}
				break;
			
			case 3: //bal
				for (Coord coord : blocks) {
					if(coord.x == xCurr && coord.y < yCurr) {
						possibleLoop = true;
						xLoop = xCurr - 1;
						yLoop = yCurr;
						System.out.println("Loop: " + (xLoop) + "," + (yLoop) + "; jobbról");
						break;
					}
				}
				break;	
		}
		
		if(yLoop >= ySize || yLoop < 0 || xLoop >= xSize || xLoop < 0 || charMatrix[yLoop][xLoop] == '#') {
			possibleLoop = false;
		}
		
		return possibleLoop;
	}

	
	private static boolean fel6bCheckLoop(int xCurr, int yCurr, int dirIndex, ArrayList<Coord> blocks, char[][] charMatrix, int xSize, int ySize) {
		boolean possibleLoop = false;
		int xLoop = 1, yLoop = -1;
		switch ((dirIndex + 1) % 4) {
			case 0: //fel
				for (Coord coord : blocks) {
					if(coord.x == xCurr && coord.y < yCurr) {
						possibleLoop = true;
						xLoop = xCurr - 1;
						yLoop = yCurr;
						System.out.println("Loop: " + (xCurr - 1) + "," + (yCurr) + "; jobbról");
						break;
					}
				}
				break;

			case 1: //jobb
				for (Coord coord : blocks) {
					if(coord.x > xCurr && coord.y == yCurr) {
						possibleLoop = true;
						xLoop = xCurr;
						yLoop = yCurr - 1;
						System.out.println("Loop: " + (xCurr) + "," + (yCurr - 1) + "; lentről");
						break;
					}
				}
				break;
			
			case 2: //le
				for (Coord coord : blocks) {
					if(coord.x == xCurr && coord.y > yCurr) {
						possibleLoop = true;
						xLoop = xCurr + 1;
						yLoop = yCurr;
						System.out.println("Loop: " + (xCurr + 1) + "," + (yCurr) + "; balról");
						break;
					}
				}
				break;
			
			case 3: //bal
				for (Coord coord : blocks) {
					if(coord.x < xCurr && coord.y == yCurr) {
						possibleLoop = true;
						xLoop = xCurr;
						yLoop = yCurr + 1;
						System.out.println("Loop: " + (xCurr) + "," + (yCurr + 1) + "; fentről");
						break;
					}
				}
				break;	
		}
		
		if(yLoop >= ySize || yLoop < 0 || xLoop >= xSize || xLoop < 0 || charMatrix[yLoop][xLoop] == '#') {
			possibleLoop = false;
		}
		
		return possibleLoop;
	}

	private static void fel6a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_6_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] charMatrix = new char[ySize][xSize];
		
		int xCurr = -1, yCurr = -1;
		int xCnt = 0;
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				char curr = line.charAt(x);
				charMatrix[y][x] = curr;
				
				if(curr == '^') {
					xCurr = x;
					yCurr = y;
					charMatrix[y][x] = 'X';
					xCnt++;
					
				}
			}
			
		}
		
		int dirIndex = 0;
		System.out.println("Start: " + xCurr + "," + yCurr);
		int xNext = -1, yNext = -1;
		while(true) {
			xNext = xCurr;
			yNext = yCurr;
			
			switch (dirIndex % 4) {
				case 0: //fel
					yNext--;
					break;
				case 1: //jobb	
					xNext++;
					break;
				case 2: //le	
					yNext++;
					break;
				case 3: //bal	
					xNext--;
					break;
			}
			
			if(xNext < 0 || xNext >= xSize || yNext < 0 || yNext >= ySize) {
				break;
			}
			
			
			switch (charMatrix[yNext][xNext]) {
				case '#':
					dirIndex++;
					break;
				case '.':
					charMatrix[yNext][xNext] = 'X';
					xCnt++;
				case 'X':
					xCurr = xNext;
					yCurr = yNext;
					break;
			}
		}
		
		System.out.println("xCnt: " + xCnt);
		
	}
	
	private static void fel5b() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_5_input.txt"), "UTF-8");
		
		ArrayList<String> incorrectRules = new ArrayList<String>();
		
		int sum = 0;
		
		int lineNr = 0;
		for (String line : lines) {
			if(line.contains("|")) {
				String[] split = line.split("\\|");
				incorrectRules.add(split[1] + "|" + split[0]);
			}
			
			if("".equals(line)) {
				//System.out.println("incrules: " + incorrectRules);
			}
			
			if(line.contains(",")) {
				lineNr++;
				String[] nums = line.split(",");
				boolean corrected = false; 
				for(int i = 0; i < nums.length -1; i++) {
					for(int j = i + 1; j < nums.length; j++) {
						if(incorrectRules.contains(nums[i] + "|" + nums[j])) {
							System.out.println("incorrect: " + nums[i] + "|" + nums[j]);
							corrected = true;
							String vi = nums[i];
							String vj = nums[j];
							
							nums[i] = vj;
							nums[j] = vi;
							
						}
					}
				}
				
				if(corrected) {
					System.out.println(lineNr + ". corrected, line: " + Arrays.asList(nums) + "adding: " + nums[nums.length/2]);
					sum += Integer.parseInt(nums[nums.length/2]);
				}
				
			}
			
		}
		
		System.out.println("sum: " + sum);
	}
	
	
	private static void fel5a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_5_input.txt"), "UTF-8");
		
		//HashMap<Integer, Integer> rules = new HashMap<Integer, Integer>();
		ArrayList<String> incorrectRules = new ArrayList<String>();
		
		int sum = 0;
		
		int lineNr = 0;
		for (String line : lines) {
			if(line.contains("|")) {
				String[] split = line.split("\\|");
				//rules.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
				incorrectRules.add(split[1] + "|" + split[0]);
			}
			
			if("".equals(line)) {
				//System.out.println("incrules: " + incorrectRules);
			}
			
			if(line.contains(",")) {
				lineNr++;
				String[] nums = line.split(",");
				boolean correct = true; 
				kulso: for(int i = 0; i < nums.length -1; i++) {
					for(int j = i + 1; j < nums.length; j++) {
						if(incorrectRules.contains(nums[i] + "|" + nums[j])) {
							//System.out.println("incorrect: " + nums[i] + "|" + nums[j]);
							correct = false;
							break kulso;
						}
					}
				}
				
				if(correct) {
					//System.out.println(lineNr + ". correct, adding: " + nums[nums.length/2]);
					sum += Integer.parseInt(nums[nums.length/2]);
				}
				
			}
			
		}
		
		System.out.println("sum: " + sum);
	}
	

	private static void fel4b() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_4_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] charMatrix = new char[ySize][xSize];
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				charMatrix[y][x] = line.charAt(x);
				
			}
			
		}
		
		int textCnt = 0;
		
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				if(charMatrix[y][x] != 'A') {
					continue;
				}

				try {
					String text1 = String.valueOf(charMatrix[y-1][x-1]) + "A" +
									String.valueOf(charMatrix[y+1][x+1]);
	
					if(!("MAS".equals(text1) || "SAM".equals(text1))) {continue;}
					
					String text2 = String.valueOf(charMatrix[y-1][x+1]) + "A" +
							String.valueOf(charMatrix[y+1][x-1]);
					
					if("MAS".equals(text2) || "SAM".equals(text2)) {textCnt++;}
					
				} catch (IndexOutOfBoundsException e) {
					
				}	
				
			}
			//System.out.println("");
			
		}
		
		System.out.println("textCnt: " + textCnt);
	}

	
	private static void fel4a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_4_input.txt"), "UTF-8");
		
		int xSize = lines.get(0).length();
		int ySize = lines.size();
		char[][] charMatrix = new char[ySize][xSize];
		
		for (int y = 0; y < ySize; y++) {
			String line = lines.get(y);
			
			for (int x = 0; x < xSize; x++) {
				charMatrix[y][x] = line.charAt(x);
				
			}
			
		}
		
		int textCnt = 0;
		
		String text = null;
		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				//System.out.print(charMatrix[y][x]);
				text = fel4aGetRight(charMatrix, x,y);
				if("XMAS".equals(text) || "SAMX".equals(text)) {textCnt++;}
				text = fel4aGetDown(charMatrix, x,y);
				if("XMAS".equals(text) || "SAMX".equals(text)) {textCnt++;}
				text = fel4aGetRightDown(charMatrix, x,y);
				if("XMAS".equals(text) || "SAMX".equals(text)) {textCnt++;}
				text = fel4aGetLeftDown(charMatrix, x,y);
				if("XMAS".equals(text) || "SAMX".equals(text)) {textCnt++;}
			}
			//System.out.println("");
			
		}
		
		System.out.println("textCnt: " + textCnt);
	}

	private static String fel4aGetRight(char[][] charMatrix, int x, int y) {
		try {
			String text = String.valueOf(charMatrix[y][x]) +
							String.valueOf(charMatrix[y][x+1]) +
							String.valueOf(charMatrix[y][x+2]) +
							String.valueOf(charMatrix[y][x+3]);
			
			return text;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private static String fel4aGetDown(char[][] charMatrix, int x, int y) {
		try {
			String text = String.valueOf(charMatrix[y][x]) +
							String.valueOf(charMatrix[y+1][x]) +
							String.valueOf(charMatrix[y+2][x]) +
							String.valueOf(charMatrix[y+3][x]);
			
			return text;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private static String fel4aGetRightDown(char[][] charMatrix, int x, int y) {
		try {
			String text = String.valueOf(charMatrix[y][x]) +
							String.valueOf(charMatrix[y+1][x+1]) +
							String.valueOf(charMatrix[y+2][x+2]) +
							String.valueOf(charMatrix[y+3][x+3]);
			
			return text;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private static String fel4aGetLeftDown(char[][] charMatrix, int x, int y) {
		try {
			String text = String.valueOf(charMatrix[y][x]) +
							String.valueOf(charMatrix[y+1][x-1]) +
							String.valueOf(charMatrix[y+2][x-2]) +
							String.valueOf(charMatrix[y+3][x-3]);
			
			return text;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private static void fel3a() throws IOException {
		//String line = "what()-%*;[mul(826,659)what()&mul(622,241)}^from();why()mul(499,923))mul(589,186)~how()why()]/~who()}mul(57,224)* ##[[*>mul(206,45)select(){~from(63,961)+!/'@mul(365,743)^ from()mul(94,410)$how()(^ )/,mul(592,884) mul(265,485))^#[[mul(763,659),mul(275,537)$;who()*mul(511,392)))what()(+from()from()>&-mul(416,947)mul(868,183)?:where()*when()#-where(890,406)#-mul(873,379)mul(195,835)/,%?],!-{(mul(225,902)where()'(where()-@#mul(544,955)how();~when(222,774)mul(538,277),from()from(717,816)$)(!%select()mul(247,162)**why()!}/where()mul(411,570)]mul(158,805)<[)}!@$select()don't()&?mul(475,153)when()mul(44,394)mul(505,328)select(),;[+mul(228,58)}/)why()?who()mul(706,785)$!#mul(635,796)#[where(){^select(275,150)-/)mul(85,214)<when()/*&where()mul(438,107)who(); what()why()why()mul(556,985):-+;@why()}?mul(581,266)why() %mul(570,646)what();@<$mul(626,256)#>do()where()@'<mul(42,958)#{mul(980,871) ,:{^!>mul(651,67)]-mul(530,38)why()+^don't(),where();what()~mul(532,711)<how()-;(^mul(538,343);mul(403,312)#where()select()<}mul(160,441) (mul(35,591)from()mul(458,977)mul(682,17)who()select()!^ :how()from()!~mul(698,638)'<@;'where()'from()!select(458,218)mul(80,356)^,what(554,850)*<&what()select()select()do()>,;)$why()+what();%mul(265,354)-mul(338,874)?]mul(284,884)}<when()mul(473,399)%>'?where(),mul(614,138)&who()~}[why()from()mul(779,747)^mul(7,27)select()}>+^-*<mul(808,414) &)when()[;&mul(969,162):where(){mul(923,581))*when()who()}?mul(604,624)**mul(968,780)):/{mul(808,433)]!who()from()mul(103,80)@^why()!mul(335,504)&how():how()}mul(407,608)'+mul(350,417)select()>who()*mul(545,113)who()('!&how(),don't()+{$&mul(842,42)<#mul(788,22),why(),mul(581,843)why()?mul(415,102)[mul(782,483)::(&',^mul(411,597)mul(800,946)[]($}:+)]mul(6,738)who()+where()@}(where()>$when()mul(737,227)select()~ why(984,422)mul(690,299)$how()!why()when() why()>:who()mul(62,391)mul(559,901)mul(152,669)([why()-select();*(mul(674,497)mul(195,917)from()#what(),mul(332,868)select()')@#mul(957,359)(when()]>(<what()!mul(602,990)+@how()]$^mul(885,543)mul(564,365)mul(912,603)what()when()how()mul(216,398+&#'*-,]:mul(971,792)!do()from()who()mul(741,501)~how()where()<what()[#where()mul(268,825)what()mul(958,990)){?(+'mul(427,368):%?how()?(mul(725,420)?when()}-from();mul(414,122/%-when():-'@mul(450,900)&what()<{ ]<'(?do()%]^!} mul(401,278)when() -(! -do()when()*select()?@[~@from()why()mul(892,125)~what()+;/mulwhy():mul(375,500)^(#>%,mul(252,836)[why()?<how(643,18);*mul(223,308)$where()mul(401,278)why():mul(276,426)mul(793,320)what(85,700)})mul(485,616)where();when()}[! from();]mul(582,705) !:mul(427,563)<*<#how():!do()'&-}!']:'mul(615,412)where(928,116)];select()(mul(812,857)&@?[select()$~do()mul(49,314)?{mul(164,850)mul(345,646)[}from()}]<'#select()don't()<;+%where(445,34)*!mul!when()-{mul(471,900)<[-from()&@mul(442,893)where())*mul(798,495)}<+select(130,600)why()(from()mul(860,565)select()~why(406,274)mul(397,514)who()why()]~mul(654,583>where()!,mul(877,551)&select()[<?&{}when(){mul(528,802),#-)+(&select()/mul(878,466)#how(872,781)mul(964,641>}->(*[mul(847,681),why()select()select()*@,mul(211,86)when()select()-:)#']mul(416,630):(}?$[:)mul(255,942):-*:!~%@how()what()mul(117,324)";
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_3_input.txt"), "UTF-8");
		
		long sum = 0;
		for (String line : lines) {
			
			//String line = "what()-%*;[mul(826,659)what()&mul(622,241)}^";
			
			//jo
			//Pattern p = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
			
			Pattern p = Pattern.compile("(mul\\((\\d{1,3}),(\\d{1,3})\\))");
			Matcher m = p.matcher(line);
			while(m.find()) {
				System.out.println(m.group(1));
				System.out.println(m.group(2) + "*" + m.group(3));
				sum += Integer.parseInt(m.group(2)) * Integer.parseInt(m.group(3));
			}
			
			/*
			if(m.find()) {
			
				for(int i = 0; i < m.groupCount(); i++) {
					System.out.println(m.group(i));
				}
			}*/

		}	
		System.out.println("sum: " + sum);
	}
	
	private static void fel3b() throws IOException {
		//String line = "what()-%*;[mul(826,659)what()&mul(622,241)}^from();why()mul(499,923))mul(589,186)~how()why()]/~who()}mul(57,224)* ##[[*>mul(206,45)select(){~from(63,961)+!/'@mul(365,743)^ from()mul(94,410)$how()(^ )/,mul(592,884) mul(265,485))^#[[mul(763,659),mul(275,537)$;who()*mul(511,392)))what()(+from()from()>&-mul(416,947)mul(868,183)?:where()*when()#-where(890,406)#-mul(873,379)mul(195,835)/,%?],!-{(mul(225,902)where()'(where()-@#mul(544,955)how();~when(222,774)mul(538,277),from()from(717,816)$)(!%select()mul(247,162)**why()!}/where()mul(411,570)]mul(158,805)<[)}!@$select()don't()&?mul(475,153)when()mul(44,394)mul(505,328)select(),;[+mul(228,58)}/)why()?who()mul(706,785)$!#mul(635,796)#[where(){^select(275,150)-/)mul(85,214)<when()/*&where()mul(438,107)who(); what()why()why()mul(556,985):-+;@why()}?mul(581,266)why() %mul(570,646)what();@<$mul(626,256)#>do()where()@'<mul(42,958)#{mul(980,871) ,:{^!>mul(651,67)]-mul(530,38)why()+^don't(),where();what()~mul(532,711)<how()-;(^mul(538,343);mul(403,312)#where()select()<}mul(160,441) (mul(35,591)from()mul(458,977)mul(682,17)who()select()!^ :how()from()!~mul(698,638)'<@;'where()'from()!select(458,218)mul(80,356)^,what(554,850)*<&what()select()select()do()>,;)$why()+what();%mul(265,354)-mul(338,874)?]mul(284,884)}<when()mul(473,399)%>'?where(),mul(614,138)&who()~}[why()from()mul(779,747)^mul(7,27)select()}>+^-*<mul(808,414) &)when()[;&mul(969,162):where(){mul(923,581))*when()who()}?mul(604,624)**mul(968,780)):/{mul(808,433)]!who()from()mul(103,80)@^why()!mul(335,504)&how():how()}mul(407,608)'+mul(350,417)select()>who()*mul(545,113)who()('!&how(),don't()+{$&mul(842,42)<#mul(788,22),why(),mul(581,843)why()?mul(415,102)[mul(782,483)::(&',^mul(411,597)mul(800,946)[]($}:+)]mul(6,738)who()+where()@}(where()>$when()mul(737,227)select()~ why(984,422)mul(690,299)$how()!why()when() why()>:who()mul(62,391)mul(559,901)mul(152,669)([why()-select();*(mul(674,497)mul(195,917)from()#what(),mul(332,868)select()')@#mul(957,359)(when()]>(<what()!mul(602,990)+@how()]$^mul(885,543)mul(564,365)mul(912,603)what()when()how()mul(216,398+&#'*-,]:mul(971,792)!do()from()who()mul(741,501)~how()where()<what()[#where()mul(268,825)what()mul(958,990)){?(+'mul(427,368):%?how()?(mul(725,420)?when()}-from();mul(414,122/%-when():-'@mul(450,900)&what()<{ ]<'(?do()%]^!} mul(401,278)when() -(! -do()when()*select()?@[~@from()why()mul(892,125)~what()+;/mulwhy():mul(375,500)^(#>%,mul(252,836)[why()?<how(643,18);*mul(223,308)$where()mul(401,278)why():mul(276,426)mul(793,320)what(85,700)})mul(485,616)where();when()}[! from();]mul(582,705) !:mul(427,563)<*<#how():!do()'&-}!']:'mul(615,412)where(928,116)];select()(mul(812,857)&@?[select()$~do()mul(49,314)?{mul(164,850)mul(345,646)[}from()}]<'#select()don't()<;+%where(445,34)*!mul!when()-{mul(471,900)<[-from()&@mul(442,893)where())*mul(798,495)}<+select(130,600)why()(from()mul(860,565)select()~why(406,274)mul(397,514)who()why()]~mul(654,583>where()!,mul(877,551)&select()[<?&{}when(){mul(528,802),#-)+(&select()/mul(878,466)#how(872,781)mul(964,641>}->(*[mul(847,681),why()select()select()*@,mul(211,86)when()select()-:)#']mul(416,630):(}?$[:)mul(255,942):-*:!~%@how()what()mul(117,324)";
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_3_input.txt"), "UTF-8");
		
		long sum = 0;
		boolean enabled = true;
		
		for (String line : lines) {
			//String line = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
			
			//jo
			//Pattern p = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
			
			Pattern p = Pattern.compile("(don't\\(\\))|(do\\(\\))|(mul\\((\\d{1,3}),(\\d{1,3})\\))");
			Matcher m = p.matcher(line);
			
			int fIndex = 0;
			
			while(m.find()) {
				fIndex++;
				
				System.out.println(fIndex + ": " + m.group(1) + "; " + m.group(2) + "; " + m.group(3) + "; " + m.group(4) + "; " + m.group(5));
				
				if(m.group(1) != null) {
					enabled = false;
				} else if(m.group(2) != null) {
					enabled= true;
				} else {
				
					if(enabled) {
						sum += Integer.parseInt(m.group(4)) * Integer.parseInt(m.group(5));
					}
				}
				
			}
		}
		System.out.println("sum: " + sum);
	}

	private static void fel2a() throws IOException {
		List<String> lines = FileUtils.readLines(new File("d:\\temp\\aoc_2_input.txt"), "UTF-8");
		
		int safeCnt = 0;
		
		int lineCnt = 0;
		for (String line : lines) {
			lineCnt++;
			String[] textItems = line.split(" ");
			
			System.out.print(lineCnt + ": " + line);
			boolean safe = fel2IsSafe(textItems);
			
			if(safe) {
				safeCnt++;
				System.out.println("   Q");
			} else {
				for(int i = 0; i < textItems.length; i++) {
					String[] newItems = removeItem(textItems, i);
					
					safe = fel2IsSafe(newItems);
					
					if(safe) {
						safeCnt++;
						break;
					}
					
				}
				
				
			}
			
		}
		
		System.out.println("SafeCnt: " + safeCnt);
	}

	private static String[] removeItem(String[] textItems, int indexToRemove) {
		String[] newItems = new String[textItems.length-1];
		
		
		int j = 0;
		for (int i = 0; i < textItems.length; i++) {
			if(i != indexToRemove) {
				newItems[j] = textItems[i];
				j++;
			}
		}
		
		return newItems;
	}

	private static boolean fel2IsSafe(String[] textItems) {
		Boolean inc = null;
		
		boolean safe = true;
		
		for(int i = 0; i < textItems.length; i++) {
			
			if(i > 0) {
				int dif = Integer.parseInt(textItems[i]) - Integer.parseInt(textItems[i-1]);
				
				if(dif == 0) {
					System.out.println("   X dif = 0");
					safe = false;
					break;
				} else if(Math.abs(dif) > 3) {
					System.out.println("   X sok");
					safe = false;
					break;
				}
				
				if(inc == null) {
					inc = dif > 0;
					continue;
				} else if(inc) {
					if(dif < 0) {
						System.out.println("   X inc -> dec");
						safe = false;
						break;
					}
				} else {
					if(dif > 0) {
						System.out.println("   X dec -> inc");
						safe = false;
						break;
					}
				}
				
			}
		}
		
		return safe;
	}
	
	private static class Coord{
		private int x;
		private int y;
		
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return x + ";" + y;
		}
	}
}
