package com.ace.demo;

import reactor.core.publisher.Mono;

public class MonoDataTransformation {

    public static void main(String[] args) {
        //Date transforming operation for mono
        //this is blocking or synchronous operation because mono creation mono mapping and subscription
        //all done by main thread
        //if we have to off load mono operation from main thread to another thread we have to use subscribeOn(Schedulers.boundedElastic());
        //this will make sure that msin thread is free to execute another task and mono pipeline operation is executed on another thread.
        Mono<String> mono = Mono.just("producct data");
        mono.map(String::toUpperCase)
                .subscribe(o -> System.out.println(o));


        //The flatMap operator is used to flatten nested Mono structures by one level at a time.
        //effectively merging the two streams
        Mono<String> r1 = Mono.just("Result1");
        Mono<Mono<String>> monofmono = Mono.just(r1);
        Mono<String> flatternMono = monofmono.flatMap(stringMono -> stringMono);
        flatternMono.subscribe(s -> System.out.println(s));



    }
}
