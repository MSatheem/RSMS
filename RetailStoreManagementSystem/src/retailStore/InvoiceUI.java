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

public class InvoiceUI extends JPanel {

	private static final long serialVersionUID = 1L;
	CustomerListTable customerListTable;
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
	
	private void clearTextFields() {
		tfProductId.setText("");
		tfProductName.setText("");
		textFieldSelectedProductName.setText("");
		textFieldPrice.setText("");
	}
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public InvoiceUI() {
		setLayout(null);
		setBounds(1,1,1000,700);
		
		JPanel panelCustomer = new JPanel();
		panelCustomer.setBounds(668, 10, 322, 324);
		add(panelCustomer);
		panelCustomer.setLayout(null);
		
		customerListTable =  new CustomerListTable();
		customerListTable.setBounds(0, 0, 319, 337);
		panelCustomer.add(customerListTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 82, 590, 169);
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
				}
			}
		});
		scrollPane.setViewportView(tableShelf);
		
		JPanel panel = new JPanel();
		panel.setBounds(45, 10, 588, 62);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setBounds(10, 20, 66, 26);
		panel.add(lblNewLabel);
		
		tfProductId = new JTextField();
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
				}
			}
		});
		tfProductId.setBounds(86, 24, 84, 19);
		panel.add(tfProductId);
		tfProductId.setColumns(10);
		
		tfProductName = new JTextField();
		tfProductName.setEditable(false);
		tfProductName.setBounds(203, 24, 132, 19);
		panel.add(tfProductName);
		tfProductName.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(668, 362, 142, 19);
		add(dateChooser);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clearing all to starting state
				populateInvoice(null);
				populateTableShelf(null);
			}
		});
		btnClear.setBounds(668, 640, 85, 21);
		add(btnClear);
		
		JButton btnPrint = new JButton("print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//saving and printing invoice
				invoice.setDate(dateChooser.getDate());
				invoice.saveInvoice();
			}
		});
		btnPrint.setBounds(905, 640, 85, 21);
		add(btnPrint);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(43, 362, 590, 246);
		add(scrollPane_1);
		
		tableInvoice = new JTable();
		tableInvoice.setEnabled(false);
		scrollPane_1.setViewportView(tableInvoice);
		
		JLabel lblNewLabel_1 = new JLabel("Total");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(39, 627, 128, 30);
		add(lblNewLabel_1);
		
		tfTotalPrice = new JTextField();
		tfTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalPrice.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfTotalPrice.setBounds(177, 622, 116, 39);
		add(tfTotalPrice);
		tfTotalPrice.setColumns(10);
		
		populateTableShelf(null);
		populateInvoice(null);
		tfTotalPrice.setText(String.valueOf(total));
		
		JLabel lblNewLabel_1_1 = new JLabel("Number of Product");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(43, 322, 172, 30);
		add(lblNewLabel_1_1);
		
		tfNumberOfProducts = new JTextField();
		tfNumberOfProducts.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNumberOfProducts.setFont(new Font("Tahoma", Font.BOLD, 20));
		tfNumberOfProducts.setColumns(10);
		tfNumberOfProducts.setBounds(245, 313, 116, 39);
		add(tfNumberOfProducts);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creating invoice
				try {
					invoiceProduct.setQuantity(Integer.parseInt(tfNumberOfProducts.getText()));
					invoice.add(invoiceProduct);
					tfTotalPrice.setText(String.valueOf(invoice.getTotal()));
				} catch (NumberFormatException | NullPointerException e1) {
					e1.printStackTrace();
				}
				
				populateInvoice(invoice.tableArray());
			}
		});
		btnAdd.setBounds(396, 318, 99, 39);
		add(btnAdd);
		
		textFieldSelectedProductName = new JTextField();
		textFieldSelectedProductName.setEditable(false);
		textFieldSelectedProductName.setColumns(10);
		textFieldSelectedProductName.setBounds(43, 273, 136, 19);
		add(textFieldSelectedProductName);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setEditable(false);
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(225, 273, 136, 19);
		add(textFieldPrice);
		
		
	}
}
