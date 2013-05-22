package org.matheusdev.numerics

import scala.math.Numeric._

object MathNumeric {
  trait ExtraImplicits {
    implicit def infixMathNumericOps[T](x: T)(implicit num: MathNumeric[T]): MathNumeric[T]#MathNumericOps = new num.MathNumericOps(x)
  }
  object Implicits extends ExtraImplicits
  
  @deprecated("This is currently a stub, only to make it possible. It casts to doubles and back to BigDecimals, which are not exact operations.")
  trait BigDecimalIsMathNumeric extends MathNumeric[BigDecimal] {
    def acos(x: BigDecimal) = BigDecimal(math.acos(x.doubleValue()))
    def asin(x: BigDecimal) = BigDecimal(math.asin(x.doubleValue()))
    def atan(x: BigDecimal) = BigDecimal(math.atan(x.doubleValue()))
    def atan2(x: BigDecimal, y: BigDecimal) = BigDecimal(math.atan2(x.doubleValue(), y.doubleValue()))
    def sin(x: BigDecimal) = BigDecimal(math.sin(x.doubleValue()))
    def sinh(x: BigDecimal) = BigDecimal(math.sinh(x.doubleValue()))
    def cos(x: BigDecimal) = BigDecimal(math.cos(x.doubleValue()))
    def cosh(x: BigDecimal) = BigDecimal(math.cosh(x.doubleValue()))
    def tan(x: BigDecimal) = BigDecimal(math.tan(x.doubleValue()))
    def tanh(x: BigDecimal) = BigDecimal(math.tanh(x.doubleValue()))
    def cbrt(x: BigDecimal) = BigDecimal(math.cbrt(x.doubleValue()))
    def ceil(x: BigDecimal) = BigDecimal(math.ceil(x.doubleValue()))
    def exp(x: BigDecimal) = BigDecimal(math.exp(x.doubleValue()))
    def expm1(x: BigDecimal) = BigDecimal(math.expm1(x.doubleValue()))
    def floor(x: BigDecimal) = BigDecimal(math.floor(x.doubleValue()))
    def hypot(x: BigDecimal, y: BigDecimal) = BigDecimal(math.hypot(x.doubleValue(), y.doubleValue()))
    def log(x: BigDecimal) = BigDecimal(math.log(x.doubleValue()))
    def log10(x: BigDecimal) = BigDecimal(math.log10(x.doubleValue()))
    def log1p(x: BigDecimal) = BigDecimal(math.log1p(x.doubleValue()))
    def pow(x: BigDecimal, y: BigDecimal) = BigDecimal(math.pow(x.doubleValue(), y.doubleValue()))
    def rint(x: BigDecimal) = BigDecimal(math.rint(x.doubleValue()))
    def round(x: BigDecimal) = x.round(x.mc)
    def sqrt(x: BigDecimal) = BigDecimal(math.sqrt(x.doubleValue()))
    def toDegrees(x: BigDecimal) = x * BigDecimalIsMathNumeric.toDegree
    def toRadians(x: BigDecimal) = x * BigDecimalIsMathNumeric.toRadians
    def ulp(x: BigDecimal) = x.ulp
    
    def fromByte(x: Byte) = BigDecimal(x)
    def fromShort(x: Short) = BigDecimal(x)
    def fromChar(x: Char) = BigDecimal(x)
    def fromLong(x: Long) = BigDecimal(x)
    def fromFloat(x: Float) = BigDecimal(x)
    def fromDouble(x: Double) = BigDecimal(x)
  }
  implicit object BigDecimalIsMathNumeric extends BigDecimalIsMathNumeric with BigDecimalIsFractional with Ordering.BigDecimalOrdering {
    private val toDegree = BigDecimal(180) / BigDecimal(math.Pi)
    private val toRadians = BigDecimal(math.Pi) / BigDecimal(180)
  }
  
