package com.vmutter.kotlin.spring.boot.oauth.poc.model.repository

import com.vmutter.kotlin.spring.boot.oauth.poc.model.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

internal interface ClientRepository : JpaRepository<Client, String>
