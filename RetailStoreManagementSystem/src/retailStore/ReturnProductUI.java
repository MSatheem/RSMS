package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

public class ReturnProductUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldInvoiceNumber;
	private JTable table;
	Invoice invoice;
	private JTextField textFieldCustomerId;
	private JTextField textFieldDate;
	private JTextField textFieldProductID;
	private JTextField textFieldQuantity;
	private int tableRowSelected = -1;
	ReturnProduct returnProduct;
	
	private void populateInvoiceTable(Object[][] obj) {
			String[] columnName = {"ID","Name","inbound","batchNo","PPU","discount","quantity","Price"};
			DefaultTableModel model =  new DefaultTableModel(obj, columnName);
			table.setModel(model);
	}

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public ReturnProductUI() {
		setLayout(null);
		setBounds(1,1,1000,700);
		
		JLabel lblNewLabel = new JLabel("InvoiceNumber");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(37, 45, 146, 38);
		add(lblNewLabel);
		
		JLabel lblCustomerid = new JLabel("CustomerId");
		lblCustomerid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomerid.setBounds(37, 114, 146, 38);
		add(lblCustomerid);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDate_1.setBounds(419, 114, 146, 38);
		add(lblDate_1);
		
		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber.setBounds(193, 45, 123, 33);
		add(textFieldInvoiceNumber);
		textFieldInvoiceNumber.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 164, 930, 259);
		add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//selecting product to return
				try {
					tableRowSelected = table.getSelectedRow();
					textFieldProductID.setText(String.valueOf(invoice.invoiceProductList.get(tableRowSelected).getId()));
					returnProduct = new ReturnProduct(invoice.invoiceProductList.get(tableRowSelected).getId());
					returnProduct.setInvoiceNumber(invoice.getInvoiceNumber());
					returnProduct.setBatchNo(invoice.invoiceProductList.get(tableRowSelected).getBatchNo());
					returnProduct.setInboundLogNo(invoice.invoiceProductList.get(tableRowSelected).getInboundLogNo());
				} catch(NullPointerException | NumberFormatException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invoice = null;
				try {
					invoice = new Invoice();
					int searchInvoice = Integer.parseInt(textFieldInvoiceNumber.getText());
					invoice.setInvoiceNumber(searchInvoice);
					textFieldCustomerId.setText(String.valueOf(invoice.getCustomerId()));
					if(invoice.isSaved()) {
						populateInvoiceTable(invoice.tableArrayRead());
						textFieldDate.setText(invoice.getDate().toString());
					} else {
						populateInvoiceTable(null);
					}
				} catch(NumberFormatException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(378, 58, 85, 21);
		add(btnNewButton);
		
		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setEditable(false);
		textFieldCustomerId.setColumns(10);
		textFieldCustomerId.setBounds(193, 114, 123, 33);
		add(textFieldCustomerId);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(506, 114, 123, 33);
		add(textFieldDate);
		
		JLabel lblProductid = new JLabel("ProductID");
		lblProductid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductid.setBounds(37, 477, 110, 38);
		add(lblProductid);
		
		textFieldProductID = new JTextField();
		textFieldProductID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldProductID.setEditable(false);
		textFieldProductID.setColumns(10);
		textFieldProductID.setBounds(169, 477, 61, 33);
		add(textFieldProductID);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(378, 477, 110, 38);
		add(lblQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(504, 477, 61, 33);
		add(textFieldQuantity);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(832, 59, 73, 19);
		add(dateChooser);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//updating database after saving return info to database
				try {
					int qunatityReturning = Integer.parseInt(textFieldQuantity.getText());
					if(qunatityReturning <= invoice.invoiceProductList.get(tableRowSelected).getQuantity()) {
						returnProduct.setQuantityOfReturn(qunatityReturning);
						returnProduct.setDate(dateChooser.getDate());
						returnProduct.saveReturn();
					
					}
				} catch(NullPointerException | NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(663, 477, 85, 34);
		add(btnNewButton_1);
		
		populateInvoiceTable(null);//empty table
		
	}
}
