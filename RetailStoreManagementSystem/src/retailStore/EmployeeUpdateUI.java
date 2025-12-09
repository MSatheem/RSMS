package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeUpdateUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfPosition;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	DefaultTableModel model;
	private JTable table;
	Employee employee;
	
	private void clearFields() {
		tfName.setText("");
		tfAddress.setText("");
		tfPosition.setText("");
		tfPhoneNo.setText("");
		tfEmail.setText("");
		employee = null;
	}
	
	private void populateTable() {
		Employee employee = new Employee();
		String name[] = {"Id","Name","Address","ContactNumber","Email"};
		model = new DefaultTableModel(employee.populateTableArray(),name);
		table.setModel(model);
	}
	
	private void populateTextFields(Employee employee) {
		tfName.setText(employee.getName());
		tfAddress.setText(employee.getAddress());
		tfPhoneNo.setText(String.valueOf(employee.getContactNumber()));
		tfEmail.setText(String.valueOf(employee.getEmail()));
		tfPosition.setText(employee.getPosition());
	}
	
	/**
	 * Create the panel.
	 */
	public EmployeeUpdateUI() {
		setLayout(null);
		setBounds(0,0,1000,700);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer.setBounds(275, 70, 450, 440);
		add(addEditCustomer);
		addEditCustomer.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setBounds(10, 38, 105, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblNewLabel);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(10, 92, 105, 24);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblAddress);
		
		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setBounds(10, 283, 105, 24);
		lblPosition.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblPosition);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setBounds(10, 154, 105, 24);
		lblPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblPhoneNo);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(10, 221, 105, 24);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 20));
		addEditCustomer.add(lblEmail);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setBounds(145, 35, 295, 30);
		addEditCustomer.add(tfName);
		tfName.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);
		tfAddress.setBounds(145, 89, 295, 30);
		addEditCustomer.add(tfAddress);
		
		tfPosition = new JTextField();
		tfPosition.setFont(new Font("Arial", Font.BOLD, 20));
		tfPosition.setColumns(10);
		tfPosition.setBounds(145, 280, 295, 30);
		addEditCustomer.add(tfPosition);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		tfPhoneNo.setBounds(145, 151, 295, 30);
		addEditCustomer.add(tfPhoneNo);
		
		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		tfEmail.setBounds(145, 218, 295, 30);
		addEditCustomer.add(tfEmail);
		
		JPanel panelSave = new JPanel();
		panelSave.setBounds(145, 346, 295, 62);
		addEditCustomer.add(panelSave);
		panelSave.setLayout(null);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(true) { //check componenets here
					
				}
				//reading details from customer UI
				employee.setName(tfName.getText());
				employee.setAddress(tfAddress.getText());
				employee.setPosition(tfPosition.getText());
				employee.setContactNumber(Integer.parseInt(tfPhoneNo.getText()));
				employee.setEmail(tfEmail.getText());
				employee.updateEmployee();
				employee = null;
				populateTable();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBounds(169, 14, 99, 33);
		panelSave.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(43, 14, 93, 33);
		panelSave.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Update");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(410, 23, 179, 24);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 549, 867, 130);
		add(scrollPane);
		
		table = new JTable() {
			public  boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				employee = new Employee();
				int rowSelected = table.getSelectedRow();
				int id = Integer.parseInt(String.valueOf(table.getValueAt(rowSelected, 0)));
				employee.setId(id);
				employee = employee.getEmployeeInfo();
				populateTextFields(employee);
			}
		});
		scrollPane.setViewportView(table);
		populateTable();
	}
}
