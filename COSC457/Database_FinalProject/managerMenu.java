//AUTHOR: Christopher Brown
//STILL WORK IN PROGRESS

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import static sql.adminLogin.placeComponents;

public class managerMenu {
	
    public managerMenu() {
        // Creating instance of JFrame
	    	

	        JFrame managerMenu = new JFrame("Manager Menu");
	        // Setting the width and height of frame
	        managerMenu.setSize(490, 750);
	        managerMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        managerMenu.add(panel);

	        // Setting the frame visibility to true
	        managerMenu.setLocationRelativeTo(null);
	        managerMenu.setVisible(true);

	        
	        placeComponents(panel);
	        managerMenu.repaint();
	    
    }
    
    public static void placeComponents(JPanel panel) {

	        panel.setLayout(null);
            
	        //===== Create Buttons ===== 
	        
                JButton newICust = new JButton("Create Individual Customer");
                newICust.setBounds(110, 20, 250, 25);
	        panel.add(newICust);
	        newICust.setActionCommand("Create Individual Customer");
	        newICust.addActionListener(new ButtonClickListener());
                
                JButton newCCust = new JButton("Create Company Customer");
                newCCust.setBounds(110, 50, 250, 25);
	        panel.add(newCCust);
	        newCCust.setActionCommand("Create Company Customer");
	        newCCust.addActionListener(new ButtonClickListener());
                
                JButton newEmployee = new JButton("Create New Employee");
	        newEmployee.setBounds(110, 80, 250, 25);
	        panel.add(newEmployee);
	        newEmployee.setActionCommand("Create New Employee");
	        newEmployee.addActionListener(new ButtonClickListener());
                
                JButton processTransaction = new JButton("Process Transaction");
                processTransaction.setBounds(110, 110, 250, 25);
	        panel.add(processTransaction);
	        processTransaction.setActionCommand("Process Transaction");
	        processTransaction.addActionListener(new ButtonClickListener());
	        
	        panel.getRootPane().setDefaultButton(processTransaction);
	        processTransaction.requestFocus();
            
	        //===== Edit Buttons ===== 
                
	        	JButton adminEditIndCustomer = new JButton("Edit Individual Customer");
	        adminEditIndCustomer.setBounds(110, 170, 250, 25);
	        panel.add(adminEditIndCustomer);
	        adminEditIndCustomer.setActionCommand("Edit Individual Customer");
	        adminEditIndCustomer.addActionListener(new ButtonClickListener());
	        
                JButton adminEditCompCustomer = new JButton("Edit Company Customer");
	        adminEditCompCustomer.setBounds(110, 200, 250, 25);
	        panel.add(adminEditCompCustomer);
	        adminEditCompCustomer.setActionCommand("Edit Company Customer");
	        adminEditCompCustomer.addActionListener(new ButtonClickListener());
                
                JButton adminEditICTransaction = new JButton("Edit Transaction");
	        adminEditICTransaction.setBounds(110, 230, 250, 25);
	        panel.add(adminEditICTransaction);
	        adminEditICTransaction.setActionCommand("Edit Individual Customer Transaction");
	        adminEditICTransaction.addActionListener(new ButtonClickListener());
	        
	        	/*JButton adminEditPCTransaction = new JButton("Edit Private Company Transaction");
	        adminEditPCTransaction.setBounds(110, 260, 250, 25);
	        panel.add(adminEditPCTransaction);
	        adminEditPCTransaction.setActionCommand("Edit Private Company Transaction");
	        adminEditPCTransaction.addActionListener(new ButtonClickListener());*/
                
                JButton adminEditEmployee = new JButton("Edit Employee");
	        adminEditEmployee.setBounds(110, 260, 250, 25);
	        panel.add(adminEditEmployee);
	        adminEditEmployee.setActionCommand("Edit Employee");
	        adminEditEmployee.addActionListener(new ButtonClickListener());
            
	        //===== Delete Buttons ===== 
	        
	        	JButton adminDeleteIndCustomer = new JButton("Delete Individual Customer");
	        adminDeleteIndCustomer.setBounds(110, 310, 250, 25);
	        panel.add(adminDeleteIndCustomer);
	        adminDeleteIndCustomer.setActionCommand("Delete Individual Customer");
	        adminDeleteIndCustomer.addActionListener(new ButtonClickListener());     
	        
                JButton adminDeleteCompCustomer = new JButton("Delete Company Customer");
	        adminDeleteCompCustomer.setBounds(110, 340, 250, 25);
	        panel.add(adminDeleteCompCustomer);
	        adminDeleteCompCustomer.setActionCommand("Delete Company Customer");
	        adminDeleteCompCustomer.addActionListener(new ButtonClickListener());
                
                JButton adminDeleteICTransaction = new JButton("Delete Individual Customer Transaction");
	        adminDeleteICTransaction.setBounds(110, 370, 250, 25);
	        panel.add(adminDeleteICTransaction);
	        adminDeleteICTransaction.setActionCommand("Delete Individual Customer Transaction");
	        adminDeleteICTransaction.addActionListener(new ButtonClickListener());
	        
	        	JButton adminDeletePCTransaction = new JButton("Delete Private Company Transaction");
	        adminDeletePCTransaction.setBounds(110, 400, 250, 25);
	        panel.add(adminDeletePCTransaction);
	        adminDeletePCTransaction.setActionCommand("Delete Private Company Transaction");
	        adminDeletePCTransaction.addActionListener(new ButtonClickListener());
                
                JButton adminDeleteEmployee = new JButton("Delete Employee");
	        adminDeleteEmployee.setBounds(110, 430, 250, 25);
	        panel.add(adminDeleteEmployee);
	        adminDeleteEmployee.setActionCommand("Delete Employee");
	        adminDeleteEmployee.addActionListener(new ButtonClickListener());
	        
	        //===== View Buttons =====
	        
	        JButton adminViewEmployee = new JButton("View Employees");
	        adminViewEmployee.setBounds(110, 480, 250, 25);
	        panel.add(adminViewEmployee);
	        adminViewEmployee.setActionCommand("View Employees");
	        adminViewEmployee.addActionListener(new ButtonClickListener());
	        
	        JButton adminViewICCustomer = new JButton("View Individual Customer");
	        adminViewICCustomer.setBounds(110, 510, 250, 25);
	        panel.add(adminViewICCustomer);
	        adminViewICCustomer.setActionCommand("View Individual Customer");
	        adminViewICCustomer.addActionListener(new ButtonClickListener());
	        
	        JButton adminViewPCCustomer = new JButton("View Private Company");
	        adminViewPCCustomer.setBounds(110, 540, 250, 25);
	        panel.add(adminViewPCCustomer);
	        adminViewPCCustomer.setActionCommand("View Private Company");
	        adminViewPCCustomer.addActionListener(new ButtonClickListener());
	        
	        JButton adminViewICTransaction = new JButton("View Individual Customer Transactions");
	        adminViewICTransaction.setBounds(110, 570, 250, 25);
	        panel.add(adminViewICTransaction);
	        adminViewICTransaction.setActionCommand("View Individual Customer Transactions");
	        adminViewICTransaction.addActionListener(new ButtonClickListener());
	        
	        JButton adminViewPCTransaction = new JButton("View Private Company Transactions");
	        adminViewPCTransaction.setBounds(110, 600, 250, 25);
	        panel.add(adminViewPCTransaction);
	        adminViewPCTransaction.setActionCommand("View Private Company Transactions");
	        adminViewPCTransaction.addActionListener(new ButtonClickListener());
	        
    }
    
