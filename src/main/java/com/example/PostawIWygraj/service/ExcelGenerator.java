package com.example.PostawIWygraj.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PostawIWygraj.model.Order;
import com.example.PostawIWygraj.model.User;

@Service
public class ExcelGenerator {

    @Autowired
    public OrderService orderService;
    @Autowired
    private UserService userService;

    private XSSFCellStyle headerCellStyle;
    private XSSFCellStyle cellStyle;
    private XSSFCellStyle titleCellStyle;
    private XSSFWorkbook workbook;

    public ByteArrayInputStream customersToExcel(String start, String end, Long id) throws IOException {

	List<Order> orders = orderService.getOrdersRaport(start, end, id);
	if (orders.size() > 0) {
	    String name = "";
	    if (id != null && !id.equals(0)) {
		try {
		    name = ((User) userService.findById(id)).getUsername();
		} catch (Exception e) {
		    System.out.print("Blad");
		}
	    }
	    

	    String[] COLUMNs = { "LP", "UŻYTKOWNIK", "NR. ZAMÓWIENIA", "ARTYKUŁ", "ILOŚĆ", "DATA", "DOSTAWCA", "KLIENT",
		    "CENA ZAKUPU", "CENA SPRZEDAŻY", "WARTOŚĆ ZAKUPU", "WARTOŚĆ SPRZEDAŻY", "ZYSK" };
	    workbook = new XSSFWorkbook();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    XSSFSheet sheet = workbook.createSheet("Customers");
	    
	    
	    XSSFCellStyle titleStyle = workbook.createCellStyle();
	    titleStyle.cloneStyleFrom(getTitleCellStyle(workbook));
	    
	    XSSFCellStyle headerStyle = workbook.createCellStyle();
	    headerStyle.cloneStyleFrom(getHeaderCellStyle(workbook));
	    
	    XSSFCellStyle style = workbook.createCellStyle();
	    style.cloneStyleFrom(getCellStyle(workbook));

	    XSSFRow titleRow = sheet.createRow(2);
	    titleRow.setHeightInPoints(20);
	    XSSFCell cell = titleRow.createCell(0);
	    CellRangeAddress zakresKomorekZlecenieProdukcyjne = new CellRangeAddress(cell.getRowIndex(),
		    cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex() + 12);
	    sheet.addMergedRegion(zakresKomorekZlecenieProdukcyjne);
	    StringBuilder sbTitle = new StringBuilder();
	    sbTitle.append("RAPORT ZAMÓWIEŃ Z DNIA OD: ").append(start).append(" DO: ").append(end);
	    if (name != null && !name.equals("")) {
		sbTitle.append(" UŻYTKOWNIKA O LOGINIE: ").append(name.toUpperCase());
	    }
	    cell.setCellValue(sbTitle.toString());
	    cell.setCellStyle(titleStyle);
	    // Row for Header
	    XSSFRow headerRow = sheet.createRow(3);

	    // Header
	    for (int col = 0; col < COLUMNs.length; col++) {
		XSSFCell cells = headerRow.createCell(col);
		cells.setCellValue(COLUMNs[col]);
		cells.setCellStyle(headerStyle);
	    }
	    for (int x = 0; x < sheet.getRow(3).getPhysicalNumberOfCells() + 1; x++) {
		sheet.setColumnWidth(x, x == 0 ? 1000 : 5000);
	    }

	    int rowIdx = 4;
	    int lp = 1;

	    List<HashMap<String, XSSFCell>> kom = new ArrayList<>();
	    for (Order order : orders) {

		HashMap<String, XSSFCell> hm = new HashMap<>();
		XSSFRow row = sheet.createRow(rowIdx++);
		XSSFCell cellLp = row.createCell(0);
		cellLp.setCellValue(lp);
		cellLp.setCellStyle(style);

		XSSFCell cellUser = row.createCell(1);
		cellUser.setCellValue(order.getUser().getUsername());
		cellUser.setCellStyle(style);

		XSSFCell cellNumber = row.createCell(2);
		cellNumber.setCellValue(order.getNumber());
		cellNumber.setCellStyle(style);

		XSSFCell cellArtykul = row.createCell(3);
		cellArtykul.setCellValue("Artykul");
		cellArtykul.setCellStyle(style);

		XSSFCell cellQuantityPurchase = row.createCell(4);
		cellQuantityPurchase.setCellValue(Double.valueOf(order.getQuantityPurchase().toString()));
		cellQuantityPurchase.setCellStyle(style);
		cellQuantityPurchase.setCellType(CellType.NUMERIC);

		XSSFCell cellDate = row.createCell(5);
		cellDate.setCellValue(order.getCreateDate().toString());
		cellDate.setCellStyle(style);

		XSSFCell cellProvider = row.createCell(6);
		cellProvider.setCellValue(order.getProvider().getName());
		cellProvider.setCellStyle(style);

		XSSFCell cellCustomer = row.createCell(7);
		cellCustomer.setCellValue(order.getCustomer().getName());
		cellCustomer.setCellStyle(style);

		XSSFCell cellPricePurchase = row.createCell(8);
		cellPricePurchase.setCellValue(
			order.getPricePurchase() != null ? Double.valueOf(order.getPricePurchase().toString())
				: Double.valueOf("0"));
		cellPricePurchase.setCellStyle(style);
		cellPricePurchase.setCellType(CellType.NUMERIC);

		XSSFCell cellPriceSell = row.createCell(9);
		cellPriceSell.setCellValue(Double.valueOf(order.getPriceSell().toString()));
		cellPriceSell.setCellStyle(style);
		cellPriceSell.setCellType(CellType.NUMERIC);

		XSSFCell cellValuePurchase = row.createCell(10);
		cellValuePurchase.setCellValue(Double.valueOf(order.getValuePurchase().toString()));
		cellValuePurchase.setCellStyle(style);
		cellValuePurchase.setCellType(CellType.NUMERIC);
		hm.put("PURCHASE", cellValuePurchase);

		XSSFCell cellValueSell = row.createCell(11);
		cellValueSell.setCellValue(Double.valueOf(order.getValueSell().toString()));
		cellValueSell.setCellStyle(style);
		cellValueSell.setCellType(CellType.NUMERIC);

		hm.put("SELL", cellValueSell);

		XSSFCell cellProfit = row.createCell(12);
		cellProfit.setCellType(CellType.NUMERIC);
		cellProfit.setCellValue(Double.valueOf(order.getProfit().toString()));
		cellProfit.setCellStyle(style);
		hm.put("PROFIT", cellProfit);
		kom.add(hm);

		lp++;
	    }
	    // Podsumowanie
	    if (!kom.isEmpty()) {
		XSSFCell firstCellProfit = kom.get(0).get("PROFIT");
		XSSFCell lastCellProfit = kom.get(kom.size() - 1).get("PROFIT");

		XSSFCell firstCellSell = kom.get(0).get("SELL");
		XSSFCell lastCellSell = kom.get(kom.size() - 1).get("SELL");

		XSSFCell firstCellPurchase = kom.get(0).get("PURCHASE");

		XSSFCell lastCellPurchase = kom.get(kom.size() - 1).get("PURCHASE");
		XSSFRow rowSumary = sheet.createRow(rowIdx++);

		XSSFCell cellSumaryPurchase = rowSumary.createCell(10);
		cellSumaryPurchase.setCellFormula("SUM(" + getKomRefStr(firstCellPurchase, false, true) + ":"
			+ getKomRefStr(lastCellPurchase, false, true) + ")");
		cellSumaryPurchase.setCellStyle(style);

		XSSFCell cellSumarySell = rowSumary.createCell(11);
		cellSumarySell.setCellFormula("SUM(" + getKomRefStr(firstCellSell, false, true) + ":"
			+ getKomRefStr(lastCellSell, false, true) + ")");
		cellSumarySell.setCellStyle(style);

		XSSFCell cellSumaryProfit = rowSumary.createCell(12);
		cellSumaryProfit.setCellFormula("SUM(" + getKomRefStr(firstCellProfit, false, true) + ":"
			+ getKomRefStr(lastCellProfit, false, true) + ")");
		cellSumaryProfit.setCellStyle(style);

	    }
	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());

	} else {
	    return null;
	}

    }

    private String getKomRefStr(XSSFCell passedKomorka, boolean bezwzglednyWiersz, boolean bezwzglednieKolumna) {
	String adresKmomorki = null;
	if (passedKomorka != null) {
	    CellReference refrencjaKomorki = new CellReference(passedKomorka.getRowIndex(),
		    passedKomorka.getColumnIndex(), bezwzglednyWiersz, bezwzglednieKolumna);
	    adresKmomorki = refrencjaKomorki.formatAsString();
	    // Tymczasowy.GetLogger().info("adresKmomorki: " + adresKmomorki);
	}
	return adresKmomorki;
    }

    private XSSFCellStyle getHeaderCellStyle(XSSFWorkbook workBook) {
	if (headerCellStyle == null) {

	    headerCellStyle = workBook.createCellStyle();

	    Font headerFont = workBook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.BLACK.getIndex());

	    headerCellStyle.setFont(headerFont);
	    headerCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	    headerCellStyle.setBorderBottom(BorderStyle.THIN);
	    headerCellStyle.setBorderTop(BorderStyle.THIN);
	    headerCellStyle.setBorderLeft(BorderStyle.THIN);
	    headerCellStyle.setBorderRight(BorderStyle.THIN);

	}
	return headerCellStyle;
    }

    private XSSFCellStyle getTitleCellStyle(XSSFWorkbook workBook) {
	if (titleCellStyle == null) {
	    titleCellStyle = workBook.createCellStyle();

	    Font headerFont = workBook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.WHITE.getIndex());
	    headerFont.setFontHeightInPoints((short) 15);

	    titleCellStyle.setFont(headerFont);
	    titleCellStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
	    titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	    titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
	    titleCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

	}
	return titleCellStyle;
    }

    private XSSFCellStyle getCellStyle(XSSFWorkbook workBook) {
	if (cellStyle == null) {

	    cellStyle = workBook.createCellStyle();
	    cellStyle.setBorderBottom(BorderStyle.THIN);
	    cellStyle.setBorderTop(BorderStyle.THIN);
	    cellStyle.setBorderLeft(BorderStyle.THIN);
	    cellStyle.setBorderRight(BorderStyle.THIN);
	}
	return cellStyle;
    }
}