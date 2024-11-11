package user.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserDTO;

@Mapper
public interface UserDAO {
    // id 중복 체크
    public int checkId(String id);

    // 회원가입
    public void write(UserDTO userDTO);

    public ArrayList<UserDTO> getList();
}