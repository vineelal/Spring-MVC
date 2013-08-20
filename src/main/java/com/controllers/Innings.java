package com.controllers;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.ui.ModelMap;

public class Innings {
	private int overs, balls, wickets, totalScore;
	private int innings;
	private String team = null;
	private Boolean allOut = false;
	ArrayList<Ball> ballList;
	HashMap<String, Integer> runs1 = new HashMap<String, Integer>();
	HashMap<String, Integer> runs2 = new HashMap<String, Integer>();
	
	public Innings(int innings) {
		ballList = new ArrayList<Ball>();
		this.innings = innings;
	}
	public Innings(){}

	public void setBalls(int overs) {
		this.balls = overs * 6;
	}

	public void decrementBalls() {
		this.balls--;
	}

	public void setOvers(int overs) {
		this.overs = overs;
		this.wickets = 0;
		setBalls(overs);
	}
	
	public void addWickets() {
		this.wickets++;
	}

	public void setTotal(int totalScore) {
		this.totalScore = totalScore;
	}
	public void setteam() {
		if (getInnings() == 0)
			this.team = "India";
		else
			this.team = "Pakistan";
	}

	public void setAllOut(Boolean allOut) {
		this.allOut = allOut;
	}

	public void setBallList(ArrayList<Ball> ballList) {
		this.ballList = ballList;
	}

	public int getBalls() {
		return balls;
	}

	public int getOvers() {
		return overs;
	}

	public int getWickets() {
		return wickets;
	}

	public int getTotal() {
		return totalScore;
	}

	public int getInnings() {
		return innings;
	}

	public String getteam() {
		return team;
	}

	public Boolean getAllOut() {
		return allOut;
	}

	public ArrayList<Ball> getBallList() {
		return ballList;
	}
	
	public HashMap<String,Integer> getruns() {
		if(innings==0)
			return runs1;
			else
				return runs2;
	}
	
	
	public void startInning(CSVFileReader readCSV) {
		this.ballList = bowl.startBowling(this, readCSV);
		//getValues(ballList);
	}

	Bowling bowl = new Bowling();
	public void getValues(ArrayList<Ball> ballList,ModelMap model) {
		
		model.addAttribute("balls" ,ballList.size());
		for (int i = 0; i < ballList.size(); i++) {
			Ball ballobj = ballList.get(i);
			model.addAttribute("curr",ballobj.getCurrentBatsmen());
			model.addAttribute("result",ballobj.getResult());
			model.addAttribute("crease",ballobj.getOnCreasePlayers());
			int[] runs = ballobj.getonCreasePlayersRuns();
			//"OnCreasePlayers runs"
			for (int j = 0; j < 2; j++)
				model.addAttribute("creaseruns",runs[j]);
			// System.out.println(ballobj.onCreasePlayersRuns[1]);
			model.addAttribute("WicketsInHand ",ballobj.getleftOverWickets());
			if(ballobj.getLastWicket()!=null)
				model.addAttribute("LastWicket ",ballobj.getLastWicket());
		}
		
	}
	
}
