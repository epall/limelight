//- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.styles.styling;

import limelight.styles.abstrstyling.StyleAttribute;
import limelight.styles.abstrstyling.ColorAttribute;

import java.awt.*;

public class SimpleColorAttribute implements ColorAttribute
{
  private final Color color;

  public SimpleColorAttribute(Color color)
  {
    this.color = color;
  }

  public Color getColor()
  {
    return color;
  }

  public String toString()
  {
    String r = Integer.toHexString(color.getRed());
    String g = Integer.toHexString(color.getGreen());
    String b = Integer.toHexString(color.getBlue());
    String a = Integer.toHexString(color.getAlpha());
    StringBuffer buffer = new StringBuffer("#");
    if(r.length() == 1)
      buffer.append("0");
    buffer.append(r);
    if(g.length() == 1)
      buffer.append("0");
    buffer.append(g);
    if(b.length() == 1)
      buffer.append("0");
    buffer.append(b);
    if(a.length() == 1)
      buffer.append("0");
    buffer.append(a);
    return buffer.toString();
  }

  public boolean equals(Object obj)
  {
    if(obj instanceof SimpleColorAttribute)
    {
      return color.equals( ((SimpleColorAttribute)obj).color );
    }
    return false;
  }
}
