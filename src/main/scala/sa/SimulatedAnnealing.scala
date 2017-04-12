package sa

/**
  * Created by wojciech on 11.04.17. 
  */

class SimulatedAnnealing[X, Y](t: SATask[X, Y],info: SAInfoLike)(implicit op: Numeric[Y]){

  protected def decide(i: SAIterationInfo, task: SATask[X, Y]): Y = task.next {
    case diff if op.gt(diff, op.zero) => true
    case diff if math.random < math.exp(op.toDouble(diff)/i.temperature) => true
    case _ => false
  }

  def start(): Iterator[(Double,Y)] = info.toIterator
    .zip(Iterator.iterate(t)(identity))
    .map(e => e._1.temperature -> decide(e._1, e._2))
}