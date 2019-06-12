package menu;

/*
 * 随时获取文本框中输入的内容，并存到一个字符串数组中
 */

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MyDocumentListener implements DocumentListener{
	private int i;
	String[] str;
	
	public MyDocumentListener(int i, String[] s) {
		this.i = i;
		str = s;
	}
	
	public void changedUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			str[i] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void removeUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			str[i] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void insertUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			str[i] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

