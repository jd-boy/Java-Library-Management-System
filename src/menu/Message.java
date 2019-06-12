package menu;

import javax.swing.JOptionPane;

public class Message {
	
	public static void setInformation(String str, String title) {
		JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void setPlain(String str, String title) {
		JOptionPane.showMessageDialog(null, str, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void setError(String str, String title) {
		JOptionPane.showMessageDialog(null, str, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void setWarning(String str, String title) {
		JOptionPane.showMessageDialog(null, str, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void setQuestion(String str, String title) {
		JOptionPane.showMessageDialog(null, str, title, JOptionPane.QUESTION_MESSAGE);
	}
	
}
