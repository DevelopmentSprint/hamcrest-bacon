package org.hamcrest.bacon.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ContainsAllMatcher extends TypeSafeMatcher<String> {

    protected final String[] substrings;

    private ContainsAllMatcher(String... substrings) {
        this.substrings = substrings;
    }

    @Factory
    public static Matcher<String> containsAllStrings(String... substrings) {
        return new ContainsAllMatcher(substrings);
    }

    @Override
    public boolean matchesSafely(String item) {
        return evalSubstringsOf(item);
    }

    @Override
    public void describeMismatchSafely(String item, Description mismatchDescription) {
        mismatchDescription.appendText("was \"").appendText(item).appendText("\"");
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a string ").appendText(relationship()).appendText(" ").appendValue(substrings);
    }

    protected boolean evalSubstringsOf(String s) {
        boolean matches = true;
        for (String sub : substrings) {
            if (s.indexOf(sub) <= 0) {
                matches = false;
                break;
            }
        }
        return matches;
    }

    protected String relationship() {
        return "containing";
    }

}
