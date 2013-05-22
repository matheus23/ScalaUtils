package org.matheusdev.vecmath

import org.matheusdev.numerics.MathNumeric

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

class Vec2[@specialized(Byte, Short, Int, Long, Float, Double) T](val x: T, val y: T)(implicit mathN: MathNumeric[T]) extends Ordered[Vec2[T]] {
  def this(vec: Vec2[T])(implicit mathN: MathNumeric[T]) = this(vec.x, vec.y)

  import mathN.mkMathNumericOps

  def toTuple = (x, y)
  override def toString = s"Vec2($x, $y)"

  def +(v: Vec2[T]) = new Vec2(x + v.x, y + v.y)
  def -(v: Vec2[T]) = new Vec2(x - v.x, y - v.y)
  def *(v: Vec2[T]) = new Vec2(x * v.x, y * v.y)
  def /(v: Vec2[T]) = new Vec2(x / v.x, y / v.y)
  def dot(v: Vec2[T]) = x * v.x + y * v.y
  def cross(v: Vec2[T]) = x * v.y - y * v.x
  
  def +(v: T) = new Vec2(x + v, y + v)
  def -(v: T) = new Vec2(x - v, y - v)
  def *(v: T) = new Vec2(x * v, y * v)
  def /(v: T) = new Vec2(x / v, y / v)

  def squaredLength: T = ((x * x) + (y * y))
  def length = mathN.sqrt(squaredLength)
  def normalized = new Vec2(x / length, y / length)
  def direction: Angle[T] = Radian(mathN.atan2(y, x))
  def perpRight = new Vec2(y, mathN negate x)
  def perpLeft = new Vec2(mathN negate y, mathN negate x)
  def unary_- = new Vec2(mathN negate x, mathN negate y)
  def rotated(angle: Angle[T]) = {
    val rad = angle.radian
    val c = mathN.cos(rad)
    val s = mathN.sin(rad)
    new Vec2(x * c - y * s, x * s + y * c)
  }

  def compare(other: Vec2[T]) =
    mathN.tryCompare(length, other.length).get

  def ==(other: Vec2[T]) = x == other.x && y == other.y
}
