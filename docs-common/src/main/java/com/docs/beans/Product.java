package com.docs.beans;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

import java.util.List;

public class Product {
    public static final String SHEET_ID = "products";
    public static final int VALID_ROW_SIZE = 8;

    private String id;
    private String name;
    private String description;
    private Double price;
    private Integer number;
    private String classification;
    private String type;
    private String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean loadFromRow(List<Object> rowValues) {
        if (rowValues == null || rowValues.size() != VALID_ROW_SIZE) {
            return false;
        }
        this.id = rowValues.get(0).toString();
        if (StringUtils.isBlank(this.id)) {
            return false;
        }
        this.name = rowValues.get(1).toString();
        this.description = rowValues.get(2).toString();
        this.price = Double.valueOf(rowValues.get(3).toString().replace(",", ""));
        this.number = Integer.valueOf(rowValues.get(4).toString().replace(",", ""));
        this.classification = rowValues.get(5).toString();
        this.type = rowValues.get(6).toString();
        this.imageUrl = rowValues.get(7).toString();
        return true;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, new StandardToStringStyle());
    }
}
