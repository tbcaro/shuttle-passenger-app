function FindServiceApp() {
  var self = this;

  var elements = { };

  self.initialize = function() {
    // TBC : Setup elements
    elements.txtSearchServices = $('#txt-search-services');
    elements.serviceCardContainer = $('#service-card-container');
    elements.btnSearch = $('#btn-search');

    bindEventHandlers()
  };

  var bindEventHandlers = function() {
    elements.serviceCardContainer.on('click', '.service-card', function() {
      var publicId = $(this).data('publicId');
      window.location = '/service-shuttles?publicId=' + publicId;
    });

    elements.btnSearch.on('click', function() {
      var filter = elements.txtSearchServices.val();
      window.location = '/find-services?filter=' + filter;
    });
  };

  self.initialize();
  return self;
}
