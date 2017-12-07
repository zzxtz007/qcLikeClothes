selectId = -1;

/**
 * 初始化menu父级标签
 */
function initModelFade() {
    var ul = $(".list-group.line-group-grey");
    var i = 1;
    $.ajax({
        url: "/GetAllFathersType",
        type: "post"
    }).done(function (f) {
        for (var type of f) {
            (function (t) {
                if (i === 1) {
                    var li = $("<li class='list-group-item' style='border-top: 0;'>" + t.name + "</li>");
                } else {
                    var li = $("<li class='list-group-item'>" + t.name + "</li>");
                }
                ul.append(li);
                var span = $("<span data-toggle='collapse'  href='#cate" + i + "'" +
                    "aria-expanded='false' aria-controls='cate" + i + "'><img " +
                    "src='images/down.png'/></span>");
                li.append(span);
                var div = $("<div id='cate" + i + "' class='collapse'></div>");
                li.append(div);
                (function (tt, ttt) {
                    show(tt, ttt)
                })(span, t);
                i++;
            })(type);
        }
    }).fail(function () {
        alert("发生了未知错误，请稍后再试！")
    })
}
/**
 * 初始化menu子级标签
 */
function show(span, type) {
    var div = $(span).siblings("div");
    $.ajax({
        url: "/GetAllSonByFatherId",
        type: "get",
        data: {"id": type.id}
    })
        .done(function (sonType) {
            var uul = $("<ul class='list-group noline-group' " +
                "style=' margin-left: 10px;'></ul>");
            div.append(uul);
            for (var index = 1; index <= sonType.length; index++) {
                var temp = sonType[index - 1];
                var li;
                if (index === 1) {
                    li = $("<li id='" + temp.id + "' class='list-group-item group-item111' style='margin-top: 0;'>" +
                        temp.name + "</li>");
                } else {
                    li = $("<li  id='" + temp.id + "' class='list-group-item group-item111'>" + temp.name + "</li>");
                }
                li.on("click", function () {
                    getSelectId(this);
                });
                uul.append(li);
            }
        })
        .fail(function () {
            alert("发生了未知错误，请稍后再试！")
        });
}

/**
 * 获取标签的id
 * @param li
 */
function getSelectId(li) {
    selectId = li.id;
}

function search() {
    if (selectId === -1) {
        alert("请先选择一个专题");
    } else {
        location.href = "index2_new.html?id=" + selectId;
    }
}

/**
 * 判断值是否为整数
 * @param obj
 * @returns {boolean}
 */
function isInteger(obj) {
    return obj % 1 === 0
}


/**
 * 初始化标题
 * @param iUrl
 * @param name
 */
function changeLable(iUrl, name) {
    var i = $(iUrl);
    if (i === null) {
        return;
    }
    i.text("'" + name + "'相关职业")
}

/**
 * 初始化index
 * @param boxUrl
 */