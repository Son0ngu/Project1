document.addEventListener('DOMContentLoaded', function () {
    const bidButton = document.getElementById('bid-button');
    const exitButton = document.getElementById('exit-button');
    const quantityButton = document.getElementById('quantity-button');
    const totalPriceDiv = document.getElementById('total-price');

    let currentBid = 1100000;
    const bidStep = 10000;
    let quantity = 10;

    bidButton.addEventListener('click', function () {
        currentBid += bidStep;
        bidButton.textContent = `Bidding : ${currentBid.toLocaleString('vi-VN')} vnd`;
        placeBid('itemID123', 'userID123', currentBid);
    });

    quantityButton.addEventListener('click', function () {
        let newQuantity = prompt('Enter new quantity:', quantity);
        if (newQuantity !== null && !isNaN(newQuantity)) {
            quantity = parseInt(newQuantity);
            quantityButton.textContent = quantity;
            updateTotalPrice();
        }
    });

    function updateTotalPrice() {
        const totalPrice = quantity * bidStep;
        totalPriceDiv.textContent = `${totalPrice.toLocaleString('vi-VN')}vnd`;
    }

    function placeBid(itemID, userID, amount) {
        fetch('/api/bids', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ itemID, userID, amount })
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
            window.location.href = 'Homepage.html'; // Redirect to the home page on exit
        });
    }
});
