package org.matheusdev.vecmath

import org.matheusdev.numerics.MathFractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

class Vec4[@specialized(Byte, Short, Int, Long, Float, Double) T](val x: T, val y: T, val z: T, val w: T)(implicit mathN: MathFractional[T])
    extends Ordered[Vec4[T]] {
  def this(vec: Vec4[T])(implicit num: MathFractional[T]) = this(vec.x, vec.y, vec.z, vec.w)

  import mathN.mkMathFractionalOps

  def toTuple = (x, y, z, w)
  override def toString = s"Vec4($x, $y, $z, $w)"

  def +(v: Vec4[T]) = new Vec4(x + v.x, y + v.y, z + v.z, w + v.w)
  def -(v: Vec4[T]) = new Vec4(x - v.x, y - v.y, z - v.z, w - v.w)
  def *(v: Vec4[T]) = new Vec4(x * v.x, y * v.y, z * v.z, w * v.w)
  def /(v: Vec4[T]) = new Vec4(x / v.x, y / v.y, z / v.z, w / v.w)
  def dot(v: Vec4[T]) = x * v.x + y * v.y + z * v.z + w * v.w
//  def cross(v: Vec4[T]) = new Vec4(
//    y * v.z - z * v.y,
//    v.x * z - v.z * x,
//    x * v.y - y * v.x,
//  )

  def +(v: T) = new Vec4(x + v, y + v, z + v, w + v)
  def -(v: T) = new Vec4(x - v, y - v, z - v, w - v)
  def *(v: T) = new Vec4(x * v, y * v, z * v, w * v)
  def /(v: T) = new Vec4(x / v, y / v, z / v, w / v)

  def *(mat: Mat3[T]) = new Vec4(
    mat.m00 * x + mat.m10 * y + mat.m20 * z,
    mat.m01 * x + mat.m11 * y + mat.m21 * z,
    mat.m02 * x + mat.m12 * y + mat.m22 * z,
    mathN.one
  )

  def squaredLength: T = ((x * x) + (y * y) + (z * z) + (w * w))
  def length = mathN.sqrt(squaredLength)
  def normalized = {
    val l = length
    new Vec4(x / l, y / l, z / l, w / l)
  }
  def unary_- = new Vec4(-x, -y, -z, -w)

  def compare(other: Vec4[T]) =
    mathN.tryCompare(length, other.length).get

  def ==(other: Vec4[T]) =
    x == other.x && y == other.y && z == other.z && w == other.w
}
