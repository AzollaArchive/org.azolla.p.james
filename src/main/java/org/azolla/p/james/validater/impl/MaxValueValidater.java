/*
 * @(#)MaxValueValidater.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.validater.impl;

import com.google.common.base.Strings;
import org.azolla.l.ling.lang.Integer0;
import org.azolla.p.james.validater.Validater;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class MaxValueValidater implements Validater
{
    public static final String VALIDATER_NAME = "maxValue";

    private Integer maxValue = Integer.MAX_VALUE;

    @Override
    public Boolean validate(String s)
    {
        return Strings.isNullOrEmpty(s) ? true : Integer0.isInt(s) ? maxValue >= Integer.valueOf(s) : false;
    }

    @Override
    public Validater setFactor(String s)
    {
        this.maxValue = Integer.valueOf(s);
        return this;
    }

    @Override
    public Validater new0()
    {
        return new MaxValueValidater();
    }
}
