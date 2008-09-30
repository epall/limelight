//- Copyright 2008 8th Light, Inc. All Rights Reserved.
//- Limelight and all included source files are distributed under terms of the GNU LGPL.

package limelight.styles;

import limelight.Context;
import limelight.styles.abstrstyling.*;

import java.util.LinkedList;

public abstract class Style
{
  public static final LinkedList<StyleDescriptor> STYLE_LIST = new LinkedList<StyleDescriptor>();

  public static StyleDescriptor descriptor(String name, String compilerType, String defaultValue)
  {
    StyleAttributeCompiler compiler = Context.instance().styleAttributeCompilerFactory.compiler(compilerType);
    compiler.setName(name);
    StyleDescriptor descriptor = new StyleDescriptor(STYLE_LIST.size(), name, compiler, compiler.compile(defaultValue));
    STYLE_LIST.add(descriptor);
    return descriptor;
  }

  public static final StyleDescriptor WIDTH = descriptor("Width", "dimension", "auto");
  public static final StyleDescriptor HEIGHT = descriptor("Height", "dimension", "auto");
  public static final StyleDescriptor MIN_WIDTH = descriptor("Min Width", "noneable integer", "none");
  public static final StyleDescriptor MIN_HEIGHT = descriptor("Min Height", "noneable integer", "none");
  public static final StyleDescriptor MAX_WIDTH = descriptor("Max Width", "noneable integer", "none");
  public static final StyleDescriptor MAX_HEIGHT = descriptor("Max Height", "noneable integer", "none");
  public static final StyleDescriptor VERTICAL_SCROLLBAR = descriptor("Vertical Scrollbar", "on/off", "off");
  public static final StyleDescriptor HORIZONTAL_SCROLLBAR = descriptor("Horizontal Scrollbar", "on/off", "off");
  public static final StyleDescriptor TOP_BORDER_COLOR = descriptor("Top Border Color", "color", "black");
  public static final StyleDescriptor RIGHT_BORDER_COLOR = descriptor("Right Border Color", "color", "black");
  public static final StyleDescriptor BOTTOM_BORDER_COLOR = descriptor("Bottom Border Color", "color", "black");
  public static final StyleDescriptor LEFT_BORDER_COLOR = descriptor("Left Border Color", "color", "black");
  public static final StyleDescriptor TOP_BORDER_WIDTH = descriptor("Top Border Width", "integer", "0");
  public static final StyleDescriptor RIGHT_BORDER_WIDTH = descriptor("Right Border Width", "integer", "0");
  public static final StyleDescriptor BOTTOM_BORDER_WIDTH = descriptor("Bottom Border Width", "integer", "0");
  public static final StyleDescriptor LEFT_BORDER_WIDTH = descriptor("Left Border Width", "integer", "0");
  public static final StyleDescriptor TOP_MARGIN = descriptor("Top Margin", "integer", "0");
  public static final StyleDescriptor RIGHT_MARGIN = descriptor("Right Margin", "integer", "0");
  public static final StyleDescriptor BOTTOM_MARGIN = descriptor("Bottom Margin", "integer", "0");
  public static final StyleDescriptor LEFT_MARGIN = descriptor("Left Margin", "integer", "0");
  public static final StyleDescriptor TOP_PADDING = descriptor("Top Padding", "integer", "0");
  public static final StyleDescriptor RIGHT_PADDING = descriptor("Right Padding", "integer", "0");
  public static final StyleDescriptor BOTTOM_PADDING = descriptor("Bottom Padding", "integer", "0");
  public static final StyleDescriptor LEFT_PADDING = descriptor("Left Padding", "integer", "0");
  public static final StyleDescriptor BACKGROUND_COLOR = descriptor("Background Color", "color", "transparent");
  public static final StyleDescriptor SECONDARY_BACKGROUND_COLOR = descriptor("Secondary Background Color", "color", "transparent");
  public static final StyleDescriptor BACKGROUND_IMAGE = descriptor("Background Image", "noneable string", "none");
  public static final StyleDescriptor BACKGROUND_IMAGE_FILL_STRATEGY = descriptor("Background Image Fill Strategy", "fill strategy", "repeat");
  public static final StyleDescriptor GRADIENT = descriptor("Gradient", "on/off", "off");
  public static final StyleDescriptor GRADIENT_ANGLE = descriptor("Gradient Angle", "degrees", "90");
  public static final StyleDescriptor GRADIENT_PENETRATION = descriptor("Gradient Penetration", "percentage", "100");
  public static final StyleDescriptor CYCLIC_GRADIENT = descriptor("Cyclic Gradient", "on/off", "off");
  public static final StyleDescriptor HORIZONTAL_ALIGNMENT = descriptor("Horizontal Alignment", "horizontal alignment", "left");
  public static final StyleDescriptor VERTICAL_ALIGNMENT = descriptor("Vertical Alignment", "vertical alignment", "top");
  public static final StyleDescriptor TEXT_COLOR = descriptor("Text Color", "color", "black");
  public static final StyleDescriptor FONT_FACE = descriptor("Font Face", "string", "Arial");
  public static final StyleDescriptor FONT_SIZE = descriptor("Font Size", "integer", "12");
  public static final StyleDescriptor FONT_STYLE = descriptor("Font Style", "font style", "plain");
  public static final StyleDescriptor TRANSPARENCY = descriptor("Transparency", "percentage", "0");
  public static final StyleDescriptor TOP_RIGHT_ROUNDED_CORNER_RADIUS = descriptor("Top Right Rounded Corner Radius", "integer", "0");
  public static final StyleDescriptor BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS = descriptor("Bottom Right Rounded Corner Radius", "integer", "0");
  public static final StyleDescriptor BOTTOM_LEFT_ROUNDED_CORNER_RADIUS = descriptor("Bottom Left Rounded Corner Radius", "integer", "0");
  public static final StyleDescriptor TOP_LEFT_ROUNDED_CORNER_RADIUS = descriptor("Top Left Rounded Corner Radius", "integer", "0");
  public static final StyleDescriptor TOP_RIGHT_BORDER_WIDTH = descriptor("Top Right Border Width", "integer", "0");
  public static final StyleDescriptor BOTTOM_RIGHT_BORDER_WIDTH = descriptor("Bottom Right Border Width", "integer", "0");
  public static final StyleDescriptor BOTTOM_LEFT_BORDER_WIDTH = descriptor("Bottom Left Border Width", "integer", "0");
  public static final StyleDescriptor TOP_LEFT_BORDER_WIDTH = descriptor("Top Left Border Width", "integer", "0");
  public static final StyleDescriptor TOP_RIGHT_BORDER_COLOR = descriptor("Top Right Border Color", "color", "black");
  public static final StyleDescriptor BOTTOM_RIGHT_BORDER_COLOR = descriptor("Bottom Right Border Color", "color", "black");
  public static final StyleDescriptor BOTTOM_LEFT_BORDER_COLOR = descriptor("Bottom Left Border Color", "color", "black");
  public static final StyleDescriptor TOP_LEFT_BORDER_COLOR = descriptor("Top Left Border Color", "color", "black");
  public static final StyleDescriptor FLOAT = descriptor("Float", "on/off", "off");
  public static final StyleDescriptor X = descriptor("X", "integer", "0");
  public static final StyleDescriptor Y = descriptor("Y", "integer", "0");

