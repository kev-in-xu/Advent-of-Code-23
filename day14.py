with open( "day14.txt", "r") as file:
    lines = [line.strip() for line in file]

pt2 = True

map = [[x for x in line] for line in lines]

def roll(map):
    # check up
    for x in range(len(map)):
        for y in range(len(map[x])-1,-1,-1):
            cur = map[y][x]
            
            if cur == '.' or cur == '#':
                continue
                
            k = y
            emp = k
            while map[k][x] != '#' and k-1 > -1:
                if map[k][x] == "O":
                    k-=1
                if map[k][x] == ".":
                    map[emp][x] = map[k][x]
                    emp = k
                    k-=1
            map[emp][x] = cur
    for x in range(len(map)):
        for y in range(len(map[x])-1,-1,-1):
            cur = map[y][x]
            
            if cur == '.' or cur == '#':
                continue
                
            k = y
            emp = k
            while map[k][x] != '#' and k-1 > -1:
                if map[k][x] == "O":
                    k-=1
                if map[k][x] == ".":
                    map[emp][x] = map[k][x]
                    emp = k
                    k-=1
            map[emp][x] = cur
    if not pt2:
        return

    # check left
    for y in range(len(map)):
        for x in range(len(map[y])-1,-1,-1):
            cur = map[y][x]

            if cur == '.' or cur == '#':
                continue

            k = x
            emp = k
            while map[y][k] != '#' and k-1 > -1:
                if map[y][k] == "O":
                    k-=1
                if map[y][k] == ".":
                    map[y][emp] = map[y][k]
                    emp = k
                    k-=1
            map[y][emp] = cur
    
    #check bot
    for x in range(len(map)):
        for y in range(len(map[x])):
            cur = map[y][x]
            
            if cur == '.' or cur == '#':
                continue
                
            k = y
            emp = k
            while k < len(map)-1 and map[k][x] != '#':
                if map[k][x] == "O":
                    k+=1
                if map[k][x] == ".":
                    map[emp][x] = map[k][x]
                    emp = k
                    k+=1
            map[emp][x] = cur

    # check right
    for y in range(len(map)):
        for x in range(len(map[y])):
            cur = map[y][x]

            if cur == '.' or cur == '#':
                continue

            k = x
            emp = k
            while k < len(map)-1 and map[y][k] != '#':
                if map[y][k] == "O":
                    k+=1
                if map[y][k] == ".":
                    map[y][emp] = map[y][k]
                    emp = k
                    k+=1
            map[y][emp] = cur

def stress(map):
    sum = 0
    for y in range(len(map)):
        val = len(map) - y
        for x in map[y]:
            if x == "O":
                sum += val
    return sum


if not pt2:
    roll(map)
    print(stress(map))

if pt2:
    # inefficient algorithm to guess and check the solution:
    for i in range(1000):
        roll(map)

    guess = stress(map)

    repeats = []
    for i in range(1000):
        roll(map)
        if (stress(map)) == guess:
            repeats.append(i)

    # guess the period
    for i in range(len(repeats)):
        cycle = repeats[i+1]-repeats[0]
        if repeats.count(repeats[1] + cycle) == 1:
            break


    rem = (1000000000 % cycle) - 1 # -1 bc cycle count starts at 1

    for i in range(2000):
        roll(map)
        if (2000 + i) % cycle == rem:
            print(stress(map))
            break
