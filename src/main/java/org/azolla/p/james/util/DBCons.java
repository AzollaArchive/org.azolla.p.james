/*
 * @(#)DBVo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.util;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class DBCons
{
    public static final String DBTABLE_COL_TITLE         = "DBTable";
    public static final String ESHEET_COL_TITLE          = "ESheet";
    public static final String DTSQLMODE_COL_TITLE       = "DTSqlMode";
    public static final String DBCOLUMN_COL_TITLE        = "DBColumn";
    public static final String ECOLUMN_COL_TITLE         = "EColumn";
    public static final String DCDATATYPE_COL_TITLE      = "DCDataType";
    public static final String ISDCKEY_COL_TITLE         = "isDCKey";
//    public static final String DCNULLSELECTIVE_COL_TITLE = "DCNullSelective";
    public static final String DCDEFAULTVALUE_COL_TITLE  = "DCDefaultValue";
    public static final String ECSEPARATOR_COL_TITLE     = "ECSeparator";
    public static final String DCPROCESSER_COL_TITLE     = "DCProcesser";


    public static final String DCDATATYPE_STRING     = "STRING";
    public static final String DCDATATYPE_INTEGER    = "INTEGER";
    public static final String DCDATATYPE_DOUBLE     = "DOUBLE";
    public static final String DCDATATYPE_DATE       = "DATE";
    public static final String DCDATATYPE_BIGDECIMAL = "BIGDECIMAL";
    public static final String DCDATATYPE_DEFAULT    = DCDATATYPE_STRING;

    public static final String ISDCKEY_Y       = "Y";
    public static final String ISDCKEY_N       = "N";
    public static final String ISDCKEY_DEFAULT = ISDCKEY_N;

//    public static final String DCNULLSELECTIVE_Y       = "Y";
//    public static final String DCNULLSELECTIVE_N       = "N";
//    public static final String DCNULLSELECTIVE_DEFAULT = DCNULLSELECTIVE_N;

    public static final String SQLMODE_I   = "INSERT";
    public static final String SQLMODE_D_I = "DELETE&INSERT";
    public static final String SQLMODE_U_I = "UPDATE&INSERT";

    public static Integer DBTABLE_COL_INDEX         = 0;
    public static Integer ESHEET_COL_INDEX          = 1;
    public static Integer DTSQLMODE_COL_INDEX       = 2;
    public static Integer DBCOLUMN_COL_INDEX        = 3;
    public static Integer ECOLUMN_COL_INDEX         = 4;
    public static Integer DCDATATYPE_COL_INDEX      = 5;
    public static Integer ISDCKEY_COL_INDEX         = 6;
//    public static Integer DCNULLSELECTIVE_COL_INDEX = 7;
    public static Integer DCDEFAULTVALUE_COL_INDEX  = 7;
    public static Integer ECSEPARATOR_COL_INDEX     = 8;
    public static Integer DCPROCESSER_COL_INDEX     = 9;

}