  protected static final int STYLE_COUNT = STYLE_LIST.size();

  protected abstract StyleAttribute get(int key);

  public abstract void put(StyleDescriptor descriptor, String value);

  public abstract void setDefault(StyleDescriptor descriptor, String value);

  protected abstract StyleAttribute getDefaultValue(StyleDescriptor descriptor);

  public abstract boolean changed();

  public abstract boolean changed(StyleDescriptor descriptor);

  public abstract void flushChanges();

  public abstract int getChangedCount();

  public abstract void removeObserver(StyleObserver observer);

  public abstract void addObserver(StyleObserver observer);

  public abstract boolean hasObserver(StyleObserver observer);

  public String get(StyleDescriptor descriptor)
  {
    return getCompiled(descriptor).toString();
  }

  public StyleAttribute getCompiled(StyleDescriptor descriptor)
  {
    StyleAttribute value = get(descriptor.index);
    if(value == null)
      return getDefaultValue(descriptor);
    else
      return value;
  }

  public int asInt(String value)
  {
    try
    {
      return Integer.parseInt(value);
    }
    catch(NumberFormatException e)
    {
      return 0;
    }
  }

  public boolean isOn(String value)
  {
    return "on".equals(value);
  }

  public void setWidth(String value)
  {
    put(WIDTH, value);
  }

  public String getWidth()
  {
    return get(WIDTH);
  }

