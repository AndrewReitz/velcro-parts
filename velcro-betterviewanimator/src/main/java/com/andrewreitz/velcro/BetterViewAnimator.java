/*
 * Copyright 2014 Andrew Reitz
 * Copyright 2014 Jake Wharton
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

package com.andrewreitz.velcro;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * A {@link ViewAnimator} that easily allows setting which view is to be displayed based off
 * of a view ID.
 *
 * @author Jake Wharton
 * @author Andrew Reitz
 * @since 1.0.0
 */
public class BetterViewAnimator extends ViewAnimator {

  public BetterViewAnimator(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /** Displays the view of the id passed in */
  public void setDisplayedChildId(@IdRes int id) {
    if (getDisplayedChildId() == id) {
      return;
    }
    for (int i = 0, count = getChildCount(); i < count; i++) {
      if (getChildAt(i).getId() == id) {
        setDisplayedChild(i);
        return;
      }
    }
    throw new IllegalArgumentException("No view with ID " + id);
  }

  /** Get the id this ViewAnimator is currently displaying */
  @IdRes public int getDisplayedChildId() {
    return getChildAt(getDisplayedChild()).getId();
  }
}
