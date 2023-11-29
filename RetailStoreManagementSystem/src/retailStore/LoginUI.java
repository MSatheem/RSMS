package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfUserName;
	private JPasswordField passwordField;
	
	public void clearFields() {
		tfUserName.setText("");
		passwordField.setText("");
	}
	
	/**
	 * Create the panel.
	 */
	public LoginUI() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(322, 178, 115, 24);
		add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Arial", Font.BOLD, 20));
		lblPassword.setBounds(322, 245, 115, 24);
		add(lblPassword);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Arial", Font.BOLD, 20));
		tfUserName.setBounds(471, 175, 176, 30);
		add(tfUserName);
		tfUserName.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.BOLD, 20));
		passwordField.setBounds(471, 242, 176, 30);
		add(passwordField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 20));
		btnLogin.setBounds(560, 321, 87, 33);
		add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(447, 321, 87, 33);
		add(btnClear);
		
	}
}
