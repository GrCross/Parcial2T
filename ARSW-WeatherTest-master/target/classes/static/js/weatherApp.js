var app = (function () {   
    var showWeather = function (param) {
        


    };
    

    return {
        searchWeather: function () {
            weather = $("#inputBuscar").val();
            if (cinema != "") {
                
                weatherClient.getweather(weather, showWeather);
            }
        },
        eliminarTexto: function () {
            $("#inputBuscar").val("");
        }
    };

})();