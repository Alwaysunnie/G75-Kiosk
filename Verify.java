import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat; 

public class Verify extends JFrame{
    private JPanel VerifyPanel;
	private Timer time;
	private JLabel timeLabel;


	private JLabel ScanLabel = new JLabel(new ImageIcon("Scan.jpeg"));
	private File file = null;
	String recognizeText = "";

    public Verify(String name, String idcard) throws Exception{
	    setTitle("Verify");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
	
		/*main panel*/
		VerifyPanel = new JPanel();
		VerifyPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(VerifyPanel);
		VerifyPanel.setLayout(null);
		VerifyPanel.add(getTimelabel());
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image3.jpg");
		
		/* Create a title label */
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 20, 400, 80);
		TitleLabel.setOpaque(false);
		VerifyPanel.add(TitleLabel);
		
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
			else if(j == 5) Progress[j] = new JLabel("Finsh", JLabel.CENTER);
			Progress[j].setBounds(155 + (125*j), 135, 125, 40);
			Progress[j].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
			Progress[j].setOpaque(true);
			VerifyPanel.add(Progress[j]);
		}

		/*Create a form*/
		JLabel AssLabel = new JLabel();
		AssLabel.setBackground(new Color(238,238,238));
		AssLabel.setBounds(150, 175, 760, 400);
		AssLabel.setOpaque(true);
		VerifyPanel.add(AssLabel);
		
		JLabel photo = new JLabel("Please leave your ID card in the designated area", JLabel.CENTER);
		photo.setBounds(50, 50, 300, 190);
		photo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		AssLabel.add(photo);
		photo.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				JFileChooser fileChooser = new JFileChooser();
				if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				    file = fileChooser.getSelectedFile(); 
				    //System.out.print(file);
					
					try{
					    photo.setIcon(new ImageIcon(file.toURI().toURL()));
				    }catch(Exception err){
					    err.printStackTrace();
				    }
				}	
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});

		/*Create a next label*/
		JButton jb = new JButton("Next");
		jb.setBounds(490, 550, 80, 30);
		jb.setVisible(false);
		VerifyPanel.add(jb);
		
		
		jb.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				try{
					dispose();
			        Finish finish = new Finish();
			        finish.setVisible(true);
				}catch(Exception err){
					err.printStackTrace();
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		JLabel NameLabel = new JLabel(name);
		NameLabel.setBounds(360, 45, 130, 40);
		NameLabel.setBorder(BorderFactory.createTitledBorder("Name"));
		NameLabel.setVisible(false);
		AssLabel.add(NameLabel);
		JLabel IDLabel = new JLabel(idcard);
		IDLabel.setBounds(360, 110, 130, 40);
		IDLabel.setBorder(BorderFactory.createTitledBorder("IDCard"));
		IDLabel.setVisible(false);
		AssLabel.add(IDLabel);
		JButton Exit = new JButton("Exit");
		Exit.setBounds(390, 175, 80, 20);
		Exit.setVisible(false);
		AssLabel.add(Exit);
		Exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		ScanLabel.setBounds(400, 130, 50, 50);
		AssLabel.add(ScanLabel);
		ScanLabel.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent e){
				if(file == null){
					JOptionPane.showMessageDialog(null, "<html><body>Please scan your ID card first!</html>", "Alert", JOptionPane.ERROR_MESSAGE); 
					return;
				}
				try {
                    recognizeText = new OCRHelper().recognizeText(file);
					System.out.println(recognizeText.substring(recognizeText.length()-18, recognizeText.length()) + "\t");
                } catch (IOException err) {
                    err.printStackTrace();
                }catch (Exception err) {
                    err.printStackTrace();
                }
				if(idcard.equals(recognizeText.substring(recognizeText.length()-18, recognizeText.length()))){
					System.out.println("Success");
					ScanLabel.setVisible(false);
					NameLabel.setVisible(true);
					IDLabel.setVisible(true);
					Exit.setVisible(true);
				}
			}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
		});
		
		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, 700, 500);
		BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		VerifyPanel.add(BackgroundLabel);
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
		Print print = new Print("1", "2");
		print.setVisible(true);
	}
}