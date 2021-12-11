
var search =  document.getElementById('search')
search.onclick = function(e){
    const main = document.getElementById('form')
    if(main){
    main.innerHTML = ` 
     <input type="text" placeholder="search here" style="border-radius: 0.2rem; height: 70%; width: 120%; border:solid black 1px">
     <button style="position: absolute; bottom: 40%; right: -38%; border:solid white 1px">Tìm kiếm</button>`
}

search.onblur = function(){
  const main = document.getElementById('form')
  if(main){
    console.log(a)
  }
}
}
var index= 1;

var next = document.getElementById('next')
var prev = document.getElementById('prev')
show(index)
function nextslide(n){
   show(index+=n)
}

function show(n){
  var i;
  var listslide = document.getElementsByClassName('swiper-slide')
  if(n>listslide.length){
    index=1
  }
  if(n<1){
    index=listslide.length
  }
  for(i= 0;i<listslide.length;++i){
    listslide[i].style.display = 'none';
  }
  listslide[index-1].style.display = 'block';
}
next.onclick = function(){
  nextslide(1)
}
prev.onclick = function(){
  nextslide(-1)
}
function check(){
  var list =  document.querySelectorAll('.h3')
  for(var i=0;i<list.length;++i){
    list[i].onclick = function(e){
      e.target.href = `/user/category/${e.target.innerHTML}` 
    }
  }
}
function check2(){
  var list =  document.querySelectorAll('.link')
  for(var i=0;i<list.length;++i){
    list[i].onclick = function(e){
      e.target.href = `/user/category/${e.target.innerHTML}` 
    }
  }
}
check2()
check()