package playground

object Playground extends App {

  println("Scala advanced")

  def printTheList(): Unit = {
    val vehiclesList:List[String] = "Truck" :: "Car" :: "Bike" :: Nil

    for (vehicle <- vehiclesList) {
      println(vehicle)
    }
  }

  printTheList()

}
