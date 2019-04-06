package Data

class Beer extends Products.Product {
  override var price = 1.0
  override var name = "bière"
  override var brand = "Boxer"

  def this(price:Double, name:String){
    this()
    this.price = price
    this.name = name
  }

  override def toString: String = brand
}