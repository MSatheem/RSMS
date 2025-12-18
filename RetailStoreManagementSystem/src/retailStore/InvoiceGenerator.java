package retailStore;

import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import javax.swing.JPanel;

import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import static net.sf.dynamicreports.report.builder.DynamicReports.sbt;;

public class InvoiceGenerator {
	private DRDataSource ds;
	private String[] columnNames = { "id", "name", "quantity", "unitPrice", "subTotal" };
	
	
	//style builders
	StyleBuilder boldStyle = stl.style().bold();
	StyleBuilder columnTitleStyle = stl.style().bold().setBackgroundColor(java.awt.Color.LIGHT_GRAY)
			.setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
	StyleBuilder fieldStyle = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
	StyleBuilder subtotalStyle = stl.style(boldStyle).setBackgroundColor(java.awt.Color.LIGHT_GRAY) .setHorizontalTextAlignment(HorizontalTextAlignment.LEFT); 
	
	
	
	TextColumnBuilder<Integer> productIdCol;
	TextColumnBuilder<String> productNameCol;
	TextColumnBuilder<Integer> quantityCol;
	TextColumnBuilder<Double> unitPriceCol;
	TextColumnBuilder<Double> subTotalCol;

	// footer
	TextColumnBuilder<Double> priceCol = col.column("Unit Price", "price", type.doubleType());
	TextColumnBuilder<Integer> qtyCol = col.column("Quantity", "quantity", type.integerType());

	// Add footer to show sum
//	qtyCol.setColumnFooter(cmp.text("Total Qty:").setStyle(stl.style().bold()));
//	totalCol.setColumnFooter(cmp.text("Grand Total:").setStyle(stl.style().bold()));

	static String buildFilename(String reportName, String period, String segment, int version, String ext) {
		String safeReport = reportName.replaceAll("[\\\\/:*?\"<>|]", "-");
		String safeSegment = segment.replaceAll("[\\\\/:*?\"<>|]", "-");
		return String.format("%s-%s-%s-v%02d.%s", safeReport, period, safeSegment, version, ext);
	}

	private void buildReport(Invoice invoice) {

		// creating data source to the report
		ds = new DRDataSource(columnNames);

		for (Object[] row : invoice.tableArrayRead()) {
			ds.add(row[0], row[1], row[2], row[4], row[7]);
		}
		productIdCol = col.column("ID", "id", type.integerType()).setTitleStyle(columnTitleStyle).setFixedWidth(25);
		productNameCol = col.column("Name", "name", type.stringType()).setTitleStyle(columnTitleStyle).setMinWidth(200);
		quantityCol = col.column("Quantity", "quantity", type.integerType()).setTitleStyle(columnTitleStyle);
		unitPriceCol = col.column("Price", "unitPrice", type.doubleType()).setTitleStyle(columnTitleStyle);
		subTotalCol = col.column("Sub Total", "subTotal", type.doubleType()).setTitleStyle(columnTitleStyle);

	}

	public JPanel showInvoice(Invoice invoice) throws DRException {
		buildReport(invoice);
		JasperPrint print = report()
                .title(Components.text("Invoice /Bill").setStyle(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
						Components.text("Customer ID: " + invoice.getCustomerId()),
						Components.text("Date: " + invoice.getDate()))
				.columns(productIdCol, productNameCol, quantityCol, unitPriceCol, subTotalCol)
				.setColumnStyle(fieldStyle).setDataSource(ds).highlightDetailEvenRows()
				
				.subtotalsAtSummary(
						sbt.text("Total", unitPriceCol).setStyle(subtotalStyle), sbt.sum(subTotalCol).setStyle(subtotalStyle).setPattern("Rs #,##0.00"))
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
