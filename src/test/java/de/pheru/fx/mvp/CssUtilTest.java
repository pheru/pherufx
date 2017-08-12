package de.pheru.fx.mvp;

import de.pheru.fx.mvp.csstestclasses.CssTestClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CssUtilTest {

    @Test
    public void loadStylesheets(){
        final List<String> stylesheets = CssUtil.loadStylesheets(CssTestClass.class, CssTestClass.class.getSimpleName());

        assertEquals(3,stylesheets.size());
        assertTrue(stylesheets.get(0).toLowerCase().endsWith("csstestclass.css"));
        assertTrue(stylesheets.get(1).toLowerCase().endsWith("additional.css"));
        assertTrue(stylesheets.get(2).toLowerCase().endsWith("additional2.css"));
    }


}