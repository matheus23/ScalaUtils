package org.matheusdev.util.tests

import java.nio.file.{Paths, Files, Path}
import scala.collection.JavaConversions._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 3:54 PM
 */
trait TestUtilities {

  def waitFor(maxtime: Long)(wait: => Boolean): Boolean = {
    val startTime = System.currentTimeMillis()
    do {
      if (wait)
        return true
      Thread.sleep(maxtime / 20)
    } while(System.currentTimeMillis()-startTime <= maxtime)
    false
  }

  def withTestingDir(thrunk: Path => Unit) {
    val dir = Files.createDirectory(Paths.get("./testingDir"))
    try {
      thrunk(dir)
    } finally {
      deleteDirs(dir)
    }
  }

  def deleteDirs(dir: Path) {
    val allFiles = Files.newDirectoryStream(dir).iterator()
    for (file <- allFiles) {
      if (Files.isDirectory(file))
        deleteDirs(dir)
      else if (Files.isRegularFile(file))
        Files.deleteIfExists(file)
    }
    Files.deleteIfExists(dir)
  }

}
