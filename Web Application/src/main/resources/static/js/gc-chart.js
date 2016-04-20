/* Draw the GC Content chart using Plotly.js */
function drawGcChart(){
    for(var i=0; i < windownums.length; i++){
        if(i < windownums.length - 1){
            windownums[i] = ((windownums[i] - 1) * 300) + " to " + (windownums[i] * windowSize);
        } else {
            windownums[i] = ((windownums[i] - 1) * 300) + " to " + contigLength;
        }
    }

    var dataForm1 = {
      x: windownums,
      y: windowdata,
      marker: {color: gcColours},
      type: 'bar',
      name: 'GC Content % Windows'
    };

    var dataForm2 = {
        x: windownums,
        y: gcMeanForAllWindows,
        mode: 'lines',
        marker: {color: 'rgba(17, 120, 120, 1)'},
        name: 'GC Content % Average'
    };

    var data = [dataForm1, dataForm2];

    var gcContentTitleTemplatePartOne =  'GC Content Percentages in window size of ';
    var gcContentTitle = gcContentTitleTemplatePartOne.concat(windowSize);

    var layout = {
      title: gcContentTitle,
      xaxis: {
        title: 'Character area',
        titlefont: {
          size: 11,
          color: 'rgb(105, 105, 105)'
        },
        tickfont: {
          size: 11,
          color: 'rgb(105, 105, 105)'
        }
      },
      yaxis: {
        title: 'GC Content %',
        titlefont: {
          size: 16,
          color: 'rgb(105, 105, 105)'
        },
        tickfont: {
          size: 14,
          color: 'rgb(105, 105, 105)'
        }
      },
    };

    Plotly.newPlot('gccontentchart', data, layout);
}