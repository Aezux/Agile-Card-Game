package base;

import java.util.ArrayList;

public class Team {
	private ArrayList<String> team1 = new ArrayList<String>();
    private ArrayList<String> team2 = new ArrayList<String>();
    
    public void addMember(String name, String team){
    	if(team.equalsIgnoreCase("team1")){
    		team1.add(name);
    	}
    	else
    		team2.add(name);
    }

	public int getTeam1Size(){
		return team1.size();
	}

	public int getTeam2Size(){
		return team2.size();
	}
	
	public String getTeam1(){
		for(int i=0; i < team1.size(); i++){
			return team1.get(i);
		}
		
		return "empty";
	}
	
	public String getTeam2(){
		for(int i=0; i < team2.size(); i++){
			return team2.get(i);
		}
		
		return "empty";
	}
    
    
}


