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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProductUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField tfProductName;
	private JTextField tfSupplierId;
	private JTextField tfSupplierName;
	
	
	private void clearFields() {
		tfProductName.setText("");
		tfSupplierId.setText("");
		tfSupplierName.setText("");
	}
	/**
	 * Create the panel.
	 */
	public ProductUI() {
		setLayout(null);
		setBounds(1, 1, 1000, 700);
		
		JPanel addEditCustomer_1 = new JPanel();
		addEditCustomer_1.setLayout(null);
		addEditCustomer_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer_1.setBounds(321, 64, 450, 305);
		add(addEditCustomer_1);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 30, 105, 24);
		addEditCustomer_1.add(lblNewLabel_1);
		
		JLabel lblAddress_1 = new JLabel("Supplier ID :");
		lblAddress_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddress_1.setBounds(10, 87, 125, 24);
		addEditCustomer_1.add(lblAddress_1);
		
		tfProductName = new JTextField();
		tfProductName.setFont(new Font("Arial", Font.BOLD, 20));
		tfProductName.setColumns(10);
		tfProductName.setBounds(145, 23, 295, 30);
		addEditCustomer_1.add(tfProductName);
		
		tfSupplierId = new JTextField();
		tfSupplierId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Supplier supplier = new Supplier();
				int id;
				
				if(!tfSupplierId.getText().contentEquals("")) { //not empty
					try { //number only allowed
						id = Integer.valueOf(tfSupplierId.getText());
						supplier.setId(id);
						if(supplier.isSaved()) {
							supplier = supplier.getSuppilerDetail();
							tfSupplierName.setText(supplier.getName());
						} else {
							tfSupplierName.setText("");
						}
					} catch (NumberFormatException e1) {
						tfSupplierName.setText("");
						System.out.println(e1);
					}	
				} else {
					tfSupplierName.setText("");
				}
			}
		});
		tfSupplierId.setFont(new Font("Arial", Font.BOLD, 20));
		tfSupplierId.setColumns(10);
		tfSupplierId.setBounds(145, 81, 62, 30);
		addEditCustomer_1.add(tfSupplierId);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(122, 203, 295, 62);
		addEditCustomer_1.add(panel_1);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = tfProductName.getText();
				int id = Integer.valueOf(tfSupplierId.getText());
				if(!tfSupplierName.getText().contentEquals("")) {
					Product newProduct = new Product(productName, id);
					clearFields();
					newProduct.saveProduct();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBounds(169, 14, 83, 33);
		panel_1.add(btnNewButton_1);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear_1.setBounds(35, 14, 91, 33);
		panel_1.add(btnClear_1);
		
		tfSupplierName = new JTextField();
		tfSupplierName.setEnabled(false);
		tfSupplierName.setFont(new Font("Arial", Font.BOLD, 20));
		tfSupplierName.setColumns(10);
		tfSupplierName.setBounds(238, 81, 202, 30);
		addEditCustomer_1.add(tfSupplierName);
		
		JLabel lblNewLabel_2 = new JLabel("Product");
		lblNewLabel_2.setBounds(523, 22, 45, 13);
		add(lblNewLabel_2);

	}
}
