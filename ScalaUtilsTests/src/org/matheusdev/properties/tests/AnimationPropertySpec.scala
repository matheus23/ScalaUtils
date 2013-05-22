package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{AnimationPropertyFloat, Property}
import org.matheusdev.interpolators.Cubic
import org.matheusdev.util.tests.TestUtilities

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:03 PM
 */
class AnimationPropertySpec extends FlatSpec with ShouldMatchers with TestUtilities {

  behavior of "An AnimationPropertyFloat"

  val prop = new Property[Float](0f) with AnimationPropertyFloat
  prop.period = 100
  prop.interpolator = Cubic

  it should "slowly animate a floating point value" in {
    prop(10f)
    prop() should not be (10f plusOrMinus 0.01f)
    Thread.sleep(110)
    prop() should be (10f plusOrMinus 0.01f)
  }

  it should "report, when the animation is over" in {
    prop(20f)
    Thread.sleep(110)
    assert(prop.isFinished,
      f"The animation didn't report 'isFinished': ${prop()}%f should be ${20f}%f")
    prop(20f)
    assert(prop.isFinished,
      f"The animation didn't report 'isFinished' after setting it to the same value: ${prop()}%f should be ${20f}%f")
  }

}
