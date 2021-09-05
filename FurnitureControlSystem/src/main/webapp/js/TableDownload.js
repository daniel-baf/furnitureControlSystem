let dataTable = document.getElementById("dataTable");

new TableCSVExporter(dataTable, true).convertToCSV();

$("#export-CSV").on("click", function () {
    const exporter = new TableCSVExporter(dataTable);
    const csvOutput = exporter.convertToCSV();
    const csvBlob = new Blob([csvOutput], {type: "text/csv"});
    const blobUrl = URL.createObjectURL(csvBlob);
    const anchorElement = document.createElement("a");

    anchorElement.href = blobUrl;
    anchorElement.download = "reporte-exportar.csv";
    anchorElement.click();

    setTimeout(() => {
        URL.revokeObjectURL(blobUrl);
    }, 500);
});