# Day       Time    Rank  Score       Time    Rank  Score
#   9   00:18:29    3545      0   00:24:27    3492      0

with open( "day09.txt", "r") as file:
    lines = [line.strip() for line in file]

p2 = False
allnums = []

for line in lines:
    nums = [int(x) for x in line.split()]
    if p2:
        nums = [int(x) for x in line.split()[::-1]]
    allnums.append(nums)

alldiffs= []
for nums in allnums:
    temp = nums
    diffs = []
    diffs.append(nums)
    while True:
        diff = []
        for i in range(len(temp)-1):
            diff.append(temp[i+1] - temp[i])
        diffs.append(diff)
        if diff.count(0) == len(diff):
            break
        temp = diff
    alldiffs.append(diffs)

sum = 0
for diffs in alldiffs:
    next = 0
    for i in range(len(diffs)-1,-1,-1):
        next = next + diffs[i-1][-1]
    sum = sum + next

print(sum)
