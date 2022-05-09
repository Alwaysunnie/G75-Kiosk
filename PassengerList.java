import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.table.*;

public class PassengerList extends JFrame {
    private JPanel ListPanel;
    private Timer time;
    private JLabel timeLabel;

    public PassengerList() throws Exception{
        setTitle("Passenger List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 300, 800, 360);
        setResizable(false);//Prohibition maximization
        setLocationRelativeTo(null);//mediate

        /*main panel*/
        ListPanel = new JPanel();
        ListPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        setContentPane(ListPanel);
        ListPanel.setLayout(null);
        ListPanel.add(getTimelabel());


       
        String[] columnNames =
                { "flight", "passenger_name", "check in status", "seat_No", "meal","note" };

        
        Object[][] obj = new Object[100][7];

        File f = new File("list.txt");

        FileReader fre = new FileReader(f);
        BufferedReader bre = new BufferedReader(fre);
        String str = "";
        int s=0;
        while((str = bre.readLine()) != null)
        {

            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        obj[s][j] = str.substring(0, 5);
                        break;
                    case 1:
                        obj[s][j] = str.substring(6, 9);
                        break;
                    case 2:
                        obj[s][j] = str.substring(10, 19);
                        break;
                    case 3:
                        obj[s][j] = str.substring(20, 23);
                        break;
                    case 4:
                        obj[s][j] = str.substring(24,34);
                        break;
                    case 5:
                        obj[s][j] = str.substring(35,36);
                        break;
                }
            }
                        s++;
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ly_time = sdf.format(new java.util.Date());
        Date date1 = sdf.parse("2009-12-31 22:22:00");
        Date date2 = sdf.parse(ly_time);
        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));
        int timeout=0;
        if (date1.compareTo(date2) > 0) {

            //System.out.println("Date1 时间在 Date2 之后");


        } else if (date1.compareTo(date2) < 0) {

            //System.out.println("Date1 时间在 Date2 之前");
            timeout=1;
        }
        for(int n=0;n<s;n++)
        {
            if(obj[n][2].equals("CHECK-OUT")&(timeout==1))
            {
                obj[n][5]=111;
                System.out.println(timeout);
            }

        }

      
        JTable table = new JTable(obj, columnNames);

        TableColumn column = null;
        int colunms = table.getColumnCount();
        for(int i = 0; i < colunms; i++)
        {
            column = table.getColumnModel().getColumn(i);
            
            column.setPreferredWidth(150);
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.setGridColor(Color.BLACK);
        
        JScrollPane scroll = new JScrollPane();

        scroll.setViewportView(table);


        scroll.setBounds(20, 20, 750, 200);


        add(scroll);

        /*create a button*/
        JButton jb1 = new JButton("Back");
        jb1.setBounds(350, 260, 80, 20);
        ListPanel.add(jb1);
        /*Return to previous page*/
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                Crew search = null;
                try {
                    search = new Crew();
                    search.setVisible(true);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });



    }

    private JLabel getTimelabel() {
        if (timeLabel == null) {
            timeLabel = new JLabel("");
            timeLabel.setBounds(0, 0, 200, 20);
            timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
            timeLabel.setForeground(new Color(182, 229, 248));
            time = new Timer(1000,new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    timeLabel.setText(new SimpleDateFormat("yyyy/MM/dd EEEE HH:mm:ss", Locale.ENGLISH).format(new Date()));
                }
            });
            time.start();
        }
        return timeLabel;
    }
}
