1)mvn clean compile
2)run *.swf, login, see data list
3)edit applicationContext.xml
chage  <object id="userDelegate" class="business.UserDelegate"/> to <!--<object id="userDelegate" class="business.UserDelegateMock"/>-->
4)run *.swf, login, see another data list