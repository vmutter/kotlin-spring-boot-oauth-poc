package com.vmutter.kotlin.spring.boot.oauth.poc.model.entity

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "T_USER")
class User : Serializable {

    @Id
    lateinit var login: String

    lateinit var password: String

}
