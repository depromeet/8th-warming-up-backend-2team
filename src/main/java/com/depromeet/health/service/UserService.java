package com.depromeet.health.service;


import com.depromeet.health.config.security.JwtTokenProvider;
import com.depromeet.health.model.User;
import com.depromeet.health.payload.LoginRequest;
import com.depromeet.health.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public User loadUser(LoginRequest request) throws UsernameNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(request.getEmail());
        return foundUser.orElseGet(() -> insertUser(request));
    }

    private User insertUser(LoginRequest request) {
        User user = new User(request);
        String token = jwtTokenProvider.createToken(user.getEmail());
        user.setToken(token);
        return userRepository.save(user);
    }

}
