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

function formatOrfSequence(sequence, startIndex, frameIndicator){
    var spacedSequence = '<p>'+ (startIndex) + ' <b><span style="color:#34532d">';
    for(var i = 0; i < sequence.length; i++){
        spacedSequence += sequence.charAt(i);
        if((i+1) % 3 == 0){
        spacedSequence += " ";
        }
        if(i==2){
            spacedSequence += "</span></b>";
        }

        // Every 20 lots of 3 characters, make a new paragraph and label with current character index.
        if((i+1) % 45 == 0 && i <= sequence.length - 4){
            if(frameIndicator >= 3){
                spacedSequence += "</p><p>" + ((startIndex - 1) - i) + "   ";
            } else {
                spacedSequence += "</p><p>" + ((startIndex - 1) + i) + "   ";
            }
        }

        if(i == sequence.length - 4){
            spacedSequence += '<b><span style="color:#e60000">';
        }

        if(i >= sequence.length){
            spacedSequence += "</span></b></p>";
         }
    }

    return spacedSequence;
}