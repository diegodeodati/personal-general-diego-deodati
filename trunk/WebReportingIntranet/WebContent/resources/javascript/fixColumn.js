$(document).ready( function () {
    var oTable = $('#locationsDataTable').dataTable( {
        "sScrollY": "300px",
        "sScrollX": "100%",
        "sScrollXInner": "150%",
        "bScrollCollapse": true,
        "bPaginate": false
    } );
    new FixedColumns( oTable );
} );