package Data

/*
 * created by Muaremi Dejvid, Siu Aurélien
 */

class Beer extends Products.Product {
  override var name = "bière"
  override var brand = "boxer"

  /**
    * Obtain the price for the current beer brand
    * @return a price of a beer brand
    */
  override def price():Double ={
    brand match {
      case "boxer"      => 1.0
      case "farmer"     => 1.0
      case "wittekop"   => 2.0
      case "punkipa"    => 3.0
      case "jackhammer" => 3.0
      case "ténébreuse" => 4.0
    }
  }

  override def toString: String = brand
}