  public DimensionAttribute getCompiledWidth()
  {
    return (DimensionAttribute)getCompiled(WIDTH);
  }

  public void setHeight(String value)
  {
    put(HEIGHT, value);
  }

  public String getHeight()
  {
    return get(HEIGHT);
  }

  public DimensionAttribute getCompiledHeight()
  {
    return (DimensionAttribute)getCompiled(HEIGHT);
  }

  public void setMinWidth(String value)
  {
    put(MIN_WIDTH, value);
  }

  public String getMinWidth()
  {
    return get(MIN_WIDTH);
  }

  public NoneableAttribute<IntegerAttribute> getCompiledMinWidth()
  {
    return (NoneableAttribute<IntegerAttribute>)getCompiled(MIN_WIDTH);
  }

  public void setMinHeight(String value)
  {
    put(MIN_HEIGHT, value);
  }

  public String getMinHeight()
  {
    return get(MIN_HEIGHT);
  }

  public NoneableAttribute<IntegerAttribute> getCompiledMinHeight()
  {
    return (NoneableAttribute<IntegerAttribute>)getCompiled(MIN_HEIGHT);
  }

  public void setMaxWidth(String value)
  {
    put(MAX_WIDTH, value);
  }

  public String getMaxWidth()
  {
    return get(MAX_WIDTH);
  }

  public NoneableAttribute<IntegerAttribute> getCompiledMaxWidth()
  {
    return (NoneableAttribute<IntegerAttribute>)getCompiled(MAX_WIDTH);
  }

  public void setMaxHeight(String value)
  {
    put(MAX_HEIGHT, value);
  }

  public String getMaxHeight()
  {
    return get(MAX_HEIGHT);
  }

  public NoneableAttribute<IntegerAttribute> getCompiledMaxHeight()
  {
    return (NoneableAttribute<IntegerAttribute>)getCompiled(MAX_HEIGHT);
  }

  public void setTextColor(String value)
  {
    put(TEXT_COLOR, value);
  }

  public String getTextColor()
  {
    return get(TEXT_COLOR);
  }

  public ColorAttribute getCompiledTextColor()
  {
    return (ColorAttribute)getCompiled(TEXT_COLOR);
  }

  public void setBorderColor(String value)
  {
    put(TOP_BORDER_COLOR, value);
    put(TOP_RIGHT_BORDER_COLOR, value);
    put(RIGHT_BORDER_COLOR, value);
    put(BOTTOM_RIGHT_BORDER_COLOR, value);
    put(BOTTOM_BORDER_COLOR, value);
    put(BOTTOM_LEFT_BORDER_COLOR, value);
    put(LEFT_BORDER_COLOR, value);
    put(TOP_LEFT_BORDER_COLOR, value);
  }

  public String getTopBorderColor()
  {
    return get(TOP_BORDER_COLOR);
  }

  public ColorAttribute getCompiledTopBorderColor()
  {
    return (ColorAttribute)getCompiled(TOP_BORDER_COLOR);
  }

  public String getRightBorderColor()
  {
    return get(RIGHT_BORDER_COLOR);
  }

  public ColorAttribute getCompiledRightBorderColor()
  {
    return (ColorAttribute)getCompiled(RIGHT_BORDER_COLOR);
  }

  public String getBottomBorderColor()
  {
    return get(BOTTOM_BORDER_COLOR);
  }

  public ColorAttribute getCompiledBottomBorderColor()
  {
    return (ColorAttribute)getCompiled(BOTTOM_BORDER_COLOR);
  }

  public String getLeftBorderColor()
  {
    return get(LEFT_BORDER_COLOR);
  }

  public ColorAttribute getCompiledLeftBorderColor()
  {
    return (ColorAttribute)getCompiled(LEFT_BORDER_COLOR);
  }

  public void setTopBorderColor(String value)
  {
    put(TOP_BORDER_COLOR, value);
  }

  public void setRightBorderColor(String value)
  {
    put(RIGHT_BORDER_COLOR, value);
  }

  public void setBottomBorderColor(String value)
  {
    put(BOTTOM_BORDER_COLOR, value);
  }

  public void setLeftBorderColor(String value)
  {
    put(LEFT_BORDER_COLOR, value);
  }

