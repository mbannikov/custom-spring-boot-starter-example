package ru.mbannikov

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "crm.client")
class CrmClientProperties {
    lateinit var url: String

    val user: User = User()

    class User {
        var stub: Boolean = false
    }
}