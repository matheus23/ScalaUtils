package org.matheusdev.vecmath.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.vecmath.Vec2F

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:55 PM
 */
class VecSpec extends FlatSpec with ShouldMatchers {

  behavior of "A Vec2[Float]"

  it should "create properly and return values properly" in {
    val vec = new Vec2F(10, 10)
    val (tx, ty) = vec.toTuple
    tx should be (10)
    ty should be (10)
    vec.x should be (10)
    vec.y should be (10)
  }

  it should "do standard arithmetic" in {
    val vec0 = new Vec2F(16, 16)
    val vec1 = new Vec2F(2, 2)
    assert((vec0 + vec1) == new Vec2F(18, 18), "Addition did not work")
    assert((vec0 - vec1) == new Vec2F(14, 14), "Subtraction did not work")
    assert((vec0 * vec1) == new Vec2F(32, 32), "Multiplication did not work")
    assert((vec0 / vec1) == new Vec2F(8, 8), "Division did not work")
  }

  it should "be immutable" in {
    val vec0 = new Vec2F(10, 10)
    val vec1 = new Vec2F(10, 10)
    val result = vec0 + vec1
    assert(result ne vec0, "Vec2F isn't immutable!")
  }

  def testNorm(vec: Vec2F, expected: Vec2F) = {
    assert((vec.normalized) == expected, s"Normalization did not work: $vec -> $expected (expected)")
  }

  it should "normalize properly" in {
    testNorm(new Vec2F(5, 0), new Vec2F(1, 0))
    testNorm(new Vec2F(0, 5), new Vec2F(0, 1))
  }

}
