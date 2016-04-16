function inspect_clicked(id)
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

function addWhiteSpace(sequence){
    var spacedSequence = '<b><span style="color:#34532d">';
    for(var i = 0; i < sequence.length; i++){
        spacedSequence += sequence.charAt(i);
        if((i+1) % 3 == 0){
        spacedSequence += " ";
        }
        if(i == sequence.length - 4){
            spacedSequence += '<span style="color:#fde234">';
        }
        if(i==2 || i >= sequence.length){
             spacedSequence += "</span></b>";
        }
    }

    return spacedSequence;
}