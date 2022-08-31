package com.hyf.cloud.mapper;

import com.hyf.cloud.domain.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heyf
 * @since 2022-08-08
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
