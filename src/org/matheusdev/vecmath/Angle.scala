package org.matheusdev.vecmath

import org.matheusdev.numerics.Floating

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 10:06 PM
 */
object Angle {
  implicit def radianToDegree[T](r: Radian[T]) = r.toDegree
  implicit def degreeToRadian[T](d: Degree[T]) = d.toRadian
  implicit def angleToDegree[T](a: Angle[T]) = a.toDegree
  implicit def angleToRadian[T](a: Angle[T]) = a.toRadian
}
sealed trait Angle[T] {
  def toRadian: Radian[T]
  def toDegree: Degree[T]
  def radian = toRadian.angle
  def degree = toDegree.angle
}
case class Radian[T](angle: T)(implicit mathN: Floating[T]) extends Angle[T] {
  def toRadian = this
  def toDegree = Degree(mathN.toDegrees(angle))
}
case class Degree[T](angle: T)(implicit mathN: Floating[T]) extends Angle[T] {
  def toRadian = Radian(mathN.toRadians(angle))
  def toDegree = this
}
