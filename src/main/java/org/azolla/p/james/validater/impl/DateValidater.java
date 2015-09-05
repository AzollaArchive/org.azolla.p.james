/*
 * @(#)DateValidater.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.validater.impl;

import com.google.common.base.Strings;
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.james.validater.Validater;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class DateValidater implements Validater
{
    public static final String VALIDATER_NAME = "isDate";

    private String dataFormat = Date0.DATE_WITH_DASH;

    @Override
    public Boolean validate(String s)
    {
        Boolean rtnBoolean = true;
        if(!Strings.isNullOrEmpty(s))
        {
            try
            {
                new SimpleDateFormat(dataFormat).parse(s);
            }
            catch (ParseException e)
            {
                rtnBoolean = false;
                Log0.error(this.getClass(), e.toString(), e);
            }
        }
        return rtnBoolean;
    }

    @Override
    public Validater setFactor(String s)
    {
        dataFormat = s;
        return this;
    }

    @Override
    public Validater new0()
    {
        return new DateValidater();
    }
}
