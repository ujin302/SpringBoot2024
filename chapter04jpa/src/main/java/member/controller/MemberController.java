package member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import member.bean.MemberDTO;
import member.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;
    
    @GetMapping("/checkId")
    @ResponseBody
    public boolean checkId(@RequestParam(value = "id") String id) {
        System.out.println("checkId: " + id);
        return memberService.checkId(id);
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join/submit")
    @ResponseBody
    public void joinSubmit(@ModelAttribute MemberDTO memberDTO) {
        memberService.submit(memberDTO);
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "0") String pg,
                            // page: 페이지번호, size: 개수, sort: 컬럼명
                            @PageableDefault(page = 0, size = 3, sort = "name", direction = Sort.Direction.DESC) Pageable pageable,
                            Model model
                        ) {
        // 1. 모든 정보 추출
        // List<MemberDTO> list = memberService.getList();

        // 2. 페이징 처리
        // Page<MemberEntity> list = memberService.getPageList(pageable);
        Map<String, Object> map = memberService.getPageList(pageable);
        // model.addAttribute("list", list);
        model.addAttribute("map", map);
        return "listPage";
    }

    // 검색
    @GetMapping("/list/search")
    @ResponseBody
    public List<MemberDTO> search(@RequestParam("colName") String colName, @RequestParam("value") String value) {
        System.out.println("colName: " + colName);
        System.out.println("value: " + value);
        List<MemberDTO> list = memberService.searchList(colName, value);
        return list;
    }

    // 검색 페이징 처리
    @GetMapping("/list/searchPaging")
    @ResponseBody
    public Map<String, Object> search(@RequestParam("colName") String colName, @RequestParam("value") String value,
                                @RequestParam(value = "page", required = false, defaultValue = "0") String pg,
                                @PageableDefault(page = 0, size = 3) Pageable pageable
    ) {
        System.out.println("colName: " + colName);
        System.out.println("value: " + value);
        System.out.println("pg: " + pg);
        Map<String, Object> map = memberService.searchList(colName, value, pageable);
        return map;
    }
    
}
