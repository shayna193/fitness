function approvePark(parkName) {
    fetch(`/Admin/approvePark/${parkName}`, {
        method: 'POST'
    }).then(response => {
        if (response.ok) {
            alert("Park approved successfully!");
            location.reload(); // Reloads the page to update the approval status
        } else {
            alert("Error approving park.");
        }
    });
}