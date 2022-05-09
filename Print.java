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

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Print extends JFrame{
    private JPanel PrintPanel;
	private Timer time;
	private JLabel timeLabel;
	
	private JLabel NextLabel = new JLabel(new ImageIcon("Right.jpeg"));
	
	/*Personal imformation*/
	private String Name;
	/*Flght imformation*/
	private String Flight_No;
	private String Departure;
	private String Destination;
	private String Gate = "05";
	private String Departure_Time;
	private String Seat;
	private String Boarding_till;
	/*Baggage imformation*/
	private String Weight;
	private double Rate = 11.00;
	private String Charge;
	private String Ticket_No;
	/*Print imformation*/
	private boolean PrintState;

    public Print(String name, String idcard, int i) throws Exception{
	    setTitle("Print");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
	
		/*main panel*/
		PrintPanel = new JPanel();
		PrintPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(PrintPanel);
		PrintPanel.setLayout(null);
		PrintPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image.jpeg");
		
		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		PrintPanel.add(TitleLabel);
		
		File f = new File("InfoList.txt");
		FileReader fre = new FileReader(f);
		BufferedReader bre = new BufferedReader(fre);
		String str = "";
		while((str = bre.readLine()) != null){
			if(str.substring(27, 45).equals(idcard)){
				Name = str.substring(147, str.length());
				Flight_No = str.substring(58, 62);
				Departure = str.substring(63, 78);
				Destination = str.substring(82, 98);
				Departure_Time = str.substring(99, 117);
			}
		}
		bre.close();
		fre.close();
		Seat = String.valueOf((char)('A'+i%4))+String.valueOf(i/4+1);
		File f1 = new File("Baggage.txt");
		FileReader fre1 = new FileReader(f1);
		BufferedReader bre1 = new BufferedReader(fre1);
		String str1 = "";
		while((str1 = bre1.readLine()) != null){
			if(str1.substring(0, 18).equals(idcard)){
				Boarding_till = str1.substring(19, 24);
				Ticket_No = str1.substring(25, 34);
				Weight = str1.substring(35, str1.length());
			}
		}
		bre1.close();
		fre1.close();
		Charge = String.valueOf(Integer.parseInt(Weight.toString())*Rate);
		
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
			else if(j == 4) Progress[j] = new JLabel("Authenticate", JLabel.CENTER);
			else if(j == 5) Progress[j] = new JLabel("Finsh", JLabel.CENTER);
			Progress[j].setBounds(155 + (125*j), 135, 125, 40);
			Progress[j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[j].setOpaque(true);
			PrintPanel.add(Progress[j]);
		}
		
		/*Create a form*/
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.decode("#E6E6FA"));
		AssLabel.setBounds(50, 175, 940, 550);//60
		AssLabel.setOpaque(true);
		PrintPanel.add(AssLabel);
		
		/*Printer*/
		JPanel PrinterPanel=new JPanel();
        PrinterPanel.setPreferredSize(new Dimension(880, 1350));
        PrinterPanel.setLayout(null);
		

		/******************************Boarding pass*******************************/
		JLabel PrintMenu1 = new JLabel();
		PrintMenu1.setBounds(0, 0, 880, 350);
		PrintMenu1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		PrinterPanel.add(PrintMenu1);
		JLabel PrintTitle1 = new JLabel("1.BOARDING PASS");
		PrintTitle1.setBounds(5, 5, 150, 20);
		PrintMenu1.add(PrintTitle1);

		JLabel Pass = new JLabel(new ImageIcon("boarding.png"));
		Pass.setBounds(40, 0, 800, 370);
		Pass.setOpaque(false);
		PrintMenu1.add(Pass);
		JLabel PassContent = new JLabel();
		PassContent.setBounds(90, 120, 700, 180);
		PassContent.setBackground(new Color(0,0,0));
		Pass.add(PassContent);
		JLabel PassName = new JLabel("<html><body>Name of passenger<br>" + Name + "</body></html>");
		PassName.setBounds(30, 0, 150, 40);
		PassName.setForeground(new Color(0,0,0));
		PassContent.add(PassName);

		JLabel PassFrom = new JLabel("<html><body>From<br>" + Departure + "</body></html>");
		PassFrom.setBounds(30, 45, 150, 40);
		PassContent.add(PassFrom);

		JLabel PassTo = new JLabel("<html><body>To<br>" + Destination + "</body></html>");
		PassTo.setBounds(30, 90, 150, 40);
		PassContent.add(PassTo);

		JLabel PassGate = new JLabel("<html><body>Gate<br>" + Gate + "</body></html>");
		PassGate.setBounds(30, 135, 100, 40);
		PassContent.add(PassGate);

		JLabel PassDate = new JLabel("<html><body>Date<br>" + Departure_Time + "</body></html>");
		PassDate.setBounds(130, 135, 150, 40);
		PassContent.add(PassDate);

		JLabel PassFlight = new JLabel("<html><body>Flight<br>" + Flight_No + "</body></html>");
		PassFlight.setBounds(280, 135, 100, 40);
		PassContent.add(PassFlight);

		JLabel PassSeat = new JLabel("<html><body>Seat<br>" + Seat + "</body></html>");
		PassSeat.setBounds(380, 135, 100, 40);
		PassContent.add(PassSeat);

		JLabel PassBoarding_till = new JLabel("<html><body>Boarding_till<br>" + Boarding_till + "</body></html>");
		PassBoarding_till.setBounds(250, 45, 150, 40);
		PassContent.add(PassBoarding_till);

		JLabel PassName1 = new JLabel("<html><body><i>Name of passenger</i><br>" + Name + "</body></html>");
		PassName1.setBounds(450, 0, 150, 40);
		PassContent.add(PassName1);

		JLabel PassFrom1 = new JLabel("<html><body><i>From</i><br>" + Departure + "</body></html>");
		PassFrom1.setBounds(450, 45, 150, 40);
		PassContent.add(PassFrom1);

		JLabel PassTo1 = new JLabel("<html><body><i>To</i><br>" + Destination + "</body></html>");
		PassTo1.setBounds(450, 90, 150, 40);
		PassContent.add(PassTo1);

		JLabel PassGate1 = new JLabel("<html><body><i>Gate</i><br>" + Gate + "</body></html>");
		PassGate1.setBounds(450, 135, 100, 40);
		PassContent.add(PassGate1);

		JLabel PassFlight1 = new JLabel("<html><body><i>Flight</i><br>" + Flight_No + "</body></html>");
		PassFlight1.setBounds(600, 0, 100, 40);
		PassContent.add(PassFlight1);
		JLabel PassSeat1 = new JLabel("<html><body><i>Seat</i><br>" + Seat + "</body></html>");
		PassSeat1.setBounds(600, 45, 100, 40);
		PassContent.add(PassSeat1);
		JLabel PassDate1 = new JLabel("<html><body><i>Date</i><br>" + Departure_Time.substring(9, Departure_Time.length()) + "</body></html>");
		PassDate1.setBounds(600, 90, 100, 40);
		PassContent.add(PassDate1);
		JLabel PassBoarding_till1 = new JLabel("<html><body><i>Boarding_till</i><br>" + Boarding_till + "</body></html>");
		PassBoarding_till1.setBounds(600, 135, 100, 40);
		PassContent.add(PassBoarding_till1);

		
		/********************************Ticket for checked baggage**************************** */
		JLabel PrintMenu2 = new JLabel();
		PrintMenu2.setBounds(0, 350, 880, 400);
		PrintMenu2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		PrinterPanel.add(PrintMenu2);

		JLabel PrintTitle2 = new JLabel("2.TICKET FOR CHECKED BAGGAGE");
		PrintTitle2.setBounds(5, 5, 300, 30);
		PrintMenu2.add(PrintTitle2);

		
		JLabel Baggage = new JLabel(new ImageIcon("checked.png"));
		Baggage.setBounds(40, 30, 800, 370);
		Baggage.setOpaque(false);
		PrintMenu2.add(Baggage);

		
		JLabel BaggageContent = new JLabel();
		BaggageContent.setBounds(60, 100, 700, 180);
		//BaggageContent.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Baggage.add(BaggageContent);
		JLabel BaggageName = new JLabel("NAME OF PASSENAGER: " + Name);
		BaggageName.setBounds(20, 50, 250, 40);
		BaggageContent.add(BaggageName);
		JLabel BaggageTicket = new JLabel("TICKET NO: " + Ticket_No, JLabel.RIGHT);
		BaggageTicket.setBounds(430, 50, 250, 40);
		BaggageContent.add(BaggageTicket);
		JLabel[] BaggageTable = new JLabel[6];
		for(int k = 0; k < 6; k++){
			if(k == 0) BaggageTable[k] = new JLabel("SECTOR", JLabel.CENTER);
			else if(k == 1) BaggageTable[k] = new JLabel("FLIGHT NO", JLabel.CENTER);
			else if(k == 2) BaggageTable[k] = new JLabel("WEIGHT", JLabel.CENTER);
			else if(k == 3) BaggageTable[k] = new JLabel("RATE/kg", JLabel.CENTER);
			else if(k == 4) BaggageTable[k] = new JLabel("CHARGE", JLabel.CENTER);
			else if(k == 5) BaggageTable[k] = new JLabel("TOTAL", JLabel.CENTER);
			BaggageTable[k].setBounds(20+(110*k), 80, 110, 40);
			BaggageTable[k].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			BaggageContent.add(BaggageTable[k]);
		}
		JLabel[] BaggageTable1 = new JLabel[6];
		for(int t = 0; t < 6; t++){
			if(t == 0) BaggageTable1[t] = new JLabel("<html><body>" + Departure + "<br>" + "-><br>" + Destination, JLabel.CENTER);
			else if(t == 1) BaggageTable1[t] = new JLabel(Flight_No, JLabel.CENTER);
			else if(t == 2) BaggageTable1[t] = new JLabel(Weight, JLabel.CENTER);
			else if(t == 3) BaggageTable1[t] = new JLabel(String.valueOf(Rate) + "0", JLabel.CENTER);
			else if(t == 4) BaggageTable1[t] = new JLabel(Charge+ "0", JLabel.CENTER);
			else if(t == 5) BaggageTable1[t] = new JLabel(Charge+ "0", JLabel.CENTER);
			BaggageTable1[t].setBounds(20+(110*t), 120, 110, 55);
			BaggageTable1[t].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			BaggageContent.add(BaggageTable1[t]);
		}	
		

		/****************************************Baggage strape tag*********************************** */
		JLabel PrintMenu3 = new JLabel();
		PrintMenu3.setBounds(0, 750, 880, 400);
		PrintMenu3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		PrinterPanel.add(PrintMenu3);
		JLabel PrintTitle3 = new JLabel("3.BAGGAGE STRAPE TAG");
		PrintTitle3.setBounds(5, 5, 300, 30);
		PrintMenu3.add(PrintTitle3);
		JLabel Tag = new JLabel(new ImageIcon("tag.png"));
		Tag.setBounds(40, 40, 800, 320);
		PrintMenu3.add(Tag);
		
		JLabel[] TagTable = new JLabel[3];
		for(int q = 0; q < 3; q++){
			if(q == 0) TagTable[q] = new JLabel("Date", JLabel.CENTER);
			else if(q == 1) TagTable[q] = new JLabel("Flight", JLabel.CENTER);
			else if(q == 2) TagTable[q] = new JLabel("Weight", JLabel.CENTER);
			TagTable[q].setBounds(20+(100*q), 100, 100, 40);
			TagTable[q].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			Tag.add(TagTable[q]);
		}
		JLabel[] TagTable1 = new JLabel[3];
		for(int q = 0; q < 3; q++){
			if(q == 0) TagTable1[q] = new JLabel(Departure_Time.substring(9, Departure_Time.length()), JLabel.CENTER);
			else if(q == 1) TagTable1[q] = new JLabel(Flight_No, JLabel.CENTER);
			else if(q == 2) TagTable1[q] = new JLabel(Weight, JLabel.CENTER);
			TagTable1[q].setBounds(20+(100*q), 140, 100, 40);
			TagTable1[q].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			Tag.add(TagTable1[q]);
		}
		
		JLabel Policy = new JLabel("Privacy and Protocal", JLabel.RIGHT);
		Policy.setForeground(Color.BLUE);
		Policy.setBounds(0, 1110, 440, 45);
		PrinterPanel.add(Policy);
		JCheckBox jcb = new JCheckBox();
		jcb.setBounds(300, 15, 20, 20);
		Policy.add(jcb);
		Policy.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				try{
					jcb.setSelected(true);
			        Policy p = new Policy();
			        p.setVisible(true);
				}catch(Exception err2){
					err2.printStackTrace();
				}	
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Policy1 = new JLabel(" Queen Mary Airline", JLabel.LEFT);
		Policy1.setBounds(440, 1110, 440, 45);
		PrinterPanel.add(Policy1);
		
		// JButton Print = new JButton("Print");
		// Print.setBounds(600, 1170, 80, 30);
		// PrinterPanel.add(Print);

		/************  create a print button **************/
		ImageIcon icon = new ImageIcon("print.png");
		JButton Print = new JButton(icon);
		Print.setBounds(600, 1170, 80, 30);
		Print.setBorder(BorderFactory.createRaisedBevelBorder());
		Print.setBorderPainted(false); 
		Print.setOpaque(false);
		PrinterPanel.add(Print);
		
		Print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jcb.isSelected() == false){
					JOptionPane.showMessageDialog(null,
							"<html><body>Unfinished!" + "<br>" + "Please read the terms and conditions first!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					Policy.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.red));
					Policy1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.red));
					return;
				}
				else{
					try{
					PrintState = true;
					File file = new File("printer.wav");
					AudioInputStream am;
					am = AudioSystem.getAudioInputStream(file);
					AudioFormat af = am.getFormat();
					SourceDataLine sd ;
					sd = AudioSystem.getSourceDataLine(af);
					sd.open();
					sd.start();
					int sumByteRead = 0; 
	                byte [] b = new byte[320];
					while (sumByteRead != -1) {
		                sumByteRead = am.read(b, 0, b.length);
		                if(sumByteRead >= 0 ) {
			                sd.write(b, 0, b.length);
		                }
	                }
	                sd.drain();
	                sd.close();
					}catch(Exception err1){
					err1.printStackTrace();
				    }
				}
			}
		});

		/************  create a next button **************/
		ImageIcon icon1 = new ImageIcon("next3.png");
		JButton Next = new JButton(icon1);
		Next.setBounds(700, 1170, 80, 30);
		Next.setBorder(BorderFactory.createRaisedBevelBorder());
		Next.setBorderPainted(false); 
		Next.setOpaque(false);
		PrinterPanel.add(Next);

		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(PrintState == true){
					    dispose();
			            Verify ver = new Verify(name, idcard);
			            ver.setVisible(true);
					}
                    else{
						JOptionPane.showMessageDialog(null,
							"<html><body>Unfinished!" + "<br>" + "Please print the relevant ticket first!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					    return;
					}					
				}catch(Exception err){
					err.printStackTrace();
				}
			}
		});

		/*Next.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				try{
					dispose();
			        Verify ver = new Verify(name, idcard);
			        ver.setVisible(true);
				}catch(Exception err){
					err.printStackTrace();
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});*/
		
		JScrollPane scrollpane = new JScrollPane(PrinterPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(30, 0, 880, 480);
		AssLabel.add(scrollpane);
		
		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		PrintPanel.add(BackgroundLabel);
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
	
	public static void main(String[] args) throws Exception{
		Print print = new Print("Zhu Zixin", "140602200910071711", 6);
		print.setVisible(true);
	}
}