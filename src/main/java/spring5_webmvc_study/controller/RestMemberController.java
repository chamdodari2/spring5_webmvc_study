package spring5_webmvc_study.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMemberController {
	@Autowired
	private MemberDao memberDao;

	@Autowired
	private MemberRegisterService registerService;

	@GetMapping("/api/members")
	public List<Member> members() {
		return memberDao.selectAll();
	}

	@GetMapping("/api/members/{id}")
	public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse response) throws IOException {
		Member member = memberDao.selectById(id);
		if (member == null) {
			//response.sendError(HttpServletResponse.SC_NOT_FOUND);//방법0
			//	return null;//방법0
		 	//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member")); //방법1 다시살림
			//방법2
			//return ResponseEntity.notFound().build();
			//방법3
			throw new MemberNotFoundException();
		}
		//return member;//방법0
		return ResponseEntity.status(HttpStatus.OK).body(member);
	}
	
	// @RestControllerAdvice 애노테이션을 이용해서 응답하기
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoData(){
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}

	
	
	///json형식의 데이터를 자바형식으로 수정해서 (Post)

	
	
	
	@PostMapping("/api/members")
	public ResponseEntity<Object> newMember( @RequestBody RegisterRequest regReq, Errors errors, HttpServletResponse response) throws IOException {
		try {
			new RegisterRequestValidator().validate(regReq, errors);
			if(errors.hasErrors()) {
			//	return ResponseEntity.badRequest().build();
				
				String errorCode = errors.getAllErrors()
						.stream()
						.map(error->error.getCodes()[0])
						.collect(Collectors.joining(","));
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body(new ErrorResponse("errorCodes = " + errorCode) );
			}
			Long newMemberId = registerService.regist(regReq);
		//방법 0	response.setHeader("Location", "/api/members/" + newMemberId);  //헤더에 알려주는것. 추가된 아이디를 응답코드로 알려줌
		//방법 0	response.setStatus(HttpServletResponse.SC_CREATED);
			
			URI uri = URI.create("/api/members/" + newMemberId);//방법1
			return ResponseEntity.created(uri).build();//방법1
		} catch (DuplicateMemberException e) {
		//방법0	response.sendError(HttpServletResponse.SC_CONFLICT);
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build(); //방법1
		}
		//방법0return null;
	}

}