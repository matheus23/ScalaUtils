package org.matheusdev.collections.mapping2d

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/4/13
 * Time: 9:53 PM
 */
// Yet, test results fail...
class ClusteredMapping2(
  val width: Int,
  val height: Int,
  val clusterWidth: Int,
  val clusterHeight: Int,
  val outerMapping: Mapping2,
  val innerMapping: Mapping2) extends Mapping2 {

  def this(width: Int, height: Int, clusterWidth: Int, clusterHeight: Int) =
    this(width, height, clusterWidth, clusterHeight,
      new LinearMapping2(width / clusterWidth, height / clusterHeight),
      new LinearMapping2(clusterWidth, clusterHeight))

  private val indicesInCluster = clusterWidth * clusterHeight

  def posToIndex(x: Int, y: Int) = {
    val clusterIndex = outerMapping.posToIndex(x / clusterWidth, y / clusterHeight)
    val innerIndex = innerMapping.posToIndex(x % clusterWidth, y % clusterHeight)
    (clusterIndex * indicesInCluster) + innerIndex
  }
  def indexToPos(ind: Int) = {
    val (xPosInCluster, yPosInCluster) = innerMapping.indexToPos(ind % indicesInCluster)
    val (xClusterPos, yClusterPos) = outerMapping.indexToPos(ind / indicesInCluster)
    (xClusterPos * clusterWidth + xPosInCluster, yClusterPos * clusterHeight + yPosInCluster)
  }
}
