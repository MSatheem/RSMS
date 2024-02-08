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
		supplier = new Supplier().getSupplierList();
		String[] dataIn = new String[supplier.length];
		for(int i=0; i<supplier.length; i++) {
			dataIn[i] =  String.valueOf(supplier[i].getId()) + " " + supplier[i].getName() ;
		}
		modelComboBox = new DefaultComboBoxModel<String>(dataIn);
		cbSupplier.setModel(modelComboBox);
		cbSupplier.setSelectedIndex(-1);
	}
	
	private void populateProduct(int supplierId) {
		System.out.println(supplierId);
		product = new Product().getAllProducts(supplierId);
		String[] dataIn = new String[product.length];
		for(int i=0; i<product.length; i++) {
			dataIn[i] =  String.valueOf(product[i].getId()) + " " + product[i].getName();
		}
		modelComboBox1 = new DefaultComboBoxModel<String>(dataIn);
		cbProduct.setModel(modelComboBox1);
		cbProduct.setSelectedIndex(-1);
	}
	/**
	 * Create the panel.
	 */
	public InboundProductUI() {
		setLayout(null);
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
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}catch (NullPointerException e2) {
					System.out.println(e2);
					//e2.printStackTrace();
				}
			}
		});
		Add.setBounds(533, 315, 107, 35);
		add(Add);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 391, 618, 216);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		cbSupplier = new JComboBox<String>();
		cbSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int supplierIndexSelected = cbSupplier.getSelectedIndex();
				if(supplierIndexSelected > -1) {
					populateProduct(supplier[supplierIndexSelected].getId());
				}
			}
		});
		cbSupplier.setBounds(160, 79, 166, 21);
		add(cbSupplier);
		
		JLabel lblNewLabel = new JLabel("Supplier");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(46, 68, 104, 35);
		add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Product Inbound");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(303, 20, 196, 35);
		add(lblNewLabel_1);
		
		lblProductId = new JLabel("Product Id");
		lblProductId.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblProductId.setBounds(46, 110, 104, 35);
		add(lblProductId);
		
		lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPurchasePrice.setBounds(46, 171, 136, 35);
		add(lblPurchasePrice);
		
		tfPurchasePrice = new JTextField();
		tfPurchasePrice.setColumns(10);
		tfPurchasePrice.setBounds(192, 171, 111, 31);
		add(tfPurchasePrice);
		
		lblBatchNo = new JLabel("Batch No");
		lblBatchNo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBatchNo.setBounds(391, 171, 136, 35);
		add(lblBatchNo);
		
		tfBatchNo = new JTextField();
		tfBatchNo.setColumns(10);
		tfBatchNo.setBounds(558, 171, 111, 31);
		add(tfBatchNo);
		
		lblExpirydate = new JLabel("Mfg Date");
		lblExpirydate.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpirydate.setBounds(46, 239, 136, 35);
		add(lblExpirydate);
		
		dateChooserMfg = new JDateChooser();
		dateChooserMfg.setDateFormatString("yyyy/MM/dd");
		dateChooserMfg.setBounds(192, 239, 136, 35);
		add(dateChooserMfg);
		
		JLabel lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpiryDate.setBounds(391, 239, 136, 35);
		add(lblExpiryDate);
		
		dateChooserExp = new JDateChooser();
		dateChooserExp.setDateFormatString("yyyy/MM/dd");
		dateChooserExp.setBounds(533, 239, 136, 35);
		add(dateChooserExp);
		
		lblExpirydate_1 = new JLabel("Quantity");
		lblExpirydate_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblExpirydate_1.setBounds(46, 302, 136, 35);
		add(lblExpirydate_1);
		
		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(192, 314, 107, 19);
		add(tfQuantity);
		
		cbProduct = new JComboBox<String>();
		cbProduct.setSelectedIndex(-1);
		cbProduct.setBounds(160, 121, 166, 21);
		add(cbProduct);
		
		JButton Save = new JButton("Save");
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productList.Save();
			}
		});
		Save.setFont(new Font("Tahoma", Font.PLAIN, 19));
		Save.setBounds(562, 643, 107, 35);
		add(Save);
		populateSupplier();
		
		populateTable();

	}
}
