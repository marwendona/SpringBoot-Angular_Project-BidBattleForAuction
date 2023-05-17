package tn.iit.bidbattle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.iit.bidbattle.dao.UserRepository;
import tn.iit.bidbattle.dto.UserDto;
import tn.iit.bidbattle.exception.InvalidPasswordException;
import tn.iit.bidbattle.exception.ResourceNotFoundException;
import tn.iit.bidbattle.model.user.Login;

import java.util.Optional;

import static tn.iit.bidbattle.application.ApplicationProperties.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/auth")
@CrossOrigin
public class AuthenticationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthenticationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public long login(@RequestBody Login login) {
        UserDto userDto = userRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
        String encodedPassword = userDto.getPassword();
        boolean isPwdRight = passwordEncoder.matches(login.getPassword(), encodedPassword);

        if (!isPwdRight) {
            throw new InvalidPasswordException("Password does not match");
        }

        return userDto.getId();
    }

}