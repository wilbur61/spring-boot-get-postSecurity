package dev.group.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


@Service
public class LoginDetailsService implements UserDetailsService {

    private WebApplicationContext applicationContext;
    private UserService userService;
     LoginDetailsService(UserService userService, WebApplicationContext applicationContext) {
        this.userService = userService;
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void completeSetup() {
        userService = applicationContext.getBean(UserService.class);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AppUserPrincipal(user);
    }
}
