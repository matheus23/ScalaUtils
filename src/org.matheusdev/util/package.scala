package org.matheusdev

import java.io.Closeable

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
}
