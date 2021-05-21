package spring5_webmvc_study.survey;

import java.util.Collections;
import java.util.List;

public class Question {

	
	private String title; //질문 제목
	private List<String> options; //답변보기 옵션
	
	//객관식
	public Question(String title, List<String> options) {
		this.title = title;//질문제목
		this.options = options;//답변보기
	}

	//주관식
	public Question(String title) {
		this(title,Collections.<String>emptyList());
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}
	
	
	//선택했는가
	public boolean isChoice() {
		return options!=null &&!options.isEmpty();
	} 
	

	



	
}
