/*
 * @(#)ExcelSheetBoTest.java		Created at 15/9/4
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.bo;

import org.junit.Test;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class ExcelSheetBoTest
{
    @Test
    public void testStringArray()
    {
//        String[][] dataArray = new String[][]{};//error
        String[][] dataArray = new String[7][8];
        dataArray[3][4] = "3,4";
        System.out.println(dataArray.length);
        System.out.println(dataArray[0].length);
    }
}
