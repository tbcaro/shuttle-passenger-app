function ShuttleMapApp() {
  var self = this;

  var elements = { };
  var geoLocator;
  var intervalId = 0;
  var initialLoad = true;
  var timeUtils;
  var activityData = null;

  self.map = { };
  self.mapMarkers = { };

  self.initialize = function() {
    // TBC : Setup elements
    elements.mapContainer = $('#map-container');
    elements.shuttleDetailsContainer = $('#shuttle-details-container');
    elements.shuttleName = $('#shuttle-name');
    elements.statusLabel = $('#status-label');
    elements.routeName = $('#route-name');
    elements.scheduleCard = $('.schedule-card');
    elements.scheduleTableBody = elements.scheduleCard.find('tbody');

    // TBC : Setup utility objects
    geoLocator = new GeoLocator();
    timeUtils = new TimeUtils();

    self.initializeMap();

    geoLocator.getLocation().done(function(location) {
      self.map.setCenter(location);
      self.map.setZoom(12);
      self.mapMarkers.userMarker = new google.maps.Marker({
        position: location,
        map: self.map,
        icon: {
          path: fontawesome.markers.MAP_MARKER,
          scale: 0.5,
          strokeWeight: 0.2,
          strokeColor: 'black',
          strokeOpacity: 1,
          fillColor: '#ff0000',
          fillOpacity: 1,
          rotation: 0
        }
      });
    });

    // bindEventHandlers();
    loadShuttleActivity();
    intervalId = setInterval(loadShuttleActivity, 5000);
  };

  self.initializeMap = function() {
    self.map = new google.maps.Map(elements.mapContainer[0], {
      center: { lat: 39.8282, lng: -98.5795 }, // TBC : Default center over central USA
      zoom: 5,
      disableDefaultUI: true
    });
  };

  // var bindEventHandlers = function() {
  //
  // };

  var loadShuttleActivity = function() {
    var shuttleId = elements.shuttleDetailsContainer.data('shuttleId');

    axios.get('/api/fetchShuttleActivity?shuttleId=' + shuttleId)
        .then(function (response) {
          activityData = response.data;
          updateDetails();
          updateShuttleMarker();

          if(initialLoad) {
            fitMapToBounds();
          }
          initialLoad = false;
        })
        .catch(function (error) {
          console.log(error);
        });
  };

  var updateDetails = function() {
    elements.shuttleName.html(activityData.shuttleName);
    elements.routeName.html(activityData.routeName);

    elements.statusLabel.removeClass('btn-success');
    elements.statusLabel.removeClass('btn-warning');
    switch (activityData.shuttleStatus) {
      case 'DRIVING':
        elements.statusLabel.addClass('btn-success');
        elements.statusLabel.html('Driving');
        break;
      case 'AT_STOP':
        elements.statusLabel.addClass('btn-warning');
        elements.statusLabel.html('At-Stop');
        break;
    }

    var report = activityData.assignmentReport;
    if (report != null) {
      elements.scheduleTableBody.empty();
      for (var i = 0; i < report.assignmentStops.length; i++) {
        var stop = report.assignmentStops[i];
        var row = $('<tr>');

        var icon = $('<td>');
        var stopIcon = $('<td>');
        var stopName = $('<td>');
        var arriveTime = $('<td>');
        var departTime = $('<td>');

        icon.append($('<i>').addClass('fa'));
        if (report.currentStop < i) {
          icon.find('i').addClass('fa-square-o');
        } else if (report.currentStop === i) {
          icon.find('i').addClass('fa-spinner');
        } else {
          icon.find('i').addClass('fa-check-square-o');
        }

        stopIcon.append($('<i>').addClass('fa fa-map-pin'));

        stopName.html(stop.name);
        if (stop.actualArriveTime != null) {
          arriveTime.html(timeUtils.formatTime(stop.actualArriveTime));
        } else {
          arriveTime.html(timeUtils.formatTime(stop.estArriveTime));
        }

        if (stop.actualDepartTime != null) {
          departTime.html(timeUtils.formatTime(stop.actualDepartTime));
        } else {
          departTime.html(timeUtils.formatTime(stop.estDepartTime));
        }

        row.append(icon);
        row.append(stopIcon);
        row.append(stopName);
        row.append(arriveTime);
        row.append(departTime);

        elements.scheduleTableBody.append(row);
      }
    }
  };

  var updateShuttleMarker = function() {
    self.mapMarkers.shuttleMarker = new google.maps.Marker({
                   position: {
                     lat: activityData.shuttleLatitude,
                     lng: activityData.shuttleLongitude
                   },
                   map: self.map,
                   icon: {
                     path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                     scale: 5,
                     strokeColor: 'lightgrey',
                     strokeOpacity: 0.5,
                     strokeWeight: 0.2,
                     rotation: activityData.shuttleHeading,
                     fillColor: 'deepskyblue',
                     fillOpacity: 1
                   }
                 });
  };

  var fitMapToBounds = function() {
    var bounds = new google.maps.LatLngBounds();

    // TBC : Extend bounds to shuttle positions
    if (self.mapMarkers.hasOwnProperty('shuttleMarker')) {
      var shuttlePosition = self.mapMarkers.shuttleMarker.getPosition();
      bounds.extend({
                      lat: shuttlePosition.lat(),
                      lng: shuttlePosition.lng()
                    });
      bounds.extend({
                      lat: shuttlePosition.lat(),
                      lng: shuttlePosition.lng()
                    });

      self.map.fitBounds(bounds);
    } else {

      // TBC : Extend bounds to user position
      if (self.mapMarkers.hasOwnProperty('userMarker')) {
        var userPosition = self.mapMarkers.userMarker.getPosition();
        bounds.extend({
                        lat: userPosition.lat(),
                        lng: userPosition.lng()
                      });
        bounds.extend({
                        lat: userPosition.lat(),
                        lng: userPosition.lng()
                      });

        self.map.fitBounds(bounds);
      }
    }
  };

  self.initialize();
  return self;
}