  trait FloatIsMathNumeric extends MathNumeric[Float] {
    def acos(x: Float) = math.acos(x).toFloat
    def asin(x: Float) = math.asin(x).toFloat
    def atan(x: Float) = math.atan(x).toFloat
    def atan2(x: Float, y: Float) = math.atan2(x, y).toFloat
    def sin(x: Float) = math.sin(x).toFloat
    def sinh(x: Float) = math.sinh(x).toFloat
    def cos(x: Float) = math.cos(x).toFloat
    def cosh(x: Float) = math.cosh(x).toFloat
    def tan(x: Float) = math.tan(x).toFloat
    def tanh(x: Float) = math.tanh(x).toFloat
    def cbrt(x: Float) = math.cbrt(x).toFloat
    def ceil(x: Float) = math.ceil(x).toFloat
    def exp(x: Float) = math.exp(x).toFloat
    def expm1(x: Float) = math.expm1(x).toFloat
    def floor(x: Float) = math.floor(x).toFloat
    def hypot(x: Float, y: Float) = math.hypot(x, y).toFloat
    def log(x: Float) = math.log(x).toFloat
    def log10(x: Float) = math.log10(x).toFloat
    def log1p(x: Float) = math.log1p(x).toFloat
    def pow(x: Float, y: Float) = math.pow(x, y).toFloat
    def rint(x: Float) = math.rint(x).toFloat
    def round(x: Float) = math.round(x).toFloat
    def sqrt(x: Float) = math.sqrt(x).toFloat
    def toDegrees(x: Float) = math.toDegrees(x).toFloat
    def toRadians(x: Float) = math.toRadians(x).toFloat
    def ulp(x: Float) = math.ulp(x).toFloat

    def fromByte(x: Byte) = x.toFloat
    def fromShort(x: Short) = x.toFloat
    def fromChar(x: Char) = x.toFloat
    def fromLong(x: Long) = x.toFloat
    def fromFloat(x: Float) = x
    def fromDouble(x: Double) = x.toFloat
  }
  implicit object FloatIsMathNumeric extends FloatIsMathNumeric with FloatIsFractional with Ordering.FloatOrdering

  trait DoubleIsMathNumeric extends MathNumeric[Double] {
    def acos(x: Double) = math.acos(x)
    def asin(x: Double) = math.asin(x)
    def atan(x: Double) = math.atan(x)
    def atan2(x: Double, y: Double) = math.atan2(x, y)
    def sin(x: Double) = math.sin(x)
    def sinh(x: Double) = math.sinh(x)
    def cos(x: Double) = math.cos(x)
    def cosh(x: Double) = math.cosh(x)
    def tan(x: Double) = math.tan(x)
    def tanh(x: Double) = math.tanh(x)
    def cbrt(x: Double) = math.cbrt(x)
    def ceil(x: Double) = math.ceil(x)
    def exp(x: Double) = math.exp(x)
    def expm1(x: Double) = math.expm1(x)
    def floor(x: Double) = math.floor(x)
    def hypot(x: Double, y: Double) = math.hypot(x, y)
    def log(x: Double) = math.log(x)
    def log10(x: Double) = math.log10(x)
    def log1p(x: Double) = math.log1p(x)
    def pow(x: Double, y: Double) = math.pow(x, y)
    def rint(x: Double) = math.rint(x)
    def round(x: Double) = math.round(x)
    def sqrt(x: Double) = math.sqrt(x)
    def toDegrees(x: Double) = math.toDegrees(x)
    def toRadians(x: Double) = math.toRadians(x)
    def ulp(x: Double) = math.ulp(x)

    def fromByte(x: Byte) = x.toDouble
    def fromShort(x: Short) = x.toDouble
    def fromChar(x: Char) = x.toDouble
    def fromLong(x: Long) = x.toDouble
    def fromFloat(x: Float) = x.toDouble
    def fromDouble(x: Double) = x
  }
  implicit object DoubleIsMathNumeric extends DoubleIsMathNumeric with DoubleIsFractional with Ordering.DoubleOrdering
  
