package retailStore;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import javax.swing.JPanel;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class InventoryReportGenerator {
	private DRDataSource ds;
	private String[] columnNames = { "id", "name", "inStore", "inShelf", "total" };
	
	StyleBuilder boldStyle = stl.style().bold();
	StyleBuilder columnTitleStyle = stl.style().bold().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
	StyleBuilder fieldStyle = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
	TextColumnBuilder<Integer> productIdCol;
	TextColumnBuilder<String> productNameCol;
	TextColumnBuilder<Integer> inStoreCol;
	TextColumnBuilder<Integer> inShelfCol;
	TextColumnBuilder<Integer> inTotal;
	
	
	
	static String buildFilename(String reportName, String period, String segment, int version, String ext) {
		String safeReport = reportName.replaceAll("[\\\\/:*?\"<>|]", "-");
		String safeSegment = segment.replaceAll("[\\\\/:*?\"<>|]", "-");
		return String.format("%s-%s-%s-v%02d.%s", safeReport, period, safeSegment, version, ext);
	}

	private void buildReport() {
		InventoryReport inventoryReport = new InventoryReport();
		Object[][] inventoryData = inventoryReport.currentInventory();

		// creating data source to the report
		ds = new DRDataSource(columnNames);

		for (Object[] row : inventoryData) {
			ds.add(row[0], row[1], row[2], row[3], row[4]);
		}
		productIdCol = col.column("ID", "id", type.integerType())
				.setTitleStyle(columnTitleStyle).setFixedWidth(25);
		productNameCol = col.column("Name", "name", type.stringType())
				.setTitleStyle(columnTitleStyle).setMinWidth(200);
		inStoreCol = col.column("Store", "inStore", type.integerType())
				.setTitleStyle(columnTitleStyle);
		inShelfCol = col.column("Shelf", "inShelf", type.integerType())
				.setTitleStyle(columnTitleStyle);
		inTotal = col.column("Total", "total", type.integerType())
				.setTitleStyle(columnTitleStyle);
			
		}
	
	

	public JPanel showReport() throws DRException {
		buildReport();
		JasperPrint print = report()
				.title(Components.text("Sales Report")
				.setStyle(boldStyle))
				.columns(productIdCol, productNameCol, inStoreCol, inShelfCol, inTotal)
				.setColumnStyle(fieldStyle)
				.setDataSource(ds)
				.highlightDetailEvenRows()
				.toJasperPrint();

		 // Create JRViewer with JasperPrint
	    JRViewer viewer = new JRViewer(print);
	    
	    // Put JRViewer inside a JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new java.awt.BorderLayout());
	    panel.add(viewer, java.awt.BorderLayout.CENTER);
	    return panel;
	}

}
