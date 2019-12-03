package billprinter;

import java.awt.Color;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.awt.print.PrinterException;
import java.awt.print.Printable;

import javax.print.PrintServiceLookup;
import javax.print.PrintException;
import javax.print.PrintService;

import javax.swing.JOptionPane;

import java.util.Properties;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Printer {
    
    
    public static final String DEFAULT_PRINTER = "default printer";
    public static final String USER_NAME = "user name";
    public static final String USER_PASSWORD = "user password";
    
    
    private Properties properties;
    
    private InputStream is = null;
    private OutputStream os = null;
    private File propertiesDisk = null;
    
    private static PrintService [] printServices;
    
    
     Printer(){
    
        
                                                             //checks if settings.txt exist else creates Dir Properties and settings.txt file
         propertiesDisk = new File("settings.bin");
         
        if(!propertiesDisk.exists()){
            File propertyDir = new File("Properties");
            propertiesDisk = new File(propertyDir+"\\settings.bin");
            propertiesDisk.mkdirs();
        }
        
        properties = new Properties();
        
    }
    
    
     public static void setDefaultPrinter(){
     
         printServices = PrintServiceLookup.lookupPrintServices(null, null);
         
         PrintService job = (PrintService) JOptionPane.showInputDialog(JOptionPane.getRootFrame(), "Select Printer", "PRINTER SETUP", JOptionPane.QUESTION_MESSAGE,
                 null, printServices, null);
         
         System.out.println(BillPrinter.getTime());
         System.out.println(job.toString());
//         PrinterJob defaultPrinter = (PrinterJob) job.createPrintJob();
     }
     
                                                        //sets a default printer to ignore look up of available printers each time the app starts up
    public void setProperty(String key, String value){
        
        properties.setProperty(key, value);
      
    }
    
    
    public String getProperty(String key){
    
        String prop;
        
        prop = properties.getProperty(key, null);
       
       return prop;
    }
    
    
    public void saveProperties(){
    
        try{
        
            os = new BufferedOutputStream(new FileOutputStream(propertiesDisk));
            properties.store(os, "settings101");
            
        }catch(IOException ioe){}
        
    }
    
    
    public void loadProperties(){
    
        try{
        
            is = new BufferedInputStream(new FileInputStream(propertiesDisk));
            properties.load(is);
            
        }catch(IOException ioe){}
    } 
    
                                                        //method to print bill
    public static void print(){
    
        PrinterJob printer = PrinterJob.getPrinterJob();
        printer.setPrintable(new Content());
        
        setDefaultPrinter();
        System.out.println(printer.getPrintService());
        
     /*   try{
        
            printer.print();
            
        }catch(PrinterException pe){
        System.out.println(pe.toString());
        }
            */
        
    }
    
    
    
                                                            //content to print out from printer
    private static class Content implements Printable{
    
        Content(){}
        
        
        @Override
        public int print(Graphics g, PageFormat pf, int pageIndex){
            
            if(pageIndex > 0){
               return Printable.NO_SUCH_PAGE;
            }
        
            pf.setOrientation(PageFormat.PORTRAIT);
            
            Graphics2D Content2D = (Graphics2D) EntryPage.getContent();
            
            
            Content2D.setColor(Color.BLACK);
            Content2D.translate(pf.getImageableX(), pf.getImageableY());
            
            String time = BillPrinter.getTime();
            Content2D.drawString(time, 35, 350);
            
            return Printable.PAGE_EXISTS;
        }
    }
    
}
