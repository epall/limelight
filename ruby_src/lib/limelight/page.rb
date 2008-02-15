require 'limelight/java_util'
require 'limelight/block'
require 'limelight/button_group_cache'

module Limelight
  class Page < Block
    
    include Java::limelight.ui.Page
  
    attr_reader :styles, :button_groups, :frame
    attr_accessor :book, :loader
    getters :book, :loader
    setters :book
    
    def initialize
      @frame = Java::limelight.ui.Frame.new(self)
      @panel = @frame.panel
      super
      @page = self
      @styles = {}
      @button_groups = ButtonGroupCache.new
    end
  
  end
end