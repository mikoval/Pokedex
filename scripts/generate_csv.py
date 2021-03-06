import os
import shutil

input_file = "data.txt"
input_image_dir = "images/gen4"
output = "list"

fp= open(input_file, 'r')

image_files = os.listdir(input_image_dir)

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
    pokemon_abilities = ""
    pokemon_tms = ""
    pokemon_hms= ""
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
            while len(peek_line(fp).strip()) is not 0:
                line = fp.readline().strip()
                pokemon_abilities = pokemon_abilities + line + "\n"
        elif key == "TMs":
            tms = arr[1].strip().replace(",", "").split(" ")
            for tm in tms:
                pokemon_tms = pokemon_tms + tm + "\n"
        elif key == "HMs":
            hms = arr[1].strip().replace(",", "").split(" ")
            for hm in hms:
                pokemon_hms = pokemon_hms + hm + "\n"
        else:
            if len(arr) == 2:
                pokemon_general = addLine(pokemon_general, arr[0].strip(), arr[1].strip())
                
#        if len(arr) is 2:
#            pokemon_general = addLine(pokemon_general, arr[0].strip(), arr[1].strip())
#        else:
#            pokemon_general = pokemon_general[:-1] + " "
#            pokemon_general = pokemon_general + line.strip() + "\n"
    
    
    pokemon_general_file = os.path.join(pokemon_dir, "tms.txt")
    f = open(pokemon_general_file, 'w')
    f.write(pokemon_tms)
    f.close() 
    
    pokemon_general_file = os.path.join(pokemon_dir, "hms.txt")
    f = open(pokemon_general_file, 'w')
    f.write(pokemon_hms)
    f.close() 
    
    pokemon_general_file = os.path.join(pokemon_dir, "abilities.txt")
    f = open(pokemon_general_file, 'w')
    f.write(pokemon_abilities)
    f.close() 
    
    pokemon_general_file = os.path.join(pokemon_dir, "info.txt")
    f = open(pokemon_general_file, 'w')
    f.write(pokemon_general)
    f.close() 

    in_img = os.path.join(input_image_dir, "{}.png".format(number))
    out_img = os.path.join(pokemon_dir, "img.png")
    shutil.copyfile(in_img, out_img)

    if len(line) is 0:
        break
