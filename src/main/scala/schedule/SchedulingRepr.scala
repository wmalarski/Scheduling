package schedule

import scala.util.Random

/**
  * Created by wojciech on 11.04.17. 
  */
abstract class SchedulingRepr(tMatrix: List[List[Double]], state: List[List[Int]]) {

  val processors: Int = tMatrix.length

  def wage: Double = state.zipWithIndex.map{
    case (list, proc) => list.map(tMatrix(proc)).sum
  }.max

  def mutate: SchedulingRepr
}
object SchedulingRepr {

  def state(tMatrix: List[List[Double]]): Option[List[List[Int]]] =
    tMatrix.headOption.flatMap{ head =>
      val taskNbr = head.length
      if(tMatrix.forall(_.length == taskNbr)) Some(taskNbr)
      else None
    }.map{ taskNbr =>
      val randLists = List.range(0, taskNbr)
        .map(_ -> Random.nextInt(tMatrix.length))
        .groupBy(_._2)
        .map(_._2.map(_._1))
        .toList
      randLists ::: List.fill(tMatrix.length - randLists.size)(Nil)
    }
}