  trait IntIsMathNumeric extends MathNumeric[Int] {
    def div(x: Int, y: Int) = (x / y)
    def acos(x: Int) = math.acos(x).toInt
    def asin(x: Int) = math.asin(x).toInt
    def atan(x: Int) = math.atan(x).toInt
    def atan2(x: Int, y: Int) = math.atan2(x, y).toInt
    def sin(x: Int) = math.sin(x).toInt
    def sinh(x: Int) = math.sinh(x).toInt
    def cos(x: Int) = math.cos(x).toInt
    def cosh(x: Int) = math.cosh(x).toInt
    def tan(x: Int) = math.tan(x).toInt
    def tanh(x: Int) = math.tanh(x).toInt
    def cbrt(x: Int) = math.cbrt(x).toInt
    def ceil(x: Int) = math.ceil(x).toInt
    def exp(x: Int) = math.exp(x).toInt
    def expm1(x: Int) = math.expm1(x).toInt
    def floor(x: Int) = math.floor(x).toInt
    def hypot(x: Int, y: Int) = math.hypot(x, y).toInt
    def log(x: Int) = math.log(x).toInt
    def log10(x: Int) = math.log10(x).toInt
    def log1p(x: Int) = math.log1p(x).toInt
    def pow(x: Int, y: Int) = math.pow(x, y).toInt
    def rint(x: Int) = math.rint(x).toInt
    def round(x: Int) = math.round(x)
    def sqrt(x: Int) = math.sqrt(x).toInt
    def toDegrees(x: Int) = math.toDegrees(x).toInt
    def toRadians(x: Int) = math.toRadians(x).toInt
    def ulp(x: Int) = math.ulp(x).toInt

    def fromByte(x: Byte) = x.toInt
    def fromShort(x: Short) = x.toInt
    def fromChar(x: Char) = x.toInt
    def fromLong(x: Long) = x.toInt
    def fromFloat(x: Float) = x.toInt
    def fromDouble(x: Double) = x.toInt
  }
  implicit object IntIsMathNumeric extends IntIsMathNumeric with IntIsIntegral with Ordering.IntOrdering

  trait LongIsMathNumeric extends MathNumeric[Long] {
    def div(x: Long, y: Long) = (x / y)
    def acos(x: Long) = math.acos(x).toLong
    def asin(x: Long) = math.asin(x).toLong
    def atan(x: Long) = math.atan(x).toLong
    def atan2(x: Long, y: Long) = math.atan2(x, y).toLong
    def sin(x: Long) = math.sin(x).toLong
    def sinh(x: Long) = math.sinh(x).toLong
    def cos(x: Long) = math.cos(x).toLong
    def cosh(x: Long) = math.cosh(x).toLong
    def tan(x: Long) = math.tan(x).toLong
    def tanh(x: Long) = math.tanh(x).toLong
    def cbrt(x: Long) = math.cbrt(x).toLong
    def ceil(x: Long) = math.ceil(x).toLong
    def exp(x: Long) = math.exp(x).toLong
    def expm1(x: Long) = math.expm1(x).toLong
    def floor(x: Long) = math.floor(x).toLong
    def hypot(x: Long, y: Long) = math.hypot(x, y).toLong
    def log(x: Long) = math.log(x).toLong
    def log10(x: Long) = math.log10(x).toLong
    def log1p(x: Long) = math.log1p(x).toLong
    def pow(x: Long, y: Long) = math.pow(x, y).toLong
    def rint(x: Long) = math.rint(x).toLong
    def round(x: Long) = math.round(x).toLong
    def sqrt(x: Long) = math.sqrt(x).toLong
    def toDegrees(x: Long) = math.toDegrees(x).toLong
    def toRadians(x: Long) = math.toRadians(x).toLong
    def ulp(x: Long) = math.ulp(x).toLong

    def fromByte(x: Byte) = x.toLong
    def fromShort(x: Short) = x.toLong
    def fromChar(x: Char) = x.toLong
    def fromLong(x: Long) = x
    def fromFloat(x: Float) = x.toLong
    def fromDouble(x: Double) = x.toLong
  }
  implicit object LongIsMathNumeric extends LongIsMathNumeric with LongIsIntegral with Ordering.LongOrdering

