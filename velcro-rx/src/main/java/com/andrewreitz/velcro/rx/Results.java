package com.andrewreitz.velcro.rx;

import retrofit2.adapter.rxjava.Result;
import rx.functions.Func1;

/**
 * Useful methods for working with {@link Result} classes and RxJava making more fluent code.
 *
 * @since 2.0.0
 */
public final class Results {

  private static final Func1<Result<?>, Boolean> SUCCESSFUL = new Func1<Result<?>, Boolean>() {
    @Override public Boolean call(Result<?> result) {
      return !result.isError() && result.response().isSuccessful();
    }
  };

  /**
   * Check if a result is successful.
   *
   * @return a function that can be easily chained with other rx methods to check if a retrofit
   * result is successful.
   */
  public static Func1<Result<?>, Boolean> isSuccessful() {
    return SUCCESSFUL;
  }

  private Results() {
    throw new AssertionError("No instances.");
  }
}
