$(document).ready( function() {
	
	//下拉框验证
	jQuery.validator.addMethod("None", function(value, element) {   
		return value == "";
	}, "必须选择一项");

	//身份证号码验证
	jQuery.validator.addMethod("idcardno", function(value, element) {
		return this.optional(element) || isIdCardNo(value);
	}, "请正确输入身份证号码");
	
	//增加身份证验证
	function isIdCardNo(num) {
	    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	    var varArray = new Array();
	    var intValue;
	    var lngProduct = 0;
	    var intCheckDigit;
	    var intStrLen = num.length;
	    var idNumber = num;
	    // initialize
	    if ((intStrLen != 15) && (intStrLen != 18)) {
	        return false;
	    }
	    // check and set value
	    for (i = 0; i < intStrLen; i++) {
	        varArray[i] = idNumber.charAt(i);
	        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
	            return false;
	        } else if (i < 17) {
	            varArray[i] = varArray[i] * factorArr[i];
	        }
	    }
	    if (intStrLen == 18) {
	        //check date
	        var date8 = idNumber.substring(6, 14);
	        if (isDate8(date8) == false) {
	            return false;
	        }
	        // calculate the sum of the products
	        for (i = 0; i < 17; i++) {
	            lngProduct = lngProduct + varArray[i];
	        }
	        // calculate the check digit
	        intCheckDigit = parityBit[lngProduct % 11];
	        // check last digit
	        if (varArray[17] != intCheckDigit) {
	            return false;
	        }
	    }
	    else {        //length is 15
	        //check date
	        var date6 = idNumber.substring(6, 12);
	        if (isDate6(date6) == false) {
	            return false;
	        }
	    }
	    return true;
	}
	function isDate6(sDate) {
	    if (!/^[0-9]{6}$/.test(sDate)) {
	        return false;
	    }
	    var year, month, day;
	    year = sDate.substring(0, 4);
	    month = sDate.substring(4, 6);
	    if (year < 1700 || year > 2500) return false
	    if (month < 1 || month > 12) return false
	    return true
	}

	function isDate8(sDate) {
	    if (!/^[0-9]{8}$/.test(sDate)) {
	        return false;
	    }
	    var year, month, day;
	    year = sDate.substring(0, 4);
	    month = sDate.substring(4, 6);
	    day = sDate.substring(6, 8);
	    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
	    if (year < 1700 || year > 2500) return false
	    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
	    if (month < 1 || month > 12) return false
	    if (day < 1 || day > iaMonthDays[month - 1]) return false
	    return true
	}

	
	//英文字母数字
	jQuery.validator.addMethod("alnum", function(value, element) {
		return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
	}, "只能包括英文字母和数字");
	
	//英文字母
	jQuery.validator.addMethod("alnum", function(value, element) {
		return this.optional(element) || /^[a-zA-Z]+$/.test(value);
	}, "只能包括英文字母");
	
	//邮政编码验证
	jQuery.validator.addMethod("zipcode", function(value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "请正确填写邮政编码");
	
	//字符最小长度验证(一个中文字符长度为2)
	jQuery.validator.addMethod("stringMinLength", function(value, element, param) {
		var length = value.length;
		for ( var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			};
		}
		return this.optional(element) || (length >= param);
	}, $.validator.format("长度不能小于{0}!"));
	
	// 字符最大长度验证(一个中文字符长度为2)
	jQuery.validator.addMethod("stringMaxLength", function(value, element, param) {
		var length = value.length;
		for ( var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			};
		}
		return this.optional(element) || (length <= param);
	}, $.validator.format("长度不能大于{0}!"));
	
	// 字符验证
	jQuery.validator.addMethod("string", function(value, element) {
		return this.optional(element) || /^[\Α-\￥\w]+$/.test(value);
	}, "不允许包含特殊符号!");
	
	// 手机号码验证
	jQuery.validator.addMethod("mobile", function(value, element) {
		var length = value.length;
		return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(value));
	}, "手机号码格式错误!");
	
	// 电话号码验证
	jQuery.validator.addMethod("phone", function(value, element) {
		var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
		return this.optional(element) || (tel.test(value));
	}, "电话号码格式错误!");
	
	// 必须以特定字符串开头验证
	jQuery.validator.addMethod("begin", function(value, element, param) {
		var begin = new RegExp("^" + param);
		return this.optional(element) || (begin.test(value));
	}, $.validator.format("必须以 {0} 开头!"));

	// 验证两次输入值是否不相同
	jQuery.validator.addMethod("notEqualTo", function(value, element, param) {
		return value != $(param).val();
	}, $.validator.format("两次输入不能相同!"));
	
	//验证两次当前当前文本框输入的值不得小于对比的文本框的值
	jQuery.validator.addMethod("compareVal", function(value, element, param) {
		var max = parseFloat(value);
		var min = parseFloat($(param).val());
		return max >= min;
	}, $.validator.format("XX的值不得小于XX的值"));
	
	// 验证值不允许与特定值等于
	jQuery.validator.addMethod("notEqual", function(value, element, param) {
		return value != param;
	}, $.validator.format("输入值不允许为{0}!"));
	
	// 验证值必须大于特定值(不能等于)
	jQuery.validator.addMethod("gt", function(value, element, param) {
		return value > param;
	}, $.validator.format("输入值必须大于{0}!"));
	
	// 验证值小数位数不能超过两位
	jQuery.validator.addMethod("decimal", function(value, element) {
		var decimal = /^-?\d+(\.\d{1,2})?$/;
		return this.optional(element) || (decimal.test(value));
	});
	
    //QQ号码验证
	jQuery.validator.addMethod("qq", function(value, element) { 
		var tel = /^[1-9]\d{4,9}$/; 
		return this.optional(element) || (tel.test(value)); 
	}, "qq号码格式错误");
    
	//ip地址验证
	jQuery.validator.addMethod("ip", function(value, element) { 
		var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/; 
		return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)); 
	}, "Ip地址格式错误");

	//验证radio以及checkbox
	jQuery.extend(jQuery.validator.defaults, {
		errorPlacement:function(error, element) {   
			if ( element.is(":radio") ) {
			  error.appendTo( element.parent().next().next() );
			}else if ( element.is(":checkbox") ){
			  error.appendTo ( element.next() ); 
			} else{ error.appendTo( element.parent().next() );} 
		}
	});
	
	//验证截止时间大于起始时间
	jQuery.validator.addMethod("compareDate", function(value, element, param) {
		var max = value;
		var min = $(param).val();
		return max >= min;
	}, $.validator.format("不得小于起始时间"));
	
	//验证正整数的方法 
	jQuery.validator.addMethod("positiveInteger", function(value, element) {
		var positiveVal = /^[0-9]*[1-9][0-9]*$/;    
		return this.optional(element) || (positiveVal.test(value));   
	}, "请输入正整数");
	
	//验证汉字
	jQuery.validator.addMethod("chinese", function(value, element) {  
		var tel = /^[\u4e00-\u9fa5]+$/;  
		return this.optional(element) || (tel.test(value));  
	}, "请输入汉字");

	//验证正浮点数（正数和小数）
	jQuery.validator.addMethod("positiveFloat", function(value, element) {  
		var positiveVal = /^[0-9].*$/;    
		return this.optional(element) || (positiveVal.test(value));  
	}, "请输入正数");
});