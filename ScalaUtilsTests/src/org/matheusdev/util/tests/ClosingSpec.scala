package org.matheusdev.util.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import java.io.Closeable
import org.matheusdev.util._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 11:20 PM
 */
class ClosingSpec extends FlatSpec with ShouldMatchers {

  class CloseableClass extends Closeable {
    var closed = "nope"
    def dosth = "something"
    def close() = closed = "yup"
  }

  "withCloseable" should "close the Closeable" in {
    val closeableClass = new CloseableClass()
    withCloseable(closeableClass) { closeable =>
      closeable.dosth should be ("something")
    }
    closeableClass.closed should be ("yup")
  }

  "withClose" should "also close the Closeable... Who would have thought that?" in {
    val closeableClass = new CloseableClass()
    withClose(closeableClass) {
      closeableClass.dosth should be ("something")
    }
    closeableClass.closed should be ("yup")
  }

}
