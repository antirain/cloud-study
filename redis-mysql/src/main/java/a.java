import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class a {

    private static final String pathPrefix = "D:\\cloud-study\\redis-mysql\\src\\main\\";
    private static final String domainPacageName = "domain";
    private static final String mapperPacageName = "mapper";
    private static final String xmlPacageName = "mapper";
    private static final String packageParent = "com.hyf.cloud";

    public static void main(String[] args) {
        DataSourceConfig.Builder dataSourceBuilder = new DataSourceConfig.Builder("jdbc:mysql://124.222.193.26:3306/oauth?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "123456")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert());

        FastAutoGenerator.create(dataSourceBuilder)
                .globalConfig(builder -> {
                    builder.author("heyf") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(pathPrefix + "java"); // 指定输出目录
                })
                .packageConfig((builder) -> builder.parent(packageParent)
                        .entity(domainPacageName)
                        .mapper(mapperPacageName)
                        .xml(xmlPacageName)
                        .pathInfo(Collections.singletonMap(OutputFile.xml, pathPrefix + "resources\\" + xmlPacageName)))
                .strategyConfig(builder -> {
                    builder.addInclude("product") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
