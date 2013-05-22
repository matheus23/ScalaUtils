package org.matheusdev.vecmath.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.vecmath.Vec2

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:55 PM
 */
class VecSpec extends FlatSpec with ShouldMatchers {

  behavior of "A Vec2[Float]"

  it should "create properly and return values properly" in {
    val vec = new Vec2[Float](10f, 10f)
    val (tx, ty) = vec.toTuple
    tx should be (10f)
    ty should be (10f)
    vec.x should be (10f)
    vec.y should be (10f)
  }

  it should "do standard arithmetic" in {
    val vec0 = new Vec2[Float](16, 16)
    val vec1 = new Vec2[Float](2, 2)
    assert((vec0 + vec1) == new Vec2[Float](18, 18), "Addition did not work")
    assert((vec0 - vec1) == new Vec2[Float](14, 14), "Subtraction did not work")
    assert((vec0 * vec1) == new Vec2[Float](32, 32), "Multiplication did not work")
    assert((vec0 / vec1) == new Vec2[Float](8, 8), "Division did not work")
  }

  it should "be immutable" in {
    val vec0 = new Vec2[Float](10, 10)
    val vec1 = new Vec2[Float](10, 10)
    val result = vec0 + vec1
    assert(result ne vec0, it + " isn't immutable!")
  }

  def testNorm(vec: Vec2[Float], expected: Vec2[Float]) = {
    assert((vec.normalized) == expected, s"Normalization did not work: $vec -> $expected (expected)")
  }

  it should "normalize properly" in {
    testNorm(new Vec2[Float](5, 0), new Vec2[Float](1, 0))
    testNorm(new Vec2[Float](0, 5), new Vec2[Float](0, 1))
  }

  "A Vec2[String]" should "do very, very fun stuff!!! :D" in {
    val vec = new Vec2[String]("x", "y")(org.matheusdev.numerics.StringIsMathFractional.StringMathFractional)
    (vec.normalized) should be
      ("Vec2(x / sqrt(x * x + y * y), y / sqrt(x * x + y * y))")
    ((vec.normalized * "scale").length) should be
      ("sqrt(x / sqrt(x * x + y * y) * scale * x / sqrt(x * x + y * y) * scale + y / sqrt(x * x + y * y) * scale * y / sqrt(x * x + y * y) * scale)")
  }

}
