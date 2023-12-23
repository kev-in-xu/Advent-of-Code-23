from math import lcm
with open( "day20.txt", "r") as file:
    lines = [line.strip() for line in file]

modnames = []
mods = {}
states = {}
conjinputs = {}
firstOn= {}
history = {}
for line in lines:
    inputs = []
    inputstates = {}
    name, out = line.split(" -> ")
    outputs = out.split(", ")

    if name[0] == '&':
        modtype = '&'
        name = name[1:]
        for bline in lines:
            if name in bline.split(" -> ")[1]:
                inputs.append(bline.split(" -> ")[0][1:])
                inputstates[bline.split(" -> ")[0][1:]] = 0
    else:
        modtype = name[0]
        name = name[1:] if modtype != 'b' else name

    mods[name] = (modtype, outputs, inputs) if inputs else (modtype, outputs)
    states[name] = 0
    firstOn[name] = 0
    conjinputs[name] = inputstates
    modnames.append(name)

states['rx'] = 0

def button(count):
    queue = []
    queue.append(("button","broadcaster", 0))
    lows = 0
    highs = 0
    while queue:
        src, mod, sig = queue.pop(0)
        if sig == 0:
            lows += 1
        if sig == 1:
            highs += 1
            
        if mod == 'rx' or mod == 'output':
            continue

        if len(mods[mod]) == 2:
            modtype, dests = mods[mod]
        else:
            modtype, dests, inputs = mods[mod]
        if modtype == 'b': # broadcaster
            for dest in dests:
                queue.append((mod, dest,sig))
        if modtype == '%': # flip flop
            if sig == 1:
                continue
            if sig == 0:
                state = 1 - states[mod]
                states[mod] = state
                if count > 0:
                    if firstOn[mod] == 0:
                        firstOn[mod] = count
                for dest in dests:
                    queue.append((mod, dest,state))
        if modtype == '&':
            conjinputs[mod][src] = sig
            if all(conjinputs[mod][input] == 1 for input in inputs):
                states[mod] = 1
                output = 0
                if count > 0:
                    if firstOn[mod] == 0:
                        firstOn[mod] = count
            else:
                output = 1
                states[mod] = 0
            for dest in dests:
                queue.append((mod, dest,output))
        #print(states)

    return lows, highs

totallow = 0
totalhigh = 0
for i in range(10000):
    result = button(i)
    #print(result)
    totallow += result[0]
    totalhigh += result[1]

    onoff = ""
    for name in modnames:
        onoff += str(states[name])
    print(onoff)
    try:
        print("this",i, history[onoff])
        #break
    except:
        history[onoff] = states.copy()


print("i", i)
print(totallow, totalhigh)
print("product:",totallow*totalhigh)

for m in mods:
    print(m, mods[m])
print(firstOn)
print()
print(mods['vf'])
print(states)



# Getting the answer required manually inspecting patterns in the outputs.
"""{'db': 5002, 'rj': 5416, 'ff': 5032, 'rc': 5928, 'bk': 5032, 
 'xz': 5008, 'gs': 5936, 'ps': 5001, 'jr': 5008, 'pm': 5001, 
 'pn': 5044, 'nv': 5004, 'rs': 5002, 'kz': 5004, 'nd': 5928, 
 'nm': 5001, 'dg': 5012, 'vg': 5004, 'hc': 5036, 'ft': 5168, 
 'mj': 5004, 'vb': 5040, 'qd': 5036, 'pp': 6068, 'cq': 5004, 
 'sr': 5040, 'lf': 5424, 'lh': 5044, 'ls': 5004, 'tv': 5160, 
 'tf': 5008, 'mk': 5001, 'bs': 5044, 'vx': 5936, 'bn': 7777, 
 'rr': 5002, 'bv': 5008, 'hp': 5044, 'pk': 5001, 'cg': 5016, 
 'gn': 5012, 'cz': 8025, 'sl': 5004, 'broadcaster': 0, 'cf': 5004, 
 'vf': 0, 'rt': 8041, 'xl': 5044, 'sh': 5001, 'bz': 5002, 'fz': 5036, 
 'gp': 7761, 'fs': 5001, 'hf': 5001, 'vr': 5036, 'rq': 5032, 
 'sz': 5036, 'dn': 6060}

{'db': 1, 'rj': 511, 'ff': 63, 'rc': 2047, 'bk': 127, 'xz': 15, 
 'gs': 2047, 'ps': 1, 'jr': 7, 'pm': 1, 'pn': 1023, 'nv': 3, 
 'rs': 1, 'kz': 15, 'nd': 1023, 'nm': 1, 'dg': 31, 'vg': 7, 
 'hc': 63, 'ft': 255, 'mj': 31, 'vb': 127, 'qd': 255, 'pp': 2047, 
 'cq': 3, 'sr': 63, 'lf': 511, 'lh': 511, 'ls': 3, 'tv': 255, 
 'tf': 31, 'mk': 1, 'bs': 255, 'vx': 1023, 'bn': 3888, 'rr': 1, 
 'bv': 7, 'hp': 127, 'pk': 1, 'cg': 15, 'gn': 15, 'cz': 4012, 
 'sl': 7, 'broadcaster': 0, 'cf': 3, 'vf': 0, 'rt': 4020, 'xl': 63, 
 'sh': 1, 'bz': 1, 'fz': 1023, 'gp': 3880, 'fs': 1, 'hf': 1, 
 'vr': 127, 'rq': 31, 'sz': 511, 'dn': 2047}

 Extracted cycles for modules that weren't pure binary counters

'gp': 3880, 'bn': 3888, 'cz': 4012, 'rt': 4020
'gp': 7761, 'bn': 7777, 'cz': 8025, 'rt': 8041,

gp cycles every 3881
cz cycles every 4013

bn cycles every 3889
rt cycles every 4021"""

print(lcm(3889,3881,4013,4021))

"""
what I need:
 - a queue of commands
 - a graph for all the modules? or a dict? DONE
 - conj modules need indices for all their inputs DONE
 - an array that keeps track of all states DONE
 - list of historical states so I can use cycle detection?
"""
"""
% flip flop
ignores highs
flip flops with input low
output the state after flipping (a-off + input low = output high)
starts off
--------------
& conjuncion
output low if ALL input high
else output high
& inv
inverts input
--------------
broadcast
sends to all dest

button
sends to broadcast
---------------
Requires a queue of pulses
"""
