import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

/* Scanning ID doc*/
public class Check3 extends JFrame {
	private JPanel Check3Panel;
	private Timer time;
	private JLabel timeLabel;
	private JLabel NoteLabel;

	public Check3() {
		VerCode vercode = new VerCode();
		setTitle("Check With scanning ID document");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);// Prohibition maximization
		setLocationRelativeTo(null);// mediate

		/* main panel */
		Check3Panel = new JPanel();
		Check3Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(Check3Panel);
		Check3Panel.setLayout(null);
		Check3Panel.add(getTimelabel());

		/* background image */
		ImageIcon backimg;
		backimg = new ImageIcon("image2.jpeg");

		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		Check3Panel.add(TitleLabel);

		/* Create a notice */
		NoteLabel = new JLabel();
		NoteLabel.setBackground(new Color(239, 230, 248));
		NoteLabel.setBounds(0, 100, 1060, 30);
		NoteLabel.setOpaque(false);
		NoteLabel.setVisible(true);
		Check3Panel.add(NoteLabel);
		MyComponent mc = new MyComponent();
		mc.setBounds(200, 0, 650, 30);
		mc.setArrText(new String[] { "Smart Flight Check is in operation. Welcome to use it!",
				"Flight BW1314 will be delayed due to bad weather. Please understand." });
		NoteLabel.add(mc);

		/* Create an access label head */
		JLabel AssLabelHead = new JLabel();
		AssLabelHead.setBackground(new Color(113, 127, 159));
		AssLabelHead.setBounds(150, 175, 760, 40);
		AssLabelHead.setOpaque(true);
		AssLabelHead.setVisible(true);
		Check3Panel.add(AssLabelHead);

		/* Create a QM logo */
		JLabel QMgui = new JLabel(new ImageIcon("QMlogo.jpg"));
		QMgui.setBounds(25, 5, 30, 30);
		QMgui.setOpaque(false);
		AssLabelHead.add(QMgui);

		/* Create a Airline logo */
		JLabel logoLabel = new JLabel("QM Airline");
		logoLabel.setFont(new Font("华文彩云", Font.BOLD, 16));
		logoLabel.setForeground(Color.white);
		logoLabel.setBounds(63, 5, 300, 30);
		logoLabel.setOpaque(false);
		AssLabelHead.add(logoLabel);

		/* Create a passenger label */
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.white);
		AssLabel.setBounds(150, 200, 760, 400);
		AssLabel.setOpaque(true);
		AssLabel.setVisible(true);
		Check3Panel.add(AssLabel);

		/* Create a passenger logo */
		JLabel PassengerLogo = new JLabel(new ImageIcon("passengerLogo.jpg"));
		PassengerLogo.setBounds(430, 1, 180, 180);
		PassengerLogo.setOpaque(false);
		AssLabel.add(PassengerLogo);

		/* Create a passenger picture */
		JLabel Passengerui = new JLabel(new ImageIcon("passengerui.jpg"));
		Passengerui.setBounds(0, 2, 300, 400);
		Passengerui.setOpaque(false);
		AssLabel.add(Passengerui);

		/* Create a telephone num label */
		final JFormattedTextField teleNum = new JFormattedTextField();
		teleNum.setForeground(Color.LIGHT_GRAY);
		teleNum.setToolTipText("Please input your telephone num.");
		teleNum.setBorder(BorderFactory.createTitledBorder("Tele Num"));
		teleNum.setText("Please input your telephone num.");
		teleNum.setBounds(410, 170, 240, 40);
		AssLabel.add(teleNum);

		/* Add a text box event */
		teleNum.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (teleNum.getText().equals("Please input your telephone num."))
					teleNum.setText(null);
				teleNum.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});

		/* Create a verification code label */
		final JFormattedTextField verCode = new JFormattedTextField();
		verCode.setForeground(Color.LIGHT_GRAY);
		verCode.setToolTipText("Please input verification code.");
		verCode.setBorder(BorderFactory.createTitledBorder("VerCode"));
		verCode.setText("VerCode");
		verCode.setBounds(410, 240, 80, 40);
		AssLabel.add(verCode);

		/* Add a text box event */
		verCode.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (verCode.getText().equals("VerCode"))
					verCode.setText(null);
				verCode.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});

		/* Create a image of VerCode */
		vercode.setBounds(520, 240, 80, 40);
		AssLabel.add(vercode);

		/* create a button */
		JButton jb = new JButton("Search");
		jb.setBounds(585, 330, 80, 20);
		AssLabel.add(jb);
		/* Legitimacy test */
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(vercode.getCode());
				if (teleNum.getText() == null || verCode.getText() == null) {
					JOptionPane.showMessageDialog(null,
							"<html><body>The Content Cannot Be Empty!" + "<br>"
									+ "Please enter the correct telephone num and verification code!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (teleNum.getText().length() != 11) {
						JOptionPane.showMessageDialog(null,
								"<html><body>Figure Error!" + "<br>" + "Please enter the correct booking num!</html>",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					} else if (!vercode.getCode().equals(verCode.getText())) {
						JOptionPane.showMessageDialog(null,
								"<html><body>Verification Code Error!" + "<br>"
										+ "Please enter the correct verification code!</html>",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						for (int i = 0; i < teleNum.getText().length(); i++) {
							if (teleNum.getText().charAt(i) < '0' || teleNum.getText().charAt(i) > '9') {
								JOptionPane.showMessageDialog(null,
										"<html><body>Formal Error!" + "<br>"
												+ "Please enter the correct booking num!</html>",
										"Alert", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						/* Get Info from a file */
						try {
							File f = new File("InfoList.txt");
							FileReader fre = new FileReader(f);
							BufferedReader bre = new BufferedReader(fre);
							String str = "";
							while ((str = bre.readLine()) != null) {
								String telenum = str.substring(46, 57);
								if (telenum.equals(teleNum.getText())) {
									bre.close();
									fre.close();

									dispose();
									Summary sum = new Summary(telenum);
									sum.setVisible(true);
									return;
								}
							}
							JOptionPane.showMessageDialog(null,
									"<html><body>Not Found!" + "<br>" + "Please enter the correct booking num!</html>",
									"Alert", JOptionPane.ERROR_MESSAGE);
						} catch (Exception err) {
							err.printStackTrace();
						}
					}
				}
			}
		});

		/* create a back button */
		JButton jb1 = new JButton("Back");
		jb1.setBounds(410, 330, 80, 20);
		AssLabel.add(jb1);
		/* Return to previous page */
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Search search = new Search();
				search.setVisible(true);
			}
		});

		JLabel BackgroundLabel = new JLabel(backimg);
		// BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		Check3Panel.add(BackgroundLabel);
	}

		/* Timer */
		private JLabel getTimelabel() {
			if (timeLabel == null) {
				timeLabel = new JLabel("");
				timeLabel.setBounds(20, 10, 300, 20);
				timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
				timeLabel.setForeground(new Color(200, 162, 120));
				time = new Timer(1000, new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						timeLabel.setText(
								new SimpleDateFormat("yyyy/MM/dd EEEE HH:mm:ss", Locale.ENGLISH).format(new Date()));
					}
				});
				time.start();
			}
			return timeLabel;
		}

}