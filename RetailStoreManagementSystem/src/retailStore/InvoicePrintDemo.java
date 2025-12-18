package retailStore;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.sf.dynamicreports.report.exception.DRException;

public class InvoicePrintDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvoicePrintDemo frame = new InvoicePrintDemo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws DRException 
	 */
	public InvoicePrintDemo() throws DRException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		////////////////Temporary code
		Invoice invoice = new Invoice();
		invoice.setInvoiceNumber(3);
		invoice.readInvoice();
		
		//////
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		JPanel invoicePanel = new JPanel();
		invoicePanel = invoiceGenerator.showInvoice(invoice);
		getContentPane().add(invoicePanel);
		
		
	}

}
