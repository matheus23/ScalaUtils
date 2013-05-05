package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 11:07 AM
 */
class FastClusteredMatrix2[@specialized E: Manifest](
  val width: Int, val height: Int, val clusterWidth: Int, val clusterHeight: Int)
  extends ArrayMatrix2[E] {

  val mapping = new FastClusteredMapping2(width, height, clusterWidth, clusterHeight)
  protected val arraySize = width * height
  protected val backingArray = new Array[E](arraySize)

  def posToIndex(x: Int, y: Int) = mapping.posToIndex(x, y)
  def indexToPos(ind: Int) = mapping.indexToPos(ind)
}
