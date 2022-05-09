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

import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.SimpleDateFormat; 

public class Bind extends JFrame{
    private JPanel BindPanel;
	private Timer time;
	private JLabel timeLabel;
	private String MealName;
	private String MealPrice;
	private String SeatName;
	private String SeatPrice;
	
	public Bind(String Name, String idCard, int i, int MealIndex, String Count, int Total) throws Exception{
		setTitle("Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		BindPanel = new JPanel();
		BindPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(BindPanel);
		BindPanel.setLayout(null);
		BindPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image3.jpg");
		
		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		BindPanel.add(TitleLabel);
		
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
			else if(j == 3) Progress[j] = new JLabel("Print", JLabel.CENTER);
			else if(j == 4) Progress[j] = new JLabel("Authenticate", JLabel.CENTER);
			else if(j == 5) Progress[j] = new JLabel("Finsh", JLabel.CENTER);
			Progress[j].setBounds(155 + (125*j), 135, 125, 40);
			Progress[j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[j].setOpaque(true);
			BindPanel.add(Progress[j]);
		}
		
		/* Create a desk */
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.white);
		AssLabel.setBounds(150, 175, 760, 480);;
		AssLabel.setOpaque(true);
		AssLabel.setVisible(true);
		BindPanel.add(AssLabel);
		
		if(i < 12){
			SeatName = "First Class";
			SeatPrice = "2000";
		}
		else if(i < 28){
			SeatName = "Economy Class";
			SeatPrice = "1000";
		}
		else{
			SeatName = "Standard";
			SeatPrice = "0";
		}
		if(MealIndex == 0){
			MealName = "StandardMeal1";
			MealPrice = "0";
		}	
		else if(MealIndex == 1){
			MealName = "StandardMeal2";
			MealPrice = "0";
		}
		else if(MealIndex == 2){
			MealName = "Vegetarian Meal";
			MealPrice = "100";
		}
		else if(MealIndex == 3){
			MealName = "Characteristic cuisine";
			MealPrice = "200";
		}
		else if(MealIndex == 4){
			MealName = "Halal meal";
			MealPrice = "300";
		}

		/*******************Create a Invoice************************/
		JLabel Invoice = new JLabel();
		Invoice.setBounds(20, 20, 350, 420);
		Invoice.setBackground(Color.white);
		Invoice.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		AssLabel.add(Invoice);
		JLabel InvTable = new JLabel("QM Airline Bill");
		InvTable.setFont(new Font("Times New Roman", Font.BOLD, 20));
		InvTable.setForeground(Color.decode("#843C1F"));
		InvTable.setBounds(100, 15, 210, 40);
		JLabel L1 = new JLabel("==============================================");
		L1.setBounds(10,40,330,20);
		L1.setForeground(Color.decode("#843C1F"));
		Invoice.add(L1);

		Invoice.add(InvTable);
		JLabel Seat = new JLabel("<html><body><b>Seat:</b><br>" + SeatName + "--" + String.valueOf((char)('A'+i%4))+String.valueOf(i/4+1) + "<br>" + SeatPrice +"$</body></html>");
		Seat.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		Seat.setBounds(50, 70, 250, 100);
		Invoice.add(Seat);
	

		JLabel Food = new JLabel("<html><body><b>Food:</b><br>" + MealName + "<br>" + Count + " * " + MealPrice + "$");
		Food.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		Food.setBounds(50, 200, 250 ,100);
		Invoice.add(Food);
		JLabel TotalLabel = new JLabel("Total: " + Total + "$");
		TotalLabel.setBounds(220, 320, 100, 40);
		TotalLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		TotalLabel.setForeground(Color.decode("#843C1F"));
		Invoice.add(TotalLabel);
		
		/* Create a Card */
		JLabel CardForm = new JLabel();
		CardForm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		CardForm.setBounds(390, 20, 350, 140);
		AssLabel.add(CardForm);

		JLabel PayTable = new JLabel("Pay By Credit Card");
		PayTable.setBounds(110, 5, 210, 40);
		PayTable.setFont(new Font("Times New Roman", Font.BOLD, 16));
		CardForm.add(PayTable);
		final JFormattedTextField CardNum = new JFormattedTextField();
		CardNum.setForeground(Color.LIGHT_GRAY);
		CardNum.setToolTipText("Please input your credit card numbers.");
		CardNum.setBorder(BorderFactory.createTitledBorder("Credit Card Num"));
		CardNum.setText("Please input your credit card number.");
		CardNum.setBounds(50, 50, 250, 40);
		CardForm.add(CardNum);
		CardNum.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e) {
				if(CardNum.getText().equals("Please input your credit card number."))
				CardNum.setText(null);
				CardNum.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		JButton jb = new JButton("Bind");
		jb.setBounds(135, 100, 80, 20);
		CardForm.add(jb);
		
		/* Create a QR */
		JLabel QRForm = new JLabel();
		QRForm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		QRForm.setBackground(Color.white);
		QRForm.setBounds(390, 150, 350, 290);
		AssLabel.add(QRForm);

		JLabel PayTable2 = new JLabel("Pay By Scanning QR");
		PayTable2.setBounds(110, 15, 210, 40);
		PayTable2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		QRForm.add(PayTable2);
	
		/*Create QR pictures*/
		JLabel QRWechat = new JLabel(new ImageIcon("QR1.png"));
		QRWechat.setBounds(20,50,150,150);
		QRWechat.setOpaque(false);
		QRForm.add( QRWechat);

		JLabel QRAli = new JLabel(new ImageIcon("QR2.png"));
		QRAli.setBounds(190,50,150,150);
		QRAli.setOpaque(false);
		QRForm.add( QRAli);

		/*Create QR Logo*/
		JLabel WechatLogo = new JLabel(new ImageIcon("wechat.png"));
		WechatLogo.setBounds(50,200,40,40);
		WechatLogo.setOpaque(false);
		QRForm.add(WechatLogo);

		JLabel AliLogo = new JLabel(new ImageIcon("alipay.png"));
		AliLogo.setBounds(220,200,40,40);
		AliLogo.setOpaque(false);
		QRForm.add(AliLogo);

		/*Create a logo text*/
		JLabel WechatLabel = new JLabel("WeChat");
		WechatLabel.setBounds(95, 200, 50, 30);
		WechatLabel.setOpaque(false);
		QRForm.add(WechatLabel);

		JLabel AliLabel = new JLabel("AliPay");
		AliLabel.setBounds(265, 200, 50, 30);
		AliLabel.setOpaque(false);
		QRForm.add(AliLabel);

		/* Create a button */	
		JButton jb2 = new JButton("Next");
		jb2.setBounds(130, 250, 80, 20);
		QRForm.add(jb2);

		/* Create a Bill */
		JLabel BillForm1 = new JLabel();
		BillForm1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		BillForm1.setBounds(100, 60, 560, 300);
		BillForm1.setBackground(new Color(246,246,246));
		BillForm1.setVisible(false);
		AssLabel.add(BillForm1);

		JLabel BillTitle = new JLabel("Successful Payment!");
		BillTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		BillTitle.setBounds(180, 30, 320, 40);
		BillForm1.add(BillTitle);

		JLabel methodLabel1 = new JLabel("Paying method: By scanning the QR");
		methodLabel1.setBounds(100, 90, 320, 40);
		BillForm1.add(methodLabel1);

		JLabel billTotal = new JLabel("Total cost: " + Total + "$");
		billTotal.setBounds(100, 140, 260, 40);
		BillForm1.add(billTotal);

		JLabel confirm1 = new JLabel(new ImageIcon("bill.png"));
		confirm1.setBounds(400,200,100,100);
		confirm1.setOpaque(false);
		BillForm1.add(confirm1);

		JButton jb1 = new JButton("Next");
		jb1.setBounds(240, 400, 80, 30);
		BillForm1.add(jb1);
		
		/* Create a Card1 */
		JLabel CardForm1 = new JLabel();
		CardForm1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		CardForm1.setBounds(100, 60, 560, 300);
		CardForm1.setBackground(new Color(246,246,246));
		CardForm1.setVisible(false);
		AssLabel.add(CardForm1);
		
		JLabel CardForm2 = new JLabel();
		CardForm2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		CardForm2.setBounds(100, 60, 560, 300);
		CardForm2.setBackground(new Color(246,246,246));
		CardForm2.setVisible(false);
		AssLabel.add(CardForm2);

		JLabel CardTitle = new JLabel("Successful Payment!");
		CardTitle.setFont(new Font("Times New Roman", Font.BOLD, 20));
		CardTitle.setBounds(180, 30, 320, 40);
		CardForm1.add(CardTitle);
		JLabel CardTitle1 = new JLabel("Successful Payment!");
		CardTitle1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		CardTitle1.setBounds(180, 30, 320, 40);
		CardForm2.add(CardTitle1);

		JLabel method1Label = new JLabel("Paying method: By credit card");
		method1Label.setBounds(100, 90, 320, 40);
		CardForm1.add(method1Label);
		JLabel method2Label = new JLabel("Paying method: By scanning QR");
		method2Label.setBounds(100, 90, 320, 40);
		CardForm2.add(method2Label);

		JLabel CardNum1 = new JLabel();
		CardNum1.setBounds(100, 140, 260, 40);
		CardForm1.add(CardNum1);
		
		JLabel PayTime = new JLabel();
		PayTime.setBounds(350, 170, 200, 20);
		CardForm1.add(PayTime);
		JLabel PayTime1 = new JLabel();
		PayTime1.setBounds(350, 170, 200, 20);
		CardForm2.add(PayTime1);

		JLabel confirm = new JLabel(new ImageIcon("bill.png"));
		confirm.setBounds(400,200,100,100);
		confirm.setOpaque(false);
		CardForm1.add(confirm);
		JLabel confirm2 = new JLabel(new ImageIcon("bill.png"));
		confirm2.setBounds(400,200,100,100);
		confirm2.setOpaque(false);
		CardForm2.add(confirm2);

		/************  create a next button **************/
		ImageIcon icon = new ImageIcon("next2.png");
		JButton jb3 = new JButton(icon);
		jb3.setBounds(320, 400, 100, 34);
		jb3.setBorder(BorderFactory.createRaisedBevelBorder());
		jb3.setBorderPainted(false); 
		jb3.setVisible(false);
		jb3.setOpaque(false);
		AssLabel.add(jb3);

		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				    File f = new File("Card.txt");
		            FileReader fre = new FileReader(f);
		            BufferedReader bre = new BufferedReader(fre);
		            String str = "";
					int k = 0;
		            while((str = bre.readLine()) != null){
			            if(str.substring(0, 8).equals(CardNum.getText()) && str.substring(14, str.length()).equals(idCard)){
						    if(Integer.parseInt(str.substring(9, 13)) >= Total){
								PayTime.setText(timeLabel.getText());
								CardForm.setVisible(false);
								Invoice.setVisible(false);
								QRForm.setVisible(false);
								CardForm1.setVisible(true);
								jb3.setVisible(true);
								CardNum1.setText("Card: " + CardNum.getText());
								k++;
							}
						    else{
							    JOptionPane.showMessageDialog(null,
							        "<html><body>No sufficient funds!" + "<br>" + "Please change another card or pay by scanning QR.</html>",
							        "Alert", JOptionPane.ERROR_MESSAGE);
								return;
						    }
					    }
		            }
					if(k != 1){
						JOptionPane.showMessageDialog(null,
							"<html><body>Card number wrong!" + "<br>" + "Please enter the correct card number!</html>",
							"Alert", JOptionPane.ERROR_MESSAGE);
					}
		            bre.close();
		            fre.close();
				}catch(Exception err){
					System.out.println(err);
				}
			}
        });	
		
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PayTime1.setText(timeLabel.getText());
				CardForm.setVisible(false);
				Invoice.setVisible(false);
				QRForm.setVisible(false);
				CardForm2.setVisible(true);
				jb3.setVisible(true);
			}
		});

        jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			    try{
					Print p = new Print(Name, idCard, i);
					p.setVisible(true);
				}catch(Exception err1){
					System.out.println(err1);
				}
            }
        });			
		
		JLabel BackgroundLabel = new JLabel(backimg);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		BindPanel.add(BackgroundLabel);
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
		Bind b = new Bind("Zhu Zixin", "140602200910071711", 23, 4, "3", 1500);
		b.setVisible(true);
	}
}	