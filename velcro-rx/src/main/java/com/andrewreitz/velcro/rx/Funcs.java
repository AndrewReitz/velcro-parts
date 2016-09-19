package com.andrewreitz.velcro.rx;

import rx.functions.Func1;

/**
 * Convenience methods for {@link rx.functions.Function}
 *
 * @since 2.0.0
 * @author Andrew Reitz
 */
public final class Funcs {

  /**
   *
   * @param func
   * @param <T>
   * @return
   */
  public static <T> Func1<T, Boolean> not(final Func1<T, Boolean> func) {
    return new Func1<T, Boolean>() {
      @Override public Boolean call(T value) {
        return !func.call(value);
      }
    };
  }

  private Funcs() {
    throw new AssertionError("No Instances");
  }
}
