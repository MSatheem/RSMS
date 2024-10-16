package retailStore;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SupplierUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	private JTextField tfContactPerson;
	SupplierListTable supplierlistTable = new SupplierListTable();
	JPanel panelField;
	
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
		setBackground(new Color(127, 255, 212));
		setLayout(null);
		setBounds(2, 2, 1197, 766);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setLayout(null);
		addEditCustomer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer.setBounds(46, 133, 562, 422);
		add(addEditCustomer);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(32, 34, 170, 24);
		addEditCustomer.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddress.setBounds(32, 92, 170, 24);
		addEditCustomer.add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		lblPhoneNo.setBounds(32, 150, 170, 24);
		addEditCustomer.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		lblEmail.setBounds(32, 208, 170, 24);
		addEditCustomer.add(lblEmail);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(234, 330, 295, 62);
		addEditCustomer.add(panel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ComponentChecker.isFieldsEmpty(panelField)) { // all fields populated
					String name = tfName.getText();
					String address = tfAddress.getText();
					String contactPerson = tfContactPerson.getText();
					int phoneNo = Integer.valueOf(tfPhoneNo.getText());
					String email = tfEmail.getText();
					Supplier supplier = new Supplier(name, address, email, phoneNo, contactPerson);
					if(phoneNo >100000000 && phoneNo < 999999999) {
						int result = JOptionPane.showConfirmDialog(btnSave, "Are you sure want to save " + supplier.getName());
						if(result == 0) {
							supplier.saveNewSupplier();
							clearFields();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Enter correct contact number", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Fields Cannot be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(169, 14, 83, 33);
		panel.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(27, 14, 99, 33);
		panel.add(btnClear);
		
		JLabel lblContactPerson = new JLabel("Contact Person:");
		lblContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		lblContactPerson.setBounds(32, 266, 170, 24);
		addEditCustomer.add(lblContactPerson);
		
		panelField = new JPanel();
		panelField.setBounds(212, 10, 340, 306);
		addEditCustomer.add(panelField);
		panelField.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(23, 21, 295, 30);
		panelField.add(tfName);
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(23, 79, 295, 30);
		panelField.add(tfAddress);
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setBounds(23, 137, 295, 30);
		panelField.add(tfPhoneNo);
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(23, 195, 295, 30);
		panelField.add(tfEmail);
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		
		tfContactPerson = new JTextField();
		tfContactPerson.setBounds(23, 253, 295, 30);
		panelField.add(tfContactPerson);
		tfContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		tfContactPerson.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Add New Supplier");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(448, 10, 300, 113);
		add(lblNewLabel_1);
		
		JPanel panelSupplierTable = new JPanel();
		panelSupplierTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panelSupplierTable.setBounds(654, 133, 412, 469);
		add(panelSupplierTable);
		panelSupplierTable.setLayout(null);
		supplierlistTable.setBounds(7, 5, 398, 459);
		panelSupplierTable.add(supplierlistTable);
		panel.setVisible(true);
		

	}
}
