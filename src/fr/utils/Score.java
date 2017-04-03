package fr.utils;

public class Score {
	private int score,id;
	private String name;
	
	public Score(){
	}
	
	public void setScore(int a){
		score=a;
	}
	
	public void setName(String a){
		name=a;
	}
	
	
	public int getScore(){
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
