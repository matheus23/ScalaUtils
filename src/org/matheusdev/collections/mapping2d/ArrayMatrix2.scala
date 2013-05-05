package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 4:39 PM
 */
trait ArrayMatrix2[E] extends Matrix2[E] {
  val width: Int
  val height: Int
  protected val backingArray: Array[E]

  protected def arrayGet(ind: Int) = backingArray(ind)
  protected def arraySet(ind: Int)(e: E) = { backingArray(ind) = e; e }

  def zippedLinearIterator = backingArray.zipWithIndex.iterator
}
