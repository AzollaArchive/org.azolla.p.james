/*
 * @(#)ProcesserHelper.java		Created at 15/9/5
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.processer;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.l.ling.lang.Char0;
import org.azolla.p.james.processer.impl.CertificateNameList2CodeProcesser;

import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ProcesserHelper
{
    public static final Map<String, Processer> processerMap = Maps.newHashMap();

    static
    {
        processerMap.put(CertificateNameList2CodeProcesser.PROCESSER_NAME, new CertificateNameList2CodeProcesser());
    }

    public static List<Processer> parse(String s)
    {
        List<Processer> rtnList = Lists.newArrayList();
        if (!Strings.isNullOrEmpty(s))
        {
            String[] processerArray = s.split(String.valueOf(Char0.SEMICOLON));
            for (String processerString : processerArray)
            {
                //isDate:YYYY-MM-DD
                String[] processArray = processerString.split(String.valueOf(Char0.COLON));
                Processer processer = null;
                if(processArray.length > 0)
                {
                    processer = processerMap.get(processArray[0]);
                }
                if(processer != null)
                {
                    processer = processer.new0();
                    rtnList.add(processer);
                }
            }
        }
        return rtnList;
    }
}
