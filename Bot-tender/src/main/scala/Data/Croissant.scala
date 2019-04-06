package Data

class Croissant extends Products.Product{
  override var price : Double = 2.0
  override var name  : String = "croissant"
  override var brand : String = "maison"

  def this(price:Double, name:String){
    this()
    this.price = price
    this.name = name
  }

  override def toString: String = name + " " + brand
}
