document.addEventListener('DOMContentLoaded', function () {
    const userForm = document.getElementById('userForm');

    if (userForm) {
        userForm.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = new FormData(event.target);
            const user = {};

            formData.forEach((value, key) => {
                user[key] = value;
            });

            console.log('User object to submit:', user);

            fetch('http://localhost:8080/api/users', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(user),
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to submit user');
                }
                console.log('User submitted successfully');
                window.location.href = '/'; // Redirect to the home page
            })
            .catch(error => {
                console.error('Error submitting user:', error);
                alert('Failed to submit user. Please try again.');
            });
        });
    } else {
        console.error('Form with id "userForm" not found');
    }
});
