package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 10:31 AM
 */
class FastClusteredMapping2(
  width: Int,
  height: Int,
  clusterWidth: Int,
  clusterHeight: Int) extends Mapping2 {

  import org.matheusdev.collections.mapping2d.{FastClusteredMapping2 => Static}

  Static.mustBePowerOfTwo(width, "width")
  Static.mustBePowerOfTwo(height, "height")
  Static.mustBePowerOfTwo(clusterWidth, "clusterWidth")
  Static.mustBePowerOfTwo(clusterHeight, "clusterHeight")

  private val logClustersWidth = Static.logBase2(width / clusterWidth)
  private val logClustersHeight = Static.logBase2(height / clusterHeight)
  private val logClusterWidth = Static.logBase2(clusterWidth)
  private val logClusterHeight = Static.logBase2(clusterHeight)
  private val logIndicesInCluster = logClusterWidth + logClusterHeight

  private val outerMapping = new FastLinearMapping2(logClustersWidth, logClustersHeight)
  private val innerMapping = new FastLinearMapping2(logClusterWidth, logClusterHeight)

  def posToIndex(x: Int, y: Int) = {
    val clusterIndex = outerMapping.posToIndex(x >> logClusterWidth, y >> logClusterHeight)
    val outerIndex = innerMapping.posToIndex(x & logClusterWidth-1, y & logClusterHeight-1)
    (clusterIndex << logIndicesInCluster) | outerIndex
  }
  def indexToPos(ind: Int) = {
    val (xPosInCluster, yPosInCluster) = innerMapping.indexToPos(ind & logIndicesInCluster-1)
    val (xOuterPos, yOuterPos) = outerMapping.indexToPos(ind >> logIndicesInCluster)
    (xOuterPos << logClusterWidth | xPosInCluster, yOuterPos << logClusterHeight | yPosInCluster)
  }
}

object FastClusteredMapping2 {
  def mustBePowerOfTwo(x: Int, name: String) = {
    if (Integer.bitCount(x) > 1)
      throw new IllegalArgumentException(s"Only accepting power of two values for $name: $x")
    else x
  }
  def logBase2(x: Int) = (math.log(x) / math.log(2)).toInt
}
