package lectures.part2asp

object PartialFunctions extends App {

  val aFunction = (x: Int) => x + 1 // Function1[Int, Int] === Int => Int

  val aFussyFunction = (x: Int) =>
    if (x == 1) 42
    else if (x == 2) 56
    else if (x == 5) 999
    else throw new FunctionNotApplicableException

  class FunctionNotApplicableException extends RuntimeException

  val aNicerFussyFunction = (x: Int) => x match {
    case 1 => 42
    case 2 => 56
    case 3 => 999
  }
  // {1,2,5} => Int

  val aPartialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 56
    case 5 => 999
  } // partial function value

  println(aPartialFunction(5))

  // PF utilities
  println(aPartialFunction.isDefinedAt(31))

  // lift
  val lifted = aPartialFunction.lift // Int => Option[Int]
  println(lifted(2))
  println(lifted(31))

  // chain PFs
  val pfChain = aPartialFunction.orElse[Int, Int] {
    case 41 => 67
  }

  println(pfChain(2))
  println(pfChain(41))

  // PF extend normal functions
  val aTotalFunction: Int => Int = {
    case 1 => 99
  }

  // HOFs accept PFs aswell
  val aMappedList = List(1,2,3).map {
    case 1 => 40
    case 2 => 70
    case 3 => 90
  }
  println(aMappedList)

  /*
     Note: PF can only have ONE parameter type
    */

  /**
   * Exercises
   *
   * 1 - construct a PF instance yourself (anonymous class)
   * 2 - dumb chatbot as a PF
   */

  val aManualFussyFunction = new PartialFunction[Int, Int] {
    override def apply(v1: Int): Int = v1 match {
      case 1 => 42
      case 2 => 56
      case 5 => 999
  }

    override def isDefinedAt(x:  Int): Boolean =
      x == 1 || x == 2 || x == 5
  }

  val chatBotPF: PartialFunction[String, String] = {
    case "hello" => "Hi, I am a robot"
    case "how are you" => "Im, good thanks for asking"
    case "do you love scala" => "Yes I love scala very much"
  }

  scala.io.Source.stdin.getLines().map(chatBotPF).foreach(println)




}