  public void setBorderWidth(String pixels)
  {
    put(TOP_BORDER_WIDTH, pixels);
    put(TOP_RIGHT_BORDER_WIDTH, pixels);
    put(RIGHT_BORDER_WIDTH, pixels);
    put(BOTTOM_RIGHT_BORDER_WIDTH, pixels);
    put(BOTTOM_BORDER_WIDTH, pixels);
    put(BOTTOM_LEFT_BORDER_WIDTH, pixels);
    put(LEFT_BORDER_WIDTH, pixels);
    put(TOP_LEFT_BORDER_WIDTH, pixels);
  }

  public String getTopBorderWidth()
  {
    return get(TOP_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledTopBorderWidth()
  {
    return (IntegerAttribute)getCompiled(TOP_BORDER_WIDTH);
  }

  public void setTopBorderWidth(String topBorderWidth)
  {
    put(TOP_BORDER_WIDTH, topBorderWidth);
  }

  public String getRightBorderWidth()
  {
    return get(RIGHT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledRightBorderWidth()
  {
    return (IntegerAttribute)getCompiled(RIGHT_BORDER_WIDTH);
  }

  public void setRightBorderWidth(String rightBorderWidth)
  {
    put(RIGHT_BORDER_WIDTH, rightBorderWidth);
  }

  public String getBottomBorderWidth()
  {
    return get(BOTTOM_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledBottomBorderWidth()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_BORDER_WIDTH);
  }

  public void setBottomBorderWidth(String bottomBorderWidth)
  {
    put(BOTTOM_BORDER_WIDTH, bottomBorderWidth);
  }

  public String getLeftBorderWidth()
  {
    return get(LEFT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledLeftBorderWidth()
  {
    return (IntegerAttribute)getCompiled(LEFT_BORDER_WIDTH);
  }

  public void setLeftBorderWidth(String leftBorderWidth)
  {
    put(LEFT_BORDER_WIDTH, leftBorderWidth);
  }

  public void setMargin(String margin)
  {
    put(TOP_MARGIN, margin);
    put(RIGHT_MARGIN, margin);
    put(BOTTOM_MARGIN, margin);
    put(LEFT_MARGIN, margin);
  }

  public String getTopMargin()
  {
    return get(TOP_MARGIN);
  }

  public IntegerAttribute getCompiledTopMargin()
  {
    return (IntegerAttribute)getCompiled(TOP_MARGIN);
  }

  public void setTopMargin(String topMargin)
  {
    put(TOP_MARGIN, topMargin);
  }

  public String getRightMargin()
  {
    return get(RIGHT_MARGIN);
  }

  public IntegerAttribute getCompiledRightMargin()
  {
    return (IntegerAttribute)getCompiled(RIGHT_MARGIN);
  }

  public void setRightMargin(String rightMargin)
  {
    put(RIGHT_MARGIN, rightMargin);
  }

  public String getBottomMargin()
  {
    return get(BOTTOM_MARGIN);
  }

  public IntegerAttribute getCompiledBottomMargin()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_MARGIN);
  }

  public void setBottomMargin(String bottomMargin)
  {
    put(BOTTOM_MARGIN, bottomMargin);
  }

  public String getLeftMargin()
  {
    return get(LEFT_MARGIN);
  }

  public IntegerAttribute getCompiledLeftMargin()
  {
    return (IntegerAttribute)getCompiled(LEFT_MARGIN);
  }

  public void setLeftMargin(String leftMargin)
  {
    put(LEFT_MARGIN, leftMargin);
  }

  public void setPadding(String padding)
  {
    put(TOP_PADDING, padding);
    put(RIGHT_PADDING, padding);
    put(BOTTOM_PADDING, padding);
    put(LEFT_PADDING, padding);
  }

  public String getTopPadding()
  {
    return get(TOP_PADDING);
  }

  public IntegerAttribute getCompiledTopPadding()
  {
    return (IntegerAttribute)getCompiled(TOP_PADDING);
  }

  public void setTopPadding(String topPadding)
  {
    put(TOP_PADDING, topPadding);
  }

  public String getRightPadding()
  {
    return get(RIGHT_PADDING);
  }

  public IntegerAttribute getCompiledRightPadding()
  {
    return (IntegerAttribute)getCompiled(RIGHT_PADDING);
  }

  public void setRightPadding(String rightPadding)
  {
    put(RIGHT_PADDING, rightPadding);
  }

  public String getBottomPadding()
  {
    return get(BOTTOM_PADDING);
  }

  public IntegerAttribute getCompiledBottomPadding()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_PADDING);
  }

  public void setBottomPadding(String bottomPadding)
  {
    put(BOTTOM_PADDING, bottomPadding);
  }

  public String getLeftPadding()
  {
    return get(LEFT_PADDING);
  }

  public IntegerAttribute getCompiledLeftPadding()
  {
    return (IntegerAttribute)getCompiled(LEFT_PADDING);
  }

  public void setLeftPadding(String leftPadding)
  {
    put(LEFT_PADDING, leftPadding);
  }

  public void setBackgroundColor(String backgroundColor)
  {
    put(BACKGROUND_COLOR, backgroundColor);
  }

  public String getBackgroundColor()
  {
    return get(BACKGROUND_COLOR);
  }

  public ColorAttribute getCompiledBackgroundColor()
  {
    return (ColorAttribute)getCompiled(BACKGROUND_COLOR);
  }

  public void setBackgroundImage(String backgroundImage)
  {
    put(BACKGROUND_IMAGE, backgroundImage);
  }

  public String getBackgroundImage()
  {
    return get(BACKGROUND_IMAGE);
  }

  public NoneableAttribute<StringAttribute> getCompiledBackgroundImage()
  {
    return (NoneableAttribute<StringAttribute>)getCompiled(BACKGROUND_IMAGE);
  }

  public String getBackgroundImageFillStrategy()
  {
    return get(BACKGROUND_IMAGE_FILL_STRATEGY);
  }

  public FillStrategyAttribute getCompiledBackgroundImageFillStrategy()
  {
    return (FillStrategyAttribute)getCompiled(BACKGROUND_IMAGE_FILL_STRATEGY);
  }

  public void setBackgroundImageFillStrategy(String backgroundImageFillStrategy)
  {
    put(BACKGROUND_IMAGE_FILL_STRATEGY, backgroundImageFillStrategy);
  }

  public void setGradient(String color)
  {
    put(GRADIENT, color);
  }

  public String getGradient()
  {
    return get(GRADIENT);
  }

  public OnOffAttribute getCompiledGradient()
  {
    return (OnOffAttribute)getCompiled(GRADIENT);
  }

  public void setSecondaryBackgroundColor(String color)
  {
    put(SECONDARY_BACKGROUND_COLOR, color);
  }

  public String getSecondaryBackgroundColor()
  {
    return get(SECONDARY_BACKGROUND_COLOR);
  }

  public ColorAttribute getCompiledSecondaryBackgroundColor()
  {
    return (ColorAttribute)getCompiled(SECONDARY_BACKGROUND_COLOR);
  }

  public void setGradientAngle(String value)
  {
    put(GRADIENT_ANGLE, value);
  }

  public String getGradientAngle()
  {
    return get(GRADIENT_ANGLE);
  }

  public DegreesAttribute getCompiledGradientAngle()
  {
    return (DegreesAttribute)getCompiled(GRADIENT_ANGLE);
  }

  public void setGradientPenetration(String value)
  {
    put(GRADIENT_PENETRATION, value);
  }

  public String getGradientPenetration()
  {
    return get(GRADIENT_PENETRATION);
  }

  public PercentageAttribute getCompiledGradientPenetration()
  {
    return (PercentageAttribute)getCompiled(GRADIENT_PENETRATION);
  }

  public void setCyclicGradient(String value)
  {
    put(CYCLIC_GRADIENT, value);
  }

  public String getCyclicGradient()
  {
    return get(CYCLIC_GRADIENT);
  }

  public OnOffAttribute getCompiledCyclicGradient()
  {
    return (OnOffAttribute)getCompiled(CYCLIC_GRADIENT);
  }

  public void setHorizontalAlignment(String alignment)
  {
    put(HORIZONTAL_ALIGNMENT, alignment);
  }

  public String getHorizontalAlignment()
  {
    return get(HORIZONTAL_ALIGNMENT);
  }

  public HorizontalAlignmentAttribute getCompiledHorizontalAlignment()
  {
    return (HorizontalAlignmentAttribute)getCompiled(HORIZONTAL_ALIGNMENT);
  }

  public void setVerticalAlignment(String alignment)
  {
    put(VERTICAL_ALIGNMENT, alignment);
  }

  public String getVerticalAlignment()
  {
    return get(VERTICAL_ALIGNMENT);
  }

  public VerticalAlignmentAttribute getCompiledVerticalAlignment()
  {
    return (VerticalAlignmentAttribute)getCompiled(VERTICAL_ALIGNMENT);
  }

  public String getFontFace()
  {
    return get(FONT_FACE);
  }

  public StringAttribute getCompiledFontFace()
  {
    return (StringAttribute)getCompiled(FONT_FACE);
  }

  public void setFontFace(String fontFace)
  {
    put(FONT_FACE, fontFace);
  }

  public String getFontSize()
  {
    return get(FONT_SIZE);
  }

  public IntegerAttribute getCompiledFontSize()
  {
    return (IntegerAttribute)getCompiled(FONT_SIZE);
  }

  public void setFontSize(String fontSize)
  {
    put(FONT_SIZE, fontSize);
  }

  public String getFontStyle()
  {
    return get(FONT_STYLE);
  }

  public FontStyleAttribute getCompiledFontStyle()
  {
    return (FontStyleAttribute)getCompiled(FONT_STYLE);
  }

  public void setFontStyle(String fontStyle)
  {
    put(FONT_STYLE, fontStyle);
  }

  public String getTransparency()
  {
    return get(TRANSPARENCY);
  }

  public PercentageAttribute getCompiledTransparency()
  {
    return (PercentageAttribute)getCompiled(TRANSPARENCY);
  }

  public void setTransparency(String transparency)
  {
    put(TRANSPARENCY, transparency);
  }

  public String getTopRightBorderWidth()
  {
    return get(TOP_RIGHT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledTopRightBorderWidth()
  {
    return (IntegerAttribute)getCompiled(TOP_RIGHT_BORDER_WIDTH);
  }

  public void setTopRightBorderWidth(String value)
  {
    put(TOP_RIGHT_BORDER_WIDTH, value);
  }

  public String getBottomRightBorderWidth()
  {
    return get(BOTTOM_RIGHT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledBottomRightBorderWidth()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_RIGHT_BORDER_WIDTH);
  }

  public void setBottomRightBorderWidth(String value)
  {
    put(BOTTOM_RIGHT_BORDER_WIDTH, value);
  }

  public String getBottomLeftBorderWidth()
  {
    return get(BOTTOM_LEFT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledBottomLeftBorderWidth()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_LEFT_BORDER_WIDTH);
  }

  public void setBottomLeftBorderWidth(String value)
  {
    put(BOTTOM_LEFT_BORDER_WIDTH, value);
  }

  public String getTopLeftBorderWidth()
  {
    return get(TOP_LEFT_BORDER_WIDTH);
  }

  public IntegerAttribute getCompiledTopLeftBorderWidth()
  {
    return (IntegerAttribute)getCompiled(TOP_LEFT_BORDER_WIDTH);
  }

  public void setTopLeftBorderWidth(String value)
  {
    put(TOP_LEFT_BORDER_WIDTH, value);
  }

  public String getTopRightBorderColor()
  {
    return get(TOP_RIGHT_BORDER_COLOR);
  }

  public IntegerAttribute getCompiledTopRightBorderColor()
  {
    return (IntegerAttribute)getCompiled(TOP_RIGHT_BORDER_COLOR);
  }

  public void setTopRightBorderColor(String value)
  {
    put(TOP_RIGHT_BORDER_COLOR, value);
  }

  public String getBottomRightBorderColor()
  {
    return get(BOTTOM_RIGHT_BORDER_COLOR);
  }

  public IntegerAttribute getCompiledBottomRightBorderColor()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_RIGHT_BORDER_COLOR);
  }

  public void setBottomRightBorderColor(String value)
  {
    put(BOTTOM_RIGHT_BORDER_COLOR, value);
  }

  public String getBottomLeftBorderColor()
  {
    return get(BOTTOM_LEFT_BORDER_COLOR);
  }

  public IntegerAttribute getCompiledBottomLeftBorderColor()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_LEFT_BORDER_COLOR);
  }

