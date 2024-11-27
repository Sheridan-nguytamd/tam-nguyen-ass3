document.addEventListener('DOMContentLoaded', function () {
    
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('userId');
    const activitiesTableBody = document.getElementById('activitiesTableBody');

    if (!userId) {
        alert('User ID not provided');
        return;
    }

    
    function loadActivities() {
        fetch(`/api/users/${userId}/activities`)
            .then(response => response.json())
            .then(activities => {
                activitiesTableBody.innerHTML = '';
                activities.forEach(activity => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${activity.activityType}</td>
                        <td>${activity.duration}</td>
                        <td>${activity.caloriesBurned}</td>
                        <td>${activity.activityDate}</td>
                    `;
                    activitiesTableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error loading activities:', error);
                console.log('Failed to load activities');
            });
    }

    
    loadActivities();

   
});
