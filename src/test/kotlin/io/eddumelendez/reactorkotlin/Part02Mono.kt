package io.eddumelendez.reactorkotlin

import org.junit.Test
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import reactor.test.test
import java.time.Duration

class Part02Mono {

    @Test
    fun empty() {
        val mono = emptyMono()

        mono.test()
                .verifyComplete()
    }

    // Return an empty Mono
    fun emptyMono(): Mono<String> {
        return Mono.empty()
    }

    @Test
    fun noSignal() {
        val mono = monoWithNoSignal()

        mono.test()
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(1))
                .thenCancel()
                .verify()
    }

    // Return an Mono that never emit any signal
    fun monoWithNoSignal(): Mono<String> {
        return Mono.never()
    }

    @Test
    fun error() {
        val mono = errorMono()

        mono.test()
                .verifyError(IllegalStateException::class.java)
    }

    // Create a Mono that emits an IllegalStateException
    fun errorMono(): Mono<String> {
        return IllegalStateException().toMono()
    }

}
