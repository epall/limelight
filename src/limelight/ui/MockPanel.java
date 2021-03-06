//- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.ui;

import limelight.ui.model.BasePanel;
import limelight.util.Box;
import limelight.styles.Style;
import limelight.styles.FlatStyle;

import java.awt.*;

public class MockPanel extends BasePanel
{
  public final FlatStyle style;
  public static int paintCount;
  public int paintIndex;
  public boolean wasPainted;
  public boolean canBeBuffered;
  public boolean changeMarkerWasReset;
  public boolean wasLaidOut;
  public boolean floater;
  public boolean consumableAreaChangedCalled;

  public MockPanel()
  {
    style = new FlatStyle();
    canBeBuffered = true;
  }

  public Box getChildConsumableArea()
  {
    return new Box(0, 0, getWidth(), getHeight());
  }

  public Box getBoxInsidePadding()
  {
    return getChildConsumableArea();
  }

  public Style getStyle()
  {
    return style;
  }

  public void paintOn(Graphics2D graphics)
  {
    wasPainted = true;
    paintIndex = paintCount++;
  }

  public boolean canBeBuffered()
  {
    return canBeBuffered;
  }

  public void doLayout()
  {
    super.doLayout();
    wasLaidOut = true;
  }

  public void stubAbsoluteLocation(Point location)
  {
    absoluteLocation = location;
  }

  public boolean isFloater()
  {
    return floater;
  }

  @Override
  public void consumableAreaChanged()
  {
    consumableAreaChangedCalled = true;
  }
}
