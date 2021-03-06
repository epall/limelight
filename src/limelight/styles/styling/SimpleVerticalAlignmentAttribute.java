//- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.styles.styling;

import limelight.LimelightError;
import limelight.styles.abstrstyling.VerticalAlignmentAttribute;
import limelight.styles.VerticalAlignment;

import java.awt.*;

public class SimpleVerticalAlignmentAttribute implements VerticalAlignmentAttribute
{
  private final VerticalAlignment alignment;

  public SimpleVerticalAlignmentAttribute(VerticalAlignment alignment)
  {
    this.alignment = alignment;
  }

  public VerticalAlignment getAlignment()
  {
    return alignment;
  }

  public String toString()
  {
    if(alignment == VerticalAlignment.TOP)
      return "top";
    else if(alignment == VerticalAlignment.CENTER)
      return "center";
    else if(alignment == VerticalAlignment.BOTTOM)
      return "bottom";
    else
      throw new LimelightError("Unknown Vertical Alignment: " + alignment);
  }

  public boolean equals(Object obj)
  {
    if(obj instanceof SimpleVerticalAlignmentAttribute)
    {
      return alignment.equals(((SimpleVerticalAlignmentAttribute) obj).alignment);
    }
    return false;
  }

  public int getY(int consumed, Rectangle area)
  {
    if(alignment == VerticalAlignment.TOP)
      return area.y;
    else if(alignment == VerticalAlignment.CENTER)
      return area.y + ( (area.height - consumed) / 2 );
    else
      return area.y + area.height - consumed;
  }
}
