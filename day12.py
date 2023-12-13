from itertools import permutations
from itertools import combinations
from itertools import combinations_with_replacement
import itertools
import math

with open( "day12.txt", "r") as file:
    lines = [line.strip() for line in file]

t = []
for line in lines:
    spring, nums = line.split(" ")
    t.append((spring, nums))


"""# find where first num can go
valid = 0
for pair in t:
    spring = pair[0]
    nums = [int(x) for x in pair[1].split(",")]
    print(spring,nums)

    size = len(spring)
    dmgcount = spring.count('#')
    unknowns = spring.count('?')
    breaks = sum(nums)

    remdmg = breaks - dmgcount

    print(breaks)
    print(remdmg)


    poss = list(combinations(range(unknowns), remdmg))
    
    for p in poss:
        dmgcount = 0
        #print(p)
        temp = [s for s in spring]
        #print(temp)
        
        for i in range(len(temp)):
            if temp[i] == '?':
                if p.count(dmgcount) == 1:
                    temp[i] = "#"
                dmgcount += 1
                    
        #print(temp)             
        #print(temp)

        newdmg = [ sum( 1 for _ in group ) for key, group in itertools.groupby(temp) if key == '#' ]
        #print(newdmg)
        if newdmg == nums:
            valid += 1
            print(p)
            print(*temp)

print(valid)"""


def equals(str1, str2):
    for i in range(len(str1)):
        if str2[i] != '?' and str2[i] != str1[i]:
            return False
    return True


# can optimize by keeping track of total string length

def tryAll(spr, patts, remlength):
    if patts == []:
        return 1
    
    if remlength > len(spr):
        # print(len(spr), remlength)
        return 0
    
    numstr = patts[0]

    total = 0
    for i in range(len(spr)-len(numstr)):
        slice = spr[i:i+len(numstr)]
        if equals(numstr, slice):
            total += tryAll(spr[i+len(slice):], patts[1:], remlength-len(numstr))
    return total

# find where first num can go
valid = 0
for pair in t:
    spring = pair[0]
    nums = [int(x) for x in pair[1].split(",")]
    nums = nums * 5

    spring = (spring + "?") * 4 + spring
    print(spring,nums)

    size = len(spring)
    dmgcount = spring.count('#')
    unknowns = spring.count('?')
    breaks = sum(nums)

    remdmg = breaks - dmgcount


    #poss = list(combinations(range(unknowns), remdmg))

    temp = [s for s in spring]

    """newlist = [ sum( 1 for _ in group ) for key, group in itertools.groupby(temp)]
    keylist = [ key for key,group in itertools.groupby(temp)]
    grouplist = list(zip(newlist,keylist))
    print(*grouplist)"""

    patts = []
    for num in nums[:-1]:
        patts.append('#' * num + '.')
    patts.append('#' * nums[-1])    
    
    remlength = sum([len(x) for x in patts])

    valid += tryAll(spring,patts,remlength)
    
    print(valid)

    """
    new idea:
    translate nums into list of substirngs with ####. ###. #. #. #. etc. DONE
    write TryAll recursive method:
        if substring length > len(string):
            return 0:
        if last substring and finds valid spot:
            return 1: 

        DONE
        
        sum = 0
        # try to place string down:
        go from spring "start" index:
            if numlist substring matches spring.substring() match (with replacement of ? can match for both . and #):
                sum +=  tryall(string.substring(len(num substring)), substringlist[1:])
        return sum
    """
print()
