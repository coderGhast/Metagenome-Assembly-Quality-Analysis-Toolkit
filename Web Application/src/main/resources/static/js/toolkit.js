/* Code for tabs accreditted to http://red-team-design.com/css3-jquery-folder-tabs/ */
$(document).ready(function() {
    $("#tabscontent").find("[id^='tab']").hide(); // Hide all content
    $("#tabs li:first").attr("id","current"); // Activate the first tab
    $("#tabscontent #tab1").fadeIn(); // Show first tab's content

    $('#tabs a').click(function(e) {
        e.preventDefault();
        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
         return;
        }
        else{
          $("#tabscontent").find("[id^='tab']").hide(); // Hide all content
          $("#tabs li").attr("id",""); //Reset id's
          $(this).parent().attr("id","current"); // Activate this
          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
        }
    });
});


function formatOrfSequence(sequence, startIndex, frameIndicator){
    var spacedSequence = new Array();
    spacedSequence.push('<p>' + startIndex + " ");
    for(var i = 0; i < sequence.length; i+=3){
            spacedSequence.push(sequence.charAt(i) + sequence.charAt(i+1) + sequence.charAt(i+2) + " ");
    }

    for(var i=0; i<spacedSequence.length; i++){
        if (spacedSequence[i] == "ATG "){
            spacedSequence[i] = "<span style='color:#339933'><b>ATG </b></span>";
        }

        if (spacedSequence[i] == "TAG " || spacedSequence[i] == "TGA " || spacedSequence[i] == "TAA ") {
            spacedSequence[i] = "<span style='color:#e60000'><b>" + spacedSequence[i] + " </b></span>";
        }
    }

    // Every lots of 15, make a new paragraph and label with current character index.
    for(var i=0; i < spacedSequence.length; i++){
        if(i % 15 == 0 && i > 0){
            if(frameIndicator >= 3){
                spacedSequence[i] = spacedSequence[i] + "</p><p>" + (((startIndex)) - (i*3)) + "   ";
            } else {
                spacedSequence[i] = spacedSequence[i] + "</p><p>" + (((startIndex)) + (i*3)) + "   ";
            }
        }
    }

    spacedSequence.push("</p>");

    var formattedSequence = "";
    for(var i=0; i<spacedSequence.length; i++){
        formattedSequence += spacedSequence[i];
    }

    return formattedSequence;
}