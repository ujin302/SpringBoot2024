package user.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.service.UserService;

@Service
@Transactional // 
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean checkId(String id) {
        int count = userDAO.checkId(id);
        System.out.println("checkId Service: " + count);
        if(count == 1) return false;
        return true;
    }

    @Override
    public void write(UserDTO userDTO) {
        System.out.println("write Service");
        userDAO.write(userDTO);
    }

    @Override
    public ArrayList<UserDTO> getList() {
        return userDAO.getList();
    }
    
}
