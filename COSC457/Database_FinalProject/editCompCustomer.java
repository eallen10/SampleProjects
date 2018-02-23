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

public class editCompCustomer {

	static final String userName = "jcrawf9";// put your MySQL user name
	static final String password = "Cosc*jdj3";// put your MySQL password
	private static Connection connection = null;

	public editCompCustomer(){
        // Creating instance of JFrame
	    	

	        JFrame editCompCustomer = new JFrame("Edit Company Customer");
	        // Setting the width and height of frame
	        editCompCustomer.setSize(420, 470);
	        editCompCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        editCompCustomer.add(panel);

	        // Setting the frame visibility to true
	        editCompCustomer.setLocationRelativeTo(null);
	        editCompCustomer.setVisible(true);

	        placeComponents(panel);
	        editCompCustomer.repaint(); 
	}

	public static void placeComponents(JPanel panel)
    {
panel.setLayout(null);
        
		JLabel companyIDLabel= new JLabel("Company ID: ");
		companyIDLabel.setBounds(10, 15, 100, 40);
		panel.add(companyIDLabel);
		
		JTextField companyID = new JTextField();
		companyID.setBounds(110, 20, 150, 30);
		panel.add(companyID);
		
		JLabel companyNameLabel= new JLabel("Company Name: ");
        companyNameLabel.setBounds(10, 65, 100, 40);
        panel.add(companyNameLabel);

        JTextField companyName = new JTextField();
        companyName.setBounds(110, 70, 150, 30);
        panel.add(companyName);
        
        JLabel companyAddrLabel= new JLabel("Company Addr: ");
        companyAddrLabel.setBounds(10, 115, 100, 40);
        panel.add(companyAddrLabel);

        JTextField companyAddr = new JTextField();
        companyAddr.setBounds(110, 120, 275, 30);
        panel.add(companyAddr);
        
        JLabel phoneNumLabel= new JLabel("Company Phone: ");
        phoneNumLabel.setBounds(10, 165 , 100, 40);
        panel.add(phoneNumLabel);

        JTextField phoneNum = new JTextField();
        phoneNum.setBounds(110, 170, 100, 30);
        panel.add(phoneNum);
        
        JLabel fNameLabel= new JLabel("First Name: ");
        fNameLabel.setBounds(10, 215, 100, 40);
        panel.add(fNameLabel);

        JTextField fName = new JTextField();
        fName.setBounds(85, 220, 210, 30);
        panel.add(fName);
        
        JLabel lNameLabel= new JLabel("Last Name: ");
        lNameLabel.setBounds(10, 265, 100, 40);
        panel.add(lNameLabel);

        JTextField lName = new JTextField();
        lName.setBounds(85, 270, 210, 30);
        panel.add(lName);
        
        
        JLabel plateNumLabel= new JLabel("Tag Number: ");
        plateNumLabel.setBounds(10, 315, 100, 40);
        panel.add(plateNumLabel);

        JTextField plateNum = new JTextField();
        plateNum.setBounds(85, 320, 80, 30);
        panel.add(plateNum);
        
        String[] vehicleTypes = {"Dump Truck", "Trash Truck", "Truck", "Van", "Car"};
        JComboBox vehicleList = new JComboBox (vehicleTypes);
        vehicleList.setBounds(280, 320, 100, 30);
        vehicleList.doLayout();
        vehicleList.setSelectedIndex(0);
        panel.add(vehicleList);
        vehicleList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});
     
        JLabel vehicleTypeLabel= new JLabel("Vehicle Type: ");
        vehicleTypeLabel.setBounds(200, 315, 100, 40);
        panel.add(vehicleTypeLabel);
        
        JButton editCustomerButton = new JButton("Update Customer");
        editCustomerButton.setBounds(130, 370, 160, 40);
        panel.add(editCustomerButton);
        editCustomerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
                if (command.equals("Edit Company Customer")){	
                    String id = companyID.getText();
                	String cNameU = companyName.getText().replaceAll("'", "");
                    String fNameU = fName.getText();
                    String lNameU = lName.getText();
                    String addrU = companyAddr.getText();
                    String phone = phoneNum.getText();
                    String plateU = plateNum.getText();
                    String typeU = (String)vehicleList.getSelectedItem();
                    
                    String userName = "jcrawf9";//put your MySQL user name 
                    String password = "Cosc*jdj3";//put your MySQL password 
                    Connection connection = null;
                    try {  
                    Object newInstance;
                    newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                    connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);     
                    Statement stmt=connection.createStatement();
                    int x = stmt.executeUpdate("UPDATE private_company SET fname = " + "'" + fNameU + "'" + ", lname = " + "'" + lNameU + "'" + ", company_name = " + "'" + cNameU + "'" + ", address" + "'" + addrU + "'" + ", license_plate_num = " + "'" + plateU + "'" + ", phone_num = " + phone + ",vehicle_type = " + "'" + typeU + "'" + " where C_ID = " + id + ";");
                    
                    JOptionPane.showMessageDialog(panel, "Success");
		connection.close();  
                    }
                    catch(Exception q) {
                        System.out.println(q);
                        JOptionPane.showMessageDialog(panel, q);
                    }

                    
                }
		}
    });
    }
}
	