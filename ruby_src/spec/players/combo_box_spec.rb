require File.expand_path(File.dirname(__FILE__) + "/../spec_helper")
require 'limelight/page'
require 'limelight/block'
require 'limelight/players/combo_box'

describe Limelight::Players::ComboBox do

  before(:each) do
    @page = Limelight::Page.new(:illuminator => make_mock("caster", :fill_cast => nil))
    @block = Limelight::Block.new(:page => @page)
    @block.include_player(Limelight::Players::ComboBox)
  end
  
  it "should get rid of the all painters and add a ComboBoxPainter" do
    @block.panel.painters.size.should == 1
    @block.panel.painters.last.class.should == Java::limelight.ui.painting.ComboBoxPainter
  end
  
  it "should clear event listeners on the panel" do
    @block.panel.mouse_listeners.length.should == 0
    @block.panel.key_listeners.length.should == 0
  end
  
  it "should have a ComboBox" do
    @block.panel.components[0].class.should == javax.swing.JComboBox
  end
  
  it "should have settable value" do
    @block.choices = ["1", "2", "3"]
    @block.value.should == "1"
    
    @block.value = "3"
    @block.value.should == "3"
  end

end