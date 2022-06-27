package it.city.edu.controller;

import it.city.edu.entity.User;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqLogin;
import it.city.edu.payload.ReqRegister;
import it.city.edu.repository.AuthRepository;
import it.city.edu.security.JwtTokenProvider;
import it.city.edu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody ReqRegister reqRegister){
        ApiResponse register = authService.register(reqRegister);
        return ResponseEntity.status(register.isSuccess()?200:409).body(register);
    }

    @PostMapping("/checkUser")
    public HttpEntity<?> checkUser( @RequestBody ReqLogin reqLogin){
        String username= reqLogin.getPassSerial()+"@@"+reqLogin.getPassNumber();
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, reqLogin.getPassword())
        );
        User user = authRepository.findByPassSerialAndPassNumber(reqLogin.getPassSerial(), reqLogin.getPassNumber()).get();
        return ResponseEntity.ok(new ReqLogin(user.getPassSerial(), user.getPassNumber(), user.getPhoneNumber()));

    }

    @PostMapping("/login")
    public  HttpEntity<?> login(@RequestBody ReqLogin reqLogin){
         User user = authRepository.findByPassSerialAndPassNumber(reqLogin.getPassSerial(), reqLogin.getPassNumber()).orElseThrow(() -> new UsernameNotFoundException("getUser"));
        String generateToken = jwtTokenProvider.generateToken(user.getId());
        return ResponseEntity.ok(generateToken);
    }

}
