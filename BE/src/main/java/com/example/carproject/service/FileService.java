package com.example.carproject.service;

import com.example.carproject.entity.Car;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FileService {

    public List<Car> parseCsvFile(MultipartFile file) throws IOException {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    try {
                        Car car = new Car();
                        car.setMake(parts[0].trim());
                        car.setModel(parts[1].trim());
                        car.setYear(Integer.parseInt(parts[2].trim()));
                        cars.add(car);
                    } catch (NumberFormatException e) {
                        // Skip invalid rows
                        continue;
                    }
                }
            }
        }
        return cars;
    }

    public List<Car> parseExcelFile(MultipartFile file) throws IOException {
        List<Car> cars = new ArrayList<>();
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            boolean isFirstRow = true;

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (isFirstRow) {
                    isFirstRow = false; // Skip header
                    continue;
                }

                try {
                    Car car = new Car();
                    car.setMake(getCellValueAsString(row.getCell(0)));
                    car.setModel(getCellValueAsString(row.getCell(1)));
                    car.setYear((int) row.getCell(2).getNumericCellValue());
                    cars.add(car);
                } catch (Exception e) {
                    // Skip invalid rows
                    continue;
                }
            }
        }
        return cars;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }

    public ByteArrayInputStream generateCsvTemplate() throws IOException {
        StringBuilder csv = new StringBuilder();
        csv.append("Make,Model,Year\n");
        csv.append("Toyota,Camry,2023\n");
        csv.append("Honda,Civic,2022\n");
        csv.append("Ford,Mustang,2021\n");

        return new ByteArrayInputStream(csv.toString().getBytes());
    }

    public ByteArrayInputStream generateExcelTemplate() throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Car Template");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Make");
            headerRow.createCell(1).setCellValue("Model");
            headerRow.createCell(2).setCellValue("Year");

            // Create sample data rows
            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("Toyota");
            row1.createCell(1).setCellValue("Camry");
            row1.createCell(2).setCellValue(2023);

            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("Honda");
            row2.createCell(1).setCellValue("Civic");
            row2.createCell(2).setCellValue(2022);

            Row row3 = sheet.createRow(3);
            row3.createCell(0).setCellValue("Ford");
            row3.createCell(1).setCellValue("Mustang");
            row3.createCell(2).setCellValue(2021);

            // Auto-size columns
            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    public ByteArrayInputStream exportCarsToCsv(List<Car> cars) throws IOException {
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Make,Model,Year\n");

        for (Car car : cars) {
            csv.append(car.getId()).append(",")
               .append(escapeCsvValue(car.getMake())).append(",")
               .append(escapeCsvValue(car.getModel())).append(",")
               .append(car.getYear()).append("\n");
        }

        return new ByteArrayInputStream(csv.toString().getBytes());
    }

    public ByteArrayInputStream exportCarsToExcel(List<Car> cars) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Cars");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Make");
            headerRow.createCell(2).setCellValue("Model");
            headerRow.createCell(3).setCellValue("Year");

            // Create data rows
            int rowNum = 1;
            for (Car car : cars) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(car.getId());
                row.createCell(1).setCellValue(car.getMake());
                row.createCell(2).setCellValue(car.getModel());
                row.createCell(3).setCellValue(car.getYear());
            }

            // Auto-size columns
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    private String escapeCsvValue(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}