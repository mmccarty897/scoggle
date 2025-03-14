import org.scalacheck.{Gen, Prop, Properties}

object WordDictionarySpec extends Properties("WordDictionary"):
  val validWords: Gen[String] = Gen.oneOf("POTATO", "butterfly", "DoG")
  val invalidWords: Gen[String] = Gen.oneOf("reeee", "scunk", "bonana")

  property("Valid words are valid") = Prop.forAll(validWords): word =>
    WordDictionary.isWord(word)

  property("Invalid words are invalid") = Prop.forAll(invalidWords): word =>
    !WordDictionary.isWord(word)
