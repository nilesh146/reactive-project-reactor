package com.ace.demo.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CreateFlux {

    public static void main(String[] args) {

        getFluxJust().subscribe(s -> System.out.println(s));

        getFluxRange().map(aLong -> aLong.toString()).subscribe(aLong -> System.out.println(aLong));

        getFluxIterable().map(aLong ->aLong.toUpperCase() ).subscribe(s -> System.out.println(s));

        getFluxWithError().onErrorResume(throwable -> Mono.just("Error occur ............."))
                .subscribe(o -> System.out.println(o));

        getMergedFlux().subscribe(o -> System.out.println(o));

    }

    public static Flux<String> getFluxJust() {
        return Flux.just("mumbai", "pune", "banglor", "delhi", "jaipur");
    }

    public static Flux<String> getFluxIterable() {
        List<String> cities = Arrays.asList("mumbai", "pune", "banglor", "delhi", "jaipur");
        return Flux.fromIterable(cities);
    }

    public static Flux getFluxWithError() {
        return Flux.error(() -> new RuntimeException("Error"));
    }

    public static Flux getEmptyFlux() {
        return Flux.empty();
    }

    public static Flux<Integer> getFluxRange() {
        return Flux.range(1, 100);
    }

    public static Flux<Long> getFluxInterval() {
        return Flux.interval(Duration.ofSeconds(1));
    }

    public static Flux getMergedFlux(){
        return getFluxJust().mergeWith(getFluxIterable());
    }
}
