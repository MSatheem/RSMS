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
public class CustomerListTable extends JPanel {
	private static JTable table;
	static String product[][];
	private JTextField textFieldSearch;
	CustomerList customerList = new CustomerList();
	
	public void populateTable(Object[][] obj) {
		String cName[] = { "ID", "Name", "ContactNo" };
		DefaultTableModel model = new DefaultTableModel(obj, cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowHeight(30);
	}
	public void populateTable() {
		customerList.customerInfo();
		String cName[] = { "ID", "Name", "ContactNo" };
		DefaultTableModel model = new DefaultTableModel(customerList.tableArray(), cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.setRowHeight(30);
	}
	

	/**
	 * Create the panel.
	 */
	public CustomerListTable() {
		setLayout(null);
		setBounds(0, 0, 319, 325);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 54, 299, 261);
		add(scrollPane);

		table = new JTable();
		table.setEnabled(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("Search Phone Number");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 15, 165, 24);
		add(lblNewLabel);

		textFieldSearch =  new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					populateTable();
				} else {
					customerList.customerInfo(Integer.parseInt(textFieldSearch.getText()));
					populateTable(customerList.tableArray());
				}
			}
		});
		textFieldSearch.setBounds(185, 15, 124, 30);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		populateTable();
	}
}
