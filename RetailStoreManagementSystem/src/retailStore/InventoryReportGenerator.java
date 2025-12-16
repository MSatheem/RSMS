package retailStore;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;

public class InventoryReportGenerator {
	private DRDataSource ds;
	private String[] columnNames = { "region", "product", "amount" };
	
	////////////////////////////
	StyleBuilder boldStyle = stl.style().bold();
	StyleBuilder columnTitleStyle = stl.style().bold().setBackgroundColor(java.awt.Color.LIGHT_GRAY);
	TextColumnBuilder<String> productCol = col.column("Region", "region", type.stringType())
			.setTitleStyle(columnTitleStyle);
	TextColumnBuilder<Integer> regionCol = col.column("Product", "product", type.integerType())
			.setTitleStyle(columnTitleStyle);
	TextColumnBuilder<Integer> amountCol = col.column("Amount", "amount", type.integerType())
			.setTitleStyle(columnTitleStyle);

	
	
	
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

	}

	public void showReport() throws DRException {
		JasperPrint print = report().title(Components.text("Sales Report").setStyle(boldStyle))
				.columns(regionCol, productCol, amountCol).setDataSource(ds).toJasperPrint();
	}

	public void inventoryReportPDF(String[] args) throws DRException {
		buildReport();

		
		report().title(Components.text("Sales Report").setStyle(boldStyle)) // setting title of the report
				.columns(regionCol, productCol, amountCol).setDataSource(ds)
				.toPdf(export.pdfExporter(buildFilename("SalesReport", "2025-12", "Global", 1, "pdf")));
	}

}
