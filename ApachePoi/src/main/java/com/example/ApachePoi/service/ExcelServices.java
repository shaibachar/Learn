package com.example.ApachePoi.service;

import com.example.ApachePoi.domain.Employee;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ExcelServices {
    public static final String WORKBOOK_SHEET = "Data";
    private String[] columns;
    private List<Employee> employees;

    public ExcelServices() {
        init();
    }

    private void init() {
        columns = new String[]{"Name", "Email", "Date Of Birth", "Salary"};
        employees = new ArrayList<>();

        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 7, 21);
        employees.add(new Employee("Rajeev Singh", "rajeev@example.com",
                dateOfBirth.getTime(), 1200000.0));

        dateOfBirth.set(1965, 10, 15);
        employees.add(new Employee("Thomas cook", "thomas@example.com",
                dateOfBirth.getTime(), 1500000.0));

        dateOfBirth.set(1987, 4, 18);
        employees.add(new Employee("Steve Maiden", "steve@example.com",
                dateOfBirth.getTime(), 1800000.0));

        dateOfBirth.set(1987, 5, 18);
        employees.add(new Employee("Steve1 Maiden", "steve@example.com",
                dateOfBirth.getTime(), -1800000.0));

        dateOfBirth.set(1988, 5, 18);
        employees.add(new Employee("    Steve3 Maiden", "steve@example.com    ",
                dateOfBirth.getTime(), -1800000));

    }

    /**
     * @param filePath
     * @throws InvalidFormatException
     * @throws IOException
     */
    public void trimExistingWorkbook(String filePath) throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        File file = new File(filePath);
        Workbook workbook = WorkbookFactory.create(file);

        // Get Sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(false);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        // Create a CellStyle with the font
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(headerFont);


        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        for (int i = firstRowNum; i < lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                CellType cellTypeEnum = cell.getCellTypeEnum();
                if (!cellTypeEnum.equals(CellType.STRING)) {
                    cell.setCellStyle(cellStyle);
                    continue;
                }
                String stringCellValue = cell.getStringCellValue();
                if (!stringCellValue.isEmpty()) {

                    try {
                        double number = Double.parseDouble(stringCellValue);
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellValue(number);
                        continue;
                    } catch (Exception e) {
                        // do nothing
                    }

//                    cell.setCellType(CellType.STRING);
                    cell.setCellValue(stringCellValue.trim());
                    cell.setCellStyle(cellStyle);
                }

            }
        }

        // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();


        // Closing the workbook
        workbook.close();
    }

    /**
     * @param filePath
     * @throws InvalidFormatException
     * @throws IOException
     */
    public void modifyExistingWorkbook(String filePath) throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new File(filePath));

        // Get Sheet at index 0
        Sheet sheet = workbook.getSheet(WORKBOOK_SHEET);

        // Get Row at index 1
        Row row = sheet.getRow(1);

        // Get the Cell at index 2 from the above row
        Cell cell = row.getCell(2);

        // Create the cell if it doesn't exist
        if (cell == null)
            cell = row.createCell(2);

        // Update the cell's value
        cell.setCellType(CellType.STRING);
        cell.setCellValue("Updated Value");

      /*  // Write the output to the file
        FileOutputStream fileOut = new FileOutputStream(EXISTING_SPREADSHEET_XLSX);
        workbook.write(fileOut);
        fileOut.close();
*/
        // Closing the workbook
        workbook.close();
    }

    /**
     * @param filePath
     * @throws IOException
     */
    public void create(String filePath) throws IOException {

        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat,
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet(WORKBOOK_SHEET);

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        // Create Other rows and cells with employees data
        int rowNum = 1;
        int row_index = 1;
        for (Employee employee : employees) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(row_index).setCellValue(employee.getName());
            row.createCell(row_index + 1).setCellValue(employee.getEmail());

            Cell dateOfBirthCell = row.createCell(row_index + 2);
            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
            dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(row_index + 3).setCellValue(employee.getSalary());
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
}
