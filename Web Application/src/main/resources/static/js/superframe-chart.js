var superframecanvas;
var superframecontext;
var superframeheight = 80;
var superframewidth = 750;

function drawWindowLine(startX, endX, startY, endY){
    superframecontext.fillStyle="#000000";
    superframecontext.beginPath();
    superframecontext.moveTo(startX, startY);
    superframecontext.lineTo(endX, endY);
    superframecontext.stroke();
}

function paintSuperFrame(){
    superframecontext.fillStyle="#CCCCCC";
    superframecontext.fillRect(0, 0,superframewidth,superframeheight);
    superframecontext.stroke();

   for (var i = 0; i < orfData.length; i++) {
        superframecontext.fillStyle="#5E9DC8";
        // For reverse frames, we swap the start and stop index to reflect the reverse.
        if(orfData[i].frameIndicator >= 3){
            superframecontext.fillRect(
                        (((orfData[i].orfStopIndex + orfData[i].frameIndicator) / contigLength) * superframewidth),
                        0,
                        ((orfData[i].orfStartIndex / contigLength) * superframewidth) - ((orfData[i].orfStopIndex / contigLength) * superframewidth),
                        superframeheight / 2);
        } else {
            superframecontext.fillRect(
                        (((orfData[i].orfStartIndex + orfData[i].frameIndicator) / contigLength) * superframewidth),
                        0,
                        ((orfData[i].orfStopIndex / contigLength) * superframewidth) - ((orfData[i].orfStartIndex / contigLength) * superframewidth),
                        superframeheight / 2);
        }
        superframecontext.stroke();
    }


    for (var i = 0; i < windowdata.length; i++) {
        superframecontext.fillStyle= gcColours[i];

        superframecontext.fillRect(
            ((i * windowSize) / contigLength) * superframewidth,
            superframeheight / 2,
            (windowSize / contigLength) * superframewidth,
            superframeheight / 2);

        drawWindowLine(
            ((i * windowSize) / contigLength) * superframewidth,
            ((i * windowSize) / contigLength) * superframewidth,
            0,
            superframeheight);
    }
    superframecontext.stroke();
}

function setupSuperframeChart(){
superframecanvas = document.getElementById("superframecanvas");

    for(var i=0; i < canvasList.length; i++){
        superframecanvas.width = superframewidth;
        superframecanvas.height = superframeheight;
        superframecontext = superframecanvas.getContext("2d");
    }
    superframecanvas.addEventListener("click", superFrameClicked, false);

    paintSuperFrame();
}

function findSuperframeWindowClicked(x){
    for(var i = 0; i < windowdata.length; i++){
        if(((i * windowSize) / contigLength) * superframewidth < x &&
            (((i + 1) * windowSize) / contigLength) * superframewidth > x){
            console.log(i + " x: " + x);
        }
    }
}

function superFrameClicked(event){
        var eventTarget;
        if (event.srcElement){
            eventTarget = event.srcElement;
        } else if (event.target){
            eventTarget = event.target;
        }

        var clientBoundary = eventTarget.getBoundingClientRect();
        var x = event.clientX - clientBoundary.left;

        findSuperframeWindowClicked(x);
}