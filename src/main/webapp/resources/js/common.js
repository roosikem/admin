
function deleteUser( uid) {
	  
		 var userId=$(this).data("value");
		 var trr="#deleteuser"+uid;
		 var $tr = $(trr).closest('tr');
			var url=ctx+"/user/deleteUser";
			if (confirm('Are you sure you want to delete this user')) {
		$.ajax({
			url:url,
			 type: "GET",
			 data: { id: uid},
			 contentType: "application/text",
			    dataType : "text",
			success:function(data){
				$tr.remove();
				alert(data);
			},
		    error:function(tx){
		    	console.log(tx.responseText);
		    	alert(tx.responseText);
		    }
		      });
			}
	};
	
$(document).ready(function(){
	     $("#emaill").change(function(){
	    	 var url=ctx+"/user/checkUser";
	         var email = $(this).val();
	         var display='<img src="/admin/resources/js/loading.gif">';
	         if(email.length > 2){
	             $(".email").html(display);
               $.ajax({
      			url:url,
      			 type: "GET",
      			 data: { email:email},
      			 contentType: "application/text",
      			    dataType : "text",
      			success:function(data){
       				if(data=="NOT"){
       					$(".email").hide();
       					$(".email").text("Email already exist").show();
      				}
      				else{
      					$(".email").hide();
      					$(".email").text("Email available").css("color","green").show();
      				}
      			},
      		    error:function(tx){
      		    	console.log(tx.responseText);
      		    	alert(tx.responseText);
      		    }
      		      });
	                }
	         else{
	            $(".email").html("<font color=red>Not a valid email</font>");
	         }
	         
	     });
	 });
	function validateForm(){
		
		var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
		var numberformat=/^\d{10}$/;
		var selectedGender=$("input:radio[name=sex]:checked").val();
		var fname=$("#firstName").val();
		var lname=$("#lastName").val();
		var email=$("#email").val();
		var phone=$("#phoneno").val();
		var address=$("#address").val();
		
		if(fname===""){
			$(".fname").text("Enter First Name").show();
			
			return false;;
		}
		$(".fname").hide();
		if(lname===""){
			$(".lname").text("Enter Last Name").show();
			
			return false;
		}
		$(".lname").hide();
		if(!email.match(emailRegex)){
			$(".email").text("Enter valid email").show();
			
			return false;
		}
		$(".email").hide();
		if(!phone.match(numberformat)){
			$(".phone").text("Enter valid phone number").show();
			
			return false;
		}
		$(".phone").hide();
		if(!$('input:radio:checked').length>0){
			$(".gender").text("select gender").show();
			
			return false;
		}
		$(".gender").hide();
		if(address===""||address.length<3){
			$(".address").text("enter address").show();
			
			return false;
		}
		$(".address").hide();
		return true;
	}
	function submitForm(){
	if(validateForm() && validateEmail())	
	     {
			$("#myForm").submit();
	     }else{
	    	
	     }
      }
     
     function validateEmail(){
			var url=ctx+"/user/checkUser";
			var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
			var email=$("#email").val();
			var display='<img src="/admin/resources/js/loading.gif">';
			$(".img").show();
			$(".img").html(display);
			var avail=false;
			if(email.match(emailRegex)){
				
			$.ajax({
				 async: false,
      			url:url,
      			 type: "GET",
      			 contentType:"application/text",
      			 data: { email:email},
      			 success: function(data)          
      		     {
      				debugger;
      		     },
      			complete:function(data){
      				debugger;
      				
      				if(data.responseText=="NOT"){
      					avail=false;
      				}else{
      					$(".img").hide();
      					$(".email").hide();
       					$(".email").text("Email available").css("color","green").show();
      					avail=true;
      				}
      				if(!avail){
      					$(".img").hide();
      					$(".email").hide();
      					$(".email").text("Email already exist").css("color","red").show();
      					return avail;
      				}
       			},
      		    error:function(tx){
      		    	console.log(tx.responseText);
      		    	alert("hello "+tx.responseText);
      		    }
      		 });
			}else{
				$(".email").hide();
					$(".email").text("Not a valid Email").css("color","red").show();
			}
			return avail;
      }