  trait ShortIsMathNumeric extends MathNumeric[Short] {
    def div(x: Short, y: Short) = (x / y).toShort
    def acos(x: Short) = math.acos(x).toShort
    def asin(x: Short) = math.asin(x).toShort
    def atan(x: Short) = math.atan(x).toShort
    def atan2(x: Short, y: Short) = math.atan2(x, y).toShort
    def sin(x: Short) = math.sin(x).toShort
    def sinh(x: Short) = math.sinh(x).toShort
    def cos(x: Short) = math.cos(x).toShort
    def cosh(x: Short) = math.cosh(x).toShort
    def tan(x: Short) = math.tan(x).toShort
    def tanh(x: Short) = math.tanh(x).toShort
    def cbrt(x: Short) = math.cbrt(x).toShort
    def ceil(x: Short) = math.ceil(x).toShort
    def exp(x: Short) = math.exp(x).toShort
    def expm1(x: Short) = math.expm1(x).toShort
    def floor(x: Short) = math.floor(x).toShort
    def hypot(x: Short, y: Short) = math.hypot(x, y).toShort
    def log(x: Short) = math.log(x).toShort
    def log10(x: Short) = math.log10(x).toShort
    def log1p(x: Short) = math.log1p(x).toShort
    def pow(x: Short, y: Short) = math.pow(x, y).toShort
    def rint(x: Short) = math.rint(x).toShort
    def round(x: Short) = math.round(x).toShort
    def sqrt(x: Short) = math.sqrt(x).toShort
    def toDegrees(x: Short) = math.toDegrees(x).toShort
    def toRadians(x: Short) = math.toRadians(x).toShort
    def ulp(x: Short) = math.ulp(x).toShort

    def fromByte(x: Byte) = x.toShort
    def fromShort(x: Short) = x
    def fromChar(x: Char) = x.toShort
    def fromLong(x: Long) = x.toShort
    def fromFloat(x: Float) = x.toShort
    def fromDouble(x: Double) = x.toShort
  }
  implicit object ShortIsMathNumeric extends ShortIsMathNumeric with ShortIsIntegral with Ordering.ShortOrdering

  trait CharIsMathNumeric extends MathNumeric[Char] {
    def div(x: Char, y: Char) = (x / y).toChar
    def acos(x: Char) = math.acos(x).toChar
    def asin(x: Char) = math.asin(x).toChar
    def atan(x: Char) = math.atan(x).toChar
    def atan2(x: Char, y: Char) = math.atan2(x, y).toChar
    def sin(x: Char) = math.sin(x).toChar
    def sinh(x: Char) = math.sinh(x).toChar
    def cos(x: Char) = math.cos(x).toChar
    def cosh(x: Char) = math.cosh(x).toChar
    def tan(x: Char) = math.tan(x).toChar
    def tanh(x: Char) = math.tanh(x).toChar
    def cbrt(x: Char) = math.cbrt(x).toChar
    def ceil(x: Char) = math.ceil(x).toChar
    def exp(x: Char) = math.exp(x).toChar
    def expm1(x: Char) = math.expm1(x).toChar
    def floor(x: Char) = math.floor(x).toChar
    def hypot(x: Char, y: Char) = math.hypot(x, y).toChar
    def log(x: Char) = math.log(x).toChar
    def log10(x: Char) = math.log10(x).toChar
    def log1p(x: Char) = math.log1p(x).toChar
    def pow(x: Char, y: Char) = math.pow(x, y).toChar
    def rint(x: Char) = math.rint(x).toChar
    def round(x: Char) = math.round(x).toChar
    def sqrt(x: Char) = math.sqrt(x).toChar
    def toDegrees(x: Char) = math.toDegrees(x).toChar
    def toRadians(x: Char) = math.toRadians(x).toChar
    def ulp(x: Char) = math.ulp(x).toChar

