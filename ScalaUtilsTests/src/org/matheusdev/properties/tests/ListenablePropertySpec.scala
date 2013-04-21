package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{Property, ListenableProperty}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:42 PM
 */
class ListenablePropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "A ListenableProperty"

  val listenable = new Property[Int](0) with ListenableProperty[Int]
  var v = 0
  val listener = (prop: Property[Int], value: Int) => v = value

  it should "listen for changes" in {
    listenable.listen(listener)
    listenable(10)
    v should be (10)
  }

  it should "detach listeners" in {
    listenable.unlisten(listener)
    listenable(20)
    v should be (10)
  }

}
