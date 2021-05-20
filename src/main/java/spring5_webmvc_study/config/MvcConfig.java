package spring5_webmvc_study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // 스프링 MVC설정 활성화
public class MvcConfig implements WebMvcConfigurer {
	
	/** DispatcherServlet의 매핑경로를 '/'주었을 때, JSP/HTML/CSS 등을 올바르게 처리하기 위한 설정 추가 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/** JSP를 통해서 컨트롤러의 실행 결과를 보여주기 위한 설정 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp"); //경로 미리설정해주는거/ 이 경로에 view 만들어야함.
	}
	
	
	@Override 
	public void addViewControllers(ViewControllerRegistry registry) { 
	registry.addViewController("/main").setViewName("main"); //메인부르면 컨트롤러 거치지 말고 바로 메인 호출시켜준다!! main.jsp는 register안 말고, view안에 바로 만들기
	}
}