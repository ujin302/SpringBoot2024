package user.controller;

import java.net.URI;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import user.bean.UserDTO;
import user.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("writeForm")
    public String writeForm() {
        return "user/writeForm";
    }
    
    @PostMapping("checkId")
    @ResponseBody
    public boolean checkId(@RequestParam String id) {
        System.out.println("checkId: " + id);
        return userService.checkId(id);
    }
    
    @PostMapping("write")
    @ResponseBody
    public void write(@ModelAttribute UserDTO userDTO) {
        System.out.println("write");
        System.out.println(userDTO);
        userService.write(userDTO);
    }
    
    @GetMapping("list")
    public String list(ModelMap map) {
        ArrayList<UserDTO> dtoList = userService.getList();

        map.put("list", dtoList);
        return "user/list";
    }
}