# Day       Time    Rank  Score       Time    Rank  Score
#  18   00:23:53    1068      0   01:08:18    1348      0

# pt1 was originally implemented with an array
# had to relearn shoelace formula for pt2
#   figuring out perimeter counting with shoelace was the tricky part
with open( "day18.txt", "r") as file:
    lines = [line.strip() for line in file]

input = [line.split() for line in lines]

pt2 = True

# 0 means R, 1 means D, 2 means L, and 3 means U.
dirs = [(0,1), (1,0), (0,-1),(-1,0)]
dirdict = {"R": 0, "D": 1, "L": 2, "U": 3}
 
instr = []
for line in input:
    dir, dist, hex = line
    if pt2: # parse hex
        dist = hex[2:7]
        dist = int(dist,16)
        dir = int(hex[7])
        instr.append((dir,dist))
    else:
        dir = dirdict[dir] # reassigned "dir" from str to int
        instr.append((dir,int(dist)))

newholes = []
y = 0
x = 0
perimeter = 0
for i in instr:
    dir, dist = i
    perimeter += dist
    x += dirs[dir][1] * dist
    y += dirs[dir][0] * dist
    newholes.append((y,x))

prevy, prevx = newholes[-1]
area = 0
for hole in newholes:
    y, x = hole
    pos = prevy + y
    neg = prevx - x
    area += (prevy + y) * (prevx - x) / 2 # shoelace formula (trapezoid)
    prevx = x
    prevy = y

# extra accounting for perimeter because shoelace 
#   usually works with vertices not corner cells
print(int(area + (perimeter / 2 + 1))) 