package retailStore;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import javax.swing.JPanel;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class InventoryReportGenerator {
	private DRDataSource ds;
	private String[] columnNames = { "region", "product", "amount" };
	
	////////////////////////////
	StyleBuilder boldStyle = stl.style().bold();
	StyleBuilder columnTitleStyle = stl.style().bold().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
	TextColumnBuilder<String> productCol;
	TextColumnBuilder<Integer> regionCol;
	TextColumnBuilder<Integer> amountCol;

	
	
	
	static String buildFilename(String reportName, String period, String segment, int version, String ext) {
		String safeReport = reportName.replaceAll("[\\\\/:*?\"<>|]", "-");
		String safeSegment = segment.replaceAll("[\\\\/:*?\"<>|]", "-");
		return String.format("%s-%s-%s-v%02d.%s", safeReport, period, safeSegment, version, ext);
	}

	private void buildReport() {
		InventoryReport inventoryReport = new InventoryReport(2024, 12);
		Object[][] inventoryData = inventoryReport.genreatereport();

		// creating data source to the report
		ds = new DRDataSource(columnNames);

		for (Object[] row : inventoryData) {
			ds.add(row[1], row[2], row[3]);
		}
		productCol = col.column("Region", "region", type.stringType())
				.setTitleStyle(columnTitleStyle);
		regionCol = col.column("Product", "product", type.integerType())
				.setTitleStyle(columnTitleStyle);
		amountCol = col.column("Amount", "amount", type.integerType())
				.setTitleStyle(columnTitleStyle);
	}

	public JPanel showReport() throws DRException {
		buildReport();
		JasperPrint print = report().title(Components.text("Sales Report").setStyle(boldStyle))
				.columns(regionCol, productCol, amountCol).setDataSource(ds).toJasperPrint();
	
		 // Create JRViewer with JasperPrint
	    JRViewer viewer = new JRViewer(print);
	    
	    // Put JRViewer inside a JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new java.awt.BorderLayout());
	    panel.add(viewer, java.awt.BorderLayout.CENTER);
	    return panel;
	}

}
