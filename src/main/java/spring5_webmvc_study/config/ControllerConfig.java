package spring5_webmvc_study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring5_webmvc_study.controller.MemberRegisterService;
import spring5_webmvc_study.survey.SurveyController;

@Configuration
@ComponentScan(basePackages = {"spring5_webmvc_study.controller","spring5_webmvc_study.survey" })
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
