package retailStore;

import javax.swing.JPanel;
import java.awt.SystemColor;

public class InvoiceUI extends JPanel {

	private static final long serialVersionUID = 1L;
	CustomerListTable customerListTable;
	/**
	 * Create the panel.
	 */
	public InvoiceUI() {
		setBackground(SystemColor.window);
		setLayout(null);
		setBounds(1,1,1000,700);
		
		JPanel panelCustomer = new JPanel();
		panelCustomer.setBounds(668, 10, 322, 324);
		add(panelCustomer);
		panelCustomer.setLayout(null);
		
		customerListTable =  new CustomerListTable();
		customerListTable.setBounds(0, 0, 319, 337);
		panelCustomer.add(customerListTable);
		
	}
}
