package com.hyf.cloud.service.impl;

import com.hyf.cloud.domain.Product;
import com.hyf.cloud.mapper.ProductMapper;
import com.hyf.cloud.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author heyf
 * @since 2022-08-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
