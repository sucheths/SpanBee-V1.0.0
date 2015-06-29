/* Directives */
angular.module('app.directives', [])
    .directive('matchContent', [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.matchContent;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {
                    // console.info(elem.val() === $(firstPassword).val());
                    ctrl.$setValidity('matchContent', elem.val() === $(firstPassword).val());
                });
            });
        }
    }
}]);