document.querySelectorAll('.playerButton').forEach(button => {
    button.addEventListener('click', (e) => {
        const player = e.target.dataset.player;

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
    });
});