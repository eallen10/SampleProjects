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

public class editPCTransaction
{
	
	static final String userName = "jcrawf9";// put your MySQL user name
	static final String password = "Cosc*jdj3";// put your MySQL password
	private static Connection connection = null;
	
	public editPCTransaction()
	{
		
        	// Creating instance of JFrame
	        JFrame editICTransaction = new JFrame("Edit Private Company Transaction");
	        // Setting the width and height of frame
	        editICTransaction.setSize(420, 550);
	        editICTransaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        editICTransaction.add(panel);

	        // Setting the frame visibility to true
	        editICTransaction.setLocationRelativeTo(null);
	        editICTransaction.setVisible(true);

	        placeComponents(panel);
	        editICTransaction.repaint();
    }
	
	public static void placeComponents(JPanel panel)
    {
		
		panel.setLayout(null);
		   
		JLabel companyNameLabel= new JLabel("Company Name: ");
		companyNameLabel.setBounds(10, 20, 100, 40);
        panel.add(companyNameLabel);

        JTextField companyName = new JTextField();
        companyName.setBounds(85, 75, 120, 30);
        panel.add(companyName);
		
		JLabel transactionIDLabel= new JLabel("Transaction ID: ");
		transactionIDLabel.setBounds(10, 70, 100, 40);
        panel.add(transactionIDLabel);

        JTextField transactionID = new JTextField();
        transactionID.setBounds(85, 75, 120, 30);
        panel.add(transactionID);
		
		JLabel plateNumLabel= new JLabel("License Plate Number: ");
		plateNumLabel.setBounds(215, 70, 100, 40);
        panel.add(plateNumLabel);

        JTextField plateNum = new JTextField();
        plateNum.setBounds(300, 75, 120, 30);
        panel.add(plateNum);
        
        JLabel eIDLabel= new JLabel("Employee ID: ");
        eIDLabel.setBounds(10, 120, 100, 40);
        panel.add(eIDLabel);

        JTextField empID = new JTextField();
        empID.setBounds(85, 125, 120, 30);
        panel.add(empID);
        
        JLabel cIDLabel= new JLabel("Customer ID: ");
        cIDLabel.setBounds(215, 120, 100, 40);
        panel.add(cIDLabel);

        JTextField custID = new JTextField();
        custID.setBounds(300, 125, 120, 30);
        panel.add(custID);
        
        JLabel dateLabel= new JLabel("Date: ");
        dateLabel.setBounds(10, 170, 100, 40);
        panel.add(dateLabel);

        JTextField date = new JTextField();
        date.setBounds(85, 175, 120, 30);
        panel.add(date);
        
        JLabel timeLabel= new JLabel("Time: ");
        timeLabel.setBounds(215, 170, 100, 40);
        panel.add(timeLabel);

        JTextField time = new JTextField();
        time.setBounds(260, 175, 100, 30);
        panel.add(time);
        
        String[] locations = {"0", "1"};
        JComboBox locationList = new JComboBox (locations);
        locationList.setBounds(85, 225, 100, 30);
        locationList.doLayout();
        locationList.setSelectedIndex(0);
        panel.add(locationList);
        locationList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});

        JLabel locationLabel= new JLabel("Location: ");
        locationLabel.setBounds(10, 220, 100, 40);
        panel.add(locationLabel);
        
        String[] wasteTypes = {"X", "Y", "Z"};
        JComboBox wasteList = new JComboBox (wasteTypes);
        wasteList.setBounds(280, 225, 100, 30);
        wasteList.doLayout();
        wasteList.setSelectedIndex(0);
        panel.add(wasteList);
        wasteList.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {}
		});

        JLabel wasteTypeLabel= new JLabel("Waste Type: ");
        wasteTypeLabel.setBounds(200, 220, 100, 40);
        panel.add(wasteTypeLabel);
        
        JLabel weightLabel= new JLabel("Weight: ");
        weightLabel.setBounds(10, 270, 100, 40);
        panel.add(weightLabel);

        JTextField weight = new JTextField();
        weight.setBounds(85, 275, 120, 30);
        panel.add(weight);
        
        JLabel feesLabel= new JLabel("Fees: ");
        feesLabel.setBounds(215, 270, 100, 40);
        panel.add(feesLabel);

        JTextField fees = new JTextField();
        fees.setBounds(260, 275, 100, 30);
        panel.add(fees);
        
        JButton editTransactionButton = new JButton("Update Transaction");
        editTransactionButton.setBounds(130, 330, 160, 40);
        panel.add(editTransactionButton);
        editTransactionButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
                if (command.equals("Update Transaction")){	
                    int tID = Integer.parseInt(transactionID.getText());
                    String p_num = plateNum.getText();
                    String e_id = empID.getText();
                    String c_id = custID.getText();
                    String d = date.getText();
                    String t = time.getText();
                    String loc = (String)locationList.getSelectedItem();
                    String wa = (String)wasteList.getSelectedItem();
                    int w = Integer.parseInt(weight.getText());
                    int f = Integer.parseInt(fees.getText());
                    String cn = companyName.getText();
                    
                    String userName = "jcrawf9";//put your MySQL user name 
                    String password = "Cosc*jdj3";//put your MySQL password 
                    Connection connection = null;
                    try {  
                    Object newInstance;
                    newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                    connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);     
                    Statement stmt=connection.createStatement();
                    int x = stmt.executeUpdate("UPDATE pc_transaction p, transaction t SET t.e_id = " + "'" + e_id + "'" + ", t.c_id = " + "'" + c_id + "'" + ", t.date = " + "'" + d + "'" + ", t.time = " + "'" + t + "'" + ", t.location = " + "'" + loc + "'" + ", t.type = " + "'" + wa + "'" + ", t.weight = " + w + ", t.fees = " +  f + ", p.lp_num = " + "'" + p_num + "'" + ", p.company_name = " + "'" + cn + "'" + " where t.transaction_id = " + tID + "AND p.t_id = " + tID + ";");
                    
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