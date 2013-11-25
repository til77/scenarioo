'use strict';

describe('Controller: MainCtrl', function () {

    var $controller, $rootScope, $location, $httpBackend, Config, SelectedBranchAndBuild,
        UseCaseService, BranchesAndBuilds;

    var $scope;
    var MainCtrl;

    beforeEach(module('scenarioo.controllers'));

    beforeEach(inject(function(_$controller_, _$rootScope_, _$location_, _$httpBackend_, _SelectedBranchAndBuild_, _UseCaseService_, _BranchesAndBuilds_) {
        $controller = _$controller_;
        $rootScope = _$rootScope_;
        $location = _$location_;
        $httpBackend = _$httpBackend_;
        SelectedBranchAndBuild = _SelectedBranchAndBuild_;
        UseCaseService = _UseCaseService_;
        BranchesAndBuilds = _BranchesAndBuilds_;

        $scope = $rootScope.$new();
        MainCtrl = $controller('MainCtrl', {$scope: $scope, $location: $location,
            SelectedBranchAndBuild: SelectedBranchAndBuild, UseCaseService: UseCaseService,
            BranchesAndBuilds: BranchesAndBuilds});
    }));

    it('goes to use case page when use case is clicked', function() {
        expect($location.path()).toBe('');

        $scope.goToUseCase('SelectPhoneNumber');

        expect($location.path()).toBe('/usecase/SelectPhoneNumber');
    });

    it('loads usecases when branch and build selection changes', function() {
        expect($scope.branchesAndBuilds).toBeUndefined();
        $httpBackend.whenGET('http://localhost:8080/scenarioo/rest/branches/release-branch-2014-01-16/builds/example-build/usecases')
            .respond('[{"useCase":{"details":{"webtestClass":"org.scenarioo.uitest.example.testcases.FindPageUITest"},"status":"success","description":"User wants to search for a page and read it.","name":"Find Page"},"scenarios":[{"details":{"userRole":"unauthenticated"},"status":"success","description":"User enters text that is not found in pages content.","calculatedData":{"numberOfPages":1,"numberOfSteps":3},"name":"find_page_no_result"},{"details":{"userRole":"unauthenticated"},"status":"success","description":"User enters some text and finds multiple pages that contain this text.","calculatedData":{"numberOfPages":1,"numberOfSteps":5},"name":"find_page_with_text_on_page_from_multiple_results"},{"details":{"userRole":"unauthenticated"},"status":"success","description":"User enters page title that is ambiguous but matches directly a page, on the page he sees the list of other meanings, and can navigate to the page he meant.","calculatedData":{"numberOfPages":1,"numberOfSteps":7},"name":"find_page_with_title_ambiguous_navigate_to_other_meaning"},{"details":{"userRole":"unauthenticated"},"status":"success","description":"User enters exact page title and finds it directly.","calculatedData":{"numberOfPages":1,"numberOfSteps":3},"name":"find_page_with_title_direct"}]}]');
        $httpBackend.whenGET('http://localhost:8080/scenarioo/rest/branches').respond('[{"branch":{"description":"Just an example development branch from example docu generation example.","name":"release-branch-2013-11-14"},"builds":[{"linkName":"example-build","build":{"details":{},"status":"success","revision":"123456","date":1385284268394,"name":"example-build"}}]},{"branch":{"description":"Just an example development branch from example docu generation example.","name":"release-branch-2014-01-16"},"builds":[{"linkName":"example-build","build":{"details":{},"status":"success","revision":"123456","date":1385284268394,"name":"example-build"}}]},{"branch":{"description":"Just an example development branch from example docu generation example.","name":"trunk"},"builds":[{"linkName":"example-build","build":{"details":{},"status":"success","revision":"123456","date":1385284268394,"name":"example-build"}}]}]');

        $location.url('/?branch=release-branch-2014-01-16&build=example-build');
        $scope.$apply();

        $httpBackend.flush();

        // TODO: Finish test, refactor to callback instead of promise
        expect($scope.branchesAndBuilds).toBeDefined();
    });

});