/*
 * @(#)I18N.java		Created at 15/8/27
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.i18n;

import org.azolla.l.ling.cfg.PropCfg;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class I18N
{
    public static PropCfg propCfg = PropCfg.cfg("i18n.james.p.Azolla.Cfg.properties");;

    public static String get(String key)
    {
        return propCfg.get(key);
    }
}
