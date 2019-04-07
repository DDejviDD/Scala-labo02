package Data

/*
 * created by Muaremi Dejvid, Siu Aurélien
 */

class Croissant extends Products.Product{
  override var name  : String = "croissant"
  override var brand : String = "maison"

  override def toString: String = name + " " + brand

  /**
    * Obtain the price for the current croissant brand
    * @return a price of a croissant brand
    */
  override def price(): Double = {
    brand match {
      case "maison"     => 2.0
      case "cailler"    => 2.0
    }
  }
}
