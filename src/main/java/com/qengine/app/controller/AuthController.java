package com.qengine.app.controller;

import com.qengine.app.DTO.AuthRequest;
import com.qengine.app.model.User;
import com.qengine.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sergey
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User Register!");
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request){
        //Заглушка - позже добавим JWT # !$
        return ResponseEntity.ok("Login successful (# !$ JWT)");
    }
}
