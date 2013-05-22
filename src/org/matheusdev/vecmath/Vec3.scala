package org.matheusdev.vecmath

import org.matheusdev.numerics.MathFractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:34 PM
 */

case class Vec3[@specialized(Float, Double) T](x: T, y: T, z: T)(implicit mathN: MathFractional[T])
    extends Ordered[Vec3[T]] {
  def this(vec: Vec3[T])(implicit num: MathFractional[T]) = this(vec.x, vec.y, vec.z)

  import mathN.mkMathFractionalOps

  def toTuple = (x, y, z)
  override def toString = s"Vec3($x, $y, $z)"

  def +(v: Vec3[T]) = Vec3(x + v.x, y + v.y, z + v.z)
  def -(v: Vec3[T]) = Vec3(x - v.x, y - v.y, z - v.z)
  def *(v: Vec3[T]) = Vec3(x * v.x, y * v.y, z * v.z)
  def /(v: Vec3[T]) = Vec3(x / v.x, y / v.y, z / v.z)
  def dot(v: Vec3[T]) = x * v.x + y * v.y + z * v.z
  def cross(v: Vec3[T]) = Vec3(
    y * v.z - z * v.y,
    v.x * z - v.z * x,
    x * v.y - y * v.x
  )

  def +(v: T) = Vec3(x + v, y + v, z + v)
  def -(v: T) = Vec3(x - v, y - v, z - v)
  def *(v: T) = Vec3(x * v, y * v, z * v)
  def /(v: T) = Vec3(x / v, y / v, z / v)

  def squaredLength: T = ((x * x) + (y * y) + (z * z))
  def length = mathN.sqrt(squaredLength)
  def normalized = {
    val l = length
    Vec3(x / l, y / l, z / l)
  }
  def unary_- = Vec3(-x, -y, -z)

  def compare(other: Vec3[T]) =
    mathN.tryCompare(length, other.length).get

  def ==(other: Vec3[T]) =
    x == other.x && y == other.y && z == other.z
}
