package schedule

import sa.SATask

/**
  * Created by wojciech on 11.04.17. 
  */
class SchedulingTask(val startState: SchedulingRepr) extends SATask[SchedulingRepr, Double] {

  def mutate(r: SchedulingRepr): SchedulingRepr = r.mutate
  def translate(r: SchedulingRepr): Double = r.wage

}
