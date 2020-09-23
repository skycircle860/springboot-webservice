var stock_page = {

    init : function () {
        var _this = this;
        $('#btn_search').on('click', function () {
            _this.search();
        });

    },
    search : function () {
       var stockdata = $('input[name=inlineRadioOptions]:checked').val();

        alert(stockdata);
        $.ajax({
            type: 'POST',
            url: '/api/v1/stock',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(stockdata)
        }).done(function(data){
            $('#stockDataDefault').empty();
            //$('#stockDataDiv').html(data);
            $('#stockDataDiv').load("refreshStockList?data="+stockdata);
            //alert('done');
            }).fail(function(error){
            alert(JSON.stringify(error));
        });

    }
};
stock_page.init();



/** var stockdata = {
                            stock_title:$('#stock_title').val(),
                            ckflag :$('#hiddenCkflag').val()
                           };

                            alert(stockdata);
                                   $.ajax({
                                       type: 'POST',
                                       url: '/api/v1/stock',
                                       dataType: 'html',
                                       contentType:'application/json; charset=utf-8',
                                       data: JSON.stringify(stockdata)
                                   }).done(function(data){  # encodeURI 안써서 에러나는게 있어서 검색해서 붙여봄
                                $('#stockDataDiv').load("refreshStockList?data="+encodeURI(data));

                           **/