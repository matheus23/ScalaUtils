package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 9:26 PM
 */
class ClusteredMatrix2[@specialized E: Manifest](
  val width: Int, val height: Int, val clusterWidth: Int, val clusterHeight: Int, val mapping: Mapping2)
  extends ArrayMatrix2[E] {

  @deprecated("use FastClusteredMatrix2 instead.")
  def this(width: Int, height: Int, clusterWidth: Int, clusterHeight: Int) =
    this(width, height, clusterWidth, clusterHeight,
      new ClusteredMapping2(width, height, clusterWidth, clusterHeight))

  protected val arraySize = (width / clusterWidth) * (height / clusterHeight) * (clusterWidth * clusterHeight)
  protected val backingArray = new Array[E](arraySize)

  def posToIndex(x: Int, y: Int) = mapping.posToIndex(x, y)
  def indexToPos(ind: Int) = mapping.indexToPos(ind)
}