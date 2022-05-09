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

public class Finish extends JFrame{
    private JPanel FinishPanel;
	private Timer time;
	private JLabel timeLabel;
	
	//private JLabel NextLabel = new JLabel(new ImageIcon("Right.jpeg"));
public Finish(){
	    setTitle("Check-in Successfully");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
	
		/*main panel*/
		FinishPanel = new JPanel();
		FinishPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(FinishPanel);
		FinishPanel.setLayout(null);
		FinishPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image3.jpg");
		
			/* Create a title label */
			JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
			TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
			TitleLabel.setForeground(Color.decode("#843C1F"));
			TitleLabel.setBounds(330, 20, 400, 80);
			TitleLabel.setOpaque(false);
			FinishPanel.add(TitleLabel);
			
			/*Create a Progress*/
		JLabel[] Progress = new JLabel[6];
		for(int j = 0; j < 6; j++){
			if(j == 0){
				Progress[j] = new JLabel("Check Identity", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}	
			else if(j == 1){
				Progress[j] = new JLabel("<html><body>Ordering &<br>Seating</body></html>", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}	
			else if(j == 2){
				Progress[j] = new JLabel("Payment", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}
			else if(j == 3){
				Progress[j] = new JLabel("Print", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}
			else if(j == 4){
				Progress[j] = new JLabel("Authenticate", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}
			else if(j == 5){
				Progress[j] = new JLabel("Finsh", JLabel.CENTER);
				Progress[j].setBackground(Color.decode("#3CB371"));
			}
			Progress[j].setBounds(155 + (125*j), 135, 125, 40);
			Progress[j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[j].setOpaque(true);
			FinishPanel.add(Progress[j]);
		}
			
            /* Create a passenger label */
            JLabel AssLabel = new JLabel();
            AssLabel.setBackground(Color.white);
            AssLabel.setBounds(150, 175, 760, 400);
            AssLabel.setOpaque(true);
            AssLabel.setVisible(true);
            FinishPanel.add(AssLabel);

            /* Create a title label */
			JLabel FinishLabel = new JLabel("Check-in successfully!");
			FinishLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
			FinishLabel.setForeground(Color.black);
			FinishLabel.setBounds(180, 10, 400, 80);
			FinishLabel.setOpaque(false);
			AssLabel.add(FinishLabel);
              /* Create a picture label */
			JLabel Finishpic = new JLabel(new ImageIcon("finish.jpg"));
			
			Finishpic.setBounds(10, 100, 760, 300);
			Finishpic.setOpaque(false);
			AssLabel.add(Finishpic);
	
	
		
		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		FinishPanel.add(BackgroundLabel);
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