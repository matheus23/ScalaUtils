package org.matheusdev.specs

import org.scalatest.Specs
import org.matheusdev.util.tests._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 11:01 PM
 */
class UtilSpecs extends Specs(
  new DirWatcherSpec,
  new ClosingSpec
)