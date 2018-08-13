#!/usr/bin/env python3
import sys
import datetime

hello = "hello"
world = "world"
msg = "{0} {1} {2}".format(hello,world,datetime.date(2000,1,1))
print(msg)

keyboardInput = input("Enter an integer: ")
number = float(keyboardInput)
if (number == 100):
    print("your number is 100!")
elif (number < 100):
    print("your number is less than 100!")
else:
    print("your number is bigger than 100!")

amount = float(input("Enter amount: "))
inrate = float(input("Enter Interest rate: "))
period = int(input("Enter period: "))
value = 0
year = 1
while year <= period:
    value = amount + (inrate * amount)
    print("Year %d Rs. %.2f" % (year, value))
    amount = value
    year = year + 1

