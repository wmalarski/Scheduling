package sa

/**
  * Created by wojciech on 12.04.17. 
  */
abstract class SATask[X, Y : Numeric] {

  val startState: X
  private var rState: X = startState
  private var nState: Y = translate(startState)

  def mutate(r: X): X
  def translate(r: X): Y

  final def next(f: Y => Boolean): Y = {
    val op = implicitly[Numeric[Y]]
    val newRState = mutate(rState)
    val newNState = translate(newRState)
    val diff = op.minus(nState, newNState)
    if (f(diff)) {
      rState = newRState
      nState = newNState
    }
    nState
  }
}