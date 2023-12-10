# Day       Time    Rank  Score       Time    Rank  Score
#  10   00:26:30    1038      0   01:41:48    1914      0

with open( "day10.txt", "r") as file:
    lines = [line.strip() for line in file]

map = []
for line in lines:
    row = [x for x in line]
    map.append(row)

pmap = []
for line in lines:
    row = [x for x in line]
    pmap.append(row)

startX = 0
startY = 0
found = False
for y in range(len(map)):
    for x in range(len(map[y])):
        if map[y][x] == 'S':
            startX = x
            startY = y
            found = True
    if found:
        break

# starting node and direction is one step away S
# done through visual inspection of input around S

curX = startX
curY = startY-1
dir = 0
steps = 1

while True:
    pipe = map[curY][curX]
    pmap[curY][curX] = 1
    if pipe == 'S':
        steps = steps + 1
        break
    if dir == 0:
        if pipe == '|':
            curY=curY-1
        elif pipe == 'F':
            curX=curX+1
            dir = 1
        elif pipe == '7':
            curX=curX-1
            dir = 3
    elif dir == 1:
        if pipe == '-':
            curX=curX+1
        elif pipe == 'J':
            curY=curY-1
            dir = 0
        elif pipe == '7':
            curY=curY+1
            dir = 2
    elif dir == 2:
        if pipe == '|':
            curY=curY+1
        elif pipe == 'J':
            curX=curX-1
            dir = 3
        elif pipe == 'L':
            curX=curX+1
            dir = 1
    elif dir == 3:
        if pipe == '-':
            curX=curX-1
        elif pipe == 'L':
            curY=curY-1
            dir = 0
        elif pipe == 'F':
            curY=curY+1
            dir = 2
    steps = steps + 1

print("pt1 = " + str(int(steps / 2)))

#pt2
inside = 0
prev = '|'
for y in range(len(map)):
    walls = 0
    for x in range(len(map[y])):
        pipe = map[y][x]
        if pmap[y][x] != 1:
            if walls % 2 == 1: # odd num of walls means inside
                inside = inside + 1
        else:
            if pipe == '|' or pipe == 'S':
                walls = walls + 1
            elif pipe == 'F' or pipe == 'L':
                prev = pipe
            elif pipe == '7':
                if prev == 'L':
                    walls = walls + 1
            elif pipe == 'J':
                if prev == 'F':
                    walls = walls + 1

print("pt2 = " + str(inside))    