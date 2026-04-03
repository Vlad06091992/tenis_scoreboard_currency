window.onload = function () {
    const url = new URL(window.location.href);
    let page = parseInt(url.searchParams.get("page")) || 1;
    let name = url.searchParams.get("name") || "";
    url.searchParams.set("page", page);
    window.history.replaceState({}, '', url);

    const input = document.querySelector(".player_input");
    const next = document.querySelector(".next");
    const prev = document.querySelector(".prev");
    const search = document.querySelector(".search");
    const clean = document.querySelector(".clean-button");

    input.value = name
    input.addEventListener('input', (e) => {
        if (e.target.value) {
            url.searchParams.set("name", e.target.value);
        } else {
            url.searchParams.delete("name"); // удаляем параметр, если поле пустое
        }

        window.history.replaceState({}, '', url);
    });


    [next, prev, search, input, clean].forEach(el => {
        el.disabled = page < 2 && el.classList.contains("prev");
    })

    next.addEventListener('click', function (e) {
        try {
            page = page + 1
            this.value = page
            url.searchParams.set("page", page);
            window.history.replaceState({}, '', url);
        } catch (e) {
            console.error('Error ' + e)
        }

    });

    prev.addEventListener('click', function (e) {
        try {
            page = page - 1
            this.value = page
            url.searchParams.set("page", page);
            window.history.replaceState({}, '', url);
        } catch (e) {
            console.error('Error ' + e)
        }
    });

    search.addEventListener('click', function (e) {
        try {
            page = 1
            this.value = 1
            url.searchParams.set("page", page);
            window.history.replaceState({}, '', url);
        } catch (e) {
            console.error('Error ' + e)
        }
    });

    clean.addEventListener('click', function (e) {
        try {
            url.searchParams.set("name", "");
            input.value = ""
            window.history.replaceState({}, '', url);
        } catch (e) {
            console.error('Error ' + e)
        }
    });

};