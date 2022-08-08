package com.hyf.cloud.dao;


import com.hyf.cloud.bean.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<Blog, Long> {

}
