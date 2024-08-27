<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Google Maps Integration</title>
    <script src="https://maps.googleapis.com/maps/api/js?key=EMPTY"></script>
    <style>
        #map {
            height: 500px;
            width: 100%;
        }
    </style>
    <script>
        function initMap() {
            var mapOptions = {
                center: { lat: -34.397, lng: 150.644 }, // Default center location
                zoom: 8
            };
            var map = new google.maps.Map(document.getElementById('map'), mapOptions);
        }

        // Call the initMap function when the page loads
        window.onload = initMap;
    </script>
</head>
<body>
    <h1>Google Maps Integration</h1>
    <div id="map"></div>
</body>
</html>
