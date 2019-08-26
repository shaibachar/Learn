from sklearn import datasets

def getData():
    iris = datasets.load_iris()
    return datasets.load_digits().data

def sayHello():
    return "Hello!  "

def useSayHello():
    return sayHello()

print(getData())