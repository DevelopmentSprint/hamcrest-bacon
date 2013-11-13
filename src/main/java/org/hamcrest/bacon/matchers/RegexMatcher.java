package org.hamcrest.bacon.matchers;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * RegexMatcher is a hamcrest matcher that matches a string against a regex.
 */
public class RegexMatcher extends TypeSafeMatcher<String> {

    private final String[] regexes;

    /**
     * Instantiates a new regex matcher.
     * 
     * @param aRegex
     *            the regex
     */
    private RegexMatcher(String... regexArray) {
        regexes = regexArray;
    }

    @SuppressWarnings("unchecked")
    @Factory
    public static <T> Matcher<T> matches(String... regexArray) {
        return (Matcher<T>) new RegexMatcher(regexArray);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format(" matches regexes \"%s\" ", Arrays.asList(regexes)));
    }

    @Override
    protected boolean matchesSafely(String string) {
        for (int i = 0; i < regexes.length; i++) {
            if (!string.matches(regexes[i])) {
                return false;
            }
        }
        return true;
    }
}