$(function() {
  if($("#dg-container").length > 0){
    $('#dg-container').gallery({
      autoplay:	true
    });
  }

  function bookScroll(){
    try{
      if($(".book-scroll")){
        var $book 		= $(".book-scroll");
        var $bookWrap 	= $(".book-scroll-wrap>ul");
        var $bookPrev	= $book.find(".btn-prev");
        var $bookNext	= $book.find(".btn-next");
        var $bookItem	= $bookWrap.find("li");
        var bookCount	= $bookItem.length;
        var pageNum 	= 4;
        var cur 		= 0;
        var $cur = $($bookItem[cur]);
        var $clientWidth = document.documentElement.clientWidth
        var mobileWidth = $clientWidth < 880

        if (mobileWidth) {
          var contentWidth = $clientWidth - 70
        } else {
          var contentWidth = Math.floor($clientWidth/2.2) + 'px';
        }
        $cur.css("width",contentWidth);

        var play = function(){
          
          var $cur = $($bookItem[cur]);
          $cur.animate({width:contentWidth},500,function(){
            $cur.width = contentWidth;
            $cur.addClass('open');
          });
          $cur.siblings('li.open').stop().animate({width:129},500,function(){
            $cur.siblings('li.open').removeAttr("style");
            $cur.siblings('li.open').removeClass('open');
          });
          
          if (mobileWidth) {
            // console.log(cur)
            $bookWrap.stop().animate({"margin-left":(-(cur*139))},500);
          } else {
            if(cur-pageNum>=-1){
              $bookWrap.stop().animate({"margin-left":(cur-pageNum+2)*-139},500);
            }else{
              $bookWrap.stop().animate({"margin-left":0},500);
            }
          }
          

        }
        $bookItem.on("click",function(){
          cur = $(this).index();
          play();
        });
        $bookPrev.on("click",function(){
          cur = cur - 1 < 0 ? 0 : cur - 1;
          play();
        });
        $bookNext.on("click",function(){
          cur = cur + 1 >= bookCount ? 0 : cur + 1;
          play();
        });

      }
    }catch(e){}
  }
  window.onresize = function(){
    bookScroll();
  }
  bookScroll();

  function search() {
    var $search = $("#search");
    $search.on("click",function(){
      $search.parent().css("overflow","inherit");
      $search.prev().focus();
    });
    $(document).click(function(e){
      if(!$search.parent().is(event.target) && $search.parent().has(event.target).length === 0){
        $search.parent().removeAttr("style");
        $search.prev().val('');
      }
    });
    
  }
  search();

  function menu() {
    var $menu    = $("#menu");
    var $menubox = $("#menu-box");
    var $menuClose = $("#menu-close");
    var $p = $("<p></p>");

    $menu.on("click",function(){
      $menubox.stop().animate({"top":0},500);
      $menubox.parent().append($p);
      $menubox.next().animate({opacity:'0.6'},1000);
      $("body").css({"overflow":"hidden"});
    });
    $menuClose.on("click",function(){
      $menubox.stop().animate({top:'-100%'},500);
      $menubox.next().remove();
      $("body").removeAttr("style");
    })
  }
  menu();

});