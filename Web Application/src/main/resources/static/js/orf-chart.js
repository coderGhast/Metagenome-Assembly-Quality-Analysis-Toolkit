var canvasList = new Array(6);
var contextList = new Array(0);

var orfDisplayWidth;
var orfDisplayHeight = 40;
var framePos = 10;
var framePosMofidier = 50;
var canvasWidth = 650;
var highlightedIndex = -1;

function paintFrames(){
    for(var i = 0; i < contextList.length; i++){
        contextList[i].fillStyle="#DADADA";
        contextList[i].fillRect(0, 0,orfDisplayWidth,orfDisplayHeight);
        contextList[i].stroke();
    }

    for (var i = 0; i < orfData.length; i++) {
        var currentContext = contextList[orfData[i].frameIndicator];
        if(i == highlightedIndex){
            currentContext.fillStyle="#DE2D26";
        }
        else {
            currentContext.fillStyle="#5E9DC8";
        }
        // For reverse frames, we swap the start and stop index to reflect the reverse.
        if(orfData[i].frameIndicator >= 3){
            currentContext.fillRect(
                        (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength) * canvasWidth),
                        0,
                        ((orfData[i].orfStartIndex / contigLength) * canvasWidth) - ((orfData[i].orfStopIndex / contigLength) * canvasWidth),
                        orfDisplayHeight);
        } else {
            currentContext.fillRect(
                        (((orfData[i].orfStartIndex + orfData[i].frameIndicator) / contigLength) * canvasWidth),
                        0,
                        ((orfData[i].orfStopIndex / contigLength) * canvasWidth) - ((orfData[i].orfStartIndex / contigLength) * canvasWidth),
                        orfDisplayHeight);
        }

        currentContext.stroke();
    };
}

function setupOrfChart(){
canvasList = [
    document.getElementById("orfcanvas-frame1"),
    document.getElementById("orfcanvas-frame2"),
    document.getElementById("orfcanvas-frame3"),
    document.getElementById("orfcanvas-frame4"),
    document.getElementById("orfcanvas-frame5"),
    document.getElementById("orfcanvas-frame6")
    ];

    for(var i=0; i < canvasList.length; i++){
        canvasList[i].addEventListener("click", onClick, false);
        canvasList[i].width = canvasWidth;
        canvasList[i].height = orfDisplayHeight;
        contextList.push(canvasList[i].getContext("2d"));
    }
    orfDisplayWidth = canvasList[0].width;

    paintFrames();
}

function displayOrfInformation(orfLocation, i){
    highlightedIndex = i;
    paintFrames();
    // TODO: Display the information from the below Log, plus characters, in the page below the chart
    console.log("Within ORF Location, Frame: " + (orfLocation.frameIndicator + 1) + " Length: " + orfLocation.orfLength +
                      " Start: " + orfLocation.orfStartIndex + " End: " + orfLocation.orfStopIndex);
}

function checkIfWithinORFLocation(x, frameNumber){
    for(var i = 0; i < orfData.length; i++){
        if(orfData[i].frameIndicator == frameNumber){
            if(orfData[i].frameIndicator >=3){
                if( x >= (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength) *  canvasWidth) &&
                    x <= (((orfData[i].orfStartIndex + orfData[i].frameIndicator) / contigLength)) * canvasWidth) {
                    displayOrfInformation(orfData[i], i);
                }
            } else {
                if( x >= (((orfData[i].orfStartIndex + orfData[i].frameIndicator) / contigLength) *  canvasWidth) &&
                    x <= (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength)) * canvasWidth) {
                    displayOrfInformation(orfData[i], i);
                }
            }
        }
    }
}

function onClick(event){
    var eventTarget;
    if (event.srcElement){
        eventTarget = event.srcElement;
    } else if (event.target){
        eventTarget = event.target;
    }

    var clientBoundary = eventTarget.getBoundingClientRect();
    var x = event.clientX - clientBoundary.left;
    var y = event.clientY - clientBoundary.top;

    var endOfIdCharacter = event.target.id.slice(event.target.id.length - 1);
    var frameNumber = endOfIdCharacter - 1;

    checkIfWithinORFLocation(x, frameNumber);
}
