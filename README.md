# Bridge Email Server
EmailServer is a JAVA Spring Application solution for email vendors authentication delegation for internal organization 
clients.

#Usage
```
export CR_PAT=<MY ghcr.io TOKEN>
echo $CR_PAT | docker login ghcr.io -u <MY gchr.io USENAME> --password-stdin
docker pull ghcr.io/<MY gchr.io USENAME>/emailserver:master
docker run --user app -p 8080:8080 <IMAGE ID>
```

# How It Works
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

# Adding New Vendor Guide
Steps:
1. Add to email.provider.properties file the relevant configuration for the email provider.
2. Under config directory add [New Provider Name]EmailProviderConfig Class.
3. Under handler directory add [New Provider Name]Handler Class.
4. Under handler directory add the new Handler to EmailHandlerContainer class.



# System Design 
Note: The system is not at mature level and has scalability flaws and data loss risks.
![System design](https://github.com/liorwap/EmailServer/blob/master/emailServer.png?raw=true)
