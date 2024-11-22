package com.ace.demo;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class BasicPublisherSubscriberImplementation {

    public static void main(String[] args) {


        Publisher publisher = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {

                subscriber.onSubscribe(new Subscription() {
                    private boolean isCancelled = false;
                    @Override
                    public void request(long n) {
                        if (isCancelled) {
                            return; // Don't emit if subscription is cancelled
                        }

                        try {
                            // Emit n items
                            for (int i = 1; i <= n; i++) {
                                if (isCancelled) {
                                    break; // Stop if cancelled
                                }
                                subscriber.onNext(i);
                            }
                            // Send onComplete signal after all items are emitted
                            subscriber.onComplete();
                        } catch (Exception e) {
                            // Send onError signal in case of an error
                            subscriber.onError(e);
                        }
                    }

                    @Override
                    public void cancel() {
                        isCancelled = true; // Mark the subscription as cancelled
                        System.out.println("Subscription cancelled");
                    }
                });
            }
        };


        Subscriber subscriber=   new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(Integer in) {
                System.out.println("Received: " + in);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error: " + t.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("All items received. Stream complete!");
            }
        };


        publisher.subscribe(subscriber);
    }
}
