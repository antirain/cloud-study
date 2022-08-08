package com.hyf.cloud.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(indexName = "qy_name_index")
public class EsNameIndex {

    @Id
    @Field(name = "NAME_ID", type = FieldType.Text, store = true)
    private String nameId;

    @ApiModelProperty("主体身份代码")
    @Field(name = "PRIPID", type = FieldType.Text, store = true)
    private String pripid;

    @ApiModelProperty("企业（机构）名称")
    @Field(name = "ENTNAME", type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String entname;

    @ApiModelProperty("名称区划")
    @TableField("NAMEDISTRICT")
    @Field(name = "NAMEDISTRICT", type = FieldType.Text, store = true)
    private String namedistrict;

    @ApiModelProperty("企业（机构）字号")
    @TableField("ENTTRA")
    @Field(name = "ENTTRA", type = FieldType.Text, store = true, analyzer = "ik_smart")
    private String enttra;

    @ApiModelProperty("名称行业")
    @TableField("NAMEIND")
    @Field(name = "NAMEIND", type = FieldType.Text, store = true)
    private String nameind;

    @ApiModelProperty("组织形式")
    @TableField("ORGFORM")
    @Field(name = "ORGFORM", type = FieldType.Text, store = true)
    private String orgform;

    @ApiModelProperty("字号拼音")
    @TableField("TRADPINY")
    @Field(name = "TRADPINY", type = FieldType.Text, store = true)
    private String tradpiny;

    @ApiModelProperty("集团简称")
    @TableField("GRPSHFORM")
    @Field(name = "GRPSHFORM", type = FieldType.Text, store = true)
    private String grpshform;

    @ApiModelProperty("保留期至")
    @TableField("SAVEDPERTO")
    @Field(name = "SAVEDPERTO", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime savedperto;

    @ApiModelProperty("核准文号")
    @TableField("APPRNO")
    @Field(name = "APPRNO", type = FieldType.Text, store = true)
    private String apprno;

    @ApiModelProperty("核准机关CA11")
    @TableField("APPRAUTH")
    @Field(name = "APPRAUTH", type = FieldType.Text, store = true)
    private String apprauth;

    @ApiModelProperty("规定登记机关CA11")
    @TableField("PREREGORG")
    @Field(name = "PREREGORG", type = FieldType.Text, store = true)
    private String preregorg;

    @ApiModelProperty("核准日期")
    @TableField("APPRDATE")
    @Field(name = "APPRDATE", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime apprdate;

    @ApiModelProperty("插入时间")
    @TableField("I_TIME")
    @Field(name = "I_TIME", type = FieldType.Date, format = DateFormat.date_hour_minute_second, store = true)
    private LocalDateTime iTime;

    @ApiModelProperty("更新时间")
    @TableField("U_TIME")
    @Field(name = "U_TIME", type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    private LocalDateTime uTime;

    @ApiModelProperty("插入用户")
    @TableField("I_USER")
    @Field(name = "I_USER", type = FieldType.Text, store = false)
    private String iUser;

    @ApiModelProperty("更新用户")
    @TableField("U_USER")
    @Field(name = "U_USER", type = FieldType.Text, store = false)
    private String uUser;

    @ApiModelProperty("插入人部门编码")
    @TableField("I_DEPART_ID")
    @Field(name = "I_DEPART_ID", type = FieldType.Text, store = false)
    private String iDepartId;

    @ApiModelProperty("更新人部门编码")
    @TableField("U_DEPART_ID")
    @Field(name = "U_DEPART_ID", type = FieldType.Text, store = false)
    private String uDepartId;

    @ApiModelProperty("专属编号")
    @TableField("ZXID")
    @Field(name = "ZXID", type = FieldType.Text, store = true)
    private String zxid;

    @ApiModelProperty("名称业务类型  1冠省名 2冠市名 3本级名称 4远程核准")
    @TableField("MCYWLX")
    @Field(name = "MCYWLX", type = FieldType.Text, store = true)
    private String mcywlx;

    @ApiModelProperty("是否加省 0否 1是")
    @TableField("IS_ADDPROVINCE")
    @Field(name = "IS_ADDPROVINCE", type = FieldType.Text, store = true)
    private String isAddprovince;

    @ApiModelProperty("是否加市 0否 1是")
    @TableField("IS_ADDCITY")
    @Field(name = "IS_ADDCITY", type = FieldType.Text, store = true)
    private String isAddcity;

    @ApiModelProperty("是否加县(区) 0否 1是")
    @TableField("IS_ADDCOUNTY")
    @Field(name = "IS_ADDCOUNTY", type = FieldType.Text, store = true)
    private String isAddcounty;

    @ApiModelProperty("是否加括号 0否 1是")
    @TableField("IS_ADDBRACKETS")
    @Field(name = "IS_ADDBRACKETS", type = FieldType.Text, store = true)
    private String isAddbrackets;

    @ApiModelProperty("0:旧系统搬迁数据 1:新系统插入数据")
    @TableField("XJFLAG")
    @Field(name = "XJFLAG", type = FieldType.Text, store = false)
    private String xjflag;

    @ApiModelProperty("集团名称")
    @TableField("GRPNAME")
    @Field(name = "GRPNAME", type = FieldType.Text, store = true)
    private String grpname;

    @ApiModelProperty("行业门类(CA05)")
    @TableField("INDUSTRYPHY")
    @Field(name = "INDUSTRYPHY", type = FieldType.Text, store = true)
    private String industryphy;

    @ApiModelProperty("行业代码(CA06)")
    @TableField("INDUSTRYCO")
    @Field(name = "INDUSTRYCO", type = FieldType.Text, store = true)
    private String industryco;

    @ApiModelProperty("是否无行业表述 0否 1是")
    @TableField("IS_HYBS")
    @Field(name = "IS_HYBS", type = FieldType.Text, store = true)
    private String isHybs;

    @ApiModelProperty("名称状态 1：预核准通过 2：设立登记通过 3：变更前  4：注销 5：吊销 6：迁移至异地 8：名称驳回 9：其他")
    @TableField("STATUS")
    @Field(name = "STATUS", type = FieldType.Text, store = true)
    private String status;

}
