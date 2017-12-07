var id = location.search.split('?id=')[1];

getMessage();
getOtherMessage();

/**
 * 根据id获取详细属性
 */
function getMessage() {
    $.ajax({
        url: '/QueryRecruitById',
        type: 'get',
        data: {
            "id": id
        }
    })
        .done(function (response) {
            addMainMessage(response);
        })
        .fail(function () {
            alert("发生了未知错误，请稍后再试！");
        });
}

/**
 * 将详细属性添加进网页
 * @param response ajax获取的招聘信息
 */
function addMainMessage(response) {
    var body = $('.panel-body').eq(0);
    body.html('');
    var json = response[0];
    if (json['verifyFlag'] === 1) {
        var img = $('<span class="signature-b"><img src="../images/check-b.png"/></span>');
        body.append(img);
    }
    var message = $('<p class="title-b">' + json['job'] + '</p>\n' +
        '            <p>公司：' + json['company'] + '</p>\n' +
        '            <p>工作地点：' + json['workPlace'] + '</p>\n' +
        '            <p>招聘人数：' + json['recruitCount'] + '人</p>\n' +
        '            <p>薪资：' + json['salary'] + '</p>\n' +
        '            <p class="title-b">岗位职责</p>\n' +
        '            <p>\n' +
        json['positionStatement'] +
        '            </p>\n' +
        '            <p class="title-b">联系方式</p>\n' +
        '            <p>13257759595</p>');
    body.append(message);
}

/**
 * 获取额外信息
 */
function getOtherMessage() {
    var url = '/QueryRecruit';

    $.ajax({
        url: url,
        type: 'GET',
        data: {"pagesize": 3, "pagenum": 1}
    })
        .done(function (response) {
            addOtherMessage(response);
        })
        .fail(function () {
            alert("发生了未知错误，请稍后再试！");
        })
    ;
}

/**
 * 添加额外信息
 * @param message 获取的json数据
 */
function addOtherMessage(message) {
    var ul = $('.list-group.line-group');
    ul.html('');
    for (var context = 0; context < message.length; context++) {
        var recruit = message[context];
        var li = $('<li class="list-group-item">' + recruit['job'] +
            '<br>' + recruit['company'] + '<span>' + recruit['salary'] + '</span></li>\n');
        ul.append(li);
        (function (id) {
            li.on('click',function () {
                location.href="detail.html?id="+id;
            });
        })(recruit.id);
    }

}
