package cn.quickits.sewing;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

public class SewingTest {

    @Test
    public void testOptions() throws ParseException {
        String args[] = {"-h"};
        Sewing.main(args);
    }
}
