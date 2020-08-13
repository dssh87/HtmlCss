package lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Lotto {

	static List<int[]> getArray = null;
	
	static int Array1[] = new int[45];
	static int Array2[] = new int[45];
	static int Array3[] = new int[45];
	static int Array4[] = new int[45];
	static int Array5[] = new int[45];
	static int Array6[] = new int[45];
	static int Array7[] = new int[45];
	static int Array8[] = new int[45];
	static int Array9[] = new int[45];
	static int Array10[] = new int[45];
	static int Array11[] = new int[45];
	static int Array12[] = new int[45];
	static int Array13[] = new int[45];
	static int Array14[] = new int[45];
	static int Array15[] = new int[45];
	static int Array16[] = new int[45];
	static int Array17[] = new int[45];
	static int Array18[] = new int[45];
	static int Array19[] = new int[45];
	static int Array20[] = new int[45];
	static int Array21[] = new int[45];
	static int Array22[] = new int[45];
	static int Array23[] = new int[45];
	static int Array24[] = new int[45];
	static int Array25[] = new int[45];
	static int Array26[] = new int[45];
	static int Array27[] = new int[45];
	static int Array28[] = new int[45];
	static int Array29[] = new int[45];
	static int Array30[] = new int[45];
	static int Array31[] = new int[45];
	static int Array32[] = new int[45];
	static int Array33[] = new int[45];
	static int Array34[] = new int[45];
	static int Array35[] = new int[45];
	static int Array36[] = new int[45];
	static int Array37[] = new int[45];
	static int Array38[] = new int[45];
	static int Array39[] = new int[45];
	static int Array40[] = new int[45];
	static int Array41[] = new int[45];
	static int Array42[] = new int[45];
	static int Array43[] = new int[45];
	static int Array44[] = new int[45];
	static int Array45[] = new int[45];

	public static void main(String[] args) {
		
		HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
		HashMap<String, List<List<String>>> listMap = new HashMap<String, List<List<String>>>();
		
		List<String> win = new ArrayList<String>();
		List<String> bonus = new ArrayList<String>();
		List<String> round = new ArrayList<String>();
		
		List<List<String>> winList = new ArrayList<List<String>>();
		List<List<String>> bonusList = new ArrayList<List<String>>();
		
		// 1~45까지 숫자배열 만들기
		int fortyFiveNum[] = new int[45];
		for(int i = 0; i < fortyFiveNum.length; i++) {
			fortyFiveNum[i] = i+1;
		}
		System.out.println(Arrays.toString(fortyFiveNum));
		
		// 웹크롤링(로또 당첨번호)
		String lottoUrl = "https://dhlottery.co.kr/gameResult.do?method=byWin";
		String winRoundNum = "&drwNo=";
		String tempUrl = "";
		int roundNum = 0;
		try {
			for(int i = 1; i <= 100; i++) {
				tempUrl = "https://dhlottery.co.kr/gameResult.do?method=byWin&drwNo=";
				lottoUrl = tempUrl + i;
				System.out.println("lottoUrl : "+lottoUrl);
				roundNum = Integer.parseInt(getRoundNum(lottoUrl));
				hashMap = getNum(lottoUrl);
				
				round.add(Integer.toString(i));
				win = hashMap.get("win");
				bonus = hashMap.get("bonus");
				
				winList.add(win);
				bonusList.add(bonus);
				System.out.println("win : "+win);
				System.out.println("bonus : "+bonus);
				System.out.println("roundNum : "+roundNum);
				
				compareArray(win);
				
				listMap.put("winList", winList);
				listMap.put("bonusList", bonusList);
			}
			System.out.println("round : "+round);
			System.out.println("listMap : "+listMap);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	// 각 번호별 출현시마다 함께 등장하는 번호를 보관하기 위한 배열 생성
	public static void compareArray(List<String> win) {
		getArray = new ArrayList<>();
		
		String arrayName = "Array";
		
		for(int i = 0; i <= 45; i++) {
			getArray.add(Array1);
			getArray.add(Array2);
			getArray.add(Array3);
			getArray.add(Array4);
			getArray.add(Array5);
			getArray.add(Array6);
			getArray.add(Array6);
			getArray.add(Array7);
			getArray.add(Array8);
			getArray.add(Array9);
			getArray.add(Array10);
			getArray.add(Array11);
			getArray.add(Array12);
			getArray.add(Array13);
			getArray.add(Array14);
			getArray.add(Array15);
			getArray.add(Array16);
			getArray.add(Array17);
			getArray.add(Array18);
			getArray.add(Array19);
			getArray.add(Array20);
			getArray.add(Array21);
			getArray.add(Array22);
			getArray.add(Array23);
			getArray.add(Array24);
			getArray.add(Array25);
			getArray.add(Array26);
			getArray.add(Array27);
			getArray.add(Array28);
			getArray.add(Array29);
			getArray.add(Array30);
			getArray.add(Array31);
			getArray.add(Array32);
			getArray.add(Array33);
			getArray.add(Array34);
			getArray.add(Array35);
			getArray.add(Array36);
			getArray.add(Array37);
			getArray.add(Array38);
			getArray.add(Array39);
			getArray.add(Array40);
			getArray.add(Array41);
			getArray.add(Array42);
			getArray.add(Array43);
			getArray.add(Array44);
			getArray.add(Array45);			
		}
		
		for(int a = 1; a <= 45; a++) {
			if(win.contains(Integer.toString(a))) {
				for(int i = 1; i <= 45; i++ ) {
					if(win.contains(Integer.toString(i))) {
						getArray.get(a-1)[i-1] += 1;
					}
				}
			}		
			System.out.println("Array"+a+" : "+Arrays.toString(getArray.get(a-1)));
		}
		
	}
	
	// 당첨번호 수집
	public static HashMap<String, List<String>> getNum(String lottoUrl)throws IOException  {
		String sourceLine = "";
		String winningNum = "";
		String bonusNum = "";
		
		List<String> win = new ArrayList<String>();
		List<String> bonus = new ArrayList<String>();
		HashMap<String, List<String>> hsMap = new HashMap<String, List<String>>();
		
		URL url = new URL(lottoUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer sourceCode = new StringBuffer();
		
		
		while((sourceLine = br.readLine()) != null) {
			if(sourceLine.contains("<span class=\"ball_645")) {
				winningNum = sourceLine.substring(sourceLine.indexOf("<span class=\"ball_645")+33, sourceLine.length()-7).trim();
				if(winningNum.length() <= 2) {
					win.add(winningNum);
					hsMap.put("win", win);
				}
				if(winningNum.length() > 2) {
					bonusNum = winningNum.substring(0, 2);
					if(bonusNum.contains("<")) {
						bonus.add(bonusNum.substring(0, 1));
					}else {
						bonus.add(bonusNum);
					}
					hsMap.put("bonus", bonus);
				}					
			}
		}
		//System.out.println("당첨번호 : "+win);
		//System.out.println("보너스 : "+bonusNum);
		
		return hsMap;
	}
	
	// 당첨회차 수집
	public static String getRoundNum(String lottoUrl)throws IOException  {
		
		URL url = new URL(lottoUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer sourceCode = new StringBuffer();
		
		String sourceLine = "";
		String roundNum = "";
		
		List<String> win = new ArrayList<>();
		while((sourceLine = br.readLine()) != null) {
					
			if(sourceLine.contains("<strong>")) {
				if(sourceLine.contains("회")) {
					roundNum = sourceLine.substring(sourceLine.indexOf("<strong>")+8, sourceLine.length()-20).trim();
				}
			}
		}		
		System.out.println("회차 : "+roundNum);
	
	return roundNum;
	}

}
