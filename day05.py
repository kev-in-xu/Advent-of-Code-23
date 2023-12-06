import sys
import copy

with open( "day05.txt", "r") as file:
    lines = []
    for line in file:
        lines.append(line)

seeds = [int(x) for x in lines[0].split(":")[1].split()]

maps = []

# 3-30 is seed to soil
seedSoil = []
for i in range(3,31):
    ints = [int(x) for x in lines[i].split()]
    seedSoil.append(ints)
seedSoil.sort()

# 33-42
soilFert = []
for i in range(33,43):
    ints = [int(x) for x in lines[i].split()]
    soilFert.append(ints)
soilFert.sort()

# 45-53, fert water
fertWater = []
for i in range(45,54):
    ints = [int(x) for x in lines[i].split()]
    fertWater.append(ints)
fertWater.sort()

# 56-78 water light
waterLight = []
for i in range(56,79):
    ints = [int(x) for x in lines[i].split()]
    waterLight.append(ints)
waterLight.sort()

# 81-112 light temp
lightTemp = []
for i in range(81,113):
    ints = [int(x) for x in lines[i].split()]
    lightTemp.append(ints)
lightTemp.sort()

# 115-159 temp humid
tempHumid = []
for i in range(115,160):
    ints = [int(x) for x in lines[i].split()]
    tempHumid.append(ints)
tempHumid.sort()

# 162-210 humid loc
humidLoc = []
for i in range(162,211):
    ints = [int(x) for x in lines[i].split()]
    humidLoc.append(ints)
humidLoc.sort()

maps = [seedSoil,
        soilFert,
        fertWater,
        waterLight,
        lightTemp,
        tempHumid,
        humidLoc]


# index 0 is destination, 1 is source, 2 is range

# PART 2

# IDEA: start tracing backwards, with location = 0 and incrementing
# seeing which seed I end up at, and checking if that is a valid seed

# Attempt 3

# Make valid seed list
# JK don't make list, just check the rules

# OLD: index 0 is destination, 1 is source, 2 is range
# NEW: index 0 is source, 1 is destination, 2 is range

# this is still slow but slightly better?
# I could make it so that if f(input + 1) = f(input) + 1, 
# then I change the increment to something larger, and then if it becomes different i stop..?

#permLoc = 0
permLoc = 99700000
prev = 0
found = False
while True:
    loc = permLoc
    for i in range(len(maps)-1,-1,-1):
        for rule in maps[i]:
            start = rule[0]
            if loc >= start and loc <= start + rule[2] -1:
                loc = loc + rule[1] - start
                break
    j = 0

    while j < len(seeds):
        seedStart = seeds[j]
        if loc >= seedStart and loc <= seedStart + seeds[j+1] -1:
            found = True
            print(seedStart)
            print("permLoc: " + str(permLoc))
        j = j+2
        
    if found:
        break
    # print(permLoc)
    if permLoc % 100000 == 0:
        print("permLoc: " + str(permLoc) + " seed: " + str(loc))
    
    permLoc = permLoc + 1

    # Running this portion of code leads to a solution at permLoc = 99734400,
    # so actual answer must be between 99730000 and 99734400
    """if loc - prev == 1 or loc - prev == 100:
        permLoc = permLoc + 100
        prev = loc
        continue
    else:
        prev = loc
        print("mapping change")
        print(loc)
        permLoc = permLoc + 1"""
    
print("seed found: " + str(loc))





"""
first approach took too long, because it checked every rule. What I need to do is 
simplify each map into a 10^10 list where index is source, and value is dest
"""

# attempt 2, also two slow because copying 10**10 is a lot
"""vmap = []
vmaps = []
vmap = [0] * (10**10)
print("done")

for map in maps:
    temp = vmap.deepcopy()
    for v in temp:
        for rule in map:
            if v >= int(rule[1]) and v <= int(rule[1]) + int(rule[2]):
                v = v + int(rule[0]) - int(rule[1])
                break
    vmaps.append(temp)

print(len(vmaps))
print(len(rules))"""


# Attempt 1, checking all seeds agaisnt the list and getting a min
"""i = 0
# i traverses indices of seed list
while i < len(seeds):

    # j traverses seed range (every other value)
    for j in range(int(seeds[i+1])):
        seed = int(seeds[i]) + j
        for map in maps:
            for rule in map:
                if int(seed) >= int(rule[1]) and int(seed) <= int(rule[1]) + int(rule[2]):
                    seed = int(seed) + int(rule[0]) - int(rule[1])
                    break
        if seed < minLoc:
            minLoc = seed
    i = i+2"""


# PART 1
"""
minLoc = sys.maxsize
for seed in seeds:
    for map in maps:
        for rule in map:
            if int(seed) >= int(rule[1]) and int(seed) <= int(rule[1]) + int(rule[2]):
                seed = int(seed) + int(rule[0]) - int(rule[1])
                break
    if seed < minLoc:
        minLoc = seed"""

"""
I can even append all of the maps into another list
basically what I need to do is match seed to each map
test each case to see if it fits

if seed is between source and source+range:
    modify by dest - source
    break

"""


