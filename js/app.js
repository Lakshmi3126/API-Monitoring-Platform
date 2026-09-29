const savedApis = localStorage.getItem("apis");
// If there are saved APIs in localStorage, use them; otherwise, use the default APIs
const apis = savedApis ? JSON.parse(savedApis) : [
    {
        name: "GitHub API",
        url: "https://api.github.com",
        status: "Healthy",
        statusCode: 200,
        responseTime: 143
    },
    {
        name: "JSONPlaceholder",
        url: "https://jsonplaceholder.typicode.com/posts",
        status: "Healthy",
        statusCode: 200,
        responseTime: 221
    },
    {
        name: "Test API",
        url: "https://example.com/api",
        status: "Down",
        statusCode: 500,
        responseTime: 830
    }
];

function renderApis() {

    const apiList = document.getElementById("api-list");

    // Clear existing cards
    apiList.innerHTML = "";

    apis.forEach(function(api) {

        const card = document.createElement("div");
        card.classList.add("api-card");

        card.innerHTML = `
            <div class="api-info">
                <h3>${api.name}</h3>
                <p>${api.url}</p>
            </div>

            <div class="api-status">
                ${api.statusCode} | ${api.responseTime} ms | ${api.status}
            </div>
        `;

        apiList.appendChild(card);
    });
}



function updateStats() {

    const totalApis = apis.length;

    const healthyApis = apis.filter(function(api) {
        return api.status === "Healthy";
    }).length;

    const downApis = apis.filter(function(api) {
        return api.status === "Down";
    }).length;

    const totalResponseTime = apis.reduce(function(total, api) {
        return total + api.responseTime;
    }, 0);

    const averageResponseTime =
        totalApis > 0 ? totalResponseTime / totalApis : 0;


    document.getElementById("total-apis").textContent = totalApis;

    document.getElementById("healthy-apis").textContent = healthyApis;

    document.getElementById("down-apis").textContent = downApis;

    document.getElementById("avg-response").textContent =
        Math.round(averageResponseTime) + " ms";
}

// Add API form functionality
const addApiButton = document.getElementById("add-api-btn");
const addApiForm = document.getElementById("add-api-form");
const cancelButton = document.getElementById("cancel-btn");

// Open form
addApiButton.addEventListener("click", function() {
    addApiForm.style.display = "block";
});

// Close form
cancelButton.addEventListener("click", function() {
    addApiForm.style.display = "none";
});

// Handle form submission
const apiForm = document.getElementById("api-form");
apiForm.addEventListener("submit", function(event) {

    event.preventDefault();

    const name = document.getElementById("api-name").value;
    const url = document.getElementById("api-url").value;
    const method = document.getElementById("api-method").value;

    const newApi = {
        name: name,
        url: url,
        method: method,
        status: "Unknown",
        statusCode: "-",
        responseTime: 0
    };

    apis.push(newApi);
    // Save the updated APIs to localStorage
    localStorage.setItem("apis", JSON.stringify(apis));
    renderApis();
    updateStats();
    apiForm.reset();

    addApiForm.style.display = "none";
});

renderApis();
updateStats();