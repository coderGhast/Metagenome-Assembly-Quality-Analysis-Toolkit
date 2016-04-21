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