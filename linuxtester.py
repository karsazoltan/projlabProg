#!/usr/bin/python3

import os
import subprocess
from subprocess import PIPE, STDOUT
import time
import sys
from threading import Thread
import threading

javapath = "/usr/lib/jvm/jdk-13.0.1/bin/java"   # Ez új, azért, mert nekem a default java a java8

succesful = 0
nr = 44
path = ''
test_folder = 'tesztek'
tests = os.listdir("tesztek/")
full_path = os.path.abspath(os.getcwd())
directories = []
start = 0
end = 42

if len(sys.argv) == 3:
    start = int(sys.argv[1]) - 1   # -1, mert az indexek eltolódnak
    end = int(sys.argv[2]) - 1     # -1 itt is


tests = sorted(tests, key = lambda x:int(x[:2]))
for directory in tests:
    directories.append(directory)


for directory in directories[start:end + 1]:
    index = directory[:2].strip(' ')
    stdout_ouptut = directory[-1] == '1'

    path = os.path.join(full_path, test_folder, directory, index + '-cmd.txt')
    cmd = open(path, 'r')
    full_cmd = ''
    p = subprocess.Popen([javapath, '-jar', 'projlabProg.jar'], stdin=PIPE, stdout=PIPE, stderr=STDOUT)   # Itt a commandot listként adom meg
    for command in cmd.readlines():
        if 'load' in command:
            command = 'load ' + os.path.join(test_folder, directory, index + '-in.txt\n')  # Itt meg a savenél a parancs végére teszek \n-t
        elif 'save' in command:
            command = 'save ' + os.path.join(test_folder, directory, index + '-out.txt\n') # \n
            p.communicate(bytes(command, 'utf-8'))
            break
        p.stdin.write(bytes(command, 'utf-8'))  # Viszont itt cserébe *nem* teszek \n-t a parancs végére (valszeg a cmd.readlines() \n-nel együtt ad sort)
    cmd.close()




    okay = True
    ep = os.path.join(full_path, test_folder, directory, index + '-expected.txt')
    gp = os.path.join(full_path, test_folder, directory, index + '-out.txt')

    try:                                                                    # try blokk, hogy ne dobja el magát print parancsra
        with open(ep, 'r') as expected, open(gp, 'r') as generated:    
            expected_lines = [line for line in expected]
            generated_lines = [line for line in generated]

            if len(expected_lines) != len(generated_lines):
                okay = False
            
            if okay:
                for e, g in zip(expected_lines, generated_lines):
                    e = e.strip('\n').replace(' ', '')
                    g = g.strip('\n').replace(' ', '')
                    if sorted(e) != sorted(g):
                        print(e)
                        print(g)
                        okay = False
                        break
    except FileNotFoundError:                                               # catch blokk, azért el nem fogadjuk
        print('Nincs output file: ' + index)
        okay = False


    if okay:
        succesful += 1
        print('[INFO] Test ' + index + '. succeeded.')
    else:
        print('[WARNING] Test ' + index + '. failed.')    

if succesful == end - start + 1:
    print('[DONE] Every test was succesful.')
else:
    print('[DONE] Task failed succesfully.')
