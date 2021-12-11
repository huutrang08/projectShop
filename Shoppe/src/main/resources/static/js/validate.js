function validator(options) {
    var formElement = document.getElementById(options.form)
    function validatos(element,input) {
        var input = document.getElementById(element.selector)
        var message = input.parentElement.querySelector('.form-message')
        var err = element.test(input.value)
                if(err){
                    message.innerText = err
                }else{
                    message.innerText = ""
                }   
    }

    formElement.onsubmit = function(e){
        options.rules.forEach(element => {
            var input = document.getElementById(element.selector)
            if(element.test(input.value)!=undefined){
                e.preventDefault();
                validatos(element,input)
            }
            
        })
    }  
    if(formElement){
        options.rules.forEach(element => {
            var input = document.getElementById(element.selector)
            if(input){
                input.onblur = function() {
                  validatos(element,input)
                }
            }
        });
    }
}
function helo(){
    console.log("nhan")
}
helo()
validator.isRequired= function (selector,message) {
    return {
        selector: selector,
        test: function(value) {
            var x = /[a-z]/
           let result = x.test(value)
            if(result){
            
            }else{
                return message || 'nhập đúng tên'
            }
        }
    }
}
validator.isEmail = function(selector){
    return{
        selector: selector,
        test: function(value) {
            const x = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
            let result = x.test(value)
            if(result){
                return undefined 
            } else{
                return 'Nhập đúng Email'
            }
         }
    }
} 
validator.isPass = function(selector,min) {
    return {
        selector: selector,
        test: function(value) {
           const x = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/
           let result = x.test(value)
           if(result && value.length >=min){
               console.log("fales")
               return undefined 
           } else{
               return 'Nhập đúng mật khẩu và đủ 6 kí tự'
           }
        }

    }
}
validator.check = function(selector,confirm,message) {
    return {
        selector: selector,
        test: function (value) {
            return value === confirm() ? undefined : message || 'vui lòng nhập đúng trường này'
        }
    }
}
validator.isPhoneNunber= function (selector,message) {
    return {
        selector: selector,
        test: function(value) {
            const x = /^[0-9()]+$/;
           let result = x.test(value)
            if(result){
            return undefined
            }else{
                return message || 'nhập đúng sdt'
            }
        }
    }
}
validator.checkdate = function(selector){
  return{
      selector:selector,
      test: function(value){
          if(value){
              return undefined
          }
          else{
              return 'nhập ngày sinh'
          }
      }
  }
}