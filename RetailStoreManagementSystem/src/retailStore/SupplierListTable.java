package retailStore;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class SupplierListTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldSearch;
	SupplierList supplierList = new SupplierList();
	
	public void populateTable(Object[][] obj) {
		String cName[] = { "ID", "Name",};
		DefaultTableModel model = new DefaultTableModel(obj, cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.setRowHeight(30);
	}
	private void populateTable() {
		String cName[] = { "ID", "Name"};
		supplierList.supplierInfo();
		DefaultTableModel model = new DefaultTableModel(supplierList.tableArray(), cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.setRowHeight(30);
	}
	
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public SupplierListTable() {
		setLayout(null);
		setBounds(0, 0, 398, 454);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 74, 372, 370);
		add(scrollPane);
		
		table = new JTable() {
			public  boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					populateTable();
				} else {
					supplierList.supplierInfo(textFieldSearch.getText());
					populateTable(supplierList.tableArray());
				}
			}
		});
		textFieldSearch.setBounds(184, 15, 198, 30);
		add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search By Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(14, 10, 158, 30);
		add(lblNewLabel);
		populateTable();

	}
}
