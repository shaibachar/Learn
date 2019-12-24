package com.example.ApachePoi;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
class ApachePoiApplicationTests {

    @Test
    public void parseExcel() throws InvalidFormatException, IOException {
        // Obtain a workbook from the excel file
        Workbook workbook = WorkbookFactory.create(new File("c:\\Users\\SHAYBA\\Downloads\\report3.xlsx"));

        Sheet sheet1 = workbook.getSheet("Sheet1");
        int lastRowNum = sheet1.getLastRowNum();
        for (int i = 0; i < lastRowNum; i++) {
            Row row = sheet1.getRow(i);
            if (row == null) {
                continue;
            }
            short lastCellNum = row.getLastCellNum();
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                String stringCellValue;
                switch (cell.getCellTypeEnum()) {
                    case STRING:
                        stringCellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        stringCellValue = ((Double) cell.getNumericCellValue()).toString();
                        break;
                    case BLANK:
                        stringCellValue = "";
                        break;
                    case _NONE:
                        stringCellValue = "";
                        break;
                    case BOOLEAN:
                        stringCellValue = cell.getBooleanCellValue() ? "true" : "false";
                        break;
                    case ERROR:
                        stringCellValue = "";
                        break;
                    case FORMULA:
                        stringCellValue = "";
                        break;
                    default:
                        stringCellValue = "";
                        break;
                }

                stringBuffer.append(stringCellValue.replaceAll(",", "")).append(",");
            }
            String line = stringBuffer.toString();
            if (line.contains("לקבוצה:") || line.contains("חובה") || line.contains("הפרש") || line.contains("מאזן בוחן") || line.contains("ת.אסמכתא") || line.contains("דומע") || line.contains("סה\"כ")) {
                continue;
            }

            if (line.trim().length() < 30) {
                System.out.println("section:" + line.trim().replaceAll(",", ""));
                //todo: add to global field
                continue;
            }
            String[] split = line.split(",");
            int length = split.length;
            ParseObject parseObject = new ParseObject();
            if (length == 7) {
                parseObject.setAccount(split[5]);
                parseObject.setAccountName(split[3]);
                parseObject.setCredit(split[2]);
                parseObject.setDebit(split[1]);
                parseObject.setSum(split[0]);
            }else if (length == 6) {
                parseObject.setAccount((split[4] == null || split[4].isEmpty()) ? split[5] : split[4]);
                parseObject.setAccountName(split[3]);
                parseObject.setCredit(split[2]);
                parseObject.setDebit(split[1]);
                parseObject.setSum(split[0]);
            } else if (length == 5 && split[4].contains("  ")) {
                parseObject.setAccount(split[4]);
                parseObject.setAccountName(split[3]);
                parseObject.setCredit(split[2]);
                parseObject.setDebit(split[1]);
                parseObject.setSum(split[0]);
            } else if (length == 5) {
                parseObject.setAccount(split[3] + " " + split[4]);
                parseObject.setAccountName(split[2]);
                parseObject.setCredit(split[1]);
                parseObject.setDebit(split[0]);
            }
            System.out.println(parseObject);
        }
        // Closing the workbook
        workbook.close();
    }

    class ParseObject {
        private String sort;
        private String account;
        private String accountName;
        private String credit;
        private String debit;
        private String sum;

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getDebit() {
            return debit;
        }

        public void setDebit(String debit) {
            this.debit = debit;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "ParseObject{" +
                    "sort='" + sort + '\'' +
                    ", account='" + account + '\'' +
                    ", accountName='" + accountName + '\'' +
                    ", credit='" + credit + '\'' +
                    ", debit='" + debit + '\'' +
                    ", sum='" + sum + '\'' +
                    '}';
        }
    }
}
