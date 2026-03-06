document.addEventListener('DOMContentLoaded', function() {
    const submitButton = document.querySelector(".submit_button");

    submitButton.addEventListener("click", function(e) {
        // Проверка полей ввода
        const player1Input = document.querySelector('input[name="player1"]');
        const player2Input = document.querySelector('input[name="player2"]');

        if (!player1Input.value.trim() || !player2Input.value.trim()) {

            player1Input.classList.add('player_input_invalid')

            alert('Пожалуйста, введите имена обоих игроков!');
            e.preventDefault();
        }
    });
});