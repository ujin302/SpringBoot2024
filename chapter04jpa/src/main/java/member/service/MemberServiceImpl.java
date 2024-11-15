package member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import member.bean.MemberDTO;
import member.bean.MemberPaging;
import member.dao.MemberRepository;
import member.entity.MemberEntity;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberPaging memberPaging;

    @Override
    public boolean checkId(String id) {
        if(memberRepository.findById(id) == null) return true;
        return false;
    }

    @Override
    public void submit(MemberDTO memberDTO) {
        memberRepository.save(memberDTO.toEntity());
    }

    @Override
    public List<MemberDTO> getList() {
        List<MemberDTO> list = new ArrayList<>();
        List<MemberEntity> getEntityList = memberRepository.findAll();
        
        for (MemberEntity memberEntity : getEntityList) {
            list.add(memberEntity.toDto());
            System.out.println(memberEntity.toString());
        }
        return list;
    }

    // @Override
    // public Page<MemberEntity> getPageList(Pageable pageable) {
    //     Page<MemberEntity> list = memberRepository.findAll(pageable);
    //     return list;
    // }

    @Override
    public Map<String, Object> getPageList(Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        
        int totalA = memberRepository.findAll().size();
        memberPaging.setCurrentPgae(pageable.getPageNumber() + 1);
        memberPaging.setPageBlock(3);
        memberPaging.setPageSize(pageable.getPageSize());
        memberPaging.setTotalA(totalA);
        memberPaging.makePagingHTML("memberPage");

        Page<MemberEntity> list = memberRepository.findAll(pageable);
        map.put("list", list);
        map.put("memberPaging", memberPaging);

        return map;
    }

    // 검색 
    @Override
    public List<MemberDTO> searchList(String colName, String value) {
        List<MemberEntity> entityList = null;
        // if(colName.equals("name")) entityList = memberRepository.findByNameContaining(value);
        // // select * from membertbl where name list concat('%', '홍', '%')
        // else if(colName.equals("id")) entityList = memberRepository.findByIdContaining(value);

        if(colName.equals("name")) entityList = memberRepository.getSearchName(value);
        else if(colName.equals("id")) entityList = memberRepository.getSearchId(value);

        List<MemberDTO> list = null;
        if(entityList != null) {
            list = new ArrayList<>();
            for (MemberEntity entity : entityList) {
                list.add(entity.toDto());
            }
        }
        
        return list;
    }

    // 검색 페이징 처리
    @Override
    public Map<String, Object> searchList(String colName, String value, Pageable pageable) {
        Map<String, Object> map = null;
        List<MemberEntity> entityList = null;
        int totalA = 0;
        
        if(colName.equals("name")) {
            entityList = memberRepository.getSearchName(value, pageable);
            // totalA = memberRepository.getSearchName(value).size();
            totalA = memberRepository.findByNameContaining(value).size();
        }
        else if(colName.equals("id")) {
            entityList = memberRepository.getSearchId(value, pageable);
            // totalA = memberRepository.getSearchId(value).size();
            totalA = memberRepository.findByIdContaining(value).size();
        }

        List<MemberDTO> list = null;
        if(entityList != null) {
            map = new HashMap<>();
            list = new ArrayList<>();

            for (MemberEntity entity : entityList) {
                list.add(entity.toDto());
            }

            memberPaging.setCurrentPgae(pageable.getPageNumber() + 1);
            memberPaging.setPageBlock(3);
            memberPaging.setPageSize(pageable.getPageSize());
            memberPaging.setTotalA(totalA);
            memberPaging.makePagingHTML("memberSearchPage");

            map.put("list", list);
            map.put("memberPaging", memberPaging);
        }
        
        return map;
    }
    
} 
