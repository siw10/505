/**
 * 
 */
/*$.datepicker.setDefaults({
    dateFormat: 'yy-m-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    changeMonth: true,
    changeYear: true,
    minDate: 0,
    yearSuffix: '년'
    
});
 
var disabledDays = [[${disabledDayList}]];



function disableAllTheseDays(date) {

    var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

    for (i = 0; i < disabledDays.length; i++) {

        if($.inArray(y + '-' +(m+1) + '-' + d,disabledDays) != -1) {

            return [false];

        }

    }

    return [true];

}

 
 jQuery(function($) {
    $( "#bookingDate" ).datepicker({
		beforeShowDay: disableAllTheseDays
	});
});*/
 
 let index = {
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => {
            this.save(); // save함수 이벤트로 호출
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function () {
		var btype = $('input:radio[name=list]:checked').val();
        let data = {
            username: $("#username").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            btype: btype,
            bookingDate: $("#bookingDate").val()
            
        }

        // ajax 호출시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 데이터를 json으로 변경하여 insert 요청을 한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/booking",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            if(res.status === 500) {
                alert("예약에 실패했습니다");
            } else {
                alert("예약이 완료되었습니다.");
                location.href = "/bookingPage";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },



    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            name: $("#name").val(),
            userTel: $("#userTel").val(),
            dogName: $("#dogName").val(),
            dogType: $("#dogType").val()
            
        }

        $.ajax({
            type: "PUT",
            url: "/userModify",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

}


index.init();