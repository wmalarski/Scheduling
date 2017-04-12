package sa

/**
  * Created by wojciech on 11.04.17. 
  */

case class SAIterationInfo(temperature: Double)

trait SAInfoLike {
  def toIterator: Iterator[SAIterationInfo]
}

case class TempLimit(alpha: Double, iteration: Int, tempStart: Double, tempEnd: Double) extends SAInfoLike {
  def toIterator: Iterator[SAIterationInfo] =
    Iterator.iterate(tempStart)(_ * alpha)
      .takeWhile(_ > tempEnd)
      .flatMap(t => Iterator.fill(iteration)(t))
      .map(SAIterationInfo)
}

case class IterationLimit(alpha: Double, tempStart: Double, iterationMax: Int) extends SAInfoLike {
  def toIterator: Iterator[SAIterationInfo] =
    Iterator.iterate(tempStart)(_ * alpha)
      .take(iterationMax)
      .map(SAIterationInfo)
}