package it.bplus.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Blank;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.icesoft.faces.component.dataexporter.OutputTypeHandler;

public class MyExcelOutputHandler extends OutputTypeHandler {

	WritableSheet sheet = null;
	WritableWorkbook workbook = null;


	public MyExcelOutputHandler(String path) {
		super(path);

		
		try{
			WorkbookSettings settings = new WorkbookSettings();
			//settings.setLocale(fc.getViewRoot().getLocale());
			settings.setLocale(new Locale("it_IT"));
			workbook = Workbook.createWorkbook(super.getFile());
			sheet = workbook.createSheet("lista_meters", 0);

			this.mimeType = "application/vnd.ms-excel";
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}

	@Override
	public void flushFile() {
		try{
			workbook.write();
			workbook.close();
		}
		catch( IOException ioe){
			ioe.printStackTrace();
		}
	}

	@Override
	public void writeCell(Object output, int col, int row) {
		WritableCellFormat format = getCellFormat();
		WritableCell cell = deriveCellFromObject(output, col, row + 1, format);
		addCell(cell);
	}

	@Override
	public void writeHeaderCell(String text, int col) {
		WritableCellFormat format = getHeaderCellFormat();
		WritableCell cell = deriveCellFromObject(text, col, 0, format);
		addCell(cell);
	}

	/**
	 * The row indexing is zero based, from the perspective of the row data,
	 * ignoring how many rows were used for the header 
	 */
	public void writeFooterCell(Object output, int col, int row) {
		WritableCellFormat format = getFooterCellFormat();
		WritableCell cell = deriveCellFromObject(output, col, row + 1, format);
		addCell(cell);
	}

	/**
	 * Unlike the other methods, row is specific to the whole worksheet,
	 * not just the data rows
	 * @throws ParseException 
	 */
	protected WritableCell deriveCellFromObject(Object output, int col, int row, WritableCellFormat format) {
		WritableCell cell = null;
		if (output == null) {
			cell = new Blank(col, row);
		}
		
		
		else if (output instanceof String) {
			try {
				Double n = Double.parseDouble((String)output);
				cell = new Number(col, row, n);
				
				// format number
			} catch (NumberFormatException e) {
				String s = (String)output;
				if (s.contains("Mostra")){
					String app = s.split("Mostra")[0];
					cell = new Label(col, row, app);
				}
				else if(s.equals("null")){
					cell = new Blank(col, row);
				}
				else if(s.contains("null ")){
					String appz = s.substring(5);
					cell = new Label(col, row, appz);
				}
				//Pattern pat = Pattern.compile("(2+[0-9]+[0-9]+[0-9]-[(0-1)+(0-9)]-[(0-3)+(0-9)]");
				else if (row==1 && col==0 && s.startsWith("G") && s.length()==12){
					
					//writeHeaderCell("Codice sala",0);
					WritableCell cell_1 = new Label(0, 0, "Codice sala");
					cell_1.setCellFormat(getHeaderCellFormat()); addCell(cell_1);
//					writeHeaderCell("Nome sala",1);
					WritableCell cell_2 = new Label(1, 0, "Nome sala");
					cell_2.setCellFormat(getHeaderCellFormat()); addCell(cell_2);
//					writeHeaderCell("Data",2);
					WritableCell cell_3 = new Label(2, 0, "Data");
					cell_3.setCellFormat(getHeaderCellFormat()); addCell(cell_3);
//					writeHeaderCell("G.macchina 600",3);
					WritableCell cell_4 = new Label(3, 0, "G.macchina 600");
					cell_4.setCellFormat(getHeaderCellFormat()); addCell(cell_4);
//					writeHeaderCell("G.maccchina Sogei",4);
					WritableCell cell_5 = new Label(4, 0, "G.maccchina Sogei");
					cell_5.setCellFormat(getHeaderCellFormat()); addCell(cell_5);
//					writeHeaderCell("Bet 600",5);
					WritableCell cell_6 = new Label(5, 0, "Bet 600");
					cell_6.setCellFormat(getHeaderCellFormat()); addCell(cell_6);
//					writeHeaderCell("Bet Sogei",6);
					WritableCell cell_7 = new Label(6, 0, "Bet Sogei");
					cell_7.setCellFormat(getHeaderCellFormat()); addCell(cell_7);
//					writeHeaderCell("Win 600",7);
					WritableCell cell_8 = new Label(7, 0, "Win 600");
					cell_8.setCellFormat(getHeaderCellFormat()); addCell(cell_8);
//					writeHeaderCell("Win Sogei",8);
					WritableCell cell_9 = new Label(8, 0, "Win Sogei");
					cell_9.setCellFormat(getHeaderCellFormat()); addCell(cell_9);
//					writeHeaderCell("Games Played 600",9);
					WritableCell cell_10 = new Label(9, 0, "Games Played 600");
					cell_10.setCellFormat(getHeaderCellFormat()); addCell(cell_10);
//					writeHeaderCell("Games Played Sogei",10);
					WritableCell cell_11 = new Label(10, 0, "Games Played Sogei");
					cell_11.setCellFormat(getHeaderCellFormat()); addCell(cell_11);
//					writeHeaderCell("Total In 600",11);
					WritableCell cell_12 = new Label(11, 0, "Total In 600");
					cell_12.setCellFormat(getHeaderCellFormat()); addCell(cell_12);
//					writeHeaderCell("Total In Sogei",12);
					WritableCell cell_13 = new Label(12, 0, "Total In Sogei");
					cell_13.setCellFormat(getHeaderCellFormat()); addCell(cell_13);
//					writeHeaderCell("Total Out 600",13);
					WritableCell cell_14 = new Label(13, 0, "Total Out 600");
					cell_14.setCellFormat(getHeaderCellFormat()); addCell(cell_14);
//					writeHeaderCell("Total Out Sogei",14);
					WritableCell cell_15 = new Label(14, 0, "Total Out Sogei");
					cell_15.setCellFormat(getHeaderCellFormat()); addCell(cell_15);

					cell = new Label(col, row, (String)output);
				}
				
				else if (row==1 && col==2 && s.startsWith("GD") && s.length()==11){					
//					writeHeaderCell("GS VLT ID",2);
					WritableCell cell_3 = new Label(2, 0, "GS VLT ID");
					cell_3.setCellFormat(getHeaderCellFormat()); addCell(cell_3);
//					writeHeaderCell("Aams VLT ID",3);
					WritableCell cell_4 = new Label(3, 0, "Aams VLT ID");
					cell_4.setCellFormat(getHeaderCellFormat()); addCell(cell_4);
//					writeHeaderCell("Data",4);
					WritableCell cell_5 = new Label(4, 0, "Data");
					cell_5.setCellFormat(getHeaderCellFormat()); addCell(cell_5);
					
					
					
					cell = new Label(col, row, (String)output);
				}
				
				else if(s.contains("2011-") || s.contains("2010-")){
					DateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd");
					Date data = null;
					try {
						data = df_1.parse(s);
						DateFormat df_2 = new SimpleDateFormat("dd-MM-yyyy");
						String dat = df_2.format(data);	
						cell = new Label(col, row, dat);
					} catch (ParseException e1) {
						e1.printStackTrace();
						cell = new Blank(col, row);
					}					
				}
				else
					cell = new Label(col, row, (String)output);
			}
		}
		else if (output instanceof Double) {
			cell = new Number(col, row, ((Double)output).doubleValue());
		}
		else if (output instanceof Date) {
			cell = new DateTime(col, row, (Date) output);
		}
		else if (output instanceof Boolean) {
			cell = new jxl.write.Boolean(col, row, ((Boolean)output).booleanValue());
		}
		else {
			cell = new Label(col, row + 1, "");
		}

		if (cell != null && format != null) {
			cell.setCellFormat(format);
		}
		
		return cell;
	}

	protected void addCell(WritableCell cell) {
		try {
			sheet.addCell(cell);
		}
		catch(WriteException we){
			System.out.println("Could not write excel cell");
			we.printStackTrace();
		}
	}

	protected WritableCellFormat getHeaderCellFormat() {
		WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		return format;
	}

	protected WritableCellFormat getCellFormat() {
		return null;
	}

	protected WritableCellFormat getFooterCellFormat() {
		WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
		WritableCellFormat format = new WritableCellFormat(font);
		return format;
	}

}
