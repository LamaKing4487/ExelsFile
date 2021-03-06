import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CreateInvoice {
    public static void main(String[] args) {
        try{
            // Create workbook in .xlsx format
            Workbook workbook = new XSSFWorkbook();
            //For .xssx workbook use new HSSFWorkBook()
            //Create Sheet
            Sheet sh = workbook.createSheet("Invoices");
            //Create top row with column headings
            String[] columnHeadings = {"Item id", "Item Name", "Qty", "Item Price", "Sold Date"};
            //We want to make it bold with a foreground color
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short)12);
            headerFont.setColor(IndexedColors.BLACK.index);
            //Create a cellstyle with the font
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
            //Create the header row
            Row headerRow = sh.createRow(0);
            //Iterate over the column heading to create columns
            for(int i=0; i< columnHeadings.length; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeadings[i]);
                cell.setCellStyle(headerStyle);
            }
            //Freeze Header Row
            sh.createFreezePane(0,  1);
            //Fill data
            ArrayList<Invoices> a = createData();
            CreationHelper creationHelper = workbook.getCreationHelper();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(creationHelper.createDataFormat().getFormat("MM/dd?yyyy"));
            int rownum =1;
            for(Invoices i : a) {
                Row row = sh.createRow(rownum++);
                row.createCell(0).setCellValue(i.getItemId());
                row.createCell(1).setCellValue(i.getItemName());
                row.createCell(2).setCellValue(i.getItemQty());
                row.createCell(3).setCellValue(i.getTotalPrice());
                Cell dateCell = row.createCell(4);
                dateCell.setCellValue(i.getItemSoldDate());
                dateCell.setCellStyle(dateStyle);

            }
            //Group and collapse rows
            int noOfRows = sh.getLastRowNum();
            sh.groupRow(1, noOfRows);
            sh.setRowGroupCollapsed(1, true);
            //Create a sum row
            Row sumRow = sh.createRow(rownum);
            Cell sumRowTitle = sumRow.createCell(0);
            sumRowTitle.setCellValue("Total");
            sumRowTitle.setCellStyle(headerStyle);

            String strFormula = "SUM(D2:D"+rownum+")";
            Cell sumcell = sumRow.createCell(3);
            sumcell.setCellFormula(strFormula);
            sumcell.setCellValue(true);


            //Autosize columns
            for(int i=0; i< columnHeadings.length; i++){
                sh.autoSizeColumn(i);
            }
            Sheet sh2 = workbook.createSheet("Second");
            //Write the output to file
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Professional\\Documents\\Documents\\invoices.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("completed");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static ArrayList<Invoices> createData() throws ParseException {ArrayList<Invoices> a = new ArrayList<>();
        a.add(new Invoices(1, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(2, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(3, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(4, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(1, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(2, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(3, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(4, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(1, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(2, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(3, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(4, "Pen", 100, 20.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(1, "Book", 2, 10.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(2, "Table", 1, 50.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        a.add(new Invoices(3, "Lamp", 5, 100.0, new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2020")));
        return a;
    }
}
