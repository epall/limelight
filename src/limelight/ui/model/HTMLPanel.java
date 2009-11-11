package limelight.ui.model;

import java.awt.Graphics2D;

import javax.swing.JEditorPane;

import limelight.styles.Style;
import limelight.util.Box;

public class HTMLPanel extends BasePanel {
	private final JEditorPane panel;

	public HTMLPanel() {
		panel = new JEditorPane();
		panel.setEditable(false);
		panel.setContentType("text/html");
	}

	public void setHtml(String code){
		panel.setText(code);
	}

	@Override
	public Box getBoxInsidePadding() {
		return null;
	}

	@Override
	public Box getChildConsumableArea() {
		return null;
	}

	@Override
	public Style getStyle() {
		return getParent().getStyle();
	}

	@Override
	public void paintOn(Graphics2D graphics) {
		panel.setSize(width, height);
		panel.repaint();
		panel.paint(graphics);
	}

	@Override
	public Layout getDefaultLayout() {
		return HTMLPanelLayout.instance;
	}

	public void compile() {
		// figure out size
		try {
			width = Integer.valueOf(this.getStyle().getWidth());
		} catch (Exception e){
			width = Math.min(this.getParent().getWidth(), panel.getPreferredSize().width);
		}
		try {
			height = Integer.valueOf(this.getStyle().getHeight());
		} catch (Exception e){
			height = panel.getPreferredSize().height;
		}
	}
}
