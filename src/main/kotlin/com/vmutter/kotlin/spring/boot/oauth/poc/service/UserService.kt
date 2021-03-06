package com.vmutter.kotlin.spring.boot.oauth.poc.service

import com.vmutter.kotlin.spring.boot.oauth.poc.model.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun findUser(login: String): UserDetails {
        return userRepository.findById(login).map { u ->
            User.builder().username(u.login).password(u.password).roles("ALL").build()
        }.orElseThrow { UsernameNotFoundException("Invalid username: ${login}") }
    }
}
