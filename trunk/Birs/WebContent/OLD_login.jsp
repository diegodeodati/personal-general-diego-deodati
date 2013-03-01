<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Betplus Business Intelligence's Reports and Statistics
	System</title>
</head>
<body >
	<div align=center 
		style="position: relative; width: 800px; margin-left: auto; margin-right: auto; background-color: #F2F6FC; border-radius: 20px; -webkit-border-radius: 20px; -moz-border-radius: 20px;">
		
		<form id="login" action="j_security_check" method="post" name="f">
			
			<table align=center height=100%>
				<tr>
					<td valign=middle>
						<table>
							
							<tr>
								<td><label for="firstname" accesskey="f">Username:
								</label></td>
								<td><input type="text" id="j_username" name="j_username"
									tabindex="1" value="" title="Username" width="20" /></td>
								<td><br />
								</td>
							</tr>
							<tr>
								<td><label for="lastname" accesskey="l">Password: </label>
								</td>
								<td><input type="password" id="j_password"
									name="j_password" tabindex="2" title="Password" value=""
									width="20" /></td>
								<td><br />
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center"><label for="kludge"></label>
								<br/>
									<input type="submit" value="Accedi" id="submit" tabindex="5"
									style="width: 100%; height: 40px;"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>