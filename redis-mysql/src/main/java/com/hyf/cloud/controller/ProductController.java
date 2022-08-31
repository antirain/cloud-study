package com.hyf.cloud.controller;

import com.hyf.cloud.domain.Product;
import com.hyf.cloud.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author heyf
 * @since 2022-08-08
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("/getAll")
    public List<Product> getAll(){
        List<Product> products = productMapper.selectList(null);
        return products;
    }

}
