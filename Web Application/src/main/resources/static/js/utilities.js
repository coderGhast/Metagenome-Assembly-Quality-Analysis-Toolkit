function inspect_clicked(id)
{
    for(var i=0; i < contigListSize; i++){
        if(id == i){
            document.getElementById('inspectbox ' + id).style.display='block';
        } else {
            document.getElementById('inspectbox ' + i).style.display='none';
        }
    }
}