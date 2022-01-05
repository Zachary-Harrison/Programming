import array


def SayHello():
    print("Hello World!")


class MyClass:
    pass

print(int("1111", base=6))
b = 3.5
x = 5
x = abs(-5)
a = array.array('i', [1, 2, 3])
MyInstance = MyClass()

print("My name is Zachary Harrison")

if x == 5:
    print("...and I am a big boy now")

print("Here's a for loop counting up: ")
for i in range(0, x):
    print(i)

print("Here's a for loop counting down: ")
for i in range(x, 0, -1):
    print(i)

print("Here's a for loop outputting array (with bad concatenation): ")
for i in range(0, x - 2, 1):
    print("i: ", end="")
    print(i)
    print("a[", end="")
    print(i, end="]: ")
    print(a[i])

print("Here's a for loop outputting array (with good concatenation): ")
for i in range(0, x - 2, 1):
    print("i: " + str(i))
    print("\ta[" + str(i) + "]: " + str(a[i]))

print("Here's a method that says hi")
SayHello()

print(int(b))
print(round(b))
print("That will be all. Thank you.")

print(eval("\"" + input("enter something (without quotation marks): ") + "\""))
