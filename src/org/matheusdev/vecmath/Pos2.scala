package org.matheusdev.vecmath

import org.matheusdev.numerics.MathFractional
import org.matheusdev.properties.Property

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/22/13
 * Time: 11:04 PM
 */
case class Pos2[T](var x: T, var y: T)(implicit mathN: MathFractional[T]) {
  import mathN.mkMathFractionalOps

  implicit def toTouple = (x, y)
  override def toString = s"Pos2($x, $y)"

  def +(that: Pos2[T]) = new Pos2(x + that.x, y + that.y)
  def -(that: Pos2[T]) = new Pos2(x - that.x, y - that.y)
  def *(that: Pos2[T]) = new Pos2(x * that.x, y * that.y)
  def /(that: Pos2[T]) = new Pos2(x / that.x, y / that.y)

  def +(that: Vec2[T]) = new Pos2(x + that.x, y + that.y)
  def -(that: Vec2[T]) = new Pos2(x - that.x, y - that.y)
  def *(that: Vec2[T]) = new Pos2(x * that.x, y * that.y)
  def /(that: Vec2[T]) = new Pos2(x / that.x, y / that.y)

  def +=(that: Pos2[T]) = { x += that.x; y += that.y; this }
  def -=(that: Pos2[T]) = { x -= that.x; y -= that.y; this }
  def *=(that: Pos2[T]) = { x *= that.x; y *= that.y; this }
  def /=(that: Pos2[T]) = { x /= that.x; y /= that.y; this }

  def +=(that: Vec2[T]) = { x += that.x; y += that.y; this }
  def -=(that: Vec2[T]) = { x -= that.x; y -= that.y; this }
  def *=(that: Vec2[T]) = { x *= that.x; y *= that.y; this }
  def /=(that: Vec2[T]) = { x /= that.x; y /= that.y; this }
  
  def <->(that: Pos2[T]) = mathN.sqrt(distanceSq(that))
  def distanceSq(that: Pos2[T]) = {
    val dx = that.x - x
    val dy = that.y - y
    dx * dx + dy * dy
  }

  def to(that: Pos2[T]) = new Vec2[T](this, that)
  def rotationTo(that: Pos2[T]) = to(that).direction
}