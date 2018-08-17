#!/usr/bin/env python3

def operations(a):
    a += 10
    print("a+=10 == {0}".format(a))
    a /= 3
    print("a/=3 == {0}".format(a))
    a += (a*40)
    print("a+=(a*40) == {0}".format(a))
    a = 9
    b = 12
    c = 3
    x = a - b / 3 + c * 2 - 1
    y = a - b / (3 + c) * (2 - 1)
    z = a - (b / (3 + c) * 2) - 1
    print("X = ", x)
    print("Y = ", y)
    print("Z = ", z)

def conversion(a):
    print("a is type of:",type(a))
    if type(a)==str:
        print("float:",float(a))
    elif type(a)==int:
        print("str:",str(a))
    elif type(a)==float:
        print("add to float 1.1:",(a+1.1))


#conversion(5.5)
#operations(5)