  public void setBottomLeftBorderColor(String value)
  {
    put(BOTTOM_LEFT_BORDER_COLOR, value);
  }

  public String getTopLeftBorderColor()
  {
    return get(TOP_LEFT_BORDER_COLOR);
  }

  public IntegerAttribute getCompiledTopLeftBorderColor()
  {
    return (IntegerAttribute)getCompiled(TOP_LEFT_BORDER_COLOR);
  }

  public void setTopLeftBorderColor(String value)
  {
    put(TOP_LEFT_BORDER_COLOR, value);
  }

  public void setRoundedCornerRadius(String radius)
  {
    put(TOP_RIGHT_ROUNDED_CORNER_RADIUS, radius);
    put(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS, radius);
    put(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS, radius);
    put(TOP_LEFT_ROUNDED_CORNER_RADIUS, radius);
  }

  public void setTopRightRoundedCornerRadius(String value)
  {
    put(TOP_RIGHT_ROUNDED_CORNER_RADIUS, value);
  }

  public String getTopRightRoundedCornerRadius()
  {
    return get(TOP_RIGHT_ROUNDED_CORNER_RADIUS);
  }

  public IntegerAttribute getCompiledTopRightRoundedCornerRadius()
  {
    return (IntegerAttribute)getCompiled(TOP_RIGHT_ROUNDED_CORNER_RADIUS);
  }

