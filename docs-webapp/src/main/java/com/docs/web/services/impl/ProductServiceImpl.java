package com.docs.web.services.impl;

import com.docs.beans.Product;
import com.docs.managers.ProductManager;
import com.docs.web.WebappUtils;
import com.docs.web.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductManager productManager;

    @Override
    public List<Product> products() {
        return products(0, WebappUtils.DEFAULT_PAGE_SIZE);
    }

    @Override
    public List<Product> products(int pageIndex, int pageSize) {
        List<Product> products = productManager.getProducts(pageIndex, pageSize);
        LOGGER.debug(String.format("Productos obtenidos: %s", products));
        return products;
    }
}
