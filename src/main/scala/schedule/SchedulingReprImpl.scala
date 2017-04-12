package schedule

import scala.util.Random

/**
  * Created by wojciech on 12.04.17. 
  */
sealed case class SchedulingReprImpl(tMatrix: List[List[Double]], state: List[List[Int]])
  extends SchedulingRepr(tMatrix, state){

  def mutate: SchedulingRepr = {
    val rand = (m: Int) => Random.nextInt(m)
    val proc1 = Iterator.continually(rand(processors)).dropWhile(state(_).isEmpty).next()
    val proc2 = rand(processors)
    val taskInd = rand(state(proc1).length)
    val task = state(proc1)(taskInd)
    val p1 = state(proc1).take(taskInd) ::: state(proc1).drop(taskInd)
    val p2 = task :: state(proc2)
    val update = state.updated(proc1, p1).updated(proc1, p2)
    SchedulingReprImpl(tMatrix, update)
  }

}
object SchedulingReprImpl {

  def apply(tMatrix: List[List[Double]]): Option[SchedulingRepr] =
    SchedulingRepr.state(tMatrix).map(SchedulingReprImpl(tMatrix, _))

}