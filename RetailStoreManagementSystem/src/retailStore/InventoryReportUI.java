package retailStore;

import java.util.Date;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import net.sf.dynamicreports.report.exception.DRException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class InventoryReportUI extends JPanel {
	static String product[][];
	int yearSelected,monthSelected;
	InventoryReportGenerator inventoryReportGenerator = new InventoryReportGenerator();
	
	public void populateTable(int year, int month, JPanel panel) {
		try {
			panel.removeAll();
			JPanel reportPanel = inventoryReportGenerator.showReport(); // returns panel with JRViewer 
			panel.add(reportPanel, BorderLayout.CENTER); // add to CENTER so it fills available area panel.revalidate(); panel.repaint(); panel.setVisible(true);
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
		setBounds(0,0,1200,800);
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setBackground(new Color(175, 238, 238));
		
		JLabel lblTitle = new JLabel("Monthly Inventory Report" ,SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		topPanel.add(lblTitle);

		add(topPanel, BorderLayout.NORTH);
		
		FlowLayout fl_controlPanel = new FlowLayout(FlowLayout.CENTER);
		fl_controlPanel.setVgap(10);
		fl_controlPanel.setHgap(10);
		JPanel controlPanel = new JPanel(fl_controlPanel);
		controlPanel.setBackground(new Color(175, 238, 238));

		final JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 17));
		monthChooser.getComboBox().setFont(new Font("Tahoma", Font.BOLD, 17));
		controlPanel.add(monthChooser);
		monthChooser.setPreferredSize(new Dimension(150, 35)); 
		Date date = new Date();
		@SuppressWarnings("deprecation")
		int year = 1900+date.getYear();
		
		final JYearChooser yearChooser = new JYearChooser();
		yearChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.getSpinner().setFont(new Font("Tahoma", Font.BOLD, 17));
		yearChooser.setEndYear(year);
		yearChooser.setStartYear(2023);
		yearChooser.setPreferredSize(new Dimension(100, 35));
		controlPanel.add(yearChooser);
		
		JPanel southPanel = new JPanel(new BorderLayout());
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setFont(new Font("Arial", Font.BOLD, 17));
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearSelected = yearChooser.getValue();
				monthSelected = monthChooser.getMonth();
				populateTable(yearSelected,monthSelected+1, southPanel); //combo box zero indexed 
			}
		});
		btnGenerate.setPreferredSize(new Dimension(150, 35));
		controlPanel.add(btnGenerate);
		
		topPanel.add(controlPanel, BorderLayout.SOUTH);
		add(southPanel, BorderLayout.CENTER);
	}
}
