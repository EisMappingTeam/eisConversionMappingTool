package com.eis.conv.mapping.core.stringsSupport;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringHelperTest {

    @Test
    public void getLastSplittedTest() {
        String r1 = StringHelper.getLastDotSplitted("com.exigen.ipb.base.datatypes.Address");
        assertThat(r1).isEqualTo("Address");

        String r2 = StringHelper.getLastDotSplitted("a-bb-ccc");
        assertThat(r2).isEqualTo("a-bb-ccc");

        String r3 = StringHelper.getLastDotSplitted("");
        assertThat(r3).isEqualTo("");
    }

    @Test
    public void getLeftTest() {
        String s1 = StringHelper.getLeft(null, 1);
        assertThat(s1).isEqualTo("");

        String s2 = StringHelper.getLeft("", 1);
        assertThat(s2).isEqualTo("");

        String s3 = StringHelper.getLeft("12", 3);
        assertThat(s3).isEqualTo("12");

        String s4 = StringHelper.getLeft("1234", 4);
        assertThat(s4).isEqualTo("1234");

        String s5 = StringHelper.getLeft("12345", 4);
        assertThat(s5).isEqualTo("1234");

        String s6 = StringHelper.getLeft("12345", 0);
        assertThat(s6).isEqualTo("");
    }

    @Test
    public void getRightTest() {
        String s1 = StringHelper.getRight(null, 1);
        assertThat(s1).isEqualTo("");

        String s2 = StringHelper.getRight("", 1);
        assertThat(s2).isEqualTo("");

        String s3 = StringHelper.getRight("X", 1);
        assertThat(s3).isEqualTo("X");

        String s4 = StringHelper.getRight("123", 2);
        assertThat(s4).isEqualTo("23");

        String s5 = StringHelper.getRight("123", 3);
        assertThat(s5).isEqualTo("123");

        String s6 = StringHelper.getRight("123", 0);
        assertThat(s6).isEqualTo("");
    }

    @Test
    public void isNumericTest() {
        assertThat(StringHelper.isNumeric("sdfdsf")).isEqualTo(false);
        assertThat(StringHelper.isNumeric("")).isEqualTo(false);
        assertThat(StringHelper.isNumeric(null)).isEqualTo(false);
        assertThat(StringHelper.isNumeric(".2143")).isEqualTo(false);

        assertThat(StringHelper.isNumeric("1312")).isEqualTo(true);
        assertThat(StringHelper.isNumeric("0.4")).isEqualTo(true);
        assertThat(StringHelper.isNumeric("23.4234")).isEqualTo(true);
    }

    @Test
    public void lowerFirstTest() {
        String s1 = StringHelper.lowerFirst(null);
        assertThat(s1).isEqualTo("");

        String s2 = StringHelper.lowerFirst("");
        assertThat(s2).isEqualTo("");

        String s3 = StringHelper.lowerFirst("a");
        assertThat(s3).isEqualTo("a");

        String s4 = StringHelper.lowerFirst("A");
        assertThat(s4).isEqualTo("a");

        String s5 = StringHelper.lowerFirst("Abs");
        assertThat(s5).isEqualTo("abs");
    }

    @Test
    public void splitToListAndTrimTest(){
        List<String> result;
        String str = "";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("");

        str = " ";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("");

        str = "3423";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("3423");

        str = " 3423 ";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("3423");

        str = ";3423";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(1)).isEqualTo("3423");

        str = "3423;";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(1)).isEqualTo("");

        str = "34;23";
        result= StringHelper.splitToListAndTrim(str,";");
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0)).isEqualTo("34");
        assertThat(result.get(1)).isEqualTo("23");
    }
}