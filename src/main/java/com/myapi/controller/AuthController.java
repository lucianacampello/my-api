package com.myapi.controller;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.model.auth.dto.LoggedDTO;
import com.myapi.model.auth.dto.SigninDTO;
import com.myapi.model.user.dto.UserPostDTO;
import com.myapi.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public LoggedDTO signin(@Valid @RequestBody SigninDTO dto) throws Exception {
        return userService.signin(dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDTO> signup(@Valid @RequestBody UserPostDTO dto) throws Exception {
        MessageResponseDTO messageDTO = userService.create(dto);

        if (messageDTO != null) {
            return ResponseEntity.badRequest().body(messageDTO);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new MessageResponseDTO(Collections.singletonMap("Success",
                        Collections.singletonList("User successfully created"))
                )
        );
    }
}
