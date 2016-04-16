var canvasList = new Array(6);
var contextList = new Array(0);

var orfDisplayWidth;
var orfDisplayHeight = 40;
var framePos = 10;
var framePosMofidier = 50;
var canvasWidth = 800;

function paintFrames(){
    for(var i = 0; i < contextList.length; i++){
        contextList[i].fillStyle="#DADADA";
        contextList[i].fillRect(0, 0,orfDisplayWidth,orfDisplayHeight);
        contextList[i].stroke();
    }

    for (var i = 0; i < orfData.length; i++) {
        var currentContext = contextList[orfData[i].frameIndicator];
        currentContext.fillStyle="#5E9DC8";
        if(orfData[i].frameIndicator >= 3){
            console.log("Here, frame: " + orfData[i].frameIndicator +
                        " Start: " + orfData[i].orfStartIndex +
                        " Stop: " + orfData[i].orfStopIndex) +
                        " Actual Start: " + (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength) * canvasWidth) +
                        " Actual Width: " + ((orfData[i].orfStartIndex / contigLength) * canvasWidth) - ((orfData[i].orfStopIndex / contigLength) * canvasWidth));
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

function checkIfWithinORFLocation(x, frameNumber){
    for(var i = 0; i < orfData.length; i++){
        if(orfData[i].frameIndicator == frameNumber){
                if( x >= (((orfData[i].orfStartIndex + orfData[i].frameIndicator) / contigLength) *  canvasWidth) &&
                    x <= (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength)) * canvasWidth) {
                    // TODO: This should be a display somewhere on the page
                alert("Within ORF Location, Frame: " + (frameNumber + 1) + " ORF: " + i + " Length: " + orfData[i].orfLength +
                " Start: " + orfData[i].orfStartIndex + " End: " + orfData[i].orfStopIndex);
            }
        }
    }
}

function onClick(event){
    var eventTarget;
    if (event.srcElement){
        eventTarget = event.srcElement;
    } else if (evt.target){
        eventTarget = event.target;
    }

    var clientBoundary = eventTarget.getBoundingClientRect();
    var x = event.clientX - clientBoundary.left;
    var y = event.clientY - clientBoundary.top;

    var endOfIdCharacter = event.target.id.slice(event.target.id.length - 1);
    var frameNumber = endOfIdCharacter - 1;

    checkIfWithinORFLocation(x, frameNumber);
}
