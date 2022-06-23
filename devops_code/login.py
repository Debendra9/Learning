class login:
    def __init__(self,id,pas):
        self.id="admin"
        self.pas="admin"

    def check(self,id,pas):
        print(self.id)
        print(log.id)
        if(self.id==log.id and self.pas==log.pas):
            print("Login success!")

log=login("","")
log.check(input("Enter Login ID:"),
        input("Enter password: "))

print ("Login Page") 