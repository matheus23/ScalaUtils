package org.matheusdev.vecmath

import org.matheusdev.numerics.Floating

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 4:35 PM
 */
case class Mat3[@specialized(Float, Double) T]
  (m00: T, m01: T, m02: T,
   m10: T, m11: T, m12: T,
   m20: T, m21: T, m22: T)(implicit mathN: Floating[T]) {
  import mathN.mkFloatingOps

  def this()(implicit num: Floating[T]) = this(
    num.one,  num.zero, num.zero,
    num.zero, num.one,  num.zero,
    num.zero, num.zero, num.one)

  def apply(x: Int, y: Int) = {
    if (x == 0)
      if      (y == 0) m00
      else if (y == 1) m10
      else if (y == 2) m20
      else yErr(y)
    else if (x == 1)
      if      (y == 0) m01
      else if (y == 1) m11
      else if (y == 2) m21
      else yErr(y)
    else if (x == 2)
      if      (y == 0) m02
      else if (y == 1) m12
      else if (y == 2) m22
      else yErr(y)
    else xErr(x)
  }

  private def xErr(x: Int) = throw new IndexOutOfBoundsException(s"x index out of 3x3 matrix range: $x")
  private def yErr(y: Int) = throw new IndexOutOfBoundsException(s"y index out of 3x3 matrix range: $y")

  def transposed = Mat3(
    m00, m10, m20,
    m01, m11, m21,
    m02, m12, m22
  )

  def determinant =
    m00 * (m11 * m22 - m12 * m21) +
    m01 * (m12 * m20 - m10 * m22) +
    m02 * (m10 * m21 - m11 * m20)

  def inverted: Option[Mat3[T]] = {
    val determ = determinant
    if (determ == mathN.zero)
      None
    else {
      val invDeterm = mathN.one / determ
      Some(Mat3(
                    (m11 * m22 - m12 * m21) * invDeterm,
        mathN.negate(m10 * m22 + m12 * m20) * invDeterm,
                    (m10 * m21 - m11 * m20) * invDeterm,
        mathN.negate(m01 * m22 + m02 * m21) * invDeterm,
                    (m00 * m22 - m02 * m20) * invDeterm,
        mathN.negate(m00 * m21 + m01 * m20) * invDeterm,
                    (m01 * m12 - m02 * m11) * invDeterm,
        mathN.negate(m00 * m12 + m02 * m10) * invDeterm,
                    (m00 * m11 - m01 * m10) * invDeterm
      ))
    }
  }

  def *(mat: Mat3[T]) = Mat3(
    m00 * mat.m00 + m10 * mat.m01 + m20 * mat.m02,
    m01 * mat.m00 + m11 * mat.m01 + m21 * mat.m02,
    m02 * mat.m00 + m12 * mat.m01 + m22 * mat.m02,
    m00 * mat.m10 + m10 * mat.m11 + m20 * mat.m12,
    m01 * mat.m10 + m11 * mat.m11 + m21 * mat.m12,
    m02 * mat.m10 + m12 * mat.m11 + m22 * mat.m12,
    m00 * mat.m20 + m10 * mat.m21 + m20 * mat.m22,
    m01 * mat.m20 + m11 * mat.m21 + m21 * mat.m22,
    m02 * mat.m20 + m12 * mat.m21 + m22 * mat.m22
  )

  def *(vec: Vec3[T]) = scaled(vec.x, vec.y, vec.z)

  def +(mat: Mat3[T]) = Mat3(
    m00 + m00, m01 + m01, m02 + m02,
    m10 + m10, m11 + m11, m12 + m12,
    m20 + m20, m21 + m21, m22 + m22
  )

  def scaled(x: T, y: T, z: T) = Mat3(
    m00 * x, m01 * y, m02 * z,
    m10 * x, m11 * y, m12 * z,
    m20 * x, m21 * y, m22 * z
  )

  def unary_- = Mat3(
    -m00, -m01, -m02,
    -m10, -m11, -m12,
    -m20, -m21, -m22
  )

}
