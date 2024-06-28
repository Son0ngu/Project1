document.getElementById('inputItemForm').addEventListener('submit', function(event) {
    event.preventDefault(); 


    const itemName = document.getElementById('itemName').value;
    const startPrice = document.getElementById('startPrice').value;
    const instantSellPrice = document.getElementById('instantSellPrice').value;
    const description = document.getElementById('description').value;


    const itemData = {
        name: itemName,
        price: startPrice,
        instantSellPrice: instantSellPrice,
        description: description
    };


    fetch('createItem', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(itemData)
    })
    .then(response => response.json())
    .then(data => {

        console.log('Success:', data);
        alert('Item added successfully!');

        localStorage.setItem('itemID', `${data.itemId}`);
        window.location.href = `Bidding%02room.html?roomId=${data.itemId}?userID=${data.userId}`;

    })
    .catch((error) => {
        console.error('Error:', error);
        alert('Failed to add item.');
    });
});
