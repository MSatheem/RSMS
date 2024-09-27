package retailStore;

import java.awt.print.*;
 
import java.awt.*;


public class InvoicePrinter implements Printable{
 
	String[][] string2d;
	int count;
	double total;
	int xCoordinate=100;
	int yCoordinate=100;
	
	public InvoicePrinter() {
	}
	
	
	public InvoicePrinter(String[][] string2d, int count, double total) {
		this.string2d = string2d;
		this.count = count;
		this.total = total;
	}
	

	public void InvoicePrinterData(String[][] arr, int count) {
		string2d = arr;
		this.count =  count;
	}
 
    public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {
 
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        /* Now we perform our rendering */
        //g.drawString("Hello world!", 100, 100);
        //g.drawString("2nd Line", 200, 100);
        
        int coordinateX = 100;
        for(int i=0; i<count; i++) {

        	if(i ==0) {
        		g.drawString("ABC Distributors", coordinateX, i);
        	}
        	//g.drawString(string2d[i][0], xCoordinate+200, yCoordinate+100);
        	g.drawString(string2d[i][0] ,100 , coordinateX );
        	g.drawString(string2d[i][1] ,140 , coordinateX );
        	g.drawString(string2d[i][2] ,220 , coordinateX );
        	g.drawString(string2d[i][3] ,300 , coordinateX );
        	g.drawString(string2d[i][4] ,350 , coordinateX );
        	g.drawString(string2d[i][5] ,460 , coordinateX );
        	coordinateX = coordinateX + 20;
        	
        	
        }
        g.drawString("Total ", 400 , coordinateX);
        g.drawString(String.valueOf(total), 460 , coordinateX);
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
 
    public  void main() throws PrinterException {
    	PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                 job.print();
            } catch (PrinterException ex) {
             /* The job did not successfully complete */
            }
        }
    }
}