package br.mia.form.teste;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class NewSwingApp extends javax.swing.JFrame {

	private JTextArea jTextArea1;

	private Rectangle stringRect = null;

	private int counter = 0;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		NewSwingApp inst = new NewSwingApp();
		inst.setVisible(true);
	}

	public NewSwingApp() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				{
					jTextArea1 = new JTextArea() {

						private static final long serialVersionUID = -8249448554025307460L;

						public void paint(Graphics g) {
							super.paint(g);
							drawStringRectangle();
						}

					};
					getContentPane().add(jTextArea1);
					jTextArea1.setBounds(14, 14, 357, 168);
					jTextArea1.setText("(   (   (   (   (   )   )   )   )   )");
					jTextArea1.addCaretListener(new CaretListener() {
						public void caretUpdate(CaretEvent evt) {
							jTextArea1CaretUpdate(evt);
						}
					});
				}
			}
			this.setSize(400, 230);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawStringRectangle() {
		if (stringRect == null)
			return;
		Graphics graphics = jTextArea1.getGraphics();
		graphics.drawRect(stringRect.x, stringRect.y, stringRect.width,
				stringRect.height);
	}

	private void jTextArea1CaretUpdate(CaretEvent evt) {
		stringRect = null;
		int dot = evt.getDot();
		if (dot == 0)
			return;
		JTextArea textArea = (JTextArea) evt.getSource();
		String text = textArea.getText();
		char c = 0;
		c = text.charAt(dot - 1);
		int counter = 1;
		int index = dot;
		int lenght = text.length();
		switch (c) {
		case '(':
			while (counter > 0 && c != ',' && index < lenght) {
				c = text.charAt(index++);
				switch (c) {
				case '(':
					counter++;
					break;
				case ')':
					counter--;
					break;
				}
			}
			if (index <= lenght && c == ')') {
				int newDot = index;
				try {
					stringRect = textArea.modelToView(newDot - 1);
					stringRect.width = textArea.modelToView(newDot).x
							- stringRect.x;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			break;
		case ')':
			index -= 2;
			while (counter > 0 && c != ',' && index >= 0) {
				c = text.charAt(index--);
				switch (c) {
				case '(':
					counter--;
					break;
				case ')':
					counter++;
					break;
				}
			}
			if (index >= -1 && c == '(') {
				int newDot = index + 1;
				try {
					stringRect = textArea.modelToView(newDot);
					stringRect.width = textArea.modelToView(newDot + 1).x
							- stringRect.x;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		textArea.repaint();
	}
}