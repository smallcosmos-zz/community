function post() {
    var id = $("#question_id").val();
    var content = $("#comment-content").val();
    if(!content){
        alert("评论不能为空~~~")
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:"application/json",
        dataType:"json",
        data:JSON.stringify( {
            "parentId":id,
            "content":content,
            "type":1
        }),
        success: function(msg){
            if(msg.code == 200){
                $(".comment-section").hide();
                window.location.reload();
            }else{
                if(msg.code == 2003){
                    var isAccepted = confirm(msg.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=04756ad84718e094174b&redirect_uri=http://localhost:8080/callback&scope=user&state=1")
                        window.localStorage.setItem("closeable", "true");
                    }
                }else{
                    alert(msg.message)
                }

            }
        }
    });
}