package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 9:58 PM
 */
class Vec2D(x: Double, y: Double) extends Vec2[Double](x, y) {
  type self = Vec2D
  protected def sqrt(x: Double): Double = math.sqrt(x)
  protected def newVec(x: Double, y: Double): self = new Vec2D(x, y)
  protected def typeName = "Double"
  protected def fromDouble(x: Double) = x
}

object Vec2D {
  def apply(x: Double, y: Double) = new Vec2D(x, y)
}
