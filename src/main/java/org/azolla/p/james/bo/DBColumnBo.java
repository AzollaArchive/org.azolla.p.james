/*
 * @(#)DBColumnBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.azolla.p.james.processer.Processer;
import org.azolla.p.james.util.DBCons;

import java.util.List;

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

    private String dcDataType;

    private Boolean dcKey;

//    private Boolean dcNullSelective;

    private String dcDefaultValue;

    private String ecSeparator;

    private List<Processer> processerList = Lists.newArrayList();

    public String getStringWithDataType(String s)
    {
        String rtnString = s;
        if(Strings.isNullOrEmpty(s))
        {
            rtnString = "null";
        }
        else
        {
            if(dcDataType != null)
            {
                if(dcDataType.contains("?"))
                {
                    rtnString = dcDataType.replace("?",s);
                }
                else if(DBCons.DCDATATYPE_STRING.equalsIgnoreCase(dcDataType))
                {
                    rtnString = "'"+s+"'";
                }
                else
                {
                    rtnString = s;
                }
            }
            else
            {
                rtnString = "'"+s+"'";
            }
        }

        return rtnString;
    }

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

    public String getDcDataType()
    {
        return dcDataType;
    }

    public DBColumnBo setDcDataType(String dcDataType)
    {
        this.dcDataType = dcDataType;
        return this;
    }

    public Boolean getDcKey()
    {
        return dcKey;
    }

    public DBColumnBo setDcKey(Boolean dcKey)
    {
        this.dcKey = dcKey;
        return this;
    }

//    public Boolean getDcNullSelective()
//    {
//        return dcNullSelective;
//    }
//
//    public DBColumnBo setDcNullSelective(Boolean dcNullSelective)
//    {
//        this.dcNullSelective = dcNullSelective;
//        return this;
//    }

    public String getDcDefaultValue()
    {
        return dcDefaultValue;
    }

    public DBColumnBo setDcDefaultValue(String dcDefaultValue)
    {
        this.dcDefaultValue = dcDefaultValue;
        return this;
    }

    public String getEcSeparator()
    {
        return ecSeparator;
    }

    public DBColumnBo setEcSeparator(String ecSeparator)
    {
        this.ecSeparator = ecSeparator;
        return this;
    }

    public List<Processer> getProcesserList()
    {
        return processerList;
    }

    public DBColumnBo setProcesserList(List<Processer> processerList)
    {
        this.processerList = processerList;
        return this;
    }

    public DBColumnBo addProcesser(Processer processer)
    {
        if(processer != null)
        {
            return addProcesserList(Lists.newArrayList(processer));
        }
        return this;
    }

    public DBColumnBo addProcesserList(List<Processer> processerList)
    {
        if(processerList != null)
        {
            this.processerList.addAll(processerList);
        }
        return this;
    }
}
