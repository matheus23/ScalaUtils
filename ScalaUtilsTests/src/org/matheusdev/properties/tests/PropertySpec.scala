package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.Property

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:23 PM
 */
class PropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "A Property"

  it should "return the values it was set to" in {
    val prop = new Property[Int](0)
    prop(10)
    prop() should be (10)
  }

  it should "produce the toString() of it's value" in {
    val str = "Test toString()"
    class TestToString { override def toString = str }
    val prop = new Property[TestToString](new TestToString)
    prop.toString should be (str)
  }

}
