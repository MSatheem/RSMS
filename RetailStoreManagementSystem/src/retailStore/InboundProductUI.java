package retailStore;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class InboundProductUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DefaultTableModel model;
	private JScrollPane scrollPane;
	private JComboBox<String> cbSupplier;
	private JComboBox<String> cbProduct;
	DefaultComboBoxModel<String> modelComboBox;
	DefaultComboBoxModel<String> modelComboBox1;
	private JLabel lblNewLabel_1;
	private JLabel lblProductId;
	private JLabel lblPurchasePrice;
	private JTextField tfPurchasePrice;
	InboundProductList productList;
	private JLabel lblBatchNo;
	private JTextField tfBatchNo;
	private JLabel lblExpirydate;
	private JLabel lblExpirydate_1;
	private JTextField tfQuantity;
	JDateChooser dateChooserMfg,dateChooserExp;
	Supplier[] supplier;
	Product[] product;
	
	//populating table
	private void populateTable() {
		String productIn[] = {"Id","Name","Purchase","Batch","Mfg Date","Exp Date", "quantity"};
		model = new DefaultTableModel(productList.listToArray(),productIn);
		table.setModel(model);
	}
	
	private void populateSupplier() {
		supplier = new Supplier().getAllSuppliers();
		String[] dataIn = new String[supplier.length];
		for(int i=0; i<supplier.length; i++) {
			dataIn[i] =  String.valueOf(supplier[i].getId()) + " " + supplier[i].getName() ;
		}
		modelComboBox = new DefaultComboBoxModel<String>(dataIn);
		cbSupplier.setModel(modelComboBox);
		cbSupplier.setSelectedIndex(-1);
	}
	
	private void populateProduct(int supplierId) {
		product = new Product().getAllProducts(supplierId);
		String[] dataIn = new String[product.length];
		for(int i=0; i<product.length; i++) {
			dataIn[i] =  String.valueOf(product[i].getId()) + " " + product[i].getName();
		}
		modelComboBox1 = new DefaultComboBoxModel<String>(dataIn);
		cbProduct.setModel(modelComboBox1);
		cbProduct.setSelectedIndex(-1);
	}
	
	private void clearTable() {
		populateProduct(0);
		cbSupplier.setSelectedIndex(-1);
		productList = new InboundProductList();
		populateTable();
		clearFields();
	}
	
	private void clearFields() {
		tfPurchasePrice.setText(null);
		tfBatchNo.setText(null);
		dateChooserExp.setDate(null);
		dateChooserMfg.setDate(null);
		tfQuantity.setText(null);
	}
	/**
	 * Create the panel.
	 */
	public InboundProductUI() {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBackground(new Color(255, 248, 220));
		setLayout(null);
		setBounds(2, 2, 1197, 766);
		
		productList = new InboundProductList();
		
		
		JButton Add = new JButton("In");
		Add.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id = product[cbProduct.getSelectedIndex()].getId();
					double price = Double.valueOf(tfPurchasePrice.getText());
					int batchNo = Integer.valueOf(tfBatchNo.getText());
					int quantity = Integer.valueOf(tfQuantity.getText());
					java.sql.Date mfgDate = new java.sql.Date(dateChooserMfg.getDate().getTime());
					java.sql.Date expDate = new java.sql.Date(dateChooserExp.getDate().getTime());
					InboundProduct product = new InboundProduct(id);
					boolean status = product.isSaved();
					if(status) {
						product.setPurchasePrice(price);
						product.setBatchNo(batchNo);
						product.setQuantityIn(quantity);
						product.setMfgDate(mfgDate);
						product.setExpDate(expDate);
						productList.addtoList(product);
					}
					populateTable();
					clearFields();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}catch (NullPointerException e2) {
					System.out.println(e2);
				}
			}
		});
		Add.setBounds(849, 313, 107, 35);
		add(Add);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(240, 391, 716, 216);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cbSupplier = new JComboBox<String>();
		cbSupplier.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int supplierIndexSelected = cbSupplier.getSelectedIndex();
				if(supplierIndexSelected > -1) {
					populateProduct(supplier[supplierIndexSelected].getId());
					productList.setSupplierId(supplier[supplierIndexSelected].getId());
					cbSupplier.setEnabled(false);
				}
			}
		});
		cbSupplier.setBounds(409, 90, 166, 40);
		add(cbSupplier);
		
		JLabel lblNewLabel = new JLabel("Supplier");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(240, 89, 104, 40);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Product Inbound");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(492, 28, 213, 40);
		add(lblNewLabel_1);
		
		lblProductId = new JLabel("Product Id");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblProductId.setBounds(624, 89, 104, 40);
		add(lblProductId);
		
		lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPurchasePrice.setBounds(240, 170, 136, 40);
		add(lblPurchasePrice);
		
		tfPurchasePrice = new JTextField();
		tfPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPurchasePrice.setColumns(10);
		tfPurchasePrice.setBounds(409, 171, 166, 40);
		add(tfPurchasePrice);
		
		lblBatchNo = new JLabel("Batch No");
		lblBatchNo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBatchNo.setBounds(624, 173, 136, 35);
		add(lblBatchNo);
		
		tfBatchNo = new JTextField();
		tfBatchNo.setColumns(10);
		tfBatchNo.setBounds(787, 175, 169, 40);
		add(tfBatchNo);
		
		lblExpirydate = new JLabel("Mfg Date");
		lblExpirydate.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpirydate.setBounds(240, 239, 136, 40);
		add(lblExpirydate);
		
		dateChooserMfg = new JDateChooser();
		dateChooserMfg.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateChooserMfg.setDateFormatString("yyyy/MM/dd");
		dateChooserMfg.setBounds(409, 239, 166, 35);
		add(dateChooserMfg);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpiryDate.setBounds(624, 239, 136, 40);
		add(lblExpiryDate);
		
		dateChooserExp = new JDateChooser();
		dateChooserExp.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dateChooserExp.setDateFormatString("yyyy/MM/dd");
		dateChooserExp.setBounds(787, 239, 169, 40);
		add(dateChooserExp);
		
		lblExpirydate_1 = new JLabel("Quantity");
		lblExpirydate_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpirydate_1.setBounds(240, 310, 136, 40);
		add(lblExpirydate_1);
		
		tfQuantity = new JTextField();
		tfQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(409, 311, 166, 40);
		add(tfQuantity);
		
		cbProduct = new JComboBox<String>();
		cbProduct.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cbProduct.setSelectedIndex(-1);
		cbProduct.setBounds(722, 90, 234, 40);
		add(cbProduct);
		
		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productList.Save();
				clearTable();
				cbSupplier.setEnabled(true);
			}
		});
		Save.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Save.setBounds(849, 644, 107, 35);
		add(Save);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				clearFields();
				productList = null;
				productList = new InboundProductList();
				cbSupplier.setEnabled(true);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnClear.setBounds(652, 644, 107, 35);
		add(btnClear);
		populateSupplier();
		
		populateTable();

	}
}
