package org.matheusdev

import java.io.Closeable
import java.util.concurrent.{Callable, ExecutorService, TimeUnit, Executors}

import scala.collection.JavaConversions._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 3/29/13
 * Time: 12:29 AM
 */
package object util {

  def withCloseable[T <: Closeable, R](c: T)(action: T => R): R = {
    try {
      action(c)
    } finally {
      if (c != null) c.close()
    }
  }

  def withClose[R](c: { def close() })(action: => R) = {
    try {
      action
    } finally {
      if (c != null)
        c.close()
    }
  }

  def measureTimeMs[A](op: => A) = {
    val time = System.currentTimeMillis()
    op
    System.currentTimeMillis()-time
  }

  def withStackTrace[E <: Exception, R](op: => R) = {
    try {
      op
    } catch {
      case e: E => e.printStackTrace()
    }
  }

  def periodically(fps: Int)(op: => Boolean) {
    val sync = new Sync {
      def getTime: Long = System.nanoTime()
    }
    var shouldRun = true
    while (shouldRun) {
      shouldRun = op
      sync.sync(fps)
    }
  }

  private class IndexRunnable(val index: Int, val numThreads: Int, val length: Int, val op: Int => Unit) extends Callable[Unit] {
    def call() {
      var i = index
      while (i < length) {
        op(i)
        i += numThreads
      }
    }
  }

  private lazy val indexIterationThreadNum = Runtime.getRuntime.availableProcessors()
  private lazy val indexIterationExecutors = Executors.newFixedThreadPool(indexIterationThreadNum)

  def parallelIndexIteration(length: Int, timeoutMs: Long,
    numThreads: Int = indexIterationThreadNum,
    executor: ExecutorService = indexIterationExecutors)(op: Int => Unit) {

    val tasks: java.util.List[Callable[Unit]] =
      (for (thread <- 0 until numThreads) yield {
        new IndexRunnable(thread, numThreads, length, op)
      })

    executor.invokeAll(tasks)
  }

  def simpleTypeName[T](implicit m: scala.reflect.Manifest[T]) = m.getClass.getSimpleName
  def completeTypeName[T](implicit m: scala.reflect.Manifest[T]) = m.getClass.getName
}
