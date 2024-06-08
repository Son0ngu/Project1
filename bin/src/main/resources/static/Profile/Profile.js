// Notification toast variables
const notificationToast = document.querySelector('[data-toast]');
const toastCloseBtn = document.querySelector('[data-toast-close]');

// Add event listener to close notification toast
toastCloseBtn.addEventListener('click', function () {
    notificationToast.classList.add('closed');
});

// Function to submit profile form
async function submitForm() {
    const username = document.getElementsByClassName("username")[0].value;
    const password = document.getElementsByClassName("password")[0].value;
    const address = document.getElementsByClassName("address")[0].value;
    const phone = document.getElementsByClassName("phone")[0].value;

    const editProfileInfo = {
        username: username,
        password: password,
        address: address,
        phone: phone
    };

    console.log(editProfileInfo);

    try {
        const response = await fetch('/EditProfile.app', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(editProfileInfo)
        });

        console.log('Server response: ', response);

        if (response.status === 200) {
            const data = await response.json();
            alert(data.message);
            // Edit successful, redirect to homepage
            window.location.href = 'Homepage.html';
        } else {
            const data = await response.json();
            console.log(data);
            alert(data.message || 'An unknown error occurred.');
        }
    } catch (error) {
        console.error('Error during edit request:', error);
        alert('An error occurred. Please try again.');
    }
}

// Function to create a bidding room
function createRoom() {
    const userId = localStorage.getItem('userId');
    window.location.href = `Bidding room.html?id=${userId}`;
}

// Function to return to homepage
var returnHomepage = function() {
    window.location.href = 'Homepage.html';
};

// Add event listener to header logo elements for homepage redirection
for (var i = 0; i < headerLogo.length; i++) {
    headerLogo[i].addEventListener('click', returnHomepage, false);
}