    public static class ButtonClickListener implements ActionListener {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String command = e.getActionCommand();
	    		
	    		//===== Create Methods ===== 
	    		
	    		if (command.equals("Create Individual Customer"))
	    		{
	    			createNewICust createNewICust = new createNewICust();
	    		}
                        
                        if (command.equals("Create Company Customer"))
	    		{
                    createNewPCCust createNewPCCust = new createNewPCCust();
	    		}
                        
                        if (command.equals("Create New Employee"))
	    		{
	    			createNewEmployee createNewEmployee = new createNewEmployee();
	    		}
                        
                        if (command.equals("Process Transaction"))
	    		{
                    newTransaction newTransaction = new newTransaction();

	    		}

                //===== Edit Methods =====   
                  
                        if (command.equals("Edit Individual Customer"))
        	    {
                    editIndCustomer editIndCustomer = new editIndCustomer();
        	  	}
                        
                        if (command.equals("Edit Company Customer"))
	    		{
	    			editCompCustomer editCompCustomer = new editCompCustomer();
	    		}
                        
                        if (command.equals("Edit Individual Customer Transaction"))
	    		{
	    			editICTransaction editICTransaction = new editICTransaction();
	    		}
                        
                        /*if (command.equals("Edit Private Company Transaction"))
        	    {
        	    	editPCTransaction editPCTransaction = new editPCTransaction();
        	    }*/
                        
                        if (command.equals("Edit Employee"))
	    		{
	    			editEmployee editEmployee = new editEmployee();
	    		}

                //===== Delete Methods =====  
                        
                        if (command.equals("Delete Individual Customer"))
        	    {
        	    	deleteIndCustomer deleteIndCustomer = new deleteIndCustomer();
        	    }
                            
                        if (command.equals("Delete Company Customer"))
	    		{
	    			deleteCompCustomer deleteCompCustomer = new deleteCompCustomer();
	    		}
                        
                        if (command.equals("Delete Individual Customer Transaction"))
	    		{
	    			deleteICTransaction deleteICTransaction = new deleteICTransaction();
	    		}
                        
                        if (command.equals("Delete Private Company Transaction"))
        	    {
        	    	deletePCTransaction deletePCTransaction = new deletePCTransaction();
        	    }
                        
                        if (command.equals("Delete Employee"))
	    		{
	    			deleteEmployee deleteEmployee = new deleteEmployee();
	    		}
                        
                //===== View Buttons ===== 
                      
                        if (command.equals("View Employees"))
        	    {
        	    	viewEmployee viewEmployee = new viewEmployee();
        	    }
                        
                        if (command.equals("View Individual Customer"))
                {
                    viewIndividualCustomer viewIndividualCustomer = new viewIndividualCustomer();
                }
                        
                        if (command.equals("View Private Company"))
                {
                    viewPrivateCompany viewPrivateCompany = new viewPrivateCompany();
                }
                        
                        if (command.equals("View Individual Customer Transactions"))
                {
                	viewICTransaction viewICTransaction = new viewICTransaction();
                }
                        
                        if (command.equals("View Private Company Transactions"))
                {
                    viewPCTransaction viewPCTransaction = new viewPCTransaction();
                }
            }
    }
    
}