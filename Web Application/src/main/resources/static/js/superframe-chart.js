var superframecanvas;
var superframecontext;
var superframeheight = 100;
var superframewidth = 500;

function paintSuperFrame(){
    superframecontext.fillStyle="#DADADA";
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
    }
    superframecontext.stroke();

    for (var i = 0; i < windowdata.length; i++) {
        superframecontext.fillStyle= gcColours[i];

        superframecontext.fillRect(
            ((i * windowSize) / contigLength) * superframewidth,
            superframeheight / 2,
            (windowSize / contigLength) * superframewidth,
            superframeheight / 2);
    }
    superframecontext.stroke();
}

function setupSuperframeChart(){
superframecanvas = document.getElementById("superframecanvas");

    for(var i=0; i < canvasList.length; i++){
        canvasList[i].addEventListener("click", onClick, false);
        superframecanvas.width = superframewidth;
        superframecanvas.height = superframeheight;
        superframecontext = superframecanvas.getContext("2d");
    }

    paintSuperFrame();
}