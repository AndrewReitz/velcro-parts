/*
 * Copyright 2014 Andrew Reitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andrewreitz.velcro.rx;

import retrofit.RetrofitError;
import rx.Observer;

/**
 * Observer for RetrofitError, that will automatically parse the error into a RetrofitError. Only
 * works for default Retrofit errors. Other errors will throw a {@link RuntimeException}.
 *
 * @author Andrew Reitz
 */
public abstract class RetrofitObserver<T> implements Observer<T> {

  /**
   * On complete that will call {@link #onEnd()}. If using on end be sure to call through to super.
   */
  @Override public void onCompleted() {
    onEnd();
  }

  /**
   * If throwable is an instance of RetrofitError if will Call {@link #onError(RetrofitError)}
   * automatically parsing the error to be a {@link RetrofitError}.
   */
  @Override public void onError(Throwable e) {
    if (e instanceof RetrofitError) {
      this.onError((RetrofitError) e);
    } else {
      nonRetrofitError(e);
    }
    onEnd();
  }

  public void nonRetrofitError(Throwable e) {
    throw new RuntimeException("Error other than retrofit error. "
        + "You should override nonRetrofitError(Throwable e) to handle this", e);
  }

  /**
   * Override to get notified from onComplete or onError. Be sure to call through to super on
   * overridden {@link #onError(Throwable)} or {@link #onCompleted()}.
   */
  public void onEnd() {

  }

  public abstract void onError(RetrofitError e);
}
