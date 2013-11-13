StudentMeetup
=============

### Compiling And Running
1. Configure your build path in MeetupBeans and MeetupUI.
  1. EAR Libraries
  2. JBoss 7.1 Runtime
  3. JRE System Library

2. You may need to mark a "src" folder as a source folder in Eclipse in the MeetupBeans project. Otherwise, when you export the jar, the required source files will not come with it.
3. Configure standalone.xml in the JBoss folder to use the following connection string for the H2 database: ```jdbc:h2:tcp://localhost/~/studentmeetup```
4. Run the server using a WAR from MeetupUI.

### Opening Ports on our Ubuntu Server
http://askubuntu.com/questions/119393/how-to-save-rules-of-the-iptables

Scripts were added to automatically save changes to iptables on shutdown, and automatically reload those changes on startup. The files that do this can be found at /etc/network/if-pre-up.d/iptablesload and /etc/network/if-pre-down.d/iptablessave.

You can open a port using:
```
sudo iptables -A INPUT -p tcp --dport 80 -j ACCEPT
```
