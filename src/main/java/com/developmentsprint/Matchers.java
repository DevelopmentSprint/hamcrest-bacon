package com.developmentsprint;

import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;

import com.developmentsprint.matchers.AProperty;
import com.developmentsprint.matchers.APropertyWithValue;
import com.developmentsprint.matchers.Has;
import com.developmentsprint.matchers.StringContainsAll;
import com.developmentsprint.matchers.That;

public final class Matchers {

    private Matchers() {
        // prevent instantiation
    }

    public static <T> Matcher<T> aProperty(String s) {
        return AProperty.aProperty(s);
    }

    public static <T> Matcher<T> aProperty(String s, Matcher<?> matcher) {
        return APropertyWithValue.aProperty(s, matcher);
    }

    public static <T> Matcher<T> has(CombinableMatcher<T> matcher) {
        return Has.has(matcher);
    }

    public static <T> Matcher<T> that(Matcher<T> matcher) {
        return That.that(matcher);
    }

    public static Matcher<String> containsAllStrings(String... s) {
        return StringContainsAll.containsAllStrings(s);
    }
}
