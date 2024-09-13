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
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private JTextField textField;
	private JTable tableShelf;
	private JTextField tfPrice;
	private JLabel lblNewLabel_3;
	private JTextField tfQuantity;
	private JTextField textFieldId;
	private JTextField textFieldPurchasePrice;
	Object[][] storage;
	private ShelfProductList shelfProductList =  new ShelfProductList();
	
	//populating table
	private void populateTable() {
		InboundProductListDetails inboundProductListDetails = new InboundProductListDetails();
		storage = inboundProductListDetails.tableArray();
		String productStored[] = {"Date","LogNo","Id","Name","Batch","Mfg Date","Exp Date","Quantity","Price"};
		String productToShelf[] = {"Id","Name","Purchase","Batch","Mfg Date","Exp Date", "quantity","sellingPrice"};
		modelStore = new DefaultTableModel(storage,productStored);
		modelShelf =  new DefaultTableModel(productList.listToArray(), productToShelf);
		tableStorage.setModel(modelStore);
		tableShelf.setModel(modelShelf);
	}
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StoreToShelfUI() {
		setLayout(null);
		setBounds(1, 1, 1000, 700);
		
		productList = new InboundProductList();
		shelfProductList = new ShelfProductList();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 112, 618, 216);
		add(scrollPane);
		
		tableStorage = new JTable();
		scrollPane.setViewportView(tableStorage);
		
		lblNewLabel_1 = new JLabel("Store to Shelf");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(402, 20, 196, 35);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Search by");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(208, 56, 92, 35);
		add(lblNewLabel);
		
		JComboBox comboBoxSearchType = new JComboBox();
		comboBoxSearchType.setModel(new DefaultComboBoxModel(new String[] {"Product Id", "Product Name"}));
		comboBoxSearchType.setBounds(310, 65, 92, 21);
		add(comboBoxSearchType);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(comboBoxSearchType.getSelectedIndex());
				//populating table according to user input
				
			}
		});
		textField.setBounds(429, 65, 128, 19);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(208, 464, 616, 165);
		add(scrollPane_1);
		
		tableShelf = new JTable();
		scrollPane_1.setViewportView(tableShelf);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(280, 418, 96, 20);
		add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(208, 418, 50, 20);
		add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(457, 418, 100, 22);
		add(lblNewLabel_3);
		
		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		tfQuantity.setBounds(567, 418, 96, 20);
		add(tfQuantity);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add product to list and showing in the table
				
			}
		});
		btnAdd.setBounds(741, 417, 85, 21);
		add(btnAdd);
		
		JButton btnAdd_1 = new JButton("Save");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reduce the amount in in bound product
				
				//save to the database
				
			}
		});
		btnAdd_1.setBounds(741, 653, 85, 21);
		add(btnAdd_1);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//obtain the product selected
				textFieldId.setText(String.valueOf(storage[tableStorage.getSelectedRow()][2]));
				textFieldPurchasePrice.setText(String.valueOf(storage[tableStorage.getSelectedRow()][8]));
			}
		});
		btnSelect.setBounds(741, 340, 85, 21);
		add(btnSelect);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(208, 364, 50, 20);
		add(lblNewLabel_2_1);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(280, 364, 96, 20);
		add(textFieldId);
		
		JLabel lblNewLabel_3_1 = new JLabel("Purchase Price");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3_1.setBounds(402, 364, 155, 22);
		add(lblNewLabel_3_1);
		
		textFieldPurchasePrice = new JTextField();
		textFieldPurchasePrice.setEditable(false);
		textFieldPurchasePrice.setColumns(10);
		textFieldPurchasePrice.setBounds(567, 369, 96, 20);
		add(textFieldPurchasePrice);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(603, 653, 85, 21);
		add(btnClear);
		
		populateTable();
		
	}
}
