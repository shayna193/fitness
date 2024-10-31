document.getElementById('submitbtn').addEventListener('click', function(event) {
    //Grab values from the comment-display section of park.html and execute when a comment is present
    const comment = document.getElementById('new-comment').value;
    const username = document.getElementById('username').value || "Anonymous";
    const rating = document.getElementById('rating').value;
    event.preventDefault()
    console.log("test");

    if (comment) {

        //Create a div to add class styling and populate new variables with content, then append to the div
        const newContainer = document.createElement('div');
        newContainer.classList.add('comment');

        const newUser = document.createElement('h4');
        newUser.textContent = username;

        const newRating = document.createElement('p');
        newRating.textContent = `Rating: ${rating}⭐`;

        const newComment = document.createElement(`p`);
        newComment.innerHTML = comment;

        newContainer.appendChild(newUser);
        newContainer.appendChild(newRating);
        newContainer.appendChild(newComment);

        //place the div before the first element of the comment-display container and reset form fields
        const commentDisplay = document.getElementsByClassName('comment-display')[0];
        if (commentDisplay) {
            commentDisplay.insertBefore(newContainer, commentDisplay.firstChild);
        }
    }

    document.getElementById('username').value = '';
    document.getElementById('rating').value = '1';
    document.getElementById('new-comment').value = '';
})