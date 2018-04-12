package fr.menus;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import fr.database.SQLiteJDBC;

public class AddQuestionMenu extends Menu{

	private static String file;
	public static int ID=150;
	@Override
	void execOption() {
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		super.enter(arg0, arg1);
		try {
			background=new Image("sprites/badAnswer.png");
		} catch (SlickException e) {
			System.out.println("main menu couldn't be loaded");
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException {

		g.drawImage(background, 0, 0);



	}

	@Override
	public void keyPressed(int key, char c) {
		if(key==Input.KEY_ENTER)
			boiteDial();
		
		
	}
	
	public static void boiteDial() {
		// Exemple num�ro 2
		// Bo�te de s�lection de fichier � partir du r�pertoire courant
		File repertoireCourant = null;
		try {
			// obtention d'un objet File qui d�signe le r�pertoire courant. Le
			// "getCanonicalFile" n'est pas absolument n�cessaire mais permet
			// d'�viter les /Truc/./Chose/ ...
			repertoireCourant = new File(".").getCanonicalFile();
			System.out.println("R�pertoire courant : " + repertoireCourant);
		} catch(IOException e) {}

		// cr�ation de la bo�te de dialogue dans ce r�pertoire courant
		// (ou dans "home" s'il y a eu une erreur d'entr�e/sortie, auquel
		// cas repertoireCourant vaut null)
		JFileChooser dialogue = new JFileChooser(repertoireCourant);

		// affichage
		dialogue.showOpenDialog(null);

		// r�cup�ration du fichier s�lectionn�
		System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
		file=dialogue.getSelectedFile().getAbsolutePath();
		SQLiteJDBC.addFromCSV(file);

	}
	
	
	



}
