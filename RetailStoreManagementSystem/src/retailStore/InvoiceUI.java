package retailStore;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

public class InvoiceUI extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnAdd;
	private JTable tableShelf;
	private JTextField tfProductId;
	private JTextField tfProductName;
	private JTable tableInvoice;
	private JTextField tfTotalPrice;
	private double total = 0.00;
	private JTextField tfNumberOfProducts;
	Invoice invoice = new Invoice();
	ShelfProductDetail shelfProductDetail;
	private JTextField textFieldSelectedProductName;
	private JTextField textFieldPrice;
	private InvoiceProduct invoiceProduct;
	private JTable tableCustomer;
	private JTextField tfContactNumber;
	private JTextField tfCustomerInfo;
	CustomerList customerList;
	private Customer customer = new Customer().getCustomerDetails(1);
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	private void populateTableShelf(Object[][] obj) {
		String[] columnName = {"InLogNo","BatchNo","Price","Discount%","Q/A"};
		DefaultTableModel model =  new DefaultTableModel(obj, columnName);
		tableShelf.setModel(model);
	}
	
	private void populateInvoice(Object[][] invoiceArray) {
		String[] columnName = {"Id","InLogNo","BatchNo","Price","Discount%","Q/A"};
		DefaultTableModel model =  new DefaultTableModel(invoiceArray, columnName);
		tableInvoice.setModel(model);
	}
	
	private void populateTableCustomer(int key) {
		customerList = new CustomerList();
		if(key == -1) {
			customerList.customerInfo();
		} else {
			customerList.customerInfo(key);
		}
		String[] columName = {"id", "name", "ContactNumber"};
		DefaultTableModel model = new DefaultTableModel(customerList.tableArray(), columName);
		tableCustomer.setModel(model);
	}
	
	private void clearTextFields() {
		tfProductId.setText(null);
		tfProductName.setText(null);
		textFieldSelectedProductName.setText(null);
		textFieldPrice.setText(null);
		tfNumberOfProducts.setText(null);
	}
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public InvoiceUI() {
		setBackground(new Color(135, 206, 235));
		setLayout(null);
		setBounds(0, 0,1197,766);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 156, 590, 169);
		add(scrollPane);
		
		tableShelf = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableShelf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableShelf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int shelfTableRowSelected = tableShelf.getSelectedRow();
				if(shelfTableRowSelected != -1) {
					invoiceProduct = new InvoiceProduct(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getId());
					invoiceProduct.setInboundLogNo(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getInboundLogNo());
					invoiceProduct.setPrice(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getSalePrice());
					invoiceProduct.setBatchNo(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getBatchNo());
					textFieldSelectedProductName.setText(invoiceProduct.getName());
					textFieldPrice.setText(String.valueOf(invoiceProduct.getPrice()));
					invoiceProduct.setLogNo(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getLogNo());
					invoiceProduct.setQuantityInShelf(shelfProductDetail.shelfProductDetailList.get(shelfTableRowSelected).getQuantityInShelf());
					btnAdd.setEnabled(true);
				}
			}
		});
		scrollPane.setViewportView(tableShelf);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(518, 335, 142, 19);
		add(dateChooser);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.RED);
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearing all to starting state
				populateInvoice(null);
				populateTableShelf(null);
				clearTextFields();
				invoice = new Invoice();
				invoiceProduct = null;
				btnAdd.setEnabled(false);
				customer = customer.getCustomerDetails(1);
				tfCustomerInfo.setText(customer.getId() + " "+ customer.getName());
				invoice.setCustomerId(customer.getId());
			}
		});
		btnClear.setBounds(749, 626, 104, 39);
		add(btnClear);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnPrint.setForeground(Color.BLUE);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//saving and printing invoice
				invoice.setDate(dateChooser.getDate());
				invoice.saveInvoice(); //saving invoice
				invoice = new Invoice(); //next invoice created
				btnAdd.setEnabled(false);
				tfTotalPrice.setText(String.valueOf(invoice.getTotal()));
				populateInvoice(null);
				populateTableShelf(null);
				clearTextFields();
				customer = customer.getCustomerDetails(1);
				tfCustomerInfo.setText(customer.getId() + " "+ customer.getName());
				invoice.setCustomerId(customer.getId());
			}
		});
		btnPrint.setBounds(950, 626, 99, 39);
		add(btnPrint);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(72, 419, 590, 246);
		add(scrollPane_1);
		
		tableInvoice = new JTable();
		tableInvoice.setEnabled(false);
		scrollPane_1.setViewportView(tableInvoice);
		
		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(739, 420, 128, 30);
		add(lblNewLabel_1);
		
		tfTotalPrice = new JTextField();
		tfTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfTotalPrice.setBounds(884, 419, 203, 39);
		add(tfTotalPrice);
		tfTotalPrice.setColumns(10);
		
		populateTableShelf(null);
		populateInvoice(null);
		tfTotalPrice.setText(String.valueOf(total));
		
		JLabel lblNewLabel_1_1 = new JLabel("Number of Product");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(72, 379, 172, 30);
		add(lblNewLabel_1_1);
		
		tfNumberOfProducts = new JTextField();
		tfNumberOfProducts.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNumberOfProducts.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfNumberOfProducts.setColumns(10);
		tfNumberOfProducts.setBounds(274, 370, 116, 39);
		add(tfNumberOfProducts);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creating invoice
				try {
					invoiceProduct.setQuantity(Integer.parseInt(tfNumberOfProducts.getText()));
					invoice.add(invoiceProduct);
					tfTotalPrice.setText(String.valueOf(invoice.getTotal()));
					invoiceProduct = null;
				} catch (NumberFormatException | NullPointerException e1) {
					e1.printStackTrace();
				}
				populateInvoice(invoice.tableArray());
				clearTextFields();
			}
		});
		btnAdd.setBounds(425, 370, 99, 39);
		add(btnAdd);
		
		textFieldSelectedProductName = new JTextField();
		textFieldSelectedProductName.setEditable(false);
		textFieldSelectedProductName.setColumns(10);
		textFieldSelectedProductName.setBounds(72, 330, 189, 30);
		add(textFieldSelectedProductName);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(294, 330, 157, 30);
		add(textFieldPrice);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(74, 120, 92, 26);
		add(lblNewLabel);
		
		tfProductId = new JTextField();
		tfProductId.setBounds(179, 123, 97, 23);
		add(tfProductId);
		tfProductId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e){
				//populating product Name
				try {
					if(tfProductId.getText().isEmpty()) {
						tfProductName.setText("");
						populateTableShelf(null);
					} else {
						Product product = new Product().getProductInfo(Integer.parseInt(tfProductId.getText()));
						if(product == null) {
							tfProductName.setText("");
							populateTableShelf(null);
						} else {
							tfProductName.setText(product.getName());
							shelfProductDetail = new ShelfProductDetail();
							populateTableShelf(shelfProductDetail.tableArray(Integer.parseInt(tfProductId.getText())));
						}
					}
				} catch (NumberFormatException | NullPointerException e1) {
					//exception handled
					populateTableShelf(null);
				}
			}
		});
		tfProductId.setColumns(10);
		
		tfProductName = new JTextField();
		tfProductName.setBounds(306, 124, 358, 22);
		add(tfProductName);
		tfProductName.setEditable(false);
		tfProductName.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(729, 156, 358, 169);
		add(scrollPane_2);
		
		tableCustomer = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableCustomer.getSelectedRow();
				Customer customer = customerList.customerDetailList.get(selectedRow);
				tfCustomerInfo.setText(customer.getId() + " " + customer.getName());
				invoice.setCustomerId(customer.getId() );
			}
		});
		scrollPane_2.setViewportView(tableCustomer);
		
		tfContactNumber = new JTextField();
		tfContactNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (tfContactNumber.getText().isEmpty()) {
					populateTableCustomer(-1);
				} else {
					try {
						populateTableCustomer(Integer.parseInt(tfContactNumber.getText()));
					} catch(NumberFormatException ex) {
						
					}
				}
			}
		});
		tfContactNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tfContactNumber.setBounds(950, 119, 137, 31);
		add(tfContactNumber);
		tfContactNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Customer contact Number");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(729, 117, 201, 31);
		add(lblNewLabel_2);
		
		tfCustomerInfo = new JTextField();
		tfCustomerInfo.setEditable(false);
		tfCustomerInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfCustomerInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfCustomerInfo.setColumns(10);
		tfCustomerInfo.setBounds(884, 505, 203, 39);
		add(tfCustomerInfo);
		tfCustomerInfo.setText(customer.getId() + " "+ customer.getName());
		
		lblNewLabel_3 = new JLabel("Customer");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(739, 505, 128, 39);
		add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Invoice");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(576, 20, 142, 59);
		add(lblNewLabel_4);
		btnAdd.setEnabled(false);
		populateTableCustomer(-1);
	}
}
