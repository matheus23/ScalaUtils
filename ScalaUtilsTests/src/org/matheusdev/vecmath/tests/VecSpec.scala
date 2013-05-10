package org.matheusdev.vecmath.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.vecmath.Vec2f

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:55 PM
 */
class VecSpec extends FlatSpec with ShouldMatchers {

  behavior of "A Vec2[Float]"

  it should "create properly and return values properly" in {
    val vec = new Vec2f(10, 10)
    val (tx, ty) = vec.toTuple
    tx should be (10)
    ty should be (10)
    vec.x should be (10)
    vec.y should be (10)
  }

  it should "do standard arithmetic" in {
    val vec0 = new Vec2f(16, 16)
    val vec1 = new Vec2f(2, 2)
    assert((vec0 + vec1) == new Vec2f(18, 18), "Addition did not work")
    assert((vec0 - vec1) == new Vec2f(14, 14), "Subtraction did not work")
    assert((vec0 * vec1) == new Vec2f(32, 32), "Multiplication did not work")
    assert((vec0 / vec1) == new Vec2f(8, 8), "Division did not work")
  }

  it should "be immutable" in {
    val vec0 = new Vec2f(10, 10)
    val vec1 = new Vec2f(10, 10)
    val result = vec0 + vec1
    assert(result ne vec0, "Vec2f isn't immutable!")
  }

  def testNorm(vec: Vec2f, expected: Vec2f) = {
    assert((vec.normalized) == expected, s"Normalization did not work: $vec -> $expected (expected)")
  }

  it should "normalize properly" in {
    testNorm(new Vec2f(5, 0), new Vec2f(1, 0))
    testNorm(new Vec2f(0, 5), new Vec2f(0, 1))
  }

}
