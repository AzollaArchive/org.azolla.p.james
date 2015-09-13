/*
 * @(#)JamesPanel.java		Created at 15/9/2
 * 
 * Copyright (c) azolla.org All rights reserved.
 * Azolla PROPRIETARY/CONFIDENTIAL. Use is subject to license terms. 
 */
package org.azolla.p.james.panel;

import com.google.common.base.Strings;
import org.azolla.l.ling.io.File0;
import org.azolla.l.ling.lang.String0;
import org.azolla.l.ling.net.Url0;
import org.azolla.l.ling.util.Date0;
import org.azolla.l.ling.util.Log0;
import org.azolla.l.sunny.dlg.Msg;
import org.azolla.l.sunny.layout.GBC;
import org.azolla.l.sunny.task.UITasks;
import org.azolla.p.james.bo.ExcelSheetBo;
import org.azolla.p.james.bo.JamesBo;
import org.azolla.p.james.i18n.I18N;
import org.azolla.p.james.startup.Startup;
import org.azolla.p.james.util.ExcelParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.MessageFormat;
import java.util.concurrent.Callable;

/**
 * The coder is very lazy, nothing to write for this class
 *
 * @author sk@azolla.org
 * @since ADK1.0
 */
public class JamesPanel extends JPanel
{
    public static final int W = 600;
    public static final int H = 100;

    JTextField pathTextField          = new JTextField();
    JButton    selectButton           = new JButton(I18N.get("Select"));
    JButton    downloadTemplateButton = new JButton(I18N.get("DownloadTemplate"));
    JButton    generateSqlButton      = new JButton(I18N.get("GenerateSql"));

    private JamesPanel()
    {
        super();
        initUI();
        initListener();
    }

    public static final JamesPanel single()
    {
        return SigletonHolder.instance;
    }

    private void initListener()
    {
        selectButton.addActionListener((e) -> {
            JFileChooser jFileChooser = createJFileChooser();
            int result = jFileChooser.showOpenDialog(JamesPanel.single());
            if (result == JFileChooser.APPROVE_OPTION)
            {
                pathTextField.setText(jFileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        downloadTemplateButton.addActionListener((e) -> {
            JFileChooser jFileChooser = createJFileChooser();
            int result = jFileChooser.showSaveDialog(JamesPanel.single());
            if (result == JFileChooser.APPROVE_OPTION)
            {
                if (File0.copy(File0.newFile(Url0.getURL(I18N.get("TEMPLATE_NAME")).getPath()), jFileChooser.getSelectedFile()))
                {
                    Msg.info(JamesPanel.single(), I18N.get("MSG_OPERATION_SUCCESSFULLY"));
                }
                else
                {
                    Msg.error(JamesPanel.single(), I18N.get("MSG_OPERATION_FAILED"));
                }
            }
        });

        generateSqlButton.addActionListener((e) -> {
            File currentFile = null;
            if (!Strings.isNullOrEmpty(pathTextField.getText()))
            {
                currentFile = File0.newFile(pathTextField.getText());
            }
            if (currentFile != null && currentFile.exists())
            {
                final File finalCurrentFile = currentFile;
                try
                {
                    UITasks.createUITask(null, new Callable<Object>()
                    {
                        @Override
                        public Object call() throws Exception
                        {
                            Startup.refreshTitle(I18N.get("ING_PROCESS"));
                            JamesBo.single().clear();

                            ExcelParser.SIGLETON.parseJamesExcel(finalCurrentFile);
                            ExcelParser.SIGLETON.parseJamesDB(finalCurrentFile);
                            for(ExcelSheetBo excelSheetBo : JamesBo.single().getStringExcelSheetBoMap().values())
                            {
                                ExcelParser.SIGLETON.parseJamesData(finalCurrentFile, excelSheetBo);
                            }
                            JamesBo.single().validate();
                            JamesBo.single().convert();
                            File folder = File0.newFile(finalCurrentFile.getParentFile(), Date0.DATATIME());
                            folder.mkdirs();
                            JamesBo.single().generateSql(folder);
                            if(JamesBo.single().hasError())
                            {
                                JamesBo.single().recordError(folder);
                            }
                            return true;
                        }
                    }).execute();
                }
                catch (Exception exp)
                {
                    Log0.error(this.getClass(), exp.toString(), exp);
                    Msg.error(JamesPanel.single(), exp.toString());
                }
                finally
                {
                    Startup.refreshTitle(I18N.get("version"));
                }
            }
            else
            {
                Msg.error(JamesPanel.single(), MessageFormat.format(I18N.get("FMT_CANNOT_FIND"), pathTextField.getText()));
            }
        });
    }

    private JFileChooser createJFileChooser()
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(new FileNameExtensionFilter(I18N.get("FILE_DESC_EXCEL"), new String[]{I18N.get("FILE_SUFFIX_XLSX")}));
        File currentFile = null;
        if (!Strings.isNullOrEmpty(pathTextField.getText()))
        {
            currentFile = File0.newFile(pathTextField.getText());
        }
        if (currentFile != null && currentFile.exists())
        {
            jFileChooser.setCurrentDirectory(currentFile);
        }
        else
        {
            jFileChooser.setCurrentDirectory(File0.newFile(Url0.getURL(String0.SLASH).getPath()));
        }
        return jFileChooser;
    }

    private void initUI()
    {
        //do nothing
        setLayout(new GridBagLayout());
//        setBackground(new Color(192, 192, 192, 192));

        JLabel selectPathButtion = new JLabel(I18N.get("SelectPath"));
        add(selectPathButtion, GBC.grid(0, 0, 1, 1).weight(0, 0).anchor(GBC.EAST).fill(GBC.NONE).insets(8, 8, 1, 1));

        pathTextField.setEditable(false);
//        pathTextField.setFocusable(false);
        add(pathTextField, GBC.grid(1, 0, 8, 1).weight(8, 0).anchor(GBC.CENTER).fill(GBC.BOTH).insets(8, 1, 1, 1));

        add(selectButton, GBC.grid(9, 0, 1, 1).weight(0, 0).anchor(GBC.WEST).fill(GBC.NONE).insets(8, 1, 1, 8));

        add(new JPanel(), GBC.grid(0, 1, 4, 1).weight(4, 0).anchor(GBC.CENTER).fill(GBC.HORIZONTAL).insets(1, 8, 8, 1));
        add(downloadTemplateButton, GBC.grid(4, 1, 1, 1).weight(0, 0).anchor(GBC.EAST).fill(GBC.NONE).insets(1, 1, 8, 1));

        add(generateSqlButton, GBC.grid(5, 1, 1, 1).weight(0, 0).anchor(GBC.WEST).fill(GBC.NONE).insets(1, 1, 8, 1));
        add(new JPanel(), GBC.grid(6, 1, 3, 1).weight(3, 0).anchor(GBC.CENTER).fill(GBC.HORIZONTAL).insets(1, 1, 8, 1));
        add(new JPanel(), GBC.grid(9, 1, 1, 1).weight(0, 0).anchor(GBC.CENTER).fill(GBC.NONE).insets(1, 1, 8, 8));

    }

    private static class SigletonHolder
    {
        private static final JamesPanel instance = new JamesPanel();
    }
}
