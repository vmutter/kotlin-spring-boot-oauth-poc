package com.vmutter.kotlin.spring.boot.oauth.poc.config.bean

import com.vmutter.kotlin.spring.boot.oauth.poc.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service("userDetailsServiceBean")
class UserDetailsServiceBean : UserDetailsService {

    @Autowired
    private lateinit var userService: UserService

    override fun loadUserByUsername(username: String): UserDetails {
        return userService.findUser(username)
    }

}
