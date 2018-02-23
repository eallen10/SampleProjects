import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class deleteCompCustomer {
    
    public deleteCompCustomer(){
    	
    	JFrame deleteCustomer = new JFrame("Delete Customer");
	        // Setting the width and height of frame
	        deleteCustomer.setSize(400, 180);
	        deleteCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        deleteCustomer.add(panel);

	        // Setting the frame visibility to true
	        deleteCustomer.setLocationRelativeTo(null);
	        deleteCustomer.setVisible(true);

	        
	        placeComponents(panel);
	        deleteCustomer.repaint();
    }
    
    private static void placeComponents(JPanel panel) {
            panel.setLayout(null);
            
            JLabel CustomerIDLabel = new JLabel("Customer ID of Customer to be Deleted: ");
            CustomerIDLabel.setBounds(50, 10, 275, 25);
            panel.add(CustomerIDLabel);
             
            JTextField CustomerID = new JTextField(20);
	    CustomerID.setBounds(280,10,100,25);
            panel.add(CustomerID);
            
	    
	    JButton DeleteCustomerButton = new JButton("Delete Customer");
	    DeleteCustomerButton.setBounds(120, 50, 150, 25);
	    panel.add(DeleteCustomerButton);
	    DeleteCustomerButton.setActionCommand("Delete Customer");
	    DeleteCustomerButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
                            String command = e.getActionCommand();
                            if (command.equals("Delete Customer")){	
                                int id = Integer.parseInt(CustomerID.getText());
                                
                                final String userName = "jcrawf9";//put your MySQL user name 
                                final String password = "Cosc*jdj3";//put your MySQL password 
                            	Connection connection = null;
                                try {  
                                Object newInstance;
                                newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);    
                                Statement stmt=connection.createStatement();
                                int r = stmt.executeUpdate("delete from PRIVATE_COMPANY WHERE C_ID =" + id);
                                int c = stmt.executeUpdate("delete from CUSTOMER WHERE CUSTOMER_ID =" + id);
                                JOptionPane.showMessageDialog(panel, "Delete Successful");
	    			connection.close();  
                                }
                                catch(Exception q) {
                                    System.out.println(q);
                                    JOptionPane.showMessageDialog(panel, "Delete Failed");
                                }

                                
                            }
                        }
            });
  
    }
    
}