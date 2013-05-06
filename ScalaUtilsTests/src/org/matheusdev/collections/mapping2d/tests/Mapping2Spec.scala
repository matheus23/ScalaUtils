package org.matheusdev.collections.mapping2d.tests

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.matheusdev.collections.mapping2d._
import scala.util.Random

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/5/13
 * Time: 11:21 AM
 */
class Mapping2Spec extends FlatSpec with ShouldMatchers {

  def testPositions[E](matrix: Matrix2[E])(op: (Int,Int) => Unit) {
    for {
      x <- 0 until matrix.width
      y <- 0 until matrix.height
    } {
      op(x, y)
    }
  }

  def testGetSetEquality(matrix: Matrix2[Int]) {
    val random = new Random()
    testPositions(matrix)((x, y) => {
      val i = random.nextInt()
      try {
        matrix(x, y) = i
        assert(matrix(x, y) == i, "The matrix didn't produce the value it was set to")
      } catch {
        case e: Exception => Console.err.println(s"Error at ($x, $y)"); throw e
      }
    })
  }

  def testBoundExceptions[E](matrix: Matrix2[E]) {
    intercept[ArrayIndexOutOfBoundsException](matrix(-1, -1))
    intercept[ArrayIndexOutOfBoundsException](matrix(matrix.width, -1))
    intercept[ArrayIndexOutOfBoundsException](matrix(-1, matrix.height))
    intercept[ArrayIndexOutOfBoundsException](matrix(matrix.width, matrix.height))
  }

  def iterateAllMatrix2Types[E: Manifest](op: Matrix2[E] => Unit) {
    // pot = power of two
    // 419, 307, 19 and 17 are prime numbers, they're for testing the non-pot collections to be working
    // 8, 16, 256 and 512 are pot and used for testing the pot-optimized collections.
    // 8 is the logBase2(256) and 9 is the logBase2(512)
    val singleMappedMatrix2: Matrix2[E] = new SingleMappedMatrix2[E](419, 307)
    val clusteredMatrix2: Matrix2[E] = new ClusteredMatrix2[E](419, 307, 19, 17)
    val fastSingleMappedMatrix2: Matrix2[E] = new SingleMappedMatrix2[E](256, 512, new FastLinearMapping2(8, 9))
    val fastClusteredMatrix2: Matrix2[E] = new FastClusteredMatrix2[E](256, 64, 8, 16)
    tryMatrix2("SingleMappedMatrix2")(op(singleMappedMatrix2))
    tryMatrix2("ClusteredMatrix2")(op(clusteredMatrix2))
    tryMatrix2("SingleMappedMatrix2 with FastLinearMapping2")(op(fastSingleMappedMatrix2))
    tryMatrix2("FastClusteredMatrix2")(op(fastClusteredMatrix2))
  }

  def tryMatrix2(name: String)(op: => Unit) {
    try {
      println(s"Testing matrix type $name")
      op
    } catch {
      case e: Exception => Console.err.println(s"Error while processing matrix type $name:"); throw e
    }
  }

  "All kinds of Mapped Matrices" should "pass the matrix tests" in {
    iterateAllMatrix2Types[Int] { matrix =>
      testGetSetEquality(matrix)
      testBoundExceptions(matrix)
    }
  }

}
