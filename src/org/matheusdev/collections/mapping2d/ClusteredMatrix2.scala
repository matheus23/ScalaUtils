package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 9:26 PM
 */
import org.matheusdev.collections.mapping2d.{ClusteredMatrix2 => Static}

class ClusteredMatrix2[@specialized E: Manifest](
  val width: Int, val height: Int, val clusterWidth: Int, val clusterHeight: Int, val mapping: Mapping2)
  extends ArrayMatrix2[E] {

  def this(width: Int, height: Int, clusterWidth: Int, clusterHeight: Int) =
    this(width, height, clusterWidth, clusterHeight,
      new ClusteredMapping2(
        Static.wantedWidth(width, clusterWidth),
        Static.wantedHeight(height, clusterHeight),
        clusterWidth, clusterHeight))

  protected val arraySize = Static.wantedWidth(width, clusterWidth) * Static.wantedHeight(height, clusterHeight)
  protected val backingArray = new Array[E](arraySize)

  def posToIndex(x: Int, y: Int) = mapping.posToIndex(x, y)
  def indexToPos(ind: Int) = mapping.indexToPos(ind)
}

object ClusteredMatrix2 {
  def wantedWidth(width: Int, clusterWidth: Int) = (width + clusterWidth - (width % clusterWidth))
  def wantedHeight(height: Int, clusterHeight: Int) = (height + clusterHeight - (height % clusterHeight))
}
