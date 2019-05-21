package ru.mbannikov

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import ru.mbannikov.response.toUserID

@SpringBootApplication
class Application(
		private val userClient: UserClient
) : CommandLineRunner {

	override fun run(vararg args: String?) {
		userClient.getUser(1.toUserID())
			.doOnNext { println(it) }
			.block()

        System.exit(0)
	}
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}