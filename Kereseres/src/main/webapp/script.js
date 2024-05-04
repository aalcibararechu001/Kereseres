// Get the canvas element and store it in the `ctx` variable
let ctx = document.getElementById('myChart');

// Declare global variables for the chart instance and JSON data
let myChart;
let jsonData;

// Send a GET request to load data from the `data.json` file
fetch("/opt/wildfly/bin/data.json")
  .then(function(response) {
    if (response.status == 200) {
      return response.json();
    }
  })
  .then(function(data) {
    // Assign the data from the JSON file to the `jsonData` global variable
    jsonData = data;

    // Call the `createChart` function to create the chart from the JSON data
    // The function takes two parameters: the JSON data and the chart type
    // Here, we initialize the chart type to `bar`
    createChart(jsonData, 'bar');
  });

// Function to create the chart
function createChart(data, type) {
  // Create a new instance of the Chart object
  myChart = new Chart(ctx, {
    // Set the chart's type to the specified type
    type: type,
    data: {
      // Extract labels and data from the JSON data using the `map` method
      labels: data.map(row => row.month),
      datasets: [{
        label: '# of Income',
        data: data.map(row => row.income),
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      },
      // Make the chart responsive
      responsive: false,
      maintainAspectRatio: false
    }
  });
}

// Function to dynamically change the chart type
function setChartType(chartType) {
  // Destroy the current chart object
  myChart.destroy();

  // Render a new chart with the updated chart type
  createChart(jsonData, chartType);
}
