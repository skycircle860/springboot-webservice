//여기서 init 함수를 index 변수의 속성으로 만든것은 브라우저의 스코프는 공용공간으로 사용되기 때문에 나중에 로딩된 js의 init,save
//함수가 먼저 로딩된 js의 function을 덮어쓰게 된다. 다른 개발자들과 협업시 함수명이 중복되어 이런식으로 코드가 꼬일 수 있기 때문에
//이런 문제를 피하기 위해 index.js만의 유효범위(scope)를 만들어서 사용하기 위해 index란 객체를 만들어 해당 객체에서 필요한 모든 function을 선언하는것.
//이렇게 하면 index 객체 안에서만 function이 유효하므로 다른 js와 겹칠 일이 없다.
var index = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();
        //rest에서 crud는 다음과 같이 http method에 매핑된다. 생성:post,읽기:get,수정:put,삭제:delete
        //url: '/api/v1/posts/'+id 같은 경우는 어느게시글을 수정할지 url path로 구분하기 위해 path에 id를 추가한다.
        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

index.init();