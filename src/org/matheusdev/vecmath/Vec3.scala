package org.matheusdev.vecmath

import scala.Fractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

abstract class Vec3[@specialized(Float, Double) T](val x: T, val y: T, val z: T)(implicit num: Fractional[T])
    extends Ordered[Vec3[T]] {
  def this(vec: Vec3[T])(implicit num: Fractional[T]) = this(vec.x, vec.y, vec.z)

  type self
  protected def sqrt(x: T): T
  protected def newVec(x: T, y: T, z: T): self
  protected def typeName: String
  protected def fromDouble(d: Double): T

  import Numeric.Implicits._
  import num.mkNumericOps

  def toTuple = (x, y, z)
  override def toString = s"Vec3[$typeName]($x, $y, $z)"

  def +(v: Vec3[T]) = newVec(x + v.x, y + v.y, z + v.z)
  def -(v: Vec3[T]) = newVec(x - v.x, y - v.y, z - v.z)
  def *(v: Vec3[T]) = newVec(x * v.x, y * v.y, z * v.z)
  def /(v: Vec3[T]) = newVec(x / v.x, y / v.y, z / v.z)
  def dot(v: Vec3[T]) = x * v.x + y * v.y + z * v.z
  def cross(v: Vec3[T]) = newVec(
    y * v.z - z * v.y,
    v.x * z - v.z * x,
    x * v.y - y * v.x
  )

  def +(v: T) = newVec(x + v, y + v, z + v)
  def -(v: T) = newVec(x - v, y - v, z - v)
  def *(v: T) = newVec(x * v, y * v, z * v)
  def /(v: T) = newVec(x / v, y / v, z / v)

  def squaredLength: T = ((x * x) + (y * y) + (z * z))
  def length = sqrt(squaredLength)
  def normalized = {
    val l = length
    newVec(x / l, y / l, z / l)
  }
  def unary_- = newVec(num negate x, num negate y, num negate z)

  def compare(other: Vec3[T]) =
    num.tryCompare(length, other.length).get

  def ==(other: Vec3[T]) =
    x == other.x && y == other.y && z == other.z
}
