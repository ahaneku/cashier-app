
package billprinter;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;


import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

import java.util.Date;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;


import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;


public class BillPrinter extends JFrame{

    public static final String UON = "UNIVERSITY OF NIGERIA";
    public static final String GEL = "General Enterprises Ltd.";
    public static final String TEL = "Tel : 08162115951, 08021377336";
    public static final String ISB = "ISSUED BY ";
    public static final String CR = "CASH RECEIPT";
    public static final String DT = "Date : ";
    public static final String GN = "GUEST NAME : ";
    public static final String AMT = "AMOUNT : ";
    public static final String RM = "ROOM : ";
    
    
    public static final String INVALID_PASSWORD = "INVALID PASSWORD";
    public static final String USER_ADDED = "USER ADDED";
    public static final String EMPTY_BOX = "USERNAME OR PASSWORD FIELD CAN NOT BE EMPTY";
    
    
    public static Dimension screen;
    public static final DateFormat datePreview = new SimpleDateFormat("dd MMMMM, yyyy");
    public static final DateFormat TIME = new SimpleDateFormat("HH:mm ");
    public static  Date date;
    public static  Date time;
    
    
    
                                                                            //main method here...
    public static void main(String[] args) {
        BillPrinter ui = new BillPrinter();
        screen = ui.getToolkit().getScreenSize();
        System.out.println(BillPrinter.screen.getHeight()+"...."+BillPrinter.screen.getWidth());
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.setSize(ui.getToolkit().getScreenSize());
        ui.setResizable(false);
        ui.setVisible(true);
        
    }
    
    
    public static final String laf = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    public static final String Wlaf = "import com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    
    
    
    private final LoginPage loginPage;
    private final EntryPage entryPage;
    private Printer printer;
    private final CalculatePage calculatePage;
    
    
    private final JButton SignIn;
    private final JButton AddUser;
    private final JButton LogOut;
    private final JButton Print;
    private final JButton setUp;
    
    
    public static JLabel Info;
    private JButton calculateTotal;
    
    
    private static String UserName = "";
    
    private static Thread calculateTotalTask = null; 
    
    BillPrinter(){
    super("Bill Printer");
    setLayout(new BorderLayout());
    
    
    
                                                                    //sets look and feel for the UI...
    try{
        UIManager.setLookAndFeel(laf);
    }
    catch(ClassNotFoundException | InstantiationException | IllegalAccessException 
                | UnsupportedLookAndFeelException ie){}
    
    
    
                                                                       //Info Control...
    JPanel bottomPanel = new JPanel(new BorderLayout());
    JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    infoPanel.setBackground(LoginPage.loginBackground);
    Info = new JLabel();
    Info.setForeground(Color.RED);
    
    
        
        
                                                                        //ic for setting
    Icon settingsIc = new ImageIcon("icons\\settings.png");
    
    
    setUp = new JButton(settingsIc);
    setUp.setBackground(LoginPage.loginBackground);
    setUp.setPreferredSize(new Dimension(32, 32));
    setUp.setBorderPainted(false);
    infoPanel.add(Info);
    bottomPanel.add(infoPanel, BorderLayout.CENTER);
    bottomPanel.add(setUp, BorderLayout.WEST);
   
    
    loginPage = new LoginPage();
    entryPage = new EntryPage();
    printer = new Printer();
    calculatePage = new CalculatePage();
    
    
        
    
    
    SignIn = loginPage.getSignIn();
    AddUser = loginPage.getAddUser();
    Print = entryPage.getPrint();
    LogOut = entryPage.getLogOut();
    calculateTotal = calculatePage.getTotalCalculateBut();
                                                        
                                                                      // Log out button listener here...
    LogOut.addActionListener((ActionEvent)->{
        
        UserName = "";
        super.remove(entryPage);
        
        setSize(screen);
        this.setLocation(0, 0);
        
        super.add(loginPage, BorderLayout.CENTER);
        super.add(bottomPanel, BorderLayout.SOUTH);
        
    });
    
                                                            
                                                                      //signButton listener here...
    SignIn.addActionListener((Actionevent)->{
        
        UserName = loginPage.getUserName();
        
        if(UserName.equals("C")){
            super.remove(loginPage);
            super.remove(bottomPanel);
            
            setSize(1300, 700);
            setSize(screen);
           // setLocationRelativeTo(null);  
            
            System.out.println("In between super functions in AignIn button");
            
            super.add(calculatePage, BorderLayout.CENTER);
            
            System.out.println("Done with super functions in SignIn button");
            
        }else{
            
            super.remove(loginPage);
            super.remove(bottomPanel);
       // if(UIManager.getLookAndFeel().equals(laf)){
            
            setSize(580, 395);
            setLocationRelativeTo(null);
       // }else{
               // this.setSize(550, 400);
      // }
        
       
        super.add(entryPage, BorderLayout.CENTER);
            
        }
        
        
        
    });
    
    
    
    
                                                                   //calculates the total Amount made
    calculateTotal.addActionListener((ActionEvent)->{
    
        calculateTotalTask = new Thread(new Runnable(){
        
            @Override
            public void run(){
                double tempTotal = CalculatePage.calculateTotal();
                CalculatePage.setTotalSum(CalculatePage.numberFormat.format(tempTotal));
                
                calculateTotalTask = null;
            }
            
        });
        
        calculateTotalTask.start();
        
    });
    
    
    
    
                                                                    //print button listener here...
     Print.addActionListener((ActionEvent)->{
     
         
         Printer.print();
         
     });
    
     
                                                                    //setUp button
     setUp.addActionListener((ActionEvent)->{
     
         Printer.setDefaultPrinter();
     });
     
     
     
    
    
    add(loginPage, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
    
    }
    
    
    public static void setInfo(String info){
    Info.setText(info);
    
    }
    
    
    public static void setUserName(String userName){
    
        UserName = userName;
    }
    
    
    
    public static String getUserName(){
    
        return UserName;
    }
    
    
  public static String getTime(){
      
      time = new Date();  
      String timeS = TIME.format(time);
      
      System.out.println(timeS);
      
      return timeS;
  }
   
  
  
  public static String getDate(){
  
      date = new Date();
      String dateString = datePreview.format(date);
    
      return DT + dateString;
  }
    
}
