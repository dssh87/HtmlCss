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
			roundNum = Integer.parseInt(getRoundNum(lottoUrl));
			for(int i = 1; i <= 10; i++) {
				tempUrl = "https://dhlottery.co.kr/gameResult.do?method=byWin&drwNo=";
				lottoUrl = tempUrl + i;
				System.out.println("lottoUrl : "+lottoUrl);
				hashMap = getNum(lottoUrl);
				
				round.add(Integer.toString(i));
				win = hashMap.get("win");
				bonus = hashMap.get("bonus");
				
				winList.add(win);
				bonusList.add(bonus);
				System.out.println("win : "+win);
				System.out.println("bonus : "+bonus);
				System.out.println("roundNum : "+roundNum);
				
				listMap.put("winList", winList);
				listMap.put("bonusList", bonusList);
			}
			System.out.println("round : "+round);
			System.out.println("listMap : "+listMap);
			
		} catch (IOException e) {
			e.printStackTrace();
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
		//System.out.println("회차 : "+roundNum);
	
	return roundNum;
}

}
