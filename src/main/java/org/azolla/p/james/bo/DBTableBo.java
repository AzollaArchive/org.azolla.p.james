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
import com.google.common.collect.Maps;
import org.azolla.l.ling.util.List0;
import org.azolla.p.james.processer.Processer;
import org.azolla.p.james.util.DBCons;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    private String dtSqlMode;

    private List<DBColumnBo> dbColumnBoList = Lists.newArrayList();

    private List<String[]> needDeleteRowIndexList = Lists.newArrayList();
    private List<String[]> needUpdateRowIndexList = Lists.newArrayList();
    private List<String[]> needInsertRowIndexList = Lists.newArrayList();

    public List<String> getSqlList()
    {
        List<String> rtnList = Lists.newArrayList();

        ExcelSheetBo excelSheetBo = JamesBo.single().getStringExcelSheetBoMap().get(eSheet);

        List<String[]> rowList = excelSheetBo.getNoErrorRowList();
        rowList = separator(rowList, excelSheetBo);
        rowList = processer(rowList, excelSheetBo);
        keySql(rowList, excelSheetBo);
        rtnList.addAll(generateSqlList(excelSheetBo));

        return rtnList;
    }

    public List<String> generateSqlList(ExcelSheetBo excelSheetBo)
    {
        List<String> sqlList = Lists.newArrayList();

        sqlList.addAll(generateDeleteSqlList(excelSheetBo));
        sqlList.addAll(generateUpdateSqlList(excelSheetBo));
        sqlList.addAll(generateInsertSqlList(excelSheetBo));

        return sqlList;
    }

    public List<String> generateInsertSqlList(ExcelSheetBo excelSheetBo)
    {
        List<String> sqlList = Lists.newArrayList();

        Map<Integer, DBColumnBo> columnMap = Maps.newHashMap();
        Map<Integer, Integer> columnIndexMap = Maps.newHashMap();
        int i = 0;
        for(DBColumnBo dbColumnBo : dbColumnBoList)
        {
            columnMap.put(i, dbColumnBo);
            if(!Strings.isNullOrEmpty(dbColumnBo.geteColumn()))
            {
                columnIndexMap.put(i, excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn()));
            }
            i ++;
        }
        if(i != 0)
        {
            StringBuffer colSb = new StringBuffer();
            colSb.append("insert into ").append(dbTable).append(" (").append(columnMap.get(0).getDbColumn());
            for(int j = 1; j < i; j ++)
            {
                colSb.append(",").append(columnMap.get(j).getDbColumn());
            }
            colSb.append(") values (");

            String colStr = colSb.toString();
            StringBuffer sqlSb = null;
            //TODO
            for(String[] rowArray : needInsertRowIndexList)
            {
                sqlSb = new StringBuffer(colStr).append(columnMap.get(0).getStringWithDataType(rowArray[columnIndexMap.get(0)]));
                for(int j = 1; j < i; j ++)
                {
                    Integer colIndex = columnIndexMap.get(j);
                    sqlSb.append(",").append(columnMap.get(j).getStringWithDataType(colIndex == null ? null : rowArray[colIndex]));
                }
                sqlSb.append(")");
                sqlList.add(sqlSb.toString());
            }
        }

        return sqlList;
    }

    public List<String> generateUpdateSqlList(ExcelSheetBo excelSheetBo)
    {
        List<String> sqlList = Lists.newArrayList();

        //TODO

        return sqlList;
    }

    public List<String> generateDeleteSqlList(ExcelSheetBo excelSheetBo)
    {
        List<String> sqlList = Lists.newArrayList();

        //TODO

        return sqlList;
    }

    public void keySql(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        List<String[]> existList = exist(rowList, excelSheetBo);
        if(DBCons.SQLMODE_D_I.equalsIgnoreCase(dtSqlMode))
        {
            needDeleteRowIndexList.addAll(existList);
            needInsertRowIndexList.addAll(rowList);
        }
        else if(DBCons.SQLMODE_U_I.equalsIgnoreCase(dtSqlMode))
        {
            needUpdateRowIndexList.addAll(existList);
            needInsertRowIndexList.addAll(List0.listNotExistInOther(rowList,existList));
        }
        else
        {
            needInsertRowIndexList.addAll(rowList);
        }
    }

    public List<String[]> exist(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        List<String[]> rtnList = Lists.newArrayList();

        Map<Integer, DBColumnBo> keyColumnMap = Maps.newHashMap();
        List<String> keyColumnSqlList = Lists.newArrayList();
        List<Integer> keyColumnIndexList = Lists.newArrayList();
        int i = 0;
        for(DBColumnBo dbColumnBo : dbColumnBoList)
        {
            if(dbColumnBo.getDcKey() == true)
            {
                keyColumnMap.put(i, dbColumnBo);
                keyColumnSqlList.add(i, dbColumnBo.geteColumn() + " = ?");
                keyColumnIndexList.add(i, excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn()));
                i ++;
            }
        }
        if(i != 0)
        {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ").append(dbTable).append(" where ");
            sql.append(Joiner.on(" and ").join(keyColumnSqlList));
            //TODO
            for(int j = 0; j < i; j ++)
            {
                //TODO set
            }
        }

        return rtnList;
    }

    public List<String[]> processer(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        for(DBColumnBo dbColumnBo : dbColumnBoList)
        {
            final Integer colIndex = excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn());
            for(Processer processer : dbColumnBo.getProcesserList())
            {
                if(processer.getProcesserName().contains("List"))
                {
                    Map<String, String> colMap = processer.process(Lists.transform(rowList, new Function<String[], String>()
                    {
                        @Nullable
                        @Override
                        public String apply(String[] input)
                        {
                            return input[colIndex];
                        }
                    }));
                    for(String[] rowArray : rowList)
                    {
                        rowArray[colIndex] = colMap.get(rowArray[colIndex]);
                    }
                }
                else
                {
                    for(String[] rowArray : rowList)
                    {
                        rowArray[colIndex] = processer.process(rowArray[colIndex]).get(rowArray[colIndex]);
                    }
                }
            }
        }

        return rowList;
    }

    public List<String[]> separator(List<String[]> rowList, ExcelSheetBo excelSheetBo)
    {
        List<String[]> rtnList = rowList;
        String separator = null;
        Integer colIndex = null;
        for(DBColumnBo dbColumnBo : dbColumnBoList)
        {
            separator = dbColumnBo.getEcSeparator();
            colIndex = excelSheetBo.getStringIntegerMap().get(dbColumnBo.geteColumn());
            if(!Strings.isNullOrEmpty(separator) && colIndex != null)
            {
                rtnList = separator(rtnList, separator, colIndex);
            }
        }
        return rtnList;
    }

    public List<String[]> separator(@Nonnull List<String[]> rowList,@Nonnull String separator,@Nonnull Integer colIndex)
    {
        List<String[]> newRowList = Lists.newArrayList();
        String cell = null;
        for(String[] rowArray : rowList)
        {
            cell = rowArray[colIndex];
            if(cell != null && cell.contains(separator))
            {
                for(String s : cell.split(separator))
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

    public String getDtSqlMode()
    {
        return dtSqlMode;
    }

    public DBTableBo setDtSqlMode(String dtSqlMode)
    {
        this.dtSqlMode = dtSqlMode;
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
        if(dbColumnBo != null)
        {
            return addDbColumnBoList(Lists.newArrayList(dbColumnBo));
        }
        return this;
    }

    public DBTableBo addDbColumnBoList(List<DBColumnBo> dbColumnBoList)
    {
        if(dbColumnBoList != null)
        {
            this.dbColumnBoList.addAll(dbColumnBoList);
        }
        return this;
    }
}
