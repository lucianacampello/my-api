package com.myapi.service;

import com.myapi.infrastructure.dto.MessageResponseDTO;
import com.myapi.infrastructure.utils.JwtUtility;
import com.myapi.model.auth.dto.LoggedDTO;
import com.myapi.model.auth.dto.SigninDTO;
import com.myapi.model.auth.mapper.AuthMapper;
import com.myapi.model.jwt.UserDetailsImpl;
import com.myapi.model.user.dto.UserPostDTO;
import com.myapi.model.user.entity.User;
import com.myapi.model.user.mapper.UserMapper;
import com.myapi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional(rollbackFor = Throwable.class)
    public MessageResponseDTO create(UserPostDTO dto) {
        //TODO throw business exception
        //TODO validation
        MessageResponseDTO messageDto = null;

        if (userRepository.existsByEmail(dto.getEmail())) {
            messageDto = new MessageResponseDTO(
                    Collections.singletonMap(
                            "error",
                            Collections.singletonList("Already exists a user registered with this email"))
            );
        } else {
            User user = UserMapper.INSTANCE.toUser(dto);
            user.setPassword(encoder.encode(dto.getPassword()));

            userRepository.save(user);
        }

        return messageDto;
    }

    @Transactional(readOnly = true)
    public LoggedDTO signin(SigninDTO dto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = StringUtils.join(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()), ", ");

        LoggedDTO loggedDTO = AuthMapper.INSTANCE.toLoggedDTO(userDetails);
        loggedDTO.setToken(jwt);
        loggedDTO.setRole(roles);

        return loggedDTO;
    }

    @Transactional(readOnly = true)
    public List<User> list() {
        return userRepository.findAll();
    }
}
