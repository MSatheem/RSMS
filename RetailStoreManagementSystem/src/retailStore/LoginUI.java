package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class LoginUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private Users user;
	
	public void clearFields() {
		tfUserName.setText("");
		passwordField.setText("");
	}
	
	/**
	 * Create the panel.
	 */
	public LoginUI(MainWindow mainWindow) {
		setBackground(new Color(239, 222, 222));
		setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
		setLayout(null);
		setBounds(0,0,1200,800);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(325, 253, 550, 293);
		add(panel);
		panel.setLayout(null);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(299, 57, 176, 30);
		panel.add(tfUserName);
		tfUserName.setFont(new Font("Arial", Font.BOLD, 20));
		tfUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(299, 129, 176, 30);
		panel.add(passwordField);
		passwordField.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(92, 130, 115, 24);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel = new JLabel("User Name :");
		lblNewLabel.setBounds(92, 53, 115, 24);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(119, 207, 87, 33);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(325, 207, 105, 33);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				mainWindow.loginSuccessfull();
				/*if(!tfUserName.getText().isBlank() & !passwordField.getPassword().toString().isBlank()) {
					String userName = tfUserName.getText();
					String password = passwordField.getText();
					user = new Users(userName, password);
					if(user.login()) { //access found
						mainWindow.loginSuccessfull();
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Credentials", "Warning", JOptionPane.WARNING_MESSAGE); 
					}
				} else {
					JOptionPane.showMessageDialog(null, "Fields Cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				user = null;
				clearFields();*/
			}
		});
		btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Retail Store Management System");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 40));
		lblNewLabel_1.setBounds(202, 79, 796, 108);
		add(lblNewLabel_1);
	}
}
