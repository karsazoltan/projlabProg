import os
import subprocess
from subprocess import PIPE, STDOUT
import time
import sys


succesful = 0
nr = 44
path = ''
test_folder = 'tesztek'
tests = os.listdir("tesztek/")
full_path = os.path.abspath(os.getcwd())
directories = []
start = 0
end = 44


tests = sorted(tests, key = lambda x:int(x[:2]))
for directory in tests:
    directories.append(directory)


for directory in directories[start:end + 1]:
    index = directory[:2].strip(' ')
    stdout_ouptut = directory[-1] == '1'
    
    cmd = open(os.path.join(full_path, test_folder, directory, index + '-cmd.txt'), 'r')
    p = subprocess.Popen('java -jar projlabProg.jar', stdin=PIPE, stdout=PIPE, stderr=STDOUT)

    for command in cmd.readlines():
        if 'load' in command:
            command = 'load ' + test_folder + '\\' + directory + '\\' + index + '-in.txt'
        elif 'save' in command:
            command = 'save ' + test_folder + '\\' + directory + '\\' + index + '-out.txt'
        p.stdin.write(bytes(command + '\n', 'utf-8'))
    p.stdin.write(bytes('exit\n', 'utf-8'))
    cmd.close()


    okay = True
    ep = os.path.join(full_path, test_folder, directory, index + '-expected.txt')
    gp = os.path.join(full_path, test_folder, directory, index + '-out.txt')

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

    if okay:
        succesful += 1
        print('[INFO] Test ' + index + '. succeeded.')
    else:
        print('[INFO] Test ' + index + '. failed.')    

if succesful == end - start + 1:
    print('[DONE] Every test was succesful.')
else:
    print('[DONE] Task failed succesfully.')