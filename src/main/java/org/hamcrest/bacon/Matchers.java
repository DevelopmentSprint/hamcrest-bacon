package org.hamcrest.bacon;

import static org.hamcrest.Matchers.anything;

import org.hamcrest.Matcher;
import org.hamcrest.bacon.matchers.ContainsAllMatcher;
import org.hamcrest.bacon.matchers.ContainsMatcher;
import org.hamcrest.bacon.matchers.FieldMatcher;
import org.hamcrest.bacon.matchers.NoopMatcher;
import org.hamcrest.bacon.matchers.RegexMatcher;

public final class Matchers {

    private Matchers() {
        // prevent instantiation
    }

    public static <T> Matcher<T> has(Matcher<T> matcher) {
        return NoopMatcher.noop(matcher, "has");
    }

    public static <T> Matcher<T> a(Matcher<T> matcher) {
        return NoopMatcher.noop(matcher, "a");
    }

    public static <T> Matcher<T> that(Matcher<T> matcher) {
        return NoopMatcher.noop(matcher, "that");
    }

    public static <T> Matcher<T> property(String s) {
        return org.hamcrest.Matchers.hasProperty(s);
    }

    public static <T> Matcher<T> property(String s, Matcher<?> matcher) {
        return org.hamcrest.Matchers.hasProperty(s, matcher);
    }

    public static <T> Matcher<T> aProperty(String s) {
        return org.hamcrest.Matchers.hasProperty(s);
    }

    public static <T> Matcher<T> aProperty(String s, Matcher<?> matcher) {
        return org.hamcrest.Matchers.hasProperty(s, matcher);
    }

    public static <T> Matcher<T> contains(Matcher<T> matcher) {
        return ContainsMatcher.contains(matcher);
    }

    public static Matcher<String> substrings(String... s) {
        return ContainsAllMatcher.containsAllStrings(s);
    }

    public static Matcher<String> containsSubstrings(String... s) {
        return ContainsAllMatcher.containsAllStrings(s);
    }

    public static <T> Matcher<T> matchesRegex(String... regexArray) {
        return RegexMatcher.matches(regexArray);
    }

    public static <T> Matcher<T> field(String fieldName) {
        return FieldMatcher.hasField(fieldName, anything());
    }

    public static <T, U> Matcher<T> field(String fieldName, Matcher<? super U> matcher) {
        return FieldMatcher.hasField(fieldName, matcher);
    }

    public static <T> Matcher<T> hasField(String fieldName) {
        return FieldMatcher.hasField(fieldName, anything());
    }

    public static <T, U> Matcher<T> hasField(String fieldName, Matcher<? super U> matcher) {
        return FieldMatcher.hasField(fieldName, matcher);
    }
}
