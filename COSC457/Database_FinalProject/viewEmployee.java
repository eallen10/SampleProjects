import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import static sql.viewTeam.buildTableModel;

public class viewEmployee {
	
	public viewEmployee(){
        JFrame viewEmployee = new JFrame("View Employee");
	        // Setting the width and height of frame
	        viewEmployee.setSize(400, 300);
	        viewEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        /* Creating panel. This is same as a div tag in HTML
	         * We can create several panels and add them to specific 
	         * positions in a JFrame. Inside panels we can add text 
	         * fields, buttons and other components.
	         */
	        JPanel panel = new JPanel();    
	        // adding panel to frame
	        viewEmployee.add(panel);

	        // Setting the frame visibility to true
	        viewEmployee.setLocationRelativeTo(null);
	        viewEmployee.setVisible(true);

	        
	        placeComponents(panel);
	        viewEmployee.repaint();
    }
    
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }

    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            vector.add(rs.getObject(columnIndex));
        }
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    
    private static void placeComponents(JPanel panel) {
            panel.setLayout(null);
            
            JLabel employeeIDLabel = new JLabel("Employee ID of Employee to be viewed: ");
            employeeIDLabel.setBounds(50, 10, 275, 25);
            panel.add(employeeIDLabel);
             
            JTextField employeeID = new JTextField(20);
	    employeeID.setBounds(280,10,100,25);
            panel.add(employeeID);
	    
	    JButton ViewEmployeeButton = new JButton("View Employee");
	    ViewEmployeeButton.setBounds(120, 50, 150, 25);
	    panel.add(ViewEmployeeButton);
	    ViewEmployeeButton.setActionCommand("View Employee");
	    ViewEmployeeButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
                            String command = e.getActionCommand();
                            if (command.equals("View Employee")){	
                                String id = employeeID.getText();
                                
                                final String userName = "jcrawf9";// put your MySQL user name
                            	final String password = "Cosc*jdj3";// put your MySQL password
                            	Connection connection = null;
                            	
                                try {  
                                Object newInstance;
                                newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();  
                                connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/jcrawf9db",userName,password); 
                                Statement stmt=connection.createStatement();
                                ResultSet r = stmt.executeQuery("select * from EMPLOYEE WHERE employee_id = '" + id + "';");
                                JTable table = new JTable(buildTableModel(r));
                                JOptionPane.showMessageDialog(null, new JScrollPane(table),"Result",JOptionPane.INFORMATION_MESSAGE);
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
