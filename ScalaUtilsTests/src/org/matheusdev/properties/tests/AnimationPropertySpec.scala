package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{AnimationProperty, Property}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:03 PM
 */
class AnimationPropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "An AnimationProperty"

  it should "slowly animate a floating point value" in {
    val prop = new Property[Float](0f) with AnimationProperty
    prop.period = 100
    prop(10f)
    prop() should not be (10f plusOrMinus 0.01f)
    Thread.sleep(110)
    prop() should be (10f plusOrMinus 0.01f)
  }

}
