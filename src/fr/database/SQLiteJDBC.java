package fr.database;
import fr.utils.Question;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import fr.utils.Score;

public class SQLiteJDBC {

	// Acc�der � la BD
	public static void openDataBase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}

	public static int addQuestion(String question,int answer,String clue1,String clue2,String clue3,String prop1,String prop2,String prop3,String prop4,String context,String type){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			try
			{String sql1="SELECT * FROM QUESTION WHERE QUESTION='"+question+"'";
			ResultSet rs = stmt.executeQuery(sql1);
			return 2;
			}catch(Exception e){}
			stmt = c.createStatement();
			String sql ="INSERT INTO QUESTIONS (QUESTION,ANSWER,CLUE1,CLUE2,CLUE3,PROPOSITION1,PROPOSITION2,PROPOSITION3,PROPOSITION4,CONTEXT,TYPE) VALUES ('"+question+"',"+answer+",'"+clue1+"','"+clue2+"','"+clue3+"','"+prop1+"','"+prop2+"','"+prop3+"','"+prop4+"','"+context+"','"+type+"')";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			return 0;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return 1;
		}
	}


	// CREATE TABLE when datas doesn't exist
	public static void initializeTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt = c.createStatement();


			/*String sql ="CREATE TABLE QUESTIONS " + "(ID INT PRIMARY KEY     NOT NULL, QUESTION TEXT NOT NULL, ANSWER INT NOT NULL, CLUE1 TEXT NOT NULL,CLUE2 TEXT NOT NULL,CLUE3 TEXT NOT NULL,CLUE4 TEXT NOT NULL,CLUE5 TEXT NOT NULL,CLUE6 TEXT NOT NULL,CLUE7 TEXT NOT NULL,PROPOSITION1 TEXT NOT NULL,PROPOSITION2 TEXT NOT NULL,PROPOSITION3 TEXT NOT NULL,PROPOSITION4 TEXT NOT NULL);";
			stmt.executeUpdate(sql);*/
			String sql="INSERT INTO QUESTIONS (QUESTION,ANSWER,CLUE1,CLUE2,CLUE3,PROPOSITION1,PROPOSITION2,PROPOSITION3,PROPOSITION4) VALUES ('Which band rejected Dave Mustain before the first Album?',2,'Master of Puppets','1st band ever to play on every continent','Cliff Burton was their first bass player','Megadeath','Metallica','Led Zepplin','Guns n Roses')";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}


	/*public static void main( String args[]){
		initializeTables();
	}*/

	public static String[] search(int ID){
		Connection c = null;
		Statement stmt = null;

		String[] result = new String[12];
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM QUESTIONS WHERE rowid="+ID);
			for (int i=1;i<12;i++){
				result[i]=rs.getString(i);
			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
		return result;
	}

	public static void addScore(int score,String name){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			stmt.executeUpdate("INSERT INTO SCORE(Name,Score) VALUES ('"+name+"',"+score+")");
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
	}

	public static ArrayList<Score> getScore(){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Score> result=new ArrayList<Score>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM SCORE ORDER BY Score DESC");

			while(rs.next()){
				Score a=new Score();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setScore(rs.getInt(3));
				result.add(a);
				if (result.size()==5){
					stmt.close();
					c.close();
					return result;
				}
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
		return result;
	}

	public static int tailleBDD(){
		Connection c = null;
		Statement stmt = null;
		int a=0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM QUESTIONS");

			while(rs.next()){
				a++;
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
		return a;
	}

	public static void setScores(ArrayList<Score> scores) throws SQLException, ClassNotFoundException{
		Connection c = null;
		Statement stmt = null;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:datas.db");
		stmt=c.createStatement();
		for (int i=0;i<scores.size();i++){
			try{
				stmt.executeUpdate("UPDATE FROM SCORE WHERE ScoreID="+scores.get(i).getId()+"SET Name='"+scores.get(i).getName()+"', Score="+scores.get(i).getScore());
			}catch(Exception e){
				stmt.executeUpdate("INSERT INTO SCORE(Name,Score) VALUES ('"+scores.get(i).getName()+"',"+scores.get(i).getScore()+")");
			}
		}
		stmt.close();
		c.close();
	}
	
	
	public static void addFromCSV(String file) {
		try	{
		   BufferedReader fichier_source = new BufferedReader(new FileReader(file));
		   String chaine;
		   int i = 1;
		 
		   while((chaine = fichier_source.readLine())!= null)
		   {
		         String[] tabChaine = chaine.split(",");
		         //Tu effectues tes traitements avec les donn�es contenues dans le tableau
		         //La premi�re information se trouve � l'indice 0
		         addQuestion(tabChaine[0],Integer.parseInt(tabChaine[1]),tabChaine[2],tabChaine[3],tabChaine[4],tabChaine[5],tabChaine[6],tabChaine[7],tabChaine[8],tabChaine[9],tabChaine[10]);
		         
		   }
		   fichier_source.close();                 
		}
		catch (FileNotFoundException e)
		{
		   System.out.println("Le fichier est introuvable !");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Integer> getQuestionByType(String type){
		Connection c = null;
		Statement stmt = null;
		ArrayList<Integer> q= new ArrayList<Integer>();
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:datas.db");
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT rowid FROM QUESTIONS WHERE TYPE="+"'"+type+"'");
			while(rs.next()){
				q.add(rs.getInt("rowid"));
			}

			rs.close();
			stmt.close();
			c.close();
			
		}catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
			System.out.println("Couldn't load the database");
		}
		return q;
	}

}