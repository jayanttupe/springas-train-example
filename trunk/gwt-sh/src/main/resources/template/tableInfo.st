<div style=" width: 500; margin-left: 40px;">
 <h1>任务通知</h1>
 <table>
  <tr><th colspan="2">任务概况</th></tr>
  <tr><td><b>用户：</b></td><td>$userInfo.name$</td></tr> 
  <tr><td><b>编号：</b></td><td>$userInfo.userId$</td></tr>
  <tr><td><b>任务：</b></td><td>$taskName$</td></tr>
  <tr><td><b>完成比例：</b></td>
  			<td class="$if(isMore)$ warn $else$ n/a $endif$" >
  					$completePre$</td></tr>
  <tr><td><b>耗时：</b></td><td>$costTime$</td></tr>
 </table>
 <table>
  <tr><th colspan="2">任务清单</th></tr>
  
  $[stepList]:{step|<tr><td><b>$step.step$:</b></td><td>$step.status$</td></tr>}$
  </table> 
<h2>任务摘要：</h2>