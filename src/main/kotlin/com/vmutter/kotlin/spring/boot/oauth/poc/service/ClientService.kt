package com.vmutter.kotlin.spring.boot.oauth.poc.service

import com.vmutter.kotlin.spring.boot.oauth.poc.model.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.NoSuchClientException
import org.springframework.stereotype.Service

@Service
class ClientService {

    @Autowired
    private lateinit var clientRepository: ClientRepository

    fun findClient(clientId: String): ClientDetails {
        return clientRepository.findById(clientId).map { c -> c.toBaseClientDetails() }
                .orElseThrow { throw NoSuchClientException("Invalid clientId: $clientId") }
    }

}
