package com.developmentsprint.matchers;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.beans.HasProperty;

public class AProperty<T> extends HasProperty<T> {

    public AProperty(String propertyName) {
        super(propertyName);
    }

    @Factory
    public static <T> Matcher<T> aProperty(String propertyName) {
        return new HasProperty<T>(propertyName);
    }

}
