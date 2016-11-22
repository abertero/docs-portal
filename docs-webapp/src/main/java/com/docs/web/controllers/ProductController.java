package com.docs.web.controllers;

import com.docs.beans.Product;
import com.docs.web.WebappUtils;
import com.docs.web.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView products(@RequestParam(required = false, defaultValue = "0") Integer pageIndex) {
        LOGGER.info("Se llama vista de productos con pageIndex: %d", pageIndex);
        ModelAndView mv = new ModelAndView("products");
        List<Product> products = productService.products(pageIndex, WebappUtils.DEFAULT_PAGE_SIZE);
        LOGGER.debug(String.format("Productos obtenidos desde sheet: %s", products));
        mv.addObject("products", products);
        mv.addObject("pageIndex", pageIndex);
        mv.addObject("hasNext", products.size() == WebappUtils.DEFAULT_PAGE_SIZE);
        return mv;
    }
}
