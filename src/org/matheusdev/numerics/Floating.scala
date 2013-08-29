package org.matheusdev.numerics

import scala.math.Numeric._

object Floating {
  trait ExtraImplicits {
    implicit def infixFloatingOps[T](x: T)(implicit num: Floating[T]): Floating[T]#FloatingOps =
      new num.FloatingOps(x)
  }
  object Implicits extends ExtraImplicits
  
  trait FloatIsFloating extends Floating[Float] {
    val Pi = math.Pi.toFloat
    val E = math.E.toFloat

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
  implicit object FloatIsFloating extends FloatIsFloating with FloatIsFractional with Ordering.FloatOrdering

  trait DoubleIsFloating extends Floating[Double] {
    val Pi = math.Pi
    val E = math.E

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
  implicit object DoubleIsFloating extends DoubleIsFloating with DoubleIsFractional with Ordering.DoubleOrdering
}

trait Floating[T] extends Fractional[T] {
  val Pi: T
  val E: T

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

  class FloatingOps(lhs: T) extends Ops(lhs) {
    def /(rhs: T) = div(lhs, rhs)
  }
  implicit def mkFloatingOps(lhs: T) =
    new FloatingOps(lhs)

}