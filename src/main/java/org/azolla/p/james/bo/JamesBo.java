/*
 * @(#)JamesBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.james.util.Cons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class JamesBo
{
    //SheetName:Bo
    private Map<String, ExcelSheetBo> stringExcelSheetBoMap = Maps.newHashMap();

    private List<DBTableBo> dbTableBoList = Lists.newArrayList();

    public static final JamesBo single()
    {
        return SigletonHolder.instance;
    }

    public void clear()
    {
        stringExcelSheetBoMap.clear();
        dbTableBoList.clear();
    }

    public void validate()
    {
        stringExcelSheetBoMap.values().forEach((excelSheetBo) -> {
            excelSheetBo.validate();
        });
    }

    public void convert()
    {
        stringExcelSheetBoMap.values().forEach((excelSheetBo) -> {
            excelSheetBo.convert();
        });
    }

    public void generateSql(File folder)
    {
        File sqlFile = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        for(DBTableBo dbTableBo : dbTableBoList)
        {
            sqlFile = File0.newFile(folder, "" + System.currentTimeMillis() + dbTableBo.getDbTable() + ".sql");
            try
            {
                fw = new FileWriter(sqlFile, false);
                bw = new BufferedWriter(fw);

                for(String sql : dbTableBo.getSqlList())
                {
                    bw.write(sql);
                    bw.newLine();
                }
                bw.flush();
            }
            catch (Exception e)
            {
                Log0.error(this.getClass(), e.toString(), e);
            }
            finally
            {
                Close0.close(bw);
                Close0.close(fw);
            }
        }
    }

    public Map<String, ExcelSheetBo> getStringExcelSheetBoMap()
    {
        return stringExcelSheetBoMap;
    }

    public JamesBo setStringExcelSheetBoMap(Map<String, ExcelSheetBo> stringExcelSheetBoMap)
    {
        this.stringExcelSheetBoMap = stringExcelSheetBoMap;
        return this;
    }

    public JamesBo addExcelSheetBo(ExcelSheetBo excelSheetBo)
    {
        if(excelSheetBo == null)
        {
            return this;
        }
        else
        {
            Map<String, ExcelSheetBo> newStringExcelSheetBoMap = Maps.newHashMap();
            newStringExcelSheetBoMap.put(excelSheetBo.getSheet(), excelSheetBo);
            return addStringExcelSheetBoMap(newStringExcelSheetBoMap);
        }
    }

    public JamesBo addStringExcelSheetBoMap(Map<String, ExcelSheetBo> stringExcelSheetBoMap)
    {
        this.stringExcelSheetBoMap.putAll(stringExcelSheetBoMap);
        return this;
    }

    public List<DBTableBo> getDbTableBoList()
    {
        return dbTableBoList;
    }

    public JamesBo setDbTableBoList(List<DBTableBo> dbTableBoList)
    {
        this.dbTableBoList = dbTableBoList;
        return this;
    }

    public JamesBo addDbTableBo(DBTableBo dbTableBo)
    {
        return dbTableBo == null ? this : addDbTableBoList(Lists.newArrayList(dbTableBo));
    }

    public JamesBo addDbTableBoList(List<DBTableBo> dbTableBoList)
    {
        if(dbTableBoList != null)
        {
            this.dbTableBoList.addAll(dbTableBoList);
        }
        return this;
    }

    public Boolean hasError()
    {
        Boolean rtnBoolean = false;
        for(ExcelSheetBo excelSheetBo : stringExcelSheetBoMap.values())
        {
            if(excelSheetBo.getErrorRowIndexMap().size() > 0)
            {
                rtnBoolean = true;
            }
        }
        return rtnBoolean;
    }

    public void recordError(File folder)
    {
        File errorLogFile = File0.newFile(folder, "error.log");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try
        {
            fw = new FileWriter(errorLogFile, false);
            bw = new BufferedWriter(fw);

            for(ExcelSheetBo excelSheetBo : stringExcelSheetBoMap.values())
            {
                for(Map.Entry<Integer, Integer> entry : excelSheetBo.getErrorRowIndexMap().entrySet())
                {
                    bw.write(excelSheetBo.getSheet() + ":" + (entry.getKey() + Cons.JAMES_DATA_TITLE_ROW_INDEX + 2) + "," + (entry.getValue() + 1));
                    bw.newLine();
                }
                bw.flush();
            }
        }
        catch (Exception e)
        {
            Log0.error(this.getClass(), e.toString(), e);
        }
        finally
        {
            Close0.close(bw);
            Close0.close(fw);
        }

    }

    private static class SigletonHolder
    {
        private static final JamesBo instance = new JamesBo();
    }
}
