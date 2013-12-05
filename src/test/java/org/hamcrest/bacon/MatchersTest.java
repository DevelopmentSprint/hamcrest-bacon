package org.hamcrest.bacon;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.bacon.Matchers.*;

import org.junit.Ignore;
import org.junit.Test;

public class MatchersTest {

    private Fake fake = mock(Fake.class);

    private DummyObject dummy = new DummyObject();

    @Test
    public void testHas() {
        assertThat(fake, has(hasProperty("property")));
    }

    @Test
    public void testA() {
        assertThat(fake, is(a(notNullValue())));
    }

    @Test(expected = AssertionError.class)
    public void testAFail() {
        assertThat(fake, is(a(nullValue())));
    }

    @Test
    public void testThat() {
        when(fake.getProperty()).thenReturn(10);
        assertThat(fake, hasProperty("property", that(is(equalTo(10)))));
    }

    @Test
    public void testPropertyString() {
        assertThat(fake, has(property("property")));
    }

    @Test
    public void testPropertyStringMatcherOfQ() {
        when(fake.getProperty()).thenReturn(10);
        assertThat(fake, has(property("property", that(is(equalTo(10))))));
    }

    @Test
    public void testAPropertyString() {
        assertThat(fake, has(aProperty("property")));
    }

    @Test
    public void testAPropertyStringMatcherOfQ() {
        when(fake.getProperty()).thenReturn(10);
        assertThat(fake, has(aProperty("property", that(is(equalTo(10))))));
    }

    @Ignore
    @Test
    public void testContains() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testSubstrings() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testContainsSubstrings() {
        fail("Not yet implemented");
    }

    @Test
    public void testMatchesRegex() {
        when(fake.getProperty()).thenReturn("blah blah");
        assertThat(fake, has(aProperty("property", that(Matchers.matchesRegex("^b(la).*$")))));
    }

    @Test
    public void testMatchesRegex2() {
        when(fake.getProperty()).thenReturn("blah blah");
        assertThat(fake, has(aProperty("property", that(not(Matchers.matchesRegex("^a(la).*$"))))));
    }

    @Test
    public void testFieldString() {
        assertThat(dummy, has(field("someField")));
    }

    @Test
    public void testFieldStringMatcherOfQsuperU() {
        assertThat(dummy, has(field("someField", that(is(equalTo("Nothing Really"))))));
    }

    @Test
    public void testHasFieldString() {
        assertThat(dummy, hasField("someField"));
    }

    @Test
    public void testHasFieldStringMatcherOfQsuperU() {
        assertThat(dummy, hasField("someField", that(is(equalTo("Nothing Really")))));
    }

}
