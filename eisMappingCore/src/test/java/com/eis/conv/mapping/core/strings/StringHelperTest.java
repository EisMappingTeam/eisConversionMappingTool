package com.eis.conv.mapping.core.strings;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHelperTest {

    @Test
    public void getLastSplitted() {
        String r1 = StringHelper.getLastDotSplitted("com.exigen.ipb.base.datatypes.Address");
        assertThat(r1).isEqualTo("Address");

        String r2 = StringHelper.getLastDotSplitted("a-bb-ccc");
        assertThat(r2).isEqualTo("a-bb-ccc");

        String r3 = StringHelper.getLastDotSplitted("");
        assertThat(r2).isEqualTo("");
    }
}