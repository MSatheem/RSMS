package retailStore;

import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class InventoryReportUI extends JPanel {
	private static JTable table;
	static String product[][];
	int yearSelected,monthSelected;
	
	static void populateTable(int year, int month) {
		try {
			InventoryReport inventoryReport = new InventoryReport(year, month);
			String cName[] = {"ID", "Name", "In Store", "In Shelf","CurrentStock","Sold", "return", "Inbound"};
			DefaultTableModel model = new DefaultTableModel(inventoryReport.genreatereport(), cName);
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
	public InventoryReportUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(175, 238, 238));
		setLayout(null);
		setBounds(0,0,1200,800);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 190, 775, 500);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.BOLD, 17));
		
		final JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 17));
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.BOLD, 17));
		monthChooser.setBounds(372, 144, 144, 36);
		add(monthChooser);
		
		Date date = new Date();
		@SuppressWarnings("deprecation")
		int year = 1900+date.getYear();
		
		final JYearChooser yearChooser = new JYearChooser();
		yearChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.setEndYear(year);
		yearChooser.setStartYear(2023);
		yearChooser.setBounds(611, 144, 75, 36);
		add(yearChooser);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 17));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearSelected = yearChooser.getValue();
				monthSelected = monthChooser.getMonth();		
				populateTable(yearSelected,monthSelected+1); //combo box zero indexed 
			}
		});
		btnGenerate.setBounds(749, 144, 133, 36);
		add(btnGenerate);
		
		JLabel lblNewLabel = new JLabel("Monthly Inventory Report");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(475, 47, 250, 30);
		add(lblNewLabel);
		yearSelected = yearChooser.getValue();
		monthSelected = monthChooser.getMonth();
		
	}
}
