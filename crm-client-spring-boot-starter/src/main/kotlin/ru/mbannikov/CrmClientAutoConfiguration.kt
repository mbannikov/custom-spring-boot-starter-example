package ru.mbannikov

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.mbannikov.client.stub.UserStubClient
import ru.mbannikov.client.web.UserWebClient

@Configuration
@ConditionalOnClass(UserClient::class)
@EnableConfigurationProperties(CrmClientProperties::class)
internal open class CrmClientAutoConfiguration(
    private val props: CrmClientProperties
) {

    @Bean
    @ConditionalOnMissingBean
    open fun userClient(): UserClient = if (props.user.stub)
        UserStubClient()
    else
        UserWebClient(props.url)
}