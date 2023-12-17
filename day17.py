# Day       Time    Rank  Score       Time    Rank  Score
#  17   01:13:02    1779      0   01:46:37    1958      0

# nice p-queue impementation after learning about them in data structures
# pt2 can be improved by moving a minimum of 4 steps after each turn
from heapq import heappop, heappush

with open( "day17.txt", "r") as file:
    lines = [line.strip() for line in file]

pt2 = True

arr = [[int(char) for char in line] for line in lines]
vis = [[[] for _ in line] for line in lines]
rows = len(arr[0])
cols = len(arr)
dirs = [(-1,0), (0,1), (1,0), (0,-1)]
# for dir: up = 0, right = 1, down = 2, left = 3

li = []
# 5-tuple: total (heat loss), y, x, dir, (step) count (in same dir)
heappush(li,(0,0,1,1,1))
heappush(li,(0,1,0,2,1))

while li:
    total, y, x, dir, count = heappop(li)

    #check if visited from same direction and stepcount
    if vis[y][x] and (dir, count) in vis[y][x]:
        continue
    vis[y][x].append((dir, count))

    total += arr[y][x] # adds heat loss to total

    if y == cols-1 and x == rows-1: # checks completion condition
        if not pt2 or count >= 4: # pt2 requires at least 4 in a row
            break

    valid = [] # checks which adjacent cells are valid
    for i in range(4):
        # check oob
        if (y + dirs[i][0] >= cols or y + dirs[i][0] < 0 or 
            x + dirs[i][1] >= rows or x + dirs[i][1] < 0):
            continue
        if i == (dir + 2) or i == (dir - 2): # check reverse
            continue

        if pt2: # pt2 checks
            if count < 4 and i != dir: # check min limit for same dir
                continue
            if count == 10 and dir == i: # check max limit for same dir
                continue

        else:  # pt1 checks
            if dir == i and count == 3: # check for 3 in a row
                continue
        
        val = arr[y + dirs[i][0]][x + dirs[i][1]]
        valid.append((val,i))
    valid.sort() # sorts by minimum heat loss of new cell

    for v in valid:
        newdir = v[1]
        if newdir == dir: # going in same direction
            heappush(li,(total, y + dirs[newdir][0], x + dirs[newdir][1], newdir, count+1))
        else:
            heappush(li,(total, y + dirs[newdir][0], x + dirs[newdir][1], newdir, 1))

print(total)
