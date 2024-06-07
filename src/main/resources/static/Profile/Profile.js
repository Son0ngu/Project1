async function submitForm() {
    const username = document.getElementsByClassName("username")[0].value;
    const password = document.getElementsByClassName("password")[0].value;
    const address = document.getElementsByClassName("address")[0].value;
    const phone = document.getElementsByClassName("phone")[0].value;

    // Hash and salt the password using bcryptjs
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);

    try {
        const response = await fetch('/updateProfile', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username: username, password: hashedPassword, address: address, phone: phone })
        });

        if (response.status === 200) {
            const data = await response.json();
            alert(data.message);
            window.location.href = 'Homepage.html'; // Redirect to homepage
        } else {
            const data = await response.json();
            alert(data.message || 'An unknown error occurred.');
        }
    } catch (error) {
        console.error('Error during profile update request:', error);
        alert('An error occurred. Please try again.');
    }
}

async function createRoom() {
    try {
        const response = await fetch('/createRoom', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.status === 200) {
            const data = await response.json();
            alert(data.message);
        } else {
            const data = await response.json();
            alert(data.message || 'An unknown error occurred.');
        }
    } catch (error) {
        console.error('Error during create room request:', error);
        alert('An error occurred. Please try again.');
    }
}
