package Data

object Products {

  // TODO: step 2 - here your will have an attribute that will contain the products (e.g. "bière"), their types (e.g. "Boxer"), and their prices (e.g. 2.0).
  // TODO: step 2 - You will also have to find a way to store the default type/brand of a product.
  /*
    case object BOXER       extends Beer(1, "Boxer")
    case object WITTEKOP    extends Beer(2, "Wittekop")
    case object PUNKIPA     extends Beer(3, "PunkIPA")
    case object JACKHAMMER  extends Beer(3, "Jackhammer")
    case object TENEBREUSE  extends Beer(4, "Ténébreuse")

    case object MAISON  extends Croissant(2, "Maison")
    case object CAILLER extends Croissant(2, "Cailler")
    */
  abstract class Product {
    var price: Double
    var name: String
    var p_type: String

    override def toString: String = name
  }


}