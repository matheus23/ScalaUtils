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
}
sealed trait Angle {
  def value: Double
  def toRadian: Double
  def toDegree: Double
}
case class Radian(angle: Double) extends Angle {
  def value = angle
  def toRadian = angle
  def toDegree = angle * Angle.ToDegree
}
case class Degree(angle: Double) extends Angle {
  def value = angle
  def toRadian = angle * Angle.ToRadian
  def toDegree = angle
}
