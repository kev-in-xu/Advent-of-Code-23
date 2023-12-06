

with open( "day06.txt", "r") as file:
    lines = [line.strip() for line in file]
    
print(lines)


times = int(lines[0].split(":")[1].strip().replace(" ",""))
dists = int(lines[1].split(":")[1].strip().replace(" ",""))

for j in range(times):
    if j * (times - j) > dists:
        print(j)
        print(str(j) + " " + str(times - j))
        break

for k in range(times-1,0,-1):
    if k * (times - k) > dists:
        print(k)
        print(str(k) + " " + str(times - k))
        break

print(k-j+1)



# PART 1
"""times = [int(x) for x in lines[0].split(":")[1].strip().split()]
dists = [int(x) for x in lines[1].split(":")[1].strip().split()]

prod = 1

for i in range(4):
    ways = 0
    for j in range(times[i]):
        if j * (times[i] - j) > dists[i]:
            ways = ways + 1
            print(str(j) + " " + str(times[i] - j))
    prod = prod * ways

print(prod)"""

