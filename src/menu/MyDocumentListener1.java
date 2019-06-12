package menu;

/*
 * 随时获取文本框中的文字，因只有引用型数据才能被改变，
 * 所以存在一个只开了一个空间的字符串数组中。
 */

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class MyDocumentListener1 implements DocumentListener{
	String[] condition;
	
	public MyDocumentListener1(String[] str) {
		condition = str;
	}
	
	public void removeUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			condition[0] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public void insertUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			condition[0] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		Document doc = e.getDocument();
		try {
			condition[0] = doc.getText(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}
}

