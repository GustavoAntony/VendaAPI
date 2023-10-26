package com.api.User.user;

import com.api.User.user.dto.ReturnUserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {

    private HashMap<String, ReturnUserDTO> cache = new HashMap<>();

    public ReturnUserDTO get(String token){
        ReturnUserDTO user = get(token);
        if(user == null){
            throw new RuntimeException("User not found");
        }

        return user;
    }

    public void put(String token, ReturnUserDTO user){
        cache.put(token, user);
    }


}
