package org.matheusdev.collections

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 4:38 PM
 */
trait Matrix2[E] extends ((Int,Int) => E) {
  val width: Int
  val height: Int

  def apply(x: Int, y: Int) = checkedGet(x, y)
  def update(x: Int, y: Int)(e: E) = checkedSet(x, y)(e)

  // Mother of the Matrix2:
  protected def posToIndex(x: Int, y: Int): Int
  protected def indexToPos(ind: Int): (Int,Int)

  protected def checkedGet(x: Int, y: Int) = withRangeCheck(x, y)(arrayGet(posToIndex(x, y)))
  protected def arrayGet(ind: Int): E

  protected def checkedSet(x: Int, y: Int)(e: E) = withRangeCheck(x, y)(arraySet(posToIndex(x, y))(e))
  protected def arraySet(ind: Int)(e: E): E

  protected def isValidPos(x: Int, y: Int) = (x >= 0 && x < width && y >= 0 && y < height)
  protected def withRangeCheck[@specialized R](x: Int, y: Int)(op: => R) =
    if (isValidPos(x, y))
      op
    else
      throw new ArrayIndexOutOfBoundsException(s"position ($x, $y) is out of range ($width, $height)")

  def foreach(op: (Int,Int,E) => Unit) {

  }
}
