package dev.group.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;


@Service
public class JpaDetailsService implements UserDetailsService {

    private UserService userService;

    JpaDetailsService(UserService userService) {
        this.userService = userService;

    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        //find our user by username
        User user = userService.findByUsername(username);
        //if the user is not found throw error if not create a new user from
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUser(user);
    }
}
