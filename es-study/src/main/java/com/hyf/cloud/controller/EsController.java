package com.hyf.cloud.controller;

import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.hyf.cloud.bean.EsNameIndex;
import com.hyf.cloud.bean.Phone;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@Api(value = "es接口", tags = "手机查询相关的接口", description = "查询测试接口")
public class EsController {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;
    static final String ENTNAME = "ENTNAME";
    static final String ENTTRA = "ENTTRA";

    @GetMapping("/uris")
    public String uris(){
        log.debug("port:" + SpringUtil.getProperty("spring.elasticsearch.rest.uris"));
        return SpringUtil.getProperty("spring.elasticsearch.rest.uris");
    }
    @GetMapping("/dom")
    public List<Map<String,Object>> dom(){
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .withSourceFilter(new FetchSourceFilter(new String[]{"NAMEDISTRICT", "ENTNAME"}, new String[0]))
                .build();
        SearchHits<EsNameIndex> search = elasticsearchRestTemplate.search(build, EsNameIndex.class);
        List<Map<String,Object>> list = new ArrayList<>();
        for (SearchHit<EsNameIndex> hit : search) {
            EsNameIndex content = hit.getContent();
            list.add(JSONUtil.parseObj(content));
        }
        return list;
    }
    @GetMapping("/get2")
    public List<EsNameIndex> get() {
        log.info("开始查询卓讯ES");
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .filter(QueryBuilders.multiMatchQuery("卓讯", ENTNAME, ENTTRA).type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                        .must(QueryBuilders.termQuery("NAMEDISTRICT", "武汉")))
                .build();

        List<EsNameIndex> list = new ArrayList<>();
        log.info("查询语句：{}", build.getQuery());
        SearchHits<EsNameIndex> searchHits = elasticsearchRestTemplate.search(build, EsNameIndex.class);
        log.info(String.valueOf(searchHits.getTotalHits()));
        for (SearchHit<EsNameIndex> searchHit : searchHits) {
            EsNameIndex content = searchHit.getContent();
            list.add(content);
        }
        return list;
    }
    @GetMapping("/get3")
    public Page<EsNameIndex> getget(){
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.filter(QueryBuilders.multiMatchQuery("卓讯", ENTNAME, ENTTRA).type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .must(QueryBuilders.matchQuery("NAMEDISTRICT","武汉"));
        queryBuilder.withQuery(boolQuery);
        PageRequest pageRequest = PageRequest.of(0, 5);
        queryBuilder.withPageable(pageRequest);

        HighlightBuilder.Field nameField = new HighlightBuilder.Field(ENTNAME).preTags("<span style='color:red'>").postTags("</span>");
        HighlightBuilder.Field nameField2 = new HighlightBuilder.Field(ENTTRA).preTags("<span style='color:red'>").postTags("</span>");

        queryBuilder.withHighlightFields(nameField, nameField2);
        NativeSearchQuery build = queryBuilder.build();
        log.info("查询语句：{}", build.getQuery());
        SearchHits<EsNameIndex> searchHits = elasticsearchRestTemplate.search(build, EsNameIndex.class);

        Iterator<SearchHit<EsNameIndex>> iterator = searchHits.stream().iterator();
        List<EsNameIndex> list = new ArrayList<>();
        while (iterator.hasNext()) {
            /*每个查询命中对象*/
            SearchHit<EsNameIndex> searchHit = iterator.next();
            EsNameIndex content = searchHit.getContent();
            Map<String, List<String>> hightlightFields = searchHit.getHighlightFields();
            /*命中的属性*/
            List<String> nameList = hightlightFields.get("name");
            if (nameList != null && nameList.size() > 0) {
                for (String s : nameList) {
                    content.setEntname(s);
                }
            }
            list.add(content);
        }

        long totalHits = searchHits.getTotalHits();
        return new PageImpl<>(list, pageRequest, totalHits);
    }

    @GetMapping("/get")
    //方法参数说明，name参数名；value参数说明，备注；dataType参数类型；required 是否必传；defaultValue 默认值
    @ApiImplicitParam(name = "title", value = "高亮模糊查询")
    //说明是什么方法(可以理解为方法注释)
    @ApiOperation(value = "高亮模糊查询", notes = "高亮模糊查询")
    public List<Phone> get(String title) {
        log.info("get方法开始");
        List<Phone> list = new ArrayList<>();

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("title", title))
                .withHighlightFields(
                        new HighlightBuilder.Field("category")
                        , new HighlightBuilder.Field("title"))
                .withHighlightBuilder(new HighlightBuilder().preTags("<span style='color:red'>").postTags("</span>"))
                .build();
        SearchHits<Phone> search = elasticsearchRestTemplate.search(query, Phone.class);
        List<SearchHit<Phone>> searchHits = search.getSearchHits();
        for (SearchHit<Phone> e : searchHits) {
            Phone content = e.getContent();
            Map<String, List<String>> highlightFields = e.getHighlightFields();
            System.out.println(highlightFields);
            list.add(content);
        }
        return list;
    }

    @PostMapping("/save")
    @ApiImplicitParam(value = "新增数据", name = "phone")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    public Map<String, Object> save(@RequestBody Phone phone) {
        Map<String, Object> ret = new HashMap<>();
        Phone save = elasticsearchRestTemplate.save(phone);
        if (save != null) {
            ret.put("success", "true");
            ret.put("data", save);
        } else {
            ret.put("success", "false");
            ret.put("message", "新增失败");
        }
        return ret;
    }
}
