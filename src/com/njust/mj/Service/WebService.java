package com.njust.mj.Service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/web")
public class WebService {
	
	@RequestMapping(value="/test.do")
	public void test() {
		System.out.println("spring hello world!");
	}
}
