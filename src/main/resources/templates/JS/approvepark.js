function approvePark(parkName) {
    fetch(`/Admin/approvePark/${parkName}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                alert("Park approved successfully.");
                location.reload(); // Refresh to show updated lists
            } else {
                alert("Failed to approve park.");
            }
        });
}