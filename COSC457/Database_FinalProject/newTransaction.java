//AUTHOR: Christopher Brown
//Processes transactions, user can use enter keys for VERY fast processing
//DONE

import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class newTransaction {
	
	//We record the user ID from the login screen for entry as EMPLOYEE_ID under TRANSACTION table
	static String userID = logScreen.employeeID;
	//a generated site ID
	static int siteID;
	//a generated transaction ID 
	static int transID;
	//a user entered plate number
	static String plateNumber;
	
	static final String userName = "jcrawf9";//put your MySQL user name 
    static final String password = "Cosc*jdj3";//put your MySQL password 
	private static Connection connection = null;
	
	//public static void main(String[] args){
	public newTransaction(){
		
        	// Creating instance of JFrame
	        JFrame newTransaction = new JFrame("Transaction Processing");
	        // Setting the width and height of frame
	        newTransaction.setSize(420, 420);
	        newTransaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        newTransaction.add(panel);

	        // Setting the frame visibility to true
	        newTransaction.setLocationRelativeTo(null);
	        newTransaction.setVisible(true);

	        placeComponents(panel);
	        newTransaction.repaint();
    }
    
    public static void placeComponents(JPanel panel)
    {

    	panel.setLayout(null);
   
        JLabel licenseNumLabel= new JLabel("Plate Number: ");
        licenseNumLabel.setBounds(10, 10, 100, 40);
        panel.add(licenseNumLabel);

        JTextField plateNum = new JTextField();
        plateNum.setBounds(95, 15, 120, 30);
        panel.add(plateNum);
        
        JButton checkPlateButton = new JButton("Check Plate");
        checkPlateButton.setBounds(240, 15, 140, 30);
        panel.add(checkPlateButton);
        panel.getRootPane().setDefaultButton(checkPlateButton);
        checkPlateButton.requestFocus();
        checkPlateButton.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e) {
        			
        			try {
        				
        				int result = 0;
        				plateNumber = plateNum.getText();
        				//Queries to see if the user is in the system / sees if they owe us money
        				result = queryDatabase(plateNumber);
        				
        				//they don't exist in the system so we can't process them without going back and creating
						if (result == 1)
						{
							JOptionPane.showMessageDialog(panel,
						    "The customer is not in the system, go back and create them first.", "Warning", JOptionPane.WARNING_MESSAGE);
						}
						//they exist, we continue creating the GUI for processing 
						else if (result == 0)
						{
							continuePlacing(panel);
							panel.remove(checkPlateButton);
							panel.repaint();
						}
						//they exist but owe us a lot of money, we cannot process until they pay us for this debt (from prev transactions)
						else
						{
							JOptionPane.showMessageDialog(panel,
						    "The customer must pay their debt of $" + result + " before being able to be processed.", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					} catch (HeadlessException | ClassNotFoundException | InstantiationException
							| IllegalAccessException | SQLException e1) {
					}
        		}
        	});
    }
    
    public static void continuePlacing (JPanel panel)
    {
        JLabel licenseNumLabel= new JLabel("Plate number is valid");
        licenseNumLabel.setBounds(230, 10, 200, 40);
        panel.add(licenseNumLabel);
        
        JLabel wasteTypeLabel= new JLabel("Waste Type: ");
        wasteTypeLabel.setBounds(10, 60, 100, 40);
        panel.add(wasteTypeLabel);
        
        String[] wasteTypes = {"General Waste", "Scrap Metal", "Brush", "Dirt/Soil"};
        JComboBox wasteTypeList = new JComboBox (wasteTypes);
        wasteTypeList.setBounds(95, 65, 120, 30);
        wasteTypeList.doLayout();
        wasteTypeList.setSelectedIndex(0);
        panel.add(wasteTypeList);
        wasteTypeList.addActionListener(new ActionListener()
        	{
        	public void actionPerformed(ActionEvent e) {
        	}
        	});
        
        JLabel specialWasteTypeLabel= new JLabel("Special Waste: ");
        specialWasteTypeLabel.setBounds(5, 110, 100, 40);
        panel.add(specialWasteTypeLabel);
        
        String[] specialTypesList = {"None", "Tires", "Gallons of Oil", "Buckets of Paint"};
        JComboBox specialTypes = new JComboBox (specialTypesList);
        specialTypes.setBounds(95, 115, 120, 30);
        specialTypes.doLayout();
        specialTypes.setSelectedIndex(0);
        panel.add(specialTypes);
        specialTypes.addActionListener(new ActionListener()
        	{
        	public void actionPerformed(ActionEvent e) {
        	}
        	});
        
        JLabel specialWasteMultiplier= new JLabel("x");
        specialWasteMultiplier.setBounds(230, 110, 100, 40);
        panel.add(specialWasteMultiplier);
        
        JTextField specialWasteAmt = new JTextField("0");
        specialWasteAmt.setBounds(245, 115, 30, 30);
        panel.add(specialWasteAmt);
        
        JLabel grossLabel= new JLabel("Gross Weight: ");
        grossLabel.setBounds(10, 160, 100, 40);
        panel.add(grossLabel);
        
        JTextField grossWtAmt = new JTextField("0");
        grossWtAmt.setBounds(95, 165, 60, 30);
        panel.add(grossWtAmt);
        
        JLabel tareLabel= new JLabel("Tare Weight: ");
        tareLabel.setBounds(160, 160, 100, 40);
        panel.add(tareLabel);
        
        JTextField tareWtAmt = new JTextField("0");
        tareWtAmt.setBounds(235, 165, 60, 30);
        panel.add(tareWtAmt);
        
        JButton calculateTotalButton = new JButton("Calculate Total");
        calculateTotalButton.setBounds(130, 215, 140, 30);
        panel.add(calculateTotalButton);
        panel.getRootPane().setDefaultButton(calculateTotalButton);
        calculateTotalButton.requestFocus();
        //Calculates the transaction using the entered information 
        calculateTotalButton.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e) {
        			
        			float gross = Float.parseFloat(grossWtAmt.getText());
        			float tare = Float.parseFloat(tareWtAmt.getText());
        			DecimalFormat decimalFormat = new DecimalFormat("#.##");
        			
        			//if tare weight > gross weight we cannot process because that means the vehicle somehow GAINED weight, and we only accept DUMPING
        			if (tare > gross)
        			{
        				JOptionPane.showMessageDialog(panel,
        				"Tare Weight (" + tare + ") > Gross Weight (" + gross + ")", "Warning", JOptionPane.WARNING_MESSAGE);
        			}
        			//else we generate the cost for the selected type of waste they're dumping 
        			else
        			{
        				String wasteType = (String) wasteTypeList.getSelectedItem();
        				float wasteCost = 0;
        				switch (wasteType)
        				{
        					case "General Waste": wasteCost = 100;
        						break;
        					case "Scrap Metal": wasteCost = 80;
    							break;
        					case "Brush": wasteCost = 50;
								break;
        					case "Dirt/Soil": wasteCost = 30;
								break;
        				}
        				
        				//Calculates the cost of the regular waste being dumped
        				float totalWasteCost = Float.valueOf(decimalFormat.format((((gross-tare) / 2000) * wasteCost)));
        				
        				String specialWasteType = (String) specialTypes.getSelectedItem();
        				float specialWasteCost = 0;
        				switch (specialWasteType)
        				{
        					case "None": specialWasteCost = 0;
        						break;
        					case "Tires": specialWasteCost = 2;
    							break;
        					case "Gallons of Oil": specialWasteCost = 2;
								break;
        					case "Buckets of Paint": specialWasteCost = 1;
								break;
        				}
        				//Calculates the cost of the special fees
        				float specialWasteAmount = Float.parseFloat(specialWasteAmt.getText());
        				float totalSpecialWasteCost = (specialWasteCost * specialWasteAmount);
        				
        				float overAllTotal = Float.valueOf(decimalFormat.format(totalSpecialWasteCost + totalWasteCost));
        				
        		        JLabel wasteCostLabel1 = new JLabel("Waste Cost: $" + totalWasteCost + " @ $" + wasteCost + "/TN");
        		        wasteCostLabel1.setBounds(10, 240, 300, 40);
        		        panel.add(wasteCostLabel1);
        		        
        		        panel.repaint();

        		        JLabel wasteCostLabel2 = new JLabel("Special Waste Cost: " + totalSpecialWasteCost + " @ $" + specialWasteCost + " x " + specialWasteAmount);
        		        wasteCostLabel2.setBounds(10, 260, 300, 40);
        		        panel.add(wasteCostLabel2);
        		        
        		        JLabel wasteCostLabel3 = new JLabel("TOTAL DUMPING COST: $" + overAllTotal);
        		        wasteCostLabel3.setBounds(10, 280, 300, 40);
        		        panel.add(wasteCostLabel3);
        		        //If the calculate button is repressed we remove everything and regenerate it with the current information entered (which assumably changed)
        		        calculateTotalButton.addActionListener(new ActionListener()
        	        	{
        	        		public void actionPerformed(ActionEvent e) {
        	        			panel.remove(wasteCostLabel1);
	        		        	panel.remove(wasteCostLabel2);
	        		        	panel.remove(wasteCostLabel3);
	        		        	panel.revalidate();
	        		        	panel.repaint();
        	        		}
        	        	});
        		        
        		        String[] paymentTypesList = {"Paid", "Unpaid"};
        		        JComboBox paymentTypes = new JComboBox (paymentTypesList);
        		        paymentTypes.setBounds(10, 320, 120, 30);
        		        paymentTypes.doLayout();
        		        paymentTypes.setSelectedIndex(0);
        		        panel.add(paymentTypes);
        		        paymentTypes.addActionListener(new ActionListener()
        		        	{
        		        	public void actionPerformed(ActionEvent e) {
        		        	}
        		        	});
        		        
        		        JButton submitButton = new JButton("SUBMIT");
        		        submitButton.setBounds(140, 320, 255, 30);
        		        panel.add(submitButton);
        		        panel.getRootPane().setDefaultButton(submitButton);
        		        submitButton.requestFocus();
        		        
        		        submitButton.addActionListener(new ActionListener()
        		        	{
        		        		public void actionPerformed(ActionEvent e) 
        		        		{
        		        			
        		        			//***TRANSACTION TABLE***
        		        			//TRANSACTION_ID = transID after generation
        		        			try {
        		        				//creates a transaction ID by looking at the current highest transaction ID and adding 1 to it
										generateTransID();
									} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
											| SQLException e1) {}
        		        			
        		        			//E_ID = userID;
        		        			
        		        			//PLATE_NUMBR = plateNumber;
        		        			
        		        			//DATE_TIME is date
        		        	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		        	        Date date = new Date();
        		        	        String dateTime = dateFormat.format(date); 
        		        	        
        		        	        
        		        	        //SITE_ID is siteID after generation; 
        		        	        try {
        		        	        	//Creates a site ID using the employee's login (they can only work at 1 site)
										generateSiteID();
									} catch (InstantiationException | IllegalAccessException | ClassNotFoundException
											| SQLException e2) {}
        		        	        
        		        	        //TYPE is wasteType
        		        	        
        		        	        //WEIGHT is totalCalcWeight
        		        	        float totalCalcWeight = gross - tare;
        		        	        
        		        	        //FEES is the cost of the additional fees, overAllTotal
        		        	        
        		        	        //Inserts the transaction into the TRANSACTION table using the information above
        		        	        String transactionInsert = "INSERT INTO jcrawf9db.TRANSACTION (TRANSACTION_ID, E_ID, PLATE_NUMBR, DATE_TIME, SITE_ID, TYPE, WEIGHT, FEES) VALUES ('" + transID + "', '" + userID + "', '" + plateNumber + "', '" + dateTime + "', '" + siteID + "', '" + wasteType + "', '" + totalCalcWeight + "', '" + overAllTotal + "');" ;
        		        	        try {
										executeQuery(transactionInsert);
									} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
											| SQLException e1) {}
        		        	        //--------------
        		        	        
        		        	        //***FEES TABLE***
        		        	        
        		        	        //T_ID is transID
        		        	        //TOTAL_COST is overAllTotal
        		        	        //SPECIAL_COST is totalSpecialWasteCost
        		        	        //WASTE_TYPE is specialWasteType
        		        	        
        		        	      //Inserts the fees into the FEES table using the information above
        		        	        String feesInsert = "INSERT INTO jcrawf9db.FEES (T_ID, TOTAL_COST, SPECIAL_COST, WASTE_TYPE) VALUES ('" + transID + "', '" + overAllTotal + "', '" + totalSpecialWasteCost + "', '" + specialWasteType + "');";
        		        	        try {
										executeQuery(feesInsert);
									} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
											| SQLException e1) {}
        		        	        
        		        	        if (paymentTypes.getSelectedItem() == "Unpaid")
        		        	        {
        		        	        	//populates the custID for our query as we need to apply debt to them under CUSTOMERS for that custid
        		        	        	try {
											int customerID = generatecustID();
											String debtInsert = "UPDATE jcrawf9db.CUSTOMER SET DEBT = '" + overAllTotal + "' WHERE CUSTOMER_ID = '" + customerID + "';";
											executeQuery(debtInsert);
										} catch (ClassNotFoundException | InstantiationException
												| IllegalAccessException | SQLException e1) {}
        		        	        
        		        	        }
        		        	         
        		        	        //Once everything is submitted we close the window and reopen it so that employees can rapidly process transactions 
        		        			 newTransaction newTransaction = new newTransaction();
        		        			 JComponent comp = (JComponent) e.getSource();
        		        			 Window win = SwingUtilities.getWindowAncestor(comp);
        		        			 win.dispose();
        		        		}
        		        	});
        			}
        		}
        	});
    }
    
    //generates a transaction ID for creation of a new transaction
    public static void generateTransID() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
            String querys="SELECT MAX(TRANSACTION_ID) FROM jcrawf9db.TRANSACTION;";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
            	transID = results.getInt("MAX(TRANSACTION_ID)")+1;
            }
    }
    
    //generates a customer ID for adding debt to their account (if necessary)
    public static int generatecustID() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
            String queryINDIVIDUAL_CUSTOMER ="SELECT C_ID FROM jcrawf9db.INDIVIDUAL_CUSTOMER WHERE LICENSE_PLATE_ID = '" + plateNumber + "';";
            String queryPRIVATE_COMPANY ="SELECT C_ID FROM jcrawf9db.PRIVATE_COMPANY WHERE LICENSE_PLATE_NUM = '" + plateNumber + "';";
            ResultSet results = queryStatement.executeQuery(queryINDIVIDUAL_CUSTOMER);
            
            
            //If they're in the INDIVIDUAL_CUSTOMER table we've found them, return the C_ID
            if (results.next())
            {
            	return results.getInt("C_ID");
            }
            
            else 
            {
            	 //If they're in the PRIVATE_COMPANY table we've found them, return the C_ID
            		ResultSet results1 = queryStatement.executeQuery(queryPRIVATE_COMPANY);
            		if (results1.next())
            		{
            			return results1.getInt("C_ID");
            		}
            		
            }
            return 0;
    }
    
    //generates a site ID using the given employee ID that is logged in (for adding to transaction data) 
    public static void generateSiteID() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
            String querys="SELECT SITE_ID FROM jcrawf9db.EMPLOYEE WHERE EMPLOYEE_ID = '" + userID + "';";
            ResultSet results = queryStatement.executeQuery(querys);
            while(results.next())
            {
            	siteID = results.getInt("SITE_ID");
            }
    }
    
    //Checks to make sure the vehicle is in the database 
    public static int queryDatabase(String plateNum) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        Statement queryStatement = connection.createStatement();
        	String query="SELECT DEBT FROM jcrawf9db.CUSTOMER WHERE CUSTOMER_ID IN (SELECT C_ID FROM jcrawf9db.VEHICLE WHERE LICENSE_PLATE_ID = '" + plateNum + "');";
            ResultSet results = queryStatement.executeQuery(query);
            
            boolean val = results.next();
            
            if (!val)
            {
            	return 1;
            }
            if (val)
            {
            	int debtAmt = results.getInt("DEBT");
            	if (debtAmt > 1) return debtAmt;
            }
            return 0;
    }

    //Executes all the queries to insert data into TRANSACTION or FEES, is also used to insert DEBT into CUSTOMER if the transaction is selected as "Unpaid" during processing
    public static void executeQuery(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
    {
        Object newInstance;
        newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db", userName, password);// Please use your database name here
        PreparedStatement updateDB;
        Statement queryStatement = connection.createStatement();

        updateDB = connection.prepareStatement(query);
        updateDB.executeUpdate();
    }

}