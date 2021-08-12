import keyboard

#basic
x = lambda a : a + 10
print(x(5))

#double arguments
y = lambda a,b : a * b
print(y(4,5))

#lambda functions are best shown them as anonymous functions inside another func
def myfunc(n):
    return lambda a: a*n
mydoubler = myfunc(2)
print(mydoubler(11))
# print(myfunc(2)) prints an address
