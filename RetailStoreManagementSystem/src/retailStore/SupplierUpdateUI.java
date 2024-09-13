package retailStore;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SupplierUpdateUI extends JPanel {

	private static final long serialVersionUID = 1L;
	JPanel panelTextFields;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfPhoneNo;
	private JTextField tfEmail;
	private JTextField tfContactPerson;
	private JTable table;
	DefaultTableModel model;
	
	private void clearFields() {
		tfName.setText("");
		tfAddress.setText("");
		tfPhoneNo.setText("");
		tfEmail.setText("");
		tfContactPerson.setText("");
	}
	
	private void populateTable() {
		Supplier suppliers = new Supplier();
		String name[] = {"Id","Name","Address", "Email", "Phone","Contact Person"};
		
		model = new DefaultTableModel(suppliers.populateSupplierTable(),name);
		table.setModel(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		
	}
	
	private void disableTextFields(boolean isEnabled) {
		Component[] component = panelTextFields.getComponents();
		for(Component comp : component) {
			comp.setEnabled(isEnabled);
		}
	}
	
	
	/**
	 * Create the panel.
	 */
	public SupplierUpdateUI() {
		setLayout(null);
		setBounds(1, 1, 1000, 700);
		
		JPanel addEditCustomer = new JPanel();
		addEditCustomer.setLayout(null);
		addEditCustomer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer.setBounds(219, 63, 562, 422);
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
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(null);
		panelBtn.setBounds(205, 329, 295, 62);
		addEditCustomer.add(panelBtn);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ComponentChecker.isFieldsEmpty(panelTextFields)) {
					String name = tfName.getText();
					String address = tfAddress.getText();
					String contactPerson = tfContactPerson.getText();
					int phoneNo = Integer.valueOf(tfPhoneNo.getText());
					String email = tfEmail.getText();
					Supplier supplier = new Supplier(name, address, email, phoneNo, contactPerson);
					
					JOptionPane.showConfirmDialog(btnUpdate, "Are you sure want to save " + supplier.toString());
					
					supplier.saveNewSupplier();
					
					clearFields();
					populateTable();
				} else {
					System.out.println("Not empty");
				}
				
							}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 20));
		btnUpdate.setBounds(162, 14, 106, 33);
		panelBtn.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear.setBounds(27, 14, 99, 33);
		panelBtn.add(btnClear);
		
		JLabel lblContactPerson = new JLabel("Contact Person:");
		lblContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		lblContactPerson.setBounds(10, 258, 166, 24);
		addEditCustomer.add(lblContactPerson);
		
		panelTextFields = new JPanel();
		panelTextFields.setBounds(174, 1, 387, 319);
		addEditCustomer.add(panelTextFields);
		panelTextFields.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(30, 28, 295, 30);
		panelTextFields.add(tfName);
		tfName.setFont(new Font("Arial", Font.BOLD, 20));
		tfName.setColumns(10);
		
		tfAddress = new JTextField();
		tfAddress.setBounds(30, 85, 295, 30);
		panelTextFields.add(tfAddress);
		tfAddress.setFont(new Font("Arial", Font.BOLD, 20));
		tfAddress.setColumns(10);
		
		tfPhoneNo = new JTextField();
		tfPhoneNo.setBounds(30, 142, 295, 30);
		panelTextFields.add(tfPhoneNo);
		tfPhoneNo.setFont(new Font("Arial", Font.BOLD, 20));
		tfPhoneNo.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(30, 199, 295, 30);
		panelTextFields.add(tfEmail);
		tfEmail.setFont(new Font("Arial", Font.BOLD, 20));
		tfEmail.setColumns(10);
		
		tfContactPerson = new JTextField();
		tfContactPerson.setBounds(30, 256, 295, 30);
		panelTextFields.add(tfContactPerson);
		tfContactPerson.setFont(new Font("Arial", Font.BOLD, 20));
		tfContactPerson.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(444, 22, 111, 37);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 541, 660, 110);
		add(scrollPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		disableTextFields(false);
		populateTable();
		
	}
}
