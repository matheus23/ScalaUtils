package org.matheusdev.properties.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.properties.{Property, DerivingProperty}

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:48 PM
 */
class DerivingPropertySpec extends FlatSpec with ShouldMatchers {

  behavior of "A DerivingProperty"

  val deriver = new Property[Long](0) with DerivingProperty[Int, Long]
  val prop = new Property[Int](0)

  it should "invoke set to what the deriver-function returns" in {
    deriver.link(prop, (property) => property() * 2L)
    prop(10)
    deriver() should be (20)
    prop(50)
    deriver() should be (100)
  }

  it should "unlink" in {
    deriver(10)
    deriver.unlink()
    prop(30)
    deriver() should be (100)
  }

}
