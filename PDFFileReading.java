import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PDFFileReading {
	static org.apache.poi.xwpf.extractor.XWPFWordExtractor oleTextExtractor;
	
	static String storedPDFData;
	static String PDFFilePath="D:\\Docu\\data.PDF";
	static String editdocPath="D:\\Docu\\EditedFile.docx";
	static String TextFilePath="D:\\Docu\\a.txt";

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("XXXXXX");
		readPDFAndStoreDataInTextFile(PDFFilePath, TextFilePath);
		readPDFFile(PDFFilePath);
		
		
	}
	public static void readPDFFile(String PDFFilePath)
	{
		String filePath = PDFFilePath;
	    PDDocument document = null;
		try
	    {
	      document = PDDocument.load(new File(filePath));
	      document.getClass();
	      if (!document.isEncrypted())
	      {
	        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
	        stripper.setSortByPosition(true);
	        PDFTextStripper Tstripper = new PDFTextStripper();
	        storedPDFData = Tstripper.getText(document);

	        System.out.println("PDF Reading is Completed.  "+"PDF Data : " + storedPDFData);
	        
	        
	      }
	    }
		catch (IOException e)
        {
        	System.out.print("Exception :"+e);
        
        }
		
	}	
	public static void readPDFAndStoreDataInTextFile(String PDFFilePath,String TextFilePath)
	{
		File textFilePath = new File(TextFilePath);
	    String textFileDirectory = textFilePath.getParent();
	    String textFileNameWithoutExtension = textFilePath.getName().split("\\.")[0];
	    System.out.println("textFileNameWithoutExtension-"+textFileNameWithoutExtension);
	    
	  
	    File directory = new File(textFileDirectory);
	    if (directory.isDirectory())
	    {
	      System.out.println("Given directory '" + textFileDirectory + "' is valid.");

	      String tempFilePath = textFileDirectory + "\\" + textFileNameWithoutExtension + ".txt";
	      File filePath = new File(tempFilePath);
	      if (filePath.isFile())
	      {
	    	  System.out.println("File '" + textFileNameWithoutExtension + "' already exist in given directory '" + textFileDirectory + "'.");

	      }
	      else
	      {
	        try
	        {
	          filePath.createNewFile();
	          readPDFFile(PDFFilePath);

	          System.out.println("Read PDF and store data successful ");
	          FileWriter output = new FileWriter(tempFilePath, true);
	       
			output.write(storedPDFData);
	          output.close();
	        }
	        catch (IOException e)
	        {
	        	System.out.print("Exception :"+e);
	        
	        }
	      }
	    }
	    else
	    {
	    	System.out.println("Given directory '" + textFileDirectory + "' is not valid.");

	     
	    }
	   
	    
	  }
	


}
