package billprinter;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;


public class LoginPage extends JPanel {
    
    public static final String [] Genders = {"Male", "Female"};
    public static final Color loginBackground = new Color(10, 50, 100); 
    
    private final JTextField UserName;
    private final JPasswordField Password;
    private final JButton SignIn;
    private final JButton SignUp;
    private final JButton AddUser;
    private final JButton Back;
   // private final JCheckBox Admin;
   // private final JButton LogIn;
    private final JComboBox<String> Gender;
    
    private final JPanel signInPanel;
    private final JPanel savePanel;
    
                                                //  database thread...
    private Thread addUserThread;
    
    LoginPage(){
        
        setBackground(loginBackground);
       
        //LoginBox Layout...
        Box loginBox = Box.createVerticalBox();
        
        //user name panel...
        JPanel userNamePanel = new JPanel(new BorderLayout(10, 20));
        userNamePanel.setBackground(loginBackground);
        UserName = new JTextField(15);
        UserName.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel username = new JLabel("USERNAME  ");
        username.setForeground(Color.WHITE);
        userNamePanel.add(username, BorderLayout.WEST);
        userNamePanel.add(UserName, BorderLayout.CENTER);
        
        
        //password panel...
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 20));
        passwordPanel.setBackground(loginBackground);
        Password = new JPasswordField(12);
        Password.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        JLabel password = new JLabel("PASSWORD ");
        password.setForeground(Color.WHITE);
        passwordPanel.add(password , BorderLayout.WEST);
        passwordPanel.add(Password, BorderLayout.CENTER);
        
        //gender panel...
        JPanel genderPanel = new JPanel(new BorderLayout(10, 20));
        genderPanel.setBackground(loginBackground);
        Gender = new JComboBox(Genders);
        JLabel gender = new JLabel("GENDER");
        gender.setForeground(Color.WHITE);
        genderPanel.add(gender, BorderLayout.WEST);
        genderPanel.add(Gender, BorderLayout.EAST);
        genderPanel.setVisible(false);
        
        
        
        //signIn panel...
        signInPanel = new JPanel(new GridLayout(1, 2));
        signInPanel.setBackground(loginBackground);
        savePanel = new JPanel(new GridLayout(1, 2));
        savePanel.setBackground(loginBackground);
        SignIn = new JButton("SignIn");
        SignUp = new JButton("SignUp");
        AddUser = new JButton("Add User");
        Back = new JButton("<<");
        
        SignIn.setBackground(loginBackground);
        SignIn.setForeground(Color.WHITE);
        SignUp.setBackground(loginBackground);
        SignUp.setForeground(Color.WHITE);
        AddUser.setBackground(loginBackground);
        AddUser.setForeground(Color.WHITE);
        Back.setBackground(loginBackground);
        Back.setForeground(Color.WHITE);
        
        
        Back.addActionListener((ActionEvent)->{
            
            savePanel.setVisible(false);
            genderPanel.setVisible(false);
            signInPanel.setVisible(true);
            
        });
        
        AddUser.addActionListener((ActionEvent)->{
        
            addUser();
            
            System.out.println("Added User...");
            
            savePanel.setVisible(false);
            genderPanel.setVisible(false);
            signInPanel.setVisible(true);
        });
        
        
        
        SignUp.addActionListener((ActionEvent)->{
            
            signInPanel.setVisible(false);
            genderPanel.setVisible(true);
            savePanel.setVisible(true);
            
            
        });
        
        
        signInPanel.add(SignIn);
        signInPanel.add(SignUp);
        savePanel.add(Back);
        savePanel.add(AddUser);
        savePanel.setVisible(false);
        
        
       loginBox.add(Box.createRigidArea(new Dimension(loginBox.getWidth(), 300)));
       loginBox.add(userNamePanel);
       loginBox.add(Box.createRigidArea(new Dimension(loginBox.getWidth(), 20)));
       loginBox.add(passwordPanel);
       loginBox.add(Box.createRigidArea(new Dimension(loginBox.getWidth(), 10)));
       loginBox.add(genderPanel);
       loginBox.add(Box.createRigidArea(new Dimension(loginBox.getWidth(), 10)));
       loginBox.add(signInPanel);
       loginBox.add(savePanel);
       
       //loginBox.setSize(new Dimension(500, 300));
       
       
       
       
        add(loginBox);
    
    }
    
    
    private void addUser(){
    
        addUserThread = new Thread(new Runnable(){
        
            @Override
            public void run(){
            
                
            }
        });
        
        
        return;
    }
    
    
    public String getUserName(){
        return UserName.getText();
    }
    
    
    public String getPassWord(){
        return Password.getText();
    }
    
    
    public String getGender(){
        return (String) Gender.getSelectedItem();
    }
    
    public JButton getSignIn(){
        return SignIn;
    }
    
    
    public JButton getAddUser(){
        return AddUser;
    }
    
}
