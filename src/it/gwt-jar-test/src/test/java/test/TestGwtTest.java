package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.environment.expression.function.EnvironmentExpressionFunctions;


@walkingkooka.j2cl.locale.LocaleAware
public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
            1,
            1
        );
    }

    public void testGetUser() {
        EnvironmentExpressionFunctions.getUser();
    }
}
