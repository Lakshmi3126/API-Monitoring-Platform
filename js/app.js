// Mock API data
const apis = [
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

// Get the API list container 
const apiList = document.getElementById("api-list");

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

// Total number of APIs
const totalApis = apis.length;


// Number of healthy APIs
const healthyApis = apis.filter(function(api) {
    return api.status === "Healthy";
}).length;


// Number of down APIs
const downApis = apis.filter(function(api) {
    return api.status === "Down";
}).length;


// Average response time
const totalResponseTime = apis.reduce(function(total, api) {
    return total + api.responseTime;
}, 0);

const averageResponseTime = totalResponseTime / apis.length;


// Update the HTML
document.getElementById("total-apis").textContent = totalApis;

document.getElementById("healthy-apis").textContent = healthyApis;

document.getElementById("down-apis").textContent = downApis;

document.getElementById("avg-response").textContent =
    Math.round(averageResponseTime) + " ms";