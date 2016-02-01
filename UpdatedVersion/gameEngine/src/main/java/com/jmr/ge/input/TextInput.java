package com.jmr.ge.input;

import javax.swing.JOptionPane;

import com.jmr.ge.Game;

public class TextInput {

	public static String getTextInput(String title, String message) {
		String val = "";
		if (Game.androidAlertBox != null) {
			val = Game.androidAlertBox.showInputBox(title, message);
		} else {
			val = JOptionPane.showInputDialog(null, message);
		}
		System.out.println(val);
		return val;
	}
	
	public static String getTextInput(String message) {
		return getTextInput("", message);
	}
	
	public static void showMessage(String title, String message) {
		if (Game.androidAlertBox != null) {
			Game.androidAlertBox.showMessageBox(title, message);
		} else {
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public static void showMessage(String message) {
		showMessage("", message);
	}
	
}
