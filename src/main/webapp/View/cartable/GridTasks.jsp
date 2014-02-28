<div style="width: 100%">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" width="100%">
				<table class="gridtasks" width="100%" align="center">
					<thead>
						<tr class="grid_header">
							<th nowrap="nowrap">select</th>
							<th nowrap="nowrap">id</th>
							<th nowrap="nowrap">name</th>
							<th nowrap="nowrap">taskDefinitionKey</th>
							<th nowrap="nowrap">description</th>
							<th nowrap="nowrap">processInstanceId</th>
							<th nowrap="nowrap">createTime</th>
							<th nowrap="nowrap">formKey</th>
							<th nowrap="nowrap">actions</th>
						</tr>
					</thead>
					<tbody id="entityBodyTask">
						<script id="GridRowTemplateTask" type="text/html">
							    <tr id="pattern${id}"  class="oddRow"
							            onmouseover="highLight(this.id)" 
							            onmouseout="normLight(this.id)" 
							            >
							        <td align="center" width="30px">
							            <span><input type="radio" name="selectedItem" id="selectedItemId${id}" /></span>
							        </td>
									<td nowrap="nowrap">${id}</td>
									<td nowrap="nowrap">${name}</td>
									<td nowrap="nowrap">${taskDefinitionKey}</td>
									<td nowrap="nowrap">${description.substring(1,50)}</td>
									<td nowrap="nowrap">${processInstanceId}</td>
									<td nowrap="nowrap">${createTime}</td>
									<td nowrap="nowrap">${formKey}</td>
									<td nowrap="nowrap">
										<span style="cursor:pointer;color:blue" onclick="claimTask('${id}')">claim</span>
										<span style="cursor:pointer;color:red" onclick="completeTask('${id}')">complete</span>
										<span style="cursor:pointer;color:green" onclick="startTask('${id}','${formKey}','${name}')">start</span>
									</td>

							    </tr>
							</script>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<!--navigation:begin-->
			<td align="left" style="width: 100%; height: 25px; background: #B9CCEB;">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<table>
								<tr>
									<td nowrap="nowrap">
										<input type="text" name="pageSize" onblur="pageSize = $('#pageSize').val();init();" id="pageSize" value="10" size="1" />
									</td>
									<td>
										<a href="#" onclick="pageSize = $('#pageSize').val();init();returnValue=false;">
											<img src="../../Content/images/refresh.gif" />
										</a>
									</td>
								</tr>
							</table>
						</td>
						<td style="padding-right: 50px;">
							<spring:message code="UI.General.ResultNum" />
							<span id="resultNum" style="font-weight: bold; color: #f00;"> </span>
						</td>
						<td style="padding-left: 5px; padding-right: 50px;">
							<table>
								<tr>
									<td>
										<spring:message code="UI.General.Page" />
									</td>
									<td>
										<a title="" id="nextIcon" class="noborder" href="#" onclick="nextPage()" align="left">
											<img src="../../Content/images/next.gif" />
										</a>
									</td>
									<td id="navigateNums" nowrap="nowrap" align="left" dir="ltr"></td>
									<td>
										<a title="" id="prevIcon" class="noborder" href="#" onclick="prevPage()">
											<img src="../../Content/images/prev.gif" />
										</a>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			<!--navigation:end-->
		</tr>
	</table>
</div>
