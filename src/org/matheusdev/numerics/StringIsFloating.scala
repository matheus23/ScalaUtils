package org.matheusdev.numerics

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/22/13
 * Time: 4:18 PM
 */
trait StringIsFloating extends Floating[String] {
  val Pi = "Pi"
  val E = "E"

  def acos(x: String) = s"acos($x)"
  def asin(x: String) = s"asin($x)"
  def atan(x: String) = s"atan($x)"
  def atan2(x: String, y: String) = s"atan2($x, $y)"
  def sin(x: String) = s"sin($x)"
  def sinh(x: String) = s"sinh($x)"
  def cos(x: String) = s"cos($x)"
  def cosh(x: String) = s"cosh($x)"
  def tan(x: String) = s"tan($x)"
  def tanh(x: String) = s"tanh($x)"
  def cbrt(x: String) = s"cbrt($x)"
  def ceil(x: String) = s"ceil($x)"
  def exp(x: String) = s"exp($x)"
  def expm1(x: String) = s"expm1($x)"
  def floor(x: String) = s"floor($x)"
  def hypot(x: String, y: String) = s"hypot($x, $y)"
  def log(x: String) = s"log($x)"
  def log10(x: String) = s"log10($x)"
  def log1p(x: String) = s"log1p($x)"
  def pow(x: String, y: String) = s"pow($x, $y)"
  def rint(x: String) = s"rint($x)"
  def round(x: String) = s"round($x)"
  def sqrt(x: String) = s"sqrt($x)"
  def toDegrees(x: String) = s"toDegrees($x)"
  def toRadians(x: String) = s"toRadians($x)"
  def ulp(x: String) = s"ulp($x)"
  def fromByte(x: Byte) = x.toString
  def fromShort(x: Short) = x.toString
  def fromChar(x: Char) = x.toString
  def fromInt(x: Int) = x.toString
  def fromLong(x: Long) = x.toString
  def fromFloat(x: Float) = x.toString
  def fromDouble(x: Double) = x.toString

  def plus(x: String, y: String) = s"$x + $y"
  def minus(x: String, y: String) = s"$x - $y"
  def times(x: String, y: String) = s"$x * $y"
  def div(x: String, y: String) = s"$x / $y"
  def negate(x: String) = s"-$x"

  def toInt(x: String) = 0
  def toLong(x: String) = 0
  def toFloat(x: String) = 0
  def toDouble(x: String) = 0
}

object StringIsFloating {
  implicit object StringFloating extends StringIsFloating with Ordering.StringOrdering
}
