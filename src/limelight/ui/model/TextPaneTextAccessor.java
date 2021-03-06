//- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.ui.model;

import limelight.LimelightError;

public class TextPaneTextAccessor implements TextAccessor
{
  private final PropPanel panel;
  private TextPanel textPane;

  public TextPaneTextAccessor(PropPanel panel)
  {
    this.panel = panel;
  }

  public void setText(String text) throws LimelightError
  {
    if(textPane == null)
    {
      if(text == null || text.length() == 0)
        return;
      if(panel.hasChildren())
        throw new LimelightError("You may only set text on empty props.");
      textPane = new TextPanel(panel, text);   
      panel.add(textPane);
      panel.sterilize();
    }
    else
      textPane.setText(text);
  }

  public String getText()
  {
    return textPane == null ? "" : textPane.getText();
  }
}
