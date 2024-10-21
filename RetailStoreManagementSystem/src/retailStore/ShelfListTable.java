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

public class ShelfListTable extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textFieldSearch;
	ShelfList shelfList = new ShelfList();
	
	public void populateTable(String searchkey) {
		String cName[] = { "ID", "Name","Quantity"};
		DefaultTableModel model = new DefaultTableModel(shelfList.tableArray(searchkey), cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.setRowHeight(30);
	}
	private void populateTable() {
		String cName[] = { "ID", "Name","Quantity"};
		DefaultTableModel model = new DefaultTableModel(shelfList.tableArray(null), cName);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.setRowHeight(30);
	}
	
	
	/**
	 * Create the panel.
	 */
	@SuppressWarnings("serial")
	public ShelfListTable() {
		setLayout(null);
		setBounds(0, 0, 401, 511);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 74, 372, 370);
		add(scrollPane);
		
		table = new JTable() {
			public  boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (textFieldSearch.getText().isEmpty()) {
					populateTable();
				} else {
					populateTable(textFieldSearch.getText());
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
