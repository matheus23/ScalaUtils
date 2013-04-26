package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 6:42 PM
 */
class Vec4F(x: Float, y: Float, z: Float, w: Float = 1) extends Vec4[Float](x, y, z, w) {
  type self = Vec4F
  protected def sqrt(x: Float): Float = math.sqrt(x).toFloat
  protected def newVec(x: Float, y: Float, z: Float, w: Float): self = new Vec4F(x, y, z, w)
  protected def typeName = "Float"
  protected def fromDouble(x: Double) = x.toFloat
}

object Vec4F {
  def apply(x: Float, y: Float, z: Float, w: Float = 0) = new Vec4F(x, y, z, w)
}
