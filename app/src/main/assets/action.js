function clickImage(img)
	{
		var imgs = document.getElementsByTagName("img");
	    var imgSrcs = [];
	    var pos = 0;
	    var name = img.src;
	    for (var i = 0; i < imgs.length; i++) {
	        imgSrcs.push(imgs[i].src);
	        if(imgs[i].src == name){
	        	pos = i;
	        }
	    }
	   // Android.showToast(pos+" "+imgSrcs);
	    Android.showToast(img.width+" "+img.height);
	};
    function changeIframeSize(w,h) {
        var imgs = document.getElementsByTagName("iframe");
        for (var i = 0; i < imgs.length; i++) {
            imgs[i].height = h;
            imgs[i].width = w;
        }

  };
//  function testEcho(){
//       Android.showToast("123");
//  }