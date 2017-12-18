package com.vmutter.kotlin.spring.boot.oauth.poc.config.bean

import com.vmutter.kotlin.spring.boot.oauth.poc.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.stereotype.Service

@Service("clientDetailsServiceBean")
class ClientDetailsServiceBean : ClientDetailsService {

    @Autowired
    private lateinit var clientService: ClientService

    override fun loadClientByClientId(clientId: String?): ClientDetails {
        return clientService.findClient(clientId)
    }

}
