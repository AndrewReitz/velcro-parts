package com.andrewreitz.velcro;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.ViewAnimator;

/**
 * A View Animator that Easily allows setting which view is to be displayed
 * See https://github.com/JakeWharton/u2020
 *
 * @author Jake Wharton
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
  public int getDisplayedChildId() {
    return getChildAt(getDisplayedChild()).getId();
  }
}
