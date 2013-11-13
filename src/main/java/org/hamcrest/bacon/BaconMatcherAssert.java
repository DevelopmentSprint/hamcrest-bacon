package org.hamcrest.bacon;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public class BaconMatcherAssert extends MatcherAssert {

    public static <T> void assertThatWith(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat("", actual, matcher);
    }

    public static <T> void assertThatWith(String reason, T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(reason, actual, matcher);
    }

    public static <T> void assertThatWith(String reason, boolean assertion) {
        MatcherAssert.assertThat(reason, assertion);
    }
}
