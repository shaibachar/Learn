#!/usr/bin/env python3

def average_of_numbers(N=5, sum=0, count=0):
    for i in range(0,N):
        number = float(input("%d:" % i))
        sum = sum + number
        count = count + 1
    average = float(sum)/N
    print("N= %d , Sum= %f, average=%f" % (N,sum,average))

def swap(*,a,b):
    a,b = b, a
    print("a={0}, b={1}".format(a,b))

swap(a=5,b=6)
swap(a="world",b="hello")
average_of_numbers(10)
