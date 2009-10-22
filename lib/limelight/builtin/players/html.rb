#- Copyright © 2008-2009 8th Light, Inc. All Rights Reserved.
#- Limelight and all included source files are distributed under terms of the GNU LGPL.

module Limelight
  module Builtin
    module Players

      module Html

        class << self

          def extended(prop) #:nodoc:
            html_panel = Limelight::UI::Model::HTMLPanel.new
            prop.panel.add(html_panel)
            prop.html_panel = html_panel
          end
          
        end

        attr_accessor :html_panel #:nodoc:
        
        def html=(path)
          html_panel.html = path
        end

      end

    end
  end
end
