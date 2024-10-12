package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		int productCount=0;
		
		try {
		/*	
			pst = DBMS.con.prepareStatement("SELECT COUNT(productId) FROM product");
			rst = pst.executeQuery();
			if(rst.next()) {
				productCount = rst.getInt(1);
			}
			product = new String[productCount][5];
			
			//writing productId, productName, available stock value to array
			int i=0;
			pst2 = DBMS.con.prepareStatement("SELECT productId, productName,availableStock FROM product");
			rst2 = pst2.executeQuery();
			while(rst2.next()) {
				product[i][0] = String.valueOf(rst2.getInt(1));
				product[i][1] = rst2.getString(2);
				product[i][4] = String.valueOf(rst2.getInt(3));
				i++;				
 			}
			
			//inboundProduct
			for(int j=0; j<productCount; j++) {
				pst3 = DBMS.con.prepareStatement("SELECT SUM(quantity) FROM inbound WHERE YEAR(date) = ? AND MONTH(date) = ? AND productId=?");
				pst3.setInt(1, year);
				pst3.setInt(2, month+1);
				pst3.setInt(3, Integer.parseInt(product[j][0]));
				rst3 = pst3.executeQuery();
				if(rst3.next()) {
					product[j][2] = String.valueOf(rst3.getInt(1));
				}
			}
			
			//outbound product
			for(int j=0; j<productCount; j++) {
				pst4 = DBMS.con.prepareStatement("SELECT SUM(quantity) FROM outbound WHERE YEAR(dateOut) = ? AND MONTH(dateOut) = ? AND productId=?");
				pst4.setInt(1, year);
				pst4.setInt(2, month+1);
				pst4.setInt(3, Integer.parseInt(product[j][0]));
				rst4 = pst4.executeQuery();
				if(rst4.next()) {
					product[j][3] = String.valueOf(rst4.getInt(1));
				}
			}
				*/
			InventoryReport inventoryReport = new InventoryReport(year, month);
			String cName[] = {"ID", "Name", "In Store", "In Shelf","CurrentStock","Sold", "return"};
			DefaultTableModel model = new DefaultTableModel(inventoryReport.genreatereport(), cName);
			table.setModel(model);
			table.setRowHeight(40);
			table.getColumnModel().getColumn(0).setPreferredWidth(1);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			//DBMS.connectClose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the panel.
	 */
	public InventoryReportUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.YELLOW);
		setLayout(null);
		setBounds(0,0,1200,800);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(4, 190, 775, 500);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.BOLD, 17));
		
		final JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 17));
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.BOLD, 17));
		monthChooser.setBounds(200, 130, 144, 36);
		add(monthChooser);
		
		Date date = new Date();
		int year = 1900+date.getYear();
		
		final JYearChooser yearChooser = new JYearChooser();
		yearChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.setEndYear(year);
		yearChooser.setStartYear(2023);
		yearChooser.setBounds(354, 130, 75, 36);
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
		btnGenerate.setBounds(439, 130, 133, 36);
		add(btnGenerate);
		
		JLabel lblNewLabel = new JLabel("Monthly Inventory Report");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(266, 47, 250, 30);
		add(lblNewLabel);
		yearSelected = yearChooser.getValue();
		monthSelected = monthChooser.getMonth();
		
	}
}
