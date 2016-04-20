function inspectclicked(id)
{
    for(var i=0; i < contigListSize; i++){
        if(id == i){
            document.getElementById(id).style.display='none';
            document.getElementById('inspectbox ' + id).style.display='block';
        } else {
            document.getElementById(i).style.display='inline-block';
            document.getElementById('inspectbox ' + i).style.display='none';
        }
    }
}

function vieworf(id){
    displayOrfInformation(orfData[id], id);
}

function loadingclick(id) {
    document.getElementById(id).value = "Loading...";
}