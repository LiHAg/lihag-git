$(function() {
  if ($('#dynamic-chart')[0]) {
      var data = [],
          totalPoints = 300;

      function getRandomData() {
          if (data.length > 0)
              data = data.slice(1);

          while (data.length < totalPoints) {
              var prev = data.length > 0 ? data[data.length - 1] : 50,
                  y = prev + Math.random() * 10 - 5;
              if (y < 0) {
                  y = 0;
              } else if (y > 90) {
                  y = 90;
              }

              data.push(y);
          }

          var res = [];
          for (var i = 0; i < data.length; ++i) {
              res.push([i, data[i]])
          }

          return res;
      }


      var updateInterval = 300;
      var plot = $.plot("#dynamic-chart", [ getRandomData() ], {
          series: {
              label: "Data",
              lines: {
                  show: true,
                  lineWidth: 1,
                  fill: 0.25,
                  fillColor: {
                      colors: [{
                          opacity: 0.1
                      }, {
                          opacity: 1
                      }]
                  }
              },

              color: 'rgba(173,255,47,0.4)',
              shadowSize: 1,
          },
          yaxis: {
              min: 0,
              max: 100,
              tickColor: 'rgba(255,255,255,0.15)',
              font :{
                  lineHeight: 13,
                  style: "normal",
                  color: "rgba(255,255,255,0.8)",
              },
              shadowSize: 0,

          },
          xaxis: {
              tickColor: 'rgba(255,255,255,0.15)',
              show: true,
              font :{
                  lineHeight: 13,
                  style: "normal",
                  color: "rgba(255,255,255,0.8)",
              },
              shadowSize: 0,
              min: 0,
              max: 250
          },
          grid: {
              borderWidth: 1,
              borderColor: 'rgba(255,255,255,0.25)',
              labelMargin:10,
              hoverable: true,
              clickable: true,
              mouseActiveRadius:6,
          },
          legend: {
              show: false
          }
      });

      function update() {
          plot.setData([getRandomData()]);
          // Since the axes don't change, we don't need to call plot.setupGrid()

          plot.draw();
          setTimeout(update, updateInterval);
      }

      update();

      $("#dynamic-chart").bind("plothover", function (event, pos, item) {
          if (item) {
              var x = item.datapoint[0].toFixed(2),
                  y = item.datapoint[1].toFixed(2);
              $("#dynamic-chart-tooltip").html(item.series.label + " of " + x + " = " + y).css({top: item.pageY+5, left: item.pageX+5}).fadeIn(200);
          }
          else {
              $("#dynamic-chart-tooltip").hide();
          }
      });

      $("<div id='dynamic-chart-tooltip' class='chart-tooltip'></div>").appendTo("body");
  }
});


