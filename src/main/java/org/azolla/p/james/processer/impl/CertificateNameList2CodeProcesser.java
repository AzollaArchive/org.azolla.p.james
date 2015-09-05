/*
 * @(#)CertificateName2CodeProcesser.java		Created at 15/9/5
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.processer.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.azolla.p.james.processer.Processer;

import java.util.List;
import java.util.Map;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class CertificateNameList2CodeProcesser implements Processer
{
    public static final String PROCESSER_NAME = "CertificateNameList2CodeProcesser";

    @Override
    public Map<String, String> process(String s)
    {
        Map<String, String> rtnMap = Maps.newHashMap();
        if(!Strings.isNullOrEmpty(s))
        {
            rtnMap.putAll(process(Lists.newArrayList(s)));
        }
        return rtnMap;
    }

    @Override
    public Map<String, String> process(List<String> sList)
    {
        Map<String, String> rtnMap = Maps.newHashMap();
        if(sList != null)
        {
            //FIXME
            for(String s : sList)
            {
                rtnMap.put(s,s);
            }
            //TODO
        }
        return rtnMap;
    }

    @Override
    public Processer new0()
    {
        return new CertificateNameList2CodeProcesser();
    }

    public String getProcesserName()
    {
        return PROCESSER_NAME;
    }
}
