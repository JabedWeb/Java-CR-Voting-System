package framejava;


import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.*;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class userlogin extends JFrame {
	private JTextField studentname;
	private JTextField studentid;
	private JTextField batch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userlogin frame = new userlogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public userlogin() {
		initialize();
		connect();
		table_load();
	}
	
	   Connection con1;
	   PreparedStatement insert;
	   ResultSet rs;
	   private JTable table;
	   private JTextField search;
	
	public void connect() {
		
	       try {
	           System.out.println("Connected Database2");
	           Class.forName("com.mysql.cj.jdbc.Driver"); 
	             System.out.println("Connected Database3");
	           con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "");    

	       } catch (ClassNotFoundException | SQLException ex) {
	    	   ex.printStackTrace();
	       }
		
	}
	
	  public void table_load()
	    {
	    	try 
	    	{
		    insert = con1.prepareStatement("select * from book");
		    rs = insert.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		    
		   
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	  
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1047, 645);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("CR Voting System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(460, 28, 131, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(45, 96, 168, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Student ID");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(45, 187, 168, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Batch");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(45, 281, 168, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
		studentname = new JTextField();
		studentname.setBounds(258, 103, 189, 22);
		contentPane.add(studentname);
		studentname.setColumns(10);
		
		studentid = new JTextField();
		studentid.setColumns(10);
		studentid.setBounds(258, 193, 189, 22);
		contentPane.add(studentid);
		
		batch = new JTextField();
		batch.setColumns(10);
		batch.setBounds(258, 288, 189, 22);
		contentPane.add(batch);
		
		JButton btnsave = new JButton("Save");
		btnsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String name = studentname.getText();
			       String Batch = batch.getText();
			       String studentId = studentid.getText();
			       System.out.println("Connected Database");

			       try {
			   
			           insert = con1.prepareStatement("insert into book (Name,Batch,StudentId)values(?,?,?)");
			           
			            System.out.println("Connected Database5");
			           insert.setString(1, name);
			           insert.setString(2, Batch);
			           insert.setString(3, studentId);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
			           JOptionPane.showMessageDialog(null, "Record Saved");
			           table_load();
			           studentname.setText("");
			           batch.setText("");
			           studentid.setText("");
			           studentname.requestFocus();
			             

			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
			}
		});
		btnsave.setBounds(90, 386, 102, 39);
		contentPane.add(btnsave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    studentname.setText("");
		           batch.setText("");
		           studentid.setText("");
		           studentname.requestFocus();
			}
		});
		btnClear.setBounds(219, 386, 102, 39);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(476, 96, 530, 352);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				 String name = studentname.getText();
			       String Batch = batch.getText();
			       String studentId = studentid.getText();
			       String Search=search.getText();

			       try {
			   
			           insert = con1.prepareStatement("update book set Name= ?,Batch=?,StudentId=? where StudentId = ?");
			           
			            System.out.println("Connected Database5");
			           insert.setString(1, name);
			           insert.setString(2, Batch);
			           insert.setString(3, studentId);
			           insert.setString(4, Search);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
			           JOptionPane.showMessageDialog(null, "Record updated");
			           table_load();
			           studentname.setText("");
			           batch.setText("");
			           studentid.setText("");
			           studentname.requestFocus();
			            
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
				
				
				
				
				
			}
		});
		btnUpdate.setBounds(501, 481, 102, 39);
		contentPane.add(btnUpdate);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				   String Search=search.getText();

			       try {
			   
			           insert = con1.prepareStatement("delete from book where StudentId = ?");
			     
			           insert.setString(1, Search);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
			           JOptionPane.showMessageDialog(null, "Record deleted");
			           table_load();
			           studentname.setText("");
			           batch.setText("");
			           studentid.setText("");
			           studentname.requestFocus();
			             

			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
				
				
				
				
			}
		});
		btndelete.setBounds(651, 481, 102, 39);
		contentPane.add(btndelete);
		
		JPanel panel = new JPanel();
		panel.setBounds(90, 538, 367, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel searchID = new JLabel("Search ID");
		searchID.setBounds(38, 10, 106, 32);
		searchID.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(searchID);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			          
			            String id = search.getText();

			                insert = con1.prepareStatement("select Name,Batch,StudentId from book where StudentId = ?");
			                insert.setString(1, id);
			                ResultSet rs = insert.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String name = rs.getString(1);
			                String Batch = rs.getString(2);
			                String studentID = rs.getString(3);
			                
			                studentname.setText(name);
			                studentid.setText(studentID);
			                batch.setText(Batch);
			                
			                
			            }   
			            else
			            {
			            	studentname.setText("");
			            	studentid.setText("");
			            	batch.setText("");
			                 
			            }

			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		search.setBounds(169, 10, 173, 32);
		search.setColumns(10);
		panel.add(search);
		
		JButton btnexit = new JButton("Exit");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnexit.setActionCommand("Exit");
		btnexit.setBounds(345, 386, 102, 39);
		contentPane.add(btnexit);
	}
}
