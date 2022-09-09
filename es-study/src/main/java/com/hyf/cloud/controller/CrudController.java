package com.hyf.cloud.controller;


import com.hyf.cloud.bean.Blog;
import com.hyf.cloud.dao.BlogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "增删改查（文档）")
@RestController
@RequestMapping("crud")
public class CrudController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @ApiOperation("添加单个文档")
    @PostMapping("addDocument")
    public Blog addDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是添加单个文档的实例" + id);
        blog.setAuthor("Tony");
        blog.setCategory("ElasticSearch");
        blog.setCreateTime(LocalDateTime.now());
        blog.setStatus(1);
        blog.setSerialNum(id.toString());

        return blogRepository.save(blog);
    }

    @ApiOperation("添加多个文档")
    @PostMapping("addDocuments")
    public Object addDocuments(Integer count) {
        List<Blog> blogs = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Long id = (long)i;
            Blog blog = new Blog();
            blog.setBlogId(id);
            blog.setTitle("Spring Data ElasticSearch学习教程" + id);
            blog.setContent("这是添加单个文档的实例" + id);
            blog.setAuthor("Tony");
            blog.setCategory("ElasticSearch");
            blog.setCreateTime(LocalDateTime.now());
            blog.setStatus(1);
            blog.setSerialNum(id.toString());
            blogs.add(blog);
        }

        return blogRepository.saveAll(blogs);
    }

    /**
     * 跟新增是同一个方法。若id已存在，则修改。
     * 无法只修改某个字段，只能覆盖所有字段。若某个字段没有值，则会写入null。
     * @return 成功写入的数据
     */
    @ApiOperation("修改单个文档")
    @PostMapping("editDocument")
    public Blog editDocument() {
        Long id = 1L;
        Blog blog = new Blog();
        blog.setBlogId(id);
        blog.setTitle("Spring Data ElasticSearch学习教程" + id);
        blog.setContent("这是修改单个文档的实例" + id);
        // blog.setAuthor("Tony");
        // blog.setCategory("ElasticSearch");
        // blog.setCreateTime(new Date());
        // blog.setStatus(1);
        // blog.setSerialNum(id.toString());

        return blogRepository.save(blog);
    }

    @ApiOperation("查找单个文档")
    @GetMapping("findById")
    public Blog findById(Long id) {
        return blogRepository.findById(id).get();
    }

    @ApiOperation("删除单个文档")
    @PostMapping("deleteDocument")
    public String deleteDocument(Long id) {
        blogRepository.deleteById(id);
        return "success";
    }

    @ApiOperation("删除所有文档")
    @PostMapping("deleteDocumentAll")
    public String deleteDocumentAll() {
        blogRepository.deleteAll();
        return "success";
    }

    @ApiOperation("查询所有文档")
    @GetMapping("/findAll")
    public List<Blog> findAll(){
        NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).build();
        SearchHits<Blog> search = elasticsearchRestTemplate.search(build, Blog.class);
        List<Blog> list = new ArrayList<>();
        for (SearchHit<Blog> blogSearchHit : search) {
            Blog content = blogSearchHit.getContent();
            list.add(content);
        }
        return list;
    }
}
