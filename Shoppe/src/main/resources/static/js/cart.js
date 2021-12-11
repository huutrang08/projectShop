var data = document.querySelectorAll('.unitPrice');
var x=0
var result=[];
for(let i =0;i<data.length;++i){
    x+=Number(data[i].innerHTML)
}
document.getElementById('price').innerText = `${x} VND`
console.log(x)
function check2(){
    var list =  document.querySelectorAll('.link')
    for(var i=0;i<list.length;++i){
      list[i].onclick = function(e){
        e.target.href = `/user/category/${e.target.innerHTML}` 
      }
    }
  }
  check2()
