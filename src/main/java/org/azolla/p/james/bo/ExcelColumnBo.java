/*
 * @(#)ExcelColumnBo.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import com.google.common.collect.Lists;
import org.azolla.p.james.converter.Converter;
import org.azolla.p.james.validater.Validater;

import java.util.List;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ExcelColumnBo
{
    private String column;

    private List<Validater> validaterList = Lists.newArrayList();

    private List<Converter> converterList = Lists.newArrayList();

    public String getColumn()
    {
        return column;
    }

    public ExcelColumnBo setColumn(String column)
    {
        this.column = column;
        return this;
    }

    public List<Validater> getValidaterList()
    {
        return validaterList;
    }

    public ExcelColumnBo setValidaterList(List<Validater> validaterList)
    {
        this.validaterList = validaterList;
        return this;
    }

    public ExcelColumnBo addValidater(Validater validater)
    {
        return validater == null ? this : addValidaterList(Lists.newArrayList(validater));
    }

    public ExcelColumnBo addValidaterList(List<Validater> validaterList)
    {
        if(validaterList != null)
        {
            this.validaterList.addAll(validaterList);
        }
        return this;
    }

    public List<Converter> getConverterList()
    {
        return converterList;
    }

    public ExcelColumnBo setConverterList(List<Converter> converterList)
    {
        this.converterList = converterList;
        return this;
    }

    public ExcelColumnBo addConverter(Converter converter)
    {
        return converter == null ? this : addConverterList(Lists.newArrayList(converter));
    }

    public ExcelColumnBo addConverterList(List<Converter> converterList)
    {
        if(converterList != null)
        {
            this.converterList.addAll(converterList);
        }
        return this;
    }
}
