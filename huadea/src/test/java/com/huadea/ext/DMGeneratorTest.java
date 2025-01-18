package com.huadea.ext;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder.globalConfig;

/**
 * 达梦 代码生成
 *
 * @author mike
 * @since 3.5.3
 */
@SpringBootTest
@Slf4j
public class DMGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:dm://192.168.100.40:5236/HUADEAEXP?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf-8", "SYSDBA", "Hda@2024...")
            .schema("SYSDBA")
            .build();

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);

        generator.global(
            globalConfig(builder -> {
                 builder.author("mike") // 设置作者
                .enableSwagger() // 开启 swagger 模式
                .outputDir("F://java_pro//huadea1//huadea011015//huadea//src//test//java"); // 指定输出目录
            })
        );

        generator.packageInfo(

        );

        generator.execute();

    }
}