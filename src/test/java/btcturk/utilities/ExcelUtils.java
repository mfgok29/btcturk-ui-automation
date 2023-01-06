package btcturk.utilities;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    HSSFSheet sheet;
    String path;
    HSSFWorkbook wb = new HSSFWorkbook();

    public ExcelUtils(String path) {
        this.path = path;
    }

    public void createSheet(String sheetName) {
        sheet = wb.createSheet(sheetName);
    }

    public void setData(String value, int rowNum, int colNum) throws IOException {
        if (sheet.getRow(rowNum) == null) {
            sheet.createRow(rowNum).createCell(colNum).setCellValue(value);
        } else {
            sheet.getRow(rowNum).createCell(colNum).setCellValue(value);
        }

        FileOutputStream fileOut = new FileOutputStream(path);

        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public String getData(String getFile,String sheetName, int rowNum, int colNum) throws IOException {
        FileInputStream fileInput = new FileInputStream(getFile);

        String data = wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).toString();

        fileInput.close();
        return data;
    }
}
