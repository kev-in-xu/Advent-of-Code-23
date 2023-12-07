from functools import cmp_to_key

p2 = True # change this to false for p1 solution

with open( "day07.txt", "r") as file:
    lines = [line.strip() for line in file]

hands = []
bids = []
for line in lines:
    handBid = line.split()
    hands.append(handBid[0])
    bids.append(handBid[1])

dict = {"1": 1,
        "2": 2,
        "3": 3,
        "4": 4,
        "5": 5,
        "6": 6,
        "7": 7,
        "8": 8,
        "9": 9,
        "T": 10,
        "J": 11,
        "Q": 12,
        "K": 13,
        "A": 14}

if p2 == True:
    dict["J"] = 0

# gives type of hand, not actual rank
def rank(handValue):
    counts = [0] * 14
    jcount = 0
    for v in handValue:
        if v == 0:
            jcount = jcount + 1
        else:
            counts[v-1] = counts[v-1] + 1
    
    if jcount != 0:
        if jcount >= 4: # 5 of a kind
            return 1
        if jcount == 3: # 5 of a kind
            if counts.count(2) == 1:
                return 1
            else:
                return 2 # 4 of a kind
        if jcount == 2:
            if counts.count(3) == 1:
                return 1 # 5 a kind
            if counts.count(2) == 1:
                return 2 # 4 a kind
            return 4
        if jcount == 1:
            if counts.count(4) == 1:
                return 1 # 5 a kind
            if counts.count(3) == 1:
                return 2 # 4 a kind
            if counts.count(2) == 2:
                return 3 # full house
            if counts.count(2) == 1:
                return 4 # 3 a kind
            return 6 # pair

    if counts.count(5) == 1:
        return 1
    if counts.count(4) == 1:
        return 2
    if counts.count(3) == 1:
        if counts.count(2) == 1:
            return 3
        return 4
    if counts.count(2) == 2:
        return 5
    if counts.count(2) == 1:
        return 6
    return 7

# assigns rank by type of hand
ranks = []
for hand in hands:
    handValue = [dict[a] for a in hand]
    ranks.append(rank(handValue))

# packages all three into one object:
hbr = []
hbrs = []
for i in range(len(hands)):
    hbr = [hands[i],
           bids[i],
           ranks[i]]
    hbrs.append(hbr)

def compare(arg1, other):
    if arg1[2] > other[2]:
        return -1
    if arg1[2] < other[2]:
        return 1
    # if same rank, compare first card, then second, then third...
    s = [dict[a] for a in arg1[0]]
    o = [dict[a] for a in other[0]]
    for i in range(len(s)):
        if s[i] > o[i]:
            return 1
        if s[i] < o[i]:
            return -1
    return 0 # never called

hbrs = sorted(hbrs, key=cmp_to_key(compare))

print(hbrs)

sum = 0
for i in range(len(hbrs)):
    value = (i+1) * int(hbrs[i][1])
    #print(str(i+1) + " " + str(int(hbrs[i][1])) + " " + str(value))
    sum = sum + value

print(sum) 


"""
Every hand is exactly one type. From strongest to weakest, they are:

Five of a kind, where all five cards have the same label: AAAAA
Four of a kind, where four cards have the same label and one card has a different label: AA8AA
Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
High card, where all cards' labels are distinct: 23456
"""


    


        




