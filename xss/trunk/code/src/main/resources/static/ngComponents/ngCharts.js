function drawLines(divId, data, option) {
    $("#" + divId).empty();
    var series = [];

    for (var i in option.yis) {
        var yi = option.yis[i];
        var se = {name:yi.title, data:[]};
        series.push(se);
        var c = 0;
        for (var j in data) {
            var item = data[j];
            var x = new Date(item[option.xis.field]).getTime() + 8 * 3600000;
            var y = item[yi.field];
            se.data.push([x,y]);
            c += y;
        }
        se.name += " (" + c + ")";
    }
    $('#' + divId).highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: option.title
        },
        subtitle: {
            text: option.subtitle || ''
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: {
                millisecond: '%H:%M',
                second: '%H:%M',
                minute: '%H:%M',
                hour: '%H:%M',
                day:'%m月%d',
                month: '%e 月 %b',
                year: '%b'
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            min: 0
        },
        tooltip: {
            formatter: function() {
                var s = '<span style="color:#034BA0">'+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'</span>';
                $.each(this.points, function(i, point) {
                    var valy = point.y;
                    var valuestr = valy;
                    s += '<br/><span style=\"'+"color:"+point.series.color+"\">"
                        + point.series.name.split(' ')[0] +'</span>:<b>'
                    + valuestr +'</b>';
                });
                return s;
            },
            xDateFormat: '%Y-%m-%d %H:%M:%S',
            shared: true,
            crosshairs: true
        },
        series: series
    });
}