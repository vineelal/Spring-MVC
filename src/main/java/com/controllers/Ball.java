package com.controllers;

import java.util.ArrayList;
import java.util.Random;

public class Ball {
	private int bowlScore, batScore, leftOverWickets;
    private String currentBatsmen, result;
	private String lastWicket;
	private Boolean out = false;
	private ArrayList<String> onCreasePlayers;
	private int[] onCreasePlayersRuns = { 0, 0 };
	Random random = new Random();

	public void setleftOverWickets(int leftOverWickets) {
		this.leftOverWickets = leftOverWickets;
	}

	public void setCurrentBatsmen(String currentBatsmen) {
		this.currentBatsmen = currentBatsmen;
	}

	public void setLastWicket() {
		this.lastWicket = currentBatsmen;
	}

	public void setLastWicket(String lastWicket) {
		this.lastWicket = lastWicket;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setOut(Boolean out) {
		this.out = out;
	}

	public void setOnCreasePlayers(ArrayList<String> onCreasePlayers) {
		this.onCreasePlayers = onCreasePlayers;
	}

	public void setOnCreasePlayersRuns(int[] onCreasePlayersRuns) {
		this.onCreasePlayersRuns = onCreasePlayersRuns;
	}

	public void setOnCreasePlayerRuns(int position, int onCreasePlayerRuns) {
		this.onCreasePlayersRuns[position] = onCreasePlayerRuns;
	}

	public void clearOnCreasePlayersRuns(int currentBatsmen) {
		this.onCreasePlayersRuns[currentBatsmen] = 0;
	}

	public int getleftOverWickets() {
		return leftOverWickets;
	}

	public String getCurrentBatsmen() {
		return currentBatsmen;
	}

	public String getLastWicket() {
		return lastWicket;
	}

	public String getResult() {
		return result;
	}

	public Boolean getOut() {
		return out;
	}

	public ArrayList<String> getOnCreasePlayers() {
		return onCreasePlayers;
	}

	public String getOnCreasePlayer(int position) {
		return onCreasePlayers.get(position);
	}

	public int[] getonCreasePlayersRuns() {
		return onCreasePlayersRuns;
	}

	public int getBowlScore() {
		bowlScore = random.nextInt(5);
		return bowlScore;
	}

	public int getBatScore() {
		batScore = random.nextInt(6);
		return batScore;
	}

	public void removeOnCreasePlayer(String currBatsmen) {
		onCreasePlayers.remove(currBatsmen);
	}

	public void addPlayerToCrease(CSVFileReader readCSV) {
		onCreasePlayers.add(readCSV.getNextBatsmen());
	}

	public String setResultOfBall(CSVFileReader readCSV) {
		getBowlScore();
		getBatScore();
		// Assuming player is out if both ball and bat scores are equal
		if (bowlScore == batScore) {
			setResult(readCSV.getResultOfBall(bowlScore));
			setOut(true);
		} else {
			setResult(readCSV.getResultOfBat(batScore));
			if (batScore == 5) {
				batScore = 6;
			}
			readCSV.setBatsmenRuns(currentBatsmen,
					readCSV.getRunsOfBatsmen(currentBatsmen) + batScore);
		}
		return result;
	}
}
