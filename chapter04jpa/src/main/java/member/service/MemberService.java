package member.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import member.bean.MemberDTO;

public interface MemberService {

    public boolean checkId(String id);

    public void submit(MemberDTO memberDTO);

    public List<MemberDTO> getList();

    // public Page<MemberEntity> getPageList(Pageable pageable);

    public Map<String, Object> getPageList(Pageable pageable);

    public List<MemberDTO> searchList(String colName, String value);

    public Map<String, Object> searchList(String colName, String value, Pageable pageable);
    
} 
