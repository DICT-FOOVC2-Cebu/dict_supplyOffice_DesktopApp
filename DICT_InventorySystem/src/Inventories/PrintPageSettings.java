/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventories;

import static Inventories.PrintSettings.disableDoubleBuffering;
import static Inventories.PrintSettings.enableDoubleBuffering;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RepaintManager;

/**
 *
 * @author Eunar B. Dayangco
 */
public class PrintPageSettings implements Printable,Pageable{

    private JPanel panel;
    private PageFormat format;
    private int numPages;
    
    public PrintPageSettings(JPanel panel) {
        
        this.panel = panel;
        Dimension totalSpace = this.panel.getPreferredSize();
        format = PrinterJob.getPrinterJob().defaultPage();
       
        numPages = (int) Math.ceil(totalSpace .height/format.getImageableHeight());
    }
    
     public void print(){
        
        PrinterJob printJob = PrinterJob.getPrinterJob();
        format = printJob.pageDialog(printJob.defaultPage());
         Paper paper = new Paper();
        double margin = 0; // half inch
        paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
        - margin * 2);
        format.setPaper(paper);
        printJob.setPrintable(this,format);
        printJob.setPageable(this);
        
        if(printJob.printDialog()){
            try {
                printJob.print();
            } catch (PrinterException ex) {
                Logger.getLogger(PrintSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    
    

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex<0 | (pageIndex >=numPages)){
            return Printable.NO_SUCH_PAGE;
        }
        else{
            Graphics2D g = (Graphics2D) graphics;
            g.translate(pageFormat.getImageableX(), pageFormat.getImageableY() - pageIndex * pageFormat.getImageableHeight());
            g.scale(0.76, 0.671);
            
            
            
            disableDoubleBuffering((Component)this.panel);
             this.panel.paint(graphics);
             enableDoubleBuffering((Component)this.panel);
             return Printable.PAGE_EXISTS;
        }
    }

    @Override
    public int getNumberOfPages() {
        return numPages;
    }

    @Override
    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException {
        return format;
    }

    @Override
    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException {
        return this;
    }
    
     public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
    
}
