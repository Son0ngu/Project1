async function submitForm() {
    const Email = document.getElementsByClassName("Email")[0].value;
    const username = document.getElementsByClassName("username")[0].value;
    const password = document.getElementsByClassName("Password")[0].value;
    const phone = document.getElementsByClassName("phone")[0].value;
    const address = document.getElementsByClassName("address")[0].value;

    // Hash and salt the password using bcryptjs
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);

    try {
        const response = await fetch('/Signup.app', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                Email: Email,
                username: username,
                password: hashedPassword, // Use the hashed password
                phone: phone,
                address: address
            })
        });

        if (response.status === 200) {
            const data = await response.json();
            const message = data.message;
            const userIdMatch = message.match(/User ID: (\d+)/);

            alert(data.message);
            if (userIdMatch && userIdMatch[1]) {
                const userId = userIdMatch[1];
                localStorage.setItem('userId', userId);
                console.log(message);
                window.location.href = `Homepage.html?id=${userId}`;
            } else {
                console.error("There's something wrong in signup function");
            }
        } else if (response.status === 401) {
            const data = await response.json();
            alert(data.message);
            console.log(data);
            console.log(response);
        } else {
            const data = await response.json();
            console.log(data);
            console.log(response);
            alert(data.message || 'An unknown error occurred.');
        }
    } catch (error) {
        console.error('Error during signup request:', error);
        alert('An error occurred. Please try again.');
    }
}
