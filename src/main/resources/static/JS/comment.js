document.getElementById('submitbtn').addEventListener('click', function(event) {
    // Grab values from the comment form fields
    const comment = document.getElementById('new-comment').value.trim();
    const username = document.getElementById('username').value.trim();
    const rating = document.getElementById('rating').value;

    if (!username) {
        alert("You must enter a username");
        event.preventDefault();
        return;
    }
    if (!comment) {
        alert("Comment cannot be empty.");
        event.preventDefault(); // Prevent submission if invalid
        return;
    }
    if (!rating || rating < 1 || rating > 5) {
        alert("Please select a valid rating between 1 and 5.");
        event.preventDefault();
        return;
    }

});
