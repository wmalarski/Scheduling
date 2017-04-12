package schedule

import sa.SATask

/**
  * Created by wojciech on 12.04.17. 
  */
class Min1DTask (val startState: Double, fun: Double => Double) extends SATask[Double, Double]{

  def mutate(r: Double): Double = r + math.random() - 0.5
  def translate(r: Double): Double = fun(r)

}
