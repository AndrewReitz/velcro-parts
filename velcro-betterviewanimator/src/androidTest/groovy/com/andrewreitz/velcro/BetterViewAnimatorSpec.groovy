package com.andrewreitz.velcro

import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.andrewreitz.spock.android.AndroidSpecification
import groovy.transform.CompileStatic
import org.hamcrest.Matcher
import org.junit.Rule

import static android.support.test.espresso.Espresso.onView
import static android.support.test.espresso.matcher.ViewMatchers.withId

import java.lang.Void as Should

class BetterViewAnimatorSpec extends AndroidSpecification {

  @Rule ActivityTestRule<BetterViewAnimatorTestActivity> activityRule =
      new ActivityTestRule<>(BetterViewAnimatorTestActivity)

  Should "set the display child by id"() {
    when:
    onView(withId(R.id.better_view_animator)).perform(new SetDisplayChildViewAction(viewId: R.id.test_view))
    onView(withId(R.id.better_view_animator)).check(new SetDisplayChildViewAssertion(expectedViewId: R.id.test_view))

    then:
    noExceptionThrown()
  }
}

@CompileStatic
class SetDisplayChildViewAction implements ViewAction {

  int viewId

  @Override Matcher<View> getConstraints() {
    return ViewMatchers.isAssignableFrom(BetterViewAnimator)
  }

  @Override String getDescription() {
    return 'set the display child'
  }

  @Override void perform(UiController uiController, View view) {
    BetterViewAnimator betterViewAnimator = view as BetterViewAnimator
    betterViewAnimator.displayedChildId = viewId
  }
}

@CompileStatic
class SetDisplayChildViewAssertion implements ViewAssertion {

  int expectedViewId

  @Override void check(View view, NoMatchingViewException noViewFoundException) {
    if (noViewFoundException) {
      throw noViewFoundException
    }

    BetterViewAnimator betterViewAnimator = view as BetterViewAnimator
    assert betterViewAnimator.displayedChildId == expectedViewId
  }
}
