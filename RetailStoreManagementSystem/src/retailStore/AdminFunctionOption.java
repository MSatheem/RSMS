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

public class AdminFunctionOption extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel panel;
	//panels
	
	private SupplierUI supplierUI;
	private ProductUI productUI;
	private InboundProductUI inboundProductUI;
	private SupplierUpdateUI supplierUpdateUI;
	private CustomerUI customerUI;
	private StoreToShelfUI shelfUI;
	private InvoiceUI invoiceUI;
	private ReturnProductUI returnProductUI;
	private SalesReportUI salesReportUI;
	private ProductUpdateUI productUpdateUI;
	private EmployeeUI employeeUI;
	private EmployeeUpdateUI employeeUpdateUI;
	private ManageUserAccountsUI manageUserAccountsUI;
	private InventoryReportUI inventoryReportUI;
	private CustomerUpdateUI customerUpdateUI;
	private CheckInventoryUI checkInventoryUI;
	/**
	 * Create the panel.
	 */
	private void menuSwitch() {
		supplierUI = null;
		productUI = null;
		inboundProductUI = null;
		supplierUpdateUI = null;
		customerUI = null;
		shelfUI = null;
		invoiceUI = null;
		returnProductUI = null;
		salesReportUI = null;
		productUpdateUI = null;
		employeeUI = null;
		employeeUpdateUI = null;
		manageUserAccountsUI = null;
		inventoryReportUI = null;
		customerUpdateUI = null;
		checkInventoryUI = null;
		panel.removeAll();
		panel.setVisible(false);
	}
	
	public AdminFunctionOption() {
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
		
		JMenuItem mntmNewMenuItem_1_2 = new JMenuItem("Inbound");
		mntmNewMenuItem_1_2.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				inboundProductUI = new InboundProductUI();
				panel.add(inboundProductUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		Inventory.add(mntmNewMenuItem_1_2);
		
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
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Stock Shelf");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				shelfUI = new StoreToShelfUI();
				panel.add(shelfUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem_3.setBackground(new Color(152, 251, 152));
		Inventory.add(mntmNewMenuItem_3);
		
		JMenu mnProduct = new JMenu("Product");
		mnProduct.setForeground(Color.WHITE);
		mnProduct.setHorizontalAlignment(SwingConstants.CENTER);
		mnProduct.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnProduct);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add");
		mntmNewMenuItem_2.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				productUI = new ProductUI();
				panel.add(productUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProduct.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1_3 = new JMenuItem("Edit");
		mntmNewMenuItem_1_3.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				productUpdateUI = new ProductUpdateUI();
				panel.add(productUpdateUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProduct.add(mntmNewMenuItem_1_3);
		
		JMenu mnSupplier = new JMenu("Supplier");
		mnSupplier.setForeground(Color.WHITE);
		mnSupplier.setHorizontalAlignment(SwingConstants.CENTER);
		mnSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnSupplier);
		
		JMenuItem mntmAddSupplier = new JMenuItem("Add");
		mntmAddSupplier.setBackground(new Color(152, 251, 152));
		mntmAddSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				supplierUI = new SupplierUI();
				panel.add(supplierUI);
				panel.setVisible(true);
			}
		});
		mntmAddSupplier.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAddSupplier.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSupplier.add(mntmAddSupplier);
		
		JMenuItem mntmNewMenuItem_1_4 = new JMenuItem("Edit");
		mntmNewMenuItem_1_4.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				supplierUpdateUI = new SupplierUpdateUI();
				panel.add(supplierUpdateUI);
				panel.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSupplier.add(mntmNewMenuItem_1_4);
		
		JMenu mnReport = new JMenu("Report");
		mnReport.setForeground(Color.WHITE);
		mnReport.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnReport);
		
		JMenuItem mntmSalesreport = new JMenuItem("SalesReport");
		mntmSalesreport.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmSalesreport.setBackground(new Color(152, 251, 152));
		mntmSalesreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				salesReportUI = new SalesReportUI();
				panel.add(salesReportUI);
				panel.setVisible(true);
			}
		});
		mnReport.add(mntmSalesreport);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Inventory Report");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem_6.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				inventoryReportUI = new InventoryReportUI();
				panel.add(inventoryReportUI);
				panel.setVisible(true);
			}
		});
		mnReport.add(mntmNewMenuItem_6);
		
		JMenu mnEmployee = new JMenu("Employee");
		mnEmployee.setForeground(Color.WHITE);
		mnEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnEmployee);
		
		JMenuItem mntmAddEmployee = new JMenuItem("Add");
		mntmAddEmployee.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmAddEmployee.setBackground(new Color(152, 251, 152));
		mntmAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				employeeUI = new EmployeeUI();
				panel.add(employeeUI);
				panel.setVisible(true);
			}
		});
		mnEmployee.add(mntmAddEmployee);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Update");
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem_4.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				employeeUpdateUI = new EmployeeUpdateUI();
				panel.add(employeeUpdateUI);
				panel.setVisible(true);
			}
		});
		mnEmployee.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu = new JMenu("User");
		mnNewMenu.setForeground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Add");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNewMenuItem_5.setBackground(new Color(152, 251, 152));
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSwitch();
				manageUserAccountsUI = new ManageUserAccountsUI();
				panel.add(manageUserAccountsUI);
				panel.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		panel.setVisible(true);
	}
}
