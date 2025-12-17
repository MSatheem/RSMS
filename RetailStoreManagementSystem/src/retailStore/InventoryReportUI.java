package retailStore;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import net.sf.dynamicreports.report.exception.DRException;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class InventoryReportUI extends JPanel {
	static String product[][];
	int yearSelected, monthSelected;
	InventoryReportGenerator inventoryReportGenerator = new InventoryReportGenerator();

	public void populateTable(JPanel panel) {
		try {
			panel.removeAll();
			JPanel reportPanel = inventoryReportGenerator.showReport();
			panel.add(reportPanel, BorderLayout.CENTER); 
			panel.revalidate();
			panel.repaint();
		} catch (DRException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Create the panel.
	 */
	public InventoryReportUI() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(175, 238, 238));
		setBounds(0, 0, 1200, 800);
		setLayout(new BorderLayout(0, 0));

		JPanel reportViewPanel = new JPanel(new BorderLayout());

		add(reportViewPanel, BorderLayout.CENTER);

		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setBackground(new Color(175, 238, 238));

		JLabel lblTitle = new JLabel("Inventory Report", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		topPanel.add(lblTitle);
		populateTable(reportViewPanel);
		
	}
}
