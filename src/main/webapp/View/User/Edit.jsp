<%@ include file="../AutoCompleteInclude.jsp" %>
<script language="javascript" type="text/javascript">
        $(function () {         
       	   $('#TxtUserName').bind('blur', UserExistance);
            $("#txtAutoComplete").autocomplete({  
                source: function(req, resp){  
                	jsonReq={ search : req.term,resultCount:20};
                    $.getJSON("<c:url value = '/rest/personel/autoComplete' />", jsonReq, function(data) {  
                    	resp( $.map( data, function( item ) {
							return {
								label: item.personCode+'  /  '+item.firstName+'  /  '+item.lastName,
								value: item.id,
								fName:item.firstName,
								lName:item.lastName,
								pCode:item.personCode,					 
							}
						}));
                	});  
           		},  
        	    select: function(event, ui) { 
        	    	$('#txtFirstName').val(ui.item.fName);
        	 		$('#txtLastName').val(ui.item.lName);
        	 		$('#personelId').val(ui.item.value);
        	    	$(event.target).val(ui.item.pCode);
        	    	$('#txtUserName').focus();
                    return false;
                },  
                minLength: 2  
            }); 
            $('.ui-autocomplete').css('font-family','tahoma');                
            $('.ui-autocomplete').css('direction','rtl');
            $('.ui-autocomplete').css('font-size','9px');
            $('.ui-autocomplete').css('width','200px'); 
        });

    
    function UserExistance() {
        if (this.value.length > 0 && $('#id').val() <= 0) {
            Loader(true);
            $.getJSON("checkUserName/"+this.value, function (data) {
                if (data) {
                    $('#lblErr').html("<font color=red> شناسه اي که شما انتخاب کرديد موجود است آن را عوض کنيد</font>");
                    $('#BtnWriteEntity').attr('disabled', 'disabled');
                }
                else {
                    $('#lblErr').html("<font color=green>شناسه ي شما در سايت موجود نيست ادامه دهيد</font>");
                    $('#BtnWriteEntity').attr('disabled', '');
                }
                Loader(false);
            });
        }
    }
</script>
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <input type="hidden" id="personelId" value="-1" />
    <br>
    <br>
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                    <div id="main_infoDiv">
                        <table cellpadding="2px">
                             <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma"> : کد پرسنل یا نام پرسنل &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="autoComplete" id="txtAutoComplete" type="text" class="text validate[required]" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">نام &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="firstName" id="txtFirstName" type="text" class="text validate[required]" size="40"  disabled="disabled"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">نام خانوادگی &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="lastName" id="txtLastName" type="text" class="text validate[required]" size="40" disabled="disabled"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">نام کاربری &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                     <input name="userName" id="txtUserName" type="text" class="text validate[required]" style="direction:ltr" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">رمز عبوز &nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="passWord" id="txtPassWord" type="text" class=" text validate[required,length[5,20]]"  style="direction:ltr" size="40" />
                                </td>
                            </tr>
                            <tr>
                                <td class="fieldTitle" align="left">
                                    <span style="font-size: 8pt; font-family: Tahoma">پست الکترونیک&nbsp;:</span>
                                </td>
                                <td class="fieldValue" align="right">
                                    <input name="email" id="txtEmail" type="text" class="text validate[required]" style="direction:ltr" size="40" />
                                </td>
                            </tr>
                        </table>
                    </div>
                <input type="button" id="BtnWriteEntity" value="ثبت" onclick="writeEntity()" class="actionBtn" />
                <input type="button" value="بازخوانی" onclick="refreshForm()" class="actionBtn" />
                <input type="button" value="جدید" onclick="clearEntity()" class="actionBtn" />
                <input type="button" value="بازگشت" onclick="showListPage()" class="actionBtn" />
            </td>
        </tr>
    </table>
</div>
