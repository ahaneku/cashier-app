package billprinter;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;


import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

public class EntryPage extends JPanel{
    
    private final JTextField GuestName;
    private final JTextField Amount;
    private JTextField Room;
    private final JButton Print;
    private final JButton LogOut;
    private static Box detailBox;
    
    private BillPage billPage;
    private static Graphics Content = null;
    
    public static final Font hardFont = new Font(Font.SERIF, Font.BOLD, 18);
    public static final Font plainFont = new Font(Font.SERIF, Font.PLAIN, 15);
    public static final Font telFont = new Font(Font.SERIF, Font.PLAIN, 10);
    public static final Font bodyFont = new Font(Font.SERIF, Font.PLAIN, 14);
    
    
    EntryPage(){
        setLayout(new BorderLayout(5, 5));
        
         
                                                              // instantiated billPage
        billPage = new BillPage();
      //  billPage.setBorder(BorderFactory.createTitledBorder("TELLER"));
        
        //entry box..
        detailBox = Box.createVerticalBox();
        detailBox.setBackground(LoginPage.loginBackground);
        
        JPanel guestNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        guestNamePanel.setBackground(LoginPage.loginBackground);
        JLabel guestname = new JLabel("GUEST NAME ");
        guestname.setForeground(Color.WHITE);
        GuestName = new JTextField(15);
        GuestName.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        guestNamePanel.add(guestname);
        guestNamePanel.add(GuestName);
        
        
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        amountPanel.setBackground(LoginPage.loginBackground);
        JLabel amount = new JLabel("AMOUNT          ");
        amount.setForeground(Color.WHITE);
        Amount = new JTextField(15);
        Amount.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Amount.addCaretListener(CalculatePage.caretListen);
        amountPanel.add(amount);
        amountPanel.add(Amount);
        
        
        
        JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        roomPanel.setBackground(LoginPage.loginBackground);
        JLabel room = new JLabel("ROOM               ");
        room.setForeground(Color.WHITE);
        Room = new JTextField(15);
        Room.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        roomPanel.add(room);
        roomPanel.add(Room);
        
        
        JPanel printPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        printPanel.setBackground(LoginPage.loginBackground);
        Print = new JButton("Print");
        Print.setBackground(LoginPage.loginBackground);
        Print.setForeground(Color.WHITE);
        printPanel.add(Print);
        
        
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoutPanel.setBackground(LoginPage.loginBackground);
        LogOut = new JButton("LogOut");
        LogOut.setBackground(LoginPage.loginBackground);
        LogOut.setForeground(Color.WHITE);
        logoutPanel.add(LogOut);
        
                                                                                            //  Input page control...
       detailBox.add(Box.createRigidArea(new Dimension(detailBox.getWidth(), 80)));
        detailBox.setBorder(BorderFactory.createTitledBorder("BILL INFO"));
         detailBox.add(guestNamePanel);
    //    detailBox.add(Box.createRigidArea(new Dimension(detailBox.getWidth(), 10)));
        detailBox.add(amountPanel);
    // detailBox.add(Box.createRigidArea(new Dimension(detailBox.getWidth(), 10)));
        detailBox.add(roomPanel);
    //    detailBox.add(Box.createRigidArea(new Dimension(detailBox.getWidth(), 10)));
        detailBox.add(printPanel);
       detailBox.add(Box.createRigidArea(new Dimension(detailBox.getWidth(), 80)));
       detailBox.add(logoutPanel);

//   detailBox.setBounds(10, 100, 450, 250);
        
        
        
                                                                        //WestPage ... EntryPage
        JPanel West = new JPanel(new BorderLayout());
        West.setBackground(LoginPage.loginBackground);
        
        
        InnerNorth innerNorth = new InnerNorth();
        InnerSouth innerSouth = new InnerSouth();
        
        
        West.add(innerNorth, BorderLayout.NORTH);
        West.add(innerSouth, BorderLayout.SOUTH);
        West.add(detailBox, BorderLayout.CENTER);
        
        //adds pages to panel
        add(West , BorderLayout.WEST);
        add(billPage, BorderLayout.CENTER);
        
        
        //listeners for caret update
        caretListener caretListens = new caretListener();
        
        GuestName.addCaretListener(caretListens);
        Amount.addCaretListener(caretListens);
        Room.addCaretListener(caretListens);
        
        
    }
  
                                                        // returns an instance of PrintButton...
    public JButton getPrint(){
        return Print;
    }
    
