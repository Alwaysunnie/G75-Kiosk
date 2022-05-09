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

public class Summary extends JFrame{
    private JPanel SummaryPanel;
	private Timer time;
	private JLabel timeLabel;
	
	/*Personal imformation*/
	private String Name;
	private String Sex;
	private String bookNum;
	private String idCard;
	private String DateOfBooking;
	private String teleNum;
	/*Flght imformation*/
	private String Flight_No;
	private String Departure;
	private String Destination;
	private String Departure_Time;
	private String Landing_Time;
	private String Flight_Time;
	
	public Summary(String param) throws Exception{
		setTitle("Basic information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		SummaryPanel = new JPanel();
		SummaryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(SummaryPanel);
		SummaryPanel.setLayout(null);
		SummaryPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image.jpeg");
		
			/* Create a title label */
			JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
			TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
			TitleLabel.setForeground(Color.decode("#843C1F"));
			TitleLabel.setBounds(330, 20, 400, 80);
			TitleLabel.setOpaque(false);
			SummaryPanel.add(TitleLabel);
			
		/*Create a Progress*/
		JLabel[] Progress = new JLabel[6];
		for(int i = 0; i < 6; i++){
			//Progress[i] = new JLabel();
			if(i == 0){
				Progress[i] = new JLabel("Check Identity", JLabel.CENTER);
				Progress[i].setBackground(Color.decode("#3CB371"));
			}	
			else if(i == 1) Progress[i] = new JLabel("<html><body>Ordering &<br>Seating</body></html>", JLabel.CENTER);
			else if(i == 2) Progress[i] = new JLabel("Payment", JLabel.CENTER);
			else if(i == 3) Progress[i] = new JLabel("Print", JLabel.CENTER);
			else if(i == 4) Progress[i] = new JLabel("Authenticate", JLabel.CENTER);
			else if(i == 5) Progress[i] = new JLabel("Finsh", JLabel.CENTER);
			Progress[i].setBounds(155 + (125*i), 135, 125, 40);
			Progress[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[i].setOpaque(true);
			SummaryPanel.add(Progress[i]);
		}	

		/* Create a passenger label */
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.white);
		AssLabel.setBounds(150, 175, 760, 400);
		AssLabel.setOpaque(true);
		AssLabel.setVisible(true);
		SummaryPanel.add(AssLabel);
		
			/* Create a subtitle */
			JLabel subtitle = new JLabel("--------------------------------------------Please check your personal information--------------------------------------------");
			subtitle.setFont(new Font("Times New Roman", Font.BOLD, 16));
			//subtitle.setForeground(Color.white);
			subtitle.setBounds(20, 5, 720, 30);
			subtitle.setOpaque(false);
			subtitle.setVisible(true);
			AssLabel.add(subtitle);
		
		File f = new File("InfoList.txt");
		FileReader fre = new FileReader(f);
		BufferedReader bre = new BufferedReader(fre);
		String str = "";
		if(param.length()==13){
			while((str = bre.readLine()) != null){
			    if(str.substring(0, 13).equals(param)){
				    bookNum = param;
				    Sex = str.substring(14, 15);
	                DateOfBooking = str.substring(16, 26);
				    idCard = str.substring(27, 45);
				    teleNum = str.substring(46, 57);
					Flight_No = str.substring(58, 62);
					Departure = str.substring(63, 78);
					Destination = str.substring(82, 98);
					Departure_Time = str.substring(99, 117);
					Landing_Time = str.substring(118, 136);
					Flight_Time = str.substring(137, 146);
				    Name = str.substring(147, str.length());
			    }
		    }
		}
		else{
			while((str = bre.readLine()) != null){
			    if(str.substring(46, 57).equals(param)){
					teleNum = param;
				    bookNum = str.substring(0, 13);
				    Sex = str.substring(14, 15);
	                DateOfBooking = str.substring(16, 26);
	                idCard = str.substring(27, 45);
					Flight_No = str.substring(58, 62);
					Departure = str.substring(63, 78);
					Destination = str.substring(82, 98);
					Departure_Time = str.substring(99, 117);
					Landing_Time = str.substring(118, 136);
					Flight_Time = str.substring(137, 146);
				    Name = str.substring(147, str.length());
			    }
		    }
		}
		bre.close();
		fre.close();
		
		/*Create a personal information label*/
		JLabel subtitle1Label = new JLabel("Personal Information");
		subtitle1Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		subtitle1Label.setBounds(140, 50, 200, 20);
		subtitle1Label.setForeground(Color.decode("#843C1F"));
		subtitle1Label.setOpaque(false);
		AssLabel.add(subtitle1Label);
		/*Create a flight information label*/
		JLabel subtitle2Label = new JLabel("Flight Information");
		subtitle2Label.setFont(new Font("Times New Roman", Font.BOLD, 16));
		subtitle2Label.setBounds(450, 50, 200, 20);
		subtitle2Label.setForeground(Color.decode("#843C1F"));
		subtitle2Label.setOpaque(false);
		AssLabel.add(subtitle2Label);
		
		/*Create a name label*/
		JLabel nameLabel = new JLabel("Name: " + Name);
		nameLabel.setBounds(140, 90, 200, 20);
		nameLabel.setOpaque(false);
		AssLabel.add(nameLabel);
		
		/*Create a sex label*/
		JLabel sexLabel = new JLabel("Sex: " + Sex);
		sexLabel.setBounds(140, 130, 200, 20);
		sexLabel.setOpaque(false);
		AssLabel.add(sexLabel);
		
		/*Create a bookNum label*/
		JLabel bookNumLabel = new JLabel("Booking_No: " + bookNum);
		bookNumLabel.setBounds(140, 170, 200, 20);
		bookNumLabel.setOpaque(false);
		AssLabel.add(bookNumLabel);
		
		/*Create a idCard label*/
		JLabel idCardLabel = new JLabel("ID_No: " + idCard);
		idCardLabel.setBounds(140, 210, 200, 20);
		idCardLabel.setOpaque(false);
		AssLabel.add(idCardLabel);
		
		/*Create a DateOfBooking label*/
		JLabel DateOfBookingLabel = new JLabel("Booking Date: " + DateOfBooking);
		DateOfBookingLabel.setBounds(140, 250, 200, 20);
		DateOfBookingLabel.setOpaque(false);
		AssLabel.add(DateOfBookingLabel);
		
		/*Create a teleNum label*/
		JLabel teleNumLabel = new JLabel("Tel_No: " + teleNum);
		teleNumLabel.setBounds(140, 290, 200, 20);
		teleNumLabel.setOpaque(false);
		AssLabel.add(teleNumLabel);
		
		/*Create a flight_no label*/
		JLabel Flight_NoLabel = new JLabel("Flight_No: " + Flight_No);
		Flight_NoLabel.setBounds(450, 90, 200, 20);
		Flight_NoLabel.setOpaque(false);
		AssLabel.add(Flight_NoLabel);
		
		/*Create a Departure label*/
		JLabel DepartureLabel = new JLabel("Departure: " + Departure);
		DepartureLabel.setBounds(450, 130, 200, 20);
		DepartureLabel.setOpaque(false);
		AssLabel.add(DepartureLabel);
		
		/*Create a Destination label*/
		JLabel DestinationLabel = new JLabel("Destination: " + Destination);
		DestinationLabel.setBounds(450, 170, 200, 20);
		DestinationLabel.setOpaque(false);
		AssLabel.add(DestinationLabel);
		
		/*Create a Departure_Time label*/
		JLabel Departure_TimeLabel = new JLabel("Departure Time: " + Departure_Time);
		Departure_TimeLabel.setBounds(450, 210, 250, 20);
		Departure_TimeLabel.setOpaque(false);
		AssLabel.add(Departure_TimeLabel);
		
		/*Create a Landing_Time label*/
		JLabel Landing_TimeLabel = new JLabel("Landing Time: " + Landing_Time);
		Landing_TimeLabel.setBounds(450, 250, 200, 20);
		Landing_TimeLabel.setOpaque(false);
		AssLabel.add(Landing_TimeLabel);
		
		/*Create a Flight_Time label*/
		JLabel Flight_TimeLabel = new JLabel("Flying Time: " + Flight_Time);
		Flight_TimeLabel.setBounds(450, 290, 200, 20);
		Flight_TimeLabel.setOpaque(false);
		AssLabel.add(Flight_TimeLabel);
		
		/*Create two buttons*/
		// JButton jb = new JButton("Next");
		// jb.setBounds(660, 600, 80, 26);
		// SummaryPanel.add(jb);
		

		/************  create a next button **************/
		ImageIcon icon = new ImageIcon("next1.png");
		JButton jb = new JButton(icon);
		jb.setBounds(650, 600, 100, 34);
		jb.setBorder(BorderFactory.createRaisedBevelBorder());
		jb.setBorderPainted(false); 
		jb.setOpaque(false);
		SummaryPanel.add(jb);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					dispose();
			        ExtraOption eo = new ExtraOption(Name, idCard, Flight_No);
			        eo.setVisible(true);
				}catch(Exception err){
					err.printStackTrace();
				}
			}
		});
