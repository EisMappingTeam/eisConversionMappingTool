package com.eis.conv.mapping.core.filesSupport;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class FileHelperTest {

    @Test
    public void getFileNameTest() {
        assertThat(FileHelper.getFileName("C:\\111\\222\\ff.txt")).isEqualTo("ff.txt");
    }

    @Test
    public void getFileNameWithoutExtensionTest() {
        assertThat(FileHelper.getFileNameWithoutExtension("C:\\111\\222\\ff.txt")).isEqualTo("ff");
        assertThat(FileHelper.getFileNameWithoutExtension("C:\\111\\222\\ff.")).isEqualTo("ff");
        assertThat(FileHelper.getFileNameWithoutExtension("C:\\111\\222\\ff")).isEqualTo("ff");
    }

    @Test
    public void getFileExtensionTest() {
        assertThat(FileHelper.getFileExtension("C:\\111\\222\\ff.txt")).isEqualTo("txt");
        assertThat(FileHelper.getFileExtension("C:\\111\\222\\ff.")).isEqualTo("");
        assertThat(FileHelper.getFileExtension("C:\\111\\222\\ff")).isEqualTo("");
    }

}