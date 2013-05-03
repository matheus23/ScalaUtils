package org.matheusdev.vecmath

import scala.Fractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

abstract class Vec2[T](val x: T, val y: T)(implicit num: Fractional[T]) extends Ordered[Vec2[T]] {
  def this(vec: Vec2[T])(implicit num: Fractional[T]) = this(vec.x, vec.y)

  type self
  protected def sqrt(x: T): T
  protected def newVec(x: T, y: T): self
  protected def typeName: String
  protected def fromDouble(d: Double): T

  import Numeric.Implicits._
  import num.mkNumericOps

  def toTuple = (x, y)
  override def toString = s"Vec2[$typeName]($x, $y)"

  def +(v: Vec2[T]) = newVec(x + v.x, y + v.y)
  def -(v: Vec2[T]) = newVec(x - v.x, y - v.y)
  def *(v: Vec2[T]) = newVec(x * v.x, y * v.y)
  def /(v: Vec2[T]) = newVec(x / v.x, y / v.y)
  def dot(v: Vec2[T]) = x * v.x + y * v.y
  def cross(v: Vec2[T]) = x * v.y - y * v.x
  
  def +(v: T) = newVec(x + v, y + v)
  def -(v: T) = newVec(x - v, y - v)
  def *(v: T) = newVec(x * v, y * v)
  def /(v: T) = newVec(x / v, y / v)

  def squaredLength: T = ((x * x) + (y * y))
  def length = sqrt(squaredLength)
  def normalized = newVec(x / length, y / length)
  def direction: Angle = Radian(math.atan2(num.toDouble(y), num.toDouble(x)))
  def perpRight = newVec(y, num negate x)
  def perpLeft = newVec(num negate y, num negate x)
  def unary_- = newVec(num negate x, num negate y)
  def rotated(angle: Angle) = {
    val rad = angle.radian
    val c = fromDouble(math.cos(rad))
    val s = fromDouble(math.sin(rad))
    newVec(x * c - y * s, x * s + y * c)
  }

  def compare(other: Vec2[T]) =
    num.tryCompare(length, other.length).get

  def ==(other: Vec2[T]) = x == other.x && y == other.y
}
