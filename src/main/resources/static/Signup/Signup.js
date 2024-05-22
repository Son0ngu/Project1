function submitForm() {
    var username = document.getElementsByClassName("username")[0].value;
    var password = document.getElementsByClassName("password")[0].value;
    console.log(username + password);
    const loginInfo = {
        username: username,
        password: password
    };

    console.log(loginInfo);

    fetch('/register.app', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginInfo)
    })
        .then(response => {
            if (response.ok) {
                alert('SIGN IN SUCCESSFULLY DATA COLLECT: ' + data);
                return response.text();
            } else {
                throw new Error('Error: ' + response.statusText);
            }
        })
        .then(data => {
            alert('SIGN IN SUCCESSFULLY: ' + data);
            console.log(data);
        })
        .catch(error => console.error('ERROR:', error));
}