
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

public class ManagerCheck extends JFrame {
    private JPanel ManagerPanel;
    private Timer time;
    private JLabel timeLabel;

    public ManagerCheck() {
        setTitle("Manager Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 200, 800, 480);
        setResizable(false);// Prohibition maximization
        setLocationRelativeTo(null);// mediate

        ImageIcon backimg;
        backimg = new ImageIcon("image.jpeg");

        /* main panel */
        ManagerPanel = new JPanel();
        ManagerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane( ManagerPanel);
        ManagerPanel.setLayout(null);
        //  ManagerPanel.add(getTimelabel());

        /* Create a access label */
        JLabel AssLabel = new JLabel();
        AssLabel.setBackground(Color.white);
        AssLabel.setBounds(1, 1, 800, 480);
        AssLabel.setOpaque(true);
        ManagerPanel.add(AssLabel);

        /* Create a manager picture */
        JLabel managerPic = new JLabel(new ImageIcon("managerPic.jpg"));
        managerPic.setBounds(0, 1, 400, 470);
        managerPic.setOpaque(false);
        AssLabel.add(managerPic);

        /* Create a manager logo */
        JLabel managerLogo = new JLabel(new ImageIcon("managerLogo.jpg"));
        managerLogo.setBounds(540, 40, 180, 180);
        managerLogo.setOpaque(false);
        AssLabel.add(managerLogo);

        /************ Input manager Information ************/
        JFormattedTextField IdText = new JFormattedTextField();
        IdText.setBackground(new Color(226, 221, 244));
        IdText.setForeground(Color.GRAY);
        IdText.setToolTipText("manager no");
        IdText.setText("manager no");
        IdText.setBounds(540, 240, 180, 20);

        JFormattedTextField PassWord2 = new JFormattedTextField();
        PassWord2.setForeground(Color.GRAY);
        PassWord2.setBackground(new Color(226, 221, 244));
        PassWord2.setToolTipText("PassWord2");
        PassWord2.setText("PassWord2");
        PassWord2.setBounds(540, 300, 180, 20);
        AssLabel.add(IdText);
        AssLabel.add(PassWord2);

        /************ Check manager Information ***************/
        IdText.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (IdText.getText().equals("manager no"))
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

        PassWord2.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (PassWord2.getText().equals("PassWord2"))
                    PassWord2.setText(null);
                PassWord2.setForeground(Color.BLACK);
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
                        File f = new File("ManagerInfo.txt");
                        FileReader fre = new FileReader(f);
                        BufferedReader bre = new BufferedReader(fre);
                        String str = "";
                        while ((str = bre.readLine()) != null) {
                            String[] s = str.split("=");
                            if (s[0].equals(IdText.getText()) && s[1].equals(PassWord2.getText())) {

                                bre.close();
                                fre.close();

                                dispose();
                               Manager manager= new Manager();

                                //manager.setVisible(true);
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
        ManagerPanel.add(BackgroundLabel);
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
