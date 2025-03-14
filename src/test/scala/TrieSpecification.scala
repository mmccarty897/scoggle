import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import org.scalacheck.Prop.propBoolean

object TrieSpecification extends Properties("String"):

  property("Empty trie has no strings") = forAll: (a: String) =>
    !Trie().contains(a)

  property("Trie with inserted string starts with first character of said string") = forAll: (a: String) =>
    (a.length() > 0) ==> Trie().insert(a).startsWith(a.head.toString)

  property("Trie with inserted string does not start with a different character") = forAll: (a: String, b: String) =>
    (a.length() > 0 && !a.startsWith(b)) ==> !Trie().insert(a).startsWith(b)

  property("Trie with inserted string contains said string") = forAll: (a: String, b: String) =>
    (a == b) ==> Trie().insert(a).contains(b)
    (a != b) ==> !Trie().insert(a).contains(b)
