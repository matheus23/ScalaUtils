package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 9:21 PM
 */
class LinearMapping2(val width: Int, val height: Int) extends Mapping2 {

  import org.matheusdev.collections.mapping2d.{LinearMapping2 => Static}

  def posToIndex(x: Int, y: Int) = Static.posToIndex(x, y, width, height)
  def indexToPos(ind: Int) = Static.indexToPos(ind, width, height)
}

object LinearMapping2 {
  def posToIndex(x: Int, y: Int, width: Int, height: Int) = (x*height)+y
  def indexToPos(ind: Int, width: Int, height: Int) = ((ind / height), (ind % height))
}
