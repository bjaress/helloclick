$(function() {

    //client-side expand/collapse with plugin
    $("td.comment").expander();

    //quick-and-dirty validation with all fields required
    $("form").submit(function() {
        var valid = true;
        $("input[type=text], textarea", $(this)).each(function (){
            $(this).removeClass('error');
            $(this).val($.trim($(this).val()));
            if ($(this).val().length === 0) {
                valid = false;
                $(this).addClass('error');
            }
        });
        return valid;
    });

    //username links
    $(".user").each(function () {
        var user = $.trim($(this).text());
        var link = $("<a href='#'/>").text(user);
        $(this).empty().append(link);
        link.click(function (){
            $.get('#', {'pageAction': 'fetchProfile', 'user': user},
                function(data) { alert(data); },
                "text");
        });
    });
});
