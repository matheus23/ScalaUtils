package org.matheusdev.vecmath

import org.matheusdev.numerics.MathFractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

case class Vec2[@specialized(Float, Double) T](x: T, y: T)
                                              (implicit mathN: MathFractional[T]) extends Ordered[Vec2[T]] {
  def this(vec: Vec2[T])
          (implicit mathN: MathFractional[T]) =
    this(vec.x, vec.y)

  def this(from: Pos2[T], to: Pos2[T])
          (implicit mathN: MathFractional[T]) =
    this(mathN.minus(to.x, from.x), mathN.minus(to.y, from.y))

  import mathN.mkMathFractionalOps

  def toTuple = (x, y)
  override def toString = s"Vec2($x, $y)"

  def +(v: Vec2[T]) = Vec2(x + v.x, y + v.y)
  def -(v: Vec2[T]) = Vec2(x - v.x, y - v.y)
  def *(v: Vec2[T]) = Vec2(x * v.x, y * v.y)
  def /(v: Vec2[T]) = Vec2(x / v.x, y / v.y)
  def dot(v: Vec2[T]) = x * v.x + y * v.y
  def cross(v: Vec2[T]) = x * v.y - y * v.x
  
  def +(v: T) = Vec2(x + v, y + v)
  def -(v: T) = Vec2(x - v, y - v)
  def *(v: T) = Vec2(x * v, y * v)
  def /(v: T) = Vec2(x / v, y / v)

  def squaredLength: T = ((x * x) + (y * y))
  def length = mathN.sqrt(squaredLength)
  def normalized = Vec2(x / length, y / length)
  def direction: Angle[T] = Radian(mathN.atan2(y, x))
  def perpRight = Vec2(y, mathN negate x)
  def perpLeft = Vec2(mathN negate y, mathN negate x)
  def unary_- = Vec2(mathN negate x, mathN negate y)
  def rotated(angle: Angle[T]) = {
    val rad = angle.radian
    val c = mathN.cos(rad)
    val s = mathN.sin(rad)
    Vec2(x * c - y * s, x * s + y * c)
  }

  def compare(other: Vec2[T]) =
    mathN.tryCompare(length, other.length).get
}
