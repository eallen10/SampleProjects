//AUTHOR: Christopher Brown
//Creates new private company customer
//DONE


import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class createNewPCCust {
	
	static int CustID = 0;
	static final String userName = "jcrawf9";//put your MySQL user name 
    static final String password = "Cosc*jdj3";//put your MySQL password 
	private static Connection connection = null;
	
    public createNewPCCust() {
        // Creating instance of JFrame
	    	

	        JFrame createNewPCCust = new JFrame("New Private Company Customer");
	        // Setting the width and height of frame
	        createNewPCCust.setSize(420, 420);
	        createNewPCCust.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        createNewPCCust.add(panel);

	        // Setting the frame visibility to true
	        createNewPCCust.setLocationRelativeTo(null);
	        createNewPCCust.setVisible(true);

	        placeComponents(panel);
	        createNewPCCust.repaint();
    }
    
    public static void placeComponents(JPanel panel)
    {
    	panel.setLayout(null);
        
        JLabel companyNameLabel= new JLabel("Company Name: ");
        companyNameLabel.setBounds(10, 15, 100, 40);
        panel.add(companyNameLabel);

        JTextField companyName = new JTextField();
        companyName.setBounds(110, 20, 150, 30);
        panel.add(companyName);
        
        JLabel companyAddrLabel= new JLabel("Company Addr: ");
        companyAddrLabel.setBounds(10, 65, 100, 40);
        panel.add(companyAddrLabel);

        JTextField companyAddr = new JTextField();
        companyAddr.setBounds(110, 70, 275, 30);
        panel.add(companyAddr);
        
        JLabel phoneNumLabel= new JLabel("Company Phone: ");
        phoneNumLabel.setBounds(10, 115 , 100, 40);
        panel.add(phoneNumLabel);

        JTextField phoneNum = new JTextField();
        phoneNum.setBounds(110, 120, 100, 30);
        panel.add(phoneNum);
        
        JLabel fNameLabel= new JLabel("First Name: ");
        fNameLabel.setBounds(10, 165, 100, 40);
        panel.add(fNameLabel);

        JTextField fName = new JTextField();
        fName.setBounds(85, 170, 210, 30);
        panel.add(fName);
        
        JLabel lNameLabel= new JLabel("Last Name: ");
        lNameLabel.setBounds(10, 215, 100, 40);
        panel.add(lNameLabel);

        JTextField lName = new JTextField();
        lName.setBounds(85, 220, 210, 30);
        panel.add(lName);
        
        
        JLabel plateNumLabel= new JLabel("Tag Number: ");
        plateNumLabel.setBounds(10, 265, 100, 40);
        panel.add(plateNumLabel);

        JTextField plateNum = new JTextField();
        plateNum.setBounds(85, 270, 80, 30);
        panel.add(plateNum);
        
        String[] vehicleTypes = {"Dump Truck", "Trash Truck", "Truck", "Van", "Car"};
        JComboBox vehicleList = new JComboBox (vehicleTypes);
        vehicleList.setBounds(280, 270, 100, 30);
        vehicleList.doLayout();
        vehicleList.setSelectedIndex(0);
        panel.add(vehicleList);
        vehicleList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});
     
        JLabel vehicleTypeLabel= new JLabel("Vehicle Type: ");
        vehicleTypeLabel.setBounds(200, 265, 100, 40);
        panel.add(vehicleTypeLabel);
        
        JButton createCustomerButton = new JButton("Create Customer");
        createCustomerButton.setBounds(130, 320, 160, 40);
        panel.add(createCustomerButton);
        createCustomerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				//First check if they exist in the system
				try {
					generateCustID();
					String companyNames = companyName.getText().replaceAll("'", "");
					String plateNumber = plateNum.getText();
					
					String individualCheck = "SELECT CUSTOMER_ID FROM jcrawf9db.CUSTOMER WHERE CUSTOMER_ID = '" + CustID + "';";
					String vehicleCheck = "SELECT LICENSE_PLATE_ID FROM jcrawf9db.VEHICLE WHERE LICENSE_PLATE_ID = '" + plateNumber + "'";
					if (!queryDatabase(individualCheck) || !queryDatabase (vehicleCheck))
					{
						JOptionPane.showMessageDialog(panel,
					    "Already exists in the system!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					//If they're not in the system we can enter them
					else 
					{
						String insert1 = "INSERT INTO jcrawf9db.PRIVATE_COMPANY (C_ID, COMPANY_NAME, LICENSE_PLATE_NUM, PHONE_NUM, ADDRESS) VALUES (" + CustID + ", '" + companyNames + "', '" + plateNumber + "', '" + phoneNum.getText() + "', '" + companyAddr.getText() + "');";
						String insert2 = "INSERT INTO jcrawf9db.CUSTOMER (CUSTOMER_ID, TYPE, DEBT, SITE_ID) VALUES ('" + CustID + "', 'COMPANY', 0, 1);";
						String insert3 = "INSERT INTO jcrawf9db.VEHICLE (LICENSE_PLATE_ID, C_ID, TYPE, OWNER_FNAME, OWNER_LNAME) VALUES ('" + plateNumber + "', '" + CustID + "', '" + vehicleList.getSelectedItem() + "', '" + fName.getText() + "', '" + lName.getText() + "');";
						queryDatabase2(insert1, insert2, insert3);
						JOptionPane.showMessageDialog(panel,
							    "User successfully created.");
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1){}
				
			}
		});
    }
    
    //Checks if users are in the system
    public static boolean queryDatabase(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
            ResultSet results = queryStatement.executeQuery(query);

            boolean val = results.next();
            
            if(!val) return true;
            else return false;       
    }
    
    //Generates a user ID from the greatest existing user ID + 1
    public static void generateCustID() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
            String querys="SELECT MAX(CUSTOMER_ID) FROM jcrawf9db.CUSTOMER;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
            	CustID = results.getInt("MAX(CUSTOMER_ID)")+1;
            }
    }
    
    //Creates the user in the necessary tables
    public static void queryDatabase2(String query1, String query2, String query3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        PreparedStatement updateDB;
        Statement queryStatement = connection.createStatement();
        updateDB = null;
        updateDB = connection.prepareStatement(query1);
        updateDB.executeUpdate();
        updateDB = connection.prepareStatement(query2);
        updateDB.executeUpdate();
        updateDB = connection.prepareStatement(query3);
        updateDB.executeUpdate();
            
    }

}