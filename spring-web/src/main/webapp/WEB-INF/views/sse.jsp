<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="${baseUrl}"/>
    <title>SSE Demo</title>
</head>
<body>
<div id="msgFrompPush"></div>
<script type="text/javascript" src="resources/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
    // EventSource 对象只有新式的浏览器才有(Chrome 等)，它的 SSE 的客户端。
    if (window.EventSource) {
        var source = new EventSource('push');

        // 添加 SSE 客户端监听，在此获得服务器端推送的消息。
        source.addEventListener('message', function (e) {
            $("#msgFrompPush").html(e.data + "<p>");
        });

        source.addEventListener('open', function (e) {
            console.log("连接打开.");
        }, false);

        source.addEventListener('error', function (e) {
            if (e.readyState == EventSource.CLOSED) {
                console.log("连接关闭");
            } else {
                console.log(e.readyState);
            }
        }, false);
    } else {
        console.log("你的浏览器不支持SSE");
    }
</script>
</body>
</html>