/************  create a back button **************/
		// JButton jb1 = new JButton("Back");
		// jb1.setBounds(310, 600, 100, 35);
		// SummaryPanel.add(jb1);
		// /*Return to home page*/
		// jb1.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
		// 		dispose();
		// 	    Search search = new Search();
		// 	    search.setVisible(true);
		// 	}
		// });
		
 /**************** create a back button *****************/
 ImageIcon icon1 = new ImageIcon("back1.png");
 JButton jb1 = new JButton(icon1);
 jb1.setBounds(310, 600, 100, 35);
 //jb1.setBorder(BorderFactory.createRaisedBevelBorder());
 jb1.setBorderPainted(false); 
 jb1.setOpaque(false);
 SummaryPanel.add(jb1);
 /* Return to previous page */
 jb1.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
		 dispose();
		 Search search = new Search();
		 search.setVisible(true);
	 }
 });

		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		SummaryPanel.add(BackgroundLabel);
	}

    public Summary(String name, String idcard) throws Exception{
		setTitle("Check With Booking Number");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 576, 360);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		SummaryPanel = new JPanel();
		SummaryPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(SummaryPanel);
		SummaryPanel.setLayout(null);
		SummaryPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image.jpeg");
		
		/*Create a title label*/
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("华文彩云", Font.BOLD, 35));
		TitleLabel.setForeground(Color.LIGHT_GRAY);
		TitleLabel.setBounds(90, 0, 400, 80);
		TitleLabel.setOpaque(false);
		SummaryPanel.add(TitleLabel);
		
		/*Create a form*/
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.decode("#E6E6FA"));
		AssLabel.setBounds(50, 60, 460, 240);
		AssLabel.setOpaque(true);
		SummaryPanel.add(AssLabel);
		/*Get Info from a file*/
		
		File f = new File("InfoList.txt");
		FileReader fre = new FileReader(f);
		BufferedReader bre = new BufferedReader(fre);
		String str = "";
		while((str = bre.readLine()) != null){
			if(str.substring(27, 45).equals(idcard) && str.substring(147, str.length()).equals(name)){
				bookNum = str.substring(0, 13);
				Sex = str.substring(14, 15);
	            DateOfBooking = str.substring(16, 26);
				idCard = idcard;
				Name = name;
				teleNum = str.substring(46, 57);
				Flight_No = str.substring(58, 62);
				Departure = str.substring(63, 78);
				Destination = str.substring(82, 98);
				Departure_Time = str.substring(99, 117);
				Landing_Time = str.substring(118, 136);
				Flight_Time = str.substring(137, 146);
			}
		}
		bre.close();
		fre.close();
		
		/*Create a personal information label*/
		JLabel subtitle1Label = new JLabel("Personal Information");
		subtitle1Label.setBounds(140, 50, 200, 20);
		subtitle1Label.setOpaque(false);
		AssLabel.add(subtitle1Label);
		/*Create a flight information label*/
		JLabel subtitle2Label = new JLabel("Flight Information");
		subtitle2Label.setBounds(450, 50, 200, 20);
		subtitle2Label.setOpaque(false);
		AssLabel.add(subtitle2Label);
		
		/*Create a name label*/
		JLabel nameLabel = new JLabel("Name: " + Name);
		nameLabel.setBounds(140, 90, 200, 20);
		nameLabel.setOpaque(false);
		AssLabel.add(nameLabel);
		
		/*Create a sex label*/
		JLabel sexLabel = new JLabel("Sex: " + Sex);
		sexLabel.setBounds(140, 130, 200, 20);
		sexLabel.setOpaque(false);
		AssLabel.add(sexLabel);
		
		/*Create a bookNum label*/
		JLabel bookNumLabel = new JLabel("Booking_No: " + bookNum);
		bookNumLabel.setBounds(140, 170, 200, 20);
		bookNumLabel.setOpaque(false);
		AssLabel.add(bookNumLabel);
		
		/*Create a idCard label*/
		JLabel idCardLabel = new JLabel("ID_No: " + idCard);
		idCardLabel.setBounds(140, 210, 200, 20);
		idCardLabel.setOpaque(false);
		AssLabel.add(idCardLabel);
		
		/*Create a DateOfBooking label*/
		JLabel DateOfBookingLabel = new JLabel("Booking Date: " + DateOfBooking);
		DateOfBookingLabel.setBounds(140, 250, 200, 20);
		DateOfBookingLabel.setOpaque(false);
		AssLabel.add(DateOfBookingLabel);
		
		/*Create a teleNum label*/
		JLabel teleNumLabel = new JLabel("Tel_No: " + teleNum);
		teleNumLabel.setBounds(140, 290, 200, 20);
		teleNumLabel.setOpaque(false);
		AssLabel.add(teleNumLabel);
		
		/*Create a flight_no label*/
		JLabel Flight_NoLabel = new JLabel("Flight_No: " + Flight_No);
		Flight_NoLabel.setBounds(450, 90, 200, 20);
		Flight_NoLabel.setOpaque(false);
		AssLabel.add(Flight_NoLabel);
		
		/*Create a Departure label*/
		JLabel DepartureLabel = new JLabel("Departure: " + Departure);
		DepartureLabel.setBounds(450, 130, 200, 20);
		DepartureLabel.setOpaque(false);
		AssLabel.add(DepartureLabel);
		
		/*Create a Destination label*/
		JLabel DestinationLabel = new JLabel("Destination: " + Destination);
		DestinationLabel.setBounds(450, 170, 200, 20);
		DestinationLabel.setOpaque(false);
		AssLabel.add(DestinationLabel);
		
		/*Create a Departure_Time label*/
		JLabel Departure_TimeLabel = new JLabel("Departure Time: " + Departure_Time);
		Departure_TimeLabel.setBounds(450, 210, 250, 20);
		Departure_TimeLabel.setOpaque(false);
		AssLabel.add(Departure_TimeLabel);
		
		/*Create a Landing_Time label*/
		JLabel Landing_TimeLabel = new JLabel("Landing Time: " + Landing_Time);
		Landing_TimeLabel.setBounds(450, 250, 200, 20);
		Landing_TimeLabel.setOpaque(false);
		AssLabel.add(Landing_TimeLabel);
		
		/*Create a Flight_Time label*/
		JLabel Flight_TimeLabel = new JLabel("Flying Time: " + Flight_Time);
		Flight_TimeLabel.setBounds(450, 290, 200, 20);
		Flight_TimeLabel.setOpaque(false);
		AssLabel.add(Flight_TimeLabel);

