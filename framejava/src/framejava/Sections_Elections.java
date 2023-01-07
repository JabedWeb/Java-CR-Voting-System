package framejava;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sections_Elections {

	private JFrame frame;
	private JTextField ElectionNameTb;
	private JTable table;
	private JScrollPane ElectionTable;
	private JLabel lblManageElection;
	private JButton BackBtn;
	private JButton AddBtn;
	private JButton EditBtn;
	private JButton DeleteBtn;
	private int Key=-1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				System.out.println("Connected Database2");
				try {
					Sections_Elections window = new Sections_Elections();
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
	public Sections_Elections() {
		initialize();
		connect();
		table_load();
	}

	Connection con1;
	PreparedStatement insert;
	ResultSet rs;

	public void connect() {
		System.out.println("Connected Database2");
		try {
			System.out.println("Connected Database2");
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connected Database3");
			con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/electiondb", "root", "");

		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void table_load() {
		try {
			insert = con1.prepareStatement("select * from electiontbl");
			rs = insert.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
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
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");

		JLabel lblNewLabel_1 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1.setBounds(448, 40, 313, 73);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(105, 152, 126, 27);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblName);

		ElectionNameTb = new JTextField();
		ElectionNameTb.setBounds(105, 202, 204, 38);
		ElectionNameTb.setColumns(10);
		frame.getContentPane().add(ElectionNameTb);

		ElectionTable = new JScrollPane();
		ElectionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		ElectionTable.setBounds(516, 190, 669, 441);
		frame.getContentPane().add(ElectionTable);

		table = new JTable();
		
		table.addMouseListener(new MouseAdapter() {
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int MyIndex =table.getSelectedRow();
				Key=Integer.valueOf(model.getValueAt(MyIndex, 0).toString());
				ElectionNameTb.setText(model.getValueAt(MyIndex, 1).toString());
				
			}
		});
		ElectionTable.setViewportView(table);

		lblManageElection = new JLabel("Manage Election");
		lblManageElection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblManageElection.setBounds(761, 122, 313, 38);
		frame.getContentPane().add(lblManageElection);

		BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new mainmenu().setVisible(true);
				frame.dispose();
			}
		});
		BackBtn.setForeground(Color.BLACK);
		BackBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		BackBtn.setBackground(Color.LIGHT_GRAY);
		BackBtn.setBounds(174, 422, 105, 46);
		frame.getContentPane().add(BackBtn);

		AddBtn = new JButton("Add");
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = ElectionNameTb.getText();
					try {

						insert = con1.prepareStatement("insert into electiontbl (Ename) values(?)");
						System.out.println("Connected Database5");
						insert.setString(1, name);
						insert.executeUpdate();
						System.out.println("Connected Database6");
						
						System.out.println("Connected Database7");
						table_load();
						System.out.println("Connected Database8");
						ElectionNameTb.setText("");
						System.out.println("Connected Database9");
						ElectionNameTb.requestFocus();

					}
					catch (SQLException ex) {
						
						ex.printStackTrace();
						
					}
				}

		});
		AddBtn.setForeground(new Color(255, 255, 255));
		AddBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		AddBtn.setBackground(new Color(0, 128, 0));
		AddBtn.setBounds(70, 332, 105, 46);
		frame.getContentPane().add(AddBtn);

		EditBtn = new JButton("Edit");
		EditBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		EditBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				 String name = ElectionNameTb.getText();

			       try {
			   
			           insert = con1.prepareStatement("update electiontbl set Ename= ? where EId = ?");
			           
			            System.out.println("Connected Database5");
			            insert.setString(1, name);
			           insert.setInt(2, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record updated");
			           table_load();
			           ElectionNameTb.setText("");
			           ElectionNameTb.requestFocus();
			            
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
				
				
				
			}
		});
		EditBtn.setForeground(new Color(255, 255, 255));
		EditBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		EditBtn.setBackground(new Color(255, 0, 128));
		EditBtn.setBounds(204, 332, 105, 46);
		frame.getContentPane().add(EditBtn);

		DeleteBtn = new JButton("Delete");
		DeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			       try {
			   
			           insert = con1.prepareStatement("delete from electiontbl where EId = ?");
			     
			           insert.setInt(1, Key);
			            System.out.println("Connected Database6");
			           insert.executeUpdate();
//			           JOptionPane.showMessageDialog(null, "Record deleted");
			           table_load();
			           ElectionNameTb.setText("");
			           ElectionNameTb.requestFocus();
			           table_load();
			   
			       } catch ( SQLException ex) {
			           ex.printStackTrace();
			       }
			}
		});
		DeleteBtn.setForeground(new Color(255, 255, 255));
		DeleteBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		DeleteBtn.setBackground(Color.RED);
		DeleteBtn.setBounds(333, 332, 105, 46);
		frame.getContentPane().add(DeleteBtn);
	}

	public void setVisible(boolean b) {
		Sections_Elections window = new Sections_Elections();
		window.frame.setVisible(true);
		
	}
}

