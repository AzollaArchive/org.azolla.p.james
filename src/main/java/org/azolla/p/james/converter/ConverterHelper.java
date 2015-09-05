/*
 * @(#)ConverterHelper.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.converter;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.lang.Char0;
import org.azolla.p.james.converter.impl.Female2FAndMale2MConverter;

import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ConverterHelper
{
    public static final Map<String, Converter> converterMap = Maps.newHashMap();
    
    static
    {
        converterMap.put(Female2FAndMale2MConverter.CONVERTER_NAME, new Female2FAndMale2MConverter());
    }

    public static List<Converter> parse(String s)
    {
        List<Converter> rtnList = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(s))
        {
            String[] converterArray = s.split(String.valueOf(Char0.SEMICOLON));
            for (String converterString : converterArray)
            {
                //isDate:YYYY-MM-DD
                String[] convertArray = converterString.split(String.valueOf(Char0.COLON));
                Converter converter = null;
                if(convertArray.length > 0)
                {
                    converter = converterMap.get(convertArray[0]);
                }
                if(converter != null)
                {
                    converter = converter.new0();
                }
                rtnList.add(converter);
            }
        }
        return rtnList;
    }
}
