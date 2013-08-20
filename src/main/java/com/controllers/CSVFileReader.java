package com.controllers;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import au.com.bytecode.opencsv.CSVReader;


public class CSVFileReader {
   //private  static CSVFileReader csvObj = null;
			
	private Map<Integer, String> bowlResults = new HashMap<Integer, String>();
	private Map<Integer, String> batResults = new HashMap<Integer, String>();
	private ArrayList<String> batsmenList1 = new ArrayList<String>();
	private ArrayList<String> onCreasePlayers1 = new ArrayList<String>();
	private ArrayList<String> batsmenList2 = new ArrayList<String>();
	private ArrayList<String> onCreasePlayers2 = new ArrayList<String>();
	int innings;
	Innings innObj;

	public CSVFileReader(Innings innObj) {
		this.innObj = innObj;
		this.innings = innObj.getInnings();
	}
	
	public ArrayList<String> createTeam() throws IOException {
		int count = 0;
		String path = getClass().getClassLoader().getResource("team.csv").getPath();
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				count++;
				if (count >= 1 && count <= 10) {
					if (count <= 2)
						onCreasePlayers1.add(nextLine[0]);
					else
						batsmenList1.add(nextLine[0]);
					innObj.runs1.put(nextLine[0], Integer.parseInt(nextLine[1]));
				} else {
					if (count >= 12 && count <= 21) {
						if (count == 12 || count == 13)
							onCreasePlayers2.add(nextLine[0]);
						else
							batsmenList2.add(nextLine[0]);
						innObj.runs2.put(nextLine[0],
								Integer.parseInt(nextLine[1]));
					} else {
						if (count >= 23 && count <= 27)
							bowlResults.put(Integer.parseInt(nextLine[0]),
									nextLine[1]);
						else if (count >= 29 && count <= 34)
							batResults.put(Integer.parseInt(nextLine[0]),
									nextLine[1]);
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		reader.close();
		return onCreasePlayers1;
	}

	String getResultOfBall(int bowlScore) {
		return bowlResults.get(bowlScore).toString();
	}

	String getResultOfBat(int batScore) {
		return batResults.get(batScore).toString();
	}

	ArrayList<String> getBatsmenList() {
		if (this.innings == 0)
			return batsmenList1;
		else
			return batsmenList2;
	}

	ArrayList<String> getOnCreasePlayers() {
		if (this.innings == 0)
			return onCreasePlayers1;
		else
			return onCreasePlayers2;
	}

	HashMap<String, Integer> getRuns() {
		if (this.innings == 0)
			return innObj.runs1;
		else
			return innObj.runs2;
	}

	void setBatsmenRuns(String batsmen, int runs) {
		if (this.innings == 0)
			this.innObj.runs1.put(batsmen, runs);
		else
			this.innObj.runs2.put(batsmen, runs);

	}

	int getRunsOfBatsmen(String currentBatsmen) {
		if (this.innings == 0)
			return innObj.runs1.get(currentBatsmen);
		else
			return innObj.runs2.get(currentBatsmen);

	}

	String getNextBatsmen() {
		if (this.innings == 0)
			return batsmenList1.get(0);
		else
			return batsmenList2.get(0);

	}

	void removeFromList() {
		if (this.innings == 0)
			batsmenList1.remove(0);
		else
			batsmenList2.remove(0);
	}

	int getTotalScore() {
		if (this.innings == 0)
			return getTotal(innObj.runs1);
		else
			return getTotal(innObj.runs2);
	}

	@SuppressWarnings("rawtypes")
	int getTotal(HashMap<String, Integer> runs) {
		int total = 0;
		Iterator<Entry<String, Integer>> iter = runs.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry pairs = iter.next();
			total += (Integer) pairs.getValue();
		}
		return total;
	}
}
