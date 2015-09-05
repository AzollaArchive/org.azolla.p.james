/*
 * @(#)SheetBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.util.Log0;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ExcelSheetBo
{
    private String sheet;

    //ColumnName:Bo
    private Map<String, ExcelColumnBo> stringExcelColumnBoMap = Maps.newHashMap();

    //ColumnName:Index
    private Map<String, Integer> stringIntegerMap = Maps.newHashMap();

    private String[][] dataArray = new String[][]{};

    private Map<Integer, Integer> errorRowIndexMap = Maps.newHashMap();

    public List<String[]> getNoErrorRowList()
    {
        List<String[]> noErrorRowList = Lists.newArrayList();

        Set<Integer> errorRowSet = errorRowIndexMap.keySet();
        int colLength = dataArray[0].length;
        for(int rowIndex = 0; rowIndex < dataArray.length && !errorRowSet.contains(rowIndex); rowIndex ++)
        {
            noErrorRowList.add(Arrays.copyOf(dataArray[rowIndex],colLength));
        }
        return noErrorRowList;
    }

    public void validate()
    {
        stringExcelColumnBoMap.values().forEach((excelColumnBo) -> {
            Integer colIndex = stringIntegerMap.get(excelColumnBo.getColumn());
            if (colIndex >= dataArray[0].length)
            {
                Log0.error(this.getClass(), "Can't find " + sheet + "." + excelColumnBo.getColumn() + "!");
            }
            else
            {
                excelColumnBo.getValidaterList().forEach((validater) -> {
                    for (int rowIndex = 0; rowIndex < dataArray.length && !errorRowIndexMap.keySet().contains(rowIndex); rowIndex++)
                    {
                        if(!validater.validate(dataArray[rowIndex][colIndex]))
                        {
                            errorRowIndexMap.put(rowIndex, colIndex);
                        }
                    }
                });
            }
        });
    }

    public void convert()
    {
        stringExcelColumnBoMap.values().forEach((excelColumnBo) -> {
            Integer colIndex = stringIntegerMap.get(excelColumnBo.getColumn());
            if (colIndex >= dataArray[0].length)
            {
                Log0.error(this.getClass(), "Can't find " + sheet + "." + excelColumnBo.getColumn() + "!");
            }
            else
            {
                excelColumnBo.getConverterList().forEach((converter) -> {
                    for (int rowIndex = 0; rowIndex < dataArray.length && !errorRowIndexMap.keySet().contains(rowIndex); rowIndex++)
                    {
                        if(!converter.convert(dataArray, rowIndex, colIndex))
                        {
                            errorRowIndexMap.put(rowIndex,colIndex);
                        }
                    }
                });
            }
        });
    }

    public String getSheet()
    {
        return sheet;
    }

    public ExcelSheetBo setSheet(String sheet)
    {
        this.sheet = sheet;
        return this;
    }

    public Map<String, ExcelColumnBo> getStringExcelColumnBoMap()
    {
        return stringExcelColumnBoMap;
    }

    public ExcelSheetBo setStringExcelColumnBoMap(Map<String, ExcelColumnBo> stringExcelColumnBoMap)
    {
        this.stringExcelColumnBoMap = stringExcelColumnBoMap;
        return this;
    }

    public ExcelSheetBo addExcelColumnBo(ExcelColumnBo excelColumnBo)
    {
        if(excelColumnBo == null)
        {
            return this;
        }
        Map<String, ExcelColumnBo> newStringExcelColumnBoMap = Maps.newHashMap();
        newStringExcelColumnBoMap.put(excelColumnBo.getColumn(), excelColumnBo);
        return addStringExcelColumnBoMap(newStringExcelColumnBoMap);
    }

    public ExcelSheetBo addStringExcelColumnBoMap(Map<String, ExcelColumnBo> stringExcelColumnBoMap)
    {
        this.stringExcelColumnBoMap.putAll(stringExcelColumnBoMap);
        return this;
    }

    public Map<String, Integer> getStringIntegerMap()
    {
        return stringIntegerMap;
    }

    public ExcelSheetBo setStringIntegerMap(Map<String, Integer> stringIntegerMap)
    {
        this.stringIntegerMap = stringIntegerMap;
        return this;
    }

    public ExcelSheetBo addStringInteger(String columnName, Integer columnIndex)
    {
        if(!Strings.isNullOrEmpty(columnName) && columnIndex != null)
        {
            stringIntegerMap.put(columnName, columnIndex);
        }
        return this;
    }

    public ExcelSheetBo addStringIntegerMap(Map<String, Integer> stringIntegerMap)
    {
        this.stringIntegerMap.putAll(stringIntegerMap);
        return this;
    }

    public String[][] getDataArray()
    {
        return dataArray;
    }

    public void setDataArray(String[][] dataArray)
    {
        this.dataArray = dataArray;
    }

    public Map<Integer, Integer> getErrorRowIndexMap()
    {
        return errorRowIndexMap;
    }

    public void setErrorRowIndexMap(Map<Integer, Integer> errorRowIndexMap)
    {
        this.errorRowIndexMap = errorRowIndexMap;
    }
}
