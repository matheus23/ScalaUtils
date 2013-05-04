package org.matheusdev.collections

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 4:39 PM
 */
class ArrayMatrix2[@specialized E: Manifest](val width: Int, val height: Int) extends Matrix2[E] {
  private val backingArray = new Array[E](width * height)

  def apply(x: Int, y: Int): E = backingArray()
  def update(x: Int, y: Int)(e: E): E = ???
}
