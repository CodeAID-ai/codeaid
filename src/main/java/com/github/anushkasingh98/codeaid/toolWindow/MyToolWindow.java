package com.github.anushkasingh98.codeaid.toolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;

public class MyToolWindow implements ToolWindowFactory {
    public static final String ID = "CodeAID Document Generator";
    private static final Key<ToolWindow> MY_TOOL_WINDOW = Key.create("ToolWindow");

    /**
     * @param project
     * @return
     */
    @Override
    public boolean isApplicable(@NotNull Project project)
    {
        return ToolWindowFactory.super.isApplicable(project);
    }

    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow)
    {
        ToolWindow docsWindow = new ToolWindow(toolWindow);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(docsWindow.getContent(), null, false);
        content.putUserData(MY_TOOL_WINDOW, docsWindow);
        toolWindow.getContentManager().addContent(content);
    }

    /**
     * Perform additional initialization routine here.
     *
     * @param toolWindow
     */
    @Override
    public void init(@NotNull ToolWindow toolWindow) {
        ToolWindowFactory.super.init(toolWindow);
    }

    /**
     * Check if tool window (and its stripe button) should be visible after startup.
     *
     * @param project
     * @see ToolWindow#isAvailable()
     */
    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return ToolWindowFactory.super.shouldBeAvailable(project);
    }

    /**
     * Return custom anchor or null to use anchor defined in Tool Window Registration or customized by user.
     */
    @Override
    public @Nullable ToolWindowAnchor getAnchor() {
        return ToolWindowFactory.super.getAnchor();
    }

    /**
     * @return
     */
    @Override
    public @Nullable Icon getIcon() {
        return ToolWindowFactory.super.getIcon();
    }

    @Nullable
    public static DocsWindow getWindow(Project project) {
        ToolWindowManager instance = ToolWindowManagerEx.getInstance(project);
        ToolWindow toolWindow = instance.getToolWindow(DocsWindowFactory.ID);
        if (toolWindow != null) {
            if (!toolWindow.isShowStripeButton()) {
                toolWindow.show();
            }
            Content[] contents = toolWindow.getContentManager().getContents();
            if (contents.length > 0) {
                Content content = contents[0];
                return content.getUserData(MY_TOOL_WINDOW);
            }
        }
        return null;
    }

    /**
     * @param project
     * @param toolWindow
     */
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    }
}
