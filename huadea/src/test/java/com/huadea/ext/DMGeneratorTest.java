package com.huadea.ext;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Types;
import java.util.Collections;

/**
 * 达梦 代码生成
 *
 * @author mike
 * @since 3.5.3
 */
@SpringBootTest
//@ActiveProfiles("dm")
public class DMGeneratorTest {
    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:dm://192.168.100.40:5236/HUADEAEXP?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8", "SYSDBA", "Hda@2024...")
            .schema("SYSDBA");

    @Test
    public void testDMDb() {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("mike") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("F://java_pro//huadea1//huadea011015//huadea//src//test"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("com.huadea.ext.web") // 设置父包名
                                //.moduleName("web") // 设置父包模块名
                                .controller("controller")
                                .mapper("mapper")
                                .entity("domain")
                                .service("service")
                                .serviceImpl("service.impl")
                                .pathInfo(Collections.singletonMap(OutputFile.xml, "F://java_pro//huadea1//huadea011015//huadea//src//test//resource")) // 设置mapperXml生成路径
                )
                .strategyConfig(builder ->
                        builder.addInclude("disk_file","disk_floder") // 设置需要生成的表名
                                .addTablePrefix("t_", "c_") // 设置过滤表前缀
                )
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}