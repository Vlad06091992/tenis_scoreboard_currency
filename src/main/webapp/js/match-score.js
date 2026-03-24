//TODO переделать на событие конретного класса
// document.addEventListener('click', function(e) {
//     if (e.target.classList.contains('playerButton')) {
//         const player = e.target.dataset.player;
//
//         const form = document.createElement('form');
//         form.method = 'POST';
//         form.action = window.location.href;
//         form.style.display = 'none';
//
//         const input = document.createElement('input');
//         input.type = 'hidden';
//         input.name = 'player';
//         input.value = player;
//
//         form.appendChild(input);
//         document.body.appendChild(form);
//         form.submit();
//     }
// });

document.querySelector('.playerButton').addEventListener('click',(e)=>{
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
})