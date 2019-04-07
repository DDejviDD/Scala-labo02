package Data

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object Products {

  abstract class Product {
    var name: String
    var brand: String

    override def toString: String = name
    /**
      * Obtain the price for the current product brand
      * @return a price of a product brand
      */
    def price(): Double
  }


}