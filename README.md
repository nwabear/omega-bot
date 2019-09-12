# omega-bot

Commands:
 - ;help: displays help menu
 - ;kick <user(s)>: kicks specified users
 - ;ban <user(s)>: bans specified users
 - ;remind <hours> <minutes> <message>: tags the user after the specified time, along with displaying the message
 - ;solve <expression>: solves math expression using google (temporarily broken)
 - ;wikipedia <query>: finds a wikipedia page about the topic stated
 - ;gimage <query>: sends a random google image based on the query
 - ;translate <input>: uses google translate to translate text to english
 - ;avatar <user>: prints the avatar picture of the specified user
 - ;roll <bound>: prints a number between 1 and the bound, bound is 100 if left blank
 - ;reverse <text>: reverses the inputted text
 - ;reverseName <user(s)>: reverses the name of the specified users, and sets it to their nickname
 - ;leet <input>: outputs the text in leetspeak
 - ;echo <text>: repeats the text inputted
 
Usage:

To run this discord bot, you need to clone this repository into an IDE, then change the token in the BotApp class from 
```java
new Token().getToken();
```
to the bot token that you get in the discord developer portal.

After setting the token, compile and run the project to start the bot, and the bot will appear as online in discord, signifying that it is now ready to run commands.
