import random
import sys

DIE_SIZE = 20


def main(numOfData):
    probDist = []
    for _ in range(DIE_SIZE):
        probDist.append(0)

    for _ in range(numOfData):
        probDist[roll() - 1] += 1

    display(probDist, numOfData)


def roll():
    DC = 1
    die = random.randint(1, DIE_SIZE)
    while die > DC:
        die = random.randint(1, DIE_SIZE)
        DC += 1
    return DC


def display(list, total):
    print("i    |  list[i]  |  list[i]/total")
    print("----------------------------------")
    mean = 0
    for i in range(len(list)):
        print(f"{i+1}    |     {list[i]}    |     {list[i]/total}")
        mean += (i+1)*list[i]/total
    print("MEAN = ", mean)


if __name__ == '__main__':
    numOfData = int(sys.argv[1])
    main(numOfData)
