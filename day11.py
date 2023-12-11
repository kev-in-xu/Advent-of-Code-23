# Day       Time    Rank  Score       Time    Rank  Score
#  11   00:31:39    4082      0   00:32:17    2408      0

with open( "day11.txt", "r") as file:
    lines = [line.strip() for line in file]

pt2 = False

# make map
map = []
for line in lines:
    row = [x for x in line]
    map.append(row)

# get rows to expand
noRows = []
for i in range(len(map)):
    if map[i].count('.') == len(map[i]):
        noRows.append(i)

# get columns to expand
tmap = list(zip(*map)) # learned how to use list(zip(*map)) to transpose an array

noCols = []
for i in range(len(tmap)):
    if tmap[i].count('.') == len(tmap[i]):
        noCols.append(i)

# get coords of all galaxies
coords = []
for y in range(len(map)):
    for x in range(len(map[y])):
        pt = map[y][x]
        if pt != '.':
            coord = (y,x)
            coords.append(coord)

# get dist between galaxies
sum = 0
for i in range(len(coords)):
    for j in range(i+1, len(coords)):
        iy = coords[i][0]
        ix = coords[i][1]
        jy = coords[j][0]
        jx = coords[j][1]

        dist = abs(jy-iy) + abs(jx - ix)
        for row in noRows:
            if row < max(jy, iy) and row > min(jy, iy):
                if pt2:
                    dist += 999998
                dist += 1
        for col in noCols:
            if col < max(jx, ix) and col > min(jx, ix):
                if pt2:
                    dist += 999998
                dist += 1
        sum += dist
print(sum)