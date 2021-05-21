package spring5_webmvc_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistController {
	
	@Autowired
	private MemberRegisterService memberRegisterService;
	
	@RequestMapping("/register/step1")
	public String handlStep1() { // step1을 부르면 매핑해주기

		return "/register/step1";
	}

	@PostMapping("/register/step2") // 요부분 안적어줘서 에러떴당
	public String handleStep2(@RequestParam(value = "agree", defaultValue = "false") Boolean agree, RegisterRequest registerRequest) {	// jsp파일에서  작성한value name이 agree 인 애 
		if (!agree) {// 동의안했으면 step1 화면 다시 호출 -> 매핑
			return "register/step1";
		}
		//model.addAttribute("registerRequest", new RegisterRequest()); // 동의체크했으면 step2화면 호출 -> 매핑

		return "register/step2";
	}

	@GetMapping("/register/step2")
	public String handleStep2Get() {
		return "redirect:/register/step1"; // 절대경로! 앞에부분 붙여주는거임
	}

	@PostMapping("/register/step3")
	public String handleStep3(RegisterRequest regReq, Errors errors) {//@ModelAttribute("formData") 이거 지워줬당
		//커맨드 객체 (즉, RegisterRequest 객체) 검증
		new RegisterRequestValidator().validate(regReq, errors);
		
		if(errors.hasErrors())
			return"register/step2";  //에러있으면 화면 이동시킨다음에,
		
		System.out.println(regReq);
		// return "";

		try {
			memberRegisterService.regist(regReq); 
			return "register/step3";
		} catch (DuplicateMemberException ex) {  //이메일 중복되면? 원래는 그냥 화면띄워줬는데 errors 메세지 띄워준다
			errors.reject("email","duplicate");
			return "register/step2";
		}
	}

	public void setMemberRegisterService(MemberRegisterService memverRegSvc) {
		// TODO Auto-generated method stub
		
	}

	

}
