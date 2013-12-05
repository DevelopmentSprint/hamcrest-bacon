Hamcrest Bacon
==============

The delicious add-on for Hamcrest
---------------------------------

Bacon is a collection of additions for Hamcrest geared towards increasing readability and ease of use.

New Matchers
------------

HB provides several new matchers that range from pure syntactic sugar to genuinely useful, meatier code. Use the `org.hamcrest.bacon.Matchers.*` static import and the following matchers become available.

**Sugar:**

* `has(Matcher<T>)`
* `a(Matcher<T>)`
* `that(Matcher<T>)`
* `property(String)`
* `property(String, Matcher<T>)`
* `aProperty(String)`
* `aProperty(String, Matcher<T>)`

**Meat**

* `contains(Matcher<T>)`
* `substrings(String...)`
* `containsSubstrings(String...)`
* `matchesRegex(String...)`
* `field(String)`
* `field(String, Matcher<T>)`
* `aField(String)`
* `aField(String, Matcher<T>)`

***

### Sugar

#### has

The HasMatcher provides a composable means of expressing ownership assertions.

    assertThat(list, has(a(property("property"))));
