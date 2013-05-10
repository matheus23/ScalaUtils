package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 7:06 PM
 */
class Mat3d(v: IndexedSeq[Double]) extends Mat3[Double] {
  def this(vs: Double*) = this(vs.toIndexedSeq)
  def this(m00: Double, m01: Double, m02: Double,
           m10: Double, m11: Double, m12: Double,
           m20: Double, m21: Double, m22: Double) =
    this(IndexedSeq(
      m00, m01, m02,
      m10, m11, m12,
      m20, m21, m22
    ))
  def this() = this(
    1, 0, 0,
    0, 1, 0,
    0, 0, 1)

  type self = Mat3d
  protected def newMat(vs: IndexedSeq[Double]) = new Mat3d(vs)
}
