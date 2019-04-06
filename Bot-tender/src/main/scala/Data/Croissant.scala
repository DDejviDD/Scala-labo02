package Data

class Croissant extends Products.Product{
  override var name  : String = "croissant"
  override var brand : String = "maison"

  def this(price:Double, name:String){
    this()
    this.name = name
  }

  override def toString: String = name + " " + brand

  override def price(): Double = {
    brand match {
      case "maison"     => 2.0
      case "cailler"    => 2.0
    }
  }
}
