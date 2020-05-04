package br.mia.form;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;

import br.mia.Controller.Controller;
import br.mia.form.data.DataPrinter;
import br.mia.form.data.SystemProver;
import br.mia.model.WellFormedFormula;
import br.mia.parser.ParseException;

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
public class SwingApplication extends javax.swing.JFrame implements DataPrinter {

	{
		// Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JSeparator jSeparator1;

	private JMenuItem jMenuItemProve;

	private JScrollPane jScrollPaneBase;

	private JScrollPane jScrollPaneConsole;

	private JPanel jPanel1;

	private JPanel jPanelProposition;

	private JPanel jPanelBase;

	private JPanel jPanelBaseTheorem;

	private JPanel jPanelConsole;

	private JSplitPane jSplitPane;

	private JMenu jMenuPropositon;

	private JMenuItem jMenuItemExit;

	private JSeparator jSeparator2;

	private JScrollPane jScrollPaneTheorem;

	private JSplitPane jSplitPaneTop;

	private JCheckBoxMenuItem jCheckBoxMenuItemDetailed;

	private JTextArea jTextAreaConsole;

	private JTextArea jTextAreaProposition;

	private JTextArea jTextAreaBase;

	private JMenuItem jMenuItemSave;

	private JMenuItem jMenuItemLoad;

	private JMenuItem jMenuItemOpen;

	private JMenu jMenuBase;

	private JMenuBar jMenuBar1;

	private Controller controller = new Controller();

	private Rectangle stringRect;

