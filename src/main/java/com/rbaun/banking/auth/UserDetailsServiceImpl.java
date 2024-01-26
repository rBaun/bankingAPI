package com.rbaun.banking.auth;

import com.rbaun.banking.model.user.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Loading user by username: {}", username);
        UserInfo userInfo = userRepository.findByUsername(username);
        if (userInfo == null) {
            logger.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User with username " + username + " not found");
        }
        logger.info("User with username {} found", username);

        return new CustomUserDetails(userInfo);
    }

}
