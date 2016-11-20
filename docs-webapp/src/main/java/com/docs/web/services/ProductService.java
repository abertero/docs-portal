package com.docs.web.services;

import com.docs.beans.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<Product> products();

    List<Product> products(int pageIndex, int pageSize);
}
