package com.api.User.user;

import com.api.User.user.dto.ReturnUserDTO;
import com.api.User.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {

    private HashMap<String, ReturnUserDTO> cache = new HashMap<>();

    public ReturnUserDTO get(String token){
        ReturnUserDTO user = get(token);
        if(user == null){
            throw new UserNotFoundException();
        }

        return user;
    }

    public void put(String token, ReturnUserDTO user){
        cache.put(token, user);
    }


}
