package lectorArchivo.reader.services.ReaderFiles;

import lectorArchivo.reader.DTO.Respuesta;
import lectorArchivo.reader.DTO.ExcelModel;
import lectorArchivo.reader.services.Reader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReaderXlsxService extends Reader {
    @Override
    public Respuesta readerFile(String urlFile) throws IOException {
        FileInputStream fis = new FileInputStream(urlFile);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        CellValue cellValue = null;
        List<ExcelModel> records = new ArrayList<>();

        // Saltar la primera fila
        if (rows.hasNext()) {
            rows.next();
        }

        int rowIndex = 1;
        while (rows.hasNext()) {
            Row row = rows.next();
            ExcelModel record = new ExcelModel();

            try {
                for (int cellIndex = 0; cellIndex < 14; cellIndex++) {
                    try {
                        if (row.getCell(cellIndex).getCellType() == CellType.FORMULA) {
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            cellValue = evaluator.evaluate(row.getCell(cellIndex));
                        }
                        switch (cellIndex) {
                            case 0:
                                record.setDate((int) row.getCell(cellIndex).getNumericCellValue());
                                break;
                            case 1:
                                record.setInjuryLocation(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 2:
                                record.setGender(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 3:
                                record.setAgeGroup(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 4:
                                record.setIncidentType(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 5:
                                record.setDaysLost((int) row.getCell(cellIndex).getNumericCellValue());
                                break;
                            case 6:
                                record.setPlant(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 7:
                                record.setReportType(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 8:
                                record.setShift(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 9:
                                record.setDepartment(row.getCell(cellIndex).getStringCellValue());
                                break;
                            case 10:
                                record.setIncidentCost((int)row.getCell(cellIndex).getNumericCellValue());
                                break;
                            case 11:
                                assert cellValue != null;
                                record.setWkDay(cellValue.getStringValue());
                                break;
                            case 12:
                                assert cellValue != null;
                                record.setMonth((int) cellValue.getNumberValue());
                                break;
                            case 13:
                                assert cellValue != null;
                                record.setYear((int) cellValue.getNumberValue());
                                break;
                        }
                    } catch (IllegalStateException e) {
                        System.out.println("Error en la fila " + rowIndex + ", celda " + cellIndex + ": " + e.getMessage());
                    }
                }

                records.add(record);
            } catch (Exception e) {
                System.out.println("Error en la fila " + rowIndex + ": " + e.getMessage());
            }

            rowIndex++;

        }

        records.forEach((element) -> {
            boolean response = this.connection.webClient().post().uri("validateData/xlsx")
                    .bodyValue(element).retrieve()
                    .bodyToMono(boolean.class).block();
            if (!response) {
                this.invalidLines++;
            }else {
                this.validLines++;
            }
        });

        fis.close();
        Respuesta respuesta = new Respuesta(this.validLines, this.invalidLines);
        return respuesta;
    }
}
