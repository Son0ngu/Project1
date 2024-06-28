console.log("test siusiu 1");

async function createRoom() {
    const userId = localStorage.getItem('userId');
    const roomName = document.getElementById('roomName').value;

    try {
        const response = await fetch(`/auction-rooms/create?ownerId=${userId}`, {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
                'Cache-Control': 'no-cache',
                'Pragma': 'no-cache',
                'Expires': '0'
            },
            body: JSON.stringify ({
                ownerId: userId,
                name: roomName
            })
        })

        console.log("test siusiu 2");

        if (response.status === 200) {
            const data = await response.json();
            const roomId = data.message;
            console.log(roomId);
            window.location.href = `Bidding room.html?roomId=${roomId}&userId=${userId}`;
            alert(`Room created:, ${roomId}`);
        } else {
            const data = await response.json();
            console.log(data);
            alert(data.message || 'Unknown error ?')
        }
    } catch (error) {
        console.error('Error during create room request:', error);
        alert('An error occurred. Please try again.');
    }
}