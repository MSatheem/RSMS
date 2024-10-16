package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CustomerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfCity;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	private JDateChooser joinDate;
	private Date dateToday;
	DefaultTableModel model;
	
	private void clearFields() {
		tfName.setText("");
		tfAddress.setText("");
		tfCity.setText("");
		tfPhoneNo.setText("");
		tfEmail.setText("");
		joinDate.setDate(dateToday);
	}
	
	private void populateTable() {
		Customer c = new Customer();
		String name[] = {"Id","Name","Address","City","ContactNumber","Email","Join Date"};
		model = new DefaultTableModel(c.populateCustomerTable(),name);
	}
	
	/**
	 * Create the panel.
	 */
	public CustomerUI() {
		setBackground(new Color(250, 235, 215));
		setLayout(null);
		setBounds(2,2,1197,766);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setBackground(new Color(255, 218, 185));
		addEditCustomer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer.setBounds(142, 163, 450, 440);
		add(addEditCustomer);
		addEditCustomer.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 30, 105, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(10, 87, 105, 24);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblAddress);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setBounds(10, 144, 105, 24);
		lblCity.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblCity);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setBounds(10, 201, 105, 24);
		lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(10, 258, 105, 24);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblEmail);
		
		JLabel lblJoiningDate = new JLabel("Join Date :");
		lblJoiningDate.setBounds(10, 315, 105, 24);
		lblJoiningDate.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblJoiningDate);
		
		dateToday = new Date();
		
		joinDate = new JDateChooser();
		joinDate.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 20));
		joinDate.setBounds(152, 315, 130, 19);
		joinDate.setDate(dateToday);
		addEditCustomer.add(joinDate);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setBounds(145, 23, 295, 30);
		addEditCustomer.add(tfName);
		tfName.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);
		tfAddress.setBounds(145, 81, 295, 30);
		addEditCustomer.add(tfAddress);
		
		tfCity = new JTextField();
		tfCity.setFont(new Font("Arial", Font.BOLD, 20));
		tfCity.setColumns(10);
		tfCity.setBounds(145, 144, 295, 30);
		addEditCustomer.add(tfCity);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		tfPhoneNo.setBounds(145, 201, 295, 30);
		addEditCustomer.add(tfPhoneNo);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		tfEmail.setBounds(145, 258, 295, 30);
		addEditCustomer.add(tfEmail);
		
		JPanel panelSave = new JPanel();
		panelSave.setBackground(new Color(250, 235, 215));
		panelSave.setBounds(145, 368, 295, 62);
		addEditCustomer.add(panelSave);
		panelSave.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(new Color(0, 128, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reading details from customer UI
				String name = tfName.getText();
				String address = tfAddress.getText();
				String city = tfCity.getText();
				int phoneNo = Integer.parseInt(tfPhoneNo.getText());
				String email = tfEmail.getText();
				java.sql.Date dateIn = new java.sql.Date(joinDate.getDate().getTime());
				
				//creating customer object
				Customer customer = new Customer(name,address,city,phoneNo,email,dateIn);
				int result = JOptionPane.showConfirmDialog(btnSave, "Are you sure want to save " + customer.getName() + "," + customer.getContactNumber());
				if(result == 1) {
					customer.saveNewCustomer();
					clearFields();
				}
				populateTable();	
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(169, 14, 83, 33);
		panelSave.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(255, 0, 0));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(43, 14, 93, 33);
		panelSave.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Customer");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(552, 61, 92, 24);
		add(lblNewLabel_1);
		
		CustomerListTable customerListTable = new CustomerListTable();
		customerListTable.setBounds(734, 221, 319, 324);
		add(customerListTable);
		populateTable();
	}
}
