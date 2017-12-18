package com.vmutter.kotlin.spring.boot.oauth.poc.config

import com.vmutter.kotlin.spring.boot.oauth.poc.config.bean.ClientDetailsServiceBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory

@Configuration
@EnableAuthorizationServer
class OAuth2Config : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private lateinit var securityProperties: SecurityProperties

    @Autowired
    @Qualifier("authenticationManagerBean")
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    @Qualifier("clientDetailsServiceBean")
    private lateinit var clientDetailsServiceBean: ClientDetailsServiceBean

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.withClientDetails(clientDetailsServiceBean)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints?.tokenStore(tokenStore())
                ?.authenticationManager(authenticationManager)?.userDetailsService(userDetailsService)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security?.tokenKeyAccess("permitAll()")?.checkTokenAccess("isAuthenticated()")
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JwtTokenStore(jwtAccessTokenConverter())
    }

    @Bean
    protected fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val keyStoreKeyFactory = KeyStoreKeyFactory(ClassPathResource(securityProperties.keyResource),
                securityProperties.keyPass?.toCharArray())

        val converter = JwtAccessTokenConverter()
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(securityProperties.keyAlias))
        return converter
    }

}
