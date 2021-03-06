//- Copyright � 2008-2009 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.background;

import limelight.util.NanoTimer;
import limelight.Context;

public abstract class Animation
{
  private static final int MaxMakeups = 5;
  
  private long delayNanos;
  private final NanoTimer timer = new NanoTimer();
  private boolean running;
  private long tolerableDelay;

  public Animation(double updatesPerSecond)
  {
    setUpdatesPerSecond(updatesPerSecond);
    timer.setMark(delayNanos + 1);
  }

  protected abstract void doUpdate();

  public void setUpdatesPerSecond(double updatesPerSecond)
  {
    delayNanos = (long)(1000000000.0 / updatesPerSecond);
    tolerableDelay = (long)(delayNanos * 0.95);
  }

  public long getDelayNanos()
  {
    return delayNanos;
  }

  public void update()
  {
    makeupMissedUpdates();
    timer.markTime();
    doUpdate();
  }

  public boolean isReady()
  {
    long nanos = nanosSinceLastUpdate();
    return nanos >= tolerableDelay;
  }

  public long nanosSinceLastUpdate()
  {
    return timer.getIdleNanos();
  }

  private void makeupMissedUpdates()
  {
    long missedUpdates = nanosSinceLastUpdate() / delayNanos;
    for(int i = 1; i < missedUpdates && i < (MaxMakeups + 1); i++)
    {
      doUpdate();
    }
  }

  public void resetTimer()
  {
    timer.markTime();
  }

  public NanoTimer getTimer()
  {
    return timer;
  }

  public void start()
  {
    running = true;
    AnimationLoop loop = Context.instance().animationLoop;
    loop.add(this);
    loop.go();
  }

  public void stop()
  {
    Context.instance().animationLoop.remove(this);
    running = false;
  }

  public boolean isRunning()
  {
    return running;
  }

  public long getTolerableDelay()
  {
    return tolerableDelay;
  }
}
