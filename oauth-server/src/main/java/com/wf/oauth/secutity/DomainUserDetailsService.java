package com.wf.oauth.secutity;

import com.wf.oauth.db.User;
import com.wf.oauth.db.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wf
 * @Date: 2020-05-07 11:28
 * @describe 用户服务  实现 Spring Security的UserDetailsService接口方法，用于身份认证
 */
@Service("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(username);
        List<? extends GrantedAuthority> authorities = new ArrayList();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

}
