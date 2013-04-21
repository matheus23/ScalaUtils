package org.matheusdev.properties.tests

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.matheusdev.properties.{FilePropertyReader, FileProperty}
import org.matheusdev.util._
import java.nio.file.{Files, Path, Paths}
import io.Source
import java.nio.charset.Charset
import tests.TestUtilities

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 3:47 PM
 */

class FilePropertySpec extends FlatSpec with ShouldMatchers with TestUtilities {

  behavior of "A FileProperty"

  it should "update together with the file" in {
    withTestingDir { testingDir =>
      val watcher = new DirWatcher(testingDir)
      val lineReader = new FilePropertyReader[String] {
        def readFile(path: Path): String = {
          val source = Source.fromFile(path.toFile)
          withClose(source)(source.mkString)
        }
      }
      val path = Files.createFile(testingDir.resolve("textfile.txt"))

      // Holy grail:
      val prop = new FileProperty[String](watcher, lineReader, path)

      Files.delete(path)

      val writer = Files.newBufferedWriter(path, Charset.defaultCharset())
      withClose(writer)(writer.write("Testing FileProperty"))

      assert(waitFor(1000)(prop.get() == "Testing FileProperty"))
    }
  }

}
