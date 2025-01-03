package board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import board.bean.BoardDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("react/board")
public class BoardReactController {
    private List<BoardDTO> list = new ArrayList<>();
    private static int seq = 0;
    
    @PostMapping("/submit")
    public String submit(@RequestBody BoardDTO boardDTO) {
        boardDTO.setLogtime(new Date());
        boardDTO.setSeq(seq);
        seq++;
        System.out.println(boardDTO);

        list.add(boardDTO);

        return "작성 성공";
    }
    
    @GetMapping("/list")
    public List<BoardDTO> getList() {
        System.out.println("list");
        System.out.println(list);
        return list;
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam int seq) {
        System.out.println("Deleting item with seq: " + seq);
        return "delete success";
    }
    
}
