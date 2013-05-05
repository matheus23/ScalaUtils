package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 4:38 PM
 */
trait Matrix2[E] extends ((Int,Int) => E) with Mapping2 {
  val width: Int
  val height: Int
  def zippedLinearIterator: Iterator[(E,Int)]

  def apply(x: Int, y: Int) = checkedGet(x, y)
  def update(x: Int, y: Int, e: E) = checkedSet(x, y)(e)

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
    val itr = zippedLinearIterator
    for ((e, index) <- itr) {
      val (x, y) = indexToPos(index)
      op(x, y, e)
    }
  }

  def map(op: (Int,Int,E) => E) {
    val itr = zippedLinearIterator
    for ((e, index) <- itr) {
      val (x, y) = indexToPos(index)
      arraySet(index)(op(x, y, e))
    }
  }
}
