package com.ace.demo;

import reactor.core.publisher.Mono;

public class MonoHandlingError {
    public static void main(String[] args) {
        //create mono that will send Throwable object
       Mono<String> monoWitherror = Mono.error(new RuntimeException("error-----"));

       //on error return send a fallback value
       Mono<String> monohandle=monoWitherror.onErrorReturn("on error Fallback value");
       monohandle.subscribe(s -> {
            System.out.println("onErrorReturn operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        });

       //switches to another Mono when an error occurs.
        Mono<String> monohandleonErrorResume= monoWitherror.onErrorResume(throwable -> Mono.just("Return another object"));
        monohandleonErrorResume.subscribe(s -> {
            System.out.println("onErrorResume operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        });


        //Maps an error to a new error.
        Mono<String> monohandleonErrorMap=   monoWitherror.onErrorMap(e -> new IllegalStateException("Mapped Error", e));
        monohandleonErrorMap.subscribe(s -> {
            System.out.println("onErrorMap operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        },throwable -> System.err.println(throwable));

        //This method lets you perform side effects on an error (like logging) without altering the error itself or stopping its propagation.
        Mono<String> monohandledoOnError=monoWitherror.doOnError(throwable -> System.out.println(throwable));
        monohandledoOnError.subscribe(s -> {
            System.out.println("doOnError operation");
            System.out.println(s);
            System.out.println("--------------------------------------");
        },throwable -> System.err.println(throwable));
    }
}
