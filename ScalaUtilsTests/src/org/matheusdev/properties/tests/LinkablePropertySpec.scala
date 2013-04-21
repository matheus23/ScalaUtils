package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{LinkableProperty, Property}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:33 PM
 */
class LinkablePropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "A LinkableProperty"

  val prop = new Property[Int](40)
  val linked = new Property[Int](0) with LinkableProperty[Int]
  linked.link(prop)

  it should "produce values from linked Properties" in {
    prop(100)
    linked() should be (100)
  }

  it should "not set values in linked Properties" in {
    linked(20)
    linked() should not be (20)
  }

  it should "unlink" in {
    linked(30)
    linked.unlink()
    prop(10)
    linked() should be (100)
  }

}
