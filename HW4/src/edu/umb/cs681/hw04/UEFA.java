package edu.umb.cs681.hw04;

import java.util.ArrayList;

public class UEFA {

		private String teamName;
		private int matchesPlayed;
		private int matchesWon;
		private int points;
		
		public UEFA(String teamName, int matchesPlayed, int matchesWon, int points) {
			this.teamName = teamName;
			this.matchesPlayed = matchesPlayed;
			this.matchesWon = matchesWon;
			this.points = points;
		}

		public String getTeamName() {
			return teamName;
		}

		public int getMatchesPlayed() {
			return matchesPlayed;
		}

		public int getWon() {
			return matchesWon;
		}

		public int getPoints() {
			return points;
		}

		public static void main(String args[]) {
			ArrayList<UEFA> uefaTable=new ArrayList<>();
			
			UEFA bayern = new UEFA("Bayern",6,5,16);
			UEFA juventus = new UEFA("Juventus",6,5,15);
			UEFA realMadrid = new UEFA("RealMadrid",6,2,9);
			UEFA milan = new UEFA("Milan",6,1,6);
			
			uefaTable.add(bayern);
			uefaTable.add(juventus);
			uefaTable.add(realMadrid);
			uefaTable.add(milan);
			
			//Maximum matches played by a team
			Integer maxMatchesPlayed = uefaTable.stream().map((UEFA epl)-> epl.getMatchesPlayed()).reduce(0, (result, matchesPlayed)->{
				if(result==0) 
					return matchesPlayed;
				else if(matchesPlayed > result) 
					return matchesPlayed; 
				return result;});

	
			Integer maxWon = uefaTable.stream().map((UEFA uefa) -> uefa.getWon()).reduce(0,(result, won)->{
				if(result==0)
					return won;
				else if(won > result)
					return won;
				return result;});

			
			//Maximum points
			Integer maxPoints = uefaTable.stream().map((UEFA epl) -> epl.getPoints()).reduce(0,(result, points)->{
				if(result==0)
					return points;
				else if(points > result)
					return points;
				return result;});
			
			
			System.out.println("Maximum matches played by a team: " + maxMatchesPlayed);
			System.out.println("Maximum matches won by a team: " + maxWon);
			System.out.println("Maximum points of a team: " + maxPoints);
			
		}
	}
