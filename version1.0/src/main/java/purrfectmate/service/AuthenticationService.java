package purrfectmate.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import purrfectmate.data.dto.LoginDTO;

@Service
public class AuthenticationService {


//    private final AuthenticationManager authenticationManager;
//
//    public AuthenticationService(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    public ResponseEntity<String> loginHuman(LoginDTO loginDTO) {
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
//                        loginDTO.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    }


}
