import schedule.{Min1DTask, SchedulingRepr, SchedulingReprImpl, SchedulingTask}
import sa.{IterationLimit, SimulatedAnnealing}

import scala.io.Source

/**
  * Created by wojciech on 11.04.17. 
  */
object main extends App {

  val tMatrix = Source.fromResource("lab5_piat1620.txt").getLines()
    .map(_.replace(',', '.'))
    .map(_.split("\t").map(_.toDouble).toList)
    .toList

  SchedulingReprImpl(tMatrix).foreach {repr =>
    val task = new SchedulingTask(repr)
    val sa = new SimulatedAnnealing(task, IterationLimit(0.999, 100, 5000))
    sa.start().foreach(println)
  }

}
