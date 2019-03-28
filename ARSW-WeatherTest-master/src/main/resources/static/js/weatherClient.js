var apiclient = (function () {

       

    return {
        getweather: function (city, callback) {
            $.get("weather/" + city, function (data) {
                callback(data);
            });
        },
        
    };
})();