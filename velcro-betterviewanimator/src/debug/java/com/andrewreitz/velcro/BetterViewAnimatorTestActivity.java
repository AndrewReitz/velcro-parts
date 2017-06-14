package com.andrewreitz.velcro;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;

public final class BetterViewAnimatorTestActivity extends Activity {

  @VisibleForTesting BetterViewAnimator betterViewAnimator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.test_view);
    betterViewAnimator = (BetterViewAnimator) findViewById(R.id.better_view_animator);
  }
}
