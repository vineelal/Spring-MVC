package com.controllers;


import java.util.ArrayList;

public class Bowling {
	PlayerOut outObj = PlayerOut.getInstance();
	private int currentBatsmen;
	private ArrayList<Ball> ballList;

	public Bowling() {
		ballList = new ArrayList<Ball>();
	}

	public ArrayList<Ball> startBowling(Innings inn, CSVFileReader readCSV) {
		ArrayList<String> tempOnCreasePlayers = new ArrayList<String>();
		String lastWicket = null;
		inn.setteam();
		inn.setOvers(5);
		ballList.clear();
		while (inn.getBalls() > 0) {
			Ball ball = new Ball();
			if (inn.getBalls() == inn.getOvers() * 6)
				ball.setOnCreasePlayers(readCSV.getOnCreasePlayers());
			else
				ball.setOnCreasePlayers(tempOnCreasePlayers);
			ball.setLastWicket(lastWicket);
			currentBatsmen = (int) (Math.random() * (ball.getOnCreasePlayers()
					.size()));
			ball.setCurrentBatsmen(ball.getOnCreasePlayer(currentBatsmen));
			ball.setResultOfBall(readCSV);
			ball.setOut(outObj.checkIfOut(inn, readCSV, ball.getOut(),
					ball.getCurrentBatsmen(), ball, ballList));
			if (inn.getAllOut()) {
				inn.setAllOut(false);
				break;
			}
			ball.setleftOverWickets(10 - inn.getWickets());
			if (ball.getOnCreasePlayers().size() == 2) {
				for (int i = 0; i < 2; i++) {
					ball.setOnCreasePlayerRuns(i,
							readCSV.getRunsOfBatsmen(ball.getOnCreasePlayer(i)));
				}
			} else {
				System.out.println("Only one player is left to bat.....");
				break;
			}
			if (ball.getOut()) {
				ball.clearOnCreasePlayersRuns(currentBatsmen);
			}
			tempOnCreasePlayers = ball.getOnCreasePlayers();
			lastWicket = ball.getLastWicket();
			ballList.add(ball);
			inn.decrementBalls();
			// ball.getValue();
		}
		inn.setTotal(readCSV.getTotalScore());
		return ballList;
	}
}
