package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 10:06 PM
 */
object Angle {
  val ToDegree = (180 / math.Pi)
  val ToRadian = (math.Pi / 180)

  implicit def radianToDegree(r: Radian) = r.toDegree
  implicit def degreeToRadian(d: Degree) = d.toRadian
  implicit def angleToDegree(a: Angle) = a.toDegree
  implicit def angleToRadian(a: Angle) = a.toRadian
}
sealed trait Angle {
  def value: Double
  def toRadian: Radian
  def toDegree: Degree
  def radian = toRadian.value
  def degree = toDegree.value
}
case class Radian(angle: Double) extends Angle {
  def value = angle
  def toRadian = this
  def toDegree = Degree(angle * Angle.ToDegree)
}
case class Degree(angle: Double) extends Angle {
  def value = angle
  def toRadian = Radian(angle * Angle.ToRadian)
  def toDegree = this
}
