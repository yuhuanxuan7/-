function validate() {
    /*
       Steps:
       1. input check;
         If any questions have not been answered, display an alert to the user telling
        them which question(s) they still need to complete.
         The incomplete question(s) for this attempt should be highlighted in yellow in the page
         Include functionality to ensure the user only selected 2 options for question 2
         The form should not submit to the server (in order to allow the user to return and complete it)
         The user should not be alerted about their score at this point

       2.answer scoring
       When all the questions have been attempted, your JavaScript should also mark the quiz as follows:
         Award 1 point for each correct answer (so there will be a max of 5)
         Accept only the correct spelling for question 4, but allow for the fact that
        the user could type it in any case (upper lower or mixed).
         Display an alert to the user telling them how many they scored (out of the maximum of 5)
        and that their answers and score will be sent to the server.
         Make sure you know the correct answers!
     */

    // check username
    var flag = empty_check();
    if (flag){
        // alert overall score of the users' answer
        var x = document.getElementsByName("thisScore");
        var thisScore=x[0].value = answers();
        alert("Your score is "+thisScore);
    }
    return flag;

}


// step1 input checking
    function empty_check() {
        var flag = true;
        // check name is empty and highlighted incomplete questions
        var InputForm = document.getElementsByTagName("input");
        var name = InputForm[0].value;

            if (name === ""){
                alert("Please input a name");
                highlight(0);
                flag=false;
            }
            // check question1 is empty and highlighted incomplete questions
            var optionForm = document.getElementsByName("Q1")[0].value;
            if (optionForm ==="" ){
                alert("please select an option");
                highlight(1);
                flag=false;
            }

            // check question2 is empty
            var limit2 = 2;
            var count2 = 0; // count the options already selected
            // choose Q2 HTML element
            for (var i=1;i<=4;i++){
                var x =InputForm[i];
                if (x.checked){
                    count2++;
                }
            }
            // check count and highlighted incomplete questions
            if (count2!==limit2){
                alert("You must select 2 options in question2");
                highlight(2);
                flag=false;
            }

            // check question3 is empty
            var radios = document.getElementsByName("Q3");
            var formValid = false;

            var i = 0;
            var count3 =0;
            while (!formValid && i < radios.length) {
                if (radios[i].checked) {
                    formValid = true;
                    count3++;
                }
                i++;
            }

            if (count3!==1){
                alert("Must select some option in question3!");
                // highlighted incomplete questions
                highlight(3);
                flag=false;
            }

            // check question4 is empty
            if (InputForm[9].value===""){
                alert("Please input an answer in question4");
                highlight(4);
                flag=false;
            }

        return flag;
    }

    //  highlight incomplete questions
    function highlight(index) {
        var paras = document.getElementsByTagName("p");
        paras[index].style.backgroundColor="yellow";
    }

// validate answers
function answers() {
    var score = 0;
    // validate Q1
    var Q1 = document.getElementsByTagName("option");
    if (Q1[2].checked){
        score++;
    }

    // validate Q2
    var Q2a = document.getElementById("Q2a");
    var Q2c = document.getElementById("Q2c");
    if (Q2a.checked){score++};
    if (Q2c.checked){score++};

    // validate Q3
    var Q3b = document.getElementById("Q3b");
    if (Q3b.checked){score++};

    // validate Q4
    var Q4 = document.getElementsByName("Q4");
    const correct_answer = "British, Australian and New Zealand Sign Language";
    const answer = Q4[0].value;
    // compare case insensitive
    if (correct_answer.localeCompare(answer,'en',{sensitivity:'base'})===0){score++};
    return score;
}






