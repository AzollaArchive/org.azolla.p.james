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
public class MinValueValidater implements Validater
{
    public static final String VALIDATER_NAME = "minValue";

    private Integer minValue = Integer.MIN_VALUE;

    @Override
    public Boolean validate(String s)
    {
        return Strings.isNullOrEmpty(s) ? true : Integer0.isInt(s) ? minValue <= Integer.valueOf(s) : false;
    }

    @Override
    public Validater setFactor(String s)
    {
        this.minValue = Integer.valueOf(s);
        return this;
    }

    @Override
    public Validater new0()
    {
        return new MinValueValidater();
    }
}
