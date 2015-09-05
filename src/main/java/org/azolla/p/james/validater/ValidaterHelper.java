/*
 * @(#)ValidaterHelper.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.validater;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.lang.Char0;
import org.azolla.p.james.validater.impl.*;

import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ValidaterHelper
{
    public static final Map<String, Validater> validaterMap = Maps.newHashMap();

    static
    {
        validaterMap.put(DateValidater.VALIDATER_NAME, new DateValidater());
        validaterMap.put(EmailValidater.VALIDATER_NAME, new EmailValidater());
        validaterMap.put(MaxLengthValidater.VALIDATER_NAME, new MaxLengthValidater());
        validaterMap.put(MaxValueValidater.VALIDATER_NAME, new MaxValueValidater());
        validaterMap.put(MinLengthValidater.VALIDATER_NAME, new MinLengthValidater());
        validaterMap.put(MinValueValidater.VALIDATER_NAME, new MinValueValidater());
        validaterMap.put(NumberValidater.VALIDATER_NAME, new NumberValidater());
    }

    public static List<Validater> parse(String s)
    {
        List<Validater> rtnList = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(s))
        {
            String[] validaterArray = s.split(String.valueOf(Char0.SEMICOLON));
            for (String validaterString : validaterArray)
            {
                //isDate:YYYY-MM-DD
                String[] validateArray = validaterString.split(String.valueOf(Char0.COLON));
                Validater validater = null;
                if(validateArray.length > 0)
                {
                    validater = validaterMap.get(validateArray[0]);
                }
                if(validater != null)
                {
                    validater = validater.new0();
                    if(validateArray.length > 1)
                    {
                        validater.setFactor(validateArray[1]);
                    }
                    rtnList.add(validater);
                }
            }
        }
        return rtnList;
    }
}
