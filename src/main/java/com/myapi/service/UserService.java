package com.myapi.service;

import com.myapi.infrastructure.utils.JwtUtility;
import com.myapi.model.Usuario;
import com.myapi.model.dto.LoggedDTO;
import com.myapi.model.dto.MessageResponseDTO;
import com.myapi.model.dto.SigninDTO;
import com.myapi.model.dto.SignupDTO;
import com.myapi.model.jwt.UserDetailsImpl;
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

    public MessageResponseDTO create(SignupDTO dto) {
        //TODO throw business exception
        MessageResponseDTO messageDto = null;

        if (userRepository.existsByEmail(dto.getEmail())) {
            messageDto = new MessageResponseDTO(
                    Collections.singletonMap(
                            "error",
                            Collections.singletonList("Já existe um cadastro com este email"))
            );
        } else {
            Usuario usuario = new Usuario(null, dto.getName(), dto.getEmail(),
                    encoder.encode(dto.getPassword()), dto.getRole());

            userRepository.save(usuario);
        }

        return messageDto;
    }

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

        return new LoggedDTO(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public List<Usuario> list() {
        return userRepository.findAll();
    }
}
