/*
 * @(#)DBColumnBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class DBColumnBo
{
    private String dbColumn;

    private String eColumn;

    private String dbSeq;

    private String dbColumnSql;

    private String bizKey;

    private String defaultValue;

    private String separator;

    public String getDbColumn()
    {
        return dbColumn;
    }

    public DBColumnBo setDbColumn(String dbColumn)
    {
        this.dbColumn = dbColumn;
        return this;
    }

    public String geteColumn()
    {
        return eColumn;
    }

    public DBColumnBo seteColumn(String eColumn)
    {
        this.eColumn = eColumn;
        return this;
    }

    public String getDbSeq()
    {
        return dbSeq;
    }

    public DBColumnBo setDbSeq(String dbSeq)
    {
        this.dbSeq = dbSeq;
        return this;
    }

    public String getDbColumnSql()
    {
        return dbColumnSql;
    }

    public DBColumnBo setDbColumnSql(String dbColumnSql)
    {
        this.dbColumnSql = dbColumnSql;
        return this;
    }

    public String getBizKey()
    {
        return bizKey;
    }

    public DBColumnBo setBizKey(String bizKey)
    {
        this.bizKey = bizKey;
        return this;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public DBColumnBo setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getSeparator()
    {
        return separator;
    }

    public DBColumnBo setSeparator(String separator)
    {
        this.separator = separator;
        return this;
    }
}
