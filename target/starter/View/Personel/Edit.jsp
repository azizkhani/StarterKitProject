
<div class="edit_form" id="edit_form" style="width: 100%" align="center">
    <input type="hidden" id="id" value="-1" />
    <br>
    <br>
    <table width="96%">
        <tr>
            <td align="center" width="100%" class="plain">
                <div id="tabContents">
                    <div id="main_infoDiv">
                     	<table cellpadding="2px" width="100%">
                         	<tr>
                                <td style="vertical-align: top;" width="50%">
				                      <table cellpadding="2px">
				                         	<tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Personel.PersonCode" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="personCode" id="txtPersonCode" type="text" class="text  validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Personel.FirstName" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="firstName" id="txtFirstName" type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:red"><spring:message code="UI.Personel.LastName" /></span> &nbsp;:</span>
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="lastName" id="txtLastName" type="text" class="text validate[required]" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Personel.FatherName" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                     <input name="fatherName" id="txtFatherName" type="text" class="text Property"  size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Personel.NationalityNumber" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="nationalityNumber" id="txtNationalityNumber" type="text" class="text validate[required,custom[integer]]" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Personel.IdNumber" /></span> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="idNumber" id="txtIdNumber" type="text" class="text validate[required,custom[integer]]" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>  
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Personel.BirthDate" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="birthDate" id="txtBirthDate" type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>           
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.Gender" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                               		<div style="width: 100%">
					                                    <label for="radioGenderMale"> 	&nbsp;&nbsp;<spring:message code="UI.Personel.Gender.Male" /> 	</label><input name="gender" id="radioGenderMale" 	type="radio"   />
					                                    &nbsp;<label for="radioGenderFemale"> <spring:message code="UI.Personel.Gender.Female" /> </label><input name="gender" id="radioGenderFemale" type="radio"   />
				                                    </div>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.MaritalStatus" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                 <div style="width: 100%">
					                                 <label for="radioMaritalStatusSingle"> <spring:message code="UI.Personel.MaritalStatus.Single" /> </label><input  name="maritalStatus" id="radioMaritalStatusSingle"   type="radio"   />
					                                 <label for="radioMaritalStatusMarried"> <spring:message code="UI.Personel.MaritalStatus.Married" /></label><input  name="maritalStatus" id="radioMaritalStatusMarried" type="radio"  />
				                                 </div>
				                                </td>
				                            </tr>
				                            
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Personel.PhoneNumber" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="phoneNumber" id="txtPhoneNumber" type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.Mobile" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="mobile" id="txtMobile" type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>
				                         </table>
                                </td>
                                <td  style="vertical-align: top;" width="50%">
				                     <table>
				                       		<tr>
				                                <td class="fieldTitle" align="left">
				                                    <spring:message code="UI.Personel.LicenseNumber" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="licenseNumber" id="txtLicenseNumber" type="text" class="text Property" style="direction:ltr" size="40" />
				                                </td>
				                            </tr>
				                             <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:black"><spring:message code="UI.Personel.LicenseType" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbLicenseType">
				                                    </select>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:black;"><spring:message code="UI.Personel.Bank" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbBank" >
				                                    </select>
				                                </td>
				                            </tr>
											 <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.AccountNumber" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <input name="accountNumber" id="txtAccountNumber" style="direction:ltr" type="text" class="text Property"  size="40" />
				                                </td>
				                            </tr>               
				                            
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <span style="color:black"><spring:message code="UI.Personel.EducationLevel" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                    <select  id="cmbEducationLevel">
				                                    </select>
				                                </td>
				                            </tr>
				                           
				                           <tr>
				                                <td class="fieldTitle" align="left">
				                                    <span style="color:red"><spring:message code="UI.Personel.Location" /></span>&nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                	<input  id="txtLocationCode" type="text" class="text validate[required]"   style="width: 40px" />
				                                	<input id="btnSelectLocation" type="button" value="...">
				                                	<span id="lblLocationTitle" style="color:blue"></span>
				                                	<input id="hfieldLocationId" type="hidden" class="validate[required]" />
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.Actors" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">  	
				                                	<div id="divPersonelActor" style="direction: rtl;height: 80px;overflow-y: scroll;">						
													</div>			                                    
													<script id="checkBoxTemplatePersonelActor" type="text/html">
										    			<input type="checkbox" id=actorItem${id}  value=${id} class="PersonelActorCheckBoxItem" /> 
														<label for="actorItem${id}"><span >${topic}</span></label>  &nbsp; 	<br/>	
													</script>
				                                </td>
				                            </tr>
				                             <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.Address" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">  
				                                    <textarea name="address" id="txtAddress"  class="text Property" rows="2" style="width: 200px" ></textarea>
				                                </td>
				                            </tr>
				                           
				                            <tr>
				                                <td class="fieldTitle" align="left">
				                                   <spring:message code="UI.Personel.Description" /> &nbsp;:
				                                </td>
				                                <td class="fieldValue" align="right">
				                                	<textarea name="description" id="txtDescription"  class="text Property" rows="2" style="width: 200px" ></textarea>                    
				                                </td>
				                            </tr>
				                       </table>
				                 </td>
				            </tr>
                         </table>

                    </div>
                </div>
                <input type="button"  value='<spring:message code="UI.General.Save"  />'    onclick="saveEntity()"   class="actionBtn" id="btnSaveEntity"/>
             	<input type="button"  value='<spring:message code="UI.General.Refresh"  />' onclick="refreshForm()"  class="actionBtn" />
              	<input type="button"  value='<spring:message code="UI.General.New"  />'     onclick="clearEntity()"  class="actionBtn" />
             	<input type="button"  value='<spring:message code="UI.General.Back"  />'    onclick="showListPage()" class="actionBtn" />
            </td>
        </tr>
    </table>
</div>
