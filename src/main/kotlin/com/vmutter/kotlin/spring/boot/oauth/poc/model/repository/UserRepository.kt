package com.vmutter.kotlin.spring.boot.oauth.poc.model.repository

import com.vmutter.kotlin.spring.boot.oauth.poc.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

internal interface UserRepository : JpaRepository<User, String>
