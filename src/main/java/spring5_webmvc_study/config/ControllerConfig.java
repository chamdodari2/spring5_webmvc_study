package spring5_webmvc_study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"spring5_webmvc_study.controller","spring5_webmvc_study.survey","spring5_webmvc_study.common" })
public class ControllerConfig {
	
//	@Autowired
//	private MemberRegisterService memverRegSvc;
	
//	
//	@Bean
//	public RegistController registerController() {
//		RegistController controller = new RegistController();
//		controller.setMemberRegisterService(memverRegSvc);
//		return controller;
//	}

	
//	@Bean
//	public SurveyController surveyController() {
//		return new SurveyController();
//	}
}
