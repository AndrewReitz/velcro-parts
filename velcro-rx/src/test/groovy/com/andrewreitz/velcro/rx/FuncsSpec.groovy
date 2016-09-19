package com.andrewreitz.velcro.rx

import spock.lang.Specification

class FuncsSpec extends Specification {
  void "should invert the boolean value of the input functions result"() {
    expect:
    !Funcs.not { it == 'a' }.call('a')
    Funcs.not { it == 'b' }.call('a')
  }
}