package org.matheusdev.vecmath

import org.matheusdev.numerics.MathNumeric

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 4:35 PM
 */
class Mat3[@specialized(Byte, Short, Int, Long, Float, Double) T](private val v: IndexedSeq[T])(implicit mathN: MathNumeric[T]) {
  if (v.length != 9)
    throw new IllegalArgumentException(s"Cannot create 3x3 matrix with other than 9 values: $v")

  import mathN.mkMathNumericOps

  def this(m00: T, m01: T, m02: T,
           m10: T, m11: T, m12: T,
           m20: T, m21: T, m22: T)
          (implicit num: MathNumeric[T]) =
    this(IndexedSeq(
      m00, m01, m02,
      m10, m11, m12,
      m20, m21, m22
    ))
  def this()(implicit num: MathNumeric[T]) = this(
    num.one,  num.zero, num.zero,
    num.zero, num.one,  num.zero,
    num.zero, num.zero, num.one)

  def val00 = v(0)
  def val01 = v(1)
  def val02 = v(2)

  def val10 = v(3)
  def val11 = v(4)
  def val12 = v(5)

  def val20 = v(6)
  def val21 = v(7)
  def val22 = v(8)

  def apply(ind: Int): T = apply(ind, 0)
  def apply(x: Int, y: Int) = {
    val index = y * 3 + x
    if (v.isDefinedAt(index))
      v(index)
    else
      throw new IndexOutOfBoundsException(s"Index out of 3x3 bounds: $index (x: $x, y: $y)")
  }

  def transposed = new Mat3(
    val00, val10, val20,
    val01, val11, val21,
    val02, val12, val22
  )

  def determinant =
    val00 * (val11 * val22 - val12 * val21) +
    val01 * (val12 * val20 - val10 * val22) +
    val02 * (val10 * val21 - val11 * val20)

  def inverted : Option[Mat3[T]] = {
    val determ = determinant
    if (determ == mathN.zero)
      None
    else {
      val invDeterm = mathN.one / determ
      Some(new Mat3(IndexedSeq(
                  (val11 * val22 - val12 * val21) * invDeterm,
        mathN.negate(val10 * val22 + val12 * val20) * invDeterm,
                  (val10 * val21 - val11 * val20) * invDeterm,
        mathN.negate(val01 * val22 + val02 * val21) * invDeterm,
                  (val00 * val22 - val02 * val20) * invDeterm,
        mathN.negate(val00 * val21 + val01 * val20) * invDeterm,
                  (val01 * val12 - val02 * val11) * invDeterm,
        mathN.negate(val00 * val12 + val02 * val10) * invDeterm,
                  (val00 * val11 - val01 * val10) * invDeterm
      )))
    }
  }

  def *(mat: Mat3[T]) = new Mat3(IndexedSeq(
    val00 * mat.val00 + val10 * mat.val01 + val20 * mat.val02,
    val01 * mat.val00 + val11 * mat.val01 + val21 * mat.val02,
    val02 * mat.val00 + val12 * mat.val01 + val22 * mat.val02,
    val00 * mat.val10 + val10 * mat.val11 + val20 * mat.val12,
    val01 * mat.val10 + val11 * mat.val11 + val21 * mat.val12,
    val02 * mat.val10 + val12 * mat.val11 + val22 * mat.val12,
    val00 * mat.val20 + val10 * mat.val21 + val20 * mat.val22,
    val01 * mat.val20 + val11 * mat.val21 + val21 * mat.val22,
    val02 * mat.val20 + val12 * mat.val21 + val22 * mat.val22
  ))

  def *(vec: Vec3[T]) = scaled(vec.x, vec.y, vec.z)

  def +(mat: Mat3[T]) = new Mat3(for (i <- v.indices) yield v(i) * mat(i))

  def scaled(x: T, y: T, z: T) = new Mat3(IndexedSeq(
    val00 * x, val01 * y, val02 * z,
    val10 * x, val11 * y, val12 * z,
    val20 * x, val21 * y, val22 * z
  ))

  def unary_- = new Mat3(v map (value => mathN.negate(value)))

}
