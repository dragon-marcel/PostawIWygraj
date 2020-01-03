package com.example.PostawIWygraj.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PostawIWygraj.model.Order;

@Service
public class ExcelGenerator {
    @Autowired
    public OrderService orderService;

    public ByteArrayInputStream customersToExcel(String start, String end) throws IOException {

	List<Order> orders = orderService.findAll();
	String[] COLUMNs = { "LP", "UŻYTKOWNIK", "NUMER ZAMÓWIENIA", "ARTYKUŁ", "ILOŚĆ", "DATA", "DOSTAWCA",
		"CENA ZAKUPU", "WARTOŚĆ ZAKUPU", "KLIENT", "CENA SPRZEDAŻY", "WARTOŚĆ", "ZYSK" };
	try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	    CreationHelper createHelper = workbook.getCreationHelper();

	    XSSFSheet sheet = workbook.createSheet("Customers");

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.BLACK.getIndex());
	    XSSFCellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);
	    headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
	    headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
	    headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
	    headerCellStyle.setBorderRight(BorderStyle.MEDIUM);

	    XSSFRow titleRow = sheet.createRow(2);

	    XSSFCell cell = titleRow.createCell(0);
	    CellRangeAddress zakresKomorekZlecenieProdukcyjne = new CellRangeAddress(cell.getRowIndex(),
		    cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex() + 12);
	    sheet.addMergedRegion(zakresKomorekZlecenieProdukcyjne);
	    cell.setCellValue("RAPORT ZAMÓWIEŃ Z DNIA OD: " + start + " DO: " + end);
	    cell.setCellStyle(headerCellStyle);

	    // Row for Header
	    XSSFRow headerRow = sheet.createRow(3);

	    // Header
	    for (int col = 0; col < COLUMNs.length; col++) {
		XSSFCell cells = headerRow.createCell(col);
		cells.setCellValue(COLUMNs[col]);
		cells.setCellStyle(headerCellStyle);
	    }
	    for (int x = 0; x < sheet.getRow(3).getPhysicalNumberOfCells() + 1; x++) {
		sheet.setColumnWidth(x, 5000);
	    }

	    // CellStyle for Age
	    CellStyle ageCellStyle = workbook.createCellStyle();
	    ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

	    int rowIdx = 4;
	    int lp = 1;

	    XSSFCellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setBorderBottom(BorderStyle.THIN);
	    cellStyle.setBorderTop(BorderStyle.THIN);
	    cellStyle.setBorderLeft(BorderStyle.THIN);
	    cellStyle.setBorderRight(BorderStyle.THIN);

	    for (Order order : orders) {
		XSSFRow row = sheet.createRow(rowIdx++);
		XSSFCell cellLp = row.createCell(0);
		cellLp.setCellValue(lp);
		cellLp.setCellStyle(cellStyle);

		XSSFCell cellUser = row.createCell(1);
		cellUser.setCellValue(order.getUser().getUsername());
		cellUser.setCellStyle(cellStyle);

		XSSFCell cellNumber = row.createCell(2);
		cellNumber.setCellValue(order.getNumber());
		cellNumber.setCellStyle(cellStyle);

		XSSFCell cellArtykul = row.createCell(3);
		cellArtykul.setCellValue("Artykul");
		cellArtykul.setCellStyle(cellStyle);

		XSSFCell cellQuantityPurchase = row.createCell(4);
		cellQuantityPurchase.setCellValue(order.getQuantityPurchase().toString());
		cellQuantityPurchase.setCellStyle(cellStyle);

		XSSFCell cellDate = row.createCell(5);
		cellDate.setCellValue(order.getCreateDate());
		cellDate.setCellStyle(cellStyle);

		XSSFCell cellProvider = row.createCell(6);
		cellProvider.setCellValue(order.getProvider().getName());
		cellProvider.setCellStyle(cellStyle);

		XSSFCell cellPricePurchase = row.createCell(7);
		cellPricePurchase
			.setCellValue(order.getPricePurchase() != null ? order.getPricePurchase().toString() : "0");
		cellPricePurchase.setCellStyle(cellStyle);

		XSSFCell cellValuePurchase = row.createCell(8);
		cellValuePurchase.setCellValue(order.getValuePurchase().toString());
		cellValuePurchase.setCellStyle(cellStyle);

		XSSFCell cellCustomer = row.createCell(9);
		cellCustomer.setCellValue(order.getCustomer().getName());
		cellCustomer.setCellStyle(cellStyle);

		XSSFCell cellPriceSell = row.createCell(10);
		cellPriceSell.setCellValue(order.getPriceSell().toString());
		cellPriceSell.setCellStyle(cellStyle);

		XSSFCell cellValueSell = row.createCell(11);
		cellValueSell.setCellValue(order.getValueSell().toString());
		cellValueSell.setCellStyle(cellStyle);

		XSSFCell cellProfit = row.createCell(12);
		cellProfit.setCellValue(order.getProfit().toString());
		cellProfit.setCellStyle(cellStyle);

		lp++;
	    }

	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }

}