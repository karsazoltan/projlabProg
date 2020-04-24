import os
import subprocess
from subprocess import PIPE
import time

succesful = 0
nr = 44
path = ''
test_folder = 'tesztek'
tests = os.listdir("tesztek/")
full_path = os.path.abspath(os.getcwd())
directories = []


for directory in tests:
    directories.append(directory)


for directory in directories:
    index = directory[:2].strip(' ')
    stdout_ouptut = directory[-1] == '1'
    
    cmd = open(os.path.join(full_path, test_folder, directory, index + '-cmd.txt'), 'r')
    p = subprocess.Popen('java -jar projlabProg.jar', stdin=PIPE, stdout=PIPE)

    for command in cmd.readlines():
        if 'load' in command:
            command = 'load ' + os.path.join(full_path, test_folder, directory, index + '-in.txt')
        if 'save' in command:
            command = 'save ' + '"' + os.path.join(full_path, test_folder, directory, index + '-out.txt') + '"'
        print(command)
        p.stdin.write(bytes(command + '\n', 'utf-8'))
    cmd.close()

    
    generated = None 
    if stdout_ouptut:
        generated = p.stdout
    else:
        generated = open(os.path.join(full_path, test_folder, directory, index + '-out.txt'), 'r')

    okay = True
    with open(os.path.join(full_path, test_folder, directory, index + '-expected.txt'), 'r') as expected:
        expected_lines = [line for line in expected]
        generated_lines = [line for line in generated]

        if len(expected_lines) != len(generated_lines):
            okay = False
        
        if okay:
            for e, g in zip(expected_lines, generated_lines):
                if e != g:
                    okay = False
                    break

    if okay:
        succesful += 1
        print('[INFO] Test Nr. ' + index + ' succeeded.')
    else:
        print('[INFO] Test Nr. ' + index + ' failed.')    


if succesful == nr:
    print('[DONE] Every test was succesful.')
else:
    print('[DONE] Task failed succesfully.')