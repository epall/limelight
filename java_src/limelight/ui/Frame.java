package limelight.ui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JPanel
{
  private RootBlockPanel panel;

  public Frame(Block block)
  {
    super();
    panel = new RootBlockPanel(block, this);
  }

  public BlockPanel getPanel()
  {
    return panel;
  }

  public void setPanel(RootBlockPanel panel)
  {
    this.panel = panel;
  }

  public void doLayout()
  {
    setLocation(0, 0);
    panel.snapToSize();
    setSize(panel.getWidth(), panel.getHeight());
  }

  public void paint(Graphics g)
  {
    panel.paint((Graphics2D)g);
  }
}
