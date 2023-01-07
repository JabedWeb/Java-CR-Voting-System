package framejava;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Voters {

	private JFrame frame;
	private JTextField Vname;
	private JTextField StudentId;
	private JComboBox ElectionName ;
	private int Key=-1;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voters window = new Voters();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Voters() {
		initialize();
		connect();
		table_load();
		GetElections();
	}

	   Connection con1;
	   PreparedStatement insert;
	   ResultSet rs;
	public void connect() {
		
	       try {
	           System.out.println("Connected Database2");
	           Class.forName("com.mysql.cj.jdbc.Driver"); 
	             System.out.println("Connected Database3");
	           con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/electiondb", "root", "");    

	       } catch (ClassNotFoundException | SQLException ex) {
	    	   ex.printStackTrace();
	       }
		
	}

	
	private JTable table;;
	  public void table_load()
	    {
	    	try 
	    	{
		    insert = con1.prepareStatement("select * from votertbl");
		    rs = insert.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		    
		   
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	  
	  ///Create 
	  
//	  

	   PreparedStatement insert2;
	   ResultSet rs2;
	 
	  public void GetElections() {
		  
		  try {
	          
	                insert2 = con1.prepareStatement("select * from electiontbl");
	               rs2 = insert2.executeQuery();
	                while (rs2.next()) {
	                	String ElectId = rs2.getString("Ename");
	                	
	                	ElectionName.addItem(ElectId);
	                }
	        } 
		
		 catch (SQLException ex) {
	           
	        }
		  
	  }
	  
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Project\\images.png"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 1220, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1.setBounds(423, 25, 313, 73);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(43, 126, 126, 27);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblName);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(274, 126, 126, 27);
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblStudentId);
		
		Vname = new JTextField();
		Vname.setBounds(43, 187, 204, 38);
		Vname.setColumns(10);
		frame.getContentPane().add(Vname);
		
		StudentId = new JTextField();
		StudentId.setBounds(274, 187, 204, 38);
		StudentId.setColumns(10);
		frame.getContentPane().add(StudentId);
		
		JLabel lblManageCandidates = new JLabel("Manage Voters");
		lblManageCandidates.setBounds(711, 126, 239, 27);
		lblManageCandidates.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblManageCandidates);
		
		JLabel lblElections = new JLabel("Elections");
		lblElections.setBounds(43, 261, 126, 27);
		lblElections.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblElections);
		
		ElectionName = new JComboBox();
		ElectionName.setBounds(43, 298, 204, 38);
		ElectionName.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(ElectionName);
		
		JButton button = new JButton("Add");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(Vname.getText().isEmpty() || StudentId.getText().isEmpty() || ElectionName.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Missing Inforamtion");
				}
				else {
					 String name = Vname.getText();
				       
				       String studentId = StudentId.getText();
				       System.out.println("Connected Database");

				       try {
				   
				           insert = con1.prepareStatement("insert into votertbl (VName,StudentsId,StudentsPass,ElectionName)values(?,?,?,?)");
				           
				            System.out.println("Connected Database5");
				           insert.setString(1, name);
				           insert.setString(2,studentId);
				           insert.setString(3,password.getText());
				           insert.setString(4,ElectionName.getSelectedItem().toString());
				            System.out.println("Connected Database6");
				           insert.executeUpdate();
				        //   JOptionPane.showMessageDialog(null, "Record Saved");
				           table_load();
				           Vname.setText("");
				           StudentId.setText("");
				           password.setText("");
				     
				             

				       } catch ( SQLException ex) {
				           ex.printStackTrace();
				       }
					
					
				}
			}
		});
		button.setBounds(93, 394, 105, 46);
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Dialog", Font.BOLD, 20));
		button.setBackground(new Color(0, 128, 0));
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 try {
					   
			           insert = con1.prepareStatement("update votertbl set VName=? , StudentsId=?, StudentsPass=?, ElectionName =? where VId =?");
			           
			            System.out.println("Connected Database5");
			            insert.setString(1,Vname.getText());
				           insert.setString(2, StudentId.getText());
				           insert.setString(3, password.getText());
				           insert.setString(4,ElectionName.getSelectedItem().toString());
			           insert.setInt(5, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record updated");
			           table_load();
			           Vname.setText("");
			           StudentId.setText("");
			           password.setText("");
			           Vname.requestFocus();
			            
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
			}
		});
		button_1.setBounds(227, 394, 105, 46);
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Dialog", Font.BOLD, 20));
		button_1.setBackground(new Color(255, 0, 128));
		frame.getContentPane().add(button_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				   try {
					   
			           insert = con1.prepareStatement("delete from votertbl where VId = ?");
			     
			           insert.setInt(1, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record deleted");
			           table_load();
			           table_load();
			           Vname.setText("");
			           StudentId.setText("");
			           password.setText("");
			           Vname.requestFocus();
			   
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
				
			}
		});
		btnDelete.setBounds(356, 394, 105, 46);
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 20));
		btnDelete.setBackground(new Color(255, 0, 0));
		frame.getContentPane().add(btnDelete);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(274, 261, 126, 27);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(271, 298, 207, 38);
		frame.getContentPane().add(password);
		
		JButton button_3_1 = new JButton("Back");
		button_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new mainmenu().setVisible(true);
				frame.dispose();
			}
		});
		button_3_1.setForeground(Color.BLACK);
		button_3_1.setFont(new Font("Dialog", Font.BOLD, 20));
		button_3_1.setBackground(Color.LIGHT_GRAY);
		button_3_1.setBounds(197, 484, 105, 46);
		frame.getContentPane().add(button_3_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(572, 187, 612, 432);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int MyIndex =table.getSelectedRow();
				Key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				Vname.setText(model.getValueAt(MyIndex, 1).toString());
				StudentId.setText(model.getValueAt(MyIndex, 2).toString());
				password.setText(model.getValueAt(MyIndex, 3).toString());
				ElectionName.setSelectedItem(model.getValueAt(MyIndex, 4).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		
	}

	public void setVisible(boolean b) {
		Voters window = new Voters();
		window.frame.setVisible(true);
		
	}
}
