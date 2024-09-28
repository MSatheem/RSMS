package retailStore;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class SalesReportUI extends JPanel {
	private DecimalFormat df = new DecimalFormat("#.00");
	
	private static final long serialVersionUID = 1L;
	JLabel lblTotalSales;
	/**
	 * Create the panel.
	 */
	public SalesReportUI() {
		setLayout(null);
		setBounds(49,49,1000,700);
		
		JLabel lblNewLabel = new JLabel("SALES REPORT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(361, 46, 278, 57);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 214, 980, 476);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(35, 34, 200, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total Sales");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1.setBounds(10, 10, 168, 52);
		panel_1.add(lblNewLabel_1);
		
		lblTotalSales = new JLabel("");
		lblTotalSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalSales.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotalSales.setBounds(15, 72, 158, 61);
		panel_1.add(lblTotalSales);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_1_1.setBackground(Color.GREEN);
		panel_1_1.setBounds(271, 34, 200, 143);
		panel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Profit");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_1_1.setBounds(10, 10, 168, 52);
		panel_1_1.add(lblNewLabel_1_1);
		
		JLabel lblProfit = new JLabel("");
		lblProfit.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfit.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblProfit.setBounds(15, 72, 158, 61);
		panel_1_1.add(lblProfit);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_1_1_1.setBackground(Color.GREEN);
		panel_1_1_1.setBounds(507, 34, 200, 143);
		panel.add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Products Sold");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(10, 10, 168, 52);
		panel_1_1_1.add(lblNewLabel_1_1_1);
		
		JLabel lblProductsSold = new JLabel("");
		lblProductsSold.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductsSold.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblProductsSold.setBounds(15, 72, 158, 61);
		panel_1_1_1.add(lblProductsSold);
		
		JPanel panel_1_1_1_1 = new JPanel();
		panel_1_1_1_1.setLayout(null);
		panel_1_1_1_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		panel_1_1_1_1.setBackground(new Color(204, 0, 153));
		panel_1_1_1_1.setBounds(743, 34, 200, 143);
		panel.add(panel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Product Return");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(10, 10, 168, 52);
		panel_1_1_1_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblProductsReturn = new JLabel("");
		lblProductsReturn.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductsReturn.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblProductsReturn.setBounds(15, 72, 158, 61);
		panel_1_1_1_1.add(lblProductsReturn);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesReport report = new SalesReport();
				lblTotalSales.setText(String.valueOf(df.format(report.getTotalSales())));
				lblProductsSold.setText(String.valueOf(report.getProductSold()));
				lblProductsReturn.setText(String.valueOf(report.getProductsReturned()));
				lblProfit.setText(String.valueOf(df.format(report.getProfit())));
			}
		});
		btnNewButton.setBounds(766, 148, 85, 21);
		add(btnNewButton);
		
	}
}
