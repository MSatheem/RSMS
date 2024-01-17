package retailStore;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SupplierUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	private JTextField tfContactPerson;

	
	private void clearFields() {
		tfName.setText("");
		tfAddress.setText("");
		tfPhoneNo.setText("");
		tfEmail.setText("");
		tfContactPerson.setText("");
	}
	
	/**
	 * Create the panel.
	 */
	public SupplierUI() {
		setLayout(null);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setLayout(null);
		addEditCustomer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer.setBounds(162, 63, 562, 422);
		add(addEditCustomer);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 30, 105, 24);
		addEditCustomer.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddress.setBounds(10, 87, 105, 24);
		addEditCustomer.add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhoneNo.setBounds(10, 144, 105, 24);
		addEditCustomer.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail.setBounds(10, 201, 105, 24);
		addEditCustomer.add(lblEmail);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setColumns(10);
		tfName.setBounds(205, 30, 295, 30);
		addEditCustomer.add(tfName);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);
		tfAddress.setBounds(205, 88, 295, 30);
		addEditCustomer.add(tfAddress);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		tfPhoneNo.setBounds(205, 151, 295, 30);
		addEditCustomer.add(tfPhoneNo);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		tfEmail.setBounds(205, 208, 295, 30);
		addEditCustomer.add(tfEmail);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(145, 327, 295, 62);
		addEditCustomer.add(panel);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String address = tfAddress.getText();
				String contactPerson = tfContactPerson.getText();
				int phoneNo = Integer.valueOf(tfPhoneNo.getText());
				String email = tfEmail.getText();
				
				Supplier supplier = new Supplier(name, address, email, phoneNo, contactPerson);
				supplier.saveNewSupplier();
				clearFields();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(169, 14, 83, 33);
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(27, 14, 99, 33);
		panel.add(btnClear);
		
		tfContactPerson = new JTextField();
		tfContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		tfContactPerson.setColumns(10);
		tfContactPerson.setBounds(205, 265, 295, 30);
		addEditCustomer.add(tfContactPerson);
		
		JLabel lblContactPerson = new JLabel("Contact Person:");
		lblContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		lblContactPerson.setBounds(10, 258, 166, 24);
		addEditCustomer.add(lblContactPerson);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier");
		lblNewLabel_1.setBounds(388, 22, 111, 37);
		add(lblNewLabel_1);

	}
}
