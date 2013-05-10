package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/25/13
 * Time: 11:59 PM
 */
class Vec3f(x: Float, y: Float, z: Float = 0) extends Vec3[Float](x, y, z) {
  type self = Vec3f
  protected def sqrt(x: Float): Float = math.sqrt(x).toFloat
  protected def newVec(x: Float, y: Float, z: Float): self = new Vec3f(x, y, z)
  protected def typeName = "Float"
  protected def fromDouble(x: Double) = x.toFloat
}

object Vec3f {
  def apply(x: Float, y: Float, z: Float) = new Vec3f(x, y, z)
}
