function getUserActivities(userId) {
    const userActivitiesDiv = document.getElementById('userActivities');
	
    userActivitiesDiv.innerHTML = '';

    fetch(`/api/users/${userId}/activities`)
        .then(response => response.json())
        .catch(error => {
            console.error('Error fetching user activities:', error);
            userActivitiesDiv.innerHTML = `<p>Error fetching activities for user ID: ${userId}</p>`;
        });
}
