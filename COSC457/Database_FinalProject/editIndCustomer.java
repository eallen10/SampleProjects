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

public class editIndCustomer {

	static final String userName = "jcrawf9";// put your MySQL user name
	static final String password = "Cosc*jdj3";// put your MySQL password
	private static Connection connection = null;

	public editIndCustomer(){
        // Creating instance of JFrame
	    	

	        JFrame editCompCustomer = new JFrame("Edit Individual Customer");
	        // Setting the width and height of frame
	        editCompCustomer.setSize(450, 320);
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
		   
		JLabel companyIDLabel= new JLabel("Customer ID: ");
		companyIDLabel.setBounds(10, 20, 100, 40);
        panel.add(companyIDLabel);

        JTextField companyID = new JTextField();
        companyID.setBounds(85, 25, 120, 30);
        panel.add(companyID);
		
		JLabel licenseNumLabel= new JLabel("DL Number: ");
        licenseNumLabel.setBounds(215, 20, 100, 40);
        panel.add(licenseNumLabel);

        JTextField licenseNum = new JTextField();
        licenseNum.setBounds(300, 25, 120, 30);
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
        
        JButton editCustomerButton = new JButton("Update Customer");
        editCustomerButton.setBounds(130, 225, 160, 40);
        panel.add(editCustomerButton);
        editCustomerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
                if (command.equals("Update Customer")){	
                    String id = companyID.getText();
                    String dlNumU = licenseNum.getText();
                    String fNameU = fName.getText();
                    String mInitU = mI.getText();
                    String lNameU = lName.getText();
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
                    int x = stmt.executeUpdate("UPDATE INDIVIDUAL_CUSTOMER SET fname = " + "'" + fNameU + "'" + ", mname = " + "'" + mInitU + "'" + ", lname = " + "'" + lNameU + "'" + ", license_plate_id = " + "'" + plateU + "'" + ", phone_num = " + phone + /*",vehicle_type = " + "'" + typeU + "'" +*/ " where C_ID = " + id + /*"AND drivers_license_num = " + dlNumU + */";");
                    
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
	