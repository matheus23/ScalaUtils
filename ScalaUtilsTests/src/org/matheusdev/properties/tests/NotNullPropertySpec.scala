package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{Property, NotNullProperty}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:26 PM
 */
class NotNullPropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "A NotNullProperty"

  it should "throw a NullPointerException when setting to null values" in {
    val prop = new Property[String]("") with NotNullProperty[String]
    prop("test") should be ("test")
    evaluating(prop(null)) should produce [NullPointerException]
  }

}