/************  create a next button **************/
		ImageIcon icon = new ImageIcon("next1.png");
		JButton jb = new JButton(icon);
		jb.setBounds(650, 600, 100, 34);
		jb.setBorder(BorderFactory.createRaisedBevelBorder());
		jb.setBorderPainted(false); 
		jb.setOpaque(false);
		SummaryPanel.add(jb);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					dispose();
			        ExtraOption eo = new ExtraOption(Name, idCard, Flight_No);
			        eo.setVisible(true);
				}catch(Exception err){
					err.printStackTrace();
				}
			}
		});
/************  create a back button **************/
		// JButton jb1 = new JButton("Back");
		// jb1.setBounds(310, 600, 100, 35);
		// SummaryPanel.add(jb1);
		// /*Return to home page*/
		// jb1.addActionListener(new ActionListener(){
		// 	public void actionPerformed(ActionEvent e){
		// 		dispose();
		// 	    Search search = new Search();
		// 	    search.setVisible(true);
		// 	}
		// });
		
 /**************** create a back button *****************/
 ImageIcon icon1 = new ImageIcon("back1.png");
 JButton jb1 = new JButton(icon1);
 jb1.setBounds(310, 600, 100, 35);
 //jb1.setBorder(BorderFactory.createRaisedBevelBorder());
 jb1.setBorderPainted(false); 
 jb1.setOpaque(false);
 SummaryPanel.add(jb1);
 /* Return to previous page */
 jb1.addActionListener(new ActionListener() {
	 public void actionPerformed(ActionEvent e) {
		 dispose();
		 Search search = new Search();
		 search.setVisible(true);
	 }
 });
		
		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		SummaryPanel.add(BackgroundLabel);
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