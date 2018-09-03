import os

class PlayWithClass(object):
    
    def __init__(self):
        pass

    def say(self):
        return "hello"

    def sayHello(self,name):
        print(self.say(),name)

p = PlayWithClass()
p.sayHello("shai")