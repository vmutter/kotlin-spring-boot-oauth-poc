package com.vmutter.kotlin.spring.boot.oauth.poc.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Autowired
    private lateinit var securityProperties: SecurityProperties

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources?.resourceId(securityProperties.resourceId)
    }

    override fun configure(http: HttpSecurity?) {
        http?.requestMatcher(OAuthRequestedMatcher())
                ?.authorizeRequests()?.antMatchers(HttpMethod.OPTIONS)
                ?.permitAll()?.anyRequest()?.authenticated()
    }

    private class OAuthRequestedMatcher : RequestMatcher {
        override fun matches(request: HttpServletRequest): Boolean {
            val auth = request.getHeader("Authorization")

            // Determine if the client request contained an OAuth Authorization
            val haveOauth2Token = auth != null && auth.startsWith("Bearer")
            val haveAccessToken = request.getParameter("access_token") != null
            return haveOauth2Token || haveAccessToken
        }

    }

}
