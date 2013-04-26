package org.matheusdev.specs

import org.scalatest.Specs
import org.matheusdev.properties.tests._

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 4/20/13
 * Time: 10:55 PM
 */
class PropertySpecs extends Specs(
  new PropertySpec,
  new NotNullPropertySpec,
  new LinkablePropertySpec,
  new DerivingPropertySpec,
  new ListenablePropertySpec,
  new FilePropertySpec,
  new AnimationPropertySpec
)
