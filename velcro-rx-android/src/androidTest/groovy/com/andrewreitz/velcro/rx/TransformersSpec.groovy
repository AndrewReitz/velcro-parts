package com.andrewreitz.velcro.rx

import com.andrewreitz.spock.android.AndroidSpecification
import rx.Observable
import rx.observers.TestSubscriber

import java.util.concurrent.TimeUnit

class TransformersSpec extends AndroidSpecification {
  void "should run on a background thread"() {
    given:
    def testSubscriber = new TestSubscriber()
    def mainThread = Thread.currentThread()

    when:
    Observable.create { o ->
      assert Thread.currentThread() != mainThread
      TimeUnit.MILLISECONDS.sleep(10)
      o.onNext('a')
      o.onNext('b')
      o.onCompleted()
    }.compose(Transformers.ioToMainSchedulerTransform())
    .subscribe(testSubscriber)

    then:
    /* give it that extra time because emulators are slow and hang */
    testSubscriber.awaitTerminalEvent(500, TimeUnit.MILLISECONDS)
    testSubscriber.assertCompleted()
    testSubscriber.assertReceivedOnNext(['a', 'b'])
  }
}
