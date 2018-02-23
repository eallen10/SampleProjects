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

public class deletePCTransaction {
    
    public deletePCTransaction(){
    	
    	JFrame deleteTransaction = new JFrame("Delete Transaction");
	        // Setting the width and height of frame
	        deleteTransaction.setSize(400, 180);
	        deleteTransaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        deleteTransaction.add(panel);

	        // Setting the frame visibility to true
	        deleteTransaction.setLocationRelativeTo(null);
	        deleteTransaction.setVisible(true);

	        
	        placeComponents(panel);
	        deleteTransaction.repaint();
    }
    
    private static void placeComponents(JPanel panel) {
            panel.setLayout(null);
            
            JLabel TransactionIDLabel = new JLabel("Transaction ID of Transaction to be Deleted: ");
            TransactionIDLabel.setBounds(50, 10, 275, 25);
            panel.add(TransactionIDLabel);
             
            JTextField TransactionID = new JTextField(20);
	    TransactionID.setBounds(280,10,100,25);
            panel.add(TransactionID);
            
	    
	    JButton DeleteTransactionButton = new JButton("Delete Transaction");
	    DeleteTransactionButton.setBounds(120, 50, 150, 25);
	    panel.add(DeleteTransactionButton);
	    DeleteTransactionButton.setActionCommand("Delete Transaction");
	    DeleteTransactionButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
                            String command = e.getActionCommand();
                            if (command.equals("Delete Transaction")){	
                                int id = Integer.parseInt(TransactionID.getText());
                                
                                final String userName = "jcrawf9";//put your MySQL user name 
                                final String password = "Cosc*jdj3";//put your MySQL password 
                            	Connection connection = null;
                                try {  
                                Object newInstance;
                                newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password);    
                                Statement stmt=connection.createStatement();
                                int r = stmt.executeUpdate("delete from TRANSACTION WHERE TRANSACTION_ID =" + id);
                                int d = stmt.executeUpdate("delete from FEES WHERE T_ID =" + id);
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