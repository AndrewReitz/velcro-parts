package com.andrewreitz.velcro.internal;

/**
 * @since 2.0.0
 */
public final class Preconditions {

  public static <T> T checkNotNull(T reference, String errorMessage) {
    if (reference == null) {
      throw new NullPointerException(errorMessage);
    }
    return reference;
  }

  private Preconditions() {
    throw new AssertionError("No Instances");
  }
}
