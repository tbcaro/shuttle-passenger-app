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
      var shuttleId = $(this).data('shuttleId');
      var publicId = $(this).data('publicId');
      window.location = '/shuttle-map?publicId=' + publicId + '&shuttleId=' + shuttleId;
    });
  };

  self.initialize();
  return self;
}
