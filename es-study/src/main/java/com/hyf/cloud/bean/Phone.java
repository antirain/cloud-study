package com.hyf.cloud.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@ToString
@Document(indexName = "shopping")
public class Phone {

        @Id
        @Field(type = FieldType.Keyword)
        private String id;
        @Field(type = FieldType.Text)
        private String category;
        @Field(type = FieldType.Text)
        private String title;
        @Field(type = FieldType.Float)
        private Float price;
        @Field(type = FieldType.Text)
        private String images;


}