                                                        // returns an instance of LogOut button...
    public JButton getLogOut(){
        return LogOut;
    }
    
    
    public static Graphics getContent(){
    
        return Content;
    }
    
    
    private static class InnerSouth extends JPanel{
        
            InnerSouth(){
            setBackground(new Color(10, 50, 90));
            }
            
            @Override
            protected void paintComponent(Graphics g){
            super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.draw3DRect(0, 0, 240, 8, true);
                g.draw3DRect(243, 2, 25, 6, true);
                g.draw3DRect(271, 0, 20, 8, true);
            }
        }
    private static class InnerNorth extends JPanel{
        
            InnerNorth(){
            setBackground(new Color(10, 50, 90));
            }
            
            @Override
            protected void paintComponent(Graphics g){
            super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.draw3DRect(0, 0, 15, 8, true);
                g.draw3DRect(18, 0, 25, 6, true);
                g.draw3DRect(46, 0, 245, 8, true);
                                                                                                                                                                                                                                                                      
            }
        }
    
    
    
    public EntryPage.BillPage getBillPage(){
    
        return billPage;
    }
    
    
    
    private class caretListener implements CaretListener{
    
        @Override
        public void caretUpdate(CaretEvent ce){
        
                                                                                     //updates Cash receipt...
            if(ce.getSource().equals(GuestName)){
                
                billPage.setGuestName(GuestName.getText());
                
            
            }else if(ce.getSource().equals(Amount)){
                
                String data = Amount.getText();
               
                if(!data.equals("")){
                    
                    if(Integer.parseInt(""+Double.valueOf(data).intValue()) >= Integer.MAX_VALUE){
                            Amount.setEditable(false);
                    }else{
                        Amount.setEditable(true);
                        billPage.setAmount(format(data ) );
                    }
                    
                    
                }else{
                
                    billPage.setAmount(data);
                }
                
                
                
            }else if(ce.getSource().equals(Room)){
            
                billPage.setRoom(Room.getText());
                
            }else{
            
                return;
            }
            
            repaint();
        }
    }
    
    
    
    public String format(String data){
    
        
        return CalculatePage.numberFormat.format(Integer.parseInt(data));
    }
   
    
    
    public static class BillPage extends JPanel{
    
    private JTextField GuestName;
    private JTextField Amount;
    private JTextField Room;
    
    
    private String guestName;
    private String amount;
    private String room;
    private StringBuilder guestNameBuffer;
    private StringBuilder amountBuffer;
    private StringBuilder roomBuffer;
    
    
    BillPage(){
    
          
          
        setBackground(Color.WHITE);
        
        guestNameBuffer = new StringBuilder();
        guestNameBuffer.append(BillPrinter.GN);
        amountBuffer = new StringBuilder();
        amountBuffer.append(BillPrinter.AMT);
        roomBuffer = new StringBuilder();
        roomBuffer.append(BillPrinter.RM);
    }
    
    
    public void setGuestName(String guestName){
        this.guestName = guestName;
        guestNameBuffer = new StringBuilder();
        guestNameBuffer.append(BillPrinter.GN);
        guestNameBuffer.append(guestName);
    }
    
    
    public void setAmount(String amount){
        this.amount = amount;
        amountBuffer = new StringBuilder();
        amountBuffer.append(BillPrinter.AMT);
        amountBuffer.append(this.amount);
    }
    
    
    public void setRoom(String room){
        this.room = room;
        roomBuffer = new StringBuilder();
        roomBuffer.append(BillPrinter.RM);
        roomBuffer.append(room);
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g){
    super.paintComponent(g);
        
    g.setColor(Color.BLACK);
    g.setFont(plainFont);
    
    g.drawRect(10, 10, 250, 345);
    g.drawString(BillPrinter.UON, 45, 30);
    g.setFont(hardFont);
    g.drawString(BillPrinter.GEL, 40, 55);
    g.setFont(telFont);
    g.drawString(BillPrinter.TEL, 60, 70);
    g.setFont(hardFont);
    g.drawString(BillPrinter.CR, 65, 100);
    g.setFont(bodyFont);
    g.drawString(guestNameBuffer.toString(), 20, 150);
    g.drawString(amountBuffer.toString(), 20, 170);
    g.drawString(roomBuffer.toString(), 20, 190);
    g.drawString(BillPrinter.getDate(), 40, 300);   
    g.drawString(BillPrinter.ISB+BillPrinter.getUserName(), 35, 325);
    
    
    System.out.println(g.toString());
    
                                                            //sets Content to print out...
    Content = g;
    
    }
    
    
    
    }
}
