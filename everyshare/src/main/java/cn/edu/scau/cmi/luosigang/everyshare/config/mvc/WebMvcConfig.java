package cn.edu.scau.cmi.luosigang.everyshare.config.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${spring.servlet.multipart.location}")
    private String path; //从application.yml配置文件中取出文件保存位置

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/picture/file/**")
                .addResourceLocations("file:"+path);//将访问图片文件的路径映射为磁盘上文件的保存位置
    }
}
