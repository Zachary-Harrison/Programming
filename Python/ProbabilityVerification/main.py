import sys
from FourAcesTogether import fourInARow

if __name__ == '__main__':
    iter = int(sys.argv[1])
    total = 0
    NUM_OF_TRIES = 100
    for i in range(NUM_OF_TRIES):
        count = fourInARow(iter)
        total += count
        print(f"{count}/{iter} = {count / iter}")
    print(f"\nTOTAL: {total}/{NUM_OF_TRIES * iter} = {total / (NUM_OF_TRIES * iter)}")
