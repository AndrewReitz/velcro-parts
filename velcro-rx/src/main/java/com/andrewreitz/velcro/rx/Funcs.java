package com.andrewreitz.velcro.rx;

import rx.functions.Func1;

/**
 * Helper methods for Rx {@link rx.functions.Function}s, making them more fluent.
 *
 * @since 2.0.0
 */
public final class Funcs {
  /**
   * Inverts a function call.
   *
   * @param func the function who's output should be inverted.
   * @param <T> The type parameter that is the type passed to the function.
   * @return a {@link Func1} that inverts the original input function.
   */
  public static <T>Func1<T, Boolean> not(final Func1<T, Boolean> func) {
    return new Func1<T, Boolean>() {
      @Override public Boolean call(T t) {
        return !func.call(t);
      }
    };
  }

  private Funcs() {
    throw new AssertionError("No Instances");
  }
}
