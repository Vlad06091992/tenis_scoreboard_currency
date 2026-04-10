window.addEventListener('load', function() {

    const buttonsContainer = document.querySelector('.buttons_container')
    const isFinished = JSON.parse(buttonsContainer.dataset.finished);
    const buttons = document.querySelectorAll('.playerButton')

    if(!isFinished){
        buttons.forEach(el=>{
            el.disabled = false
        })
    }

    buttons.forEach(button => {
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

})