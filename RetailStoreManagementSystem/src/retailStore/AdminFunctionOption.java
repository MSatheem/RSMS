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

public class AdminFunctionOption extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel panel;
	//panels
	
	private SupplierUI supplierUI;
	private ProductUI productUI;
	private InboundProductUI inboundProductUI;
	private SupplierUpdateUI supplierUpdateUI;
	private CustomerUI customerUI;
	/**
	 * Create the panel.
	 */
	
	public AdminFunctionOption() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0,0,1199,750);

		panel = new JPanel();
		panel.setBounds(29, 41, 1141, 702);
		add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		supplierUI = new SupplierUI();
		productUI = new ProductUI();
		inboundProductUI = new InboundProductUI();
		supplierUpdateUI = new SupplierUpdateUI();
		customerUI = new CustomerUI();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.setBounds(0, 0, 500, 31);
		add(menuBar);
		
		JMenu StoreMenu = new JMenu("Store");
		StoreMenu.setHorizontalAlignment(SwingConstants.CENTER);
		StoreMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(StoreMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sales");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		StoreMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Return");
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		StoreMenu.add(mntmNewMenuItem_1);
		
		JMenu CustomerMenu = new JMenu("Customer");
		CustomerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		CustomerMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(CustomerMenu);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(customerUI);
				customerUI.setVisible(true);
				panel.setVisible(true);
			}
		});
		mntmAdd.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAdd.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CustomerMenu.add(mntmAdd);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Edit");
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CustomerMenu.add(mntmNewMenuItem_1_1);
		
		JMenu Inventory = new JMenu("Inventory");
		Inventory.setHorizontalAlignment(SwingConstants.CENTER);
		Inventory.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(Inventory);
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Inbound");
		mntmNewMenuItem_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(inboundProductUI);
				inboundProductUI.setVisible(true);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		Inventory.add(mntmNewMenuItem_1_2);
		
		JMenuItem mntmCheck = new JMenuItem("Check");
		mntmCheck.setHorizontalAlignment(SwingConstants.LEFT);
		mntmCheck.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		Inventory.add(mntmCheck);
		
		JMenu mnProduct = new JMenu("Product");
		mnProduct.setHorizontalAlignment(SwingConstants.CENTER);
		mnProduct.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnProduct);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(productUI);
				productUI.setVisible(true);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProduct.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Edit");
		mntmNewMenuItem_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProduct.add(mntmNewMenuItem_1_3);
		
		JMenu mnSupplier = new JMenu("Supplier");
		mnSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		mnSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnSupplier);
		
		JMenuItem mntmAddSupplier = new JMenuItem("Add");
		mntmAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(supplierUI);
				supplierUI.setVisible(true);
				panel.setVisible(true);
			}
		});
		mntmAddSupplier.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAddSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSupplier.add(mntmAddSupplier);
		
		JMenuItem mntmNewMenuItem_1_4 = new JMenuItem("Edit");
		mntmNewMenuItem_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.add(supplierUpdateUI);
				supplierUpdateUI.setVisible(true);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSupplier.add(mntmNewMenuItem_1_4);
		
	}
}
