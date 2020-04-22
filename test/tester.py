import os
import subprocess
from subprocess import PIPE
import time

succesful = 0
nr = 44
path = '' # TODO: set this

for i in range(nr):
    cmd = open(path + str(i) + '-cmd.txt')

    p = subprocess.Popen(['java -jar', 'game.jar'], stdin=PIPE, stdout=PIPE)
    for command in cmd:
        p.stdin.write(bytes(command + '\n', 'utf-8'))

    cmd.close()
    with open(path + str(i) + '-expected.txt', 'r') as expected, open(path + str(i) + '-out.txt', 'r') as generated:
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
        print('[INFO] Test Nr. ' + str(i) + ' succeeded.')
    else:
        print('[INFO] Test Nr. ' + str(i) + ' failed.')    


if succesful == nr:
    print('[DONE] Every test was succesful.')
else:
    print('[DONE] Task failed succesfully.')