package com.developmentsprint.matchers;

import static org.hamcrest.beans.PropertyUtil.NO_ARGUMENTS;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.beans.PropertyUtil;

public class APropertyWithValue<T> extends TypeSafeDiagnosingMatcher<T> {

    private final String propertyName;

    private final Matcher<?> valueMatcher;

    public APropertyWithValue(String propertyName, Matcher<?> valueMatcher) {
        this.propertyName = propertyName;
        this.valueMatcher = valueMatcher;
    }

    @Override
    public boolean matchesSafely(T bean, Description mismatchDescription) {
        try {
            Method readMethod = findReadMethod(bean, mismatchDescription);
            if (readMethod == null) {
                return false;
            }
            Object propertyValue = readMethod.invoke(bean, NO_ARGUMENTS);
            boolean valueMatches = valueMatcher.matches(propertyValue);
            if (!valueMatches) {
                mismatchDescription.appendText("property \"" + propertyName
                        + "\" ");
                valueMatcher.describeMismatch(propertyValue,
                        mismatchDescription);
            }
            return valueMatches;
        } catch (IllegalArgumentException e) {
            return false;
        } catch (IllegalAccessException e) {
            return false;
        } catch (InvocationTargetException e) {
            return false;
        }
    }

    private Method findReadMethod(Object argument,
            Description mismatchDescription) throws IllegalArgumentException {
        PropertyDescriptor property = PropertyUtil.getPropertyDescriptor(
                propertyName, argument);
        if (null == property) {
            mismatchDescription.appendText("No property \"" + propertyName
                    + "\"");
            return null;
        }
        Method readMethod = property.getReadMethod();
        if (null == readMethod) {
            mismatchDescription.appendText("property \"" + propertyName
                    + "\" is not readable");
        }
        return readMethod;
    }

    public void describeTo(Description description) {
        description.appendText("hasProperty(");
        description.appendValue(propertyName);
        description.appendText(", ");
        description.appendDescriptionOf(valueMatcher);
        description.appendText(")");
    }

    @Factory
    public static <T> Matcher<T> aProperty(String propertyName, Matcher<?> value) {
        return new APropertyWithValue<T>(propertyName, value);
    }
}
