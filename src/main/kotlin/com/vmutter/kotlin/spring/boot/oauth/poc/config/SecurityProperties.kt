package com.vmutter.kotlin.spring.boot.oauth.poc.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "security")
class SecurityProperties {
    var keyResource: String? = null
    var keyPass: String? = null
    var keyAlias: String? = null
    var resourceId: String? = null
    var accessToken: AccessToken? = null
}

class AccessToken {
    var validityPeriod: String? = null
}
