package org.hamcrest.bacon.matchers;

import static org.hamcrest.Matchers.anything;

import java.lang.reflect.Field;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * HasFieldWithValue uses reflection to inspect member data of objects.
 * 
 * @param <T>
 *            the object type
 * @param <V>
 *            the value type
 */
public class FieldMatcher<T, V> extends TypeSafeDiagnosingMatcher<T> {

    private final String fieldName;

    private final Matcher<? super V> matcher;

    /**
     * Instantiates a new checks for field with value.
     * 
     * @param name
     *            name
     * @param matcher
     *            matcher
     */
    private FieldMatcher(String name, Matcher<? super V> matcher) {
        fieldName = name;
        this.matcher = matcher;
    }

    /**
     * Test to see if an object has a field with a particular value.
     * 
     * @param <T>
     *            generic type
     * @param <U>
     *            generic type
     * @param fieldName
     *            The name of the field
     * @param matcher
     *            The matcher for the field value
     * @return Either true or false
     */
    @Factory
    public static <T, U> Matcher<T> hasField(String fieldName, Matcher<? super U> matcher) {
        return new FieldMatcher<T, U>(fieldName, matcher);
    }

    /**
     * Test to see if an object has a given field.
     * 
     * @param <T>
     *            generic type
     * @param fieldName
     *            The name of the field
     * @return Either true or false
     */
    @Factory
    public static <T> Matcher<T> hasField(String fieldName) {
        return new FieldMatcher<T, Object>(fieldName, anything());
    }

    @Override
    protected boolean matchesSafely(final T anObject, final Description aDescription) {
        try {
            Field field = anObject.getClass().getDeclaredField(fieldName);
            boolean flag = field.isAccessible();
            field.setAccessible(true);
            Object value = field.get(anObject);

            field.setAccessible(flag);
            boolean ret = matcher.matches(value);
            if (!ret) {
                aDescription.appendText("\"" + fieldName + "\" ");
                matcher.describeMismatch(value, aDescription);
            }
            return ret;

        } catch (Exception e) {
            aDescription.appendText("access to \"" + fieldName + "\" failed with \"" + e.getMessage() + "\"");

            return false;
        }
    }

    @Override
    public void describeTo(Description aDescription) {
        aDescription.appendText("has field \"");
        aDescription.appendText(fieldName);
        aDescription.appendText("\": ");
        aDescription.appendDescriptionOf(matcher);
    }

}