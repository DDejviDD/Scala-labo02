package Data

class Croissant extends Products.Product{
  override var price : Double = 2.0
  override var name  : String = "Maison"
  override var p_type: String = "Croissant"

  def this(price:Double, name:String){
    this()
    this.price = price
    this.name = name
  }

  override def toString: String = p_type + " " + name
}
