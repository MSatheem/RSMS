package retailStore;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Insets;

public class SalesPersonFunctionOption extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel panel;
	//panels
	
	private CustomerUI customerUI;
	private InvoiceUI invoiceUI;
	private ReturnProductUI returnProductUI;
	private CustomerUpdateUI customerUpdateUI;
	private CheckInventoryUI checkInventoryUI;
	/**
	 * Create the panel.
	 */
	private void menuSwitch() {
		customerUI = null;
		invoiceUI = null;
		returnProductUI = null;
		customerUpdateUI = null;
		checkInventoryUI = null;
		panel.removeAll();
		panel.setVisible(false);
	}
	
	public SalesPersonFunctionOption() {
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0,0,1200, 800);

		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBounds(1, 34, 1197, 766);
		add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBackground(Color.BLACK);
		menuBar.setMargin(new Insets(1, 1, 1, 1));
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.setBounds(3, 3, 603, 31);
		add(menuBar);
		
		JMenu StoreMenu = new JMenu("Store");
		StoreMenu.setForeground(Color.WHITE);
		StoreMenu.setHorizontalAlignment(SwingConstants.CENTER);
		StoreMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(StoreMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sales");
		mntmNewMenuItem.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				invoiceUI =  new InvoiceUI();
				panel.add(invoiceUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		StoreMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Return");
		mntmNewMenuItem_1.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				returnProductUI = new ReturnProductUI();
				panel.add(returnProductUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		StoreMenu.add(mntmNewMenuItem_1);
		
		JMenu CustomerMenu = new JMenu("Customer");
		CustomerMenu.setForeground(Color.WHITE);
		CustomerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CustomerMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(CustomerMenu);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.setBackground(new Color(152, 251, 152));
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				customerUI = new CustomerUI();
				panel.add(customerUI);
				panel.setVisible(true);
			}
		});
		mntmAdd.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAdd.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CustomerMenu.add(mntmAdd);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Edit");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				customerUpdateUI = new CustomerUpdateUI();
				panel.add(customerUpdateUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_1.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CustomerMenu.add(mntmNewMenuItem_1_1);
		
		JMenu Inventory = new JMenu("Inventory");
		Inventory.setForeground(Color.WHITE);
		Inventory.setHorizontalAlignment(SwingConstants.CENTER);
		Inventory.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(Inventory);
		
		JMenuItem mntmCheck = new JMenuItem("Check");
		mntmCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				checkInventoryUI = new CheckInventoryUI();
				panel.add(checkInventoryUI);
				panel.setVisible(true);
			}
		});
		mntmCheck.setBackground(new Color(152, 251, 152));
		mntmCheck.setHorizontalAlignment(SwingConstants.LEFT);
		mntmCheck.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		Inventory.add(mntmCheck);
		panel.setVisible(true);
	}
}
