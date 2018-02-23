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

public class deleteEmployee {
    
    public deleteEmployee(){
    	
    	JFrame deleteEmployee = new JFrame("Delete Employee");
	        // Setting the width and height of frame
	        deleteEmployee.setSize(400, 180);
	        deleteEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        deleteEmployee.add(panel);

	        // Setting the frame visibility to true
	        deleteEmployee.setLocationRelativeTo(null);
	        deleteEmployee.setVisible(true);

	        
	        placeComponents(panel);
	        deleteEmployee.repaint();
    }
    
    private static void placeComponents(JPanel panel) {
            panel.setLayout(null);
            
            JLabel EmployeeIDLabel = new JLabel("Employee ID of Employee to be Deleted: ");
            EmployeeIDLabel.setBounds(50, 10, 275, 25);
            panel.add(EmployeeIDLabel);
             
            JTextField EmployeeID = new JTextField(20);
	    EmployeeID.setBounds(280,10,100,25);
            panel.add(EmployeeID);
            
	    
	    JButton DeleteEmployeeButton = new JButton("Delete Employee");
	    DeleteEmployeeButton.setBounds(120, 50, 150, 25);
	    panel.add(DeleteEmployeeButton);
	    DeleteEmployeeButton.setActionCommand("Delete Employee");
	    DeleteEmployeeButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
                            String command = e.getActionCommand();
                            if (command.equals("Delete Employee")){	
                                String id = EmployeeID.getText();
                                
                                final String userName = "jcrawf9";//put your MySQL user name 
                                final String password = "Cosc*jdj3";//put your MySQL password 
                            	Connection connection = null;
                                try {  
                                Object newInstance;
                                newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);    
                                Statement stmt=connection.createStatement();
                                int r = stmt.executeUpdate("delete from EMPLOYEE WHERE EMPLOYEE_ID ='" + id + "'");
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