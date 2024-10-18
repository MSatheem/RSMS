package retailStore;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ProductListTable extends JPanel {
	private static JTable table;
	static String product[][];
	private JTextField textFieldSearch;
	ProductList productList = new ProductList();
	
	public void populateTable(Object[][] obj) {
		String cName[] = { "ID", "Name", "SupplierID" };
		DefaultTableModel model = new DefaultTableModel(obj, cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(8);
		table.setRowHeight(30);
	}
	public void populateTable() {
		productList.productInfo();
		String cName[] = { "ID", "Name", "SupplierId" };
		DefaultTableModel model = new DefaultTableModel(productList.tableArray(), cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(8);
		table.setRowHeight(30);
	}
	

	/**
	 * Create the panel.
	 */
	public ProductListTable() {
		setLayout(null);
		setBounds(0, 0, 481, 405);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 54, 461, 341);
		add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Search by name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(64, 15, 165, 30);
		add(lblNewLabel);

		textFieldSearch =  new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					populateTable();
				} else {
					productList.productInfo(textFieldSearch.getText());
					populateTable(productList.tableArray());
				}
			}
		});
		textFieldSearch.setBounds(293, 15, 124, 30);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		populateTable();
	}
}
