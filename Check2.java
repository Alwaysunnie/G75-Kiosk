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

/*Name & ID*/
public class Check2 extends JFrame {
	private JPanel Check2Panel;
	private Timer time;
	private JLabel timeLabel;
	private JLabel NoteLabel;

	public Check2() {
		setTitle("Check With surname and ID number");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);// Prohibition maximization
		setLocationRelativeTo(null);// mediate

		/* main panel */
		Check2Panel = new JPanel();
		Check2Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(Check2Panel);
		Check2Panel.setLayout(null);
		Check2Panel.add(getTimelabel());

		/* background image */
		ImageIcon backimg;
		backimg = new ImageIcon("image2.jpeg");

		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		Check2Panel.add(TitleLabel);

		/* Create a notice */
		NoteLabel = new JLabel();
		NoteLabel.setBackground(new Color(239, 230, 248));
		NoteLabel.setBounds(0, 100, 1060, 30);
		NoteLabel.setOpaque(false);
		NoteLabel.setVisible(true);
		Check2Panel.add(NoteLabel);
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
		Check2Panel.add(AssLabelHead);

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
		Check2Panel.add(AssLabel);

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

		/* Create a name label */
		final JFormattedTextField Name = new JFormattedTextField();
		Name.setForeground(Color.LIGHT_GRAY);
		Name.setToolTipText("Please input your name.");
		Name.setBorder(BorderFactory.createTitledBorder("Name"));
		Name.setText("Please input your name.");
		Name.setBounds(410, 170, 240, 40);
		AssLabel.add(Name);

		/* Add a text box event */
		Name.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (Name.getText().equals("Please input your name."))
					Name.setText(null);
				Name.setForeground(Color.BLACK);
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

		/* Create a id num label */
		final JFormattedTextField idNum = new JFormattedTextField();
		idNum.setForeground(Color.LIGHT_GRAY);
		idNum.setToolTipText("Please input your ID num.");
		idNum.setBorder(BorderFactory.createTitledBorder("ID Num"));
		idNum.setText("Please input your ID num.");
		idNum.setBounds(410, 240, 240, 40);
		AssLabel.add(idNum);
		/* Add a text box event */
		idNum.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (idNum.getText().equals("Please input your ID num."))
					idNum.setText(null);
				idNum.setForeground(Color.BLACK);
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

		/* create a button */
		JButton jb = new JButton("Search");
		jb.setBounds(585, 330, 80, 20);
		AssLabel.add(jb);
		/* Legitimacy test */
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(Name.getText());
				System.out.println(idNum.getText());
				if (Name.getText() == null || idNum.getText() == null) {
					JOptionPane.showMessageDialog(null,
							"<html><body>The Content Cannot Be Empty!" + "<br>"
									+ "Please enter the correct name and id num!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (idNum.getText().length() != 18) {
						JOptionPane.showMessageDialog(null,
								"<html><body>Figure Error!" + "<br>" + "Please enter the correct id num!</html>",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						for (int i = 0; i < Name.getText().length(); i++) {
							if ((Name.getText().charAt(i) < 'A' || Name.getText().charAt(i) > 'Z') &&
									(Name.getText().charAt(i) < 'a' || Name.getText().charAt(i) > 'z') &&
									(Name.getText().charAt(i) != ' ')) {
								JOptionPane.showMessageDialog(null,
										"<html><body>Formal Error!" + "<br>" + "Please enter the correct name!</html>",
										"Alert", JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						for (int i = 0; i < idNum.getText().length(); i++) {
							if (idNum.getText().charAt(i) < '0' || idNum.getText().charAt(i) > '9') {
								JOptionPane.showMessageDialog(null,
										"<html><body>Formal Error!" + "<br>"
												+ "Please enter the correct id num!</html>",
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
								String name = str.substring(147, str.length());
								String idnum = str.substring(27, 45);
								if (name.equals(Name.getText()) && idnum.equals(idNum.getText())) {
									bre.close();
									fre.close();

									dispose();
									Summary sum = new Summary(name, idnum);
									sum.setVisible(true);
									return;
								}
							}
							JOptionPane.showMessageDialog(null,
									"<html><body>Not Found!" + "<br>"
											+ "Please enter the correct name and id num!</html>",
									"Alert", JOptionPane.ERROR_MESSAGE);
						} catch (Exception err) {
							err.printStackTrace();
						}
					}
				}
			}
		});

		/* create a button */
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
		Check2Panel.add(BackgroundLabel);
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