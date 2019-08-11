//填充概述卡片
$.ajax({
    url: "/xhy/api/getSummary",
    type: "get",
    success: function (data) {
        $('#userCount').html(data.userCount)
        $('#customerCount').html(data.customerCount)
        $('#receiptCount').html(data.receiptCount)
        $('#visitCount').html(data.visitCount)
    },
    error: function (data) {
        console.log(data)
    }
})
//填充图表

$.ajax({
    url: "/xhy/api/getOrder",
    type: "get",
    success: function (data) {
        $('#contractCount').html(data.contractCount)
        $('#recentCount').html(data.recentCount)
        $('#price').html(data.price)
    },
    error: function (data) {
        console.log(data)
    }
})

//格式化接待模式
function changeMode(value) {
    var mode;
    if (value != null) {
        if (value == 1)
            mode = "电话回访";
        else
            mode = "来访";
    }
    return mode;
}

//获取跟进记录信息
$.ajax({
    url: "/xhy/api/getBriefRecord",
    type: "get",
    success: function (data) {
        for (var i = 0; i < data.length; i++) {
            var j = i + 1;
            var staff = "#staff" + j
            var content = "#content" + j
            var customer = "#customer" + j
            var date = "#date" + j

            $(staff).html(data[i].staffName)
            $(content).html(data[i].content)
            $(customer).html(data[i].customerName)
            $(date).html(changeDate(data[i].date))
        }
    },
    error: function (data) {
        console.log(data)
    }
})

//时间戳格式化
function changeDate(value) {
    var dateVal = value + "";
    if (value != null) {
        var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        return date.getFullYear() + "-" + month + "-" + currentDate;
    }
}

//获取订单数据
$.ajax({
    url: "/xhy/api/getEachDay",
    type: "get",
    success: function (data) {
        var len = data.length > 10 ? 10 : data.length
        var list = data.slice(0, len)
        new Morris.Line({
            // ID of the element in which to draw the chart.
            element: 'myfirstchart',
            // Chart data records -- each entry in this array corresponds to a point on
            // the chart.
            data: list,
            // The name of the data record attribute that contains x-values.
            xkey: 'date',
            // A list of names of data record attributes that contain y-values.
            ykeys: ['amount'],
            // Labels for the ykeys -- will be displayed when you hover over the
            // chart.
            labels: ['Value']
        });
    },
    error: function (data) {
        console.log(data)
    }
})
//渲染待办事项

$.ajax({
    url: "/xhy/api/getDutyOnStaff",
    type: "get",
    success: function (data) {
       for(var i = 0;i<data.length;i++){
           $('#todoList').append('<li>\n' +
               '<a class="check-link"><i class="fa fa-square-o"></i> </a>'+
               '<small class="label label-primary"><i class="fa fa-clock-o"></i> 在岗</small>\n' +
               '<span class="m-l-xs">'+data[i].staffName+'</span>\n' +

               '</li>')
       }

    },
    error: function (data) {
        console.log(data)
    }
})