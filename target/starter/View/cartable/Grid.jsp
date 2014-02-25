<div style="width: 100%">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" width="100%">
				<table class="grid" width="100%" align="center">
					<thead>
						<tr class="grid_header">
							<th nowrap="nowrap">
								<spring:message code="UI.General.Select" />
							</th>
							<th nowrap="nowrap">name</th>
							<th nowrap="nowrap">key</th>
							<th nowrap="nowrap">description</th>
							<th nowrap="nowrap">version</th>
<!-- 							<th nowrap="nowrap">resourceName</th> -->
<!-- 							<th nowrap="nowrap">diagramResourceName</th> -->
							<th nowrap="nowrap">startFormKey</th>
							<th nowrap="nowrap">actions</th>
						</tr>
					</thead>
					<tbody id="entityBody">
						<script id="GridRowTemplate" type="text/html">
							    <tr id="pattern${id}"  class="oddRow"
							            onmouseover="highLight(this.id)" 
							            onmouseout="normLight(this.id)" 
							            >
							        <td align="center" width="30px">
							            <span><input type="radio" name="selectedItem" id="selectedItemId${id}" /></span>
							        </td>
									<td nowrap="nowrap">${name}</td>
									<td nowrap="nowrap">${key}</td>
									<td nowrap="nowrap">${description}</td>
									<td nowrap="nowrap">${version}</td>
									<td nowrap="nowrap">${startFormKey}</td>
									<td nowrap="nowrap">
										<span style="cursor:hand" onclick="startProcess(${id})">startProcess</span>
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
