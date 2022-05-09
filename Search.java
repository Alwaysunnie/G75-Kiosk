import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat; 
import java.util.concurrent.ExecutorService;

public class Search extends JFrame{
	
	private JPanel SearchPanel;
	private Timer time;
	private JLabel timeLabel;
	private JLabel PassLabel = new JLabel("Passenger", JLabel.CENTER);
	private JLabel CrewLabel = new JLabel("Crew", JLabel.CENTER);
	private JLabel ManaLabel = new JLabel("Manager", JLabel.CENTER);
	private JLabel NoteLabel;
	
	public Search(){
		
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(true);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		SearchPanel = new JPanel();
		SearchPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(SearchPanel);
		SearchPanel.setLayout(null); 
		SearchPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image.jpeg");
		
		/*Create a title label*/
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		SearchPanel.add(TitleLabel);
		
		/*Create a notice*/
		NoteLabel = new JLabel();
		NoteLabel.setBackground(new Color(239,230,248));
		NoteLabel.setBounds(0, 100, 1060, 30);
		NoteLabel.setOpaque(false);
		NoteLabel.setVisible(true);
		SearchPanel.add(NoteLabel);
		MyComponent mc = new MyComponent();
		mc.setBounds(200, 0, 650, 30);
		mc.setArrText(new String []{"Welcome to Queen Mary Airline!", "Select your identity on the homepage."});
		NoteLabel.add(mc);

		/*Create a passenger label head*/
		JLabel AssLabelHead = new JLabel();
		AssLabelHead.setBackground(new Color(113,127,159));
		AssLabelHead.setBounds(150, 175, 760, 40);
		AssLabelHead.setOpaque(true);
		AssLabelHead.setVisible(true);
		SearchPanel.add(AssLabelHead);

		/*Create a QM logo*/
		JLabel QMgui = new JLabel(new ImageIcon("QMlogo.jpg"));
		QMgui.setBounds(25,5,30,30);
		QMgui.setOpaque(false);
		AssLabelHead.add(QMgui);

		/*Create a Airline logo*/
		JLabel logoLabel = new JLabel("Queen Mary  Airline");
		logoLabel.setFont(new Font("华文彩云", Font.BOLD, 16));
		logoLabel.setForeground(Color.white);
		logoLabel.setBounds(63, 5, 300, 30);
		logoLabel.setOpaque(false);
		AssLabelHead.add(logoLabel);

		/*Create a passenger label*/
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(Color.white);
		AssLabel.setBounds(150, 200, 760, 400);
		AssLabel.setOpaque(true);
		AssLabel.setVisible(true);
		SearchPanel.add(AssLabel);


		/*Create a crew label*/
		JLabel Ass1Label = new JLabel();
		Ass1Label.setBackground(Color.white);
		Ass1Label.setBounds(150, 200, 760, 400);
		Ass1Label.setOpaque(true);
		Ass1Label.setVisible(false);
		SearchPanel.add(Ass1Label);
		/*Create a crew check label*/
		JLabel crewSubtitle = new JLabel("Click login to check your ID:");
		crewSubtitle.setBackground(Color.white);
		crewSubtitle.setBounds(280, 260, 200, 30);
		crewSubtitle.setOpaque(true);
		crewSubtitle.setVisible(false);
		Ass1Label.add(crewSubtitle);

		/*Create a manager label*/
		JLabel Ass2Label = new JLabel();
		Ass2Label.setBackground(Color.BLACK);
		Ass2Label.setBounds(100, 220, 276, 200);
		Ass2Label.setOpaque(true);
		Ass2Label.setVisible(false);
		SearchPanel.add(Ass2Label);

		/*Create a discription 身份选择*/
		JLabel discLogo = new JLabel("Choose from the Identity Selector: ");
		discLogo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		discLogo.setBounds(250, 15, 280, 30);
		AssLabel.add(discLogo);

	/*Create a picture 三个头像*/
JLabel Search1gui = new JLabel(new ImageIcon("searchLogo.jpg"));
Search1gui.setBounds(1,40,760,180);
Search1gui.setOpaque(false);
Ass1Label.add(Search1gui);
	/*Create a picture 三个头像*/
	JLabel Searchgui = new JLabel(new ImageIcon("searchLogo.jpg"));
	Searchgui.setBounds(1,40,760,180);
	Searchgui.setOpaque(false);
	AssLabel.add(Searchgui);
		
	
		/* create a Crew input */
		JLabel SubTitleLabel2 = new JLabel("crew log in:");
		SubTitleLabel2.setFont(new Font("华文彩云", Font.BOLD, 16));
		SubTitleLabel2.setBackground(Color.white);
		SubTitleLabel2.setBounds(280, 260, 200, 30);
		SubTitleLabel2.setOpaque(true);
		Ass1Label.add(SubTitleLabel2);
		JButton jb1 = new JButton("Enter");
		jb1.setBounds(315, 360, 130, 30);
		jb1.setBorder(BorderFactory.createRaisedBevelBorder());
		Ass1Label.add(jb1);

		/*Create a jcombobox*/
		JComboBox <String> jc = new JComboBox <String>();
		jc.addItem("Input booking number");
		jc.addItem("Enter surname and ID number");
		jc.addItem("Scan ID document");
		jc.setBounds(280, 260, 200, 30);
		jc.setVisible(false);
		jc.setOpaque(false);
		AssLabel.add(jc);

		/*Add click events*/
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(jc.getSelectedIndex());
					dispose();
					CrewCheck cc=new CrewCheck();
					cc.setVisible(true);
			}
		});
		
		/******************************Create a passenger label*************************************/

	
			/*create a button*/
			ImageIcon icon = new ImageIcon("enter.png");
			//JButton jb = new JButton("Enter");
			JButton jb = new JButton(icon);
			jb.setBounds(300, 350, 160, 50);
			jb.setBorder(BorderFactory.createRaisedBevelBorder());
			jb.setBorderPainted(false); 
			jb.setOpaque(false);
			jb.setVisible(false);
			AssLabel.add(jb);
			 
			/*Add click events 乘客check-in方式*/
			jb.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					System.out.println(jc.getSelectedIndex());
					if(jc.getSelectedIndex() == 0) {
						dispose();
						Check1 ch1 = new Check1();
						ch1.setVisible(true);
					}
					else if(jc.getSelectedIndex() == 1) {
						dispose();
						Check2 ch2 = new Check2();
						ch2.setVisible(true);
					}
					else {
						dispose();
						Check3 ch3 = new Check3();
						ch3.setVisible(true);
					}
				}
			});
			
		PassLabel.setBackground(new Color(222,222,222));
		PassLabel.setBounds(95, 220, 70, 25);
		PassLabel.setOpaque(true);
		AssLabel.add(PassLabel);
		PassLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				Ass1Label.setVisible(false);
				Ass2Label.setVisible(false);
				AssLabel.setVisible(true);
				jc.setVisible(true);
				jb.setVisible(true);
				PassLabel.setBackground(new Color(127, 153, 202));
		        PassLabel.setBounds(90, 215, 80, 35);
		        PassLabel.setOpaque(true);
				CrewLabel.setBackground(new Color(222,222,222));
				CrewLabel.setBounds(335, 220, 70, 25);
		        CrewLabel.setOpaque(true);
				ManaLabel.setBackground(new Color(222,222,222));
		        ManaLabel.setBounds(580, 220, 70, 25);
		        ManaLabel.setOpaque(true);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		/*************************************Create a crew label**************************************/
		CrewLabel.setBackground(new Color(222,222,222));
		CrewLabel.setBounds(335, 220, 70, 25);
		CrewLabel.setOpaque(true);
		AssLabel.add(CrewLabel);
		CrewLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				Ass2Label.setVisible(false);
				AssLabel.setVisible(false);
				Ass1Label.setVisible(false);
				CrewLabel.setVisible(false);
				//Searchgui.setVisible(true);
				CrewCheck cc = new CrewCheck();
						cc.setVisible(true);
						
				jc.setVisible(false);
				crewSubtitle.setVisible(true);
				PassLabel.setBackground(new Color(222,222,222));
		        PassLabel.setBounds(95, 220, 70, 25);
				PassLabel.setForeground(Color.black);
		        PassLabel.setVisible(true);
				CrewLabel.setBackground(new Color(127, 153, 202));
				CrewLabel.setForeground(Color.white);
		        CrewLabel.setBounds(330, 215, 80, 35);
		        CrewLabel.setVisible(true);
				ManaLabel.setBackground(new Color(222,222,222));
		        ManaLabel.setBounds(580, 220, 70, 25);
		        ManaLabel.setVisible(true);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		/*****************************************Create a manager label****************************************/
		ManaLabel.setBackground(new Color(222,222,222));
		ManaLabel.setBounds(580, 220, 70, 25);
		ManaLabel.setOpaque(true);
		AssLabel.add(ManaLabel);
		ManaLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				Ass1Label.setVisible(false);
				Ass2Label.setVisible(false);
				AssLabel.setVisible(false);

				ManagerCheck mc = new ManagerCheck();
						mc.setVisible(true);
				PassLabel.setBackground(new Color(222,222,222));
		        PassLabel.setBounds(10, 70, 60, 20);
		        PassLabel.setOpaque(true);
				CrewLabel.setBackground(new Color(222,222,222));
				CrewLabel.setBounds(335, 180, 70, 25);
		        CrewLabel.setOpaque(true);
				ManaLabel.setBackground(new Color(127, 153, 202));
		        ManaLabel.setBounds(575, 175, 80, 35);
		        ManaLabel.setOpaque(true);
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		JLabel BackgroundLabel = new JLabel(backimg);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		SearchPanel.add(BackgroundLabel);
	}
	
	/*Timer*/
	private JLabel getTimelabel() {
		if (timeLabel == null) {
			timeLabel = new JLabel("");
			timeLabel.setBounds(20, 10, 300, 20);
			timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
			timeLabel.setForeground(new Color(200, 162, 120));
			time = new Timer(1000,new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					timeLabel.setText(new SimpleDateFormat("yyyy/MM/dd EEEE HH:mm:ss",Locale.ENGLISH).format(new Date()));
				}
			});
			time.start();
		}
		return timeLabel;
	}
	
    public static void main(String[] args) {
	    Search search = new Search();
		search.setVisible(true);
	}
} 