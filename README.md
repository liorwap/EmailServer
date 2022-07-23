# Bridge Email Server
EmailServer is a JAVA Spring Application solution for email vendors authentication delegation for internal organization 
clients.
# How it works

Exposes REST API POST method that accepts JSON of the form:
```
    {
        "to":"lior@gmail.com",
        "from":"lior2@gmail.com",
        "body":"silly text"
    }
```
Sends a simple email with the parameters given using the vendor specified in the from postfix field.

Supported vendors:
* Gmail
* Walla
* Yahoo

# System Design 
Note: The system is not at mature level and has scalability flaws and data loss risks.
![System design](https://github.com/liorwap/EmailServer/blob/master/emailServer.png?raw=true)
