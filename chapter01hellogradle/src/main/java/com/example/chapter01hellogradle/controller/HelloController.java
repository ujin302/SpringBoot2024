package com.example.chapter01hellogradle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



// @Controller
@RestController // 브라우저에 바로 문자열 출력
// @RestController는 JSP 같은 뷰를 별도로 만들지 않는 대신에 컨트롤러 메소드가 리턴하는 데이터 자체를 클라이언트로 보낸다.
public class HelloController {

    public HelloController() {
        System.out.println("HelloController 생성자");
    }

    // @RequestMapping("/")
    // // @ResponseBody
    // public String index() {
    //     return "index 함수1";
    // }
    
    @RequestMapping(value = "/hello", method=RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "안녕하세요. " + name + "님!!";
    }
    
}
