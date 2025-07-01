package com.qengine.app.controller;

import com.qengine.app.DTO.AuthRequest;
import com.qengine.app.DTO.RegistrationDto;
import com.qengine.app.config.JwtUtils;
import com.qengine.app.model.User;
import com.qengine.app.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sergey
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private static Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto registrationDto, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return "register";
        }

        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {

            model.addAttribute("error", "Логин уже занят!");
            return "register";
        }

        User user = new User();

        String password = passwordEncoder.encode(registrationDto.getPassword());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(password);
        user.setEmail(registrationDto.getEmail());

        userRepository.save(user);

        return "redirect:/login?success";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtils.generateToken(user);
        response.addHeader("Set-Cookie", "token=" + token + "; Path=/"); //Куки
        return ResponseEntity.ok("Успешный вход!");
    }

}
