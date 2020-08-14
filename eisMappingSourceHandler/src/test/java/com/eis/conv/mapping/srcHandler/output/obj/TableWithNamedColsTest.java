package com.eis.conv.mapping.srcHandler.output.obj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TableWithNamedColsTest {

    @Test
    public void addColumn() {
        TableWithNamedCols t = new TableWithNamedCols();
        t.addColumn("One");
        t.addColumn("Two");

        assertThat(t.getColumnIndex("One")).isEqualTo(0);
        assertThat(t.getColumnIndex("Two")).isEqualTo(1);
        assertThat(t.getColumnIndex("NoOne")).isEqualTo(-1);
    }


    @Test
    public void getColumnsCount() {
        TableWithNamedCols t = new TableWithNamedCols();
        t.addColumn("One");
        t.addColumn("Two");

        assertThat(t.getColumnsCount()).isEqualTo(2);
    }

    @Test
    public void putValue() {

        String caption_00 = "Col_0";
        String caption_01 = "Col_1";
        String caption_02 = "Col_2";
        String caption_03 = "Col_3";

        String value_0_0 = "0x0";
        String value_1_1 = "1x1";
        String value_1_2 = "1x2";
        String value_2_0 = "2x0";
        String value_2_2 = "2x2";
        String value_3_2 = "3x2";
        String value_3_3 = "3x3";

        TableWithNamedCols t = new TableWithNamedCols();

        t.addColumn(caption_00);
        t.putValue(1, caption_01, value_1_1);
        t.putValue(2, caption_00, value_2_0);
        t.putValue(2, caption_02, value_2_2);
        t.putValue(0, caption_00, value_0_0); // add to column which was added by 'addColumn'

        t.addColumn(caption_03);
        t.putValue(3, caption_02, value_3_2); // add to column which was added by 'addColumn'
        t.putValue(3, caption_03, value_3_3); // add to column which was added by 'addColumn'
        t.putValue(1, caption_02, value_1_2);

        assertThat(t.getColumnsCount()).isEqualTo(4);
        assertThat(t.getCellValue(0, 0)).isEqualTo(value_0_0);
        assertThat(t.getCellValue(1, 1)).isEqualTo(value_1_1);
        assertThat(t.getCellValue(1, 2)).isEqualTo(value_1_2);
        assertThat(t.getCellValue(2, 0)).isEqualTo(value_2_0);
        assertThat(t.getCellValue(2, 2)).isEqualTo(value_2_2);
        assertThat(t.getCellValue(3, 2)).isEqualTo(value_3_2);
        assertThat(t.getCellValue(3, 3)).isEqualTo(value_3_3);
        assertThat(t.getCellValue(0, 3)).isEqualTo(""); //no entry, value =""

    }

    @Test
    public void putInNewRow() {
        String caption_00 = "Col_0";
        String caption_01 = "Col_1";

        String value_0_0 = "0x0";
        String value_1_1 = "1x1";

        TableWithNamedCols t = new TableWithNamedCols();
        t.putInNewRow(caption_00, value_0_0);
        t.putInNewRow(caption_01, value_1_1);

        assertThat(t.getColumnsCount()).isEqualTo(2);
        assertThat(t.getRowsCount()).isEqualTo(2);

        assertThat(t.getCellValue(0, 0)).isEqualTo(value_0_0);
        assertThat(t.getCellValue(1, 1)).isEqualTo(value_1_1);
        assertThat(t.getCellValue(1, 0)).isEqualTo("");


    }

    @Test
    public void getRowAsString() {
        String delimiter = "-";
        String caption_00 = "Col_0";
        String caption_01 = "Col_1";

        String value_0_0 = "0x0";
        String value_1_0 = "1x0";
        String value_1_1 = "1x1";

        String result_0 = "0x0-";
        String result_1 = "1x0-1x1";

        TableWithNamedCols t = new TableWithNamedCols();
        t.putValue(0, caption_00, value_0_0) ;
        t.putValue(1, caption_00, value_1_0 ) ;
        t.putValue(1, caption_01, value_1_1) ;

        assertThat(t.getRowAsString(0,delimiter) ).isEqualTo(result_0);
        assertThat(t.getRowAsString(1,delimiter) ).isEqualTo(result_1);
    }
}