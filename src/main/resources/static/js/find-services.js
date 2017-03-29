function FindServiceApp() {
  var self = this;

  var elements = { };

  self.initialize = function() {
    // TBC : Setup elements
    elements.txtSearchServices = $('#txt-search-services');
    elements.serviceCardContainer = $('#service-card-container');

    bindEventHandlers()
  };

  var bindEventHandlers = function() {
    elements.serviceCardContainer.on('click', '.service-card', function() {
      // TBC : TODO : Get public_id attached to service card and query for shuttles using service's public ID
      var serviceCard = $(this);
      window.location = '/service-shuttles';
    });
  };

  self.initialize();
  return self;
}
