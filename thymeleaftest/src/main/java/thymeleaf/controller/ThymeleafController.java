package thymeleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        // model.addAttribute("bb", bb);
        
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

}
