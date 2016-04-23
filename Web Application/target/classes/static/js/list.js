/*
 For the /list page, handles the clicking of the 'Show/Hide explanation' about the inspection process.
*/
function viewinspectionexplanation(){
    var div = document.getElementById("inspectionexplanation");
    var indicatordiv = document.getElementById("explanationindicator");
    if(div.style.display == 'none'){
        div.style.display = 'block';
        indicatordiv.textContent = 'hide inspection explanation <<';
    } else {
        div.style.display = 'none';
        indicatordiv.textContent = 'show inspection explanation >>'
    }
}


// Handles the clicking event of an Inspect button.
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