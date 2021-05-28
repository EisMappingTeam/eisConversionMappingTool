package com.eis.conv.mapping.srcHandler.output.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableWithNamedCols {
    private List<String> cols = new ArrayList<>();
    private List<Map<String, String>> rows = new ArrayList();

    public void addColumn(String caption) {
        boolean itemExist = cols.stream().anyMatch(item -> item.equalsIgnoreCase(caption));
        if (!itemExist) {
            cols.add(caption);
        }
    }

    public int putInNewRow(String columnCaption, String value) {
        int row = rows.size();
        addColumn(columnCaption);
        addRows(row);
        putCellValue(row, columnCaption, value);
        return row;
    }

    public void putValue(int row, String columnCaption, String value) {
        addRows(row);
        addColumn(columnCaption);
        putCellValue(row, columnCaption, value);
    }

    public int getColumnIndex(String caption) {
        return cols.indexOf(caption);
    }

    public int getColumnsCount() {
        return cols.size();

    }

    public String getColumnCaption(int idx) {
        return cols.get(idx);
    }

    public int getRowsCount() {
        return rows.size();

    }

    public String getCellValue(int row, int col) {
        Map oneRow = rows.get(row);
        return getCellValue(oneRow, col, "");
    }

    public String getCaptionsAsString(String delimiter) {
        return cols.stream().collect(Collectors.joining(delimiter));
    }

    public String getRowAsString(int row, String delimiter) {
        String result = getRowAsList(row).stream().collect(Collectors.joining(delimiter));
        return result;
    }

    public List<String> getRowAsList(int row) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            result.add(getCellValue(row, i));
        }
        return result;
    }

    private void addRows(int rowNum) {
        for (int i = rows.size(); i <= rowNum; i++) {
            rows.add(new HashMap<>());
        }
    }

    private void putCellValue(int row, String columnCaption, String value) {
        Map oneRow = rows.get(row);
        if (oneRow.containsKey(columnCaption)) {
            oneRow.replace(columnCaption, value);
        } else {
            oneRow.put(columnCaption, value);
        }
    }


    private String getCellValue(Map<String, String> row, int colNum, String defaultValue) {
        for (String k : row.keySet()) {
            if (getColumnIndex(k) == colNum) {
                return row.get(k);
            }
        }
        return defaultValue;
    }
}
