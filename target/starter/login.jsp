<%@page pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<title>Login Page</title>
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body onload='document.f.j_username.focus();'>
	<c:if test="${not empty param.error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
		<div align="center">
        <table border="0" cellpadding="0" cellspacing="0" dir="rtl" style="width: 70%;">
            <tr>
                <td style="background: url(Content/Login/P_RightTop.jpg) no-repeat right bottom;
                    width: 20px; height: 13px">
                    &nbsp;
                </td>
                <td style="background: url(Content/Login/P_RepeatTop.jpg) repeat-x 50% bottom;
                    width: 97%; height: 13px">
                </td>
                <td style="background: url(Content/Login/P_LeftTop.jpg) no-repeat left bottom;
                    width: 24px; height: 13px">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td style="background: url(Content/Login/P_RepeatRight.jpg) repeat-y right 50%;
                    width: 20px; height: 37px">
                    &nbsp;
                </td>
                <td style="text-align: center; width: 100%">
                    <table style="font-size: 12pt; font-family: Times New Roman; width: 100%" dir="rtl">
                        <tr>
                            <td colspan="6" style="text-align: center;">
                                    <table width="344px" border="0" cellspacing="0" cellpadding="0" align="center">
                                        <tr>
                                            <td height="74" style="background: url(Content/Login/Up.gif) no-repeat center bottom;
                                                width: 363px; text-align: left;">
                                                <br />
                                                <br />
                                                <br />
                                                <br />
                                                <br />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="background: url(Content/Login/Middle.gif) repeat-y center; height: 73px;
                                                width: 363px; text-align: center">
                                                <br />
                                                <br />
                                                <table align="center">
                                                    <tr>
                                                        <td style="width: 78px; text-align: left">
                                                            <span style="font-size: 9pt; font-family: Tahoma">نــام کـاربـری :</span>
                                                        </td>
                                                        <td style="width: 167px; font-size: 12pt; font-family: Times New Roman;">
                                                            <input  type='text' name='j_username' value=''>
                                                        </td>
                                                        <td style="width: 18px; font-size: 12pt; font-family: Times New Roman;">
                                                            
                                                        </td>
                                                    </tr>
                                                    <tr style="font-size: 12pt; color: #000000; font-family: Times New Roman">
                                                        <td style="width: 78px; text-align: left">
                                                            <span style="font-size: 9pt; font-family: Tahoma">کـلـمـه عـبـور :</span>
                                                        </td>
                                                        <td style="width: 167px">
                                                           <input type='password' name='j_password'  />
                                                        </td>
                                                        <td style="width: 18px">
                                                            
                                                        </td>
                                                    </tr>
                                                    <tr style="font-size: 12pt; font-family: Times New Roman">
                                                        <td style="width: 78px">
                                                        </td>
                                                        <td style="width: 167px">
                                                        </td>
                                                        <td style="width: 18px">
                                                        </td>
                                                    </tr>
                                                    <tr style="font-size: 12pt; font-family: Times New Roman">
                                                        <td style="width: 78px">
                                                        </td>
                                                        <td style="width: 167px; text-align: center;">
                                                           <input name="submit" type="submit" value="ورود به سامانه" style="font-size: 9pt; font-family: tahoma" />
                                                        </td>
                                                        <td style="width: 18px">
                                                        </td>
                                                    </tr>
                                                    <tr style="font-size: 12pt; font-family: Times New Roman">
                                                        <td style="width: 78px">
                                                        </td>
                                                        <td style="width: 167px">
                                                        </td>
                                                        <td style="width: 18px">
                                                        </td>
                                                    </tr>
                                                    <tr style="font-size: 12pt; font-family: Times New Roman">
                                                        <td colspan="3" style="text-align: right">
                                                            
                                                            <br />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="background: url(Content/Login/Down.gif) no-repeat center top;
                                                width: 363px; height: 23px;">
                                            </td>
                                        </tr>
                                    </table>
                            </td>
                        </tr>
                        <tr style="font-size: 12pt; font-family: Times New Roman">
                            <td style="width: 33px">
                            </td>
                            <td style="width: 68px">
                                <img src="Content/Login/Monitor.gif" />
                            </td>
                            <td style="width: 473px; text-align: right">
                                <br />
                                <span style="font-size: 8pt; color: #990033; font-family: Tahoma">
                                    <br />
                                    <br />
                                    <span style="color: #0000cc">حداقل وضوح تصوير</span> <span style="font-size: 12pt"><span
                                        style="font-family: Times New Roman"><span style="font-size: 8pt; color: #0000ff;
                                            font-family: Tahoma">Screen </span><span style="color: #000000"><span style="font-size: 8pt;
                                                color: #ff0000; font-family: Tahoma"><span style="color: #0000ff">resolution</span><strong>
                                                    1024 * 768</strong></span> </span></span></span></span>
                            </td>
                            <td style="font-size: 12pt; width: 291px; font-family: Times New Roman">
                            </td>
                            <td style="font-size: 12pt; font-family: Times New Roman">
                                <br />
                            </td>
                            <td style="font-size: 12pt; width: 342px; font-family: Times New Roman; text-align: left;">
                                <span style="font-size: 8pt; color: #990033; font-family: Tahoma"><span style="color: #0000cc">
                                </span><span style="font-size: 12pt"><span style="font-family: Times New Roman"><span
                                    style="color: #000000"><span style="font-size: 12pt"><span style="font-family: Times New Roman">
                                        <span style="color: #000000"></span></span></span></span></span></span></span>
                            </td>
                        </tr>
                        <tr style="font-size: 12pt; font-family: Times New Roman">
                            <td style="width: 33px;">
                            </td>
                            <td style="width: 68px;">
                                <img src="Content/Login/firefox.jpg" width="46" height="51" />
                            </td>
                            <td style="text-align: right" colspan="2">
                                <br />
                                <span style="font-size: 8pt; color: #cc0066; font-family: Tahoma"><span style="color: #3300ff">
                                    مرورگرنسخه </span>&nbsp;<strong><span style="color: #ff3300">Firefox 3.5 </span>
                                    </strong><span style="color: #3300ff">به بالا به طور کامل این نرم افزار را پشتیبانی
                                        می کند</span></span>
                            </td>
                            <td>
                            </td>
                            <td style="width: 342px; text-align: left;">
                                <span style="font-size: 8pt; font-family: Tahoma">
                                    <table border="0" cellpadding="0" cellspacing="0" dir="rtl" style="width: 180px;
                                        height: 83px">
                                        <tr>
                                            <td style="background: url(Content/Login/P_RightTop.jpg) no-repeat right bottom;
                                                width: 20px; height: 13px">
                                                &nbsp;
                                            </td>
                                            <td style="background: url(Content/Login/P_RepeatTop.jpg) repeat-x 50% bottom;
                                                width: 432px; height: 13px">
                                            </td>
                                            <td style="background: url(Content/Login/P_LeftTop.jpg) no-repeat left bottom;
                                                width: 24px; height: 13px">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="background: url(Content/Login/P_RepeatRight.jpg) repeat-y right 50%;
                                                width: 20px; height: 37px">
                                                &nbsp;
                                            </td>
                                            <td style="width: 432px; height: 37px">
                                                <p>
                                                    <table style="font-size: 8pt; font-family: Tahoma">
                                                        <tr>
                                                            <td style="width: 97px; height: 15px">
                                                                شماره نسخه :<span style="color: #990033">&nbsp;</span>
                                                            </td>
                                                            <td style="width: 50px; height: 15px; text-align: right">
                                                                <span style="color: #990033">0.2</span>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td style="width: 97px">
                                                                &nbsp;آخرین بروزرسانی :&nbsp;
                                                            </td>
                                                            <td style="width: 50px; text-align: right">
                                                                <span style="color: #990033">91/02/20</span>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </p>
                                            </td>
                                            <td style="background: url(Content/Login/P_RepeatLeft.jpg) repeat-y left 50%;
                                                width: 24px; height: 37px">
                                                &nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td style="background: url(Content/Login/P_RightBtm.jpg) no-repeat right top;
                                                width: 20px">
                                                &nbsp;
                                            </td>
                                            <td style="background: url(Content/Login/P_RepeatBtm.jpg) repeat-x; width: 432px">
                                                &nbsp;
                                            </td>
                                            <td style="background: url(Content/Login/P_LeftBtm.jpg) no-repeat left top;
                                                width: 24px">
                                                &nbsp;
                                            </td>
                                        </tr>
                                    </table>
                                </span>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="background: url(Content/Login/P_RepeatLeft.jpg) repeat-y left 50%;
                    width: 24px; height: 37px">
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td style="background: url(Content/Login/P_RightBtm.jpg) no-repeat right top;
                    width: 20px">
                    &nbsp;
                </td>
                <td style="background: url(Content/Login/P_RepeatBtm.jpg) repeat-x; width: 97%;
                    height: 37px">
                    &nbsp;
                </td>
                <td style="background: url(Content/Login/P_LeftBtm.jpg) no-repeat left top;
                    width: 24px">
                    &nbsp;
                </td>
            </tr>
        </table>
    </div>
    
		
		
		
	</form>
</body>
</html>