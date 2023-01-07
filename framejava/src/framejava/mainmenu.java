package framejava;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Color;

public class mainmenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainmenu window = new mainmenu();
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
	public mainmenu() {
		initialize();
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
		
		JLabel Electio = new JLabel("");
		Electio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Sections_Elections().setVisible(true);
				frame.dispose();
				
			}
		});
		Electio.setIcon(new ImageIcon("E:\\Java Project\\Gub Election.png"));
		Electio.setBounds(41, 156, 218, 263);
		frame.getContentPane().add(Electio);
		
		JLabel Cadidate = new JLabel("");
		Cadidate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Candidates().setVisible(true);
				frame.dispose();
			}
		});
		Cadidate.setIcon(new ImageIcon("E:\\Java Project\\Candidate.png"));
		Cadidate.setBounds(341, 156, 218, 263);
		frame.getContentPane().add(Cadidate);
		
		JLabel Voter = new JLabel("");
		Voter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			new Voters().setVisible(true);
			frame.dispose();
			}
		});
		Voter.setIcon(new ImageIcon("E:\\Java Project\\Voters.png"));
		Voter.setBounds(649, 156, 218, 263);
		frame.getContentPane().add(Voter);
		
		JLabel Result = new JLabel("");
		Result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				new Dashboard().setVisible(true);
				frame.dispose();
			}
		});
		Result.setIcon(new ImageIcon("E:\\Java Project\\Result.png"));
		Result.setBounds(946, 156, 218, 263);
		frame.getContentPane().add(Result);
		
		JLabel Election = new JLabel("Election");
		Election.setFont(new Font("Tahoma", Font.BOLD, 20));
		Election.setBounds(41, 415, 126, 27);
		frame.getContentPane().add(Election);
		
		JLabel Candidates = new JLabel("Candidates");
		Candidates.setFont(new Font("Tahoma", Font.BOLD, 20));
		Candidates.setBounds(351, 415, 126, 27);
		frame.getContentPane().add(Candidates);
		
		JLabel lblVoters = new JLabel("Voters");
		lblVoters.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblVoters.setBounds(659, 415, 126, 27);
		frame.getContentPane().add(lblVoters);
		
		JLabel lblResult = new JLabel("Result");
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblResult.setBounds(955, 403, 126, 27);
		frame.getContentPane().add(lblResult);
		
		JLabel lblNewLabel_1_3 = new JLabel("CR VOTING SYSTEM", SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1_3.setBounds(396, 36, 313, 73);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JButton BackBtn = new JButton("Back");
		BackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new login().setVisible(true);
				frame.dispose();
			}
		});
		BackBtn.setForeground(Color.BLACK);
		BackBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		BackBtn.setBackground(Color.LIGHT_GRAY);
		BackBtn.setBounds(993, 508, 105, 46);
		frame.getContentPane().add(BackBtn);
		frame.setLocationRelativeTo(null);
		frame.setTitle("CR VOTING SYSTEM");
	}

	public void setVisible(boolean b) {
		mainmenu window = new mainmenu();
		window.frame.setVisible(true);
		
	}
}
