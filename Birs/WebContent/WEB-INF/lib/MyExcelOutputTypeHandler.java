package it.bplus.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.icesoft.faces.component.dataexporter.OutputTypeHandler;

public class MyExcelOutputHandler extends OutputTypeHandler {

	Sheet sheet = null;
	Workbook workbook = null;


	public MyExcelOutputHandler(String path) {
		super("workbook.xlsx");

		try {
		workbook = new XSSFWorkbook();		    
//			workbook = Workbook.createWorkbook(super.getFile());
		
			workbook.write(super.getFile());
		
		
		sheet = workbook.createSheet("lista_meters");
		
		
		//this.mimeType = "application/vnd.ms-excel";
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	@Override
	public void flushFile() {
		try{
//			workbook.write();
//			workbook.close();			
		    workbook.write(super.getFile());
		    super.getFile().close();
		}
		catch( IOException ioe){
			ioe.printStackTrace();
		}
	}

	@Override
	public void writeCell(Object output, int col, int row) {
		deriveCellFromObject(output, col, row + 1, null);
	}

	@Override
	public void writeHeaderCell(String text, int col) {
		deriveCellFromObject(text, col, 0, getHeaderCellStyle());
	}

	/**
	 * The row indexing is zero based, from the perspective of the row data,
	 * ignoring how many rows were used for the header 
	 */
	public void writeFooterCell(Object output, int col, int row) {
		deriveCellFromObject(output, col, row+1, getFooterCellStyle());
	}

	/**
	 * Unlike the other methods, row is specific to the whole worksheet,
	 * not just the data rows
	 * @throws ParseException 
	 */
	protected Cell deriveCellFromObject(Object output, int col, int row1, CellStyle style) {
		Row row = sheet.createRow((short)(row1));
		Cell cell = row.createCell(col);
		if (output == null) {
			cell.setCellValue("");
		}
		
		
		else if (output instanceof String) {
			try {
				Double n = Double.parseDouble((String)output);
				cell.setCellValue(n);
				
				// format number
			} catch (NumberFormatException e) {
				String s = (String)output;
				if (s.contains("Mostra")){
					String app = s.split("Mostra")[0];
					cell.setCellValue(app);
				}
				else if(s.equals("null")){
					cell.setCellValue("");
				}
				else if(s.contains("null ")){
					String appz = s.substring(5);
					cell.setCellValue(appz);
				}
				//Pattern pat = Pattern.compile("(2+[0-9]+[0-9]+[0-9]-[(0-1)+(0-9)]-[(0-3)+(0-9)]");
				else if (row1==1 && col==0 && s.startsWith("G") && s.length()==12){
					
					//writeHeaderCell("Codice sala",0);
					Cell cell_1 = sheet.createRow((short)0).createCell(0); cell_1.setCellValue("Codice sala"); 
					cell_1.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Nome sala",1);
					Cell cell_2 = sheet.createRow((short)0).createCell(1); cell_2.setCellValue("Nome sala");
					cell_2.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Data",2);
					Cell cell_3 = sheet.createRow((short)0).createCell(2); cell_3.setCellValue("Data");
					cell_3.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("G.macchina 600",3);
					Cell cell_4 = sheet.createRow((short)0).createCell(3); cell_4.setCellValue("G.macchina 600");
					cell_4.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("G.maccchina Sogei",4);
					Cell cell_5 = sheet.createRow((short)0).createCell(4); cell_5.setCellValue("G.maccchina Sogei");
					cell_5.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Bet 600",5);
					Cell cell_6 = sheet.createRow((short)0).createCell(5); cell_6.setCellValue("Bet 600");
					cell_6.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Bet Sogei",6);
					Cell cell_7 = sheet.createRow((short)0).createCell(6); cell_7.setCellValue("Bet Sogei");
					cell_7.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Win 600",7);
					Cell cell_8 = sheet.createRow((short)0).createCell(7); cell_8.setCellValue("Win 600");
					cell_8.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Win Sogei",8);
					Cell cell_9 = sheet.createRow((short)0).createCell(8); cell_9.setCellValue("Win Sogei");
					cell_9.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Games Played 600",9);
					Cell cell_10 = sheet.createRow((short)0).createCell(9); cell_10.setCellValue("Games Played 600");
					cell_10.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Games Played Sogei",10);
					Cell cell_11 = sheet.createRow((short)0).createCell(10); cell_11.setCellValue("Games Played Sogei");
					cell_11.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Total In 600",11);
					Cell cell_12 = sheet.createRow((short)0).createCell(11); cell_12.setCellValue("Total In 600");
					cell_12.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Total In Sogei",12);
					Cell cell_13 = sheet.createRow((short)0).createCell(12); cell_13.setCellValue("Total In Sogei");
					cell_13.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Total Out 600",13);
					Cell cell_14 = sheet.createRow((short)0).createCell(13); cell_14.setCellValue("Total Out 600");
					cell_14.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Total Out Sogei",14);
					Cell cell_15 = sheet.createRow((short)0).createCell(14); cell_15.setCellValue("Total Out Sogei");
					cell_15.setCellStyle(getHeaderCellStyle());

					cell.setCellValue((String)output);
				}
				
				else if (row1==1 && col==2 && s.startsWith("GD") && s.length()==11){					
//					writeHeaderCell("GS VLT ID",2);
					Cell cell_3 = sheet.createRow((short)0).createCell(2); cell_3.setCellValue("GS VLT ID");
					cell_3.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Aams VLT ID",3);
					Cell cell_4 = sheet.createRow((short)0).createCell(3); cell_4.setCellValue("Aams VLT ID");
					cell_4.setCellStyle(getHeaderCellStyle());
//					writeHeaderCell("Data",4);
					Cell cell_5 = sheet.createRow((short)0).createCell(4); cell_5.setCellValue("Data");
					cell_5.setCellStyle(getHeaderCellStyle());
					
					
					
					cell.setCellValue((String)output);
				}
				
				else if(s.contains("2011-") || s.contains("2010-")){
					DateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd");
					Date data = null;
					try {
						data = df_1.parse(s);
						DateFormat df_2 = new SimpleDateFormat("dd-MM-yyyy");
						String dat = df_2.format(data);	
						cell.setCellValue(dat);
					} catch (ParseException e1) {
						e1.printStackTrace();
						cell.setCellValue("");
					}					
				}
				else
					cell.setCellValue((String)output);
			}
		}
		else if (output instanceof Double) {
			cell.setCellValue((Double)output);
		}
		else if (output instanceof Date) {
			cell.setCellValue((Date) output);
		}
		else if (output instanceof Boolean) {
			cell.setCellValue((Boolean)output);
		}
		else {
			cell = sheet.createRow((short)(row1+1)).createCell(col);
			cell.setCellValue("");
		}

		if (cell != null && style != null) {
			cell.setCellStyle(style);
		}
		
		return cell;
	}

	protected void addCell(WritableCell cell) {
//		try {
//			//sheet.addCell(cell);
//		}
//		catch(WriteException we){
//			System.out.println("Could not write excel cell");
//			we.printStackTrace();
//		}
	}

	protected CellStyle getHeaderCellStyle() {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("Arial");
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	protected WritableCellFormat getCellFormat() {
		return null;
	}

	protected CellStyle getFooterCellStyle() {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short)10);
		font.setFontName("Arial");
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

}
