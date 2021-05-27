package com.myapi.controller;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.model.auth.dto.LoggedDTO;
import com.myapi.model.auth.dto.SigninDTO;
import com.myapi.model.user.dto.UserPostDTO;
import com.myapi.model.user.entity.User;
import com.myapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    //FIXME Remove when tests is done
    @GetMapping("/list")
    public ResponseEntity<List<User>> home() {
        return ResponseEntity.ok(userService.list());
    }

    @PostMapping("/signin")
    public LoggedDTO signin(@Valid @RequestBody SigninDTO dto) throws Exception {
        return userService.signin(dto);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponseDTO> signup(@Valid @RequestBody UserPostDTO dto) throws Exception {
        MessageResponseDTO messageDTO = userService.create(dto);

        if (messageDTO != null) {
            return ResponseEntity.badRequest().body(messageDTO);
        }

        return ResponseEntity.ok(new MessageResponseDTO(Collections.singletonMap("Success",
                Collections.singletonList("User successfully created"))));
    }

    @PostMapping("/signout")
    public void signOut() throws Exception {
        //TODO implement
    }

}
