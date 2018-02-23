//AUTHOR: Christopher Brown
//Creates new individual customer
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

public class createNewICust {
	
	static int CustID = 0;
	static final String userName = "jcrawf9";//put your MySQL user name 
    static final String password = "Cosc*jdj3";//put your MySQL password 
	private static Connection connection = null;
	
    //public createNewICust() {
	public createNewICust(){
        // Creating instance of JFrame
	    	

	        JFrame createNewICust = new JFrame("New Individual Customer");
	        // Setting the width and height of frame
	        createNewICust.setSize(420, 330);
	        createNewICust.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        createNewICust.add(panel);

	        // Setting the frame visibility to true
	        createNewICust.setLocationRelativeTo(null);
	        createNewICust.setVisible(true);
    
	        placeComponents(panel);
	        createNewICust.repaint();          
    }
    
    public static void placeComponents(JPanel panel)
    {
    	panel.setLayout(null);
   
        JLabel licenseNumLabel= new JLabel("DL Number: ");
        licenseNumLabel.setBounds(10, 20, 100, 40);
        panel.add(licenseNumLabel);

        JTextField licenseNum = new JTextField();
        licenseNum.setBounds(85, 25, 120, 30);
        panel.add(licenseNum);
        
        JLabel fNameLabel= new JLabel("First Name: ");
        fNameLabel.setBounds(10, 70, 100, 40);
        panel.add(fNameLabel);

        JTextField fName = new JTextField();
        fName.setBounds(85, 75, 120, 30);
        panel.add(fName);
        
        JLabel mILabel= new JLabel("Middle Initial: ");
        mILabel.setBounds(215, 70, 100, 40);
        panel.add(mILabel);

        JTextField mI = new JTextField();
        mI.setBounds(300, 75, 30, 30);
        panel.add(mI);
        
        JLabel lNameLabel= new JLabel("Last Name: ");
        lNameLabel.setBounds(10, 120, 100, 40);
        panel.add(lNameLabel);

        JTextField lName = new JTextField();
        lName.setBounds(85, 125, 120, 30);
        panel.add(lName);
        
        JLabel phoneNumLabel= new JLabel("Phone: ");
        phoneNumLabel.setBounds(215, 120, 100, 40);
        panel.add(phoneNumLabel);

        JTextField phoneNum = new JTextField();
        phoneNum.setBounds(260, 125, 100, 30);
        panel.add(phoneNum);
        
        JLabel plateNumLabel= new JLabel("Tag Number: ");
        plateNumLabel.setBounds(10, 170, 100, 40);
        panel.add(plateNumLabel);

        JTextField plateNum = new JTextField();
        plateNum.setBounds(85, 175, 80, 30);
        panel.add(plateNum);
        
        String[] vehicleTypes = {"Car", "Truck", "Van"};
        JComboBox vehicleList = new JComboBox (vehicleTypes);
        vehicleList.setBounds(280, 175, 100, 30);
        vehicleList.doLayout();
        vehicleList.setSelectedIndex(0);
        panel.add(vehicleList);
        String vehicle1 = null;
        vehicleList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});

        JLabel vehicleTypeLabel= new JLabel("Vehicle Type: ");
        vehicleTypeLabel.setBounds(200, 170, 100, 40);
        panel.add(vehicleTypeLabel);
        
        JButton createCustomerButton = new JButton("Create Customer");
        createCustomerButton.setBounds(130, 225, 160, 40);
        panel.add(createCustomerButton);
        //Upon pressing this button it checks the database to see if the user is in the system
        createCustomerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				
				//First check if they exist in the system
				try {
					generateCustID();
					
					String dlNum = licenseNum.getText();
					String plateNumber = plateNum.getText();
					String individualCheck = "SELECT DRIVERS_LICENSE_NUM FROM jcrawf9db.INDIVIDUAL_CUSTOMER WHERE DRIVERS_LICENSE_NUM = '" + dlNum + "';";
					String vehicleCheck = "SELECT LICENSE_PLATE_ID FROM jcrawf9db.VEHICLE WHERE LICENSE_PLATE_ID = '" + plateNumber + "';";
					if (!queryDatabase(individualCheck) || !queryDatabase(vehicleCheck))
					{
						JOptionPane.showMessageDialog(panel,
					    "Already exists in the system!", "Warning", JOptionPane.WARNING_MESSAGE);
					}
					//If they're not in the system we can enter them
					else 
					{
						String insert1 = "INSERT INTO jcrawf9db.INDIVIDUAL_CUSTOMER (DRIVERS_LICENSE_NUM, C_ID, LICENSE_PLATE_ID, PHONE_NUM, FNAME, MNAME, LNAME) VALUES ('" + dlNum + "', " + CustID + ", '" + plateNumber + "', '" + phoneNum.getText() + "', '" + fName.getText() + "', '" + mI.getText() + "', '" + lName.getText() + "');";
						//System.out.println(insert1);
						String insert2 = "INSERT INTO jcrawf9db.CUSTOMER (CUSTOMER_ID, TYPE, DEBT, SITE_ID) VALUES ('" + CustID + "', 'INDIVIDUAL', 0, 0);";
						//System.out.println(insert2);
						String insert3 = "INSERT INTO jcrawf9db.VEHICLE (LICENSE_PLATE_ID, C_ID, TYPE, OWNER_FNAME, OWNER_LNAME) VALUES ('" + plateNumber + "', '" + CustID + "', '" + vehicleList.getSelectedItem() + "', '" + fName.getText() + "', '" + lName.getText() + "');";
						//System.out.println(insert3);
						queryDatabase2(insert1, insert2, insert3);
						JOptionPane.showMessageDialog(panel,
							    "User successfully created.");
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e1){}
				
			}
		});
    }
    
    //Checks to see if the user is already in the system
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
    
    //Create a new customer ID from the largest existing customer ID + 1
    public static void generateCustID() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        PreparedStatement updateStaff;
        Statement queryStatement = connection.createStatement();
        updateStaff = null;
            String querys="SELECT MAX(CUSTOMER_ID) FROM jcrawf9db.CUSTOMER;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
            	CustID = results.getInt("MAX(CUSTOMER_ID)")+1;
            }
    }
    
    //Inserts for given queries 
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
