document.addEventListener('DOMContentLoaded', function () {
    // Get userId from query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('userId');
    const activityForm = document.getElementById('activityForm');

    if (!userId) {
        alert('User ID not provided');
        window.location.href = '/'; // Redirect to home page if userId is missing
        return;
    }

    // Handle form submission
    activityForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(activityForm);
        const newActivity = {};

        // Populate the activity object
        formData.forEach((value, key) => {
            newActivity[key] = value;
        });

        // Add userId to the activity object
        newActivity.userId = userId;

        // Submit the activity to the backend
        fetch(`/api/users/${userId}/activity`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newActivity),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to add activity');
                }
                console.log('Activity added successfully');
                window.location.href = `/activities?userId=${userId}`;
            })
            .catch(error => {
                console.error('Error adding activity:', error);
                console.log('Failed to add activity. Please try again.');
            });
    });
});
