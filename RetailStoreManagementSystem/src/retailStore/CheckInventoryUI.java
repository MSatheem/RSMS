package retailStore;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CheckInventoryUI extends JPanel {
	private static JTable table;
	static String product[][];
	int yearSelected,monthSelected;
	
	static void populateTable() {
		try {
			InventoryReport inventoryReport = new InventoryReport();
			String cName[] = {"ID", "Name", "In Store", "In Shelf","CurrentStock"};
			DefaultTableModel model = new DefaultTableModel(inventoryReport.currentInventory(), cName);
			table.setModel(model);
			table.setRowHeight(40);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the panel.
	 */
	public CheckInventoryUI() {
		setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setBackground(new Color(175, 238, 238));
		setLayout(null);
		setBounds(0,0,1200,800);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 190, 775, 500);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.BOLD, 17));
		
		JLabel lblNewLabel = new JLabel("Check Inventory");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(475, 47, 250, 30);
		add(lblNewLabel);
		
		populateTable();
	}
}
