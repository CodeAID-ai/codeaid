package com.github.anushkasingh98.codeaid;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.util.text.StringUtil;
import org.apache.tools.ant.Project;

import javax.swing.*;

public class CaptureSelectedCodeAction extends AnAction
{
    @Override
    public void actionPerformed(AnActionEvent e)
    {
        Editor editor = CommonDataKeys.EDITOR.getData(e.getDataContext());
        String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText != null) {
            selectedText = StringUtil.escapeStringCharacters(selectedText);
            System.out.println(selectedText);
        }
        else{
            System.out.println("Error: OOPS!");
        }
    }
}
