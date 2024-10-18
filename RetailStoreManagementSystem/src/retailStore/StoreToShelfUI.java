package retailStore;

import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;

public class StoreToShelfUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableStorage;
	DefaultTableModel modelStore, modelShelf;
	private JScrollPane scrollPane;
	DefaultComboBoxModel<String> modelComboBox;
	DefaultComboBoxModel<String> modelComboBox1;
	private JLabel lblNewLabel_1;
	InboundProductList productList;
	Supplier[] supplier;
	Product[] product;
	private JTextField tfProductId;
	private JTable tableShelf;
	private JTextField tfSellingPrice;
	private JLabel lblNewLabel_3;
	private JTextField tfQuantity;
	private JTextField tFId;
	private JTextField tFPurchasePrice;
	Object[][] storage;
	private ShelfProductList shelfProductList =  new ShelfProductList();
	ShelfProduct shelfProduct =  new ShelfProduct();
	private JLabel lblNewLabel_2_2;
	private JTextField tFLogNo;
	private JLabel lblNewLabel_3_2;
	private JTextField tFBatchNo;
	JDateChooser dateChooser;
	private JPanel panel;
	private ShelfListTable shelfListTable;
	
	private void populateTable(int productId) {
		InboundProductListDetails inboundProductListDetails = new InboundProductListDetails();
		storage = inboundProductListDetails.tableArrayAll(productId);
		String productStored[] = {"Date","LogNo","Id","Name","Batch","Mfg Date","Exp Date","Quantity","Price"};
		modelStore = new DefaultTableModel(inboundProductListDetails.tableArrayAll(productId),productStored);
		tableStorage.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableStorage.setModel(modelStore);
	}
	
	//populating table with product id or name value
	
	//
	private void populateShelfTable() {
		String productToShelf[] = {"Id","Name","BatchNo", "Purchase", "quantity","sellingPrice"};
		modelShelf =  new DefaultTableModel(shelfProductList.tableArray(), productToShelf);
		tableShelf.setModel(modelShelf);
	}
	
	private void shelfListTable() {
		shelfListTable = new ShelfListTable();
		shelfListTable.setBounds(0, 0, 401, 511);
		panel.add(shelfListTable);
	}
	/**
	 * Create the panel.
	 */
	public StoreToShelfUI() {
		setLayout(null);
		setBounds(2, 2, 1197, 766);
		
		productList = new InboundProductList();
		shelfProductList = new ShelfProductList();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 111, 618, 216);
		add(scrollPane);
		
		tableStorage = new JTable();
		scrollPane.setViewportView(tableStorage);
		
		lblNewLabel_1 = new JLabel("Store to Shelf");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(402, 20, 196, 35);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Search by product id");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(39, 55, 184, 35);
		add(lblNewLabel);
		
		tfProductId = new JTextField();
		tfProductId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//populating table according to product id inserted
				try {
					if(!tfProductId.getText().isBlank()) {
						int productIdRead = Integer.parseInt(tfProductId.getText());
						if(productIdRead>0) {
							populateTable(productIdRead);
						}
					} else {
						populateTable(-1);
					}
				} catch(NumberFormatException ex) {
					ex.printStackTrace();
				}
			}
		});
		tfProductId.setBounds(260, 64, 128, 19);
		add(tfProductId);
		tfProductId.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(39, 479, 616, 165);
		add(scrollPane_1);
		
		tableShelf = new JTable();
		scrollPane_1.setViewportView(tableShelf);
		
		tfSellingPrice = new JTextField();
		tfSellingPrice.setBounds(398, 417, 96, 20);
		add(tfSellingPrice);
		tfSellingPrice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Selling Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(233, 417, 155, 20);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(39, 411, 100, 22);
		add(lblNewLabel_3);
		
		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(127, 417, 96, 20);
		add(tfQuantity);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add product to list and showing in the table
				ShelfProduct shelfProduct = new ShelfProduct();
				shelfProduct.setId(Integer.parseInt(tFId.getText()));
				shelfProduct.setBatchNo(Integer.parseInt(tFBatchNo.getText()));
				shelfProduct.setInboundLogNo(Integer.parseInt(tFLogNo.getText()));
				shelfProduct.setSalePrice(Integer.parseInt(tfSellingPrice.getText()));
				shelfProduct.setQuantityMovedToShelf(Integer.valueOf(tfQuantity.getText()));
				shelfProductList.add(shelfProduct);
				populateShelfTable();
			}
		});
		btnAdd.setBounds(572, 416, 85, 21);
		add(btnAdd);
		
		JButton btnAdd_1 = new JButton("Save");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.sql.Date date =  new java.sql.Date(dateChooser.getDate().getTime());
				shelfProductList.setDate(date);
				//save to the database
				shelfProductList.saveList();
				populateTable(-1);
				//load updated shelf list
				shelfListTable();
			}
		});
		btnAdd_1.setBounds(572, 652, 85, 21);
		add(btnAdd_1);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtain the product selected
				int selectedRow = tableStorage.getSelectedRow();
				tFId.setText(String.valueOf(storage[selectedRow][2]));
				tFPurchasePrice.setText(String.valueOf(storage[selectedRow][8]));
				tFBatchNo.setText(String.valueOf(storage[selectedRow][4]));
				tFLogNo.setText(String.valueOf(storage[selectedRow][1]));
			}
		});
		btnSelect.setBounds(572, 339, 85, 21);
		add(btnSelect);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(39, 378, 50, 20);
		add(lblNewLabel_2_1);
		
		tFId = new JTextField();
		tFId.setEditable(false);
		tFId.setColumns(10);
		tFId.setBounds(127, 383, 96, 20);
		add(tFId);
		
		JLabel lblNewLabel_3_1 = new JLabel("Purchase Price");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(233, 385, 155, 22);
		add(lblNewLabel_3_1);
		
		tFPurchasePrice = new JTextField();
		tFPurchasePrice.setEditable(false);
		tFPurchasePrice.setColumns(10);
		tFPurchasePrice.setBounds(398, 387, 96, 20);
		add(tFPurchasePrice);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(434, 652, 85, 21);
		add(btnClear);
		
		lblNewLabel_2_2 = new JLabel("LogNo");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(40, 340, 70, 20);
		add(lblNewLabel_2_2);
		
		tFLogNo = new JTextField();
		tFLogNo.setEditable(false);
		tFLogNo.setColumns(10);
		tFLogNo.setBounds(127, 340, 96, 20);
		add(tFLogNo);
		
		lblNewLabel_3_2 = new JLabel("Batch No");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_2.setBounds(233, 337, 155, 22);
		add(lblNewLabel_3_2);
		
		tFBatchNo = new JTextField();
		tFBatchNo.setEditable(false);
		tFBatchNo.setColumns(10);
		tFBatchNo.setBounds(398, 340, 96, 20);
		add(tFBatchNo);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/MM/dd");
		dateChooser.setBounds(515, 64, 142, 19);
		add(dateChooser);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(250, 250, 210));
		panel.setBounds(736, 55, 401, 511);
		add(panel);
		
		shelfListTable();
		populateTable(-1);
		populateShelfTable();
		
	}
}
