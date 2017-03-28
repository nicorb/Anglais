package fr.database;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

import fr.utils.Score;

public class SQLiteJDBC {

	// Accéder à la BD
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

	public static int addQuestion(String question,int answer,String clue1,String clue2,String clue3,String prop1,String prop2,String prop3,String prop4,String context){
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
			String sql ="INSERT INTO QUESTIONS (QUESTION,ANSWER,CLUE1,CLUE2,CLUE3,PROPOSITION1,PROPOSITION2,PROPOSITION3,PROPOSITION4,CONTEXT) VALUES ('"+question+"',"+answer+",'"+clue1+"','"+clue2+"','"+clue3+"','"+prop1+"','"+prop2+"','"+prop3+"','"+prop4+"','"+context+"')";
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM QUESTIONS WHERE ID="+ID);
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
				a.setName(rs.getString(2));
				a.setScore(rs.getInt(3));
				result.add(a);
				if (result.size()==5)
					return result;
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

}