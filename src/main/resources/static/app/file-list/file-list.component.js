angular.
	module('fileList').
	component('fileList', {
		templateUrl: 'app/file-list/file-list.template.html',
		controller: function FileListController($scope, $rootScope, $http, $translate, DTOptionsBuilder, DTColumnBuilder) {

			var self = this;
			
			self.dtInstance = {};

			self.dtOptions = $rootScope.newDTOptionsBuilder($scope, 'http://localhost:8081/services/file');

			self.dtColumns = [
			    DTColumnBuilder.newColumn('name', $translate('file.name')),
			    DTColumnBuilder.newColumn('username', $translate('file.username')),
			    DTColumnBuilder.newColumn('dateUpload', $translate('file.dateUpload')),
			    
			    DTColumnBuilder.newColumn('chunksNumber', $translate('file.chunksNumber')),
			    DTColumnBuilder.newColumn('status', $translate('file.status')),
			    
			    DTColumnBuilder.newColumn(null, $translate('common.actions')).notSortable().renderWith(function(data, type, full, meta) {
			    	var ret = '<div class="text-nowrap">';
			    	/*ret += '<a ng-href="#!/teams/' + data.id + '"><button class="btn btn-primary" type="button"><i class="fa fa-pencil"></i></button></a>&nbsp;';
                	ret += '<a href="javascript:;" ng-click="deleteTeam(' + data.id + ');"><button class="btn btn-danger" type="button"><i class="fa fa-times"></i></button></a>&nbsp;';*/
                	ret += '</div>';

                	return ret;
                })           
            ];
/*			
			$scope.alterTeam = function(date) {
				$scope.team = date;
			};*/

			$scope.refresh = function() {
		        self.dtInstance.rerender();
			};
			/*
			$scope.deleteTeam = function(id) {
				if(confirm($translate.instant('common.deleteConfirm'))) {
					$http.post('services/team/delete/' + id).then(function(response) {
						$rootScope.successMessage();
						$scope.refresh();
					}, function(response) {
						if(response.data.exception.indexOf('DataIntegrityViolationException') >= 0) {
							$rootScope.warningMessage('common.deleteConstraint');
						} else {
							$rootScope.errorMessage(response);	
						}
					});
				}
			};*/
		}		  
	});
