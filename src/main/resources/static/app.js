const form = document.querySelector("#search-form");
const input = document.querySelector("#pokemon-name");
const statusEl = document.querySelector("#status");
const resultEl = document.querySelector("#result");

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const name = input.value.trim();
    if (!name) {
        setStatus("Enter a Pokemon name.", true);
        return;
    }

    setLoading(true);
    setStatus("Searching...");

    try {
        const response = await fetch(`/api/v1/pokemon/${encodeURIComponent(name)}`);
        const payload = await response.json();

        if (!response.ok) {
            throw new Error(payload.message || "Pokemon search failed.");
        }

        renderPokemon(payload);
        setStatus("Loaded from local API. Repeat searches are served faster by cache.");
    } catch (error) {
        renderEmpty(error.message);
        setStatus(error.message, true);
    } finally {
        setLoading(false);
    }
});

function renderPokemon(pokemon) {
    resultEl.className = "result";
    resultEl.innerHTML = `
        <article class="pokemon-card">
            <div class="art-wrap">
                <img src="${escapeAttribute(pokemon.imageUrl || "")}" alt="${escapeAttribute(pokemon.name)} artwork">
            </div>
            <div>
                <h2 class="pokemon-name">${escapeHtml(pokemon.name)}</h2>
                <ul class="meta">
                    <li>#${pokemon.id}</li>
                    <li>${pokemon.height / 10} m</li>
                    <li>${pokemon.weight / 10} kg</li>
                    <li>${pokemon.baseExperience || 0} XP</li>
                </ul>

                <p class="section-title">Types</p>
                <div class="pill-list">${pokemon.types.map(toPill).join("")}</div>

                <p class="section-title">Abilities</p>
                <div class="pill-list">${pokemon.abilities.map(toPill).join("")}</div>

                <p class="section-title">Base stats</p>
                <div class="stats">
                    ${pokemon.stats.map(toStat).join("")}
                </div>
            </div>
        </article>
    `;
}

function renderEmpty(message) {
    resultEl.className = "result is-empty";
    resultEl.innerHTML = `
        <div class="empty-state">
            <h2 class="error">No result</h2>
            <p>${escapeHtml(message)}</p>
        </div>
    `;
}

function toPill(value) {
    return `<span class="pill">${escapeHtml(value)}</span>`;
}

function toStat(stat) {
    const percent = Math.min(100, Math.round((stat.value / 180) * 100));
    return `
        <div class="stat">
            <strong>${escapeHtml(stat.name)}</strong>
            <span class="bar" aria-hidden="true"><span style="--value: ${percent}%"></span></span>
            <span>${stat.value}</span>
        </div>
    `;
}

function setStatus(message, isError = false) {
    statusEl.textContent = message;
    statusEl.classList.toggle("error", isError);
}

function setLoading(isLoading) {
    form.querySelector("button").disabled = isLoading;
}

function escapeHtml(value) {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

function escapeAttribute(value) {
    return escapeHtml(value);
}
