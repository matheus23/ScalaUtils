package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 11:59 PM
 */
class Vec3F(x: Float, y: Float, z: Float = 0) extends Vec3[Float](x, y, z) {
  type self = Vec3F
  protected def sqrt(x: Float): Float = math.sqrt(x).toFloat
  protected def newVec(x: Float, y: Float, z: Float): self = new Vec3F(x, y, z)
  protected def typeName = "Float"
  protected def fromDouble(x: Double) = x.toFloat
}

object Vec3F {
  def apply(x: Float, y: Float, z: Float) = new Vec3F(x, y, z)
}
