document.addEventListener('DOMContentLoaded', function () {
    const bidButton = document.getElementById('bid-button');
    const exitButton = document.getElementById('exit-button');
    //const quantityButton = document.getElementById('quantity-button');
    //const totalPriceDiv = document.getElementById('total-price');


    bidButton.addEventListener('click', function () {
        const bidAmountInput = document.getElementById('Bidding');
        const itemIdInput = document.getElementById('ItemId');

        const currentBid = parseFloat(bidAmountInput.value);

        if (isNaN(currentBid) || currentBid <= 0) {
            alert('Please enter a valid bid amount.');
            return;
        }

    const urlParams = new URLSearchParams(window.location.search);
    const itemID = itemIdInput.value;
    const roomID = urlParams.get('roomId');

    let userID = urlParams.get('userId');
        if (userID) {
            localStorage.setItem('userID', userID);
        } else {
            userID = localStorage.getItem('userID');
        }

    if (!itemID || !userID) {
        alert('Item ID or User ID is missing in the URL.');
        return;
    }

    bidButton.textContent = `Bidding : ${currentBid.toLocaleString('vi-VN')} vnd`;
    placeBid(itemID, userID, roomID, currentBid);
});
        

    function placeBid(itemID, userID, roomID, amount) {
        fetch('/api/bids', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ itemID, userID, roomID, amount })
        })
            .then(response => response.json())
            .then(data => {
                console.log('Bid placed:', data);
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    }
    

    if (exitButton) {
        exitButton.addEventListener('click', function () {
            const userID = localStorage.getItem('userID');
            if (userID) {
                window.location.href = `Homepage.html?id=${userID}`;
            } else {
                alert('User ID not found.');
            }
        });
    }
});
