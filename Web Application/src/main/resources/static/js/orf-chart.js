var canvas;
var context;
var orfDisplayWidth;
var orfDisplayHeight = 40;
var framePos = 10;
var framePosMofidier = 50;
// 1 percent of the size of the Orf display, so we can apply the data visuals to it easily
var orfLocationIndicatorMarkerTranslation;

function paintFrames(){
    context.rect(10,framePos + (framePosMofidier * 0),orfDisplayWidth,orfDisplayHeight);
    context.rect(10,framePos + (framePosMofidier * 1),orfDisplayWidth,orfDisplayHeight);
    context.rect(10,framePos + (framePosMofidier * 2),orfDisplayWidth,orfDisplayHeight);
    context.rect(10,framePos + (framePosMofidier * 3),orfDisplayWidth,orfDisplayHeight);
    context.rect(10,framePos + (framePosMofidier * 4),orfDisplayWidth,orfDisplayHeight);
    context.rect(10,framePos + (framePosMofidier * 5),orfDisplayWidth,orfDisplayHeight);

        for (var i = 0; i < orfData.length; i++) {
            context.fillStyle="#5E9DC8";
            context.fillRect(
                (10 + (  orfData[i].orfStartIndex / orfLocationIndicatorMarkerTranslation)),
                framePos + (framePosMofidier * orfData[i].frameIndicator),
                ((orfData[i].orfStopIndex / orfLocationIndicatorMarkerTranslation) - (orfData[i].orfStartIndex / orfLocationIndicatorMarkerTranslation)),
                orfDisplayHeight);
        };

    context.stroke();
}

function setupOrfChart(){
    canvas = document.getElementById("orfcanvas");
    canvas.addEventListener("click", onClick, false);
    canvas.width= 800
    context = canvas.getContext("2d");
    orfDisplayWidth = canvas.width-20;
    orfLocationIndicatorMarkerTranslation = orfDisplayWidth / 100;

    paintFrames();
}

function checkIfWithinORFLocation(x, y, frameNumber){
    var pageY = event.pageY;
    var pageX = event.pageX;
    for(var i = 0; i < orfData.length; i++){
        if(orfData[i].frameIndicator == frameNumber){
                if( x >= (10) + (orfData[i].orfStartIndex / orfLocationIndicatorMarkerTranslation) &&
                    x <= (10) + (orfData[i].orfStopIndex / orfLocationIndicatorMarkerTranslation)){
                alert("Within ORF Location, Frame: " + (frameNumber + 1) + " ORF: " + i + " Length: " + orfData[i].orfLength);
            }
        }
    }
}

function withinFrameLocations(x, y){
    for (var i = 0; i < 6; i++) {
        if((y >= (framePos) + (framePosMofidier * i)) &&
         (y <= (framePos + orfDisplayHeight + (framePosMofidier * i)))){
            checkIfWithinORFLocation(x, y, i);
        }
     };
}

function withinFrameVisual(x, y){
    if(x >= (10)  && x <= (10) + orfDisplayWidth){
        withinFrameLocations(x, y);
    }

    return true;
}

function onClick(event) {
    var clientBoundary = canvas.getBoundingClientRect();
    var x = event.clientX - clientBoundary.left;
    var y = event.clientY - clientBoundary.top;
    withinFrameVisual(x, y);
}
