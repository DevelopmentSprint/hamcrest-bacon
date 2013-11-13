package org.hamcrest.bacon.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

public class ThatMatcher<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    private ThatMatcher(Matcher<T> matcher) {
        this.matcher = matcher;
    }

    @Factory
    public static <T> Matcher<T> that(Matcher<T> matcher) {
        return new ThatMatcher<T>(matcher);
    }

    @Override
    public boolean matches(Object arg) {
        return matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("that ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

}
