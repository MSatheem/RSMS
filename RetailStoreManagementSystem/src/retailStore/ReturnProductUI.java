package retailStore;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.border.LineBorder;
import java.awt.Color;

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
	ReturnProductRead returnProductRead;
	private JTextField tfReturnable;
	
	private void populateInvoiceTable(Object[][] obj) {
		String[] columnName = {"ID","Name","inbound","batchNo","PPU","discount","quantity","Price"};
		DefaultTableModel model =  new DefaultTableModel(obj, columnName);
		table.setModel(model);
	}
	
	private void clearFields() {
		populateInvoiceTable(null);
		textFieldCustomerId.setText(null);
		textFieldDate.setText(null);
		textFieldProductID.setText(null);
		tfReturnable.setText(null);
		textFieldQuantity.setText(null);
		invoice = null;
		returnProduct = null;
		returnProductRead = null;
	}

	/**
	 * Create the panel.
	 */
	public ReturnProductUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setBounds(2,2,1197,766);
		
		JLabel lblNewLabel = new JLabel("InvoiceNumber");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(131, 108, 146, 38);
		add(lblNewLabel);
		
		JLabel lblCustomerid = new JLabel("CustomerId");
		lblCustomerid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCustomerid.setBounds(131, 177, 146, 38);
		add(lblCustomerid);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDate_1.setBounds(513, 177, 146, 38);
		add(lblDate_1);
		
		textFieldInvoiceNumber = new JTextField();
		textFieldInvoiceNumber.setBounds(287, 108, 123, 33);
		add(textFieldInvoiceNumber);
		textFieldInvoiceNumber.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 225, 930, 259);
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
					tfReturnable.setText(String.valueOf(returnProductRead.returnableCount(invoice.invoiceProductList.get(tableRowSelected))));
				} catch(NullPointerException | NumberFormatException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
				invoice = null;
				try {
					invoice = new Invoice();
					int searchInvoice = Integer.parseInt(textFieldInvoiceNumber.getText());
					invoice.setInvoiceNumber(searchInvoice);
					textFieldCustomerId.setText(String.valueOf(invoice.getCustomerId()));
					if(invoice.isSaved()) {
						populateInvoiceTable(invoice.tableArrayRead());
						textFieldDate.setText(invoice.getDate().toString());
						returnProductRead = new ReturnProductRead(invoice.getInvoiceNumber());
					} else {
						populateInvoiceTable(null);
					}
				} catch(NumberFormatException | NullPointerException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(491, 108, 110, 34);
		add(btnNewButton);
		
		textFieldCustomerId = new JTextField();
		textFieldCustomerId.setEditable(false);
		textFieldCustomerId.setColumns(10);
		textFieldCustomerId.setBounds(287, 177, 123, 33);
		add(textFieldCustomerId);
		
		textFieldDate = new JTextField();
		textFieldDate.setEditable(false);
		textFieldDate.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(600, 177, 123, 33);
		add(textFieldDate);
		
		JLabel lblProductid = new JLabel("ProductID");
		lblProductid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProductid.setBounds(131, 529, 110, 38);
		add(lblProductid);
		
		textFieldProductID = new JTextField();
		textFieldProductID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldProductID.setEditable(false);
		textFieldProductID.setColumns(10);
		textFieldProductID.setBounds(263, 529, 61, 33);
		add(textFieldProductID);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQuantity.setBounds(513, 529, 110, 38);
		add(lblQuantity);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(662, 532, 61, 33);
		add(textFieldQuantity);

		java.util.Date date = new java.util.Date();
		JDateChooser dateChooser = new JDateChooser(date);
		dateChooser.setBounds(830, 108, 110, 31);
		add(dateChooser);
		
		JButton btnNewButton_1 = new JButton("Return");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//updating database after saving return info to database
				try {
					int qunatityReturning = Integer.parseInt(textFieldQuantity.getText());
					int returnable = Integer.parseInt(tfReturnable.getText());
					if(qunatityReturning <= returnable) {
						returnProduct.setQuantityOfReturn(qunatityReturning);
						returnProduct.setDate(dateChooser.getDate());
						returnProduct.saveReturn();
					} else {
						JOptionPane.showMessageDialog(null, "Check the number of products", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch(NullPointerException | NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Check the number of products", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(757, 529, 85, 34);
		add(btnNewButton_1);
		
		tfReturnable = new JTextField();
		tfReturnable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfReturnable.setEditable(false);
		tfReturnable.setColumns(10);
		tfReturnable.setBounds(366, 529, 61, 33);
		add(tfReturnable);
		
		JLabel lblNewLabel_1 = new JLabel("Product Return");
		lblNewLabel_1.setBounds(513, 36, 110, 51);
		add(lblNewLabel_1);
		
		populateInvoiceTable(null);//empty table
		
	}
}
