package Data

class Chips extends Products.Product {
  override var name  : String = "chips"
  override var brand : String = ""
  override def price():Double ={
    brand match {
      case "chips"      => 0.0
    }
  }
}
