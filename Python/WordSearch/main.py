import sys

matrix_Size = 3

onlyNumbers = True
for number in sys.argv:
    if not number.isdigit():
        onlyNumbers = False
        break

if len(sys.argv) < matrix_Size ** 2 + 1:
    print("There are not enough numbers")
    sys.exit(1)
elif not onlyNumbers:
    print("Please only give numbers as arguments")
    sys.exit(1)
else:
    main()
