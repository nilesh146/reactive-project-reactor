package com.ace.demo.flux;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxBasic {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("A", "B", "C","D","E","F");

        flux.doFirst(() -> System.out.println("do at start"))
                .doFinally(data -> System.out.println("do at end"))
                .doAfterTerminate(() -> System.out.println("do after terminate"))
                .onErrorResume(throwable -> Mono.just(throwable.getMessage()))
                .doOnComplete(() -> System.out.println("do on completion"))
                .doOnNext(s -> System.out.println("next : " + s))
                .doOnRequest(n -> System.out.println("Requesting " + n + " items"))
                .limitRate(2)
                //.map(s -> s.length())
                .subscribe(
                        item -> System.out.println("Received: " + item), // onNext
                        error -> System.err.println("Error: " + error),  // onError
                        () -> System.out.println("Sequence Complete")    // onComplete
                );

    }
}
