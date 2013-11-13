package org.hamcrest.bacon.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class ContainsMatcher<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    private ContainsMatcher(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Factory
    public static <T> Matcher<T> contains(Matcher<T> matcher) {
        return new ContainsMatcher<T>(matcher);
    }

    @Override
    public boolean matches(Object arg) {
        return matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("contains ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

}
