package jp.co.canon.boilerplate.spring_boilerplate.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();

        // MustacheView 재설정
        resolver.setCharset("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8");
        resolver.setPrefix("classpath:/templates/"); // classpath: 프로젝트 경로
        resolver.setSuffix(".html"); // .html 파일을 만들어도 mustache가 인식하게 됨

        registry.viewResolver(resolver); // 등록
    }
}
