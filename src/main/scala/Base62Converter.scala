
object Base62Encoder {

  val ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
  
  def toBase62(value: Int): String = {
    def loop(encodedStr: String, value: Int) : String = {
      value match {
        case 0 => encodedStr
        case _ => loop(ALPHABET(value % 62) + encodedStr, value / 62)
      }
    }
    loop("", value)
  }

  def fromBase62Encoded(encodedStr: String): Int = {    
    def loop(value: Int, encoded: String) : Int = {
      encoded.size match {
        case 0 => value
        case _ => loop((scala.math.pow(62,encoded.size-1)*ALPHABET.indexOf(encoded.take(1))).toInt + value, encoded.drop(1) )
      }
    }
    loop(0, encodedStr)
  }

  //
  // println(toBase62(48273263))
  // println(fromBase62Encoded("3gy51")) 
  //
}
