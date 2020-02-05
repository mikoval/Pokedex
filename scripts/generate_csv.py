import os
import shutil

input_file = "data.txt"
output = "list"

fp = open(input_file, 'r')

    
if os.path.exists(output):
    shutil.rmtree(output)
    #os.rmdir(output)
os.mkdir(output)

def NumberName(n):
    n = int(n)
    name = ""
    for i in range(3):
        r = n % 10 
        n = n / 10
        name = str(r) + name
    return name

def ProcessTitle(line):
    arr = line.split(' ')
    number = (arr[0])[:-1]
    name = arr[1]
    types = (arr[2])[1:-2]
    types = types.split('/')
    type1 = types[0]
    type2 = ""
    if len(types) is 2:
        type2 = types[1]
    return number, name, type1, type2

def addLine(base, title, value):
    return base + "{}:{}\n".format(title, value)

def peek_line(f):
    pos = f.tell()
    line = f.readline()
    f.seek(pos)
    return line

while True:
    number, name, type1, type2 = ProcessTitle(fp.readline())
    print "{} = {} : {}  /  {}".format(number, name, type1, type2)
    pokemon_dir = os.path.join(output, NumberName(number) + "_" + name)
    os.mkdir(pokemon_dir)
    pokemon_general = ""
    pokemon_general = addLine(pokemon_general, "number", str(number))
    pokemon_general = addLine(pokemon_general, "name", name)
    pokemon_general = addLine(pokemon_general, "type_primary", type1)
    pokemon_general = addLine(pokemon_general, "type_secondary", type2)
    line = fp.readline()
    while len(line.strip()) is not 0:
        line = fp.readline() 
        arr = line.split(':')
        key = arr[0].strip()
        if key == "Pokedex":
            pokemon_general = addLine(pokemon_general, arr[0].strip(), arr[1].strip())
            peek = peek_line(fp)
            if len(peek.split()) is not 2:
                line = fp.readline() 
                pokemon_general = pokemon_general[:-1] + " "
                pokemon_general = pokemon_general + line.strip() + "\n"
        elif key == "Abilities":
            pass
        elif key == "TMs":
            pass
        elif key == "HMs":
            pass
        else:
            if len(arr) == 2:
                pokemon_general = addLine(pokemon_general, arr[0].strip(), arr[1].strip())
                
#        if len(arr) is 2:
#            pokemon_general = addLine(pokemon_general, arr[0].strip(), arr[1].strip())
#        else:
#            pokemon_general = pokemon_general[:-1] + " "
#            pokemon_general = pokemon_general + line.strip() + "\n"
    
    
    pokemon_general_file = os.path.join(pokemon_dir, "info.txt")
    f = open(pokemon_general_file, 'w')
    f.write(pokemon_general)
    f.close() 


    if len(line) is 0:
        break
