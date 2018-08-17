#!/usr/bin/env python3


def fibonacciSeries():
    a, b = 0, 1
    while b < 100:
        print(b)
        a, b = b, a + b

def printArr(a,start,end):
    if start < 0 or start > len(a):
        raise ValueError("start value should be between 0 and a length")
    if end < 0 or end > len(a):
        raise ValueError("end value should be between 0 and a length")
    
    b=a[start:end]
    for i in range(0,len(b)):
        print("{0}:{1}".format(i,b[i]))

a = ['shai','bachar','hello','world']
printArr(a,3,4)


fibonacciSeries()
