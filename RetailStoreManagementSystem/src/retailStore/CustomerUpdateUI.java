package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerUpdateUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfCity;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	private JTable table;
	DefaultTableModel model;
	Customer customer;
	JPanel panelFields;
	private void clearFields() {
		tfName.setText("");
		tfAddress.setText("");
		tfCity.setText("");
		tfPhoneNo.setText("");
		tfEmail.setText("");
	}
	
	private void populateFieldForEditing(Customer customer) {
		tfName.setText(customer.getName());
		tfAddress.setText(customer.getAddress());
		tfCity.setText(customer.getCity());
		tfPhoneNo.setText(String.valueOf(customer.getContactNumber()));
		tfEmail.setText(customer.getEmail());
	}
	
	private void populateTable() {
		Customer c = new Customer();
		String name[] = {"Id","Name","Address","City","Contact","Email","Join Date"};
		
		model = new DefaultTableModel(c.populateCustomerTable(),name);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(4).setWidth(20);
		table.getColumnModel().getColumn(6).setMinWidth(100);
		table.getColumnModel().getColumn(6).setMaxWidth(130);
		
	}
	
	/**
	 * Create the panel.
	 */
	public CustomerUpdateUI() {
		setBackground(new Color(128, 128, 128));
		setLayout(null);
		setBounds(2,2,1197,766);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setBackground(new Color(211, 211, 211));
		addEditCustomer.setBorder(new LineBorder(new Color(255, 0, 0), 1, true));
		addEditCustomer.setBounds(375, 100, 450, 370);
		add(addEditCustomer);
		addEditCustomer.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 31, 105, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(10, 86, 105, 24);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblAddress);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setBounds(10, 141, 105, 24);
		lblCity.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblCity);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setBounds(10, 196, 105, 24);
		lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(10, 251, 105, 24);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblEmail);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(160, 303, 93, 33);
		addEditCustomer.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(302, 303, 124, 33);
		addEditCustomer.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!ComponentChecker.isFieldsEmpty(panelFields)) { //fields are not empty
					if(Integer.valueOf(tfPhoneNo.getText()) >100000000 && Integer.valueOf(tfPhoneNo.getText()) < 999999999) { //checking whether contact number is 9 digits and correct
						//reading details from customer UI and changing attributes in customer object
						customer.setName(tfName.getText());
						customer.setAddress(tfAddress.getText());
						customer.setCity(tfCity.getText());
						customer.setContactNumber(Integer.parseInt(tfPhoneNo.getText()));
						customer.setEmail(tfEmail.getText());
						int result = JOptionPane.showConfirmDialog(null, "Are you sure want to update " + customer.getName());
						if(result == 0) { //yes selected
							customer.updateCustomer();
							customer = null;
							populateTable();
							clearFields();
						}
					} else { //phone number length is < 9
						JOptionPane.showMessageDialog(null, "Enter correct contact number", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else { //at least on of the field empty 
					JOptionPane.showMessageDialog(null, "Fields Cannot be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		
		panelFields = new JPanel();
		panelFields.setBackground(new Color(211, 211, 211));
		panelFields.setBounds(125, 10, 313, 293);
		addEditCustomer.add(panelFields);
		panelFields.setLayout(null);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(18, 240, 295, 30);
		panelFields.add(tfEmail);
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setBounds(18, 186, 295, 30);
		panelFields.add(tfPhoneNo);
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		
		tfCity = new JTextField();
		tfCity.setBounds(18, 133, 295, 30);
		panelFields.add(tfCity);
		tfCity.setFont(new Font("Arial", Font.BOLD, 20));
		tfCity.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(18, 76, 295, 30);
		panelFields.add(tfAddress);
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);

		tfName = new JTextField();
		tfName.setBounds(18, 20, 295, 30);
		panelFields.add(tfName);
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Updating Customer Details");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(360, 48, 479, 24);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 608, 1114, 144);
		add(scrollPane);
		
		table = new JTable() {
			public  boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tableRowSelected = table.getSelectedRow();
				customer = new Customer();
				int customerId= Integer.valueOf(String.valueOf(table.getValueAt(tableRowSelected, 0)));
				customer.setId(customerId);
				customer = customer.getCustomerDetails(customerId);
				populateFieldForEditing(customer);
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		populateTable();
		

	}
}
