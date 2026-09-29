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

    const name = document.getElementById("api-name").value.trim();
    const url = document.getElementById("api-url").value.trim();
    const method = document.getElementById("api-method").value;

    const formError = document.getElementById("form-error");

    formError.textContent = "";

    // Validation
    if (name === "") {
        formError.textContent = "API name is required.";
        return;
    }

    if (url === "") {
        formError.textContent = "API URL is required.";
        return;
    }

    const newApi = {
        name: name,
        url: url,
        method: method,
        status: "Unknown",
        statusCode: "-",
        responseTime: 0
    };

    apis.push(newApi);

    localStorage.setItem("apis", JSON.stringify(apis));

    renderApis();
    updateStats();

    apiForm.reset();

    addApiForm.style.display = "none";
});

renderApis();
updateStats();

// Function to check the status of an API
async function checkApi(api) {

    const startTime = performance.now();

    try {

        const response = await fetch(api.url, {
            method: api.method
        });

        const endTime = performance.now();

        api.responseTime = Math.round(endTime - startTime);
        api.statusCode = response.status;

        if (response.ok) {
            api.status = "Healthy";
        } else {
            api.status = "Down";
        }

    } catch (error) {

        api.status = "Down";
        api.statusCode = "-";
        api.responseTime = 0;

    }

    localStorage.setItem("apis", JSON.stringify(apis));

    renderApis();
    updateStats();
}
// Check all APIs on page load
async function checkAllApis() {

    for (const api of apis) {
        await checkApi(api);
    }
}

checkAllApis();
