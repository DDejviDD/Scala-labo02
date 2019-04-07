package Data

/*
 * created by Muaremi Dejvid, Siu Aurélien
 */

class Chips extends Products.Product {
  override var name  : String = "chips"
  override var brand : String = "chips"

  /**
    * Obtain the price for the current chips brand
    * @return a price of a chips brand
    */
  override def price():Double ={
    brand match {
      case "chips"      => 0.0
    }
  }
}
