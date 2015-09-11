/*
 * @(#)Female2FAndMale2MConverter.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.converter.impl;

import org.azolla.p.james.converter.Converter;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class Female2FAndMale2MConverter implements Converter
{
    public static final String CONVERTER_NAME = "Female2FAndMale2MConverter";

    public static final String FEMALE = "Female";
    public static final String F      = "F";
    public static final String MALE   = "Male";
    public static final String M      = "M";

    @Override
    public Boolean convert(String[][] dataArray, Integer row, Integer col)
    {
        Boolean rtnBoolean = true;
        if (FEMALE.equalsIgnoreCase(dataArray[row][col]))
        {
            dataArray[row][col] = F;
        }
        else if (MALE.equalsIgnoreCase(dataArray[row][col]))
        {
            dataArray[row][col] = M;
        }
        else
        {
            rtnBoolean = false;
        }
        return rtnBoolean;
    }

    public Converter new0()
    {
        return new Female2FAndMale2MConverter();
    }
}
