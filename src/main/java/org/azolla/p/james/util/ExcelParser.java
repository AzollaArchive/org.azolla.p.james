/*
 * @(#)ExcelParser.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.util;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.azolla.l.ling.io.Close0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.james.bo.*;
import org.azolla.p.james.converter.ConverterHelper;
import org.azolla.p.james.processer.ProcesserHelper;
import org.azolla.p.james.validater.ValidaterHelper;

import java.io.File;
import java.io.FileInputStream;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public enum ExcelParser
{
    SIGLETON;

    public void parseJamesExcel(File excelFile)
    {
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(Cons.JAMES_EXCEL);
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFCell titleXSSFCell;
            XSSFRow jamesExcelXSSFRow = sheet.getRow(Cons.JAMES_EXCEL_TITLE_ROW_INDEX);
            for (int colIndex = 0; colIndex < jamesExcelXSSFRow.getPhysicalNumberOfCells(); colIndex++)
            {
                titleXSSFCell = jamesExcelXSSFRow.getCell(colIndex);

                if (titleXSSFCell == null)
                {
                    continue;
                }

                if (ExcelCons.SHEET_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    ExcelCons.SHEET_COL_INDEX = colIndex;
                }
                else if (ExcelCons.COLUMN_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    ExcelCons.COLUMN_COL_INDEX = colIndex;
                }
                else if (ExcelCons.VALIDATER_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    ExcelCons.VALIDATER_COL_INDEX = colIndex;
                }
                else if (ExcelCons.CONVERTER_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    ExcelCons.CONVERTER_COL_INDEX = colIndex;
                }
            }
            String lastSheet = null;
            String currentSheet = null;
            XSSFCell sheetXSSFCell;
            XSSFCell columnXSSFCell;
            XSSFCell validaterXSSFCell;
            XSSFCell converterXSSFCell;
            ExcelSheetBo excelSheetBo = null;
            ExcelColumnBo excelColumnBo;
            for (int rowIndex = Cons.JAMES_EXCEL_TITLE_ROW_INDEX + 1; rowIndex < rows; rowIndex++)
            {
                jamesExcelXSSFRow = sheet.getRow(rowIndex);
                if (jamesExcelXSSFRow == null)
                {
                    continue;
                }

                columnXSSFCell = jamesExcelXSSFRow.getCell(ExcelCons.COLUMN_COL_INDEX);
                if (columnXSSFCell == null)
                {
                    continue;
                }
                sheetXSSFCell = jamesExcelXSSFRow.getCell(ExcelCons.SHEET_COL_INDEX);
                validaterXSSFCell = jamesExcelXSSFRow.getCell(ExcelCons.VALIDATER_COL_INDEX);
                converterXSSFCell = jamesExcelXSSFRow.getCell(ExcelCons.CONVERTER_COL_INDEX);

                currentSheet = sheetXSSFCell == null ? lastSheet : sheetXSSFCell.toString();

                if (excelSheetBo == null || !currentSheet.equalsIgnoreCase(lastSheet))
                {
                    JamesBo.single().addExcelSheetBo(excelSheetBo);
                    excelSheetBo = new ExcelSheetBo().setSheet(currentSheet);
                    lastSheet = currentSheet;
                }
                excelColumnBo = new ExcelColumnBo();
                excelColumnBo.setColumn(columnXSSFCell.toString());
                excelColumnBo.addValidaterList(validaterXSSFCell == null ? null : ValidaterHelper.parse(validaterXSSFCell.toString()));
                excelColumnBo.addConverterList(converterXSSFCell == null ? null : ConverterHelper.parse(converterXSSFCell.toString()));

                excelSheetBo.addExcelColumnBo(excelColumnBo);
            }
            JamesBo.single().addExcelSheetBo(excelSheetBo);
        }
        catch (Exception ex)
        {
            Log0.error(this.getClass(), ex.toString(), ex);
        }
        finally
        {
            Close0.close(fileInputStream);
        }
    }

    public void parseJamesDB(File excelFile)
    {
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(Cons.JAMES_DB);
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFCell titleXSSFCell;
            XSSFRow jamesDBXSSFRow = sheet.getRow(Cons.JAMES_DB_TITLE_ROW_INDEX);
            for (int colIndex = 0; colIndex < jamesDBXSSFRow.getPhysicalNumberOfCells(); colIndex++)
            {
                titleXSSFCell = jamesDBXSSFRow.getCell(colIndex);

                if (titleXSSFCell == null)
                {
                    continue;
                }

                if (DBCons.DBTABLE_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DBTABLE_COL_INDEX = colIndex;
                }
                else if (DBCons.ESHEET_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.ESHEET_COL_INDEX = colIndex;
                }
                else if (DBCons.DTSQLMODE_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DTSQLMODE_COL_INDEX = colIndex;
                }
                else if (DBCons.DBCOLUMN_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DBCOLUMN_COL_INDEX = colIndex;
                }
                else if (DBCons.ECOLUMN_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.ECOLUMN_COL_INDEX = colIndex;
                }
                else if (DBCons.DCDATATYPE_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DCDATATYPE_COL_INDEX = colIndex;
                }
                else if (DBCons.ISDCKEY_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.ISDCKEY_COL_INDEX = colIndex;
                }
//                else if (DBCons.DCNULLSELECTIVE_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
//                {
//                    DBCons.DCNULLSELECTIVE_COL_INDEX = colIndex;
//                }
                else if (DBCons.DCDEFAULTVALUE_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DCDEFAULTVALUE_COL_INDEX = colIndex;
                }
                else if (DBCons.ECSEPARATOR_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.ECSEPARATOR_COL_INDEX = colIndex;
                }
                else if (DBCons.DCPROCESSER_COL_TITLE.equalsIgnoreCase(titleXSSFCell.toString()))
                {
                    DBCons.DCPROCESSER_COL_INDEX = colIndex;
                }
            }
            XSSFCell dbTableXSSFCell;
            XSSFCell eSheetXSSFCell;
            XSSFCell dtSqlMode;
            XSSFCell dbColumnXSSFCell;
            XSSFCell eColumnXSSFCell;
            XSSFCell dcDataTypeXSSFCell;
            XSSFCell isDCKeyXSSFCell;
//            XSSFCell dcNullSelectiveXSSFCell;
            XSSFCell ecSeparatorXSSFCell;
            XSSFCell dcProcesserXSSFCell;

            DBTableBo dbTableBo = null;
            DBColumnBo dbColumnBo = null;
            for (int rowIndex = Cons.JAMES_DB_TITLE_ROW_INDEX + 1; rowIndex < rows; rowIndex++)
            {
                jamesDBXSSFRow = sheet.getRow(rowIndex);
                if (jamesDBXSSFRow == null)
                {
                    //new
                    if (dbTableBo == null)
                    {
                        continue;
                    }
                    else
                    {
                        JamesBo.single().addDbTableBo(dbTableBo);
                    }
                    dbTableXSSFCell = null;
                    eSheetXSSFCell = null;
                    dtSqlMode = null;
                    dbColumnXSSFCell = null;
                    eColumnXSSFCell = null;
                    dcDataTypeXSSFCell = null;
                    isDCKeyXSSFCell = null;
//                    dcNullSelectiveXSSFCell = null;
                    ecSeparatorXSSFCell = null;
                    dcProcesserXSSFCell = null;

                    dbTableBo = null;
                    dbColumnBo = null;
                    continue;
                }

                dbColumnXSSFCell = jamesDBXSSFRow.getCell(DBCons.DBCOLUMN_COL_INDEX);
                if (dbColumnXSSFCell == null)
                {
                    continue;
                }
                else
                {
                    dbTableXSSFCell = jamesDBXSSFRow.getCell(DBCons.DBTABLE_COL_INDEX);
                    eSheetXSSFCell = jamesDBXSSFRow.getCell(DBCons.ESHEET_COL_INDEX);
                    dtSqlMode = jamesDBXSSFRow.getCell(DBCons.DTSQLMODE_COL_INDEX);
                    eColumnXSSFCell = jamesDBXSSFRow.getCell(DBCons.ECOLUMN_COL_INDEX);
                    dcDataTypeXSSFCell = jamesDBXSSFRow.getCell(DBCons.DCDATATYPE_COL_INDEX);
                    isDCKeyXSSFCell = jamesDBXSSFRow.getCell(DBCons.ISDCKEY_COL_INDEX);
//                    dcNullSelectiveXSSFCell = jamesDBXSSFRow.getCell(DBCons.DCNULLSELECTIVE_COL_INDEX);
                    ecSeparatorXSSFCell = jamesDBXSSFRow.getCell(DBCons.ECSEPARATOR_COL_INDEX);
                    dcProcesserXSSFCell = jamesDBXSSFRow.getCell(DBCons.DCPROCESSER_COL_INDEX);

                    if (dbTableBo == null)
                    {
                        dbTableBo = new DBTableBo().setDbTable(dbTableXSSFCell.toString()).seteSheet(eSheetXSSFCell.toString()).setDtSqlMode(dtSqlMode.toString());
                    }
                    dbColumnBo = new DBColumnBo().setDbColumn(dbColumnXSSFCell.toString());
                    if (eColumnXSSFCell != null)
                    {
                        dbColumnBo.seteColumn(eColumnXSSFCell.toString());
                    }
                    dbColumnBo.setDcDataType(dcDataTypeXSSFCell == null ? DBCons.DCDATATYPE_DEFAULT : dcDataTypeXSSFCell.toString());
                    dbColumnBo.setDcKey(isDCKeyXSSFCell == null ? false : (DBCons.ISDCKEY_Y.equalsIgnoreCase(isDCKeyXSSFCell.toString()) ? true : false));
//                    dbColumnBo.setDcNullSelective(dcNullSelectiveXSSFCell == null ? false : (DBCons.DCNULLSELECTIVE_Y.equalsIgnoreCase(dcNullSelectiveXSSFCell.toString()) ? true : false));
                    if (ecSeparatorXSSFCell != null)
                    {
                        dbColumnBo.setEcSeparator(ecSeparatorXSSFCell.toString());
                    }
                    if (dcProcesserXSSFCell != null)
                    {
                        dbColumnBo.addProcesserList(ProcesserHelper.parse(dcProcesserXSSFCell.toString()));
                    }
                    dbTableBo.addDbColumnBo(dbColumnBo);
                }
            }
            dbTableBo.addDbColumnBo(dbColumnBo);
            JamesBo.single().addDbTableBo(dbTableBo);
        }
        catch (Exception ex)
        {
            Log0.error(this.getClass(), ex.toString(), ex);
        }
        finally
        {
            Close0.close(fileInputStream);
        }
    }

    public void parseJamesData(File excelFile, ExcelSheetBo excelSheetBo)
    {
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheet(excelSheetBo.getSheet());
            int rows = sheet.getPhysicalNumberOfRows();
            XSSFCell titleXSSFCell;
            XSSFRow jamesDataXSSFRow = sheet.getRow(Cons.JAMES_DATA_TITLE_ROW_INDEX);
            int cols = jamesDataXSSFRow.getPhysicalNumberOfCells();
            for (int colIndex = 0; colIndex < cols; colIndex++)
            {
                titleXSSFCell = jamesDataXSSFRow.getCell(colIndex);

                if (titleXSSFCell == null)
                {
                    continue;
                }

                for (String col : excelSheetBo.getStringExcelColumnBoMap().keySet())
                {
                    if (col.equalsIgnoreCase(titleXSSFCell.toString()))
                    {
                        excelSheetBo.addStringInteger(col, colIndex);
                        break;
                    }
                }
            }

            String[][] dataArray = new String[rows - Cons.JAMES_DATA_TITLE_ROW_INDEX - 1][cols];
            XSSFCell dataXSSFCell;
            for (int rowIndex = Cons.JAMES_DATA_TITLE_ROW_INDEX + 1; rowIndex < rows; rowIndex++)
            {
                jamesDataXSSFRow = sheet.getRow(rowIndex);
                if (jamesDataXSSFRow == null)
                {
                    continue;
                }

                for (int colIndex = 0; colIndex < cols; colIndex++)
                {
                    dataXSSFCell = jamesDataXSSFRow.getCell(colIndex);

                    if (dataXSSFCell == null)
                    {
                        continue;
                    }

                    dataArray[rowIndex - Cons.JAMES_DATA_TITLE_ROW_INDEX - 1][colIndex] = dataXSSFCell.toString();
                }
            }
            excelSheetBo.setDataArray(dataArray);
        }
        catch (Exception ex)
        {
            Log0.error(this.getClass(), ex.toString(), ex);
        }
        finally
        {
            Close0.close(fileInputStream);
        }
    }
}
