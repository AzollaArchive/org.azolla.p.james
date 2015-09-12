/*
 * @(#)DBTableBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.azolla.l.ling.lang.Char0;
import org.azolla.l.ling.lang.String0;
import org.azolla.p.james.util.Cons;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class DBTableBo
{
    private String dbTable;

    private String eSheet;

    private List<DBColumnBo> dbColumnBoList = Lists.newArrayList();

    private String sqlTemplate = "insert into {0}({1}) select {2};";
    ;

    public List<String> getSqlList()
    {
        List<String> rtnList = Lists.newArrayList();

        ExcelSheetBo excelSheetBo = JamesBo.single().getStringExcelSheetBoMap().get(eSheet);

        List<String[]> rowList = excelSheetBo.getNoErrorRowList();
        rowList = separator(rowList, excelSheetBo);
        rtnList.addAll(generateSqlList(rowList, excelSheetBo));

        return rtnList;
    }

    public List<String> generateSqlList(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        List<String> sqlList = Lists.newArrayList();

        for (String[] colArray : rowList)
        {
            sqlList.add(generateSql(colArray, excelSheetBo));
        }

        return sqlList;
    }

    public String generateSql(String[] colArray, ExcelSheetBo excelSheetBo)
    {
        StringBuffer colStringBuffer = new StringBuffer();
        StringBuffer valStringBuffer = new StringBuffer();
        for (DBColumnBo dbColumnBo : dbColumnBoList)
        {
            if (!Cons.Y.equalsIgnoreCase(dbColumnBo.getDbSeq()))
            {
                colStringBuffer.append(String0.COMMA).append(dbColumnBo.getDbColumn());
                if (Strings.isNullOrEmpty(dbColumnBo.geteColumn()))
                {
                    valStringBuffer.append(String0.COMMA).append(Char0.SINGLE_QUOTATION).append(dbColumnBo.getDefaultValue()).append(Char0.SINGLE_QUOTATION);
                }
                else
                {
                    if (Strings.isNullOrEmpty(dbColumnBo.getDbColumnSql()))
                    {
                        valStringBuffer.append(String0.COMMA).append(Char0.SINGLE_QUOTATION).append(colArray[excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn())]).append(Char0.SINGLE_QUOTATION);
                    }
                    else
                    {
                        if (dbColumnBo.geteColumn().contains(String0.COMMA))
                        {
                            String[] eColArray = dbColumnBo.geteColumn().split(String0.COMMA);
                            String[] vColArray = new String[eColArray.length];
                            for (int i = 0; i < eColArray.length; i++)
                            {
                                vColArray[i] = colArray[excelSheetBo.getStringIntegerMap().get(eColArray[i])];
                            }
                            valStringBuffer.append(String0.COMMA).append(new MessageFormat(dbColumnBo.getDbColumnSql()).format(vColArray));
                        }
                        else
                        {
                            valStringBuffer.append(String0.COMMA).append(MessageFormat.format(dbColumnBo.getDbColumnSql(), colArray[excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn())]));
                        }
                    }
                }
            }
        }
        return MessageFormat.format(sqlTemplate, dbTable, colStringBuffer.toString().substring(1), valStringBuffer.toString().substring(1));
    }

    public List<String[]> separator(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        List<String[]> rtnList = rowList;
        String separator = null;
        Integer colIndex = null;
        for (DBColumnBo dbColumnBo : dbColumnBoList)
        {
            separator = dbColumnBo.getSeparator();
            colIndex = excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn());
            if (!Strings.isNullOrEmpty(separator) && colIndex != null)
            {
                rtnList = separator(rtnList, separator, colIndex);
            }
        }
        return rtnList;
    }

    public List<String[]> separator(@Nonnull List<String[]> rowList, @Nonnull String separator, @Nonnull Integer colIndex)
    {
        List<String[]> newRowList = Lists.newArrayList();
        String cell = null;
        for (String[] rowArray : rowList)
        {
            cell = rowArray[colIndex];
            if (cell != null && cell.contains(separator))
            {
                for (String s : cell.split(separator))
                {
                    String[] newRowArray = Arrays.copyOf(rowArray, rowArray.length);
                    newRowArray[colIndex] = s;
                    newRowList.add(newRowArray);
                }
            }
            else
            {
                newRowList.add(rowArray);
            }
        }
        return newRowList;
    }

    public String getDbTable()
    {
        return dbTable;
    }

    public DBTableBo setDbTable(String dbTable)
    {
        this.dbTable = dbTable;
        return this;
    }

    public String geteSheet()
    {
        return eSheet;
    }

    public DBTableBo seteSheet(String eSheet)
    {
        this.eSheet = eSheet;
        return this;
    }

    public List<DBColumnBo> getDbColumnBoList()
    {
        return dbColumnBoList;
    }

    public DBTableBo setDbColumnBoList(List<DBColumnBo> dbColumnBoList)
    {
        this.dbColumnBoList = dbColumnBoList;
        return this;
    }

    public DBTableBo addDbColumnBo(DBColumnBo dbColumnBo)
    {
        if (dbColumnBo != null)
        {
            return addDbColumnBoList(Lists.newArrayList(dbColumnBo));
        }
        return this;
    }

    public DBTableBo addDbColumnBoList(List<DBColumnBo> dbColumnBoList)
    {
        if (dbColumnBoList != null)
        {
            this.dbColumnBoList.addAll(dbColumnBoList);
        }
        return this;
    }
}
