package framejava;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

public class login{

	private JFrame frame;
	private JTextField username;
	private JPasswordField password ;
	private JComboBox RoleCb;
	private JLabel authentication_message ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
		connect();
		authentication_message.setVisible(false);
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
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(543, 260, 126, 27);
		frame.getContentPane().add(lblNewLabel);
		
		username = new JTextField();
		username.setBounds(700, 249, 204, 38);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(543, 332, 126, 27);
		frame.getContentPane().add(lblPassword);

		
		 password = new JPasswordField();
		password.setBounds(700, 331, 207, 38);
		frame.getContentPane().add(password);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(RoleCb.getSelectedIndex()==-1) {
					authentication_message.setVisible(true);
					
				}
				else if (RoleCb.getSelectedIndex()==-0) {
					if(username.getText().isEmpty() || password.getText().isEmpty()) {
						
						authentication_message.setVisible(true);

					}
					else if (username.getText().equals("Admin") || password.getText().equals("Password")) {
						
						try {
						 new mainmenu().setVisible(true);
						 frame.dispose();
						
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else {
						authentication_message.setVisible(true);
						username.setText("");
						password.setText("");
						username.requestFocus();
					}
				}
				else {
					//voters login code
					
					try {
						
		                insert = con1.prepareStatement("select * from votertbl where Vname='"+ username.getText() +"' and StudentsPass='"+ password.getText() +"' ");
		               
		                ResultSet rs1 = insert.executeQuery();

		            if(rs1.next())
		            {
		           
		             Voting obj= new Voting();
		             Voting obj2= new Voting(rs1.getInt(1));
		            	int value=rs1.getInt(1);
		            	System.out.println(value);
		             obj.setVisible(true);
		              frame.dispose();    
		            }   
		            else
		            {
		            	authentication_message.setVisible(true);
		            	username.setText("");
						password.setText("");
						username.requestFocus();
		                 
		            }

		        } 
			
			 catch (Exception ex) {
				 authentication_message.setVisible(true);
		        }
					 
				}
				
			}
		});
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		loginBtn.setBounds(807, 421, 99, 38);
		frame.getContentPane().add(loginBtn);
		
		String[] colum= {"Voter","admin"};
		
		RoleCb = new JComboBox(colum);
		RoleCb.setFont(new Font("Tahoma", Font.BOLD, 14));
		RoleCb.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Voter"}));
		RoleCb.setBounds(470, 93, 204, 57);
		frame.getContentPane().add(RoleCb);
		
		JLabel lblNewLabel_1 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(441, 10, 313, 73);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		lblNewLabel_1_1.setIcon(new ImageIcon("E:\\Java Project\\images.png"));
		lblNewLabel_1_1.setBounds(97, 211, 243, 258);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		authentication_message = new JLabel("Authentication Failed");
		authentication_message.setFont(new Font("Tahoma", Font.BOLD, 15));
		authentication_message.setForeground(new Color(255, 0, 0));
		authentication_message.setBounds(700, 379, 204, 27);
		frame.getContentPane().add(authentication_message);
	}

	public void setVisible(boolean b) {
		login window = new login();
		window.frame.setVisible(true);
		
	}
}
