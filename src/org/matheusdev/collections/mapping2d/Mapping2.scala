package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 10:58 PM
 */
trait Mapping2 {
  def posToIndex(x: Int, y: Int): Int
  def indexToPos(ind: Int): (Int,Int)
}
