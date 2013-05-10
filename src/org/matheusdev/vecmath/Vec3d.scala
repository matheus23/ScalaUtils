package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 12:00 AM
 */
class Vec3d(x: Double, y: Double, z: Double) extends Vec3[Double](x, y, z) {
  type self = Vec3d
  protected def sqrt(x: Double): Double = math.sqrt(x)
  protected def newVec(x: Double, y: Double, z: Double): self = new Vec3d(x, y, z)
  protected def typeName = "Double"
  protected def fromDouble(x: Double) = x
}

object Vec3d {
  def apply(x: Double, y: Double, z: Double) = new Vec3d(x, y, z)
}
