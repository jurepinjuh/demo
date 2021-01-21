package com.jurepinjuh.demo.Authenticatiion;

import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    MessageSource messageSource;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.getUser(username);
        if(user==null){
            throw new UsernameNotFoundException(messageSource.getMessage("msg.login.notExist",null, LocaleContextHolder.getLocale()));
        }
        return UserDetailsImpl.build(user);
    }
}