    def fromByte(x: Byte) = x.toChar
    def fromShort(x: Short) = x.toChar
    def fromChar(x: Char) = x
    def fromLong(x: Long) = x.toChar
    def fromFloat(x: Float) = x.toChar
    def fromDouble(x: Double) = x.toChar
  }
  implicit object CharIsMathNumeric extends CharIsMathNumeric with CharIsIntegral with Ordering.CharOrdering

  trait ByteIsMathNumeric extends MathNumeric[Byte] {
    def div(x: Byte, y: Byte) = (x / y).toByte
    def acos(x: Byte) = math.acos(x).toByte
    def asin(x: Byte) = math.asin(x).toByte
    def atan(x: Byte) = math.atan(x).toByte
    def atan2(x: Byte, y: Byte) = math.atan2(x, y).toByte
    def sin(x: Byte) = math.sin(x).toByte
    def sinh(x: Byte) = math.sinh(x).toByte
    def cos(x: Byte) = math.cos(x).toByte
    def cosh(x: Byte) = math.cosh(x).toByte
    def tan(x: Byte) = math.tan(x).toByte
    def tanh(x: Byte) = math.tanh(x).toByte
    def cbrt(x: Byte) = math.cbrt(x).toByte
    def ceil(x: Byte) = math.ceil(x).toByte
    def exp(x: Byte) = math.exp(x).toByte
    def expm1(x: Byte) = math.expm1(x).toByte
    def floor(x: Byte) = math.floor(x).toByte
    def hypot(x: Byte, y: Byte) = math.hypot(x, y).toByte
    def log(x: Byte) = math.log(x).toByte
    def log10(x: Byte) = math.log10(x).toByte
    def log1p(x: Byte) = math.log1p(x).toByte
    def pow(x: Byte, y: Byte) = math.pow(x, y).toByte
    def rint(x: Byte) = math.rint(x).toByte
    def round(x: Byte) = math.round(x).toByte
    def sqrt(x: Byte) = math.sqrt(x).toByte
    def toDegrees(x: Byte) = math.toDegrees(x).toByte
    def toRadians(x: Byte) = math.toRadians(x).toByte
    def ulp(x: Byte) = math.ulp(x).toByte

    def fromByte(x: Byte) = x
    def fromShort(x: Short) = x.toByte
    def fromChar(x: Char) = x.toByte
    def fromLong(x: Long) = x.toByte
    def fromFloat(x: Float) = x.toByte
    def fromDouble(x: Double) = x.toByte
  }
  implicit object ByteIsMathNumeric extends ByteIsMathNumeric with ByteIsIntegral with Ordering.ByteOrdering
}

trait MathNumeric[T] extends Numeric[T] {
  def div(x: T, y: T): T
  def acos(x: T): T
  def asin(x: T): T
  def atan(x: T): T
  def atan2(x: T, y: T): T
  def sin(x: T): T
  def sinh(x: T): T
  def cos(x: T): T
  def cosh(x: T): T
  def tan(x: T): T
  def tanh(x: T): T
  def cbrt(x: T): T
  def ceil(x: T): T
  def exp(x: T): T
  def expm1(x: T): T
  def floor(x: T): T
  def hypot(x: T, y: T): T
  def log(x: T): T
  def log10(x: T): T
  def log1p(x: T): T
  def pow(x: T, y: T): T
  def rint(x: T): T
  def round(x: T): T
  def sqrt(x: T): T
  def toDegrees(x: T): T
  def toRadians(x: T): T
  def ulp(x: T): T
  
  def fromByte(x: Byte): T
  def fromShort(x: Short): T
  def fromChar(x: Char): T
  def fromLong(x: Long): T
  def fromFloat(x: Float): T
  def fromDouble(x: Double): T

  class MathNumericOps(lhs: T) extends Ops(lhs) {
    def /(rhs: T) = div(lhs, rhs)
  }
  implicit def mkMathNumericOps(lhs: T) =
    new MathNumericOps(lhs)

}