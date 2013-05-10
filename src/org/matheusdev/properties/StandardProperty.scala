package org.matheusdev.properties

/*
 * Created with IntelliJ IDEA.
 * Author: matheusdev
 * Date: 5/9/13
 * Time: 1:58 PM
 */
class StandardProperty[E](initial: E) extends Property[E](initial) with LinkableProperty[E] with ListenableProperty[E]