package com.controllers;


import java.util.ArrayList;

public class PlayerOut {
	private static PlayerOut playObj = new PlayerOut();
	private PlayerOut(){}
	public static PlayerOut getInstance()
	{
		return playObj;
	}
	void BatsmenOut(Innings inn,CSVFileReader readCSV,String currBatsmen,Ball ball) {
		
		ball.removeOnCreasePlayer(currBatsmen);
		inn.addWickets();
		checkAllOut(inn,readCSV);
		if (!(inn.getAllOut())) {
			ball.addPlayerToCrease(readCSV);
			readCSV.removeFromList();
		}
	}

	Boolean checkIfOut(Innings inn,CSVFileReader readCSV,Boolean out,String currBatsmen,Ball ball,ArrayList<Ball> ballList) {
	//	inn.getValues(inn.ballList);
		if (out == true) {
			ball.setLastWicket();
			BatsmenOut(inn,readCSV,currBatsmen,ball);
			ball.setOut(false);
		}
		return ball.getOut();
	}

	Boolean checkAllOut(Innings inn,CSVFileReader readCSV) {
		if (readCSV.getBatsmenList().size() < 1) {
			inn.setAllOut(true);
		}
		return inn.getAllOut();
	}
}