	public SwingApplication() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				BoxLayout thisLayout = new BoxLayout(getContentPane(),
						javax.swing.BoxLayout.X_AXIS);
				getContentPane().setLayout(thisLayout);
				this.setFocusTraversalKeysEnabled(false);
				this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				this.setTitle("Theorem Prover");
				this.setExtendedState(Frame.MAXIMIZED_BOTH);
				{
					jSplitPane = new JSplitPane();
					getContentPane().add(jSplitPane);
					jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
					jSplitPane
							.setPreferredSize(new java.awt.Dimension(400, 410));
					{
						jPanelConsole = new JPanel();
						BoxLayout jPanelBaseTeoremLayout = new BoxLayout(
								jPanelConsole, javax.swing.BoxLayout.X_AXIS);
						jPanelConsole.setLayout(jPanelBaseTeoremLayout);
						jSplitPane.add(jPanelConsole, JSplitPane.BOTTOM);
						jPanelConsole.setPreferredSize(new java.awt.Dimension(
								610, 90));
						{
							jPanel1 = new JPanel();
							BoxLayout jPanel1Layout = new BoxLayout(jPanel1,
									javax.swing.BoxLayout.X_AXIS);
							jPanel1.setLayout(jPanel1Layout);
							jPanelConsole.add(jPanel1);
							jPanel1.setBorder(BorderFactory
									.createTitledBorder("Console"));
							jPanel1.setPreferredSize(new java.awt.Dimension(
									610, 68));
							{
								jScrollPaneConsole = new JScrollPane();
								jPanel1.add(jScrollPaneConsole);
								jScrollPaneConsole
										.setPreferredSize(new java.awt.Dimension(
												171, 96));
								{
									jTextAreaConsole = new JTextArea();
									jScrollPaneConsole
											.setViewportView(jTextAreaConsole);
									jTextAreaConsole.setFont(new java.awt.Font(
											"Courier New", 0, 16));
								}
							}
						}
					}
					{
						jPanelBaseTheorem = new JPanel();
						BoxLayout jPanelConsoleLayout = new BoxLayout(
								jPanelBaseTheorem, javax.swing.BoxLayout.X_AXIS);
						jSplitPane.add(jPanelBaseTheorem, JSplitPane.LEFT);
						jPanelBaseTheorem
								.setPreferredSize(new java.awt.Dimension(610,
										288));
						jPanelBaseTheorem.setLayout(jPanelConsoleLayout);
						{
							jSplitPaneTop = new JSplitPane();
							jPanelBaseTheorem.add(jSplitPaneTop);
							{
								jPanelProposition = new JPanel();
								jSplitPaneTop.add(jPanelProposition,
										JSplitPane.RIGHT);
								BoxLayout jPanelTheoremLayout = new BoxLayout(
										jPanelProposition,
										javax.swing.BoxLayout.X_AXIS);
								jPanelProposition
										.setLayout(jPanelTheoremLayout);
								jPanelProposition.setBorder(BorderFactory
										.createTitledBorder("Proposition"));
								jPanelProposition
										.setPreferredSize(new java.awt.Dimension(
												93, 286));
								{
									jScrollPaneTheorem = new JScrollPane();
									jPanelProposition.add(jScrollPaneTheorem);
									{
										jTextAreaProposition = new JTextArea() {

											private static final long serialVersionUID = 6879759473524394513L;

											public void paint(Graphics g) {
												super.paint(g);
												drawStringRectangle(this);
											}

										};
										jScrollPaneTheorem
												.setViewportView(jTextAreaProposition);
										jTextAreaProposition
												.setFont(new java.awt.Font(
														"Courier New", 0, 16));
										jTextAreaProposition
												.addCaretListener(new CaretListener() {
													public void caretUpdate(
															CaretEvent evt) {
														jTextAreaTheoremCaretUpdate(evt);
													}
												});
									}
								}
							}
						}
						{
							jPanelBase = new JPanel();
							jSplitPaneTop.add(jPanelBase, JSplitPane.LEFT);
							BoxLayout jPanelBaseLayout = new BoxLayout(
									jPanelBase, javax.swing.BoxLayout.X_AXIS);
							jPanelBase.setLayout(jPanelBaseLayout);
							jPanelBase.setBorder(BorderFactory
									.createTitledBorder("Base"));
							jPanelBase.setPreferredSize(new java.awt.Dimension(
									408, 286));
							{
								jScrollPaneBase = new JScrollPane();
								jPanelBase.add(jScrollPaneBase);
								jScrollPaneBase
										.setPreferredSize(new java.awt.Dimension(
												262, 58));
								{
									jTextAreaBase = new JTextArea() {

										private static final long serialVersionUID = 9164098806621414192L;

										public void paint(Graphics g) {
											super.paint(g);
											drawStringRectangle(this);
										}

									};
									jScrollPaneBase
											.setViewportView(jTextAreaBase);
									jTextAreaBase.setFont(new java.awt.Font(
											"Courier New", 0, 16));
									jTextAreaBase
											.addCaretListener(new CaretListener() {
												public void caretUpdate(
														CaretEvent evt) {
													jTextAreaBaseCaretUpdate(evt);
												}
											});
								}
							}
						}
					}
				}
			}
			this.setSize(621, 462);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenuBase = new JMenu();
					jMenuBar1.add(jMenuBase);
					jMenuBase.setText("Base");
					{
						jMenuItemOpen = new JMenuItem();
						jMenuBase.add(jMenuItemOpen);
						jMenuItemOpen.setText("Open");
						jMenuItemOpen.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItemOpenActionPerformed(evt);
							}
						});
					}
					{
						jMenuItemLoad = new JMenuItem();
						jMenuBase.add(jMenuItemLoad);
						jMenuItemLoad.setText("Load");
						jMenuItemLoad.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItemLoadActionPerformed(evt);
							}
						});
					}
					{
						jMenuItemSave = new JMenuItem();
						jMenuBase.add(jMenuItemSave);
						jMenuItemSave.setText("Save");
						jMenuItemSave.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItemSaveActionPerformed(evt);
							}
						});
					}
					{
						jSeparator2 = new JSeparator();
						jMenuBase.add(jSeparator2);
					}
					{
						jMenuItemExit = new JMenuItem();
						jMenuBase.add(jMenuItemExit);
						jMenuItemExit.setText("Exit");
					}
				}
				{
					jMenuPropositon = new JMenu();
					jMenuBar1.add(jMenuPropositon);
					jMenuPropositon.setText("Proposition");
					{
						jMenuItemProve = new JMenuItem();
						jMenuPropositon.add(jMenuItemProve);
						jMenuItemProve.setText("Prove");
						jMenuItemProve.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								jMenuItemProveActionPerformed(evt);
							}
						});
					}
					{
						jSeparator1 = new JSeparator();
						jMenuPropositon.add(jSeparator1);
					}
					{
						jCheckBoxMenuItemDetailed = new JCheckBoxMenuItem();
						jMenuPropositon.add(jCheckBoxMenuItemDetailed);
						jCheckBoxMenuItemDetailed.setText("Detailed");
						jCheckBoxMenuItemDetailed.setSelected(true);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void drawStringRectangle(JTextArea jTextArea) {
		if (stringRect == null)
			return;
		Graphics graphics = jTextArea.getGraphics();
		graphics.drawRect(stringRect.x, stringRect.y, stringRect.width,
				stringRect.height);
	}

	private void jTextAreaCaretUpdate(CaretEvent evt) {
		stringRect = null;
		JTextArea jTextArea = (JTextArea) evt.getSource();
		int dot = evt.getDot();
		if (dot == 0) {
			jTextArea.repaint();
			return;
		}
		String text = jTextArea.getText();
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
					stringRect = jTextArea.modelToView(newDot - 1);
					stringRect.width = jTextArea.modelToView(newDot).x
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
					stringRect = jTextArea.modelToView(newDot);
					stringRect.width = jTextArea.modelToView(newDot + 1).x
							- stringRect.x;
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
			break;
		}
		jTextArea.repaint();
	}

	private void jTextAreaBaseCaretUpdate(CaretEvent evt) {
		jTextAreaCaretUpdate(evt);
	}

	private void jTextAreaTheoremCaretUpdate(CaretEvent evt) {
		jTextAreaCaretUpdate(evt);
	}

	private void jMenuItemLoadActionPerformed(ActionEvent evt) {

		new Thread() {
			public void run() {
				jTextAreaConsole.setText(new String());
				SystemProver.out.println("Parsing the current base...");
				try {
					List<WellFormedFormula> wffList = controller
							.parse(jTextAreaBase.getText());
					SystemProver.out
							.println("The base was parsed successfully!");
					SystemProver.out.println("Loading knoledge base...");
					controller.createKnowledgeBase(wffList);
					SystemProver.out
							.println("Knoledge base loaded successfully!");
				} catch (ParseException e) {
					SystemProver.out.println("Parsing failed!");
					SystemProver.out.println(e.getMessage());
				}
			}
		}.start();
	}

	public void print(String text) {
		jTextAreaConsole.append(text);
	}

	public void println(String text) {
		print(text);
		println();
	}

	public void println() {
		jTextAreaConsole.append("\n");
	}

	private void jMenuItemOpenActionPerformed(ActionEvent evt) {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".base");
			}

			public String getDescription() {
				return "Base file";
			}
		});

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(this);

		if (result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();
		if (file == null || file.getName().equals(""))
			JOptionPane.showMessageDialog(this, "Inavalid file name",
					"Inavalid file name", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				byte[] bytes = new byte[fileInputStream.available()];
				fileInputStream.read(bytes);

				this.jTextAreaBase.setText(new String(bytes));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error opening file",
						"Error", JOptionPane.ERROR_MESSAGE);
				SystemProver.out.println(e.getMessage());
			}
		}
	}

	private void jMenuItemSaveActionPerformed(ActionEvent evt) {

		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".base");
			}

			public String getDescription() {
				return "Base file";
			}
		});

		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result = fileChooser.showSaveDialog(this);

		if (result == JFileChooser.CANCEL_OPTION)
			return;

		File file = fileChooser.getSelectedFile();
		if (file == null || file.getName().equals(""))
			JOptionPane.showMessageDialog(this, "Inavalid File Name",
					"Inavalid File Name", JOptionPane.ERROR_MESSAGE);
		else {
			try {
				if (!file.getName().endsWith(".base"))
					file = new File(file.getAbsolutePath() + ".base");
				OutputStream fileOutputStream = new FileOutputStream(file);
				fileOutputStream.write(jTextAreaBase.getText().getBytes());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Error Saving File",
						"Error", JOptionPane.ERROR_MESSAGE);
				SystemProver.out.println(e.getMessage());
			}
		}
	}

	private void jMenuItemProveActionPerformed(ActionEvent evt) {
		try {
			List<WellFormedFormula> wffList = this.controller
					.parse(this.jTextAreaProposition.getText());

			for (WellFormedFormula wff : wffList) {
				this.controller.setCurrentProposition(wff);
				boolean result = this.controller.proveCurrentProposition();
				SystemProver.out.println(result ? "Proved" : "Can not be proved!");
			}

		} catch (Exception e) {
			SystemProver.out.println("Parsing failed!");
			SystemProver.out.println(e.getMessage());
		}
	}
}