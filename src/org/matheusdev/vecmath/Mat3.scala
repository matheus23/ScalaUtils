package org.matheusdev.vecmath

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/26/13
 * Time: 4:35 PM
 */
abstract class Mat3[T](private val v: IndexedSeq[T])(implicit num: Fractional[T]) {
  if (v.length != 9)
    throw new IllegalArgumentException(s"Cannot create 3x3 matrix with other than 9 values: $v")

  type self

  def this(vs: T*) = this(vs.toIndexedSeq)
  def this(m00: T, m01: T, m02: T,
           m10: T, m11: T, m12: T,
           m20: T, m21: T, m22: T) =
    this(IndexedSeq(
      m00, m01, m02,
      m10, m11, m12,
      m20, m21, m22
    ))
  def this() = this(
    num.one,  num.zero, num.zero,
    num.zero, num.one,  num.zero,
    num.zero, num.zero, num.one)

  protected def newMat(vs: Seq[T]): self
  protected def newMat(vs: T*) = newMat(vs.toIndexedSeq)

  def val00 = v(Mat3.m00)
  def val01 = v(Mat3.m01)
  def val02 = v(Mat3.m02)

  def val10 = v(Mat3.m10)
  def val11 = v(Mat3.m11)
  def val12 = v(Mat3.m12)

  def val20 = v(Mat3.m20)
  def val21 = v(Mat3.m21)
  def val22 = v(Mat3.m22)

  def apply(ind: Int) = apply(ind, 0)
  def apply(x: Int, y: Int) = v.applyOrElse(y * 3 + x,
    ind => throw new IndexOutOfBoundsException(s"Index out of 3x3 bounds: $ind (x: $x, y: $y)"))

  def transposed = newMat(IndexedSeq(
    val00, val10, val20,
    val01, val11, val21,
    val02, val12, val22
  ))

  import Numeric.Implicits._
  import num.mkNumericOps

  def determinant =
    val00 * (val11 * val22 - val12 * val21) +
    val01 * (val12 * val20 - val10 * val22) +
    val02 * (val10 * val21 - val11 * val20)

  def inverted : Option[self] = {
    val determ = determinant
    if (determ == num.zero)
      None
    else {
      val invDeterm = num.one / determ
      Some(newMat(IndexedSeq(
                  (val11 * val22 - val12 * val21) * invDeterm,
        num.negate(val10 * val22 + val12 * val20) * invDeterm,
                  (val10 * val21 - val11 * val20) * invDeterm,
        num.negate(val01 * val22 + val02 * val21) * invDeterm,
                  (val00 * val22 - val02 * val20) * invDeterm,
        num.negate(val00 * val21 + val01 * val20) * invDeterm,
                  (val01 * val12 - val02 * val11) * invDeterm,
        num.negate(val00 * val12 + val02 * val10) * invDeterm,
                  (val00 * val11 - val01 * val10) * invDeterm
      )))
    }
  }

  def *(mat: Mat3[T]) = newMat(IndexedSeq(
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

  def +(mat: Mat3[T]) = newMat(for (i <- v.indices) yield v(i) * mat(i))
  def +(mat: Mat3[T]) = newMat(for (i <- v.indices) yield v(i) * mat(i))

  def scaled(x: T, y: T, z: T) = newMat(IndexedSeq(
    val00 * x, val01 * y, val02 * z,
    val10 * x, val11 * y, val12 * z,
    val20 * x, val21 * y, val22 * z
  ))

  def unary_- = newMat(v map (value => num.negate(value)))

}

object Mat3 extends Enumeration {
  val m00 = 0
  val m01 = 1
  val m02 = 2
  val m10 = 3
  val m11 = 4
  val m12 = 5
  val m20 = 6
  val m21 = 7
  val m22 = 8
}
