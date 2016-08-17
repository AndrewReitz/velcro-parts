package com.andrewreitz.velcro.internal;

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
