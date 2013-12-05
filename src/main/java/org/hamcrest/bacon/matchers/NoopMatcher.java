package org.hamcrest.bacon.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

/**
 * Simple pass through matcher.
 * 
 * @author todd.orr
 * 
 * @param <T>
 */
public class NoopMatcher<T> extends BaseMatcher<T> {

    private final Matcher<T> matcher;

    private final String op;

    private NoopMatcher(Matcher<T> matcher, String op) {
        this.matcher = matcher;
        this.op = op;
    }

    @Override
    public boolean matches(Object arg) {
        return matcher.matches(arg);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(op + " ").appendDescriptionOf(matcher);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        matcher.describeMismatch(item, mismatchDescription);
    }

    @Factory
    public static <T> Matcher<T> noop(Matcher<T> matcher, String op) {
        return new NoopMatcher<T>(matcher, op);
    }
}
