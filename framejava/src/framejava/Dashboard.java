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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard {

	private JFrame frame;
	private JTextField Cname;
	private JTable table;
	private JTextField StudentId;
	private int Key=-1;
	private JLabel VotesLbl;
	private JLabel winnerpercentages;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
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
	public Dashboard() {
		initialize();
		connect() ;
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
	int Winnerid,votes;
	public void GetWinner() {
		
	   	try 
   	{
	   		System.out.print(Key);
	   		System.out.println("ok1");
	 insert = con1.prepareStatement("select CandidateId, Count(CandidateId) from voterstbl where ElectionId="+Key+" Group BY CandidateId ORDER BY CandidateId DESC LIMIT 1 ");
	   rs = insert.executeQuery();

	     if(rs.next())
        {
	    	 Winnerid=rs.getInt(1);
	    	 System.out.println(Winnerid + " winner iD"); 
        }
	} 
   	catch (SQLException e) 
   	 {
   		e.printStackTrace();
   		System.out.print("Not working");
	  } 
		
	}

	
	
	public void GetWinnerdata() {
		
	   	try 
	   	{
	   		
		   		System.out.println("ok1" + Winnerid);
		   	 insert = con1.prepareStatement("select Cname,StudentId from candidatetbl where Cld = ?");
	         insert.setInt(1, Winnerid);
	         rs = insert.executeQuery();

		     while(rs.next())
	        {
		    	 String name = rs.getString(1);
	             String studentID = rs.getString(2);
	             
	             Cname.setText(name);
	             StudentId.setText(studentID);
	        }
		} 
	   	catch (SQLException ex) 
	   	 {
	   		ex.printStackTrace();
	   		System.out.print("Not working");
		  } 
		
	}
	
	public void GetVotes() {
		
	   	try 
   	{
	   		System.out.println("ok1");
	    insert = con1.prepareStatement("select Count(CandidateId) from voterstbl where CandidateId= ?");
	    insert.setInt(1, Winnerid);
	    
	   rs = insert.executeQuery();

	     while(rs.next())
        {
	    	 votes=rs.getInt(1);
	    	 VotesLbl.setText(votes+ " Votes");
	    	 System.out.println("ok "+votes); 
        }
	} 
   	catch (SQLException e) 
   	 {
   		e.printStackTrace();
   		System.out.print("Not working");
	  } 
		
	}
	//vote total percentage
	int totalVotes;
	double winpercentage;
	public void percentage() {
		
	   	try 
   	{
	   		System.out.println("ok1");
	    insert = con1.prepareStatement("select Count(*) from voterstbl where ElectionId= ?");
	    insert.setInt(1, Key);
	    
	   rs = insert.executeQuery();

	     while(rs.next())
        {
	    	 totalVotes=rs.getInt(1);
	    	 winpercentage=(votes*100)/totalVotes;
	    	 winnerpercentages.setText(winpercentage+ "% Votes");
	    	 System.out.println("ok "+votes); 
        }
	} 
   	catch (SQLException e) 
   	 {
   		e.printStackTrace();
   		System.out.print("Not working");
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
		
		JLabel lblNewLabel_1_1 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(431, 40, 313, 73);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblManageCandidates_1 = new JLabel("Welcome In CR Voting System");
		lblManageCandidates_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblManageCandidates_1.setBounds(431, 101, 313, 27);
		frame.getContentPane().add(lblManageCandidates_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(457, 151, 126, 27);
		frame.getContentPane().add(lblName);
		
		Cname = new JTextField();
		Cname.setEditable(false);
		Cname.setColumns(10);
		Cname.setBounds(401, 188, 196, 38);
		frame.getContentPane().add(Cname);
		
		StudentId = new JTextField();
		StudentId.setEditable(false);
		StudentId.setColumns(10);
		StudentId.setBounds(607, 188, 204, 38);
		frame.getContentPane().add(StudentId);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentId.setBounds(634, 151, 126, 27);
		frame.getContentPane().add(lblStudentId);
		
		JLabel lblName_1_1 = new JLabel("Election List");
		lblName_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_1_1.setBounds(1006, 256, 190, 27);
		frame.getContentPane().add(lblName_1_1);
		
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
		button_3_1_1.setBounds(1057, 597, 105, 46);
		frame.getContentPane().add(button_3_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			
		
			
			}
		});
		scrollPane.setBounds(104, 302, 1070, 278);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				int MyIndex =table.getSelectedRow();
				Key=Integer.valueOf(model.getValueAt(MyIndex, 1).toString());
				GetWinner();
				GetWinnerdata();
				GetVotes();
				percentage();
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblName_1_1_1 = new JLabel("Winner");
		lblName_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_1_1_1.setBounds(120, 164, 190, 27);
		frame.getContentPane().add(lblName_1_1_1);
		
		VotesLbl = new JLabel("Vote");
		VotesLbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		VotesLbl.setBounds(986, 151, 190, 27);
		frame.getContentPane().add(VotesLbl);
		
		winnerpercentages = new JLabel("Percentage");
		winnerpercentages.setFont(new Font("Tahoma", Font.BOLD, 20));
		winnerpercentages.setBounds(984, 200, 190, 27);
		frame.getContentPane().add(winnerpercentages);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");
	}

	public void setVisible(boolean b) {
		Dashboard window = new Dashboard();
		window.frame.setVisible(true);
		
	}
}
