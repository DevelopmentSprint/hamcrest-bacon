package org.hamcrest.bacon.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.CombinableMatcher;

public class HasMatcher<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    private HasMatcher(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean matches(Object arg) {
        return matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("has ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T> Matcher<T> has(CombinableMatcher<T> matcher) {
        return new HasMatcher<T>(matcher);
    }
}
