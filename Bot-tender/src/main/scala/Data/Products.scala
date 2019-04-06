package Data

/*
 * edited by Muaremi Dejvid, Siu Aurélien
 */

object Products {

  abstract class Product {
    var name: String
    var brand: String

    override def toString: String = name
    def price(): Double
  }


}