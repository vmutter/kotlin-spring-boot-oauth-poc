package com.vmutter.kotlin.spring.boot.oauth.poc.model.entity

import org.springframework.security.oauth2.provider.client.BaseClientDetails
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_CLIENT")
class Client : Serializable {

    @Id
    lateinit var login: String

    lateinit var password: String

    fun toBaseClientDetails(): BaseClientDetails {
        val clientDetails = BaseClientDetails()
        clientDetails.clientId = this.login
        clientDetails.clientSecret = this.password
        return clientDetails
    }
}
