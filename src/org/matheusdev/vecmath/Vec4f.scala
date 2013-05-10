package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 6:42 PM
 */
class Vec4f(x: Float, y: Float, z: Float, w: Float = 1) extends Vec4[Float](x, y, z, w) {
  type self = Vec4f
  protected def sqrt(x: Float): Float = math.sqrt(x).toFloat
  protected def newVec(x: Float, y: Float, z: Float, w: Float): self = new Vec4f(x, y, z, w)
  protected def typeName = "Float"
  protected def fromDouble(x: Double) = x.toFloat
}

object Vec4f {
  def apply(x: Float, y: Float, z: Float, w: Float = 0) = new Vec4f(x, y, z, w)
}
