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
			    
			    DTColumnBuilder.newColumn('chunkCount', $translate('file.chunkCount')),
			    DTColumnBuilder.newColumn('status', $translate('file.status')),
			    
			    DTColumnBuilder.newColumn(null, $translate('common.actions')).notSortable().renderWith(function(data, type, full, meta) {
			    	var ret = '<div class="text-nowrap">';
			    	ret += '<a href="javascript:;" ng-click="download(' + data.id + ');"><button class="btn btn-primary" type="button"><i class="fa fa-download"></i></button></a>&nbsp;';
                	ret += '</div>';

                	return ret;
                })           
            ];

			$scope.openDialogUpload = function() {
			    $('#uploadDialog').modal('show');
			};

			$scope.upload = function() {
			};

			$scope.refresh = function() {
		        self.dtInstance.rerender();
			};
			
			$scope.download = function(fileId) {

				$http.get('http://localhost:8081/services/file/download/' + fileId).then(function(response) {
					
				    var blob = new Blob([response.data], {type:'text/html'});
				    var blobURL = (window.URL || window.webkitURL).createObjectURL(blob);

				    var contentDispositionHeader = response.headers('Content-Disposition');
				    var filename = contentDispositionHeader.split(';')[1].trim().split('=')[1];

				    var a = document.createElement("a");
				    a.download = filename;
				    a.href = blobURL;
				    a.click();
				});
			};
			
		}		  
	});
