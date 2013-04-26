package org.matheusdev.vecmath

import scala.Fractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

abstract class Vec4[@specialized(Float, Double) T](val x: T, val y: T, val z: T, val w: T)(implicit num: Fractional[T])
    extends Ordered[Vec4[T]] {
  def this(vec: Vec4[T]) = this(vec.x, vec.y, vec.z, vec.w)

  type self
  protected def sqrt(x: T): T
  protected def newVec(x: T, y: T, z: T, w: T): self
  protected def typeName: String
  protected def fromDouble(d: Double): T

  import Numeric.Implicits._
  import num.mkNumericOps

  def toTuple = (x, y, z, w)
  override def toString = s"Vec4[$typeName]($x, $y, $z, $w)"

  def +(v: Vec4[T]) = newVec(x + v.x, y + v.y, z + v.z, w + v.w)
  def -(v: Vec4[T]) = newVec(x - v.x, y - v.y, z - v.z, w - v.w)
  def *(v: Vec4[T]) = newVec(x * v.x, y * v.y, z * v.z, w * v.w)
  def /(v: Vec4[T]) = newVec(x / v.x, y / v.y, z / v.z, w / v.w)
  def dot(v: Vec4[T]) = x * v.x + y * v.y + z * v.z + w * v.w
//  def cross(v: Vec4[T]) = newVec(
//    y * v.z - z * v.y,
//    v.x * z - v.z * x,
//    x * v.y - y * v.x,
//  )

  def +(v: T) = newVec(x + v, y + v, z + v, w + v)
  def -(v: T) = newVec(x - v, y - v, z - v, w - v)
  def *(v: T) = newVec(x * v, y * v, z * v, w * v)
  def /(v: T) = newVec(x / v, y / v, z / v, w / v)

  def *(mat: Mat3[T]) = newVec(
    mat.val00 * x + mat.val10 * y + mat.val20 * z,
    mat.val01 * x + mat.val11 * y + mat.val21 * z,
    mat.val02 * x + mat.val12 * y + mat.val22 * z,
    num.one
  )

  def squaredLength: T = ((x * x) + (y * y) + (z * z) + (w * w))
  def length = sqrt(squaredLength)
  def normalized = {
    val l = length
    newVec(x / l, y / l, z / l, w / l)
  }
  def unary_- = newVec(num negate x, num negate y, num negate z, num negate w)

  def compare(other: Vec4[T]) =
    num.tryCompare(length, other.length).get

  def ==(other: Vec4[T]) =
    x == other.x && y == other.y && z == other.z && w == other.w
}
