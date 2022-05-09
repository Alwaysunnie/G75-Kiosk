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

public class CrewCheck extends JFrame {
    private JPanel CrewPanel;
    private Timer time;
    private JLabel timeLabel;

    public CrewCheck() {
        setTitle("Crew Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 200, 800, 480);
        setResizable(false);// Prohibition maximization
        setLocationRelativeTo(null);// mediate

        ImageIcon backimg;
        backimg = new ImageIcon("image.jpeg");

        /* main panel */
        CrewPanel = new JPanel();
        CrewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(CrewPanel);
        CrewPanel.setLayout(null);
        // CrewPanel.add(getTimelabel());

        /* Create a access label */
        JLabel AssLabel = new JLabel();
        AssLabel.setBackground(Color.white);
        AssLabel.setBounds(1, 1, 800, 480);
        AssLabel.setOpaque(true);
        CrewPanel.add(AssLabel);

        /* Create a crew picture */
        JLabel crewPic = new JLabel(new ImageIcon("crewPic.jpg"));
        crewPic.setBounds(5, 5, 470, 470);
        crewPic.setOpaque(false);
        AssLabel.add(crewPic);

        /* Create a crew logo */
        JLabel crewLogo = new JLabel(new ImageIcon("crewLogo.jpg"));
        crewLogo.setBounds(540, 40, 180, 180);
        crewLogo.setOpaque(false);
        AssLabel.add(crewLogo);

        /************ Input staff Information ************/
        JFormattedTextField IdText = new JFormattedTextField();
        IdText.setBackground(new Color(226, 221, 244));
        IdText.setForeground(Color.GRAY);
        IdText.setToolTipText("staff no");
        IdText.setText("staff no");
        IdText.setBounds(540, 240, 180, 20);

        JFormattedTextField PassWord = new JFormattedTextField();
        PassWord.setForeground(Color.GRAY);
        PassWord.setBackground(new Color(226, 221, 244));
        PassWord.setToolTipText("PassWord");
        PassWord.setText("PassWord");
        PassWord.setBounds(540, 300, 180, 20);
        AssLabel.add(IdText);
        AssLabel.add(PassWord);

        /************ Check staff Information ***************/
        IdText.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (IdText.getText().equals("staff no"))
                    IdText.setText(null);
                IdText.setForeground(Color.BLACK);
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

        PassWord.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (PassWord.getText().equals("PassWord"))
                    PassWord.setText(null);
                PassWord.setForeground(Color.BLACK);
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
        /**************** create a back button *****************/
        ImageIcon icon1 = new ImageIcon("back.png");
        JButton jb1 = new JButton(icon1);
        jb1.setBounds(510, 350, 80, 26);
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
        /************  create a login button **************/
			ImageIcon icon = new ImageIcon("login.png");
			JButton jb = new JButton(icon);
			jb.setBounds(680, 350, 80, 26);
			jb.setBorder(BorderFactory.createRaisedBevelBorder());
			jb.setBorderPainted(false); 
			jb.setOpaque(false);
            AssLabel.add(jb);

        /**************** Legitimacy test ******************/
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (IdText.getText() == null) {
                    JOptionPane.showMessageDialog(null,
                            "<html><body>Figure Error!" + "<br>" + "Please enter the correct ID!</html>", "Alert",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    for (int i = 0; i < IdText.getText().length(); i++) {
                        if (IdText.getText().charAt(i) < '0' || IdText.getText().charAt(i) > '9') {
                            JOptionPane.showMessageDialog(null,
                                    "<html><body>Formal Error!" + "<br>" + "Please enter the correct ID!</html>",
                                    "Alert", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    /* Get Info from a file */
                    try {
                        File f = new File("CrewInfo.txt");
                        FileReader fre = new FileReader(f);
                        BufferedReader bre = new BufferedReader(fre);
                        String str = "";
                        while ((str = bre.readLine()) != null) {
                            String[] s = str.split("=");
                            if (s[0].equals(IdText.getText()) && s[1].equals(PassWord.getText())) {

                                bre.close();
                                fre.close();

                                dispose();
                                Crew crew = new Crew();

                                crew.setVisible(true);
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null,
                                "<html><body>Not Found!" + "<br>" + "Please enter the correct ID</html>", "Alert",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                }
            }

        });

        JLabel BackgroundLabel = new JLabel(backimg);
        BackgroundLabel.setBounds(0, 0, 700, 500);
        BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
        CrewPanel.add(BackgroundLabel);
    }

    /*
     * private JLabel getTimelabel() {
     * if (timeLabel == null) {
     * timeLabel = new JLabel("");
     * timeLabel.setBounds(0, 0, 200, 20);
     * timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
     * timeLabel.setForeground(new Color(182, 229, 248));
     * time = new Timer(1000,new ActionListener() {
     * public void actionPerformed(ActionEvent arg0) {
     * timeLabel.setText(new
     * SimpleDateFormat("yyyy/MM/dd EEEE HH:mm:ss",Locale.ENGLISH).format(new
     * Date()));
     * }
     * });
     * time.start();
     * }
     * return timeLabel;
     * }
     */
}
