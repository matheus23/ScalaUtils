package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 8:53 PM
 */
class Vec2f(x: Float, y: Float) extends Vec2[Float](x, y) {
  type self = Vec2f
  protected def sqrt(x: Float): Float = math.sqrt(x).toFloat
  protected def newVec(x: Float, y: Float): self = new Vec2f(x, y)
  protected def typeName = "Float"
  protected def fromDouble(x: Double) = x.toFloat
}

object Vec2f {
  def apply(x: Float, y: Float) = new Vec2f(x, y)
}
