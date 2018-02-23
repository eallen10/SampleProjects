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

public class editEmployee {

	static final String userName = "jcrawf9";// put your MySQL user name
	static final String password = "Cosc*jdj3";// put your MySQL password
	private static Connection connection = null;

	public editEmployee(){
        // Creating instance of JFrame
	    	

	        JFrame editEmployee = new JFrame("Edit Employee");
	        // Setting the width and height of frame
	        editEmployee.setSize(420, 320);
	        editEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        editEmployee.add(panel);

	        // Setting the frame visibility to true
	        editEmployee.setLocationRelativeTo(null);
	        editEmployee.setVisible(true);

	        placeComponents(panel);
	        editEmployee.repaint(); 
	}

	public static void placeComponents(JPanel panel)
    {
    	panel.setLayout(null);
   
        JLabel empIDLabel= new JLabel("Employee ID: ");
        empIDLabel.setBounds(10, 20, 100, 40);
        panel.add(empIDLabel);

        JTextField empID = new JTextField();
        empID.setBounds(85, 25, 40, 30);
        panel.add(empID);
        
        JLabel dobLabel= new JLabel("DOB (YYYY-MM-DD): ");
        dobLabel.setBounds(140, 20, 150, 40);
        panel.add(dobLabel);

        JTextField empDOB = new JTextField();
        empDOB.setBounds(260, 25, 100, 30);
        panel.add(empDOB);
        
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
        
        JLabel ssnLabel= new JLabel("SSN: ");
        ssnLabel.setBounds(215, 120, 100, 40);
        panel.add(ssnLabel);

        JTextField empSSN = new JTextField();
        empSSN.setBounds(260, 125, 100, 30);
        panel.add(empSSN);
        
        JLabel empLocLabel= new JLabel("Employee Location: ");
        empLocLabel.setBounds(10, 170, 120, 40);
        panel.add(empLocLabel);
        
        String[] empLoc = {"0", "1"};
        JComboBox empLocList = new JComboBox (empLoc);
        empLocList.setBounds(125, 175, 70, 30);
        empLocList.doLayout();
        empLocList.setSelectedIndex(0);
        panel.add(empLocList);
        empLocList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});
        
        String[] empTypes = {"1", "2", "3", "4"};
        JComboBox empTypeList = new JComboBox (empTypes);
        empTypeList.setBounds(300, 175, 50, 30);
        empTypeList.doLayout();
        empTypeList.setSelectedIndex(0);
        panel.add(empTypeList);
        empTypeList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});
        
        
        JLabel vehicleTypeLabel= new JLabel("Employee Type: ");
        vehicleTypeLabel.setBounds(200, 170, 100, 40);
        panel.add(vehicleTypeLabel);
        
        JButton createEmployeeButton = new JButton("Update Employee");
        createEmployeeButton.setBounds(120, 225, 160, 40);
        panel.add(createEmployeeButton);
        createEmployeeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
                if (command.equals("Edit Employee")){	
                    String id = empID.getText();
                    String fNameU = fName.getText();
                    String mInitU = mI.getText();
                    String lNameU = lName.getText();
                    String dobU = empDOB.getText();
                    int ssnU = Integer.parseInt(empSSN.getText());
                    String locU = (String)empLocList.getSelectedItem();
                    String typeU = (String)empTypeList.getSelectedItem();
                    
                    String userName = "jcrawf9";//put your MySQL user name 
                    String password = "Cosc*jdj3";//put your MySQL password 
                    Connection connection = null;
                    try {  
                    Object newInstance;
                    newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                    connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);     
                    Statement stmt=connection.createStatement();
                    int x = stmt.executeUpdate("UPDATE employee SET fname = " + "'" + fNameU + "'" + ", mname = " + "'" + mInitU + "'" + ", lname = " + "'" + lNameU + "'" + ", dob = " + "'" + dobU + "'" + ", ssn = " + ssnU + ", location = " + "'" + locU + "'" + ", employee_type = " + "'" + typeU + "'" + " where EMPLOYEE_ID = " + id + ";");
                    
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
	