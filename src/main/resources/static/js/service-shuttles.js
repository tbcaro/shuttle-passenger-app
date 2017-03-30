function ServiceShuttleApp() {
  var self = this;

  var elements = { };

  self.initialize = function() {
    // TBC : Setup elements
    elements.btnRefresh = $('#btn-refresh');
    elements.shuttleCardContainer = $('#shuttle-card-container');

    bindEventHandlers();
  };

  var bindEventHandlers = function() {
    elements.btnRefresh.on('click', function() {
      window.location.reload(true);
    });

    elements.shuttleCardContainer.on('click', '.shuttle-card', function() {
      // TBC : TODO : Get shuttle id attached to service card and query for shuttle activity using shuttle id
      var shuttleCard = $(this);
      window.location = '/shuttle-map';
    });
  };

  self.initialize();
  return self;
}
