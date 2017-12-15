package com.vmutter.kotlin.spring.boot.oauth.poc.model

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table("T_USER")
internal class User : Serializable {

    @Id
    lateinit var id: Long? = null

    lateinit val name: String

}
