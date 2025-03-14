## Scoggle

### Usage

Scoggle is a Scala implementation of everyone's favorite 4x4 word game!

Scoggle depends on Scala 3.6.4 and sbt 1.10.10.

To run scoggle, run:

```
sbt run
```

To run the test suite, run:

```
sbt test
```

Some basic property-based tests via [Scalacheck](https://scalacheck.org/) are done to verify the Trie implementation, which powers the scoggle algorithm.
