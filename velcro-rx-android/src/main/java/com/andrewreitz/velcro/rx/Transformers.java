package com.andrewreitz.velcro.rx;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Common Rx {@link Transformers} functions.
 *
 * @since 2.0.0
 */
public final class Transformers {

  /**
   * Transformer for applying the common Android scheduler pattern to Observables. That
   * is io as the thread doing work and the thread using the results is the main thread.
   * This is common for Retrofit and many other long running tasks.
   *
   * @param <T> The type of the Observable
   * @return An observable that has the schedulers are applied.
   */
  public static <T> Observable.Transformer<T, T> ioToMainSchedulerTransform() {
    return new Observable.Transformer<T, T>() {
      @Override public Observable<T> call(Observable<T> o) {
        return o.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  private Transformers() {
    throw new AssertionError("No Instances.");
  }
}