package Data

class Beer extends Products.Product {
  override var price = 1.0
  override var name = "Boxer"
  override var p_type = "Beer"

  def this(price:Double, name:String){
    this()
    this.price = price
    this.name = name
  }
}