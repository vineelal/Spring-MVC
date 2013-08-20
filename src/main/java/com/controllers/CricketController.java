package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cricket")
public class CricketController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		ArrayList<Innings> innList = new ArrayList<Innings>();
		for (int innings = 0; innings < 2; innings++) {
			Innings inningsObj = new Innings(innings);	
			CSVFileReader readCSV =new CSVFileReader(inningsObj);
			try {
				readCSV.createTeam();
			} catch (IOException e) {
				e.printStackTrace();
			}
			inningsObj.startInning(readCSV);
			innList.add(inningsObj);
		}
		getValues(innList,model);
		return "cricket";
	}
	
	void getValues(ArrayList<Innings> innList,ModelMap model) {
	model.addAttribute("innings",innList.size());
	model.addAttribute("overs",innList.get(0).getOvers());
////		for (Innings inn : innList) {
////			model.addAttribute("Team",inn.getTeamPlaying());
////			model.addAttribute("wickets", inn.getWickets());
////			model.addAttribute("totalscore" ,inn.getTotal());
////			if (inn.getAllOut())
////				model.addAttribute("allOut",inn.getAllOut());
////			model.addAttribute("runs",inn.getruns());
////			inn.getValues(inn.getBallList(),model);
////		}
		model.addAttribute("Team",innList.get(0).getteam());
		model.addAttribute("wickets", innList.get(0).getWickets());
		model.addAttribute("totalscore" ,innList.get(0).getTotal());
		if (innList.get(0).getAllOut())
			model.addAttribute("allOut",innList.get(0).getAllOut());
		model.addAttribute("runs",innList.get(0).getruns());
		//innList.get(0).getValues(innList.get(0).getBallList(),model);
		model.addAttribute("balls1",innList.get(0).getBallList());
		
		
		model.addAttribute("Team1",innList.get(1).getteam());
		model.addAttribute("wickets1", innList.get(1).getWickets());
		model.addAttribute("totalscore1" ,innList.get(1).getTotal());
		if (innList.get(1).getAllOut())
			model.addAttribute("allOut1",innList.get(1).getAllOut());
		model.addAttribute("runs1",innList.get(1).getruns());
		//innList.get(1).getValues(innList.get(1).getBallList(),model);
		model.addAttribute("balls2",innList.get(1).getBallList());

		
		if(innList.get(0).getTotal()>innList.get(1).getTotal())
		{
		model.addAttribute("won", innList.get(0).getteam());
		model.addAttribute("diff_runs",innList.get(0).getTotal()-innList.get(1).getTotal());
		model.addAttribute("diff_wickets", 10-innList.get(0).getWickets());
		}
		else
		{
			model.addAttribute("won", innList.get(1).getteam());
			model.addAttribute("diff_runs",innList.get(1).getTotal()-innList.get(0).getTotal());
			model.addAttribute("diff_wickets", 10-innList.get(1).getWickets());
		}
	}
}
