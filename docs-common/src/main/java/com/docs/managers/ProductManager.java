package com.docs.managers;

import com.docs.beans.Product;
import com.docs.utils.SheetsUtils;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductManager.class);

    private Sheets sheets;
    private Properties sheetsPropertyFile;

    public ProductManager(Sheets sheets, PropertiesManager propertiesManager) {
        this.sheets = sheets;
        this.sheetsPropertyFile = propertiesManager.getSheetsPropertyFile();
    }

    public List<Product> getProducts(int pageIndex, int pageSize) {
        List<Product> products = new ArrayList<>();
        String spreadsheetId = sheetsPropertyFile.get("product.sheet").toString();
        // rango inicial es 2
        int initialRange = pageIndex * pageSize + 2;
        int endRange = initialRange + pageSize - 1;
        String range = String.format("%s!A%d:H%d", Product.SHEET_ID, initialRange, endRange);
        ValueRange response = null;
        try {
            response = sheets.spreadsheets().values().get(spreadsheetId, range).execute();
        } catch (IOException e) {
            LOGGER.error(String.format("Problema obteniendo los productos para: {spreadsheetId: %s, range: %s}", spreadsheetId, range), e);
        }
        if (response != null) {
            List<List<Object>> cellValues = response.getValues();
            for (List<Object> rowValues : cellValues) {
                Product product = new Product();
                boolean isValidRow = product.loadFromRow(rowValues);
                if (isValidRow) {
                    products.add(product);
                }
            }
        }
        return products;
    }

    public static void main(String[] args) throws IOException {
        Sheets sheets = SheetsUtils.getSheetsService();
        PropertiesManager propertiesManager = new PropertiesManager();
        ProductManager productManager = new ProductManager(sheets, propertiesManager);
        List<Product> products = productManager.getProducts(0, 10);
        System.out.println(String.format("resultSize: %d", products.size()));
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
