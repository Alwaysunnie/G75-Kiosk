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

/*Book_No*/
public class Check1 extends JFrame {
	private JPanel Check1Panel;
	private Timer time;
	private JLabel timeLabel;
	private JLabel NoteLabel;

	public Check1() {
		setTitle("Check With Booking Number");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);// Prohibition maximization
		setLocationRelativeTo(null);// mediate

		/* main panel */
		Check1Panel = new JPanel();
		Check1Panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(Check1Panel);
		Check1Panel.setLayout(null);
		Check1Panel.add(getTimelabel());

		/* background image */
		ImageIcon backimg;
		backimg = new ImageIcon("image2.jpeg");

		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		Check1Panel.add(TitleLabel);

		/* Create a notice */
		NoteLabel = new JLabel();
		NoteLabel.setBackground(new Color(239, 230, 248));
		NoteLabel.setBounds(0, 100, 1060, 30);
		NoteLabel.setOpaque(false);
		NoteLabel.setVisible(true);
		Check1Panel.add(NoteLabel);
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
		Check1Panel.add(AssLabelHead);

		/* Create a QM logo */
		JLabel QMgui = new JLabel(new ImageIcon("QMlogo.jpg"));
		QMgui.setBounds(25, 5, 30, 30);
		QMgui.setOpaque(false);
		AssLabelHead.add(QMgui);

		/* Create a Airline logo */
		JLabel logoLabel = new JLabel("Queen Mary Airline");
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
		Check1Panel.add(AssLabel);

		/* Create a passenger picture */
		JLabel Passengerui = new JLabel(new ImageIcon("passengerui.jpg"));
		Passengerui.setBounds(0, 2, 300, 400);
		Passengerui.setOpaque(false);
		AssLabel.add(Passengerui);

		/* Create a passenger logo */
		JLabel PassengerLogo = new JLabel(new ImageIcon("passengerLogo.jpg"));
		PassengerLogo.setBounds(430, 1, 180, 180);
		PassengerLogo.setOpaque(false);
		AssLabel.add(PassengerLogo);

		/* Create a booking num label */
		final JFormattedTextField bookingNum = new JFormattedTextField();
		bookingNum.setForeground(Color.LIGHT_GRAY);
		bookingNum.setToolTipText("Please input your booking num.");
		bookingNum.setBorder(BorderFactory.createTitledBorder("Booking Num"));
		bookingNum.setText("Please input your booking num.");
		bookingNum.setBounds(410, 190, 260, 40);
		AssLabel.add(bookingNum);

		/* Add a text box event */
		bookingNum.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
				if (bookingNum.getText().equals("Please input your booking num."))
					bookingNum.setText(null);
				bookingNum.setForeground(Color.BLACK);
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

		/* create a search button */
		ImageIcon icon = new ImageIcon("search.png");
		JButton jb = new JButton(icon);
		jb.setBounds(585, 330, 80, 26);
		jb.setBorder(BorderFactory.createRaisedBevelBorder());
		jb.setBorderPainted(false); 
		jb.setOpaque(false);
		AssLabel.add(jb);

		/* Legitimacy test */
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bookingNum.getText() == null) {
					JOptionPane.showMessageDialog(null,
							"<html><body>Figure Error!" + "<br>" + "Please enter the correct booking num!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					if (bookingNum.getText().length() != 13) {
						JOptionPane.showMessageDialog(null,
								"<html><body>Figure Error!" + "<br>" + "Please enter the correct booking num!</html>",
								"Alert", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						for (int i = 0; i < bookingNum.getText().length(); i++) {
							if (bookingNum.getText().charAt(i) < '0' || bookingNum.getText().charAt(i) > '9') {
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
								String bookNum = str.substring(0, 13);
								if (bookNum.equals(bookingNum.getText())) {
									bre.close();
									fre.close();

									dispose();
									Summary sum = new Summary(bookNum);
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

		
		/**************** create a back button *****************/
        ImageIcon icon1 = new ImageIcon("back.png");
        JButton jb1 = new JButton(icon1);
        jb1.setBounds(410, 330, 80, 26);
        //jb1.setBorder(BorderFactory.createRaisedBevelBorder());
		jb1.setBorderPainted(false); 
		jb1.setOpaque(false);
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
		Check1Panel.add(BackgroundLabel);
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