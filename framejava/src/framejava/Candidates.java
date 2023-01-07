package framejava;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import net.proteanit.sql.DbUtils;

public class Candidates {
	
	private JFrame frame;
	  private JTextField Cname;
	private JTextField StudentId;
	private JComboBox ElectionName ;
	private int Key=-1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Candidates window = new Candidates();
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
	public Candidates() {
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

	
	   private JTable table;
	  public void table_load()
	    {
	    	try 
	    	{
		    insert = con1.prepareStatement("select * from candidatetbl");
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
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Project\\images.png"));
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 1220, 710);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1.setBounds(448, 40, 313, 73);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(68, 141, 126, 27);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblName);
		
		JLabel lblManageCandidates = new JLabel("Manage Candidates");
		lblManageCandidates.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblManageCandidates.setBounds(736, 141, 239, 27);
		frame.getContentPane().add(lblManageCandidates);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentId.setBounds(282, 141, 126, 27);
		frame.getContentPane().add(lblStudentId);
		
		StudentId = new JTextField();
		StudentId.setColumns(10);
		StudentId.setBounds(282, 202, 204, 38);
		frame.getContentPane().add(StudentId);
		

		ElectionName = new JComboBox();
		ElectionName.setBounds(68, 315, 196, 46);
		frame.getContentPane().add(ElectionName);
		
		JLabel lblElections = new JLabel("Elections");
		lblElections.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblElections.setBounds(68, 261, 126, 27);
		frame.getContentPane().add(lblElections);
		
		JButton button_3_1_1 = new JButton("Back");
		button_3_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new mainmenu().setVisible(true);
				frame.dispose();
			}
		});
		button_3_1_1.setForeground(Color.BLACK);
		button_3_1_1.setFont(new Font("Dialog", Font.BOLD, 20));
		button_3_1_1.setBackground(Color.LIGHT_GRAY);
		button_3_1_1.setBounds(178, 495, 105, 46);
		frame.getContentPane().add(button_3_1_1);
		
		JButton button = new JButton("Add");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				
				if(Cname.getText().isEmpty() || StudentId.getText().isEmpty() || ElectionName.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Missing Inforamtion");
				}
				else {
					 String name = Cname.getText();
				       
				       String studentId = StudentId.getText();
				       System.out.println("Connected Database");

				       try {
				   
				           insert = con1.prepareStatement("insert into candidatetbl (CName,StudentId,ElectionName)values(?,?,?)");
				           
				            System.out.println("Connected Database5");
				           insert.setString(1, name);
				           insert.setString(2,studentId);
				           insert.setString(3,ElectionName.getSelectedItem().toString());
				            System.out.println("Connected Database6");
				           insert.executeUpdate();
				        //   JOptionPane.showMessageDialog(null, "Record Saved");
				           table_load();
				           Cname.setText("");
				           StudentId.setText("");
				           Cname.requestFocus();
				             

				       } catch ( SQLException ex) {
				           ex.printStackTrace();
				       }
					
					
				}
				
			}
		});
		button.setForeground(new Color(255, 255, 255));
		button.setFont(new Font("Dialog", Font.BOLD, 20));
		button.setBackground(new Color(0, 128, 0));
		button.setBounds(74, 405, 105, 46);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Edit");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				

			       try {
			   
			           insert = con1.prepareStatement("update candidatetbl set CName=? , StudentId=?, ElectionName =? where Cld =?");
			           
			            System.out.println("Connected Database5");
			            insert.setString(1,Cname.getText());
				           insert.setString(2, StudentId.getText());
				           insert.setString(3,ElectionName.getSelectedItem().toString());
			           insert.setInt(4, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record updated");
			           table_load();
			           Cname.setText("");
			           StudentId.setText("");
			           Cname.requestFocus();
			            
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
				
				
			}
		});
		button_1.setForeground(new Color(255, 255, 255));
		button_1.setFont(new Font("Dialog", Font.BOLD, 20));
		button_1.setBackground(new Color(255, 0, 128));
		button_1.setBounds(208, 405, 105, 46);
		frame.getContentPane().add(button_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				

			       try {
			   
			           insert = con1.prepareStatement("delete from candidatetbl where Cld = ?");
			     
			           insert.setInt(1, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record deleted");
			           table_load();
			           Cname.setText("");
			           Cname.requestFocus();
			           table_load();
			   
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
			}
		});
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 20));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(337, 405, 105, 46);
		frame.getContentPane().add(btnDelete);
		
		Cname = new JTextField();
		Cname.setBounds(76, 202, 196, 38);
		frame.getContentPane().add(Cname);
		Cname.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(560, 213, 620, 372);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int MyIndex =table.getSelectedRow();
				Key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				Cname.setText(model.getValueAt(MyIndex, 1).toString());
				StudentId.setText(model.getValueAt(MyIndex, 2).toString());
				ElectionName.setSelectedItem(model.getValueAt(MyIndex, 3).toString());
				
			}
		});
		scrollPane.setViewportView(table);

	}

	public void setVisible(boolean b) {
		try {
			Candidates window = new Candidates();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
