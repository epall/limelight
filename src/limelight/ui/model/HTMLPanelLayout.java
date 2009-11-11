//- Copyright © 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.ui.model;

import limelight.ui.Panel;

public class HTMLPanelLayout implements Layout
{
  public static HTMLPanelLayout instance = new HTMLPanelLayout();

  public void doLayout(Panel thePanel)
  {
    HTMLPanel panel = (HTMLPanel) thePanel;
    panel.resetLayout();
    try
    {
      panel.compile();
    }
    catch(Exception e)
    {
      // Can fail if the graphics are not ready.
      panel.markAsNeedingLayout();
      panel.getParent().markAsNeedingLayout();
    }
  }

  public boolean overides(Layout other)
  {
    return true;
  }

  public void doLayout(Panel panel, boolean topLevel)
  {
    doLayout(panel);
  }
}
