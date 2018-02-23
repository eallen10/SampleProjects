import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import static sql.operatorLogin.placeComponents;

public class operatorMenu {
	
    public operatorMenu() {
        // Creating instance of JFrame
	    	

	        JFrame operatorMenu = new JFrame("Operator Menu");
	        // Setting the width and height of frame
	        operatorMenu.setSize(490, 350);
	        operatorMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        operatorMenu.add(panel);

	        // Setting the frame visibility to true
	        operatorMenu.setLocationRelativeTo(null);
	        operatorMenu.setVisible(true);

	        
	        placeComponents(panel);
	        operatorMenu.repaint();
	    
    }
    
    public static void placeComponents(JPanel panel) {

	        panel.setLayout(null);
            
	        //===== Create Buttons ===== 
	        
            /*    JButton newICust = new JButton("Create Individual Customer");
                newICust.setBounds(110, 20, 190, 25);
	        panel.add(newICust);
	        newICust.setActionCommand("Create Individual Customer");
	        newICust.addActionListener(new ButtonClickListener());
                
                JButton newCCust = new JButton("Create Company Customer");
                newCCust.setBounds(110, 50, 190, 25);
	        panel.add(newCCust);
	        newCCust.setActionCommand("Create Company Customer");
	        newCCust.addActionListener(new ButtonClickListener());
                
                JButton newEmployee = new JButton("Create New Employee");
	        newEmployee.setBounds(110, 80, 190, 25);
	        panel.add(newEmployee);
	        newEmployee.setActionCommand("Create New Employee");
	        newEmployee.addActionListener(new ButtonClickListener());
            */    
                JButton processTransaction = new JButton("Process Transaction");
                processTransaction.setBounds(110, 50, 250, 25);
	        panel.add(processTransaction);
	        processTransaction.setActionCommand("Process Transaction");
	        processTransaction.addActionListener(new ButtonClickListener());
	        
	        panel.getRootPane().setDefaultButton(processTransaction);
	        processTransaction.requestFocus();
            
	        //===== Edit Buttons ===== 
                
	        /*	JButton operatorEditIndCustomer = new JButton("Edit Individual Customer");
	        operatorEditIndCustomer.setBounds(150, 150, 120, 25);
	        panel.add(operatorEditIndCustomer);
	        operatorEditIndCustomer.setActionCommand("Edit Individual Customer");
	        operatorEditIndCustomer.addActionListener(new ButtonClickListener());
	        
                JButton operatorEditCompCustomer = new JButton("Edit Company Customer");
	        operatorEditCompCustomer.setBounds(150, 180, 120, 25);
	        panel.add(operatorEditCompCustomer);
	        operatorEditCompCustomer.setActionCommand("Edit Company Customer");
	        operatorEditCompCustomer.addActionListener(new ButtonClickListener());
                
                JButton operatorEditICTransaction = new JButton("Edit Individual Customer Transaction");
	        operatorEditICTransaction.setBounds(150, 210, 120, 25);
	        panel.add(operatorEditICTransaction);
	        operatorEditICTransaction.setActionCommand("Edit Individual Customer Transaction");
	        operatorEditICTransaction.addActionListener(new ButtonClickListener());
	        
	        	JButton operatorEditPCTransaction = new JButton("Edit Private Company Transaction");
	        operatorEditPCTransaction.setBounds(150, 240, 120, 25);
	        panel.add(operatorEditPCTransaction);
	        operatorEditPCTransaction.setActionCommand("Edit Private Company Transaction");
	        operatorEditPCTransaction.addActionListener(new ButtonClickListener());
                
                JButton operatorEditEmployee = new JButton("Edit Employee");
	        operatorEditEmployee.setBounds(150, 270, 120, 25);
	        panel.add(operatorEditEmployee);
	        operatorEditEmployee.setActionCommand("Edit Employee");
	        operatorEditEmployee.addActionListener(new ButtonClickListener());
            */
	        //===== Delete Buttons ===== 
	        /*
	        	JButton operatorDeleteIndCustomer = new JButton("Delete Individual Customer");
	        operatorDeleteIndCustomer.setBounds(150, 310, 120, 25);
	        panel.add(operatorDeleteIndCustomer);
	        operatorDeleteIndCustomer.setActionCommand("Delete Individual Customer");
	        operatorDeleteIndCustomer.addActionListener(new ButtonClickListener());     
	        
                JButton operatorDeleteCompCustomer = new JButton("Delete Company Customer");
	        operatorDeleteCompCustomer.setBounds(150, 340, 120, 25);
	        panel.add(operatorDeleteCompCustomer);
	        operatorDeleteCompCustomer.setActionCommand("Delete Company Customer");
	        operatorDeleteCompCustomer.addActionListener(new ButtonClickListener());
                
                JButton operatorDeleteICTransaction = new JButton("Delete Individual Customer Transaction");
	        operatorDeleteICTransaction.setBounds(150, 370, 120, 25);
	        panel.add(operatorDeleteICTransaction);
	        operatorDeleteICTransaction.setActionCommand("Delete Individual Customer Transaction");
	        operatorDeleteICTransaction.addActionListener(new ButtonClickListener());
	        
	        	JButton operatorDeletePCTransaction = new JButton("Delete Private Company Transaction");
	        operatorDeletePCTransaction.setBounds(150, 400, 120, 25);
	        panel.add(operatorDeletePCTransaction);
	        operatorDeletePCTransaction.setActionCommand("Delete Private Company Transaction");
	        operatorDeletePCTransaction.addActionListener(new ButtonClickListener());
                
                JButton operatorDeleteEmployee = new JButton("Delete Employee");
	        operatorDeleteEmployee.setBounds(150, 430, 120, 25);
	        panel.add(operatorDeleteEmployee);
	        operatorDeleteEmployee.setActionCommand("Delete Employee");
	        operatorDeleteEmployee.addActionListener(new ButtonClickListener());
	        */
	        //===== View Buttons =====
	        
	        JButton operatorViewEmployee = new JButton("View Employees");
	        operatorViewEmployee.setBounds(110, 100, 250, 25);
	        panel.add(operatorViewEmployee);
	        operatorViewEmployee.setActionCommand("View Employees");
	        operatorViewEmployee.addActionListener(new ButtonClickListener());
	        
	        JButton operatorViewICCustomer = new JButton("View Individual Customer");
	        operatorViewICCustomer.setBounds(110, 130, 250, 25);
	        panel.add(operatorViewICCustomer);
	        operatorViewICCustomer.setActionCommand("View Individual Customer");
	        operatorViewICCustomer.addActionListener(new ButtonClickListener());
	        
	        JButton operatorViewPCCustomer = new JButton("View Private Company");
	        operatorViewPCCustomer.setBounds(110, 160, 250, 25);
	        panel.add(operatorViewPCCustomer);
	        operatorViewPCCustomer.setActionCommand("View Private Company");
	        operatorViewPCCustomer.addActionListener(new ButtonClickListener());
	        
	        JButton operatorViewICTransaction = new JButton("View Individual Customer Transactions");
	        operatorViewICTransaction.setBounds(110, 190, 250, 25);
	        panel.add(operatorViewICTransaction);
	        operatorViewICTransaction.setActionCommand("View Individual Customer Transactions");
	        operatorViewICTransaction.addActionListener(new ButtonClickListener());
	        
	        JButton operatorViewPCTransaction = new JButton("View Private Company Transactions");
	        operatorViewPCTransaction.setBounds(110, 220, 250, 25);
	        panel.add(operatorViewPCTransaction);
	        operatorViewPCTransaction.setActionCommand("View Private Company Transactions");
	        operatorViewPCTransaction.addActionListener(new ButtonClickListener());
	        
    }
    
    public static class ButtonClickListener implements ActionListener {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String command = e.getActionCommand();
	    		
	    		//===== Create Methods ===== 
	    		/*
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
                */        
                        if (command.equals("Process Transaction"))
	    		{
                    newTransaction newTransaction = new newTransaction();

	    		}

                //===== Edit Methods =====   
                /*  
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
                        
                        if (command.equals("Edit Private Company Transaction"))
        	    {
        	    	editPCTransaction editPCTransaction = new editPCTransaction();
        	    }
                        
                        if (command.equals("Edit Employee"))
	    		{
	    			editEmployee editEmployee = new editEmployee();
	    		}
				*/
                //===== Delete Methods =====  
                /*        
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
                */        
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