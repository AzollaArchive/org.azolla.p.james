/*
 * @(#)EmailValidater.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.validater.impl;

import com.google.common.base.Strings;
import org.azolla.p.james.validater.Validater;

import java.util.regex.Pattern;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class EmailValidater implements Validater
{
    public static final String VALIDATER_NAME = "isEmail";

    Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    @Override
    public Boolean validate(String s)
    {
        return Strings.isNullOrEmpty(s) ? true : p.matcher(s).matches();
    }

    @Override
    public Validater new0()
    {
        return new EmailValidater();
    }
}
