package com.andrewreitz.velcro

import android.content.Context
import android.view.View
import com.andrewreitz.spock.android.AndroidSpecification
import com.andrewreitz.spock.android.WithContext

class BetterViewAnimatorSpec extends AndroidSpecification {

  @WithContext Context context

  void "should_do_shit"() {
    given:
    context.getApplicationInfo()
    def classUnderTest = new BetterViewAnimator(context, null)
    (1..5).each {
      def childView = new View()
      childView.id = it
      classUnderTest.addView(childView)
    }

    when:
    classUnderTest.displayedChildId = 2

    then:
    classUnderTest.displayedChildId == 2
  }
}
