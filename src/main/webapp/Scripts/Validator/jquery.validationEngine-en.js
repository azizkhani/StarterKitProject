

(function ($) {
    $.fn.validationEngineLanguage = function () { };
    $.validationEngineLanguage = {
        newLang: function () {
            $.validationEngineLanguage.allRules = { "required": {    			// Add your regex rules here, you can take telephone as an example
                "regex": "none",
                "alertText": "این فیلد الزامی است",
                "alertTextCheckboxMultiple": "یکی از موارد را انتخاب کنید",
                "alertTextCheckboxe": "لطفا این مورد را انتخاب کنید"
            },
                "length": {
                    "regex": "none",
                    "alertText": "بین ",
                    "alertText2": " تا ",
                    "alertText3": " حرف مجاز است "
                },
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* Checks allowed Exceeded"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* Please select ",
                    "alertText2": " options"
                },
                "confirm": {
                    "regex": "none",
                    "alertText": "تکرار رمز صحیح نیست"
                },
                "telephone": {
                    "regex": "/^[+][0-9\-\(\)\ ]+$/",
                    "alertText": "شماره تلفن اشتباه است"
                },
                "email": {
                    "regex": "/^[a-zA-Z0-9_\.\-]+\@([a-zA-Z0-9\-]+\.)+[a-zA-Z0-9]{2,4}$/",
                    "alertText": "آدرس ایمیل معتبر نمی باشد"
                },
                
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "لطفا عدد وارد کنید"
                },
                
                
                "date": {
                    "regex": "/^[0-9]{4}\-\[0-9]{1,2}\-\[0-9]{1,2}$/",
                    "alertText": "* Invalid date, must be in YYYY-MM-DD format"
                },
                "onlyNumber": {
                    "regex": "/^[0-9\ ]+$/",
                    "alertText": "لطفا عدد وارد کنید"
                },
                "noSpecialCaracters": {
                    "regex": "/^[0-9a-zA-Z]+$/",
                    "alertText": "* No special caracters allowed"
                },
                "ajaxUser": {
                    "file": "validateUser.php",
                    "extraData": "name=eric",
                    "alertTextOk": "* This user is available",
                    "alertTextLoad": "* Loading, please wait",
                    "alertText": "* This user is already taken"
                },
                "ajaxName": {
                    "file": "validateUser.php",
                    "alertText": "* This name is already taken",
                    "alertTextOk": "* This name is available",
                    "alertTextLoad": "* Loading, please wait"
                },
                "onlyLetter": {
                    "regex": "/^[a-zA-Z\ \']+$/",
                    "alertText": "تنها ورود حروف مجاز است"
                },
                "validate2fields": {
                    "nname": "validate2fields",
                    "alertText": "* You must have a firstname and a lastname"
                },
                "validateMelliCode": {
                    "file": "",
                    "alertText": "کد ملی وارد شده معتبر نیست",
                    "alertTextLoad": "در حال بررسی..."
                },
                "checkUserExist": {
                    "file": "",
                    "alertText": "این نام کاربری تکراری است",
                    "alertTextLoad": "بررسی نام کاربری..."
                }
            }

        }
    }
})(jQuery);

$(document).ready(function() {	
	$.validationEngineLanguage.newLang()
});