var epsms= (function(){
  var t = {};
  // t.profiles = 'dev';
  t.config = {
    ctx: 'http://localhost:8090',
    textSeparator: '~@~'
  };
  t.setConfig = function(options){
    t.config = Object.assign(t.config, options);
  };

  t.checkImgInfo = function(file){
    var isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
    var isLt2M = file.size / 1024 / 1024 < 2;

    if (!isJPG) {
      app.$message.error('上传头像图片只能是 JPG,JPEG,JPE,PNG 格式!');
    }
    if (!isLt2M) {
      app.$message.error('上传头像图片大小不能超过 2MB!');
    }
    return isJPG && isLt2M;
  };

  /**
   * dependency http-vue-loader.js
   * @param path
   */
  t.vueLoader = function(path){
    return httpVueLoader(t.config.ctx + path);
  };

  t.url = function(path){
    var u = encodeURI(t.config.ctx + path).replace(/#/g, '%23');
    // console.log(u);
    return u;
  };

  t.openLink = function(url){
    // var b = document.createElement('a');
    // document.body.appendChild(b);
    // b.style.display = 'none';
    // b.href = url;
    // b.target= '_blank';
    // b.click();
    // document.body.removeChild(b);
    window.open(url);
  };
  // t.emptyPromise = function(rst){
  //     var p = new Promise(function(resolve, reject){
  //         var data = rst || {"result": "true"}
  //         resolve({data: data});
  //     });
  //     return p;
  // };
  t.distinctArr = function(arr){
    // var newArr = [];
    // for(var i=0;i<arr;i++){
    //     if(newArr.indexOf(arr[i]) == -1){
    //         newArr.push(arr[i]);
    //     }
    // }
    // return newArr;
    var obj = {}
    var newArr = []
    for(var i = 0; i < arr.length; i++) {
      if(!obj[arr[i]]){
        obj[arr[i]] = 1
        newArr.push(arr[i])
      }
    }
    return newArr;
    // return [...new Set(arr)];
  };

  t.any = function(arr, func){
    if(!Array.isArray(arr)){
      return false;
    }

    //var arr = items.slice(0);//clone
    var rst = false;
    for(var i=0;i<arr.length;i++){
      if(func(arr[i], i)){
        rst=true;
        break;
      }
    }
    return rst;
  };

  t.dateStr = function(datetimeStr){
    if(!datetimeStr){
      return datetimeStr;
    }
    if(datetimeStr.length<10){
      return datetimeStr;
    }
    return datetimeStr.substring(0,10);
  };

  /*1.用浏览器内部转换器实现html转码*/
  t.htmlEncode =function (html){
    //1.首先动态创建一个容器标签元素，如DIV
    var temp = document.createElement ("div");
    //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(旧版火狐，google支持)
    (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
    //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
    var output = temp.innerHTML;
    temp = null;
    return output;
  };
  /*2.用浏览器内部转换器实现html解码*/
  t.htmlDecode = function (text){
    //1.首先动态创建一个容器标签元素，如DIV
    var temp = document.createElement("div");
    //2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
    temp.innerHTML = text;
    //3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
  };
  /*3.用正则表达式实现html转码*/
  t.htmlEncodeByRegExp = function (str){
    var s = "";
    if(str.length == 0) return "";
    s = str.replace(/&/g,"&amp;");
    s = s.replace(/</g,"&lt;");
    s = s.replace(/>/g,"&gt;");
    s = s.replace(/\s/g,"&nbsp;");
    s = s.replace(/\'/g,"&#39;");
    s = s.replace(/\"/g,"&quot;");
    return s;
  };
  /*4.用正则表达式实现html解码*/
  t.htmlDecodeByRegExp = function (str){
    var s = "";
    if(str.length == 0) return "";
    s = str.replace(/&amp;/g,"&");
    s = s.replace(/&lt;/g,"<");
    s = s.replace(/&gt;/g,">");
    s = s.replace(/&nbsp;/g," ");
    s = s.replace(/&#39;/g,"\'");
    s = s.replace(/&quot;/g,"\"");
    return s;
  };
  t.textareaInput = function(event){
    // console.log(event);
    if(event.isTrusted){
      event.target.value = event.target.value.replace(/\n/g, '');
      event.target.dispatchEvent(new Event('input'));
    }
  };
  //定义整个数字全部转换的方法，需要依次对数字进行10000为单位的取余，然后分成小节，按小节计算，当每个小节的数不足1000时，则需要进行补零

  t.numToChinese = function(num){

    //如果数字含有小数部分，那么可以将小数部分单独取出
    //将小数部分的数字转换为字符串的方法：

    var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
    var chnUnitSection = ["","万","亿","万亿","亿亿"];
    var chnUnitChar = ["","十","百","千"];

    var numToChn = function(num){
      var index =  num.toString().indexOf(".");
      if(index != -1){
        var str = num.toString().slice(index);
        var a = "点";
        for(var i=1;i<str.length;i++){
          a += chnNumChar[parseInt(str[i])];
        }
        return a ;
      }else{
        return '';
      }
    }

    //定义在每个小节的内部进行转化的方法，其他部分则与小节内部转化方法相同
    var sectionToChinese = function(section){
      var str = '', chnstr = '',zero= false,count=0;   //zero为是否进行补零， 第一次进行取余由于为个位数，默认不补零
      while(section>0){
        var v = section % 10;  //对数字取余10，得到的数即为个位数
        if(v ==0){                    //如果数字为零，则对字符串进行补零
          if(zero){
            zero = false;        //如果遇到连续多次取余都是0，那么只需补一个零即可
            chnstr = chnNumChar[v] + chnstr;
          }
        }else{
          zero = true;           //第一次取余之后，如果再次取余为零，则需要补零
          str = chnNumChar[v];
          str += chnUnitChar[count];
          chnstr = str + chnstr;
        }
        count++;
        section = Math.floor(section/10);
      }
      return chnstr;
    }


    var a = numToChn(num);
    num = Math.floor(num);
    var unitPos = 0;
    var strIns = '', chnStr = '';
    var needZero = false;

    if(num === 0){
      return chnNumChar[0];
    }
    while(num > 0){
      var section = num % 10000;
      if(needZero){
        chnStr = chnNumChar[0] + chnStr;
      }
      strIns = sectionToChinese(section);
      strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
      chnStr = strIns + chnStr;
      needZero = (section < 1000) && (section > 0);
      num = Math.floor(num / 10000);
      unitPos++;
    }

    return chnStr+a;
  }
  return t;
})();
