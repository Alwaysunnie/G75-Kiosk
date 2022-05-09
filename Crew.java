import javax.swing.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;

public class Crew extends JFrame {
    private JPanel CrewPanel;
    private Timer time;
    private JLabel timeLabel;
    private JLabel NoteLabel;

    public Crew() throws Exception {
        setTitle("Crew");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 300, 1060, 710);
        setResizable(false);//Prohibition maximization
        setLocationRelativeTo(null);//mediate

        /*main panel*/
        CrewPanel = new JPanel();
        CrewPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(CrewPanel);
        CrewPanel.setLayout(null);
        CrewPanel.add(getTimelabel());
/* background image */
ImageIcon backimg;
backimg = new ImageIcon("image2.jpeg");
/* Create a title label */
JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
TitleLabel.setForeground(Color.decode("#843C1F"));
TitleLabel.setBounds(330, 20, 400, 80);
TitleLabel.setOpaque(false);
CrewPanel.add(TitleLabel);

/* Create a notice */
NoteLabel = new JLabel();
NoteLabel.setBackground(new Color(239, 230, 248));
NoteLabel.setBounds(0, 100, 1060, 30);
NoteLabel.setOpaque(false);
NoteLabel.setVisible(true);
CrewPanel.add(NoteLabel);
MyComponent mc = new MyComponent();
mc.setBounds(200, 0, 650, 30);
mc.setArrText(new String[] { "Smart Flight Check is in operation. Welcome to use it!",
        "Flight BW1314 will be delayed due to bad weather. Please understand." });
NoteLabel.add(mc);


     

        /*create a button*/
        JButton jb1 = new JButton("Passenger List");
        jb1.setBounds(230, 250, 180, 20);
        CrewPanel.add(jb1);

        JButton jb2 = new JButton("meal");
        jb2.setBounds(460, 250, 90, 20);
        CrewPanel.add(jb2);

        JButton jb3 = new JButton("Meal Manager");
        jb3.setBounds(580, 250, 180, 20);
        CrewPanel.add(jb3);


        /*Add click events*/
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                PassengerList ps= null;
                try {
                    ps = new PassengerList();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                ps.setVisible(true);
            }
        });

        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Notice ch1= new Notice();

            }
        });

        jb3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                try {
                    mealManager mealM = new mealManager();
                    mealM.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });



        JLabel BackgroundLabel = new JLabel(backimg);
        //BackgroundLabel.setBounds(0, 0, 700, 500);
        BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
        CrewPanel.add(BackgroundLabel);

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

