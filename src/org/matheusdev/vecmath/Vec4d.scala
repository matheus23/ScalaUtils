package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 6:42 PM
 */
class Vec4d(x: Double, y: Double, z: Double, w: Double = 1) extends Vec4[Double](x, y, z, w) {
  type self = Vec4d
  protected def sqrt(x: Double): Double = math.sqrt(x)
  protected def newVec(x: Double, y: Double, z: Double, w: Double): self = new Vec4d(x, y, z, w)
  protected def typeName = "Double"
  protected def fromDouble(x: Double) = x
}

object Vec4d {
  def apply(x: Double, y: Double, z: Double, w: Double = 0) = new Vec4d(x, y, z, w)
}
