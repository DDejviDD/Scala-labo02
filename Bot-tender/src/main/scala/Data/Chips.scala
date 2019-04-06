package Data

class Chips extends Products.Product {
  override var name  : String = "chips"
  override var brand : String = "chips"
  override def price():Double ={
    brand match {
      case "chips"      => 0.0
    }
  }
}
