package org.matheusdev.vecmath.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.vecmath.Pos2

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/24/13
 * Time: 5:21 PM
 */
class Pos2Spec extends FlatSpec with ShouldMatchers {

  behavior of "A Pos2[Float]"

  it should "add-set properly" in {
    val pos1 = new Pos2(10f, 10f)
    pos1 += new Pos2(5f, 5f)
    assert(pos1.x == 15f)
    assert(pos1.y == 15f)
  }

}
