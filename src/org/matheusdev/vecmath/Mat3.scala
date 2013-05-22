package org.matheusdev.vecmath

import org.matheusdev.numerics.MathFractional

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 4:35 PM
 */
class Mat3[@specialized(Float, Double) T]
  (val m00: T, val m01: T, val m02: T,
   val m10: T, val m11: T, val m12: T,
   val m20: T, val m21: T, val m22: T)(implicit mathN: MathFractional[T]) {
  import mathN.mkMathFractionalOps

  def this()(implicit num: MathFractional[T]) = this(
    num.one,  num.zero, num.zero,
    num.zero, num.one,  num.zero,
    num.zero, num.zero, num.one)

  def apply(x: Int, y: Int) = {
    x match {
      case 0 => y match {
        case 0 => m00
        case 1 => m10
        case 2 => m20
        case _ => yOut(y)
      }
      case 1 => y match {
        case 0 => m01
        case 1 => m11
        case 2 => m21
        case _ => yOut(y)
      }
      case 2 => y match {
        case 0 => m02
        case 1 => m12
        case 2 => m22
        case _ => yOut(y)
      }
      case _ => xOut(x)
    }
  }

  private def xOut(x: Int) = throw new IndexOutOfBoundsException(s"x index out of 3x3 matrix range: $x")
  private def yOut(y: Int) = throw new IndexOutOfBoundsException(s"y index out of 3x3 matrix range: $y")

  def transposed = new Mat3(
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
      Some(new Mat3(
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

  def *(mat: Mat3[T]) = new Mat3(
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

  def +(mat: Mat3[T]) = new Mat3(
    m00 + m00, m01 + m01, m02 + m02,
    m10 + m10, m11 + m11, m12 + m12,
    m20 + m20, m21 + m21, m22 + m22
  )

  def scaled(x: T, y: T, z: T) = new Mat3(
    m00 * x, m01 * y, m02 * z,
    m10 * x, m11 * y, m12 * z,
    m20 * x, m21 * y, m22 * z
  )

  def unary_- = new Mat3(
    -m00, -m01, -m02,
    -m10, -m11, -m12,
    -m20, -m21, -m22
  )

}
