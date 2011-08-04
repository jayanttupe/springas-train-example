<?xml version="1.0" encoding="utf-8"?>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
 <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<style>
body { margin: 0; padding: 0; font-family: verdana, tahoma, sans-serif; font-size: 8pt; text-align: left; background: #FFFFFF; color: #989898; }
h1 { color: #FF6810; font-size: 17.5pt; font-weight: bold; padding: 0; margin: 15px 0 20px 0; }
h2 { font-size: 10.5pt; font-weight: bold; margin-top: 15px; }
table { font-size: 8pt; border-collapse: collapse; border-spacing: 0; }
table th { background: #B8B8F0; border: 3px solid #FFF; color: #FFF; font-weight: normal; text-align: center; padding: 3px; }
table td.warn { background: #E8E4F8; border: 3px solid #FFF; color: #A880A8; text-align: left; padding: 3px; }
table td { background: #E8E4F8; border: 3px solid #FFF; color: #7880A8; text-align: left; padding: 3px; }
p { margin-left: 20px; margin-top: 5px; text-align: justify; }
</style>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8"></head>

<body>
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

<p>$taskDist$</p>

 <div style="font-size: 7pt;  margin: 20px 0 0 0;">
 本通知邮件由$admin$自动发送 ,
 <br><a href="$taskurl$">点击查看相关任务信息</a><br>
 $time;format="fulltime"$
 </div>
</div>
</body></html>

