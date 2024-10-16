package retailStore;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ProductUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField tfProductName;
	JComboBox<String> cbSupplier;	
	Supplier[] supplier;
	DefaultComboBoxModel<String> modelComboBoxSupplier;
	ProductListTable productListTable;
	
	private void clearFields() {
		tfProductName.setText("");
		cbSupplier.setSelectedIndex(-1); //no item is selected in combo box supplier
	}
	
	
	private void populateSupplier() {
		supplier = new Supplier().getAllSuppliers();
		String[] dataIn = new String[supplier.length];
		for(int i=0; i<supplier.length; i++) {
			dataIn[i] =  String.valueOf(supplier[i].getId()) + " " + supplier[i].getName() ;
		}
		modelComboBoxSupplier = new DefaultComboBoxModel<String>(dataIn);
		cbSupplier.setModel(modelComboBoxSupplier);
		cbSupplier.setSelectedIndex(-1);
	}
	/**
	 * Create the panel.
	 */
	public ProductUI() {
		setBackground(new Color(0, 255, 204));
		setLayout(null);
		setBounds(2, 2, 1197, 766);
		
		JPanel addEditCustomer_1 = new JPanel();
		addEditCustomer_1.setLayout(null);
		addEditCustomer_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer_1.setBounds(88, 230, 450, 305);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(122, 203, 295, 62);
		addEditCustomer_1.add(panel_1);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear_1.setBounds(35, 14, 91, 33);
		panel_1.add(btnClear_1);
		
		JLabel lblNewLabel_2 = new JLabel("Product");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_2.setBounds(487, 89, 223, 71);
		add(lblNewLabel_2);
		
		cbSupplier = new JComboBox<String>();
		cbSupplier.setBounds(146, 79, 294, 40);
		addEditCustomer_1.add(cbSupplier);
		cbSupplier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = tfProductName.getText();
				if(cbSupplier.getSelectedIndex() > -1) { //item selected in combo box
					int supplierId = supplier[cbSupplier.getSelectedIndex()].getId();
					Product product = new Product(productName, supplierId);
					int result = JOptionPane.showConfirmDialog(null, "Are you sure want to save " + product.getName());
					if(result == 0) { //yes selected
						product.saveProduct(); //saving product
						clearFields();
						productListTable.populateTable();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Fields Cannot be Empty", "Warning", JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
		btnSave.setFont(new Font("Arial", Font.BOLD, 20));
		btnSave.setBounds(169, 14, 83, 33);
		panel_1.add(btnSave);
		
		productListTable = new ProductListTable();
		productListTable.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		productListTable.setBounds(626, 230, 481, 405);
		add(productListTable);
		
		populateSupplier();
	
	}
}
