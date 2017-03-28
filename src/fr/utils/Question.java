package fr.utils;

public class Question {
	private String question,clue1,clue2,clue3,ans1,ans2,ans3,ans4,context;
	private int numRealAns,id;
	
	public Question(){
	}
	
	public int getnumRealAns(){
		return numRealAns;
	}
	
	public int getID(){
		return id;
	}
	
	public String[] getClues(){
		String [] result=new String[3];
		result[1]=clue1;
		result[2]=clue2;
		result[3]=clue3;
		return result;
	}
	
	public String[] getAns(){
		String [] result=new String[4];
		result[1]=ans1;
		result[2]=ans2;
		result[3]=ans3;
		result[4]=ans4;
		return result;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getContext(){
		return context;
	}
	
	public void setClues(String clue1,String clue2,String clue3){
		this.clue1=clue1;
		this.clue2=clue2;
		this.clue3=clue3;
	}
	
	public void setAns(String ans1,String ans2,String ans3,String ans4){
		this.ans1=ans1;
		this.ans2=ans2;
		this.ans3=ans3;
		this.ans4=ans4;
	}
	
	public void setQuestion(String s){
		question=s;
	}
	
	public void setContext(String s){
		context=s;
	}
	
	public void setNumRealAns(int i){
		numRealAns=i;
	}
	
	public void setID(int i){
		id=i;
	}
}
