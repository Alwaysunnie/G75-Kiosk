import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import java.io.*;


import java.util.Enumeration;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class mealManager extends JFrame {
    public mealManager() throws Exception{

        setTitle("Commodity information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 300, 1060, 710);
        setResizable(false);//Prohibition maximization
        setLocationRelativeTo(null);//mediate


        JPanel MealPanel=new JPanel();
        MealPanel.setPreferredSize(new Dimension(1000, 540));
        MealPanel.setLayout(null);
        add(MealPanel);

        /* set state*/
        /*---------------------------------------------------------------------------*/
        int[] state=new int[5];
        String[] num=new String[10];
        int i=0;
        ArrayList<String>array =new ArrayList<>();

        File f1 = new File("MealState.txt");
        FileReader fre1 = new FileReader(f1);
        BufferedReader bre1 = new BufferedReader(fre1);
        String str1 = "";
        while((str1 = bre1.readLine()) != null){
            array.add(str1);
            if(str1.substring(2,3).equals("1"))
                state[i]=1;

            else state[i]=0;
            num[i]=str1.substring(4,7);
            i++;
        }

        /* meal1*/
        /*---------------------------------------------------------------------------*/
        JLabel MealMenu1 = new JLabel();
        MealMenu1.setBounds(0, 20, 900, 100);
        MealMenu1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        MealPanel.add(MealMenu1);
        JLabel StandardIcon1 = new JLabel(new ImageIcon("StandardMeal1.jpg"));
        StandardIcon1.setBounds(60, 15, 70, 70);
        MealMenu1.add(StandardIcon1);
        JLabel StandardName1 = new JLabel("<html><body>Standard<br>Meal</body></html>");
        StandardName1.setBounds(150, 30, 60, 40);
        MealMenu1.add(StandardName1);


        if(state[0]==1){
            JLabel meal11=new JLabel("FOR SALE");
            meal11.setBounds(250, 30, 60, 40);
            MealMenu1.add(meal11);
        }else {
            JLabel meal10=new JLabel("SOLD OUT");
            meal10.setBounds(250, 30, 60, 40);
            MealMenu1.add(meal10);
        }

        /* modify the number*/
        /*---------------------------------------------------------------------------*/
        JFormattedTextField num1 = new JFormattedTextField();
        num1.setBackground(new Color(226, 221, 244));
        num1.setForeground(Color.GRAY);
        num1.setToolTipText("num");
        num1.setText(num[0]);
        num1.setBounds(330, 30, 60, 20);
        MealMenu1.add(num1);

        num1.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (num1.getText().equals(num[0]))
                    num1.setText(null);
                num1.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        num1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String number1= array.get(0);
                StringBuilder sb = new StringBuilder(number1);
                System.out.println(num1.getText());
                sb.replace(4,8,String.valueOf(num1.getText()));
                number1=sb.toString();
                array.set(0,number1);

                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                    bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        /* add and remove */
        /*---------------------------------------------------------------------------*/
        JButton jb10 = new JButton("Added");
        jb10.setBounds(430, 30, 100, 20);
        MealMenu1.add(jb10);

        JButton jb11 = new JButton("REMOVE");
        jb11.setBounds(550, 30, 100, 20);
        MealMenu1.add(jb11);

        jb10.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1= array.get(0);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2,3,String.valueOf("1"));
                number1=sb.toString();
                array.set(0,number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        jb11.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1 = array.get(0);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2, 3, String.valueOf("0"));
                number1 = sb.toString();
                array.set(0, number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for (int q = 0; q < 5; q++)
                        bre1.write(array.get(q) + "\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        /* meal2*/
        /*---------------------------------------------------------------------------*/
        JLabel MealMenu2 = new JLabel();
        MealMenu2.setBounds(0, 120, 900, 100);
        MealMenu2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        MealPanel.add(MealMenu2);
        JLabel StandardIcon2 = new JLabel(new ImageIcon("StandardMeal2.jpg"));
        StandardIcon2.setBounds(60, 20, 70, 70);
        MealMenu2.add(StandardIcon2);
        JLabel StandardName2 = new JLabel("<html><body>Standard<br>Meal</body></html>");
        StandardName2.setBounds(150, 30, 60, 40);
        MealMenu2.add(StandardName2);


        if(state[1]==1){
            JLabel meal21=new JLabel("FOR SALE");
            meal21.setBounds(250, 40, 60, 40);
            MealMenu2.add(meal21);
        }else {
            JLabel meal20=new JLabel("SOLD OUT");
            meal20.setBounds(250, 40, 60, 40);
            MealMenu2.add(meal20);
        }

        /* modify the number*/
        /*---------------------------------------------------------------------------*/
        JFormattedTextField num2 = new JFormattedTextField();
        num2.setBackground(new Color(226, 221, 244));
        num2.setForeground(Color.GRAY);
        num2.setToolTipText("num");
        num2.setText(num[1]);
        num2.setBounds(330, 50, 60, 20);
        MealMenu2.add(num2);

        num2.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (num2.getText().equals(num[1]))
                    num2.setText(null);
                num2.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        num2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String number2= array.get(1);
                StringBuilder sb = new StringBuilder(number2);
                System.out.println(num2.getText());
                sb.replace(4,8,String.valueOf(num2.getText()));
                number2=sb.toString();
                array.set(1,number2);

                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        /* add and remove */
        /*---------------------------------------------------------------------------*/
        JButton jb21 = new JButton("Added");
        jb21.setBounds(430, 50, 100, 20);
        MealMenu2.add(jb21);

        JButton jb20 = new JButton("REMOVE");
        jb20.setBounds(550, 50, 100, 20);
        MealMenu2.add(jb20);

        jb21.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number2= array.get(1);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(2,3,String.valueOf("1"));
                number2=sb.toString();
                array.set(1,number2);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        jb20.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1 = array.get(1);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2, 3, String.valueOf("0"));
                number1 = sb.toString();
                array.set(1, number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for (int q = 0; q < 5; q++)
                        bre1.write(array.get(q) + "\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        /* meal3*/
        /*---------------------------------------------------------------------------*/
        JLabel MealMenu3 = new JLabel();
        MealMenu3.setBounds(0, 220, 900, 100);
        MealMenu3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        MealPanel.add(MealMenu3);
        JLabel StandardIcon3 = new JLabel(new ImageIcon("StandardMeal3.jpg"));
        StandardIcon3.setBounds(60, 20, 70, 70);
        MealMenu3.add(StandardIcon3);
        JLabel StandardName3 = new JLabel("<html><body>Standard<br>Meal</body></html>");
        StandardName3.setBounds(150, 30, 60, 40);
        MealMenu3.add(StandardName3);


        if(state[2]==1){
            JLabel meal31=new JLabel("FOR SALE");
            meal31.setBounds(250, 40, 60, 40);
            MealMenu3.add(meal31);
        }else {
            JLabel meal30=new JLabel("SOLD OUT");
            meal30.setBounds(250, 40, 60, 40);
            MealMenu3.add(meal30);
        }

        /* modify the number*/
        /*---------------------------------------------------------------------------*/
        JFormattedTextField num3 = new JFormattedTextField();
        num3.setBackground(new Color(226, 221, 244));
        num3.setForeground(Color.GRAY);
        num3.setToolTipText("num");
        num3.setText(num[2]);
        num3.setBounds(330, 50, 60, 20);
        MealMenu3.add(num3);

        num3.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (num3.getText().equals(num[2]))
                    num3.setText(null);
                num3.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        num3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String number2= array.get(2);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(4,8,String.valueOf(num3.getText()));
                number2=sb.toString();
                array.set(2,number2);

                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        /* add and remove */
        /*---------------------------------------------------------------------------*/
        JButton jb31 = new JButton("Added");
        jb31.setBounds(430, 50, 100, 20);
        MealMenu3.add(jb31);

        JButton jb30 = new JButton("REMOVE");
        jb30.setBounds(550, 50, 100, 20);
        MealMenu3.add(jb30);

        jb31.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number2= array.get(2);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(2,3,String.valueOf("1"));
                number2=sb.toString();
                array.set(2,number2);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        jb30.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1 = array.get(2);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2, 3, String.valueOf("0"));
                number1 = sb.toString();
                array.set(2, number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for (int q = 0; q < 5; q++)
                        bre1.write(array.get(q) + "\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });


        /* meal4*/
        /*---------------------------------------------------------------------------*/
        JLabel MealMenu4 = new JLabel();
        MealMenu4.setBounds(0, 320, 900, 100);
        MealMenu4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        MealPanel.add(MealMenu4);
        JLabel StandardIcon4 = new JLabel(new ImageIcon("StandardMeal4.jpg"));
        StandardIcon4.setBounds(60, 20, 70, 70);
        MealMenu4.add(StandardIcon4);
        JLabel StandardName4 = new JLabel("<html><body>Characteristic<br>cuisine</body></html>");
        StandardName4.setBounds(150, 30, 60, 40);
        MealMenu4.add(StandardName4);


        if(state[3]==1){
            JLabel meal41=new JLabel("FOR SALE");
            meal41.setBounds(250, 40, 60, 40);
            MealMenu4.add(meal41);
        }else {
            JLabel meal40=new JLabel("SOLD OUT");
            meal40.setBounds(250, 40, 60, 40);
            MealMenu4.add(meal40);
        }

        /* modify the number*/
        /*---------------------------------------------------------------------------*/
        JFormattedTextField num4 = new JFormattedTextField();
        num4.setBackground(new Color(226, 221, 244));
        num4.setForeground(Color.GRAY);
        num4.setToolTipText("num");
        num4.setText(num[3]);
        num4.setBounds(330, 50, 60, 20);
        MealMenu4.add(num4);

        num4.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (num4.getText().equals(num[3]))
                    num4.setText(null);
                num4.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        num4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String number2= array.get(3);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(4,8,String.valueOf(num4.getText()));
                number2=sb.toString();
                array.set(3,number2);

                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        /* add and remove */
        /*---------------------------------------------------------------------------*/
        JButton jb41 = new JButton("Added");
        jb41.setBounds(430, 50, 100, 20);
        MealMenu4.add(jb41);

        JButton jb40 = new JButton("REMOVE");
        jb40.setBounds(550, 50, 100, 20);
        MealMenu4.add(jb40);

        jb41.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number2= array.get(3);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(2,3,String.valueOf("1"));
                number2=sb.toString();
                array.set(3,number2);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        jb40.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1 = array.get(3);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2, 3, String.valueOf("0"));
                number1 = sb.toString();
                array.set(3, number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for (int q = 0; q < 5; q++)
                        bre1.write(array.get(q) + "\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });


        /* meal5*/
        /*---------------------------------------------------------------------------*/
        JLabel MealMenu5 = new JLabel();
        MealMenu5.setBounds(0, 420, 900, 100);
        MealMenu5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
        MealPanel.add(MealMenu5);
        JLabel StandardIcon5 = new JLabel(new ImageIcon("StandardMeal5.jpg"));
        StandardIcon5.setBounds(60, 20, 70, 70);
        MealMenu5.add(StandardIcon5);
        JLabel StandardName5 = new JLabel("<html><body>Halal<br>meal</body></html>");
        StandardName5.setBounds(150, 30, 60, 40);
        MealMenu5.add(StandardName5);


        if(state[4]==1){
            JLabel meal51=new JLabel("FOR SALE");
            meal51.setBounds(250, 40, 60, 40);
            MealMenu5.add(meal51);
        }else {
            JLabel meal50=new JLabel("SOLD OUT");
            meal50.setBounds(250, 40, 60, 40);
            MealMenu5.add(meal50);
        }

        /* modify the number*/
        /*---------------------------------------------------------------------------*/
        JFormattedTextField num5 = new JFormattedTextField();
        num5.setBackground(new Color(226, 221, 244));
        num5.setForeground(Color.GRAY);
        num5.setToolTipText("num");
        num5.setText(num[4]);
        num5.setBounds(330, 50, 60, 20);
        MealMenu5.add(num5);

        num5.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                if (num5.getText().equals(num[4]))
                    num5.setText(null);
                num5.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        num5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String number2= array.get(4);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(4,8,String.valueOf(num5.getText()));
                number2=sb.toString();
                array.set(4,number2);

                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        });

        /* add and remove */
        /*---------------------------------------------------------------------------*/
        JButton jb51 = new JButton("Added");
        jb51.setBounds(430, 50, 100, 20);
        MealMenu5.add(jb51);

        JButton jb50 = new JButton("REMOVE");
        jb50.setBounds(550, 50, 100, 20);
        MealMenu5.add(jb50);

        jb51.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number2= array.get(4);
                StringBuilder sb = new StringBuilder(number2);
                sb.replace(2,3,String.valueOf("1"));
                number2=sb.toString();
                array.set(4,number2);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for(int q=0;q<5;q++)
                        bre1.write(array.get(q)+"\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        jb50.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                String number1 = array.get(4);
                StringBuilder sb = new StringBuilder(number1);
                sb.replace(2, 3, String.valueOf("0"));
                number1 = sb.toString();
                array.set(4, number1);
                File f1 = new File("MealState.txt");
                FileWriter fre1 = null;
                try {
                    fre1 = new FileWriter(f1);
                    BufferedWriter bre1 = new BufferedWriter(fre1);
                    for (int q = 0; q < 5; q++)
                        bre1.write(array.get(q) + "\t\n");
                    bre1.flush();

                    bre1.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }
        });


        /**************** create a back button *****************/
        JButton jb1 = new JButton("Back");
        jb1.setBounds(450, 600, 80, 20);
        MealPanel.add(jb1);
        /* Return to previous page */
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Crew crewpage = null;
                try {
                    crewpage = new Crew();
                    crewpage.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
        });

    }
}
