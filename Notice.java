import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class Notice {

    public Notice() {
        JFrame jf = new JFrame("Cell Combine Table");
        JTable cTable = null;
        try {
            cTable = getTable2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        jf.getContentPane().add(new JScrollPane(cTable));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setBounds(400, 300, 1060, 550);
        jf.setVisible(true);
    }

    private static CombineTable getTable2() throws Exception {

        File f = new File("meal.txt");
        FileReader fre = new FileReader(f);
        BufferedReader bre = new BufferedReader(fre);
        String str = "";
        int o=0;
        int p=0;
        String []food1=new String[10];
        String []food2=new String[10];
        while((str = bre.readLine()) != null){
            if(str.charAt(9)=='1')
            {
                food1[o]=str;
                o++;
            }
            else if(str.charAt(9)=='2')
            {
                food2[p]=str;
                p++;

            }
        }
        System.out.println(p);
        String[][] datas = new String[10][6];
        for (int i = 0; i < datas.length; i++) {
            String[] data = datas[i];
            for (int j = 0; j < data.length; j++) {
                data[j] = "";
            }
            if(i<o){
                data[0] = "Standard_1";
                data[1] =String.valueOf(o);
                String s=food1[i];
                data[2] = s.substring(13,15);
                data[3] = s.substring(16,19);

            }

            else if(i<o+p&&i>=o) {
                data[0]="Standard_2";
                data[1] =String.valueOf(p);
                String s=food2[i-o];
                data[2] = s.substring(13,15);
                data[3] = s.substring(16,19);
            }

        }

        CombineData m = new CombineData(datas, 0, 1);
        DefaultTableModel tm = new DefaultTableModel(datas, new String[]{"meal", "number", "seat_No", "passenger_name"});
        CombineTable cTable = new CombineTable(m, tm);

        TableColumnModel columnModel = cTable.getColumnModel();
        for (Integer integer : m.combineColumns) {
            TableColumn column = columnModel.getColumn(integer);
            column.setCellRenderer(new CombineColumnRender());
            column.setWidth(80);
            column.setMaxWidth(80);
            column.setMinWidth(50);
        }

        cTable.setCellSelectionEnabled(true);
        return cTable;
    }

   
}
