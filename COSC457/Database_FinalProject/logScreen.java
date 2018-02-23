//AUTHOR: Christopher Brown
//DONE

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class logScreen {
	    
	static final String userName = "jcrawf9";//put your MySQL user name 
    static final String password = "Cosc*jdj3";//put your MySQL password 
	private static Connection connection = null;
	
	static String employeeID;
	 	
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException {    
        JFrame frameMainScreen = new JFrame("Summerville County Landfill Systems");
        // Setting the width and height of frame
        frameMainScreen.setSize(600, 400);
        frameMainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
        JPanel panel = new JPanel();    
        // adding panel to frame
        frameMainScreen.add(panel);

        // Setting the frame visibility to true
        frameMainScreen.setLocationRelativeTo(null);
        frameMainScreen.setVisible(true);

        
        placeComponents(panel);
        frameMainScreen.repaint();
    }

    public static void placeComponents(JPanel panel) throws IOException {

        panel.setLayout(null);
     
        JLabel welcomeMessage = new JLabel("Welcome to Summerville County Landfill Systems");
        welcomeMessage.setBounds(155, 50, 300, 25);
        panel.add(welcomeMessage);
        
        JLabel pleaseLogInLabel = new JLabel("Please enter log in information");
        pleaseLogInLabel.setBounds(200, 75, 200, 25);
        panel.add(pleaseLogInLabel);
        
        JLabel UsernameLabel = new JLabel("Username: ");
        UsernameLabel.setBounds(175, 150, 200, 25);
        panel.add(UsernameLabel);
        
        JLabel PasswordLabel = new JLabel("Password: ");
        PasswordLabel.setBounds(175, 200, 200, 25);
        panel.add(PasswordLabel);
        
        JTextField username = new JTextField();
        username.setBounds(250, 150, 80, 30);
        panel.add(username);
        
        JTextField password = new JTextField();
        password.setBounds(250, 200, 80, 30);
        panel.add(password);
        
        JButton LogInButton = new JButton("Log In");
        LogInButton.setBounds(238, 265, 100, 40);
        panel.add(LogInButton);
        LogInButton.addActionListener(new ActionListener()
		{
        	//When the log in button is pressed it tries to check the log in and launches menus accordingly 
        	//TODO: This only works for EMPLOYEE whose EMPLOYEE_TYPE = 1, 2, or 4. Need to fix menus and their options.
			public void actionPerformed(ActionEvent e) {
				
				try {
					String userID = username.getText();
					String userPassword = password.getText();
					employeeID = userID;
					if (checkLogin(userID, userPassword) == 0)
						{JOptionPane.showMessageDialog(panel,
		            		    "Invalid credentials. Try again.");
						}
					if (checkLogin(userID, userPassword) == 1)
					{
						managerMenu manager = new managerMenu();
					}
					if (checkLogin(userID, userPassword) == 2)
					{
						managerMenu manager = new managerMenu();
					}
					if (checkLogin(userID, userPassword) == 3)
					{
						operatorMenu operator = new operatorMenu();
					}
					if (checkLogin(userID, userPassword) == 4)
					{
						JOptionPane.showMessageDialog(panel,
		            		    "General laborers have no menu options.");
					}
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1) {
				}
			}
		});
    }
    
    //Checks to see if the employee exists, if they do then return their employee type for menu launching 
    public static int checkLogin(String userID, String userPassword) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
        	String querys="SELECT EMPLOYEE_ID, EMPLOYEE_TYPE FROM jcrawf9db.EMPLOYEE WHERE EMPLOYEE_ID = " + "'" + userID + "'" + " AND SSN = " + userPassword;
            ResultSet results = queryStatement.executeQuery(querys);
            
            boolean val = results.next();
            
            if(!val)
            {
            	return 0;
            }
            if (val)
            {
            	if (results.getInt("EMPLOYEE_TYPE") == 1)
            	{
            		return 1;
            	}
            	if (results.getInt("EMPLOYEE_TYPE") == 2)
            	{
            		return 2;
            	}
            	if (results.getInt("EMPLOYEE_TYPE") == 3)
            	{
            		return 3;
            	}
            	//TODO: What can a general laborer see? 
            	if (results.getInt("EMPLOYEE_TYPE") == 4)
            	{
            		return 4;
            	}
           
            	return 0;
            }  
            return 0;    
    }
}



