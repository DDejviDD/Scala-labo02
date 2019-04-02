package Data

class Chips extends Products.Product {
  override var price : Double = 0.0
  override var name  : String = ""
  override var p_type: String = "chips"

  override def toString: String = p_type
}
