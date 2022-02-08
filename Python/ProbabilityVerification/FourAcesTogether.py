import random


def fourInARow(iter):
    cards = []
    for i in range(1, 14):
        for j in range(4):
            cards.append(i)

    count = 0
    for i in range(iter):
        random.shuffle(cards)
        for j in range(len(cards)):
            if cards[j] == 13:
                if cards[j + 1] == 13 and cards[j + 2] == 13 and cards[j + 3] == 13:
                    print(f"\t{cards[j]} => {cards[j+1]}, {cards[j+2]}, {cards[j+3]}")
                    count += 1
                break
    return count
