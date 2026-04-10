document.addEventListener('DOMContentLoaded', function() {
    const submitButton = document.querySelector(".submit_button");
    const player1Input = document.querySelector('input[name="playerEntity1"]');
    const player2Input = document.querySelector('input[name="playerEntity2"]');

    [player1Input, player2Input].forEach(el => {
        el.addEventListener("change", function() {
            this.classList.remove('player_input_invalid');
        });
    });

    submitButton.addEventListener("click", function(e) {

        const errors = []
        const firstPlayerName = player1Input.value.trim().length;
        const secondPlayerName = player2Input.value.trim().length;

        if (!firstPlayerName) {
            errors.push(player1Input)
        }

        if (!secondPlayerName) {
            errors.push(player2Input)
        }

        if(errors.length){
            errors.forEach(el=>el.classList.add('player_input_invalid'))
            alert('Пожалуйста, введите имена обоих игроков!');
            e.preventDefault();
        }
    });
});