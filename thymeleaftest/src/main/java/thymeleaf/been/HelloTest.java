package thymeleaf.been;

import org.springframework.stereotype.Component;

@Component
public class HelloTest {
    
    public String hello(String name) {
        return "안녕하세요! " + name;
    }
}
