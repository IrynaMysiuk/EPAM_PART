package com.epam.lab.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.utils.Constants.SHIFT;
import static com.epam.lab.utils.PropertyReader.usersGmailPath;

public class XLSReader {
    private PropertyReader propertyReader;
    private int cellCount;
    List<ModelGmailUsers> usersData = null;

    public XLSReader() {
        propertyReader = new PropertyReader();
        propertyReader.readProperties();
    }

    public List<ModelGmailUsers> readXSLfile() {
        try (FileInputStream excelFile = new FileInputStream(new File(usersGmailPath))) {
            usersData = new ArrayList<>();
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = SHIFT; i < sheet.getPhysicalNumberOfRows() - SHIFT; i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow.getCell(0).getStringCellValue().isEmpty())
                    continue;
                ModelGmailUsers gmailData = new ModelGmailUsers();
                gmailData.setLogin(currentRow.getCell(0).getStringCellValue());
                gmailData.setPassword(currentRow.getCell(1).getStringCellValue());
                gmailData.setSendTo(currentRow.getCell(2).getStringCellValue());
                gmailData.setSubject(currentRow.getCell(3).getStringCellValue());
                gmailData.setIncorrectEmail(currentRow.getCell(4).getStringCellValue());
                usersData.add(gmailData);
            }
            cellCount = usersData.size();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return usersData;
    }
}
