with open( "day12.txt", "r") as file:
    lines = [line.strip() for line in file]

t = []
for line in lines: # parse input
    spring, nums = line.split(" ")
    t.append((spring, nums))

pt2 = True

# HashTable class from https://intellipaat.com/blog/what-is-hash-table/
class HashTable:
    def __init__(self, size):
        self.size = size
        self.buckets = [[] for _ in range(self.size)]
        
    def hash_function(self, key):
        return hash(key) % self.size
    
    def set_value(self, key, value):
        index = self.hash_function(key)
        found = False
        for i, kv in enumerate(self.buckets[index]):
            k, v = kv
            if key == k:
                self.buckets[index][i] = (key, value)
                found = True
                break
        if not found:
            self.buckets[index].append((key, value))
    
    def get_value(self, key):
        index = self.hash_function(key)
        for k, v in self.buckets[index]:
            if key == k:
                return v
        return None

# determines whether a set of springs with unknowns can match 
# a set of known springs that are either working or broken
def canBe(str1, str2):
    for i in range(len(str1)):
        if str2[i] != '?' and str2[i] != str1[i]:
            return False
    return True

# recursive method with memoization
def recFind(pair, ht):
    spring = pair[0]
    pattern = pair[1]

    if not pattern:
        if spring.count("#") == 0: # no more broken springs in either springs nor pattern
            return 1
        else:
            return 0
    if not spring or len(pattern[0]) > len(spring): # i.e. more broken but no more springs
        return 0
    
    # check hash table to retrieve value and return
    count = ht.get_value(spring + ''.join(pattern))
    if count != None:        
        return count
    else:
        count = 0

    # if not, then go through calculation
    if canBe(pattern[0],spring[0:len(pattern[0])]): # check if pattern is possible
        count += recFind((spring[len(pattern[0]):], pattern[1:]), ht)
        if spring[0] != '#': # don't recurse if "#" bc all broken springs need to be accounted for
            count += recFind((spring[1:], pattern), ht)
    else:
        # means spring is broken but still doesn't fit pattern
        if spring[0] == '#': 
            ht.set_value(spring + ''.join(pattern),count)
            return 0
        else:
            count += recFind((spring[1:], pattern), ht)

    # after finding result, insert into hash table
    ht.set_value(spring + ''.join(pattern),count)

    return count

valid = 0
for pair in t:
    if pt2:
        spring = (pair[0] + "?") * 4 + pair[0] # last element does not have '?' after it
        nums = [int(x) for x in pair[1].split(",")] * 5
    else:
        spring = pair[0]
        nums = [int(x) for x in pair[1].split(",")]

    patts = [('#' * num + '.') for num in nums[:-1]]
    patts.append('#' * nums[-1]) # last element does not require a '.' after it

    htable = HashTable(1017)
    result = recFind((spring, patts),htable)
    valid += result

print(valid)
