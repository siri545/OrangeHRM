package libraries;


import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import static org.testng.Assert.assertTrue;

public class ExcelManager {
    final static Logger logger = Logger.getLogger(ExcelManager.class);

    private static String filePath;
    private static Workbook wb;
    private static Sheet sh;

    public ExcelManager(String excelFile, String sheetName) {
        try {
            File excelDataFile = new File(excelFile);
            filePath = excelDataFile.getAbsolutePath();
            logger.info("Reading Excel File ---> " + filePath);
            FileInputStream fs = new FileInputStream(excelDataFile);
            wb = getWorkbook(fs, filePath);
            sh = wb.getSheet(sheetName);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }

    public ExcelManager(String excelFile, int sheetIndex) {
        try {
            File excelDataFile = new File(excelFile);
            filePath = excelDataFile.getAbsolutePath();
            logger.info("Reading Excel File ---> " + filePath);
            FileInputStream fs = new FileInputStream(excelDataFile);
            wb = getWorkbook(fs, filePath);
            sh = wb.getSheetAt(sheetIndex);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
    }


    public String readExcelData(int rowIndex, int colIndex) {
        String cellData = null;
        try {
            Row row = sh.getRow(rowIndex);
            Cell cell = row.getCell(colIndex);

            cellData = formatDataCellToString(cell);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return cellData;
    }


    private String formatDataCellToString(Cell cell) {
        String cellString = null;
        try {
            DataFormatter formatter = new DataFormatter();
            cellString = formatter.formatCellValue(cell);
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return cellString;
    }


    private Workbook getWorkbook(FileInputStream fis, String excelFilePath) {
        Workbook workbook = null;
        try {
            if (excelFilePath.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            } else if (excelFilePath.endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);
            } else {
                throw new IllegalArgumentException("The specified file is not Excel file");
            }

        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return workbook;
    }

    public static void main(String[] args) {
        ExcelManager excelManager = new ExcelManager("src/test/resources/tesData/HRM2.xlsx", 0);
        logger.info("Excel data is successful print");
        logger.info(excelManager.getExcelAllData());
    }

    public String[][] getExcelAllData() {
        String[][] arrayExcelData = null;
        try {
            Iterator<Row> iterator = sh.iterator();
            int totalCols = sh.getRow(0).getPhysicalNumberOfCells();
            int totalRows = sh.getPhysicalNumberOfRows();
            arrayExcelData = new String[totalRows - 1][totalCols];
            int iRowCount = 0;

            while (iterator.hasNext()) {
                Row row = iterator.next();
                // skip the wor 1 because it is always the table data header information
                if (iRowCount > 0) {
                    Iterator<Cell> colIterator = row.iterator();
                    int iColCount = 0;
                    while (colIterator.hasNext()) {
                        Cell cell = colIterator.next();
                        // need to format the cells before read it as a string
                        String data = formatDataCellToString(cell);
                        arrayExcelData[iRowCount - 1][iColCount] = data;
                        logger.info("Row: " + iRowCount + ", Col: " + iColCount + ", Data: [" + data + "]");
                        iColCount++;
                    }
                }
                iRowCount++;
            }
        } catch (Exception e) {
            logger.error("Error: ", e);
            assertTrue(false);
        }
        return arrayExcelData;
    }

}









