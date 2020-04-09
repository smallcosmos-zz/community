function post() {
    var id = $("#question_id").val();
    var content = $("#comment-content").val();
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
            }else{
                alert(msg.message)
            }
        }
    });
}