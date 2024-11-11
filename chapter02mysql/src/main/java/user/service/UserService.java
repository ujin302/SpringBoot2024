package user.service;

import java.util.ArrayList;

import user.bean.UserDTO;

public interface UserService {

    public boolean checkId(String id);

    public void write(UserDTO userDTO);

    public ArrayList<UserDTO> getList();

}