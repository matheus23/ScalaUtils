package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 7:01 PM
 */
class Mat3f(v: IndexedSeq[Float]) extends Mat3[Float] {
  def this(vs: Float*) = this(vs.toIndexedSeq)
  def this(m00: Float, m01: Float, m02: Float,
           m10: Float, m11: Float, m12: Float,
           m20: Float, m21: Float, m22: Float) =
    this(IndexedSeq(
      m00, m01, m02,
      m10, m11, m12,
      m20, m21, m22
    ))
  def this() = this(
    1, 0, 0,
    0, 1, 0,
    0, 0, 1)

  type self = Mat3f
  protected def newMat(vs: IndexedSeq[Float]) = new Mat3f(vs)
}
