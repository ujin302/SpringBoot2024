package thymeleaf.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import jakarta.servlet.http.HttpSession;
import thymeleaf.been.PersonDTO;


@Controller
public class ThymeleafController {
    
    public ThymeleafController() {
        System.out.println("ThymeleafController 생성자");
    }

    @GetMapping("hitymeleaf")
    public String hitymeleaf(Model model) {
        model.addAttribute("say","안녕!");

        return "basic/01_hitymeleaf";
    }

    @GetMapping("literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring Boot");
        return "basic/02_literal";
    }

    @GetMapping("variable")
    public String variable(Model model) {
        PersonDTO aa = new PersonDTO("홍1", 25);
        PersonDTO bb = new PersonDTO("홍2", 35);
        PersonDTO cc = new PersonDTO("홍3", 28);

        model.addAttribute("aa", aa);
        
        List<PersonDTO> list = new ArrayList<>();
        list.add(aa);
        list.add(bb);
        list.add(cc);
        model.addAttribute("list", list);

        // Map
        Map<String, Object> map = new HashMap<>();
        map.put("cc", cc);

        model.addAttribute("map", map);

        return "basic/03_variable";
    }

    @GetMapping("operation")
    public String operation(Model model) {

        return "basic/04_operation";
    }

    @GetMapping("text_basic")
    public String text_basic(ModelMap modelMap) {
        modelMap.put("data", "Spring Boot");
        return "basic/05_text_basic";
    }

    @GetMapping("attribute")
    public String attribute(ModelMap modelMap) {
        modelMap.put("data", "Spring Boot");
        return "basic/06_attribute";
    }

    @GetMapping("ifcondition")
    public String ifcondition(Model model) {
        model.addAttribute("age", 15);
        model.addAttribute("movie", "탈주");
        model.addAttribute("today", "수요일");
        return "basic/07_ifcondition";
    }

    @GetMapping("condition")
    public String condition(Model model) {
        PersonDTO aa = new PersonDTO("홍1", 25);
        PersonDTO bb = new PersonDTO("홍2", 35);
        PersonDTO cc = new PersonDTO("홍3", 18);

        List<PersonDTO> list = new ArrayList<>();
        list.add(aa);
        list.add(bb);
        list.add(cc);
        model.addAttribute("list", list);

        return "basic/08_condition";
    }

    @GetMapping("each")
    public String each(Model model) {
        PersonDTO aa = new PersonDTO("홍1", 25);
        PersonDTO bb = new PersonDTO("홍2", 35);
        PersonDTO cc = new PersonDTO("홍3", 18);

        List<PersonDTO> list = new ArrayList<>();
        list.add(aa);
        list.add(bb);
        list.add(cc);
        model.addAttribute("list", list);

        return "basic/09_each";
    }

    @GetMapping("block")
    public String block(Model model) {
        PersonDTO aa = new PersonDTO("홍1", 25);
        PersonDTO bb = new PersonDTO("홍2", 35);
        PersonDTO cc = new PersonDTO("홍3", 18);

        List<PersonDTO> list = new ArrayList<>();
        list.add(aa);
        list.add(bb);
        list.add(cc);
        model.addAttribute("list", list);

        return "basic/10_block";
    }

    @GetMapping("link")
    public String link() {
        return "basic/11_link";
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello(@RequestParam(name="name", defaultValue="no name") String name, 
                        @RequestParam(name="age", defaultValue = "0") String age) {
        return name + " / " + age;
    }

    @GetMapping("hello/{name}/{age}")
    @ResponseBody
    public String hello2(@PathVariable String name, @PathVariable String age) {
        return name + " / " + age;
    }
    
    @GetMapping("hello/{name}")
    @ResponseBody
    public String hello3(@PathVariable String name, 
                        @RequestParam(name="age", defaultValue = "0") String age) {
        return name + " / " + age;
    }

    @GetMapping("select")
    @ResponseBody
    public String select() {
        return "select";
    }

    @GetMapping("insert")
    @ResponseBody
    public String insert(@RequestParam(defaultValue = "1000") int pageno) {

        return "pageno: " + pageno;
    }

    @GetMapping("/character/detail/{name}/{number}")
    @ResponseBody
    public String characterDetail(@PathVariable String name, @PathVariable int number) {
        return name + " / " + number;
    }

    @GetMapping("basic_object")
    public String basic_object(Model model, HttpSession session) {
        LocalDateTime startTime = LocalDateTime.now();
        model.addAttribute("startTime", startTime);
        model.addAttribute("str", " String Boot  ");
        model.addAttribute("num", 1234567.9817);

        // 세션
        session.setAttribute("memId", "hong");
        session.setAttribute("memName", "홍길동");

        return "basic/12_basic_object";
    }

    @GetMapping("fragment1")
    public String fragmentMain1() {

        return "fragment/main/fragmentMain1";
    }

    @GetMapping("fragment2")
    public String fragmentMain2() {

        return "fragment/main/fragmentMain2";
    }
}