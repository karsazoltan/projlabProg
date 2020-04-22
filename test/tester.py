import os
import subprocess
from subprocess import PIPE
import time

succesful = 0
nr = 44
path = ''
test_folder = 'tesztek'
this_folder = os.listdir("./")
directories = []

for directory in this_folder:
  if os.path.isdir(directory):
      this_folder.append(directories)


for directory in directories:
    index = directory[:2]
    stdout_ouptut = directory[-1] == '1'

    cmd = open(os.path.join(this_folder + test_folder + index + '-cmd.txt'))
    p = subprocess.Popen(['java -jar', 'game.jar'], stdin=PIPE, stdout=PIPE)
    for command in cmd:
        p.stdin.write(bytes(command + '\n', 'utf-8'))
    cmd.close()

    
    expected = None 
    if stdout_ouptut:
        expected = p.stdout
    else:
        expected = open(os.path.join(this_folder + test_folder + index + '-expected.txt', 'r'))

    okay = True
    with open(os.path.join(this_folder + test_folder + index + '-expected.txt', 'r')) as generated:
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