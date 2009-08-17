module Alerts

  prop_reader :message_input

  def open_alert
    stage.alert(message_input.text)
  end

  def open_incompatible_production
    production_path = File.join(path, "incompatible_production")
    Thread.new { Limelight::Studio.open(production_path) }
  end

end