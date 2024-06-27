/*'use strict';

//console.log("siusiusiutest");


async function submitForm() {
    const username = document.getElementsByClassName("username")[0].value;
    const password = document.getElementsByClassName("password")[0].value;
    const phone = document.getElementsByClassName("phone")[0].value;
    const address = document.getElementsByClassName("address")[0].value;

    // Hash and salt the password using bcryptjs
    const saltRounds = 10;
    const hashedPassword = await bcrypt.hash(password, saltRounds);

    try {
        const response = await fetch('/register.app', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: hashedPassword, // Use the hashed password
                phone: phone,
                address: address
            })
        });

        if (response.status === 200) {
            const data = await response.json();

            console.log(data.message);

            alert(data.message);
            
            const message = data.message;
            const userIdMatch = message.match(/User ID: (\d+)/);

            console.log("Regester success");

            if (userIdMatch && userIdMatch[1]) {
                const userId = userIdMatch[1];
                localStorage.setItem('userId', userId);
                console.log(message);
                window.location.href = `/static/Homepage.html?id=${userId}`;

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

*/

'use strict';

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('signupForm');
    form.addEventListener('submit', async function(e) {
        e.preventDefault(); // Ngăn chặn hành động mặc định của form

        const username = document.querySelector('.username').value;
        const password = document.querySelector('.password').value;
        const phone = document.querySelector('.phone').value;
        const address = document.querySelector('.address').value;

        

        try {
            const response = await fetch('/register.app', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    username: username,
                    password: password, // Use the hashed password
                    phone: phone,
                    address: address
                })
            });

            if (response.status === 200) {
                const data = await response.json();

                console.log(data.message);
                alert(data.message);

                const message = data.message;
                const userIdMatch = message.match(/User ID: (\d+)/);

                console.log("Register success");

                localStorage.setItem('userId', `${data.userId}`); // Add user ID to local storage for future usage
                window.location.href = `static/Homepage.html?id=${data.userId}`;
                

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
    });
});

