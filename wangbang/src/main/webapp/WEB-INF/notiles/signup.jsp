<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="../css/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	
	var jungbok = 0;
	
	$('#overBtn').click(function(){
		var id = $('#id').val();
		
		if(id.trim()==""){
			alert("아이디를 입력해주세요.")
			$('#id').focus();
			return;
		} else {
			jungbok = 1;
		}
		
		$.ajax({
			type:'post',
			url:'../member/member_over.do',
			data:{id:id},
			success:function(response){
				var count = response.trim();
				if(count==1){
					alert("이미 사용 중인 ID입니다. 다른 ID를 입력해주세요.");
					$('#id').val("");
					$('#id').focus();
				} else if(count==0) {
					alert("사용 가능한 ID입니다.");
				}
			}
		});
	});
	
	$('#joinBtn').click(function(){
		var id = $('#id').val();
		var pwd = $('#pwd').val();
		var pwd2 = $('#pwd2').val();
		var name = $('#name').val();
		
		if(id.trim()==""){
			alert("아이디를 입력해주세요.")
			$('#id').focus();
			return;
		} else if(pwd.trim()==""){
			alert("비밀번호를 입력해주세요.")
			$('#pwd').focus();
			return;
		} else if(pwd2.trim()==""){
			alert("비밀번호 확인을 입력해주세요.")
			$('#pwd2').focus();
			return;
		} else if(name.trim()==""){
			alert("이름을 입력해주세요.")
			$('#name').focus();
			return;
		}
		
		if(jungbok==0){
			alert("아이디 중복 확인을 해주세요.")
			return;
		}
		
		if(pwd!=pwd2){
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.")
			$('#pwd').val("");
			$('#pwd2').val("");
			return;
		}
		
		alert("회원가입이 정상적으로 처리되었습니다.");
		$('#join').submit();
	});
});
</script>
    
</head>

<body class="h-100">
    
    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
            </svg>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->

    



    <div class="login-form-bg h-100">
        <div class="container h-100">
            <div class="row justify-content-center h-100">
                <div class="col-xl-6">
                    <div class="form-input-content">
                        <div class="card login-form mb-0">
                            <div class="card-body pt-5">
                                
                                    <h4 class="text-center" style="padding-bottom:50px;">회원가입</h4>
        
                                <form class="login-input" id="join" name="frm" method="post" action="../member/member_insert.do">
                                    <div class="form-group">
                                        <input type="text" class="form-control"  placeholder="ID" id=id name=id style="width:75%;display: initial;" required>&nbsp;
                                        <input type=button class="btn btn-outline-primary" id="overBtn" value="중복확인">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Password" id=pwd name=pwd required>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Confirm Password" id=pwd2 name=pwd2 required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control"  placeholder="Name" id=name name=name required>
                                    </div>
                                    <input type=button class="btn login-form__btn submit w-100" id="joinBtn" value="회원가입">
                                    <div class="text-center" style="padding-top:20px;">
                                    	<a href="../main/main.do">취소</a>
                                    </div>
                                </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    

    

    <!--**********************************
        Scripts
    ***********************************-->
    <script src="../plugins/common/common.min.js"></script>
    <script src="../js/custom.min.js"></script>
    <script src="../js/settings.js"></script>
    <script src="../js/gleek.js"></script>
    <script src="../js/styleSwitcher.js"></script>
</body>
</html>





