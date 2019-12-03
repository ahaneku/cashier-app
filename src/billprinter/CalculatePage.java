package billprinter;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


import javax.swing.BorderFactory;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;



                                                //begining of class here
public class CalculatePage extends JPanel {
    
    private double [] Sum = null;
    
    private static JTable [] Table;
    private static JButton calculateTotal;
    private static JTextField totalSum;
    public static JScrollPane scrollPane;
    
    
    public static JButton [] Calculate;
    public static JTextField [] Balance;
    public static JLabel [] Total;
    public static Box tableBox;
    
    
    
    private static Thread calculateTask = null;
    public  static final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    public static  CaretListen caretListen;
    
                                            //Class Constructor here...
    CalculatePage (){
    
        setLayout(new BorderLayout(15, 15));
        setBackground(Color.GRAY);
        
        
                                                                          //total sum control
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    topPanel.setBackground(Color.GRAY);
    calculateTotal = new JButton("CALCULATE");
    calculateTotal.setBackground(LoginPage.loginBackground);
    calculateTotal.setForeground(Color.WHITE);
    totalSum = new JTextField(15);
    totalSum.setHorizontalAlignment(JTextField.RIGHT);
    totalSum.setBackground(Color.WHITE);
    totalSum.setForeground(Color.BLACK);
    totalSum.setEditable(false);
    totalSum.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    topPanel.add(calculateTotal);
    topPanel.add(totalSum);
    topPanel.setBorder(BorderFactory.createTitledBorder("CALCULATE TOTAL AMOUNT"));
    
    
        
       
                                                     //main box
        tableBox = Box.createHorizontalBox();
        tableBox.setAlignmentY(Box.RIGHT_ALIGNMENT);
         
        
        Table = new JTable[3];
        Calculate = new JButton[3];
        Balance = new JTextField[3];
        Total = new JLabel[3];
        
        
        for(int n = 0; n < 3; n++){
            Table[n] = CalculatePage.createTable(n);
            CalculatePage.addTable(Table[n], n);
        }
        
        
      
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.GRAY);
        tablePanel.add(tableBox, BorderLayout.CENTER);
        
        
                                                               //to hold calculate panel...
        scrollPane = new JScrollPane(tablePanel);
        scrollPane.setBackground(LoginPage.loginBackground);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
       
        
        caretListen = new CaretListen();
        
        
        totalSum.addCaretListener(caretListen);
        
        
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
    }
    
    
    
                                                                     //method to create a new table
    public static JTable createTable(int which){
    
        mTableModel tm = new mTableModel();
        JTable table = new JTable(tm);
        
        
         Font panFont = new Font(table.getFont().getFontName(), Font.BOLD, 15);
        
        JTableHeader tableHead = table.getTableHeader();
        tableHead.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        tableHead.setFont(panFont);
        
        
        table.setRowHeight(25);
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.setEnabled(false);
        table.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        
        
        System.out.println("create table");
        
        return table;
    }
    
    
                                                                        //method to add tables
    public static void addTable(JTable table, int which){
    
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5));
        topPanel.setBackground(LoginPage.loginBackground);
        topPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        JLabel dateLabel = new JLabel(BillPrinter.DT);
        dateLabel.setForeground(LoginPage.loginBackground);
        topPanel.add(dateLabel);
        
        
        
        
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        bottomPanel.setBackground(Color.GRAY);
      //  bottomPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        
     /*   Calculate[buts] = new JButton("CALCULATE");
        Calculate[buts].setBackground(LoginPage.loginBackground);
        Calculate[buts].setForeground(Color.WHITE);  */
        
        Total[which] = new JLabel("TOTAL");
        Font tFont = new Font(Total[which].getFont().getFontName(), Font.BOLD, 15);
        Total[which].setFont(tFont);
        Total[which].setForeground(Color.BLACK);
        
        Balance[which] = new JTextField(9);
        Balance[which].setHorizontalAlignment(JTextField.RIGHT);
        Balance[which].setEditable(false);
        Balance[which].setBackground(Color.WHITE);
        Balance[which].setForeground(Color.BLACK);
        Balance[which].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED)); 
        Balance[which].addCaretListener(caretListen);
        
        
        bottomPanel.add(Total[which]);
        bottomPanel.add(Balance[which]);
        
        
                                                                                //Task calculates total and sets the value of total
        
        calculateTask = new Thread(new Runnable(){
        
            @Override
            public void run(){
             double tempSum = calculate(which);
             
                          
             setBalance(numberFormat.format(tempSum), which);
               
             calculateTask = null;
            }
        });
        
        calculateTask.start();
        
        
      //  buts++;
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.GRAY);
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        Font panFont = new Font(panel.getFont().getFontName(), Font.BOLD, 15);
        
        if(which == 1){
                 panel.setBorder(BorderFactory.createTitledBorder(null, "DATE : " + "   27 may, 2016"+"        EMPLOYEE :   " + "Emeka Onuoha"
                , TitledBorder.RIGHT, TitledBorder.ABOVE_TOP, panFont, Color.BLACK));
        }
        else if(which == 2){
                 panel.setBorder(BorderFactory.createTitledBorder(null, "DATE : " + "   28 may, 2016"+"        EMPLOYEE :   " + "Emeka Onuoha"
                , TitledBorder.RIGHT, TitledBorder.ABOVE_TOP, panFont, Color.BLACK));
        }else{
             panel.setBorder(BorderFactory.createTitledBorder(null, "DATE : " + "   26 may, 2016"+"        EMPLOYEE :   " + "Emeka Onuoha"
                , TitledBorder.RIGHT, TitledBorder.ABOVE_TOP, panFont, Color.BLACK));
        }
       
        
        
        tableBox.add(Box.createRigidArea(new Dimension(25, tableBox.getHeight())), 0);
        tableBox.add(panel, 1);
        tableBox.add(Box.createRigidArea(new Dimension(25, tableBox.getHeight())), 2);
        tableBox.validate();
    }
    
                                                                    //returns caretListener for width manupulation
    public static CaretListen getCaretListener(){
    
        return caretListen;
    }
 
    public class CaretListen implements CaretListener {
    
        @Override
        public void caretUpdate(CaretEvent ce){
            if( ((JTextField)ce.getSource()).getText().toCharArray().length >=  ((JTextField)ce.getSource()).getColumns()){
                
                ((JTextField)ce.getSource()).setColumns(((JTextField)ce.getSource()).getText().toCharArray().length);
                
                System.out.println("field column count : "+((JTextField)ce.getSource()).getText().toCharArray().length);
                System.out.println("initial column count : "+((JTextField)ce.getSource()).getColumns());
                
                tableBox.validate();
            }
        }
        
    }
    
                                                                        //method to set a balance for a table
    public static void setBalance(String balance, int which){
    
        Balance[which].setText(balance);
    }
    
                                                                        //method to getBalance for a table
    public static long getBalance(int which){
        
        String sBalance = Balance[which].getText();
        
        return Integer.parseInt(sBalance);
    }
    
                              
                                                                   //method to retrive calculateTotal button
    public JButton getTotalCalculateBut(){
        return calculateTotal;
    }
    
    
                             
    //method to calculate sum
    public static double calculate(int which){
         double sum = 0;
        
         TableModel tableModel = Table[which].getModel();
         
         for(int n = 0; n < tableModel.getRowCount(); n++){
             
             String sSum = (String)tableModel.getValueAt(n, 1);
             
             sum += Double.parseDouble(sSum);
         }
        
         return sum;
    } 
    
                                                                    //method to calculate total Sum;
    public static double calculateTotal(){
       
        double tSum = 0;
        
        for(int n = 0; n < Balance.length; n++){
        
                tSum += getBalance(n);
        }
        
        return tSum;
    }
    
                                                                   //method to setTotalSum
    public static void setTotalSum(String tSum){
        totalSum.setText(tSum);
    }
    
    
    
    
                                                                             //Table model here...
    private static class mTableModel extends DefaultTableModel{
    
        private static final String GUEST = "GUEST";
        private static final String AMOUNT = "AMOUNT";
        private static final String ROOM = "ROOM";
        private static final String TIME = "TIME";
        
                
        
        mTableModel(){
            super(new String[][]{{"Chijioke", "1500250", "224"},
                {"Uguru", "15002507877", "324"},
                {"Chijioke", "1500250778877", "224"},
                {"Uguru", "15002508877887", "324"},
                {"Chijioke", "1500250887777", "224"},
                {"Uguru", "150025087777", "324"},
                {"Chijioke", "15002508877777", "224"},
                {"Uguru", "1500250877877", "324"},
                {"Chijioke", "1500250877777", "224"},
                {"Uguru", "15002507887777777", "324"},
                {"Chijioke", "1500250766766667", "224"},
                {"Uguru", "1500250", "324"},
                {"Chijioke", "1500250", "224"},
                {"Uguru", "1500250", "324"},
                {"Chijioke", "1500250", "224"},
                {"Uguru", "1500250", "324"},
                {"Chijioke", "1500250", "224"},
                {"Uguru", "1500250", "324"},
                {"Chijioke", "1500250", "224"},
                {"Uguru", "1500250", "324"},
                {"Chijioke", "1500250", "224"},
                {"Uguru", "1500250", "324"}
            }, new String[]{GUEST, AMOUNT, ROOM, TIME});
            
        }
    }
    
    
}
