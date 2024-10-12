package retailStore;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private LoginUI loginUI;
	JButton btnNewButton;
	
	public void loginSuccessfull() {
		frame.getContentPane().remove(loginUI);
		frame.getContentPane().setVisible(false);
		AdminFunctionOption adminFunctionOption = new AdminFunctionOption();
		frame.getContentPane().add(adminFunctionOption);
		frame.getContentPane().setVisible(true);
		adminFunctionOption.add(btnNewButton);
	}
	
	public void login() {
		loginUI = new LoginUI(this);
		frame.getContentPane().add(loginUI);
		loginUI.add(btnNewButton);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		
		//starting xampp
		//XamppAuto.startXamppServer();
		
		btnNewButton = new JButton("X");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//closing xampp
				//XamppAuto.closeXamppServer();
				System.exit(0);
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(1113, 0, 85, 30);
		login();
	}
}
