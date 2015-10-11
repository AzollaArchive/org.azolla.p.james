/*
 * @(#)Startup.java		Created at 15/9/2
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.startup;

import org.azolla.l.ling.i18n.I18N0;
import org.azolla.l.ling.util.Log0;
import org.azolla.p.james.panel.JamesPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class Startup
{
    private static JFrame tzfeFrame = new JFrame();

    public static void main(String[] args)
    {
        //do nothing
        SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                //do nothing
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        Log0.info(Startup.class, "James starting...");
        //do nothing
        tzfeFrame.setLayout(new BorderLayout());
        tzfeFrame.add(JamesPanel.single(), BorderLayout.CENTER);
        refreshTitle(I18N0.i18n(Startup.class).get("version"));
        tzfeFrame.setSize(JamesPanel.W, JamesPanel.H);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        tzfeFrame.setLocation((int) (screen.getWidth() - JamesPanel.W) / 2, (int) (screen.getHeight() - JamesPanel.H) / 2);
        tzfeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tzfeFrame.setResizable(false);
        tzfeFrame.setVisible(true);
        Log0.info(Startup.class, "James started");
    }

    public static void refreshTitle(String state)
    {
        tzfeFrame.setTitle(I18N0.i18n(Startup.class).get("artifact") + " - " + state);
    }


}
