import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class Policy extends JFrame{
    private JPanel PolicyPanel;
	private Timer time;
	
	public Policy() throws Exception{
		setTitle("Policy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 500, 400);
		setResizable(false);//Prohibition maximization
		setLocationRelativeTo(null);//mediate
		
		/*main panel*/
		PolicyPanel = new JPanel();
		PolicyPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(PolicyPanel);
		PolicyPanel.setLayout(null);
		
		JButton jb = new JButton();
		jb.setBounds(180, 300, 80, 30);
		PolicyPanel.add(jb);
		
		time = new Timer(1000, new ActionListener() {
			int i = 5;
		    public void actionPerformed(ActionEvent arg0) {
				jb.setText(i + "s");
			    i--;
				if(i == -1){
					jb.setText("I agree");
					time.stop();
					jb.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                dispose();
			            }
		            });
				}
			}
	    });
		time.start();
	}
}	