package retailStore;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class ProductUpdateUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField tfProductName;
	private JTextField tfSupplierId;
	private JTextField tfSupplierName;
	private JTable table;
	DefaultTableModel model;
	Product product;
	private void clearFields() {
		tfProductName.setText("");
		tfSupplierId.setText("");
		tfSupplierName.setText("");
	}
	
	private void populateProductDetailTable() {
		Product product = new Product();
		String name[] = {"Product Id","Name","SupplierId"};
		
		model = new DefaultTableModel(product.populateProductTable(), name);
		table.setModel(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
	}
	
	private void populateFieldForEditing(Product product) {
		tfProductName.setText(product.getName());
		tfSupplierId.setText(String.valueOf(product.getSupplierId()));
	}
	
	private void populateSupplierNameField() {
		Supplier supplier = new Supplier();
		int id;
		
		if(!tfSupplierId.getText().contentEquals("")) { //not empty
			try { //number only allowed
				id = Integer.valueOf(tfSupplierId.getText());
				supplier.setId(id);
				supplier = supplier.getSuppilerDetail();
				tfSupplierName.setText(supplier.getName());
				
			} catch (NumberFormatException e1) {
				tfSupplierName.setText("");
				System.out.println(e1);
			}	
		} else {
			tfSupplierName.setText("");
		}
	}
	/**
	 * Create the panel.
	 */
	public ProductUpdateUI() {
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setBounds(2, 2, 1197, 766);
		
		JPanel addEditCustomer_1 = new JPanel();
		addEditCustomer_1.setBackground(Color.PINK);
		addEditCustomer_1.setLayout(null);
		addEditCustomer_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		addEditCustomer_1.setBounds(373, 111, 450, 305);
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
		tfSupplierId.setEditable(false);
		tfSupplierId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				populateSupplierNameField();
			}
		});
		tfSupplierId.setFont(new Font("Arial", Font.BOLD, 20));
		tfSupplierId.setColumns(10);
		tfSupplierId.setBounds(145, 81, 62, 30);
		addEditCustomer_1.add(tfSupplierId);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		panel_1.setLayout(null);
		panel_1.setBounds(122, 203, 295, 62);
		addEditCustomer_1.add(panel_1);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productName = tfProductName.getText();
				if(!tfProductName.getText().contentEquals("")) {
					product.setName(productName);
					int result = JOptionPane.showConfirmDialog(null, "Are you sure want to save " + product.getName());
					if(result == 0) { //yes selected
						product.update(); //updating product
						populateProductDetailTable();
						clearFields();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Fields Cannot be Empty", "Warning", JOptionPane.WARNING_MESSAGE); 
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton_1.setBounds(143, 14, 124, 33);
		panel_1.add(btnNewButton_1);
		
		JButton btnClear_1 = new JButton("Clear");
		btnClear_1.setForeground(Color.BLUE);
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnClear_1.setBounds(26, 14, 91, 33);
		panel_1.add(btnClear_1);
		
		tfSupplierName = new JTextField();
		tfSupplierName.setEditable(false);
		tfSupplierName.setFont(new Font("Arial", Font.BOLD, 20));
		tfSupplierName.setColumns(10);
		tfSupplierName.setBounds(238, 81, 202, 30);
		addEditCustomer_1.add(tfSupplierName);
		
		JLabel lblNewLabel_2 = new JLabel("Edit Product");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_2.setBounds(525, 49, 146, 33);
		add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(191, 465, 815, 265);
		add(scrollPane);
		
		table = new JTable() {
			public  boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int tableRowSelected = table.getSelectedRow();
				product = new Product();
				int productId = Integer.valueOf(String.valueOf(table.getValueAt(tableRowSelected, 0)));
				product.setId(productId);
				product = product.getProductInfo(productId);
				populateFieldForEditing(product);
				populateSupplierNameField();
			}
		});
		scrollPane.setViewportView(table);
		populateProductDetailTable();

	}
}
