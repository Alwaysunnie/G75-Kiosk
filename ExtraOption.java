import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;


import java.util.Enumeration;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.SimpleDateFormat; 

public class ExtraOption extends JFrame{
    private JPanel EOPanel;
	private Timer time;
	private JLabel timeLabel;
	private JLabel SeatLabel = new JLabel(new ImageIcon("seat.png"));
	private JLabel MealLabel = new JLabel(new ImageIcon("meal.png"));
	private JLabel NextLabel = new JLabel(new ImageIcon("pay.png"));
	private String StandardCount1 = "0";
	private String StandardCount2 = "0";
	private String StandardCount3 = "0";
	private String StandardCount4 = "0";
	private String StandardCount5 = "0";
	private String[] SeatStates;
	private int MealIndex;
	private String Count;
	private int Total = 0;
	
	public ExtraOption(String Name, String idCard, String Flight_No) throws Exception{
		setTitle("Commodity information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		EOPanel = new JPanel();
		EOPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(EOPanel);
		EOPanel.setLayout(null);
		EOPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image3.jpg");
		
		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		EOPanel.add(TitleLabel);
		
		/*Create a Progress*/
		JLabel[] Progress = new JLabel[6];
		for(int i = 0; i < 6; i++){
			//Progress[i] = new JLabel();
			if(i == 0){
				Progress[i] = new JLabel("Check Identity", JLabel.CENTER);
				Progress[i].setBackground(Color.decode("#3CB371"));
			}	
			else if(i == 1){
				Progress[i] = new JLabel("<html><body>Ordering &<br>Seating</body></html>", JLabel.CENTER);
				Progress[i].setBackground(Color.decode("#3CB371"));
			}	
			else if(i == 2) Progress[i] = new JLabel("Payment", JLabel.CENTER);
			else if(i == 3) Progress[i] = new JLabel("Print", JLabel.CENTER);
			else if(i == 4) Progress[i] = new JLabel("Authenticate", JLabel.CENTER);
			else if(i == 5) Progress[i] = new JLabel("Finsh", JLabel.CENTER);
			Progress[i].setBounds(155 + (125*i), 135, 125, 40);
			Progress[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[i].setOpaque(true);
			EOPanel.add(Progress[i]);
		}
		
		/*Create a Seat label*/
		JLabel AssLabel1 = new JLabel();
		AssLabel1.setBackground(new Color(238,238,238));
		AssLabel1.setBounds(150, 175, 760, 480);;
		AssLabel1.setOpaque(true);
		AssLabel1.setVisible(true);
		EOPanel.add(AssLabel1);
		/*Create a Meal label*/
		JLabel AssLabel2 = new JLabel();
		AssLabel2.setBackground(new Color(238,238,238));
		AssLabel2.setBounds(150, 175, 760, 480);
		AssLabel2.setOpaque(true);
		AssLabel2.setVisible(false);
		EOPanel.add(AssLabel2);

		/*Down view of aircraft seat*/
		JLabel DownLabel = new JLabel(new ImageIcon("Flight.png"));
		DownLabel.setBounds(0, 210, 760, 270);
		DownLabel.setOpaque(true);
		AssLabel1.add(DownLabel);

		JLabel TopLabel = new JLabel();
		TopLabel.setBounds(150, 0, 760, 210);
		TopLabel.setOpaque(true);
		AssLabel1.add(TopLabel);
		JLabel Sample1 = new JLabel(); 
		Sample1.setBounds(55, 10 ,20 ,20);
		Sample1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		TopLabel.add(Sample1);
		JLabel Sample2 = new JLabel("Available"); 
		Sample2.setBounds(80, 10 ,60 ,20);
		TopLabel.add(Sample2);
		JLabel Sample3 = new JLabel(); 
		Sample3.setBounds(175, 10 ,20 ,20);
		Sample3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Sample3.setBackground(Color.decode("#FF4500"));
		Sample3.setOpaque(true);
		TopLabel.add(Sample3);
		JLabel Sample4 = new JLabel("Blocked"); 
		Sample4.setBounds(200, 10 ,60 ,20);
		TopLabel.add(Sample4);
		JLabel Sample5 = new JLabel(); 
		Sample5.setBounds(295, 10 ,20 ,20);
		Sample5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Sample5.setBackground(Color.decode("#3CB371"));
		Sample5.setOpaque(true);
		TopLabel.add(Sample5);
		JLabel Sample6 = new JLabel("Your choices"); 
		Sample6.setBounds(320, 10 ,80 ,20);
		TopLabel.add(Sample6);
		
		JLabel ALabel = new JLabel("A");
		ALabel.setBounds(35, 180, 20, 20);
		TopLabel.add(ALabel);
		JLabel BLabel = new JLabel("B");
		BLabel.setBounds(35, 160, 20, 20);
		TopLabel.add(BLabel);
		JLabel CLabel = new JLabel("C");
		CLabel.setBounds(35, 100, 20, 20);
		TopLabel.add(CLabel);
		JLabel DLabel = new JLabel("D");
		DLabel.setBounds(35, 80, 20, 20);
		TopLabel.add(DLabel);
		JLabel Label1 = new JLabel("1");
		Label1.setBounds(55, 60, 20, 20);
		TopLabel.add(Label1);
		JLabel Label2 = new JLabel("2");
		Label2.setBounds(95, 60, 20, 20);
		TopLabel.add(Label2);
		JLabel Label3 = new JLabel("3");
		Label3.setBounds(135, 60, 20, 20);
		TopLabel.add(Label3);
		JLabel Label4 = new JLabel("4");
		Label4.setBounds(175, 60, 20, 20);
		TopLabel.add(Label4);
		JLabel Label5 = new JLabel("5");
		Label5.setBounds(215, 60, 20, 20);
		TopLabel.add(Label5);
		JLabel Label6 = new JLabel("6");
		Label6.setBounds(255, 60, 20, 20);
		TopLabel.add(Label6);
		JLabel Label7 = new JLabel("7");
		Label7.setBounds(295, 60, 20, 20);
		TopLabel.add(Label7);
		JLabel Label8 = new JLabel("8");
		Label8.setBounds(335, 60, 20, 20);
		TopLabel.add(Label8);
		JLabel Label9 = new JLabel("9");
		Label9.setBounds(375, 60, 20, 20);
		TopLabel.add(Label9);
		/*----------------------------------First Class-----------------------------------*/
		JLabel FirstLabel = new JLabel("|<--First Class--->|");
		FirstLabel.setBounds(55, 40, 100, 20);
		TopLabel.add(FirstLabel);
		JLabel FirstPrice = new JLabel("|<-----2000$------>|");
		FirstPrice.setBounds(55, 130, 100, 20);
		TopLabel.add(FirstPrice);
		/*----------------------------------Economy class---------------------------------*/
		JLabel EcoLabel = new JLabel("|<---Economy class---->|");
		EcoLabel.setBounds(175, 40, 140, 20);
		TopLabel.add(EcoLabel);
		JLabel EcoPrice = new JLabel("|<----------1000$--------->|");
		EcoPrice.setBounds(175, 130, 140, 20);
		TopLabel.add(EcoPrice);
		/*----------------------------------Standard--------------------------------------*/
		JLabel StaLabel = new JLabel("|Standard|");
		StaLabel.setBounds(335, 40, 80, 20);
		TopLabel.add(StaLabel);
		JLabel StaPrice = new JLabel("|<---0$-->|");
		StaPrice.setBounds(335, 130, 80, 20);
		TopLabel.add(StaPrice);
		
		JLabel[] Seat = new JLabel[36];
		for(int i = 0; i < 36; i++){
			//Seat[i] = new JLabel(String.valueOf((char)('A'+i%4))+String.valueOf(i/4+1));
			Seat[i] = new JLabel();
			if(i%4 == 0){
				Seat[i].setBounds(55+40*(i/4), 180, 20, 20);
			}
			else if(i%4 == 1){
				Seat[i].setBounds(55+40*(i/4), 160, 20, 20);
			}
			else if(i%4 == 2){
				Seat[i].setBounds(55+40*(i/4), 100, 20, 20);
			}
			else{
				Seat[i].setBounds(55+40*(i/4), 80, 20, 20);
			}
			Seat[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			TopLabel.add(Seat[i]);
		}
		
		File f = new File(Flight_No + "/SeatState.txt");
		FileReader fre = new FileReader(f);
		BufferedReader bre = new BufferedReader(fre);
		String str = "";
		SeatStates = new String[36];
		int k = 0;
		while((str = bre.readLine()) != null){
			SeatStates[k] = str.substring(3, 4);
			if(str.substring(3, 4).equals("1")){
				Seat[k].setBackground(Color.decode("#FF4500"));
				Seat[k].setOpaque(true); 
			}
			else{
				Seat[k].addMouseListener(new MouseListener(){
					int n = 0;
			        public void mousePressed(MouseEvent e){}
			        public void mouseExited(MouseEvent e){}
			        public void mouseEntered(MouseEvent e){}
			        public void mouseClicked(MouseEvent e){
						for(int i = 0; i < 36; i++){
			                if(SeatStates[i] == "-1" && e.getSource() != Seat[i]) return;
		                }
						n++;
						for(int i = 0; i < 36; i++){
							if(e.getSource() == Seat[i]){
							    if(n%2 == 1){
								    SeatStates[i] = "-1";
								    Seat[i].setBackground(Color.decode("#3CB371"));
								    Seat[i].setOpaque(true);
							    }
							    else{
								    SeatStates[i] = "0";
								    Seat[i].setBackground(Color.gray);
								    Seat[i].setOpaque(false);
							    }
							}
						}
					}
			        public void mouseReleased(MouseEvent e) {}
		        });
			}
			k++;
		}
		bre.close();
		fre.close();
		
		/*----------------------------Meal Page-----------------------------------------------*/
		JPanel MealPanel=new JPanel();
        MealPanel.setPreferredSize(new Dimension(450, 540));
        MealPanel.setLayout(null);
		
		JLabel MealTip = new JLabel("<------------------------Standard----------------------->", JLabel.CENTER);
		MealTip.setForeground(Color.decode("#FFA500"));
		MealTip.setBounds(0, 0, 450, 20);
		MealPanel.add(MealTip);
		
		JRadioButton Meal1 = new JRadioButton("StandardMeal1");
		JRadioButton Meal2 = new JRadioButton("StandardMeal2");
		JRadioButton Meal3 = new JRadioButton("Vegetarian Meal");
		JRadioButton Meal4 = new JRadioButton("Characteristic cuisine");
		JRadioButton Meal5 = new JRadioButton("Halal meal");
		ButtonGroup group = new ButtonGroup();
        group.add(Meal1);
        group.add(Meal2);
        group.add(Meal3);
		group.add(Meal4);
		group.add(Meal5);
		/*----------------------------Meal1-----------------------------------------------*/
		JLabel MealMenu1 = new JLabel();
		MealMenu1.setBounds(0, 20, 430, 100);
		MealMenu1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		MealPanel.add(MealMenu1);
		Meal1.setBounds(20, 40, 20, 20);
		MealMenu1.add(Meal1);
		JLabel StandardIcon1 = new JLabel(new ImageIcon("StandardMeal1.jpg"));
		StandardIcon1.setBounds(60, 15, 70, 70);
		MealMenu1.add(StandardIcon1);
		JLabel StandardName1 = new JLabel("<html><body>Standard<br>Meal</body></html>");
		StandardName1.setBounds(150, 30, 60, 40);
		MealMenu1.add(StandardName1);
		JLabel StandardDes1 = new JLabel("<html><body>Rice with<br>Pork & Potato</body></html>");
		StandardDes1.setBounds(230, 20, 60, 60);
		MealMenu1.add(StandardDes1);

		JLabel StandardNum1 = new JLabel();
		StandardNum1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		StandardNum1.setText(StandardCount1);
		StandardNum1.setBounds(310, 40, 40, 20);
		MealMenu1.add(StandardNum1);
		JLabel Num1Add = new JLabel("^",JLabel.CENTER);
		Num1Add.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num1Add.setBounds(20, 0, 20, 10);
		StandardNum1.add(Num1Add);
		Num1Add.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount1 = String.valueOf(Integer.parseInt(StandardCount1.toString())+1);
				StandardNum1.setText(StandardCount1);
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Num1Sub = new JLabel("v",JLabel.CENTER);
		Num1Sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num1Sub.setBounds(20, 10, 20, 10);
		StandardNum1.add(Num1Sub);
		Num1Sub.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount1 = String.valueOf(Integer.parseInt(StandardCount1.toString())-1);
				StandardNum1.setText(StandardCount1);
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Price1 = new JLabel("$0");
		Price1.setBounds(370, 40, 40, 20);
		MealMenu1.add(Price1);
		/*----------------------------Meal2-----------------------------------------------*/
		JLabel MealMenu2 = new JLabel();
		MealMenu2.setBounds(0, 120, 430, 100);
		MealMenu2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		MealPanel.add(MealMenu2);
		Meal2.setBounds(20, 40, 20, 20);
		MealMenu2.add(Meal2);
		JLabel StandardIcon2 = new JLabel(new ImageIcon("StandardMeal2.jpg"));
		StandardIcon2.setBounds(60, 15, 70, 70);
		MealMenu2.add(StandardIcon2);
		JLabel StandardName2 = new JLabel("<html><body>Standard<br>Meal</body></html>");
		StandardName2.setBounds(150, 30, 60, 40);
		MealMenu2.add(StandardName2);
		JLabel StandardDes2 = new JLabel("<html><body>Noodles with<br>Mashroom & Beef</body></html>");
		StandardDes2.setBounds(230, 15, 60, 70);
		MealMenu2.add(StandardDes2);
		JLabel StandardNum2 = new JLabel();
		StandardNum2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		StandardNum2.setText(StandardCount1);
		StandardNum2.setBounds(310, 40, 40, 20);
		MealMenu2.add(StandardNum2);
		JLabel Num2Add = new JLabel("^",JLabel.CENTER);
		Num2Add.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num2Add.setBounds(20, 0, 20, 10);
		StandardNum2.add(Num2Add);
		Num2Add.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount2 = String.valueOf(Integer.parseInt(StandardCount2.toString())+1);
				StandardNum2.setText(StandardCount2);
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Num2Sub = new JLabel("v",JLabel.CENTER);
		Num2Sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num2Sub.setBounds(20, 10, 20, 10);
		StandardNum2.add(Num2Sub);
		Num2Sub.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount2 = String.valueOf(Integer.parseInt(StandardCount2.toString())-1);
				StandardNum2.setText(StandardCount2);
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Price2 = new JLabel("$0");
		Price2.setBounds(370, 40, 40, 20);
		MealMenu2.add(Price2);
		/*----------------------------MealTip-----------------------------------------------*/
		JLabel MealTipMid = new JLabel("<----------Extra---------->", JLabel.CENTER);
		MealTipMid.setForeground(Color.decode("#FFA500"));
		MealTipMid.setBounds(0, 220, 450, 20);
		MealPanel.add(MealTipMid);
		/*----------------------------Meal3-----------------------------------------------*/
		JLabel MealMenu3 = new JLabel();
		MealMenu3.setBounds(0, 240, 430, 100);
		MealMenu3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		MealPanel.add(MealMenu3);
		Meal3.setBounds(20, 40, 20, 20);
		MealMenu3.add(Meal3);
		JLabel StandardIcon3 = new JLabel(new ImageIcon("StandardMeal3.jpg"));
		StandardIcon3.setBounds(60, 15, 70, 70);
		MealMenu3.add(StandardIcon3);
		JLabel StandardName3 = new JLabel("<html><body>Vegetarian<br>Meal</body></html>");
		StandardName3.setBounds(150, 30, 60, 40);
		MealMenu3.add(StandardName3);
		JLabel StandardDes3 = new JLabel("<html><body>vitamins and<br>dietary fiber</body></html>");
		StandardDes3.setBounds(230, 15, 60, 70);
		MealMenu3.add(StandardDes3);
		JLabel StandardNum3 = new JLabel();
		StandardNum3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		StandardNum3.setText(StandardCount1);
		StandardNum3.setBounds(310, 40, 40, 20);
		MealMenu3.add(StandardNum3);
		JLabel Num3Add = new JLabel("^",JLabel.CENTER);
		Num3Add.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num3Add.setBounds(20, 0, 20, 10);
		StandardNum3.add(Num3Add);
		JLabel Price3 = new JLabel();
		Price3.setText("$" + String.valueOf(Integer.parseInt(StandardCount3.toString())*100));
		Price3.setBounds(370, 40, 40, 20);
		MealMenu3.add(Price3);
		Num3Add.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount3 = String.valueOf(Integer.parseInt(StandardCount3.toString())+1);
				StandardNum3.setText(StandardCount3);
				Price3.setText("$" + String.valueOf(Integer.parseInt(StandardCount3.toString())*100));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Num3Sub = new JLabel("v",JLabel.CENTER);
		Num3Sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num3Sub.setBounds(20, 10, 20, 10);
		StandardNum3.add(Num3Sub);
		Num3Sub.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount3 = String.valueOf(Integer.parseInt(StandardCount3.toString())-1);
				StandardNum3.setText(StandardCount3);
				Price3.setText("$" + String.valueOf(Integer.parseInt(StandardCount3.toString())*100));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		/*----------------------------Meal4-----------------------------------------------*/
		JLabel MealMenu4 = new JLabel();
		MealMenu4.setBounds(0, 340, 430, 100);
		MealMenu4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		MealPanel.add(MealMenu4);
		//JCheckBox jcb4 = new JCheckBox();
		Meal4.setBounds(20, 40, 20, 20);
		MealMenu4.add(Meal4);
		JLabel StandardIcon4 = new JLabel(new ImageIcon("StandardMeal4.jpg"));
		StandardIcon4.setBounds(60, 15, 70, 70);
		MealMenu4.add(StandardIcon4);
		JLabel StandardName4 = new JLabel("<html><body>Characteristic<br>cuisine</body></html>");
		StandardName4.setBounds(150, 30, 60, 40);
		MealMenu4.add(StandardName4);
		JLabel StandardDes4 = new JLabel("<html><body>Specialty<br>food at your<br>destination</body></html>");
		StandardDes4.setBounds(230, 15, 60, 70);
		MealMenu4.add(StandardDes4);
		JLabel StandardNum4 = new JLabel();
		StandardNum4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		StandardNum4.setText(StandardCount1);
		StandardNum4.setBounds(310, 40, 40, 20);
		MealMenu4.add(StandardNum4);
		JLabel Num4Add = new JLabel("^",JLabel.CENTER);
		Num4Add.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num4Add.setBounds(20, 0, 20, 10);
		StandardNum4.add(Num4Add);
		JLabel Price4 = new JLabel();
		Price4.setText("$" + String.valueOf(Integer.parseInt(StandardCount3.toString())*200));
		Price4.setBounds(370, 40, 40, 20);
		MealMenu4.add(Price4);
		Num4Add.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount4 = String.valueOf(Integer.parseInt(StandardCount4.toString())+1);
				StandardNum4.setText(StandardCount4);
				Price4.setText("$" + String.valueOf(Integer.parseInt(StandardCount4.toString())*200));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Num4Sub = new JLabel("v",JLabel.CENTER);
		Num4Sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num4Sub.setBounds(20, 10, 20, 10);
		StandardNum4.add(Num4Sub);
		Num4Sub.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount4 = String.valueOf(Integer.parseInt(StandardCount4.toString())-1);
				StandardNum4.setText(StandardCount4);
				Price4.setText("$" + String.valueOf(Integer.parseInt(StandardCount4.toString())*200));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		/*----------------------------Meal5-----------------------------------------------*/
		JLabel MealMenu5 = new JLabel();
		MealMenu5.setBounds(0, 440, 430, 100);
		MealMenu5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		MealPanel.add(MealMenu5);
		Meal5.setBounds(20, 40, 20, 20);
		MealMenu5.add(Meal5);
		JLabel StandardIcon5 = new JLabel(new ImageIcon("StandardMeal5.jpg"));
		StandardIcon5.setBounds(60, 15, 70, 70);
		MealMenu5.add(StandardIcon5);
		JLabel StandardName5 = new JLabel("<html><body>Halal<br>meal</body></html>");
		StandardName5.setBounds(150, 30, 60, 40);
		MealMenu5.add(StandardName5);
		JLabel StandardDes5 = new JLabel("<html><body>Use beef<br>and chicken</body></html>");
		StandardDes5.setBounds(230, 15, 60, 70);
		MealMenu5.add(StandardDes5);
		JLabel StandardNum5 = new JLabel();
		StandardNum5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		StandardNum5.setText(StandardCount1);
		StandardNum5.setBounds(310, 40, 40, 20);
		MealMenu5.add(StandardNum5);
		JLabel Num5Add = new JLabel("^",JLabel.CENTER);
		Num5Add.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num5Add.setBounds(20, 0, 20, 10);
		StandardNum5.add(Num5Add);
		JLabel Price5 = new JLabel();
		Price5.setText("$" + String.valueOf(Integer.parseInt(StandardCount3.toString())*300));
		Price5.setBounds(370, 40, 40, 20);
		MealMenu5.add(Price5);
		Num5Add.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount5 = String.valueOf(Integer.parseInt(StandardCount5.toString())+1);
				StandardNum5.setText(StandardCount5);
				Price5.setText("$" + String.valueOf(Integer.parseInt(StandardCount5.toString())*300));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		JLabel Num5Sub = new JLabel("v",JLabel.CENTER);
		Num5Sub.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		Num5Sub.setBounds(20, 10, 20, 10);
		StandardNum5.add(Num5Sub);
		Num5Sub.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				StandardCount5 = String.valueOf(Integer.parseInt(StandardCount5.toString())-1);
				StandardNum5.setText(StandardCount5);
				Price5.setText("$" + String.valueOf(Integer.parseInt(StandardCount5.toString())*300));
			}
			public void mouseReleased(MouseEvent e) {}
		});
		
		/*----------------------------Sold Out-----------------------------------------------*/
		JLabel so1Label = new JLabel("StandardMeal1 sold out!", JLabel.CENTER);
		so1Label.setBounds(0, 20, 430, 100);
		so1Label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		so1Label.setVisible(false);
		MealPanel.add(so1Label);
		
		JLabel so2Label = new JLabel("StandardMeal2 sold out!", JLabel.CENTER);
		so2Label.setBounds(0, 120, 430, 100);
		so2Label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		so2Label.setVisible(false);
		MealPanel.add(so2Label);
		
		JLabel so3Label = new JLabel("Vegetarian Meal sold out!", JLabel.CENTER);
		so3Label.setBounds(0, 240, 430, 100);
		so3Label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		so3Label.setVisible(false);
		MealPanel.add(so3Label);
		
		JLabel so4Label = new JLabel("Characteristic cuisine sold out!", JLabel.CENTER);
		so4Label.setBounds(0, 340, 430, 100);
		so4Label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		so4Label.setVisible(false);
		MealPanel.add(so4Label);
		
		JLabel so5Label = new JLabel("Halal meal sold out!", JLabel.CENTER);
		so5Label.setBounds(0, 440, 430, 100);
		so5Label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		so5Label.setVisible(false);
		MealPanel.add(so5Label);
		
		JScrollPane scrollpane = new JScrollPane(MealPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setBounds(150, 5, 450, 470);
		AssLabel2.add(scrollpane);
		
		File f1 = new File(Flight_No + "/MealState.txt");
		FileReader fre1 = new FileReader(f1);
		BufferedReader bre1 = new BufferedReader(fre1);
		String str1 = "";
		while((str1 = bre1.readLine()) != null){
			if(str1.substring(0, 1).equals("1") && (str1.substring(2, 3).equals("0") || str1.substring(4, str1.length()).equals("0"))){
				MealMenu1.setVisible(false);
				so1Label.setVisible(true);
			}
			if(str1.substring(0, 1).equals("2") && (str1.substring(2, 3).equals("0") || str1.substring(4, str1.length()).equals("0"))){
				MealMenu2.setVisible(false);
				so2Label.setVisible(true);
			}
			if(str1.substring(0, 1).equals("3") && (str1.substring(2, 3).equals("0") || str1.substring(4, str1.length()).equals("0"))){
				MealMenu3.setVisible(false);
				so3Label.setVisible(true);
			}
			if(str1.substring(0, 1).equals("4") && (str1.substring(2, 3).equals("0") || str1.substring(4, str1.length()).equals("0"))){
				MealMenu4.setVisible(false);
				so4Label.setVisible(true);
			}
			if(str1.substring(0, 1).equals("5") && (str1.substring(2, 3).equals("0") || str1.substring(4, str1.length()).equals("0"))){
				MealMenu5.setVisible(false);
				so5Label.setVisible(true);
			}
		}
		bre1.close();
		fre1.close();
		
		/*Create a Next label*/
		NextLabel.setBounds(90, 390, 50, 50);
		NextLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		EOPanel.add(NextLabel);
		NextLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){

				int i = 0;
				for(i = 0; i < 36; i++){
					if(SeatStates[i] == "-1") break;
				}
				int j = 0;
				for(Enumeration<AbstractButton> Meal = group.getElements(); Meal.hasMoreElements();){
					AbstractButton meal = Meal.nextElement();
                    if(meal.isSelected()){
						MealIndex = j;
                        break;
                    }
					j++;
				}
				if(j == 0) Count = StandardNum1.getText();
				else if(j == 1) Count = StandardNum2.getText();
				else if(j == 2) Count = StandardNum3.getText();
				else if(j == 3) Count = StandardNum4.getText();
				else if(j == 4) Count = StandardNum5.getText();
				if(i < 36 && j < 5 && !Count.equals("0")){
					try{
						if(i < 12) Total += 2000;
						else if(i >= 12 && i < 28) Total += 1000;
						else if(i >= 28 && i <36) Total += 0;
						if(j == 0 || j == 1) Total += 0;
						else if(j == 2) Total += Integer.parseInt(Price3.getText().substring(1, Price3.getText().length()));
						else if(j == 3) Total += Integer.parseInt(Price4.getText().substring(1, Price4.getText().length()));
						else if(j == 4) Total += Integer.parseInt(Price5.getText().substring(1, Price5.getText().length()));
					    dispose();
			            Bind bind = new Bind(Name, idCard, i, MealIndex, Count, Total);
			            bind.setVisible(true);
				    }catch(Exception err){
					    err.printStackTrace();
				    }
				}
				else{
					JOptionPane.showMessageDialog(null,
							"<html><body>Unfinished!" + "<br>" + "Please complete your seat selection and reservation!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		/*Create a Seat label*/
		SeatLabel.setBounds(90, 250, 50, 50);
		SeatLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		EOPanel.add(SeatLabel);
		SeatLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				AssLabel1.setVisible(true);
				AssLabel2.setVisible(false);
				SeatLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
				MealLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		/*Create a Meal label*/
		MealLabel.setBounds(90, 320, 50, 50);
		MealLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
		EOPanel.add(MealLabel);
		MealLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				AssLabel1.setVisible(false);
				SeatLabel.setVisible(true);
				AssLabel2.setVisible(true);
				NextLabel.setVisible(true);
				SeatLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
				MealLabel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		
		
		JLabel BackgroundLabel = new JLabel(backimg);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		EOPanel.add(BackgroundLabel);
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
		ExtraOption eo = new ExtraOption("Zhu Zixin", "140602200910071711", "A666");
		eo.setVisible(true);
	}
}	