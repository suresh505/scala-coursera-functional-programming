package patmat

import scala.concurrent.Promise

/**
  * Created by Suresh on 07/01/2017.
  */
object MyTest extends App{

  def time() :Long = {
    println("Entered time() ...")
    System.nanoTime
  }

  // uses a by-name parameter here
  def exec(t: => Long) = {
    println("Entered exec, calling t ...")
    println("t = " + t)
    println("Calling t again ...")
    t
  }

  println(exec(time()))
}
