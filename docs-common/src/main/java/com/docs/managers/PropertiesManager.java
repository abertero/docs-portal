package com.docs.managers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesManager.class);
    private Properties sheetsPropertyFile;

    private static Properties loadPropertyFile(String propertyFile) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream(propertyFile);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                LOGGER.warn(String.format("No se pudo encontrar el archivo: %s", propertyFile));
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(String.format("Problema encontrando el archivo de properties %s", propertyFile), e);
        } catch (IOException e) {
            LOGGER.error(String.format("Problema cargando le archivo de properties %s", propertyFile), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.warn("Problema cerrando el stream", e);
                }
            }
        }
        return properties;
    }

    public Properties getSheetsPropertyFile() {
        if (sheetsPropertyFile == null) {
            sheetsPropertyFile = loadPropertyFile("sheets.properties");
        }
        return sheetsPropertyFile;
    }

    public static void main(String[] args) {
        PropertiesManager propertiesManager = new PropertiesManager();
        Properties sheetsPropertyFile = propertiesManager.getSheetsPropertyFile();
        Enumeration<?> e = sheetsPropertyFile.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = sheetsPropertyFile.getProperty(key);
            System.out.println("Key : " + key + ", Value : " + value);
        }
    }
}
