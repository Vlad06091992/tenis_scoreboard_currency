async function sendPostRequest(data) {
    try {
        const response = await fetch(window.location.href, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });
        // window.location.reload()
    } catch (error) {
        console.error('Ошибка:', error);
        // window.location.reload()
    }
}


document.addEventListener('click', function(e) {
    if (e.target.classList.contains('playerButton')) {
        // const player = e.target.dataset.player; // лучше использовать dataset
        // sendPostRequest({player})


        const player = e.target.dataset.player;

        // Создаем и отправляем форму
        const form = document.createElement('form');
        form.method = 'POST';
        form.action = window.location.href;
        form.style.display = 'none';

        const input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'player';
        input.value = player;

        form.appendChild(input);
        document.body.appendChild(form);
        form.submit();

    }
});