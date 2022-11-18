/**
 * Created by zhoupan on 9/4/16.
 */

$("#pickdate").dateDropper({
    animate: false,
    format: 'Y-m-d',
    maxYear: '2050'
});
$("#picktime").timeDropper({
    meridians: false,
    format: 'HH:mm'
});