  public void setBottomRightRoundedCornerRadius(String value)
  {
    put(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS, value);
  }

  public String getBottomRightRoundedCornerRadius()
  {
    return get(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS);
  }

  public IntegerAttribute getCompiledBottomRightRoundedCornerRadius()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS);
  }

  public void setBottomLeftRoundedCornerRadius(String value)
  {
    put(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS, value);
  }

  public String getBottomLeftRoundedCornerRadius()
  {
    return get(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS);
  }

  public IntegerAttribute getCompiledBottomLeftRoundedCornerRadius()
  {
    return (IntegerAttribute)getCompiled(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS);
  }

  public void setTopLeftRoundedCornerRadius(String value)
  {
    put(TOP_LEFT_ROUNDED_CORNER_RADIUS, value);
  }

  public String getTopLeftRoundedCornerRadius()
  {
    return get(TOP_LEFT_ROUNDED_CORNER_RADIUS);
  }

  public IntegerAttribute getCompiledTopLeftRoundedCornerRadius()
  {
    return (IntegerAttribute)getCompiled(TOP_LEFT_ROUNDED_CORNER_RADIUS);
  }

  public void setTopRoundedCornerRadius(String radius)
  {
    put(TOP_RIGHT_ROUNDED_CORNER_RADIUS, radius);
    put(TOP_LEFT_ROUNDED_CORNER_RADIUS, radius);
  }

  public void setRightRoundedCornerRadius(String radius)
  {
    put(TOP_RIGHT_ROUNDED_CORNER_RADIUS, radius);
    put(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS, radius);
  }

  public void setBottomRoundedCornerRadius(String radius)
  {
    put(BOTTOM_RIGHT_ROUNDED_CORNER_RADIUS, radius);
    put(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS, radius);
  }

  public void setLeftRoundedCornerRadius(String radius)
  {
    put(BOTTOM_LEFT_ROUNDED_CORNER_RADIUS, radius);
    put(TOP_LEFT_ROUNDED_CORNER_RADIUS, radius);
  }

  public String getFloat()
  {
    return get(FLOAT);
  }

  public OnOffAttribute getCompiledFloat()
  {
    return (OnOffAttribute)getCompiled(FLOAT);
  }

  public void setFloat(String value)
  {
    put(FLOAT, value);
  }

  public String getX()
  {
    return get(X);
  }

  public IntegerAttribute getCompiledX()
  {
    return (IntegerAttribute)getCompiled(X);
  }

  public void setX(String value)
  {
    put(X, value);
  }

  public String getY()
  {
    return get(Y);
  }

  public IntegerAttribute getCompiledY()
  {
    return (IntegerAttribute)getCompiled(Y);
  }

  public void setY(String value)
  {
    put(Y, value);
  }

  public void setScrollbars(String value)
  {
    put(VERTICAL_SCROLLBAR, value);
    put(HORIZONTAL_SCROLLBAR, value);
  }

  public String getVerticalScrollbar()
  {
    return get(VERTICAL_SCROLLBAR);
  }

  public OnOffAttribute getCompiledVerticalScrollbar()
  {
    return (OnOffAttribute)getCompiled(VERTICAL_SCROLLBAR);
  }

  public String getHorizontalScrollbar()
  {
    return get(HORIZONTAL_SCROLLBAR);
  }

  public OnOffAttribute getCompiledHorizontalScrollbar()
  {
    return (OnOffAttribute)getCompiled(HORIZONTAL_SCROLLBAR);
  }

  public void setVerticalScrollbar(String value)
  {
    put(VERTICAL_SCROLLBAR, value);
  }

  public void setHorizontalScrollbar(String value)
  {
    put(HORIZONTAL_SCROLLBAR, value);
  }
}
