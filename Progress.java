import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Progress extends JFrame{
    private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 50;
    private static int currentProgress = MIN_PROGRESS;
	private JPanel ProPanel;
	private MyJPanel BarPanel;
	private Timer time;
	int index;
	ImageIcon[] imgs = {
		//new ImageIcon("images/1.jpg"),
		new ImageIcon("images/2.jpg"),
		//new ImageIcon("images/3.jpg"),
		//new ImageIcon("images/4.jpg"),
		//new ImageIcon("images/5.jpeg"),
	};
	
	public Progress(){
		setTitle("QM Airplane System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 300, 1060, 710);
		setResizable(true);//Prohibition maximization
		setLocationRelativeTo(null);//mediate

        /*main panel*/
		ProPanel = new JPanel();
		ProPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(ProPanel);
		ProPanel.setLayout(null); 
		BarPanel = new MyJPanel();
		BarPanel.setBounds(150, 100, 860, 560);
		ProPanel.add(BarPanel);
		Timer timer1 = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				BarPanel.repaint();
			}
		});
		timer1.start();
		
		/*background image*/
		ImageIcon backimg;
		backimg=new ImageIcon("image.jpeg");

        /*Create a title label*/
		JLabel TitleLabel = new JLabel("SMART FLIGHT CHECK");
		TitleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 35));
		TitleLabel.setForeground(Color.decode("#843C1F"));
		TitleLabel.setBounds(330, 0, 400, 80);
		TitleLabel.setOpaque(false);
		ProPanel.add(TitleLabel);		
		
		/*Create a ProgressBar*/
		final JProgressBar progressBar = new JProgressBar();
		progressBar.setOrientation(JProgressBar.HORIZONTAL);
		//progressBar.setSize(200, 30);
		progressBar.setBounds(50, 620, 960, 30);;
		progressBar.setMinimum(MIN_PROGRESS);
        progressBar.setMaximum(MAX_PROGRESS);
		progressBar.setValue(currentProgress);
		progressBar.setStringPainted(true);
		progressBar.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("Current: " + progressBar.getValue() + "; " + "Persent: " + progressBar.getPercentComplete());
            }
        });
        ProPanel.add(progressBar);
		time = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentProgress++;
                if (currentProgress > MAX_PROGRESS) {
                    dispose();
					Search s = new Search();
					s.setVisible(true);
					time.stop();
                }
                progressBar.setValue(currentProgress);
            }
        });
		time.start();
		
		JLabel BackgroundLabel = new JLabel(backimg);
		//BackgroundLabel.setBounds(0, 0, backimg.getIconWidth(), backimg.getIconHeight());
		//ProPanel.add(BackgroundLabel);
	}
	
	class MyJPanel extends JPanel{
		public void paint(Graphics g){
			super.paint(g);
			g.drawImage(imgs[index%imgs.length].getImage(), 0, 0, this);
			index++;
		}
	}
	
	public static void main(String[] args) {
		Progress pro = new Progress();
		pro.setVisible(true);
	}
}